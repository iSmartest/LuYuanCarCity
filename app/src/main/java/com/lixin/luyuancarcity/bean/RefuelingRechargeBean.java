package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/3
 * My mailbox is 1403241630@qq.com
 */

public class RefuelingRechargeBean {
    private String cmd;// getComeOnTopUp
    private String result;
    private String resultNote;
    private String img;
    public List<topUpList> topUpList;

    public RefuelingRechargeBean(String cmd) {
        this.cmd = cmd;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<RefuelingRechargeBean.topUpList> getTopUpList() {
        return topUpList;
    }

    public void setTopUpList(List<RefuelingRechargeBean.topUpList> topUpList) {
        this.topUpList = topUpList;
    }

    public class topUpList{
        private String topupid;
        private String mouthTime;
        private String discount;

        public String getTopupid() {
            return topupid;
        }

        public void setTopupid(String topupid) {
            this.topupid = topupid;
        }

        public String getMouthTime() {
            return mouthTime;
        }

        public void setMouthTime(String mouthTime) {
            this.mouthTime = mouthTime;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }
    }
}
