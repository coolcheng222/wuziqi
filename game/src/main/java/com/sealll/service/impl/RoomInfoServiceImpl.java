package com.sealll.service.impl;

import com.sealll.bean.RoomInfo;
import com.sealll.bean.User;
import com.sealll.dao.RoomInfoDao;
import com.sealll.service.RoomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sealll
 * @time 2021/7/29 15:31
 */
@Service
public class RoomInfoServiceImpl implements RoomInfoService {
    @Autowired
    private RoomInfoDao roomInfoDao;
    @Override
    public RoomInfo getRoomInfo(User user) {
        RoomInfo roomInfo = roomInfoDao.getRoomInfo(user);
        return roomInfo;
    }
}
