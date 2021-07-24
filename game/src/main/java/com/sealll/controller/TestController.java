package com.sealll.controller;

import com.sealll.bean.Msg;
import com.sealll.rpc.RoomRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sealll
 * @time 2021/7/23 18:50
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private RoomRemoteService roomRemoteService;
    @GetMapping("check")
    public Msg check(String token){
        Msg check = roomRemoteService.check(token);
        System.out.println(check);
        return check;
    }
}
