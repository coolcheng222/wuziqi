package com.sealll.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sealll
 * @time 2021/6/30 11:45
 */
public class Room{
    private Integer id;
    private Set<Integer> uids = new HashSet<>();
    private String password;

    public Integer getCount(){
        return uids.size();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Integer> getUids() {
        return uids;
    }

    public void setUids(Set<Integer> uids) {
        this.uids = uids;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", uids=" + uids +
                ", password='" + password + '\'' +
                '}';
    }
}
