package com.sealll.service;

/**
 * @author sealll
 * @time 2021/6/19 18:54
 */
public interface GameSevice {
    public boolean startGame();
    public String setChess(Integer color,int x, int y);
    public boolean endGame();
}
