package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/14
 * My mailbox is 1403241630@qq.com
 */

public class StoreBean {
   public String cmd;
   public String serveType;
   public int sort;
    public List<String> filtrate;
   public int nowPage;
    public String longitude;
    public String latitude;


        public StoreBean(String cmd, String serveType, int sort, List<String> filtrate, int nowPage, String longitude, String latitude) {
        this.cmd = cmd;
        this.serveType = serveType;
        this.sort = sort;
        this.filtrate = filtrate;
        this.nowPage = nowPage;
        this.longitude = longitude;
        this.latitude = latitude;

    }

    public String result;
    public String resultNote;
    public int totalPage;
    public List<shop> shop;

    public List<StoreBean.shop> getShop() {
        return shop;
    }

    public void setShop(List<StoreBean.shop> shop) {
        this.shop = shop;
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

    public class shop {
        public String shopid;
        public String shopIcon;
        public String shopName;
        public String shopLocaltion;
        public String sellerNum;
        public String shopStar;
        public String shopType;
        public String distance;

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

        public String getShopStar() {
            return shopStar;
        }

        public void setShopStar(String shopStar) {
            this.shopStar = shopStar;
        }

        public String getShopType() {
            return shopType;
        }

        public void setShopType(String shopType) {
            this.shopType = shopType;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }
    }
}
