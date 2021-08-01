package com.sealll.controller;

import com.sealll.bean.Chess;
import com.sealll.bean.Msg;
import com.sealll.bean.RoomInfo;
import com.sealll.bean.User;
import com.sealll.constants.ResultConstants;
import com.sealll.constants.WsConstants;
import com.sealll.security.RoomCheckinInterceptor;
import com.sealll.service.GameSevice;
import com.sealll.service.RoomInfoService;
import com.sealll.service.RoomStateService;
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
    @Autowired
    private RoomStateService roomStateService;
    @Autowired
    private RoomInfoService roomInfoService;

    @PostMapping("/prepare")
    public Msg start(@RequestAttribute(value = RoomCheckinInterceptor.REQUEST_ATTR,required = false) User user){
        Integer rid = user.getRoomid();
        Integer uid = user.getUid();
        boolean b = roomStateService.togglePrepare(rid, uid);
        if(b){
            if(roomStateService.allPrepared(rid)){
                roomStateService.startGame(rid);
                gameSevice.startGame(rid);
                return Msg.success(true);
            }
            return Msg.success(false);
        }else{
            return Msg.fail("");
        }
    }

    @PostMapping("/chess")
    public Msg setChess(@RequestAttribute(value = RoomCheckinInterceptor.REQUEST_ATTR,required = false) User user,
                        @RequestBody Chess chess){
        Integer rid = user.getRoomid();
        Integer uid = user.getUid();
        String s = gameSevice.setChess(rid,chess.getColor(), chess.getX(), chess.getY());
        if(ResultConstants.SAFE.equals(s)){
            return Msg.success(null).message(WsConstants.CHESS);
        }else if(ResultConstants.BLOCK.equals(s)){
            return Msg.fail("该处已有子");
        }else if(ResultConstants.WIN.equals(s)){
            roomStateService.endGame(rid);
            gameSevice.endGame(rid);
            return Msg.success("您已获胜").message(WsConstants.WIN);
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
            RoomInfo roomInfo = roomInfoService.getRoomInfo((User) attribute);
            return Msg.success(roomInfo);
        }
    }
}
