package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/13
 * My mailbox is 1403241630@qq.com
 */

public class MaintenanceBean {
    public String cmd;
    public String uid;
    public MaintenanceBean(String cmd, String uid) {
        this.cmd = cmd;
        this.uid = uid;
    }
    public String result;
    public String resultNote;
    public List<maintainList> maintainList;

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

    public List<MaintenanceBean.maintainList> getMaintainList() {
        return maintainList;
    }

    public void setMaintainList(List<MaintenanceBean.maintainList> maintainList) {
        this.maintainList = maintainList;
    }

    public class maintainList{
        public String maintainName;
        public List<maintaingeneral> maintaingeneral;

        public String getMaintainName() {
            return maintainName;
        }

        public void setMaintainName(String maintainName) {
            this.maintainName = maintainName;
        }

        public List<MaintenanceBean.maintainList.maintaingeneral> getMaintaingeneral() {
            return maintaingeneral;
        }

        public void setMaintaingeneral(List<MaintenanceBean.maintainList.maintaingeneral> maintaingeneral) {
            this.maintaingeneral = maintaingeneral;
        }

        public class maintaingeneral{
            public String maintainName;//二级标题名字 刹车油
            public String maintainTimes;//月/次
            public String maintainMile;//km/次
            public String totalePrice;//保养总价格
            public List<maintaincomdity> maintaincomdity;

            public String getMaintainName() {
                return maintainName;
            }

            public void setMaintainName(String maintainName) {
                this.maintainName = maintainName;
            }

            public String getMaintainTimes() {
                return maintainTimes;
            }

            public void setMaintainTimes(String maintainTimes) {
                this.maintainTimes = maintainTimes;
            }

            public String getMaintainMile() {
                return maintainMile;
            }

            public void setMaintainMile(String maintainMile) {
                this.maintainMile = maintainMile;
            }

            public String getTotalePrice() {
                return totalePrice;
            }

            public void setTotalePrice(String totalePrice) {
                this.totalePrice = totalePrice;
            }

            public List<MaintenanceBean.maintainList.maintaingeneral.maintaincomdity> getMaintaincomdity() {
                return maintaincomdity;
            }

            public void setMaintaincomdity(List<MaintenanceBean.maintainList.maintaingeneral.maintaincomdity> maintaincomdity) {
                this.maintaincomdity = maintaincomdity;
            }

            public class maintaincomdity{
                public String commodityid;
                public String commodityTitle;
                public String commodityIcon;
                public String commodityDescription;
                public String commodityNewPrice;
                public String commodityOriginalPrice;

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
    }
}
