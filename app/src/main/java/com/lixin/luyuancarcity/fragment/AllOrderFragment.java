package com.lixin.luyuancarcity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
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
import com.lixin.luyuancarcity.adapter.MyOrderAdapter;
import com.lixin.luyuancarcity.bean.MyOrderBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.SPUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

import static com.lixin.luyuancarcity.activity.MyApplication.getApplication;

/**
 * Created by 小火
 * Create time on  2017/7/18
 * My mailbox is 1403241630@qq.com
 */

public class AllOrderFragment extends BaseFragment{
    private View view;
    private PullToRefreshListView order_list;
    private MyOrderAdapter mAdapter;
    private int nowPage = 1;
    private String uid;
    private String orderState = "1";
    private List<MyOrderBean.orders> mList;
    private String cmd = "getOrderInfo";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order_content,null);
        uid = (String) SPUtils.get(getActivity(),"uid","");
        mList = new ArrayList<>();
        initView();
        getdata();
        return view;
    }

    private void initView() {
        order_list = (PullToRefreshListView) view.findViewById(R.id.order_list);
        mAdapter = new MyOrderAdapter();
        order_list.setAdapter(mAdapter);
        order_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
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
        order_list.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                Toast.makeText(getApplication(), "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        //在刷新时允许继续滑动
        order_list.setScrollingWhileRefreshingEnabled(true);
        order_list.setMode(PullToRefreshBase.Mode.BOTH);
        order_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void getdata() {
        Map<String, String> params = new HashMap<>();
        MyOrderBean myOrderBean = new MyOrderBean(cmd,orderState,nowPage,uid);
        String json = new Gson().toJson(myOrderBean);
        params.put("json", json);
        dialog.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showMessageShort(context, e.getMessage());
                dialog.dismiss();
                order_list.onRefreshComplete();
            }
            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                dialog.dismiss();
                order_list.onRefreshComplete();
                MyOrderBean myOrderBean = gson.fromJson(response, MyOrderBean.class);
                if (myOrderBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, myOrderBean.resultNote);
                    return;
                }
                if (myOrderBean.totalPage < nowPage) {
                    ToastUtils.showMessageShort(context, "没有更多了");
                    return;
                }
                List<MyOrderBean.orders> securitiesList = myOrderBean.orders;
                mList.addAll(securitiesList);
                mAdapter.setMyOrder(getActivity(),mList,uid,orderState);
                order_list.setAdapter(mAdapter);
                order_list.onRefreshComplete();
            }
        });
    }


}
