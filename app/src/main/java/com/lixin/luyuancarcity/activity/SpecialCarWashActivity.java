package com.lixin.luyuancarcity.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.AreAdapter;
import com.lixin.luyuancarcity.adapter.SpecialCarWashAdapter;
import com.lixin.luyuancarcity.adapter.StandardAdapter;
import com.lixin.luyuancarcity.bean.SpecialCarWashBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 小火
 * Create time on  2017/7/10
 * My mailbox is 1403241630@qq.com
 */

public class SpecialCarWashActivity extends BaseActivity{
    private View view;
    private ListView lv_group1;
    private ListView lv_group2;
    private String title;
    private int nowPage;
    private String areid;
    private String clearCarid;
    private String longitude;
    private String latitude;
    private List<SpecialCarWashBean.clearInfo> mList;
    private PullToRefreshListView wash_store_list;
    private SpecialCarWashAdapter mAdapter;
    private TextView tvAre,tvStandard;
    private ImageView ivDown01,ivDown02,ivUp01,ivUp02;
    private PopupWindow popupWindow1,popupWindow2;
    private LinearLayout mAre,mStandard;
    private int temp1 = 0;
    private int temp2 = 0;
    private List<String >areList;
    private List<String >standardList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_car_wash);
        mList = new ArrayList<>();
        hideBack(5);
//        setTitleText();
        initView();
    }

    private void initView() {
        wash_store_list = (PullToRefreshListView) findViewById(R.id.wash_store_list);
        ivDown01 = (ImageView) findViewById(R.id.iv_tag_down01);
        ivDown02 = (ImageView) findViewById(R.id.iv_tag_down02);
        ivUp01 = (ImageView) findViewById(R.id.iv_tag_up01);
        ivUp02 = (ImageView) findViewById(R.id.iv_tag_up02);
        tvAre = (TextView) findViewById(R.id.text_car_wash_are);
        tvStandard = (TextView) findViewById(R.id.text_car_wash_standard);
        mAre = (LinearLayout) findViewById(R.id.linear_special_car_wash_are);
        mStandard = (LinearLayout) findViewById(R.id.linear_special_car_wash_standard);
        mAre.setOnClickListener(this);
        mStandard.setOnClickListener(this);
        mAdapter = new SpecialCarWashAdapter();
        wash_store_list.setAdapter(mAdapter);
        wash_store_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(SpecialCarWashActivity.this, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                nowPage = 1;
                mList.clear();
                getdata();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                nowPage ++;
                getdata();
            }
        });

        wash_store_list.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                Toast.makeText(getApplication(), "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        //在刷新时允许继续滑动
        wash_store_list.setScrollingWhileRefreshingEnabled(true);
        wash_store_list.setMode(PullToRefreshBase.Mode.BOTH);
        wash_store_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(SpecialCarWashActivity.this,StoreDecActivity.class));
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_special_car_wash_are:
                if (temp1 == 0){
                    temp1 = 1;
                    tvAre.setTextColor(getResources().getColor(R.color.app_main_colour));
                    ivUp01.setVisibility(View.VISIBLE);
                    ivDown01.setVisibility(View.GONE);
                }else {
                    temp1 = 0;
                    tvAre.setTextColor(getResources().getColor(R.color.black));
                    ivDown01.setVisibility(View.VISIBLE);
                    ivUp01.setVisibility(View.GONE);
                }
                popuwindShow1(mAre);
                break;
            case R.id.linear_special_car_wash_standard:
                if (temp2 == 0){
                    temp2 = 1;
                    tvStandard.setTextColor(getResources().getColor(R.color.app_main_colour));
                    ivUp02.setVisibility(View.VISIBLE);
                    ivDown02.setVisibility(View.GONE);
                }else {
                    temp2 = 0;
                    tvStandard.setTextColor(getResources().getColor(R.color.black));
                    ivDown02.setVisibility(View.VISIBLE);
                    ivUp02.setVisibility(View.GONE);
                }
                popuwindShow2(mStandard);
                break;
        }
    }
    private void popuwindShow1(View parent) {
        if (popupWindow1 == null) {
            LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.are_list, null);
            lv_group1 = (ListView) view.findViewById(R.id.lvGroup1);
            Collections.reverse(areList);
            AreAdapter areAdapter = new AreAdapter(SpecialCarWashActivity.this, areList);
            lv_group1.setAdapter(areAdapter);
            int width = parent.getMeasuredWidth();
            popupWindow1 = new PopupWindow(view, width, 400);
        }
        popupWindow1.setFocusable(true);
        popupWindow1.setOutsideTouchable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow1.setBackgroundDrawable(new BitmapDrawable());
        WindowManager windowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        int xPos = windowManager.getDefaultDisplay().getWidth() / 2
                - popupWindow1.getWidth() / 2;
        popupWindow1.showAsDropDown(parent, 0, 4);
        lv_group1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                getdata();
                mAdapter.notifyDataSetChanged();
                if (popupWindow1 != null)
                    popupWindow1.dismiss();
            }
        });
    }
    private void popuwindShow2(View parent) {
        if (popupWindow2 == null) {
            LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.standard_list, null);
            lv_group2 = (ListView) view.findViewById(R.id.lvGroup2);
            Collections.reverse(standardList);
            StandardAdapter standardAdapter = new StandardAdapter(SpecialCarWashActivity.this, standardList);
            lv_group2.setAdapter(standardAdapter);
            int width = parent.getMeasuredWidth();
            popupWindow2 = new PopupWindow(view, width, 400);
        }
        popupWindow2.setFocusable(true);
        popupWindow2.setOutsideTouchable(true);
        popupWindow2.setBackgroundDrawable(new BitmapDrawable());
        WindowManager windowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        int xPos = windowManager.getDefaultDisplay().getWidth() / 2
                - popupWindow2.getWidth() / 2;
        popupWindow2.showAsDropDown(parent, 0, 4);
        lv_group2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                getdata();
                mAdapter.notifyDataSetChanged();
                if (popupWindow2 != null){
                    popupWindow2.dismiss();
                }
            }
        });
    }
    private void getdata() {
        Map<String, String> params = new HashMap<>();
        SpecialCarWashBean requestBean = new SpecialCarWashBean("getHomeList",title,areid,clearCarid,longitude,latitude,nowPage);
        String json = new Gson().toJson(requestBean);
        params.put("json", json);
        Log.i("MyShareActivity", "onResponse: " + json.toString());
        dialog1.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showMessageShort(context, e.getMessage());
                dialog1.dismiss();
                wash_store_list.onRefreshComplete();
            }
            @Override
            public void onResponse(String response, int id) {
                Log.i("MyShareActivity", "onResponse: " + response.toString());
                Gson gson = new Gson();
                dialog1.dismiss();
                wash_store_list.onRefreshComplete();
                SpecialCarWashBean specialCarWashBean = gson.fromJson(response, SpecialCarWashBean.class);
                if (specialCarWashBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, specialCarWashBean.resultNote);
                    return;
                }
                if (specialCarWashBean.getTatalPage() < nowPage) {
                    ToastUtils.showMessageShort(context, "没有更多了");
                    return;
                }
                List<SpecialCarWashBean.clearInfo> securitiesList = specialCarWashBean.clearInfo;
                mList.addAll(securitiesList);
                mAdapter.setSpecialCarWash(SpecialCarWashActivity.this,mList);
                wash_store_list.setAdapter(mAdapter);
                wash_store_list.onRefreshComplete();
            }
        });
    }
}
