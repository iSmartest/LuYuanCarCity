package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/5
 * My mailbox is 1403241630@qq.com
 */

public class CheckBean {
    public String cmd;//findIllegal
    public String carNumer;
    public String carEngineNum;
    public String carFrame;



    public String result;
    public String resultNote;
    public List<illegalInfo> illegalInfo;

    public CheckBean(String cmd, String carNumer, String carEngineNum, String carFrame) {
        this.cmd = cmd;
        this.carNumer = carNumer;
        this.carEngineNum = carEngineNum;
        this.carFrame = carFrame;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultNote() {
        return resultNote;
    }

    public void setResultNote(String resultNote) {
        this.resultNote = resultNote;
    }

    public List<CheckBean.illegalInfo> getIllegalInfo() {
        return illegalInfo;
    }

    public void setIllegalInfo(List<CheckBean.illegalInfo> illegalInfo) {
        this.illegalInfo = illegalInfo;
    }

    public class illegalInfo{
        public String address;
        public String time;
        public String content;
        public String money;
        public String tel;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }
}
