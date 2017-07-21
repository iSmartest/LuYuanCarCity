package com.lixin.luyuancarcity.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.ManagementCarAdapter;
import com.lixin.luyuancarcity.bean.MaintenanceBean;
import com.lixin.luyuancarcity.bean.ManagementCarBean;
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
 * Create time on  2017/7/19
 * My mailbox is 1403241630@qq.com
 */

public class ManagementCarActivity extends BaseActivity{
    private ListView management_car_list;
    private LinearLayout mNewAdd;
    private ManagementCarAdapter mAdapter;
    private String cmd = "managerCar";
    private String uid;
    private List<ManagementCarBean.carManagerList> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_car);
        hideBack(5);
        setTitleText("管理车型",false);
        uid = (String) SPUtils.get(context,"uid","");
        mList = new ArrayList<>();
        initView();
    }

    private void initView() {
        management_car_list = (ListView) findViewById(R.id.management_car_list);
        mAdapter = new ManagementCarAdapter();
        management_car_list.setAdapter(mAdapter);
        mNewAdd = (LinearLayout) findViewById(R.id.linear_management_car_new_add);
        mNewAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_management_car_new_add:
                ToastUtils.showMessageShort(context,"跳转到车型选择");
                break;
        }
    }
    public void getdata(){
        Map<String,String> params = new HashMap<>();
        final ManagementCarBean manageCarBean = new ManagementCarBean(cmd,uid);
        String json = new Gson().toJson(manageCarBean);
        params.put("json",json);
        dialog1.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showMessageShort(context,e.getMessage());
                dialog1.dismiss();
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                dialog1.dismiss();
                ManagementCarBean managementCarBean = gson.fromJson(response, ManagementCarBean.class);
                if (manageCarBean.result.equals("1")){
                    ToastUtils.showMessageShort(context,manageCarBean.getResultNote());
                }
                List<ManagementCarBean.carManagerList> carManagerList = managementCarBean.carManagerList;
                mList.addAll(carManagerList);
                mAdapter.setManagmentCar(context,mList);
                management_car_list.setAdapter(mAdapter);

            }
        });
    }
}
