package com.sealll.manager;

import com.sealll.bean.Room;
import com.sealll.bean.User;
import com.sealll.manager.utils.AuthenticResolver;
import com.sealll.manager.utils.RoomValidator;
import com.sealll.manager.utils.TokenResolver;
import com.sealll.manager.utils.UidGenerator;

import java.util.Set;

/**
 * @author sealll
 * @time 2021/7/9 13:10
 */
public interface RoomManager {
    public String createRoom(Integer rid,String password);
    public boolean isRoomFilled(Integer rid);
    public String enRoom(Integer rid,String password);
    public boolean deRoom(Integer rid,Integer uid);
    public boolean deRoom(String token);

    public User getByToken(String token);
    public Room checkRoomCache(Integer rid,Integer uid);
    public void removeRoomCache(Integer rid);
    public User checkUserCache(Integer rid,Integer uid);
    public void removeUserCache(Integer rid);
    public String generateToken(Integer rid,Integer uid);

    public String storeRoom(Room room);
    public String storeUser(User user);

    public void deleteRoom(Integer rid);

    public boolean canMoreRoom();

    public Set<Integer> checkRooms();
    public Room getRoomInfo(Integer rid);

}
