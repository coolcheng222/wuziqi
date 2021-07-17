package com.sealll.websocket;

import com.sealll.RoomApplication;
import com.sealll.bean.Room;
import com.sealll.bean.User;
import com.sealll.config.SpringConfig2;
import com.sealll.manager.RoomManager;
import com.sealll.manager.exception.RoomException;
import com.sealll.manager.impl.SimpleRoomManager;
import com.sealll.service.RoomService;
import com.sealll.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sealll
 * @time 2021/7/13 19:34
 */
@ServerEndpoint(value="/game/{rid}/{uid}")
@Component
public class ChessEndPoint {
    public static Map<User, Session> sessions =  new HashMap<>();
    private static RoomManager roomManager;
    private ApplicationContext ioc;
    @Autowired
    private void setRoomManager(RoomManager roomManager){
        ChessEndPoint.roomManager = roomManager;
    }

    public ChessEndPoint(){
        System.out.println("aaaaaaaaaaaaaaaaa");
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
            roomManager.createRoomDb(room);
        }

        User user = new User();
        user.setRoomid(rid);
        user.setUid(uid);
        String token = roomManager.generateToken(rid,uid);
        user.setToken(token);
        roomManager.createUserDb(user);

        sessions.put(user,session);
    }
    @OnMessage
    public void message(String str){
        System.out.println(str);
    }

    @OnClose
    public void close(Session session, @PathParam("rid")Integer rid, @PathParam("uid")Integer uid){
        boolean roomFilled = roomManager.isRoomFilled(rid);
        boolean b = roomManager.deRoom(rid, uid);
        if(b && !roomFilled){
            roomManager.deleteRoom(rid);
        }
    }

}
