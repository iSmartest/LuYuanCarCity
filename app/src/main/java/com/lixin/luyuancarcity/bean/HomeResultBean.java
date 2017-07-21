package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/6/26
 * My mailbox is 1403241630@qq.com
 */

public class HomeResultBean {
    public String result;
    public String resultNote;
    public String time;//秒杀倒计时时间
    public List<serveTop> serveTop;//顶部服务展示4个
    public List<serveBottom> serveBottom;//顶部服务展示6个
    public List<rotateTopCommoditys> rotateTopCommoditys;//顶部轮播
    public List<carerFinancial> carerFinancial;//车主金融
    public List<checkServes> checkServes;//九宫格
    public List<dailyPrice> dailyPrice;//每日特价只放新汽车
    public List<bestGoods> bestGoods;//精选商品
    public List<dailyKill> dailyKill;
    public List<goodsAdvive> goodsAdvive;
    public class serveTop{
        public String serveIcon;
        public String serveType;
    }
    public class serveBottom{
        public String serveIcon;
        public String serveType;
    }
    public class rotateTopCommoditys{
        public String rotateType; // 0代表活动详情，1代表商品详情
        public String rotateid;//活动url 或者是商品id 跳转到商品详情得到具体商品或者活动详情
        public String rotateIcon;
    }
    public class carerFinancial{
        public String serveType;
        public String serveDetailTitle; //服务描述
        public String serveIcon;
    }
    public class checkServes{//宫格广告展示10个后台设计时注意第六个是长图不传服务描述
        public String serveType;
        public String serveDetailTitle; //服务描述
        public String serveIcon;
        public String serveTypeId;
    }
    public class dailyPrice{//宫格广告展示10个后台设计时注意第六个是长图不传服务描述
        public String carVersionDetail;//汽车系描述
        public String carVersionName; //汽车系名字
        public String carIcon;
        public String carVersionId;//车系id，用于查看车型时用
        public String carPrice;//价格
    }
    public class bestGoods{
        public String commodityid;//汽车系描述
        public String commodityTitle; //汽车系名字
        public String commodityIcon;
        public String commodityDescription;//车系id，用于查看车型时用
        public String commodityNewPrice;//价格
        public String commodityOriginalPrice;//价格
        public String commodityCommendNum;//价格
        public String commoditysellerNum;//价格
    }
    public class dailyKill{
        public String commodityid;
        public String commodityTitle;
        public String commodityIcon;
        public String commodityDescription;
        public String commodityNewPrice;//商品现价
        public String commodityOriginalPrice;//商品的原价
        public String commodityCommendNum;//商品的评论数
        public String commoditysellerNum;//商品的售出数目
    }
    public class goodsAdvive{
        public String commodityid;
        public String commodityTitle;
        public String commodityIcon;
        public String commodityDescription;
        public String commodityNewPrice;//商品现价
        public String commodityOriginalPrice;//商品的原价
        public String commodityCommendNum;//商品的评论数
        public String commoditysellerNum;//商品的售出数目
    }
}
