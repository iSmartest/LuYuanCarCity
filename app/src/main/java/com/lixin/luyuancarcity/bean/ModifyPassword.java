package com.lixin.luyuancarcity.bean;

/**
 * Created by 小火
 * Create time on  2017/7/19
 * My mailbox is 1403241630@qq.com
 */

public class ModifyPassword {
    public String cmd;
    public String uid;
    public String newPassword;
    public String oldPassword;
    public ModifyPassword(String cmd, String uid, String oldPassword, String newPassword) {
        this.cmd = cmd;
        this.uid = uid;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }
    public String result;
    public String resultNote;
}
