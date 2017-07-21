package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/5
 * My mailbox is 1403241630@qq.com
 */

public class ClassListBean {
    public String cmd;//getClassifyListInfo
    public int nowPage;
    public String meunid;//分类id
    public String searchKey;//搜索关键词
    public int meunType;//0价格,1销量
    public int meunSort;//0升序，1代表降序

    public ClassListBean(String cmd,int nowPage,String meunid,int meunType,int meunSort) {
        this.cmd = cmd;
        this.nowPage = nowPage;
        this.meunid = meunid;
        this.meunType = meunType;
        this.meunSort = meunSort;
    }
    public ClassListBean(String cmd,int nowPage,String searchKey) {
        this.cmd = cmd;
        this.nowPage = nowPage;
        this.searchKey = searchKey;
    }

    public String result;
    public String resultNote;
    public int totalPage;
    public List<commoditys> commoditys;
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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ClassListBean.commoditys> getCommoditys() {
        return commoditys;
    }

    public void setCommoditys(List<ClassListBean.commoditys> commoditys) {
        this.commoditys = commoditys;
    }

    public class commoditys{
        public String commodityid;//用于跳转到商品详情得到具体商品
        public String commodityTitle;//商品名
        public String commodityIcon;
        public String commodityDescription;//商品描述
        public String commodityNewPrice;//商品现价
        public String commodityOriginalPrice;//商品的原价
        public String commodityCommendNum;//商品的评论数
        public String commoditysellerNum;//商品的售出数目
        public String commodityRecommend;//商品是否为推荐商品，0推荐，1为非推荐

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

        public String getCommodityRecommend() {
            return commodityRecommend;
        }

        public void setCommodityRecommend(String commodityRecommend) {
            this.commodityRecommend = commodityRecommend;
        }
    }
}
