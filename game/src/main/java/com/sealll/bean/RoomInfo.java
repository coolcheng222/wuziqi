package com.sealll.bean;

/**
 * @author sealll
 * @time 2021/7/29 14:02
 */
public class RoomInfo {
    private Integer rid;
    private Integer me;
    private Integer other;
    private boolean mePrepared;
    private boolean otherPrepared;
    private boolean gaming;
    private ChessMap chessMap;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getMe() {
        return me;
    }

    public void setMe(Integer me) {
        this.me = me;
    }

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    public boolean isMePrepared() {
        return mePrepared;
    }

    public void setMePrepared(boolean mePrepared) {
        this.mePrepared = mePrepared;
    }

    public boolean isOtherPrepared() {
        return otherPrepared;
    }

    public void setOtherPrepared(boolean otherPrepared) {
        this.otherPrepared = otherPrepared;
    }

    public boolean isGaming() {
        return gaming;
    }

    public void setGaming(boolean gaming) {
        this.gaming = gaming;
    }

    public ChessMap getChessMap() {
        return chessMap;
    }

    public void setChessMap(ChessMap chessMap) {
        this.chessMap = chessMap;
    }
}
