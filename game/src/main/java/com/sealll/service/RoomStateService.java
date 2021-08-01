package com.sealll.service;

import com.sealll.bean.RoomInfo;
import com.sealll.bean.User;

/**
 * @author sealll
 * @time 2021/7/29 15:29
 */
public interface RoomStateService {
    public boolean togglePrepare(Integer rid,Integer uid);
    public boolean allPrepared(Integer rid);

    public void startGame(Integer rid);
    public void endGame(Integer rid);
    public boolean gameStarted(Integer rid);
}
