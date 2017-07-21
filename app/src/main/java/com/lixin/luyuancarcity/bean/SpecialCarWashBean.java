package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/10
 * My mailbox is 1403241630@qq.com
 */

public class SpecialCarWashBean {
    public String cmd;//clearCar
    public String carId; //品牌
    public String areid;//地址
    public String clearCarid;//洗车类型
    public String longitude;//经度
    public String latitude;//纬度
    public int nowPage;
    public SpecialCarWashBean(String cmd, String carId, String areid, String clearCarid, String longitude, String latitude, int nowPage) {
        this.cmd = cmd;
        this.carId = carId;
        this.areid = areid;
        this.clearCarid = clearCarid;
        this.longitude = longitude;
        this.latitude = latitude;
        this.nowPage = nowPage;
    }


    public String result;
    public String resultNote;
    public int tatalPage;
    public List<clearInfo> clearInfo;

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

    public int getTatalPage() {
        return tatalPage;
    }

    public void setTatalPage(int tatalPage) {
        this.tatalPage = tatalPage;
    }

    public List<SpecialCarWashBean.clearInfo> getClearInfo() {
        return clearInfo;
    }

    public void setClearInfo(List<SpecialCarWashBean.clearInfo> clearInfo) {
        this.clearInfo = clearInfo;
    }

    public class clearInfo {
        public String shopid;
        public String shopIcon;
        public String shopName;
        public String shopLocaltion;
        public String sellerNum;
        public String shopCommentNum;//门店综合评分
        public String distance;//距离
        public String clearCarOriginPrice;
        public String clearCarNowPrice;

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public String getShopIcon() {
            return shopIcon;
        }

        public void setShopIcon(String shopIcon) {
            this.shopIcon = shopIcon;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopLocaltion() {
            return shopLocaltion;
        }

        public void setShopLocaltion(String shopLocaltion) {
            this.shopLocaltion = shopLocaltion;
        }

        public String getSellerNum() {
            return sellerNum;
        }

        public void setSellerNum(String sellerNum) {
            this.sellerNum = sellerNum;
        }

        public String getShopCommentNum() {
            return shopCommentNum;
        }

        public void setShopCommentNum(String shopCommentNum) {
            this.shopCommentNum = shopCommentNum;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getClearCarOriginPrice() {
            return clearCarOriginPrice;
        }

        public void setClearCarOriginPrice(String clearCarOriginPrice) {
            this.clearCarOriginPrice = clearCarOriginPrice;
        }

        public String getClearCarNowPrice() {
            return clearCarNowPrice;
        }

        public void setClearCarNowPrice(String clearCarNowPrice) {
            this.clearCarNowPrice = clearCarNowPrice;
        }
    }
}
