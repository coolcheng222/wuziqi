package com.sealll.bean;

import java.util.Objects;

/**
 * @author sealll
 * @time 2021/6/30 11:49
 */
public class User extends ExpireEntity{
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(roomid, user.roomid) &&
                Objects.equals(uid, user.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomid, uid);
    }
}
