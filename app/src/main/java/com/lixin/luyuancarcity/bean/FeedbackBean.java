package com.lixin.luyuancarcity.bean;

/**
 * Created by 小火
 * Create time on  2017/7/19
 * My mailbox is 1403241630@qq.com
 */

public class FeedbackBean{
    public String cmd;
    public String uid;
    public String content;
    public String result;
    public String resultNote;
    public FeedbackBean(String cmd, String uid, String content) {
        this.cmd = cmd;
        this.uid = uid;
        this.content = content;
    }
}
