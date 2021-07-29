package com.sealll.dao.impl;

import com.sealll.bean.Msg;
import com.sealll.bean.Room;
import com.sealll.bean.RoomInfo;
import com.sealll.bean.User;
import com.sealll.dao.ChessMapDao;
import com.sealll.dao.RoomInfoDao;
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
    @Override
    public RoomInfo getRoomInfo(User user) {
        Msg info = roomRemote.info(user.getRoomid());
        if(info.getErrno() != 0){
            return null;
        }else{
            Set<Integer> uids = ((Room) (info.getExtend())).getUids();
            Integer uid = null;
            for (Integer integer : uids) {
                if(!integer.equals(user.getUid())){
                    uid = integer;
                    break;
                }
            }
            RoomInfo roomInfo = new RoomInfo();
            roomInfo.setRid(user.getRoomid());
            roomInfo.setMe(user.getUid());
            roomInfo.setOther(uid);
            roomInfo.setChessMap(chessMapDao.getChessMap(user.getRoomid()));
            //TODO 准备状态和游戏状态
            return roomInfo;
        }
    }
}
