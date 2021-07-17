package com.sealll.bean;

/**
 * @author sealll
 * @time 2021/7/15 21:47
 */
public class Msg {
    private int errno;
    private String message;
    private Object extend;

    public static Msg success(){
        Msg msg = new Msg();
        msg.setErrno(0);
        return msg;
    }
    public static Msg fail(){
        Msg msg = new Msg();
        msg.setErrno(1);
        return msg;
    }
    public Msg extend(Object extend){
        this.setExtend(extend);
        return this;
    }
    public Msg msg(String message){
        this.setMessage(message);
        return this;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
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
