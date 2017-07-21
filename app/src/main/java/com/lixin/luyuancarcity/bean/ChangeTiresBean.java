package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/21
 * My mailbox is 1403241630@qq.com
 */

public class ChangeTiresBean {
    public String cmd;
    public int nowPage;
    public String carId;
    public int sort;
    public int isTop;
    public int explosionproof;
    public int snowfield;
    public List<String> tireBrand;
    public List<price> price;
    public List <tireSize> tireSize;
    public ChangeTiresBean(String cmd, int nowPage, String carId, int sort, int isTop, int explosionproof,
                           int snowfield, List<String> tireBrand, List<price> price,List <tireSize> tireSize) {
        this.cmd = cmd;
        this.nowPage = nowPage;
        this.carId = carId;
        this.sort = sort;
        this.isTop = isTop;
        this.explosionproof = explosionproof;
        this.snowfield = snowfield;
        this.tireBrand = tireBrand;
        this.price = price;
        this.tireSize = tireSize;
    }

    public class price{
        public String lowprice;
        public String hightprice;
    }

    public class tireSize {
        public String tireFirstSize;
        public String tireScendSize;
        public String tireTre;
    }

    public String result;
    public String resultNote;
    public int totalPage;
    public List<tireList> tireList;

    public List<ChangeTiresBean.tireList> getTireList() {
        return tireList;
    }

    public void setTireList(List<ChangeTiresBean.tireList> tireList) {
        this.tireList = tireList;
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

    public class tireList{
        public String commodityid;
        public String commodityTitle;
        public String commodityIcon;
        public String commodityDescription;
        public String commodityNewPrice;
        public String commodityOriginalPrice;
        public String commodityCommendNum;
        public String commoditysellerNum;

        public String getCommodityid() {
            return commodityid;
        }

        public void setCommodityid(String commodityid) {
            this.commodityid = commodityid;
        }

        public String getCommodityTitle() {
            return commodityTitle;
        }

        public void setCommodityTitle(String commodityTitle) {
            this.commodityTitle = commodityTitle;
        }

        public String getCommodityIcon() {
            return commodityIcon;
        }

        public void setCommodityIcon(String commodityIcon) {
            this.commodityIcon = commodityIcon;
        }

        public String getCommodityDescription() {
            return commodityDescription;
        }

        public void setCommodityDescription(String commodityDescription) {
            this.commodityDescription = commodityDescription;
        }

        public String getCommodityNewPrice() {
            return commodityNewPrice;
        }

        public void setCommodityNewPrice(String commodityNewPrice) {
            this.commodityNewPrice = commodityNewPrice;
        }

        public String getCommodityOriginalPrice() {
            return commodityOriginalPrice;
        }

        public void setCommodityOriginalPrice(String commodityOriginalPrice) {
            this.commodityOriginalPrice = commodityOriginalPrice;
        }

        public String getCommodityCommendNum() {
            return commodityCommendNum;
        }

        public void setCommodityCommendNum(String commodityCommendNum) {
            this.commodityCommendNum = commodityCommendNum;
        }

        public String getCommoditysellerNum() {
            return commoditysellerNum;
        }

        public void setCommoditysellerNum(String commoditysellerNum) {
            this.commoditysellerNum = commoditysellerNum;
        }
    }
}
