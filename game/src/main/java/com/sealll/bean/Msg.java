package com.sealll.bean;

/**
 * @author sealll
 * @time 2021/6/19 18:38
 */
public class Msg {
    private Integer errno;
    private String message;
    private Object extend;

    public static Msg success(Object extend){
        Msg msg = new Msg();
        msg.setExtend(extend);
        msg.setErrno(0);
        return msg;
    }
    public static Msg fail(String message){
        Msg msg = new Msg();
        msg.setMessage(message);
        msg.setErrno(1);
        return msg;
    }

    public Msg extend(Object extend){
        this.setExtend(extend);
        return this;
    }
    public Msg message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getExtend() {
        return extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }
}
