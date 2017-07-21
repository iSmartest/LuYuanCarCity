package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/6/27
 * My mailbox is 1403241630@qq.com
 */

public class InsuranceCompanyResultBean {
    public String result;
    public String resultNote;
    public String advIcon;//提交页广告图
    public String insurancePhone;//提交页咨询电话
    public List<insuranceCompanyList> insuranceCompanyList;

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

    public String getAdvIcon() {
        return advIcon;
    }

    public void setAdvIcon(String advIcon) {
        this.advIcon = advIcon;
    }

    public String getInsurancePhone() {
        return insurancePhone;
    }

    public void setInsurancePhone(String insurancePhone) {
        this.insurancePhone = insurancePhone;
    }

    public List<InsuranceCompanyResultBean.insuranceCompanyList> getInsuranceCompanyList() {
        return insuranceCompanyList;
    }

    public void setInsuranceCompanyList(List<InsuranceCompanyResultBean.insuranceCompanyList> insuranceCompanyList) {
        this.insuranceCompanyList = insuranceCompanyList;
    }

    public class insuranceCompanyList{
       public String companyid;//承险保险公司id
       public String companyLogo;//承险保险公司logo
       public String companyName;//承险保险公司名称

        public String getCompanyid() {
            return companyid;
        }

        public void setCompanyid(String companyid) {
            this.companyid = companyid;
        }

        public String getCompanyLogo() {
            return companyLogo;
        }

        public void setCompanyLogo(String companyLogo) {
            this.companyLogo = companyLogo;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }
    }
}
