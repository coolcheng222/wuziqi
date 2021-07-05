package com.sealll.bean;

/**
 * @author sealll
 * @time 2021/6/30 11:49
 */
public class RoomUser {
    private Integer roomid;
    private Integer uid;
    private String token;

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
