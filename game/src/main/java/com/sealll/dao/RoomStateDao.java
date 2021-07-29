package com.sealll.dao;

import java.util.Set;

/**
 * @author sealll
 * @time 2021/7/29 14:58
 */
public interface RoomStateDao{
    public boolean togglePrepare(Integer rid,Integer uid);
    public boolean allPrepared(Integer rid);

    public void startGame(Integer rid);
    public void endGame(Integer rid);
    public Set<Integer> getPreparedSet(Integer rid);
}
