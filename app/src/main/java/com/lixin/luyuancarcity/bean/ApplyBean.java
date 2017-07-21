package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/20
 * My mailbox is 1403241630@qq.com
 */

public class ApplyBean {
    public String cmd;
    public String certificateName;
    public String certificateCompany;
    public String certificateBusiness1;
    public String certificateLegalPerson;
    public String certificateCompanyDetailAddress;
    public String certificateAddress;
    public String certificateTel;
    public String certificateWeixinorqq;
    public String certificateIdCardUp;
    public String certificateIdCardDown;
    public String certificateIdCardHand;
    public List<String> certificateCertificateImages;
    public String certificateSupplement;
    public String result;
    public String resultNote;

    public ApplyBean(String cmd, String certificateName, String certificateCompany, String certificateBusiness1,
                     String certificateLegalPerson, String certificateCompanyDetailAddress,
                     String certificateAddress, String certificateTel, String certificateWeixinorqq,
                     String certificateIdCardUp, String certificateIdCardDown, String certificateIdCardHand,
                     List<String> certificateCertificateImages, String certificateSupplement) {
        this.cmd = cmd;
        this.certificateName = certificateName;
        this.certificateCompany = certificateCompany;
        this.certificateBusiness1 = certificateBusiness1;
        this.certificateLegalPerson = certificateLegalPerson;
        this.certificateCompanyDetailAddress = certificateCompanyDetailAddress;
        this.certificateAddress = certificateAddress;
        this.certificateTel = certificateTel;
        this.certificateWeixinorqq = certificateWeixinorqq;
        this.certificateIdCardUp = certificateIdCardUp;
        this.certificateIdCardDown = certificateIdCardDown;
        this.certificateIdCardHand = certificateIdCardHand;
        this.certificateCertificateImages = certificateCertificateImages;
        this.certificateSupplement = certificateSupplement;
    }
}
