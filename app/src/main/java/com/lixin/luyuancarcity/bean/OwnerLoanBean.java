package com.lixin.luyuancarcity.bean;

/**
 * Created by 小火
 * Create time on  2017/7/14
 * My mailbox is 1403241630@qq.com
 */

public class OwnerLoanBean {
    public String cmd;
    public String certificateName;
    public String certificateNumber;
    public String certificateTel;
    public String certificateLoanMoney;
    public String certificateYear;
    public String certificateMouth;
    public String certificateZhifubao;
    public String certificateWeixin;
    public String certificateBackCard;
    public String certificateBack;
    public String certificateAddress;
    public String certificateDetailAddress;
    public String certificateIdCardUp;
    public String certificateIdCardDown;
    public String certificateIdCardHand;
    public String result;
    public String resultNote;

    public OwnerLoanBean(String cmd, String certificateName, String certificateNumber, String certificateTel,
                         String certificateLoanMoney, String certificateYear, String certificateMouth,
                         String certificateZhifubao, String certificateWeixin, String certificateBackCard,
                         String certificateBack, String certificateAddress, String certificateDetailAddress,
                         String certificateIdCardUp, String certificateIdCardDown, String certificateIdCardHand) {
        this.cmd = cmd;
        this.certificateName = certificateName;
        this.certificateNumber = certificateNumber;
        this.certificateTel = certificateTel;
        this.certificateLoanMoney = certificateLoanMoney;
        this.certificateYear = certificateYear;
        this.certificateMouth = certificateMouth;
        this.certificateZhifubao = certificateZhifubao;
        this.certificateWeixin = certificateWeixin;
        this.certificateBackCard = certificateBackCard;
        this.certificateBack = certificateBack;
        this.certificateAddress = certificateAddress;
        this.certificateDetailAddress = certificateDetailAddress;
        this.certificateIdCardUp = certificateIdCardUp;
        this.certificateIdCardDown = certificateIdCardDown;
        this.certificateIdCardHand = certificateIdCardHand;
    }

    public String getResultNote() {
        return resultNote;
    }

    public void setResultNote(String resultNote) {
        this.resultNote = resultNote;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
