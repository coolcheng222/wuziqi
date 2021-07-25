package com.sealll.dao;

import com.sealll.bean.Room;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;
import java.util.Set;

/**
 * @author sealll
 * @time 2021/6/30 11:47
 */
public interface RoomDao {
    public boolean create(Room room);

    public void delete(Integer id);

    @MapKey("id")
    public Map<Integer,Room> selectAll();

    public Room selectById(Integer id);

    public Room selectByIdWithUser(Integer id);

    @MapKey("id")
    public Map<Integer,Room> selectAllWithUser();

    public Set<Integer> selectIds();
    
}
