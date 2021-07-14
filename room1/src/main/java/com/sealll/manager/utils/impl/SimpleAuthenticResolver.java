package com.sealll.manager.utils.impl;

import com.sealll.bean.Room;
import com.sealll.config.SpringConfig2;
import com.sealll.manager.exception.RoomException;
import com.sealll.manager.exception.RoomNotExistException;
import com.sealll.manager.utils.AuthenticResolver;
import com.sealll.service.RoomService;
import com.sealll.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author sealll
 * @time 2021/7/9 23:49
 */
public class SimpleAuthenticResolver implements AuthenticResolver {
    private RoomService roomService;
    public SimpleAuthenticResolver(){
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig2.class);
        roomService = ioc.getBean(RoomService.class);
    }
    @Override
    public Room getRoom(Integer rid) throws RoomNotExistException{
        Room room = roomService.selectById(rid);
        if(room == null){
            throw new RoomNotExistException();
        }
        return room;
    }

    @Override
    public boolean checkPass(Integer rid,String pass) throws RoomException{
        Room room = getRoom(rid);
        return pass.equals(room.getPassword());
    }
}
