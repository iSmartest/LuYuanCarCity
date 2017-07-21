package com.lixin.luyuancarcity.bean;

/**
 * Created by 小火
 * Create time on  2017/7/14
 * My mailbox is 1403241630@qq.com
 */

public class OwnerCreditCardBean {
    public String cmd;
    public String certificateName;
    public String certificateConpany;
    public String certificateAddress;
    public String certificateDetailAddress;
    public String certificateNumber;
    public String creditCardType;
    public String certificateIdCardUp;
    public String certificateIdCardDown;
    public String certificateIdCardHand;
    public String result;
    public String resultNote;

    public OwnerCreditCardBean(String cmd, String certificateName, String certificateConpany, String certificateAddress,
                               String certificateDetailAddress, String certificateNumber, String creditCardType,
                               String certificateIdCardUp, String certificateIdCardDown, String certificateIdCardHand) {
        this.cmd = cmd;
        this.certificateName = certificateName;
        this.certificateConpany = certificateConpany;
        this.certificateAddress = certificateAddress;
        this.certificateDetailAddress = certificateDetailAddress;
        this.certificateNumber = certificateNumber;
        this.creditCardType = creditCardType;
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
