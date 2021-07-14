package com.sealll.websocket;

import com.sealll.bean.Room;
import com.sealll.bean.User;
import com.sealll.manager.RoomManager;
import com.sealll.manager.impl.SimpleRoomManager;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sealll
 * @time 2021/7/13 19:34
 */
@ServerEndpoint("/game/{rid}/{uid}")
public class ChessEndPoint {
    public static Map<User, Session> sessions =  new HashMap<>();
    private RoomManager roomManager = new SimpleRoomManager();
    @OnOpen
    public void open(Session session, @PathParam("rid")Integer rid, @PathParam("uid")Integer uid){
        Room room = roomManager.checkRoomCache(rid);
        User user1 = roomManager.checkUserCache(rid);
        boolean flag = false;
        if(room == null && user1 == null){
            flag = true;
        }else if(room != null && user1 == null){
            if(room.getUids().contains(uid)){

            }else{
                flag = true;
            }
        }else if(room == null){
            if(user1.getUid().equals(uid)){

            }else{
                flag = true;
            }
        }else{

        }

        User user = new User();
        user.setUid(uid);
        user.setRoomid(rid);
        sessions.put(user,session);
    }

}
