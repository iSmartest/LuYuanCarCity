package com.lixin.luyuancarcity.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.InsuranceTypeDecAdapter;
import com.lixin.luyuancarcity.bean.InsuranceTypeDecBean;
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

public class InsuranceTypeDecActivity extends BaseActivity
{
    private String title ,emergencyid;
    private LinearLayout mPhoneConsultation,mCollection;
    private TextView mNowBuy,mName,mNowPrice,mOrigPrice,mTvCollection;
    private ListView insurance_dec_list;
    private View headView,footView;
    private ImageView mPicture,mIvCollection;
    private WebView wabView;
    private String uid;
    private List<InsuranceTypeDecBean.commodityParameters> mList;
    private InsuranceTypeDecAdapter mAdapter;
    private String telephone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_dec);
        uid = (String) SPUtils.get(InsuranceTypeDecActivity.this,"uid","");
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        emergencyid = intent.getStringExtra("emergencyid");
        setTitleText(title,false);
        mList = new ArrayList<>();
        hideBack(5);
        initView();
//        getdata();
    }

    private void initView() {
        mPhoneConsultation = (LinearLayout) findViewById(R.id.linear_dec_phone_consultation);
        mPhoneConsultation.setOnClickListener(this);
        mNowBuy = (TextView) findViewById(R.id.text_dec_now_buy);
        mNowBuy.setOnClickListener(this);
        insurance_dec_list = (ListView) findViewById(R.id.insurance_dec_list);
        headView = LayoutInflater.from(InsuranceTypeDecActivity.this).inflate(R.layout.insrance_dec_head,null);
        mPicture = (ImageView) headView.findViewById(R.id.img_insurance_dec_picture);
        mName = (TextView) headView.findViewById(R.id.text_insurance_dec_name);
        mNowPrice = (TextView) headView.findViewById(R.id.text_insurance_dec_now_price);
        mOrigPrice = (TextView) headView.findViewById(R.id.text_insurance_dec_orig_price);
        mCollection = (LinearLayout) headView.findViewById(R.id.linear_insurance_dec_collection);
        mIvCollection = (ImageView) headView.findViewById(R.id.iv_insurance_dec_collection);
        mTvCollection = (TextView) headView.findViewById(R.id.text_insurance_dec_collection);
        footView = LayoutInflater.from(InsuranceTypeDecActivity.this).inflate(R.layout.insrance_dec_foot,null);
        wabView = (WebView) footView.findViewById(R.id.wabView);
        insurance_dec_list.addHeaderView(headView);
        insurance_dec_list.addFooterView(footView);
        mAdapter = new InsuranceTypeDecAdapter();
        insurance_dec_list.setAdapter(mAdapter);
    }

    private void getdata() {
        Map<String, String> params = new HashMap<>();
        RequestBean requestBean = new RequestBean("inEmergencyList",uid,emergencyid);
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
                InsuranceTypeDecBean insuranceTypeDecBean = gson.fromJson(response, InsuranceTypeDecBean.class);
                if (insuranceTypeDecBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, insuranceTypeDecBean.resultNote);
                    return;
                }
                List<InsuranceTypeDecBean.commodityParameters> financialList = insuranceTypeDecBean.commodityParameters;
                mList.addAll(financialList);
                mAdapter.setInsuranceTypeDec(context,mList);
                insurance_dec_list.setAdapter(mAdapter);
                telephone = insuranceTypeDecBean.getEmergencyiPhone();
                //缺少底部图片字段
                mName.setText(insuranceTypeDecBean.getEmergencyTitle());
                mNowPrice.setText(insuranceTypeDecBean.getEmergencyCurrentPrice());
                mOrigPrice.setText(insuranceTypeDecBean.getEmergencyOriPrice());
                switch (insuranceTypeDecBean.getEmergencyLike()){
                    case "0":
                        mIvCollection.setImageResource(R.drawable.shop_collection);
                        mTvCollection.setText("取消收藏");
                        break;
                    case "1":
                        mIvCollection.setImageResource(R.drawable.not_collection);
                        mTvCollection.setText("收藏");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_dec_phone_consultation://可能需要弹窗
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_CALL);
                intent2.setData(Uri.parse("tel:" + telephone));
                startActivity(intent2);
                break;

            case R.id.text_dec_now_buy:
                break;
        }
    }
}
