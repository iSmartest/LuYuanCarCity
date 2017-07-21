package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/18
 * My mailbox is 1403241630@qq.com
 */

public class FootBean {
    public String cmd;
    public String uid;
    public int nowPage;
    public FootBean(String cmd, String uid, int nowPage) {
        this.cmd = cmd;
        this.uid = uid;
        this.nowPage = nowPage;
    }
    public String result;
    public String resultNote;
    public String totalPage;
    public List<footPrintList> footPrintList;

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

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public List<FootBean.footPrintList> getFootPrintList() {
        return footPrintList;
    }

    public void setFootPrintList(List<FootBean.footPrintList> footPrintList) {
        this.footPrintList = footPrintList;
    }

    public class footPrintList {
        public String commodityId;
        public String commodityIcon;
        public String commodityNewPrice;
        public String commodityTitle;
        public String commodityTime;

        public String getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(String commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityIcon() {
            return commodityIcon;
        }

        public void setCommodityIcon(String commodityIcon) {
            this.commodityIcon = commodityIcon;
        }

        public String getCommodityNewPrice() {
            return commodityNewPrice;
        }

        public void setCommodityNewPrice(String commodityNewPrice) {
            this.commodityNewPrice = commodityNewPrice;
        }

        public String getCommodityTitle() {
            return commodityTitle;
        }

        public void setCommodityTitle(String commodityTitle) {
            this.commodityTitle = commodityTitle;
        }

        public String getCommodityTime() {
            return commodityTime;
        }

        public void setCommodityTime(String commodityTime) {
            this.commodityTime = commodityTime;
        }
    }
}
