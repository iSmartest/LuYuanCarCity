package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/6/27
 * My mailbox is 1403241630@qq.com
 */

public class CarStyleBean {
    public String result;
    public String resultNote;
    public List<carsSelectList> carsSelectList;

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

    public List<CarStyleBean.carsSelectList> getCarsSelectList() {
        return carsSelectList;
    }

    public void setCarsSelectList(List<CarStyleBean.carsSelectList> carsSelectList) {
        this.carsSelectList = carsSelectList;
    }

    public class carsSelectList{
        public String carBrandId;
        public String carleader;//汽车头标
        public String carName;

        public String getCarBrandId() {
            return carBrandId;
        }

        public void setCarBrandId(String carBrandId) {
            this.carBrandId = carBrandId;
        }

        public String getCarleader() {
            return carleader;
        }

        public void setCarleader(String carleader) {
            this.carleader = carleader;
        }

        public String getCarName() {
            return carName;
        }

        public void setCarName(String carName) {
            this.carName = carName;
        }
    }
}
