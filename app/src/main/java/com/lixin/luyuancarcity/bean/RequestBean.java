package com.lixin.luyuancarcity.bean;

/**
 * Created by 小火
 * Create time on  2017/6/26
 * My mailbox is 1403241630@qq.com
 */

public class RequestBean {
    public String cmd;
    public String uid;
    public String emergencyid;
    public RequestBean(String cmd) {
        this.cmd = cmd;
    }
    public RequestBean(String cmd,String uid){
        this.cmd = cmd;
        this.uid = uid;
    }
    public RequestBean(String cmd,String uid,String emergencyid){
        this.cmd = cmd;
        this.uid = uid;
        this.emergencyid = emergencyid;
    }
}
