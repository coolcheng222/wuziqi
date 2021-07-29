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
    public boolean startGame() {
        if(isStarted()){
            return false;
        }
        chessMap = new ChessMap();
        return true;
    }

    @Override
    public String setChess(Integer color, int x, int y) {
        if(isStarted()){
            boolean b = chessMap.setChess(color, x, y);
            if(b){
                if(chessMap.isWin(color,x,y)){
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
    public boolean endGame() {
        if(isStarted()){
            chessMap = null;
            return true;
        }else{
            return false;
        }

    }


    private boolean isStarted(){
        return chessMap != null;
    }
}
