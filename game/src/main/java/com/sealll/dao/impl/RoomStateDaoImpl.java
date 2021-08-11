package com.sealll.dao.impl;

import com.sealll.bean.RoomState;
import com.sealll.dao.RoomStateDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author sealll
 * @time 2021/7/29 15:03
 */
@Repository
public class RoomStateDaoImpl implements RoomStateDao {
    private Map<Integer, RoomState> roomStateMap = new HashMap<>();

    @Override
    public synchronized boolean togglePrepare(Integer rid, Integer uid) {
        RoomState roomState = roomStateMap.get(rid);
        if(roomState == null){
            return false;
        }else{
            if(roomState.isGameStarted()){
                return false;
            }
            Set<Integer> prepared = roomState.getPrepared();
            if(prepared.contains(uid)){
                prepared.remove(uid);
            }else{
                prepared.add(uid);
            }
        }
        return true;
    }

    @Override
    public boolean allPrepared(Integer rid) {
        RoomState roomState = roomStateMap.get(rid);
        if(roomState == null){
            return false;
        }else{
            if(roomState.isGameStarted()){
                return false;
            }
            Set<Integer> prepared = roomState.getPrepared();
            return prepared.size() == 2;
        }
    }

    @Override
    public boolean createRoom(Integer rid) {
        roomStateMap.put(rid,new RoomState());
        return true;
    }

    @Override
    public boolean deleteRoom(Integer rid) {
        roomStateMap.remove(rid);
        return true;
    }

    @Override
    public void startGame(Integer rid) {
        RoomState roomState = roomStateMap.get(rid);
        if(roomState == null){
            return ;
        }else{
            roomState.setGameStarted(true);
        }
    }

    @Override
    public void endGame(Integer rid) {
        RoomState roomState = roomStateMap.get(rid);
        if(roomState != null){
            roomState.setGameStarted(false);
        }
    }

    @Override
    public boolean gameStarted(Integer rid) {
        RoomState roomState = roomStateMap.get(rid);
        if(roomState != null){
            return roomState.isGameStarted();
        }
        return false;
    }

    @Override
    public Set<Integer> getPreparedSet(Integer rid) {
        RoomState roomState = roomStateMap.get(rid);
        if(roomState == null){
            return null;
        }else{
            return roomState.getPrepared();
        }
    }



}
