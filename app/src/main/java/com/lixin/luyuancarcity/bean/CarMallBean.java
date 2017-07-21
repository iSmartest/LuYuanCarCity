package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/4
 * My mailbox is 1403241630@qq.com
 */

public class CarMallBean {
    public String cmd;//getCommodityClassifyInfo
    public String result;//
    public String resultNote;//
    public List<classifyMeun> classifyMeun;//

    public CarMallBean(String cmd) {
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

    public List<CarMallBean.classifyMeun> getClassifyMeun() {
        return classifyMeun;
    }

    public void setClassifyMeun(List<CarMallBean.classifyMeun> classifyMeun) {
        this.classifyMeun = classifyMeun;
    }

    public class classifyMeun{
       public String meunType;
       public String commodityIcon;
       public String commodityid;
       public List<meun> meun;//

        public String getMeunType() {
            return meunType;
        }

        public void setMeunType(String meunType) {
            this.meunType = meunType;
        }

        public String getCommodityIcon() {
            return commodityIcon;
        }

        public void setCommodityIcon(String commodityIcon) {
            this.commodityIcon = commodityIcon;
        }

        public String getCommodityid() {
            return commodityid;
        }

        public void setCommodityid(String commodityid) {
            this.commodityid = commodityid;
        }

        public List<CarMallBean.classifyMeun.meun> getMeun() {
            return meun;
        }

        public void setMeun(List<CarMallBean.classifyMeun.meun> meun) {
            this.meun = meun;
        }

        public class meun{
           public String meunid;
           public String meunType;
           public String advertisementIcon;

            public String getMeunid() {
                return meunid;
            }

            public void setMeunid(String meunid) {
                this.meunid = meunid;
            }

            public String getMeunType() {
                return meunType;
            }

            public void setMeunType(String meunType) {
                this.meunType = meunType;
            }

            public String getAdvertisementIcon() {
                return advertisementIcon;
            }

            public void setAdvertisementIcon(String advertisementIcon) {
                this.advertisementIcon = advertisementIcon;
            }
        }
   }

}

