package com.lixin.luyuancarcity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.MyPolicyDecAdapter;
import com.lixin.luyuancarcity.bean.MyPolicyDecBean;
import com.lixin.luyuancarcity.bean.MyPolicyResultBean;
import com.lixin.luyuancarcity.bean.RequestBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.SPUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 小火
 * Create time on  2017/6/28
 * My mailbox is 1403241630@qq.com
 */

public class MyPolicyDecActivity extends BaseActivity{
    private ListView my_policy_dec_list;
    private MyPolicyDecAdapter mAdapter;
    private String uid;
    private String financialId;
    private List<MyPolicyDecBean.financialDetail> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_policy_dec);
        uid = (String) SPUtils.get(MyPolicyDecActivity.this,"uid","");
        Intent intent = getIntent();
        financialId = intent.getStringExtra("financialId");
        mList = new ArrayList<>();
        setTitleText("我的理财",false);
        hideBack(5);
        initView();
//        getdata();
    }

    private void initView() {
        my_policy_dec_list = (ListView) findViewById(R.id.my_policy_dec_list);
        mAdapter = new MyPolicyDecAdapter();
        my_policy_dec_list.setAdapter(mAdapter);
    }



    private void getdata() {
        Map<String, String> params = new HashMap<>();
        MyPolicyDecBean myPolicyDecBean = new MyPolicyDecBean("getMyFinancial",uid,financialId);
        String json = new Gson().toJson(myPolicyDecBean);
        params.put("json", json);
        Log.i("ShopDecActivity", "getdata: " + json);
        dialog1.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showMessageShort(context, e.getMessage());
                dialog1.dismiss();
            }
            @Override
            public void onResponse(String response, int id) {
                Log.i("ShopDecActivity", "onResponse: " + response.toString());
                Gson gson = new Gson();
                dialog1.dismiss();
                MyPolicyDecBean myPolicyDecBean = gson.fromJson(response, MyPolicyDecBean.class);
                if (myPolicyDecBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, myPolicyDecBean.resultNote);
                    return;
                }
                List<MyPolicyDecBean.financialDetail> financialList = myPolicyDecBean.financialDetail;
                mList.addAll(financialList);
                mAdapter.setMyPolicyDec(context,mList);
                my_policy_dec_list.setAdapter(mAdapter);
            }
        });
    }



}
