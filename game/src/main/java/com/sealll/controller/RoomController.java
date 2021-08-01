package com.sealll.controller;

import com.sealll.bean.Msg;
import com.sealll.bean.Room;
import com.sealll.rpc.RoomRemoteService;
import com.sealll.security.RoomCheckinInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sealll
 * @time 2021/7/24 13:24
 */
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomRemoteService roomRemoteService;
    @PostMapping
    public Msg create(@RequestBody Room room){
        Msg msg = roomRemoteService.create(room);
        return msg;
    }

    @PutMapping
    public Msg enRoom(@RequestBody Room room){
        return roomRemoteService.enRoom(room);
    }

}
