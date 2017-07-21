package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/6/27
 * My mailbox is 1403241630@qq.com
 */

public class MyPolicyResultBean {
    public String result;
    public String resultNote;
    public List<financialList> financialList;

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

    public List<MyPolicyResultBean.financialList> getFinancialList() {
        return financialList;
    }

    public void setFinancialList(List<MyPolicyResultBean.financialList> financialList) {
        this.financialList = financialList;
    }

    public class financialList{
        public String financialId;
        public String emergencyTitle;
        public String financialStartLimit;
        public String financialEndLimit;

        public String getFinancialId() {
            return financialId;
        }

        public void setFinancialId(String financialId) {
            this.financialId = financialId;
        }

        public String getEmergencyTitle() {
            return emergencyTitle;
        }

        public void setEmergencyTitle(String emergencyTitle) {
            this.emergencyTitle = emergencyTitle;
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

