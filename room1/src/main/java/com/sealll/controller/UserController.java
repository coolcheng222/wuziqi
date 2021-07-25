package com.sealll.controller;

import com.sealll.bean.Msg;
import com.sealll.bean.User;
import com.sealll.manager.RoomManager;
import com.sealll.websocket.ChessEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sealll
 * @time 2021/7/23 15:26
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RoomManager roomManager;
    @GetMapping("/check")
    public Msg check(String token){
        User byToken = roomManager.getByToken(token);
        if(byToken == null){
            return Msg.fail();
        }else{
            return Msg.success().extend(byToken);
        }
    }

    @PutMapping
    public Msg delete(User user){
        Integer rid = user.getRoomid();
        Integer uid = user.getUid();
        synchronized (UserController.class) {
            System.out.println(rid + " closing");
            boolean roomFilled = roomManager.isRoomFilled(rid);
            boolean b = roomManager.deRoom(rid, uid);
            if (b && !roomFilled) {
                roomManager.deleteRoom(rid);
            }
            ChessEndPoint.remove(rid, uid);
        }
        return Msg.success();
    }
}
