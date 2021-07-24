package com.sealll.controller;

import com.sealll.bean.Msg;
import com.sealll.bean.User;
import com.sealll.manager.RoomManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
