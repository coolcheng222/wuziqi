package com.sealll.controller;

import com.sealll.bean.Msg;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author sealll
 * @time 2021/8/11 15:45
 */
@ControllerAdvice
public class AnyExceptionHandler{
    @ExceptionHandler(value={Exception.class})
    public Msg doCatch(Exception e){
        return Msg.fail().msg(e.getMessage());
    }
}
