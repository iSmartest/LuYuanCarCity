package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/19
 * My mailbox is 1403241630@qq.com
 */

public class ManagementCarBean {
    public String cmd;
    public String uid;
    public ManagementCarBean(String cmd, String uid) {
        this.cmd = cmd;
        this.uid = uid;
    }
    public String result;
    public String resultNote;
    public List<carManagerList> carManagerList;

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

    public List<ManagementCarBean.carManagerList> getCarManagerList() {
        return carManagerList;
    }

    public void setCarManagerList(List<ManagementCarBean.carManagerList> carManagerList) {
        this.carManagerList = carManagerList;
    }

    public class carManagerList {

        public String carId;
        public String carImg;
        public String carTitle;
        public String carDes;
        public String isDefault;

        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }

        public String getCarImg() {
            return carImg;
        }

        public void setCarImg(String carImg) {
            this.carImg = carImg;
        }

        public String getCarTitle() {
            return carTitle;
        }

        public void setCarTitle(String carTitle) {
            this.carTitle = carTitle;
        }

        public String getCarDes() {
            return carDes;
        }

        public void setCarDes(String carDes) {
            this.carDes = carDes;
        }

        public String getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(String isDefault) {
            this.isDefault = isDefault;
        }
    }
}
