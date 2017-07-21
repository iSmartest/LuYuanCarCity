package com.lixin.luyuancarcity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.MyAddressAdapter;
import com.lixin.luyuancarcity.bean.MyAddressBean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/20
 * My mailbox is 1403241630@qq.com
 */

public class MyAddressActivity extends BaseActivity{
    private ListView my_address_list;
    private ImageView mBack,mAddAddress;
    private MyAddressAdapter mAdapter;
    private String uid;
    private String temp;
    private List<MyAddressBean.addressList> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        initView();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.Iv_my_address_back);
        mBack.setOnClickListener(this);
        mAddAddress = (ImageView) findViewById(R.id.iv_my_address_edit);
        mAddAddress.setOnClickListener(this);
        my_address_list = (ListView) findViewById(R.id.my_address_list);
        mAdapter = new MyAddressAdapter();
        my_address_list.setAdapter(mAdapter);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Iv_my_address_back:
                finish();
                break;
            case R.id.iv_my_address_edit:
                Intent intent = new Intent(MyAddressActivity.this,AddAddressActivity.class);
                startActivityForResult(intent,2001);
                break;
        }
    }
}
