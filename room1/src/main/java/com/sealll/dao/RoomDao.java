package com.sealll.dao;

import com.sealll.bean.Room;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

/**
 * @author sealll
 * @time 2021/6/30 11:47
 */
public interface RoomDao {
    public String create(Room room);

    public void delete(Integer id);

    @MapKey("id")
    public Map<Integer,Room> selectAll();

}
