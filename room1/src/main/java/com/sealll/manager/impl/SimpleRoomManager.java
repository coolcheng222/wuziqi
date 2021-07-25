package com.sealll.manager.impl;

import com.sealll.bean.Room;
import com.sealll.bean.User;
import com.sealll.config.SpringConfig2;
import com.sealll.manager.RoomManager;
import com.sealll.manager.constants.RoomConstants;
import com.sealll.manager.exception.RoomException;
import com.sealll.manager.utils.*;
import com.sealll.manager.utils.impl.SimpleAuthenticResolver;
import com.sealll.manager.utils.impl.SimpleRoomValidator;
import com.sealll.manager.utils.impl.SimpleTimeCacheMap;
import com.sealll.manager.utils.impl.SimpleTokenResolver;
import com.sealll.service.RoomService;
import com.sealll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sealll
 * @time 2021/7/9 23:01
 */
@Configuration
@ConfigurationProperties(prefix = "room")
public class SimpleRoomManager implements RoomManager {

    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;

    @Autowired
    private RoomValidator roomValidator;
    @Autowired
    private TokenResolver tokenResolver;
    @Autowired
    private AuthenticResolver authenticResolver;
    @Autowired
    private UidGenerator uidGenerator;
    //key == roomid
    private TimeCacheMap<Integer,User> userCacheMap;
    //key == roomid
    private TimeCacheMap<Integer,Room> roomCacheMap;

    private Long TTL = 100000L;
    private Integer roomSize;
    private Integer roomMax;


    public SimpleRoomManager(){
        userCacheMap = new SimpleTimeCacheMap<>();
        roomCacheMap = new SimpleTimeCacheMap<>();
    }
    @Override
    public String createRoom(Integer rid, String password) {
        System.out.println(roomMax);
        Room room1 = roomService.selectById(rid);
        Room room2 = roomCacheMap.get(rid);
        if(room1 != null || room2 != null){
            return RoomConstants.ROOM_EXIST;
        }
        if(!canMoreRoom()){
            return RoomConstants.ROOM_OUT_OF_NUM;
        }
        String s = roomValidator.passValidate(password);
        if(s != null){
            return s;
        }
        String s1 = roomValidator.ridValidate(rid);
        if(s1 != null){
            return s1;
        }
        Room room = new Room();
        Integer uid = uidGenerator.getUid(rid);
        room.setId(rid);
        room.setPassword(password);
        room.getUids().add(uid);
        roomCacheMap.storeWithTTL(rid,room, TTL);
        return uid.toString();
    }

//    private String createUser(Integer rid) {
//        try{
//            Integer uid = uidGenerator.getUid(rid);
//            if(uid != null){
//                User user = new User();
//                user.setRoomid(rid);
//                user.setUid(uid);
//                String token = tokenResolver.getToken(rid, uid);
//                user.setToken(token);
//                boolean user1 = userService.createUser(user);
//                return uid.toString();
//            }else{
//                System.out.println("??????????????");
//                return "???????????????";
//            }
//        }catch (DuplicateKeyException e){
//            return RoomConstants.ROOM_EXIST;
//        }catch (UncategorizedSQLException e){
//            return RoomConstants.ROOM_OUT_OF_NUM;
//        }
//    }

    @Override
    public boolean isRoomFilled(Integer rid) {
        userCacheMap.removeExpire();
        User user = userCacheMap.get(rid);
        return (user == null? 0: 1) + roomService.selectByIdWithUser(rid).getUids().size() >= roomSize;
    }

    @Override
    public String enRoom(Integer rid, String password) {
        try {
            boolean b = authenticResolver.checkPass(rid, password);
            if(b){
                if(isRoomFilled(rid)){
                    return RoomConstants.ROOM_FILLED;
                }
                Integer uid = uidGenerator.getUid(rid);
                if(uid != null){
                    User user = new User();
                    user.setRoomid(rid);
                    user.setUid(uid);
//                    String token = tokenResolver.getToken(rid, uid);
//                    user.setToken(token);
                    userCacheMap.storeWithTTL(rid,user,TTL);
                    return uid.toString();
                }else{
                    System.out.println("??????????????");
                    return "???????????????";
                }
//                this.createUser(rid);
            }else{
                return RoomConstants.PASSFAULT;
            }
        } catch (RoomException e) {
            return RoomConstants.ROOM_NOT_EXIST;
        }
    }

    @Override
    public boolean deRoom(Integer rid, Integer uid) {
        User user = new User();
        user.setRoomid(rid);
        user.setUid(uid);
        try {
            boolean b = userService.removeUserSelective(user);
            return b;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean deRoom(String token) {
        User user = new User();
        user.setToken(token);
        try {
            boolean b = userService.removeUserSelective(user);
            return b;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User getByToken(String token) {
        User byToken = tokenResolver.getByToken(token);
        return byToken;
    }

    @Override
    public void deleteRoom(Integer rid){
        roomService.delete(rid);
    }

    @Override
    public Room checkRoomCache(Integer rid,Integer uid) {
        Room room = roomCacheMap.get(rid);
        if(room == null){
            return null;
        }else{
            if(room.getUids().contains(uid)){
                return room;
            }else{
                return null;
            }
        }
    }

    @Override
    public void removeRoomCache(Integer rid) {
        roomCacheMap.remove(rid);
    }

    @Override
    public User checkUserCache(Integer rid,Integer uid) {
        User user = userCacheMap.get(rid);
        if(user == null){
            return null;
        }else{
            if(user.getUid().equals(uid)){
                return user;
            }else{
                return null;
            }
        }
    }

    @Override
    public void removeUserCache(Integer rid) {
        userCacheMap.remove(rid);
    }

    @Override
    public String generateToken(Integer rid, Integer uid) {
        return tokenResolver.getToken(rid,uid);
    }

    @Override
    public String storeRoom(Room room) {
        try{
            boolean b = roomService.create(room);
            return null;
        }catch (DuplicateKeyException e){
            return RoomConstants.ROOM_EXIST;
        }catch (UncategorizedSQLException e){
            return RoomConstants.ROOM_OUT_OF_NUM;
        }
    }

    @Override
    public String storeUser(User user) {
        try{
            boolean user1 = userService.createUser(user);
            return null;
        }catch (DuplicateKeyException e){
            return RoomConstants.ROOM_EXIST;
        }catch (UncategorizedSQLException e){
            return RoomConstants.ROOM_OUT_OF_NUM;
        }
    }


    @Override
    public boolean canMoreRoom() {
        roomCacheMap.removeExpire();
        int size = roomCacheMap.size();
        return roomService.selectAll().size() + size < roomMax;
    }

    @Override
    public Set<Integer> checkRooms() {
        ConcurrentHashMap.KeySetView<Integer, Room> integers = roomCacheMap.keySet();
        Set<Integer> integers1 = roomService.selectIds();
        integers1.addAll(integers);
        return integers1;
    }

    public void setRoomValidator(RoomValidator roomValidator) {
        this.roomValidator = roomValidator;
    }

    public void setTokenResolver(TokenResolver tokenResolver) {
        this.tokenResolver = tokenResolver;
    }

    public void setAuthenticResolver(AuthenticResolver authenticResolver) {
        this.authenticResolver = authenticResolver;
    }

    public void setUidGenerator(UidGenerator uidGenerator) {
        this.uidGenerator = uidGenerator;
    }

    public Long getTTL() {
        return TTL;
    }

    public void setTTL(Long TTL) {
        this.TTL = TTL;
    }

    public Integer getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(Integer roomSize) {
        this.roomSize = roomSize;
    }

    public Integer getRoomMax() {
        return roomMax;
    }

    public void setRoomMax(Integer roomMax) {
        this.roomMax = roomMax;
    }
}
