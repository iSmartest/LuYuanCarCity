package com.lixin.luyuancarcity.adapter;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/17
 * My mailbox is 1403241630@qq.com
 */

public class ShopingCartBean {
    public String cmd;
    public int nowPage;
    public String uid;
    public ShopingCartBean(String cmd, String uid, int nowPage) {
        this.cmd =cmd;
        this.uid =uid;
        this.nowPage = nowPage;
    }
    public String result;
    public String resultNote;
    public int totalPage;
    public List<commoditys> commoditys;

    public List<ShopingCartBean.commoditys> getCommoditys() {
        return commoditys;
    }

    public void setCommoditys(List<ShopingCartBean.commoditys> commoditys) {
        this.commoditys = commoditys;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getResultNote() {
        return resultNote;
    }

    public void setResultNote(String resultNote) {
        this.resultNote = resultNote;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public class commoditys{
        public String commodityid;
        public String commodityBrandid;
        public String commodityShopid;
        public String commodityIcon;
        public String commodityNewPrice;
        public String commodityTitle;
        public String commoditySize;
        public String commoditysellerNum;
        public String commodityShooCarNum;
        public String commodityDescription;
        public boolean isChoosed;
        public boolean isCheck = false;

        public boolean isChoosed() {
            return isChoosed;
        }

        public void setChoosed(boolean choosed) {
            isChoosed = choosed;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getCommodityid() {
            return commodityid;
        }

        public void setCommodityid(String commodityid) {
            this.commodityid = commodityid;
        }

        public String getCommodityBrandid() {
            return commodityBrandid;
        }

        public void setCommodityBrandid(String commodityBrandid) {
            this.commodityBrandid = commodityBrandid;
        }

        public String getCommodityShopid() {
            return commodityShopid;
        }

        public void setCommodityShopid(String commodityShopid) {
            this.commodityShopid = commodityShopid;
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

        public String getCommoditySize() {
            return commoditySize;
        }

        public void setCommoditySize(String commoditySize) {
            this.commoditySize = commoditySize;
        }

        public String getCommoditysellerNum() {
            return commoditysellerNum;
        }

        public void setCommoditysellerNum(String commoditysellerNum) {
            this.commoditysellerNum = commoditysellerNum;
        }

        public String getCommodityShooCarNum() {
            return commodityShooCarNum;
        }

        public void setCommodityShooCarNum(String commodityShooCarNum) {
            this.commodityShooCarNum = commodityShooCarNum;
        }

        public String getCommodityDescription() {
            return commodityDescription;
        }

        public void setCommodityDescription(String commodityDescription) {
            this.commodityDescription = commodityDescription;
        }


    }
}
