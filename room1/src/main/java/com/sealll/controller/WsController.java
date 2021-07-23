package com.sealll.controller;

import com.sealll.bean.Msg;
import com.sealll.bean.User;
import com.sealll.manager.RoomManager;
import com.sealll.service.WsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sealll
 * @time 2021/7/23 14:07
 */
@RestController
@RequestMapping("/ws")
public class WsController {
    @Autowired
    private RoomManager roomManager;
    @Autowired
    private WsService wsService;
    @PostMapping("/other")
    public Msg other(HttpServletRequest request, @RequestBody Msg msg){
        String token = request.getHeader("token");
        User byToken = roomManager.getByToken(token);
        try {
            wsService.others(msg, byToken.getRoomid(),byToken.getUid());
            return Msg.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }
    @PostMapping("/broadcast")
    public Msg broad(HttpServletRequest request, @RequestBody Msg msg){
        String token = request.getHeader("token");
        User byToken = roomManager.getByToken(token);
        try {
            wsService.broadcast(msg,byToken.getRoomid());
            return Msg.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }
}
