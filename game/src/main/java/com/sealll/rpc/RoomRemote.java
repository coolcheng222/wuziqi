package com.sealll.rpc;

import com.sealll.bean.Msg;
import com.sealll.bean.Room;

/**
 * @author sealll
 * @time 2021/7/25 14:06
 */
public interface RoomRemote {
    public Msg checkRooms();
    public Msg enRoom(Room room);
    public Msg create(Room room);
    public Msg other(Msg msg);
    public Msg check(String token);
}
