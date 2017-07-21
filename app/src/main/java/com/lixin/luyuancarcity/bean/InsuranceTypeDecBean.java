package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/6/28
 * My mailbox is 1403241630@qq.com
 */

public class InsuranceTypeDecBean {
    public String result;
    public String resultNote;
    public String emergencyTitle;//标题
    public String emergencyOriPrice;//原价
    public String emergencyCurrentPrice; //现价
    public String emergencyLike;//是否收藏过0代表收藏过，1代表取消收藏
    public String carUrl;//车商品特点链接
    public String emergencyiPhone;//咨询电话
    public List<commodityParameters> commodityParameters;

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

    public String getEmergencyTitle() {
        return emergencyTitle;
    }

    public void setEmergencyTitle(String emergencyTitle) {
        this.emergencyTitle = emergencyTitle;
    }

    public String getEmergencyOriPrice() {
        return emergencyOriPrice;
    }

    public void setEmergencyOriPrice(String emergencyOriPrice) {
        this.emergencyOriPrice = emergencyOriPrice;
    }

    public String getEmergencyCurrentPrice() {
        return emergencyCurrentPrice;
    }

    public void setEmergencyCurrentPrice(String emergencyCurrentPrice) {
        this.emergencyCurrentPrice = emergencyCurrentPrice;
    }

    public String getEmergencyLike() {
        return emergencyLike;
    }

    public void setEmergencyLike(String emergencyLike) {
        this.emergencyLike = emergencyLike;
    }

    public String getCarUrl() {
        return carUrl;
    }

    public void setCarUrl(String carUrl) {
        this.carUrl = carUrl;
    }

    public String getEmergencyiPhone() {
        return emergencyiPhone;
    }

    public void setEmergencyiPhone(String emergencyiPhone) {
        this.emergencyiPhone = emergencyiPhone;
    }

    public List<InsuranceTypeDecBean.commodityParameters> getCommodityParameters() {
        return commodityParameters;
    }

    public void setCommodityParameters(List<InsuranceTypeDecBean.commodityParameters> commodityParameters) {
        this.commodityParameters = commodityParameters;
    }

    public class commodityParameters{
        public String parameterTypes;//参数的类型
        public String parameters;//参数类型对应的具体参数

        public String getParameterTypes() {
            return parameterTypes;
        }

        public void setParameterTypes(String parameterTypes) {
            this.parameterTypes = parameterTypes;
        }

        public String getParameters() {
            return parameters;
        }

        public void setParameters(String parameters) {
            this.parameters = parameters;
        }
    }

}
