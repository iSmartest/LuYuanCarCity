package com.lixin.luyuancarcity.bean;

/**
 * Created by 小火
 * Create time on  2017/7/20
 * My mailbox is 1403241630@qq.com
 */

public class AddAddressBean {
    public String cmd;
    public String name;
    public String uid;
    public String tel;
    public String postCode;
    public String address;
    public String detailAddress;
    public int isDefault;
    public String result;
    public int resultNote;

    public AddAddressBean(String cmd, String name, String uid, String tel, String postCode, String address,
                          String detailAddress, int isDefault) {
        this.cmd = cmd;
        this.name = name;
        this.uid = uid;
        this.tel = tel;
        this.postCode = postCode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.isDefault = isDefault;

    }
}
