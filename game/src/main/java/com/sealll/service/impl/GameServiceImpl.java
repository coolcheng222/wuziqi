package com.sealll.service.impl;

import com.sealll.bean.Msg;
import com.sealll.constants.ResultConstants;
import com.sealll.bean.ChessMap;
import com.sealll.dao.ChessMapDao;
import com.sealll.rpc.RoomRemote;
import com.sealll.service.GameSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author sealll
 * @time 2021/6/19 18:58
 */
@Service
public class GameServiceImpl implements GameSevice {
    private Logger logger = LoggerFactory.getLogger(GameSevice.class);
    @Autowired
    private ChessMapDao chessMapDao;

    @Autowired
    private RoomRemote roomRemote;

    @Scheduled(cron = "5 */10 * * * *")
    public void deleteTtl(){
        Msg msg = roomRemote.checkRooms();
        Set<Integer> extend = (Set<Integer>) msg.getExtend();
        logger.info(extend.toString());
        chessMapDao.deleteTtl(extend);
    }

    @Override
    public boolean startGame(Integer rid) {
        boolean b = chessMapDao.startGame(rid);
        return b;
    }

    @Override
    public String setChess(Integer rid,Integer color, int x, int y) {
        if(isStarted(rid)){
            boolean b = chessMapDao.setChess(rid,color, x, y);
            if(b){
                if(chessMapDao.isWin(rid,color,x,y)){
                    return ResultConstants.WIN;
                }
                return ResultConstants.SAFE;
            }else{
                return ResultConstants.BLOCK;
            }

        }else{
            return ResultConstants.NOTSTARTED;
        }
    }

    @Override
    public boolean endGame(Integer rid) {
        boolean b = chessMapDao.endGame(rid);
        return b;
    }


    private boolean isStarted(Integer rid){
        return chessMapDao.isStarted(rid);
    }
}
