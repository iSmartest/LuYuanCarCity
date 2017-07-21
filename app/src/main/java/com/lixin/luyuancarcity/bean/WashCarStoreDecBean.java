package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/11
 * My mailbox is 1403241630@qq.com
 */

public class WashCarStoreDecBean {
    public String cmd;
    public String result;
    public String resultNote;

    public List<String> rotateShopPics;//门店展示轮播图
    public String shopid;
    public String shopName;//"魔卡器汽配世界"
    public String shopLocaltion;//"郑州市中州大道09号"
    public String sellerNum;//销售数量
    public String shopTime;
    public String shopLongitude;//门店经度
    public String shopLatitude;//纬度
    public String shopDatil;//门店简介
    public String shopCommentNum;//门店综合评分
    public String shopCommondAllNum;//门店总评价人数
    public String shopServeType;//0换轮胎，1做保养，3两个服务都有
    public List<commoditys> commoditys;

    public WashCarStoreDecBean(String cmd, String shopid) {
        this.cmd = cmd;
        this.shopid = shopid;
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

    public List<String> getRotateShopPics() {
        return rotateShopPics;
    }

    public void setRotateShopPics(List<String> rotateShopPics) {
        this.rotateShopPics = rotateShopPics;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
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

    public String getShopTime() {
        return shopTime;
    }

    public void setShopTime(String shopTime) {
        this.shopTime = shopTime;
    }

    public String getShopLongitude() {
        return shopLongitude;
    }

    public void setShopLongitude(String shopLongitude) {
        this.shopLongitude = shopLongitude;
    }

    public String getShopLatitude() {
        return shopLatitude;
    }

    public void setShopLatitude(String shopLatitude) {
        this.shopLatitude = shopLatitude;
    }

    public String getShopDatil() {
        return shopDatil;
    }

    public void setShopDatil(String shopDatil) {
        this.shopDatil = shopDatil;
    }

    public String getShopCommentNum() {
        return shopCommentNum;
    }

    public void setShopCommentNum(String shopCommentNum) {
        this.shopCommentNum = shopCommentNum;
    }

    public String getShopCommondAllNum() {
        return shopCommondAllNum;
    }

    public void setShopCommondAllNum(String shopCommondAllNum) {
        this.shopCommondAllNum = shopCommondAllNum;
    }

    public String getShopServeType() {
        return shopServeType;
    }

    public void setShopServeType(String shopServeType) {
        this.shopServeType = shopServeType;
    }

    public List<WashCarStoreDecBean.commoditys> getCommoditys() {
        return commoditys;
    }

    public void setCommoditys(List<WashCarStoreDecBean.commoditys> commoditys) {
        this.commoditys = commoditys;
    }

    public class commoditys {
        public String commodityid;
        public String commodityTitle;//商品名
        public String commodityIcon;
        public String commodityDescription;//商品描述
        public String commodityNewPrice;//商品现价
        public String commodityOriginalPrice;//商品的原价
        public boolean isChoosed;

        public boolean isChoosed() {
            return isChoosed;
        }

        public void setChoosed(boolean choosed) {
            isChoosed = choosed;
        }

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
    }
}
