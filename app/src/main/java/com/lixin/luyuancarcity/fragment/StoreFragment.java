package com.lixin.luyuancarcity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.activity.StoreDecActivity;
import com.lixin.luyuancarcity.adapter.StoreAdapter;
import com.lixin.luyuancarcity.bean.StoreBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 小火
 * Create time on  2017/6/23
 * My mailbox is 1403241630@qq.com
 */

public class StoreFragment extends BaseFragment implements View.OnClickListener {
    private View view;
    private PullToRefreshListView store_class;
    private LinearLayout mPosition,mTypes,mDefaultSort;
    private TextView tvPosition,tvTypes,tvDefaultSort;
    private ImageView mDown01,mDown02,mDown03,mUp01,mUp02,mUp03;
    private StoreAdapter mAdapter;
    private int nowPage = 1;
    private String cmd = "getShopListInfo";
    private String serveType;
    private int sort;
    private List<String> filtrate;
    private String longitude;
    private String latitude;
    private List<StoreBean.shop> mList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frgment_store,container,false);
        mList = new ArrayList<>();
        initView();
        return view;
    }

    private void initView() {
        mPosition = (LinearLayout) view.findViewById(R.id.linear_store_list_position);
        mPosition.setOnClickListener(this);
        mTypes = (LinearLayout) view.findViewById(R.id.linear_store_list_types);
        mTypes.setOnClickListener(this);
        mDefaultSort = (LinearLayout) view.findViewById(R.id.linear_store_list_default_sort);
        mDefaultSort.setOnClickListener(this);
        tvPosition = (TextView) view.findViewById(R.id.text_position);
        tvTypes = (TextView) view.findViewById(R.id.text_position);
        tvDefaultSort = (TextView) view.findViewById(R.id.text_position);
        mDown01 = (ImageView) view.findViewById(R.id.iv_store_tag_down01);
        mDown02 = (ImageView) view.findViewById(R.id.iv_store_tag_down02);
        mDown03 = (ImageView) view.findViewById(R.id.iv_store_tag_down03);
        mUp01 = (ImageView) view.findViewById(R.id.iv_store_tag_up01);
        mUp02 = (ImageView) view.findViewById(R.id.iv_store_tag_up02);
        mUp03 = (ImageView) view.findViewById(R.id.iv_store_tag_up03);
        store_class = (PullToRefreshListView) view.findViewById(R.id.store_class);
        mAdapter = new StoreAdapter();
        store_class.setAdapter(mAdapter);
        store_class.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(context, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                nowPage = 1;
                mList.clear();
                getdata();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                nowPage++;
                getdata();
            }
        });
        store_class.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                ToastUtils.showMessageShort(context,"已经到底了");

            }
        });
        //在刷新时允许继续滑动
        store_class.setScrollingWhileRefreshingEnabled(true);
        store_class.setMode(PullToRefreshBase.Mode.BOTH);

        store_class.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),StoreDecActivity.class);
                intent.putExtra("rotateid",mList.get(position-1).getShopid());
                intent.putExtra("rotateIcon",mList.get(position-1).getShopIcon());
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_store_list_position:
                break;
            case R.id.linear_store_list_types:
                break;
            case R.id.linear_store_list_default_sort:
                break;

        }
    }
    private void getdata() {
        Map<String, String> params = new HashMap<>();
        StoreBean storeBean = new StoreBean(cmd,serveType,sort,filtrate,nowPage,longitude,latitude);
        String json = new Gson().toJson(storeBean);
        params.put("json", json);
        Log.i("ShopDecActivity", "getdata: " + json);
        dialog.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showMessageShort(context, e.getMessage());
                dialog.dismiss();
            }
            @Override
            public void onResponse(String response, int id) {
                Log.i("ShopDecActivity", "onResponse: " + response.toString());
                Gson gson = new Gson();
                dialog.dismiss();
                StoreBean storeBean = gson.fromJson(response, StoreBean.class);
                if (storeBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, storeBean.resultNote);
                    return;
                }
                List<StoreBean.shop> shoplist = storeBean.shop;
                mList.addAll(shoplist);
                mAdapter.setStoreList(context,mList);
                store_class.setAdapter(mAdapter);
                store_class.onRefreshComplete();
            }
        });
    }
}
