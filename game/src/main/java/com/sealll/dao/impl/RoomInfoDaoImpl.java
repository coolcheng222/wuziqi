package com.sealll.dao.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sealll.bean.*;
import com.sealll.dao.ChessMapDao;
import com.sealll.dao.RoomInfoDao;
import com.sealll.dao.RoomStateDao;
import com.sealll.rpc.RoomRemote;
import com.sealll.rpc.RoomRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author sealll
 * @time 2021/7/29 14:31
 */
@Repository
public class RoomInfoDaoImpl implements RoomInfoDao {
    @Autowired
    private RoomRemote roomRemote;
    @Autowired
    private ChessMapDao chessMapDao;
    @Autowired
    private RoomStateDao roomStateDao;
    @Autowired
    ObjectMapper objectMapper;
    @Override
    public RoomInfo getRoomInfo(User user) {
        Msg info = roomRemote.info(user.getRoomid());
        if(info.getErrno() != 0){
            return null;
        }else{
            RoomInfo roomInfo = new RoomInfo();
            Object extend = info.getExtend();
            String s = null;
            try {
                s = objectMapper.writeValueAsString(extend);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            Room room = null;
            try {
                room = objectMapper.readValue(s, Room.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            Set<Integer> uids = room.getUids();
            Integer uid = null;
            for (Integer integer : uids) {
                if(!integer.equals(user.getUid())){
                    uid = integer;
                    break;
                }
            }
            Set<Integer> preparedSet = roomStateDao.getPreparedSet(user.getRoomid());
            if(preparedSet == null){
                throw new RuntimeException("房间竟然不存在?");
            }
            if(uid != null && preparedSet.contains(uid)){
                roomInfo.setOtherPrepared(true);
            }
            if(preparedSet.contains(user.getUid())){
                roomInfo.setMePrepared(true);
            }
            roomInfo.setGaming(roomStateDao.gameStarted(user.getRoomid()));

            roomInfo.setRid(user.getRoomid());
            roomInfo.setMe(user.getUid());
            roomInfo.setOther(uid);
            roomInfo.setChessMap(chessMapDao.getChessMap(user.getRoomid()));
            //TODO 准备状态和游戏状态


            return roomInfo;
        }
    }
}
