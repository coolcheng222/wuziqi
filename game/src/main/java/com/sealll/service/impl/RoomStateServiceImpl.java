package com.sealll.service.impl;

import com.sealll.bean.RoomInfo;
import com.sealll.bean.User;
import com.sealll.dao.RoomStateDao;
import com.sealll.service.RoomStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sealll
 * @time 2021/7/29 15:30
 */
@Service
public class RoomStateServiceImpl implements RoomStateService {
    @Autowired
    private RoomStateDao roomStateDao;
    @Override
    public boolean togglePrepare(Integer rid, Integer uid) {
        boolean b = roomStateDao.togglePrepare(rid, uid);
        return b;
    }

    @Override
    public boolean allPrepared(Integer rid) {
        return roomStateDao.allPrepared(rid);
    }

    @Override
    public void startGame(Integer rid) {
        roomStateDao.startGame(rid);
    }

    @Override
    public void endGame(Integer rid) {
        roomStateDao.endGame(rid);
    }

    @Override
    public boolean gameStarted(Integer rid) {
        return roomStateDao.gameStarted(rid);
    }
}
