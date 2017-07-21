package com.lixin.luyuancarcity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.RefuelingRechargeAdapter;
import com.lixin.luyuancarcity.bean.InsuranceCompanyResultBean;
import com.lixin.luyuancarcity.bean.RefuelingRechargeBean;
import com.lixin.luyuancarcity.bean.RequestBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.utils.ImageManager;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 小火
 * Create time on  2017/7/3
 * My mailbox is 1403241630@qq.com
 */

public class RefuelingRechargeActivity extends BaseActivity implements RefuelingRechargeAdapter.RechargeInterface {
    private ListView oil_card_information_list;
    private RefuelingRechargeAdapter mAdapter;
    private  List<RefuelingRechargeBean.topUpList> mList;
    private ImageView mPicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refueling_recharge);
        hideBack(5);
        setTitleText("加油充值",false);
        mList = new ArrayList<>();
        initView();
        getOilCardInformation();
    }



    private void initView() {
        oil_card_information_list = (ListView) findViewById(R.id.oil_card_information_list);
        mPicture = (ImageView) findViewById(R.id.imag_refueling_recharge);
        mAdapter = new RefuelingRechargeAdapter();
        mAdapter.setRechargeInterface(this);
        oil_card_information_list.setAdapter(mAdapter);
    }
    private void getOilCardInformation() {
        Map<String, String> params = new HashMap<>();
        RefuelingRechargeBean refuelingRechargeBean = new RefuelingRechargeBean("getComeOnTopUp");
        String json = new Gson().toJson(refuelingRechargeBean);
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
                RefuelingRechargeBean refuelingRechargeBean = gson.fromJson(response, RefuelingRechargeBean.class);
                if (refuelingRechargeBean.getResult().equals("1")) {
                    ToastUtils.showMessageShort(context, refuelingRechargeBean.getResultNote());
                    return;
                }
                List<RefuelingRechargeBean.topUpList> topUpList = refuelingRechargeBean.topUpList;
                mList.addAll(topUpList);
                mAdapter.setRefuelingRecharge(context,mList);
                oil_card_information_list.setAdapter(mAdapter);
                String img = refuelingRechargeBean.getImg();
                if (TextUtils.isEmpty(img)){
                    mPicture.setImageResource(R.drawable.image_fail_empty);
                }else {
                    ImageManager.imageLoader.displayImage(img,mPicture,ImageManager.options3);
                }
            }
        });
    }

    @Override
    public void nowRecharge(int position) {
        // 点击立即充值方法
        Intent intent = new Intent(RefuelingRechargeActivity.this,RefuelingRechargeDecActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
    }
}
