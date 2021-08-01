package com.sealll.dao.impl;

import com.sealll.bean.ChessMap;
import com.sealll.dao.ChessMapDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sealll
 * @time 2021/7/24 20:56
 */
@Repository
public class ChessMapDaoImpl implements ChessMapDao {
    private HashMap<Integer, ChessMap> chessMaps = new HashMap<>();

    public void deleteTtl(Set<Integer> ids){
        if(ids == null){
            return;
        }
        Set<Integer> chess = new HashSet<>(chessMaps.keySet());
        chess.removeAll(ids);
        for(Integer i: chess){
            chessMaps.remove(i);
        }
    }

    @Override
    public boolean setChess(Integer rid,Integer color, int x, int y) {
        ChessMap chessMap = chessMaps.get(rid);
        if(chessMap != null){
            return chessMap.setChess(color,x,y);
        }else{
            return false;
        }
    }

    @Override
    public boolean isWin(Integer rid,Integer color, int x, int y) {
        ChessMap chessMap = chessMaps.get(rid);
        if(chessMap != null){
            return chessMap.isWin(color, x, y);
        }else{
            return false;
        }
    }

    @Override
    public boolean isValidPoint(Integer rid,int x, int y) {
        ChessMap chessMap = chessMaps.get(rid);
        if(chessMap != null){
            return chessMap.isValidPoint(x,y);
        }else{
            return false;
        }
    }

    @Override
    public ChessMap getChessMap(Integer rid) {
        return chessMaps.get(rid);
    }

    @Override
    public boolean startGame(Integer rid) {
        ChessMap chessMap = chessMaps.get(rid);
        if(chessMap == null){
            chessMaps.put(rid,new ChessMap());
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean endGame(Integer rid) {
        ChessMap chessMap = chessMaps.get(rid);
        if(chessMap == null){
            return false;
        }else{
            chessMaps.remove(rid);
            return true;
        }
    }

    @Override
    public boolean isStarted(Integer rid) {
        ChessMap chessMap = chessMaps.get(rid);
        return chessMap != null;
    }
}
