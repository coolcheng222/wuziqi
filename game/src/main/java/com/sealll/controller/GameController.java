package com.sealll.controller;

import com.sealll.bean.Chess;
import com.sealll.bean.Msg;
import com.sealll.constants.ResultConstants;
import com.sealll.security.RoomCheckinInterceptor;
import com.sealll.service.GameSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sealll
 * @time 2021/6/19 18:37
 */
@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameSevice gameSevice;

    @PostMapping("/start")
    public Msg start(){
        boolean b = gameSevice.startGame();
        if(b){
            return Msg.success(null);
        }else{
            return Msg.fail("游戏已开始");
        }
    }

    @PostMapping("restart")
    public Msg restart(){
        gameSevice.endGame();
        gameSevice.startGame();
        return Msg.success("");
    }

    @PostMapping("/chess")
    public Msg setChess(@RequestBody Chess chess){
        String s = gameSevice.setChess(chess.getColor(), chess.getX(), chess.getY());
        if(ResultConstants.SAFE.equals(s)){
            return Msg.success(null);
        }else if(ResultConstants.BLOCK.equals(s)){
            return Msg.fail("该处已有子");
        }else if(ResultConstants.WIN.equals(s)){
            return Msg.success("您已获胜");
        }else if(ResultConstants.NOTSTARTED.equals(s)){
            return Msg.fail("游戏未开始");
        }
        return Msg.fail("网络异常");
    }

    @GetMapping("/check")
    public Msg check(HttpServletRequest request){
        Object attribute = request.getAttribute(RoomCheckinInterceptor.REQUEST_ATTR);
        if(attribute == null){
            return Msg.fail("");
        }else{
            return Msg.success(attribute);
        }
    }
}
