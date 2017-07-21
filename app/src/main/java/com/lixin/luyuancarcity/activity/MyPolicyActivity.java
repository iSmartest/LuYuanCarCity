package com.lixin.luyuancarcity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.MyPolicyAdapter;
import com.lixin.luyuancarcity.bean.HomeResultBean;
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
 * Create time on  2017/6/27
 * My mailbox is 1403241630@qq.com
 */

public class MyPolicyActivity extends BaseActivity{
    private ListView my_policy_list;
    private MyPolicyAdapter mAdapter;
    private String uid;
    private List<MyPolicyResultBean.financialList> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_policy);
        setTitleText("我的保单",false);
        hideBack(5);
        uid = (String) SPUtils.get(MyPolicyActivity.this,"uid","");
        mList = new ArrayList<>();
        initView();
//        getdata();
    }
    private void initView() {
        my_policy_list = (ListView) findViewById(R.id.my_policy_list);
        mAdapter = new MyPolicyAdapter();
        my_policy_list.setAdapter(mAdapter);
        my_policy_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyPolicyActivity.this,MyPolicyDecActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("financialId",mList.get(position).getFinancialId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void getdata() {
        Map<String, String> params = new HashMap<>();
        RequestBean requestBean = new RequestBean("getMyFinancial",uid);
        String json = new Gson().toJson(requestBean);
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
                MyPolicyResultBean myPolicyResultBean = gson.fromJson(response, MyPolicyResultBean.class);
                if (myPolicyResultBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, myPolicyResultBean.resultNote);
                    return;
                }
                List<MyPolicyResultBean.financialList> financialList = myPolicyResultBean.financialList;
                mList.addAll(financialList);
                mAdapter.setMyPolicy(context,mList);
                my_policy_list.setAdapter(mAdapter);
            }
        });
    }




}
