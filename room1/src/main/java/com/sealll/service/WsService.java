package com.sealll.service;

import com.sealll.bean.Msg;

/**
 * @author sealll
 * @time 2021/7/23 12:54
 */
public interface WsService {
    public void broadcast(Msg msg, Integer rid);

    public void others(Msg msg,Integer rid,Integer uid);
}
