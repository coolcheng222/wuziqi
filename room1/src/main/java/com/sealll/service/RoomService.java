package com.sealll.service;

import com.sealll.bean.Room;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

/**
 * @author sealll
 * @time 2021/7/6 20:33
 */
public interface RoomService {
    public boolean create(Room room);

    public void delete(Integer id);

    @MapKey("id")
    public Map<Integer,Room> selectAll();

    public Room selectById(Integer id);

    public Room selectByIdWithUser(Integer id);

    @MapKey("id")
    public Map<Integer,Room> selectAllWithUser();
}
