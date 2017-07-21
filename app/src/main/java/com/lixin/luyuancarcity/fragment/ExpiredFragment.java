package com.lixin.luyuancarcity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.ExpiredAdapter;
import com.lixin.luyuancarcity.bean.CouponBean;
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
 * Create time on  2017/5/18
 * My mailbox is 1403241630@qq.com
 */

public class ExpiredFragment extends BaseFragment{
    private View view;
    private PullToRefreshListView expired_list;
    private ExpiredAdapter expiredAdapter;
    private List<CouponBean.securitiesList> mList;
    private String uid;
    private String securitiesType = "2";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_expired,null);
        mList = new ArrayList<>();
        uid = (String) SPUtils.get(getActivity(),"uid","");
        initView();
        getdata();
        return view;
    }

    private void initView() {
        expired_list = (PullToRefreshListView) view.findViewById(R.id.expired_list);
        expiredAdapter = new ExpiredAdapter();
        expired_list.setAdapter(expiredAdapter);
        expired_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                mList.clear();
                getdata();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                mList.clear();
                getdata();
            }
        });
        expired_list.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                ToastUtils.showMessageShort(getActivity(),"已经到底了");
            }
        });
        //在刷新时允许继续滑动
        expired_list.setScrollingWhileRefreshingEnabled(true);
        expired_list.setMode(PullToRefreshBase.Mode.BOTH);
        expired_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
    private void getdata() {
        Map<String, String> params = new HashMap<>();
        CouponBean couponBean = new CouponBean("getCouponInfo",securitiesType,uid);
        String json = new Gson().toJson(couponBean);
        params.put("json", json);
        Log.i("ExpiredFragment", "onResponse: " + json.toString());
        dialog.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showMessageShort(context, e.getMessage());
                dialog.dismiss();
                expired_list.onRefreshComplete();
            }
            @Override
            public void onResponse(String response, int id) {
                Log.i("ExpiredFragment", "onResponse: " + response.toString());
                Gson gson = new Gson();
                dialog.dismiss();
                expired_list.onRefreshComplete();
                CouponBean couponBean = gson.fromJson(response, CouponBean.class);
                if (couponBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, couponBean.resultNote);
                    return;
                }
                List<CouponBean.securitiesList> securitiesList = couponBean.securitiesList;
                mList.addAll(securitiesList);
                expiredAdapter.setExpired(getActivity(),mList,uid);
                expired_list.setAdapter(expiredAdapter);
                expired_list.onRefreshComplete();
            }
        });
    }
}
