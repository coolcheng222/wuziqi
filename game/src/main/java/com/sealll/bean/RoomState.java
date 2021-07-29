package com.sealll.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sealll
 * @time 2021/7/29 14:55
 */
public class RoomState {
    private Set<Integer> prepared = new HashSet<>();
    private boolean gameStarted;

    public Set<Integer> getPrepared() {
        return prepared;
    }

    public void setPrepared(Set<Integer> prepared) {
        this.prepared = prepared;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }
}
