package com.sealll.service.impl;

import com.sealll.bean.Msg;
import com.sealll.service.WsService;
import com.sealll.websocket.ChessEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sealll
 * @time 2021/7/23 13:06
 */
@Service
public class WsServiceImpl implements WsService {
    @Override
    public void broadcast(Msg msg, Integer rid) {
        List<ChessEndPoint> users = ChessEndPoint.getRoom(rid);
        for (ChessEndPoint user : users){
            user.send(msg);
        }
    }

    @Override
    public void others(Msg msg, Integer rid, Integer uid) {
        List<ChessEndPoint> users = ChessEndPoint.getRoom(rid);
        for (ChessEndPoint user : users){
            if(!user.getUser().getUid().equals(uid)){
                user.send(msg);
            }
        }
    }

}
