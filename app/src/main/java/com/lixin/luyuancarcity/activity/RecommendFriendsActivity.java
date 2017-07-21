package com.lixin.luyuancarcity.activity;

import android.os.Bundle;

import com.lixin.luyuancarcity.R;

/**
 * Created by 小火
 * Create time on  2017/7/17
 * My mailbox is 1403241630@qq.com
 */

public class RecommendFriendsActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_friends);
        hideBack(5);
        setTitleText("推荐好友",false);
    }
}
