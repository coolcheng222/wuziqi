package com.sealll.dao;

import com.sealll.bean.ChessMap;

import java.util.Set;

/**
 * @author sealll
 * @time 2021/7/25 13:24
 */
public interface ChessMapDao {
    public void deleteTtl(Set<Integer> ids);
    public boolean setChess(Integer rid, Integer color,int x,int y);
    public boolean isWin(Integer rid, Integer color,int x,int y);
    public boolean isValidPoint(Integer rid, int x,int y);
    public ChessMap getChessMap(Integer rid);
}
