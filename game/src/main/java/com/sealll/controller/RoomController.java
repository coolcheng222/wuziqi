package com.sealll.controller;

import com.sealll.bean.Msg;
import com.sealll.bean.Room;
import com.sealll.rpc.RoomRemoteService;
import com.sealll.security.RoomCheckinInterceptor;
import com.sealll.service.RoomStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author sealll
 * @time 2021/7/24 13:24
 */
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomRemoteService roomRemoteService;
    @Autowired
    private RoomStateService roomStateService;
    @PostMapping
    public Msg create(@RequestBody Room room){
        Msg msg = roomRemoteService.create(room);
        if(msg.getErrno() == 0){
            roomStateService.createRoom(room.getId());
        }
        return msg;
    }

    @PutMapping
    public Msg enRoom(@RequestBody Room room){
        return roomRemoteService.enRoom(room);
    }

    @DeleteMapping
    public Msg deRoom(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute(RoomCheckinInterceptor.SESSION_ATTR);
        return Msg.success("");
    }
}
