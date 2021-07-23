package com.sealll.controller;

import com.sealll.bean.Msg;
import com.sealll.bean.Room;
import com.sealll.manager.RoomManager;
import com.sealll.manager.impl.SimpleRoomManager;
import com.sealll.service.WsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sealll
 * @time 2021/7/15 21:40
 */
@RestController
@RequestMapping("/room")
@CrossOrigin(origins = {"http://localhost:63343/"})
public class RoomController {
    @Autowired
    private RoomManager roomManager;
    @Autowired
    private WsService wsService;

    @PostMapping
    public Msg create(@RequestBody Room room){
        String room1 = roomManager.createRoom(room.getId(), room.getPassword());
        try {
            int i = Integer.parseInt(room1);
            return Msg.success().extend(i);
        } catch (NumberFormatException e) {
            return Msg.fail().msg(room1);
        }
    }

    @PutMapping
    public Msg enRoom(@RequestBody Room room){
        String s = roomManager.enRoom(room.getId(), room.getPassword());
        try {
            int i = Integer.parseInt(s);
            return Msg.success().extend(i);
        } catch (NumberFormatException e) {
            return Msg.fail().msg(s);
        }
    }
}
