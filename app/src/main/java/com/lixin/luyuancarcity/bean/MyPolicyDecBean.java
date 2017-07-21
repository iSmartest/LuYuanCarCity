package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/6/29
 * My mailbox is 1403241630@qq.com
 */

public class MyPolicyDecBean {
    public String cmd;// getMyFinancialDetail
    public String uid;
    public String financialId;

    public MyPolicyDecBean(String cmd, String uid, String financialId) {
        this.cmd = cmd;
        this.uid = uid;
        this.financialId = financialId;
    }
    public String result;
    public String resultNote;
    public List<financialDetail> financialDetail;

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

    public List<MyPolicyDecBean.financialDetail> getFinancialDetail() {
        return financialDetail;
    }

    public void setFinancialDetail(List<MyPolicyDecBean.financialDetail> financialDetail) {
        this.financialDetail = financialDetail;
    }

    public class financialDetail{
        public String emergencyTitle;
        public String orderNumer;//数字类型 NO.17041545845855
        public String emergencydes;//说明 由路远在线承接
        public String isInsurancer;//被保险人
        public String emergencylines;//保障的额度
        public String emergencycost;//保障的费用
        public String financialStartLimit;
        public String financialEndLimit;//理财的期限

        public String getEmergencyTitle() {
            return emergencyTitle;
        }

        public void setEmergencyTitle(String emergencyTitle) {
            this.emergencyTitle = emergencyTitle;
        }

        public String getOrderNumer() {
            return orderNumer;
        }

        public void setOrderNumer(String orderNumer) {
            this.orderNumer = orderNumer;
        }

        public String getEmergencydes() {
            return emergencydes;
        }

        public void setEmergencydes(String emergencydes) {
            this.emergencydes = emergencydes;
        }

        public String getIsInsurancer() {
            return isInsurancer;
        }

        public void setIsInsurancer(String isInsurancer) {
            this.isInsurancer = isInsurancer;
        }

        public String getEmergencylines() {
            return emergencylines;
        }

        public void setEmergencylines(String emergencylines) {
            this.emergencylines = emergencylines;
        }

        public String getEmergencycost() {
            return emergencycost;
        }

        public void setEmergencycost(String emergencycost) {
            this.emergencycost = emergencycost;
        }

        public String getFinancialStartLimit() {
            return financialStartLimit;
        }

        public void setFinancialStartLimit(String financialStartLimit) {
            this.financialStartLimit = financialStartLimit;
        }

        public String getFinancialEndLimit() {
            return financialEndLimit;
        }

        public void setFinancialEndLimit(String financialEndLimit) {
            this.financialEndLimit = financialEndLimit;
        }
    }
}
