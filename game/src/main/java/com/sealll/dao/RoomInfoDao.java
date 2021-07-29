package com.sealll.dao;

import com.sealll.bean.RoomInfo;
import com.sealll.bean.User;

/**
 * @author sealll
 * @time 2021/7/29 14:30
 */
public interface RoomInfoDao {
    public RoomInfo getRoomInfo(User user);
}
