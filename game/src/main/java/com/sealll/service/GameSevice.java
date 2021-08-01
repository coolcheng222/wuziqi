package com.sealll.service;

/**
 * @author sealll
 * @time 2021/6/19 18:54
 */
public interface GameSevice {
    public boolean startGame(Integer rid);
    public String setChess(Integer rid,Integer color,int x, int y);
    public boolean endGame(Integer rid);
}
