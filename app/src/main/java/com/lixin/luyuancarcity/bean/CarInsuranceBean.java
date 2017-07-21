package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/6/29
 * My mailbox is 1403241630@qq.com
 */

public class CarInsuranceBean {
    public String cmd;
    public String uid;
    public String carNumber;
    public String engineSixNum;
    public String carFrameSixNum;
    public String name;
    public String tel;
    public List<String> license;
    public List<String> cardID;
    public String result;
    public String resultNote;
    public CarInsuranceBean(String cmd, String uid, String carNumber, String engineSixNum, String carFrameSixNum, String name, String tel, List<String> license, List<String> cardID) {
        this.cmd = cmd;
        this.uid = uid;
        this.carNumber = carNumber;
        this.engineSixNum = engineSixNum;
        this.carFrameSixNum = carFrameSixNum;
        this.name = name;
        this.tel = tel;
        this.license = license;
        this.cardID = cardID;
    }
}
