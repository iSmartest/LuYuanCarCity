package com.lixin.luyuancarcity.bean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/20
 * My mailbox is 1403241630@qq.com
 */

public class MyAddressBean {
    public String cmd;
    public String uid;
    public String result;
    public String resultNote;
    public List<addressList> addressList;
    public class addressList {
        public String sendid;
        public String sendSelected;
        public String sendDetailAddress;
    }
}
