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
import com.lixin.luyuancarcity.adapter.MaintenanceAdapter;
import com.lixin.luyuancarcity.bean.MaintenanceBean;
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
 * Create time on  2017/7/13
 * My mailbox is 1403241630@qq.com
 */

public class MaintenanceActivity extends BaseActivity{
    private PullToRefreshListView maintenance_list;
    private String title;
    private MaintenanceAdapter mAdapter;
    private String uid;
    private List<MaintenanceBean.maintainList> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);
        title = (String) SPUtils.get(MaintenanceActivity.this,"myCar","");
        uid = (String) SPUtils.get(MaintenanceActivity.this,"uid","");
        mList = new ArrayList<>();
        hideBack(5);
        setTitleText(title,false);
        initView();
    }

    private void initView() {
        maintenance_list = (PullToRefreshListView) findViewById(R.id.maintenance_list);
        mAdapter = new MaintenanceAdapter();
        maintenance_list.setAdapter(mAdapter);
        maintenance_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(MaintenanceActivity.this, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
//                mList.clear();
//                getdata();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                mList.clear();
//                getdata();

            }
        });
        maintenance_list.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                ToastUtils.showMessageShort(MaintenanceActivity.this,"已经到底了");

            }
        });
        //在刷新时允许继续滑动
        maintenance_list.setScrollingWhileRefreshingEnabled(true);
        maintenance_list.setMode(PullToRefreshBase.Mode.BOTH);
        maintenance_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void getdata() {
        Map<String,String> params = new HashMap<>();
        MaintenanceBean maintenanceBean = new MaintenanceBean("getMaintenance",uid);
        String json = new Gson().toJson(maintenanceBean);
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
                MaintenanceBean maintenanceBean = gson.fromJson(response,MaintenanceBean.class);
                if (maintenanceBean.result.equals("1")){
                    ToastUtils.showMessageShort(context,maintenanceBean.resultNote);
                }
                List<MaintenanceBean.maintainList> maintainList = maintenanceBean.maintainList;
                mList.addAll(maintainList);
                mAdapter.setMaintenance(context,mList);
                maintenance_list.setAdapter(mAdapter);
                maintenance_list.onRefreshComplete();
            }
        });
    }
}
