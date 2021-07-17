package com.sealll.websocket;

import com.sealll.bean.Msg;
import com.sealll.config.SpringConfig2;
import com.sealll.service.RoomService;
import com.sealll.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author sealll
 * @time 2021/6/22 15:55
 */
@Component
@ServerEndpoint("/room/{room-id}/{id}")
public class RoomEndPoint {
    public static ConcurrentHashMap<String,RoomEndPoint> endPoints = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String,Integer> nums = new ConcurrentHashMap<>();
    private Session session;


    @OnOpen
    public void onOpen(Session session, @PathParam("room-id") String rid, @PathParam("id") String id) throws IOException {
        synchronized (RoomEndPoint.class){
            this.session = session;
            System.out.println("opening + " + rid + "-" + id);
            if(nums.get(rid) != null && nums.get(rid) >= 2){
                System.out.println("room overflow");
                session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT,"房间溢出"));
            }else{
                endPoints.put(generateKey(rid,id),this);
                Integer orDefault = nums.getOrDefault(rid, 0);
                nums.put(rid,orDefault + 1);
            }

        }
    }
    @OnClose
    public void onClose(Session session,@PathParam("room-id") String rid, @PathParam("id") String id){
        endPoints.remove(generateKey(rid,id));
        nums.put(rid,nums.get(rid) - 1);
        System.out.println("websocket closed");
    }

    @OnMessage
    public void onMessage(String message,Session session,@PathParam("room-id") String rid, @PathParam("id") String id){
        System.out.println(message);
    }

    private static String generateKey(String rid,String id){
        return rid + "_" + id;
    }

    public static void sendMessage(String rid, Msg message){
        for (Map.Entry<String, RoomEndPoint> entry : endPoints.entrySet()) {
            if(rid.equals(entry.getKey().split("-")[0])){
                entry.getValue().session.getAsyncRemote()
                        .sendObject(message);
            }
        }
    }
    public static void sendMessage(String rid,String id,Msg message){
        RoomEndPoint roomEndPoint = endPoints.get(generateKey(rid, id));
        roomEndPoint.session.getAsyncRemote().sendObject(message);
    }
}
