package com.lixin.luyuancarcity.activity;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.FootAdapter;
import com.lixin.luyuancarcity.bean.FootBean;
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
 * Create time on  2017/7/17
 * My mailbox is 1403241630@qq.com
 */

public class MyFootActivity extends BaseActivity {
    PullToRefreshListView my_foot_list;
    private FootAdapter mAdapter;
    private String cmd = "footPrint";
    private String uid;
    private int nowPage = 1;
    private List<FootBean.footPrintList> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_foot);
        uid = (String) SPUtils.get(context,"uid","");
        mList = new ArrayList<>();
        hideBack(5);
        setTitleText("我的足迹",false);
        initView();
    }

    private void initView() {
        my_foot_list = (PullToRefreshListView) findViewById(R.id.my_foot_list);
        mAdapter = new FootAdapter();
        my_foot_list.setAdapter(mAdapter);
        my_foot_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(context, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
//                mList.clear();
                getdata();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                mList.clear();
                getdata();
            }
        });
        my_foot_list.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                ToastUtils.showMessageShort(context,"已经到底了");
            }
        });
        //在刷新时允许继续滑动
        my_foot_list.setScrollingWhileRefreshingEnabled(true);
        my_foot_list.setMode(PullToRefreshBase.Mode.BOTH);
        my_foot_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void getdata() {
        Map<String,String> params = new HashMap<>();
        FootBean footBean = new FootBean(cmd,uid,nowPage);
        String json = new Gson().toJson(footBean);
        params.put("json",json);
        dialog1.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dialog1.dismiss();
                ToastUtils.showMessageShort(context,e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                dialog1.dismiss();
                FootBean footBean = gson.fromJson(response, FootBean.class);
                if (footBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, footBean.resultNote);
                    return;
                }
                List<FootBean.footPrintList> financialList = footBean.footPrintList;
                mList.addAll(financialList);
                mAdapter.setMyFoot(context,mList);
                my_foot_list.setAdapter(mAdapter);
            }
        });
    }
}
