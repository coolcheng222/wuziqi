package com.sealll.service.impl;

import com.sealll.bean.Chess;
import com.sealll.constants.ResultConstants;
import com.sealll.dao.ChessMap;
import com.sealll.service.GameSevice;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author sealll
 * @time 2021/6/19 18:58
 */
@Service
public class GameServiceImpl implements GameSevice {
    private ChessMap chessMap;
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
