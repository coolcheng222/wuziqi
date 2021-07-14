package com.sealll.service.impl;

import com.sealll.bean.Room;
import com.sealll.dao.RoomDao;
import com.sealll.service.RoomService;
import org.graalvm.compiler.serviceprovider.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author sealll
 * @time 2021/7/6 20:35
 */

@Service
@Transactional
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDao roomDao;
    @Override
    public boolean create(Room room) {
        return roomDao.create(room);
    }

    @Override
    public void delete(Integer id) {
        roomDao.delete(id);
    }

    @Override
    public Map<Integer, Room> selectAll() {
        return roomDao.selectAll();
    }

    @Override
    public Room selectById(Integer id) {
        return selectById(id);
    }

    @Override
    public Room selectByIdWithUser(Integer id) {
        return roomDao.selectByIdWithUser(id);
    }

    @Override
    public Map<Integer, Room> selectAllWithUser() {
        return roomDao.selectAllWithUser();
    }
}
