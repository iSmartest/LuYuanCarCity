package com.lixin.luyuancarcity.bean;

/**
 * Created by 小火
 * Create time on  2017/6/27
 * My mailbox is 1403241630@qq.com
 */

public class SortModel {

    private String name;   //显示的数据
    private String sortLetters;  //显示数据拼音的首字母
    private String leader;


    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSortLetters() {
        return sortLetters;
    }
    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

}
