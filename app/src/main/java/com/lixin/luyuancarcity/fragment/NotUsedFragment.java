package com.lixin.luyuancarcity.fragment;

import android.app.Activity;
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
import com.lixin.luyuancarcity.adapter.NotUsedAdapter;
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

public class NotUsedFragment extends BaseFragment{
    private View view;
    private NotUsedAdapter notUsedAdapter;
    private PullToRefreshListView not_used_list;
    private List<CouponBean.securitiesList> mList;
    private String securitiesType = "0";
    private String uid;
    private Double totalPrice;
    private Double reducePrice;
    private Double allPrice;
    private static final String MARK = "mark";// 设置标记
    private CallBackValue callBackValue;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //当前fragment从activity重写了回调接口  得到接口的实例化对象
        callBackValue =(NotUsedFragment.CallBackValue) getActivity();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_not_used,null);
        mList = new ArrayList<>();
        uid = (String) SPUtils.get(getActivity(),"uid","");
        Bundle bundle = this.getArguments();
        if (bundle != null)
        {
            totalPrice = bundle.getDouble(MARK);
        }
        initView();
        getdata();
        return view;
    }

    private void initView() {
        not_used_list = (PullToRefreshListView) view.findViewById(R.id.not_used_list);
        notUsedAdapter = new NotUsedAdapter();
        not_used_list.setAdapter(notUsedAdapter);
        not_used_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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
        not_used_list.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                ToastUtils.showMessageShort(getActivity(),"已经到底了");
            }
        });
        //在刷新时允许继续滑动
        not_used_list.setScrollingWhileRefreshingEnabled(true);
        not_used_list.setMode(PullToRefreshBase.Mode.BOTH);
        not_used_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                allPrice = Double.parseDouble(mList.get(position - 1).getSecuritiesAllPrice());
                reducePrice = Double.parseDouble(mList.get(position - 1).getSecuritiesReducePrice());
                if (totalPrice >= allPrice){
                    callBackValue.SendMessageValue(allPrice,reducePrice,mList.get(position - 1).getSecuritiesid());
                }else {
                    ToastUtils.showMessageShort(getActivity(),"只有满" + allPrice + "才可以使用！");
                }
            }
        });
    }

    private void getdata() {
        Map<String, String> params = new HashMap<>();
        CouponBean couponBean = new CouponBean("getCouponInfo",securitiesType,uid);
        String json = new Gson().toJson(couponBean);
        params.put("json", json);
        Log.i("NotUsedFragment", "onResponse: " + json.toString());
        dialog.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showMessageShort(context, e.getMessage());
                dialog.dismiss();
                not_used_list.onRefreshComplete();
            }
            @Override
            public void onResponse(String response, int id) {
                Log.i("NotUsedFragment", "onResponse: " + response.toString());
                Gson gson = new Gson();
                dialog.dismiss();
                not_used_list.onRefreshComplete();
                CouponBean couponBean = gson.fromJson(response, CouponBean.class);
                if (couponBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, couponBean.resultNote);
                    return;
                }
                List<CouponBean.securitiesList> securitiesList = couponBean.securitiesList;
                mList.addAll(securitiesList);
                notUsedAdapter.setShopCart(getActivity(),mList,uid);
                not_used_list.setAdapter(notUsedAdapter);
                not_used_list.onRefreshComplete();
            }
        });
    }

    public static NotUsedFragment newMyFragment(double mTotalPrice) {
        //将fragment绑定参数
        Bundle bundle = new Bundle();
        bundle.putDouble(MARK, mTotalPrice);
        NotUsedFragment fragment = new NotUsedFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    //写一个回调接口
    public interface CallBackValue{
        public void SendMessageValue(Double value1, Double value2, String value3);
    }
}
