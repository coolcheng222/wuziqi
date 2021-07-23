package com.sealll.websocket;

import com.sealll.bean.Msg;
import com.sealll.bean.Room;
import com.sealll.bean.User;
import com.sealll.manager.RoomManager;
import com.sealll.websocket.encoder.MsgTextEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author sealll
 * @time 2021/7/13 19:34
 */
@ServerEndpoint(value="/game/{rid}/{uid}",encoders = {MsgTextEncoder.class})
@Component
public class ChessEndPoint {
    private Session sessionSelf;
//    public static Map<User, Session> sessions =  new HashMap<>();
    public static Map<User,ChessEndPoint> chessEndPointMap = new HashMap<>();
    private static RoomManager roomManager;
    private ApplicationContext ioc;

    private User user;
    @Autowired
    private void setRoomManager(RoomManager roomManager){
        ChessEndPoint.roomManager = roomManager;
    }


    public ChessEndPoint(){

    }
    public User getUser(){
        return user;
    }

    @OnOpen
    public void open(Session session, @PathParam("rid")Integer rid, @PathParam("uid")Integer uid){
        System.out.println(roomManager);
        Room room = roomManager.checkRoomCache(rid,uid);
        User user1 = roomManager.checkUserCache(rid,uid);
        try {
            if(room == null && user1 == null){
               session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT,"未通过校验"));
                return;
            }else if(room != null && user1 != null){
                session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT,"重复校验"));
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(room != null){
            roomManager.storeRoom(room);
        }

        User user = new User();
        user.setRoomid(rid);
        user.setUid(uid);
        String token = roomManager.generateToken(rid,uid);
        user.setToken(token);
        roomManager.storeUser(user);
        roomManager.removeRoomCache(rid);
        roomManager.removeUserCache(rid);
        session.getAsyncRemote().sendObject(Msg.success().extend(token));
        sessionSelf = session;
        this.user = user;
        chessEndPointMap.put(user,this);
    }
    @OnMessage
    public void message(String str){
        System.out.println(str);
    }

    @OnClose
    public void close(Session session, @PathParam("rid")Integer rid, @PathParam("uid")Integer uid){
        synchronized (ChessEndPoint.class) {
            System.out.println(rid + " closing");
            boolean roomFilled = roomManager.isRoomFilled(rid);
            boolean b = roomManager.deRoom(rid, uid);
            if (b && !roomFilled) {
                roomManager.deleteRoom(rid);
            }
            User user = new User();
            user.setUid(uid);
            user.setRoomid(rid);
            chessEndPointMap.remove(user);
        }
    }

    public void send(Msg msg){
        sessionSelf.getAsyncRemote().sendObject(msg);
    }

    public static List<ChessEndPoint> getRoom(Integer id){
        ArrayList<ChessEndPoint>  res = new ArrayList<>();
        for(User user : chessEndPointMap.keySet()){
            if(id.equals(user.getRoomid())){
                res.add(chessEndPointMap.get(user));
            }
        }
        return res;
    }



}
