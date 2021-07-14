package com.sealll.manager.utils;

import com.sealll.bean.Room;
import com.sealll.manager.exception.RoomException;
import com.sealll.manager.exception.RoomNotExistException;

/**
 * @author sealll
 * @time 2021/7/9 23:43
 */
public interface AuthenticResolver {
    public Room getRoom(Integer rid) throws RoomNotExistException;
    public boolean checkPass(Integer rid,String pass) throws RoomException;
}
