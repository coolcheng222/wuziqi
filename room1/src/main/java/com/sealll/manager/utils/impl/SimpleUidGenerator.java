package com.sealll.manager.utils.impl;

import com.sealll.bean.Room;
import com.sealll.config.SpringConfig2;
import com.sealll.manager.utils.UidGenerator;
import com.sealll.service.RoomService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

/**
 * @author sealll
 * @time 2021/7/10 22:02
 */
public class SimpleUidGenerator implements UidGenerator {
    private RoomService roomService;
    public SimpleUidGenerator(){
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig2.class);
        roomService = ioc.getBean(RoomService.class);
    }
    @Override
    public Integer getUid(Integer rid) {
        Room room = roomService.selectByIdWithUser(rid);
        Integer res = 0;
        switch (room.getUids().size()){
            case 0:
                res = new Random().nextInt(32767);
                break;
            case 1:
                res = room.getUids().iterator().next() +  new Random().nextInt(3293);
                break;
            case 2:
            default:
                res = null;
        }
        return res;
    }
}
