package com.lixin.luyuancarcity.activity;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.ChangeTiresAdapter;
import com.lixin.luyuancarcity.bean.ChangeTiresBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.popupwindow.FilterPopupWindow;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 小火
 * Create time on  2017/7/21
 * My mailbox is 1403241630@qq.com
 */

public class ChangeTiresActivity extends BaseActivity{
    private String title;
    private CheckBox mChooseTop;
    private LinearLayout mSort,mScreen;
    private PullToRefreshListView change_tires_list;
    private View main;
    private ChangeTiresAdapter mAdapter;
    private FilterPopupWindow popupWindow;
    private String cmd = "changeTire";
    private int nowPage = 1;
    private List<ChangeTiresBean.tireList> mList;
    private String carId;
    public int sort;
    private int isTop;
    private int explosionproof;
    private int snowfield;
    private List<String> tireBrand;
    private List<ChangeTiresBean.price> price;
    private List <ChangeTiresBean.tireSize> tireSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_tires);
        mList = new ArrayList<>();
        hideBack(6);
        setTitleText(title,false);
        setRelease("切换规格");
        initView();
    }

    private void initView() {
        main = findViewById(R.id.main);
        mSort = (LinearLayout) findViewById(R.id.linear_change_tires_sort);
        mSort.setOnClickListener(this);
        mScreen = (LinearLayout) findViewById(R.id.linear_change_tires_screen);
        mScreen.setOnClickListener(this);
        mChooseTop = (CheckBox) findViewById(R.id.ck_choose_top);
        change_tires_list = (PullToRefreshListView) findViewById(R.id.change_tires_list);
        mAdapter = new ChangeTiresAdapter();

        change_tires_list.setAdapter(mAdapter);
        change_tires_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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
        change_tires_list.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                ToastUtils.showMessageShort(context, "已经到底了");

            }
        });
        //在刷新时允许继续滑动
        change_tires_list.setScrollingWhileRefreshingEnabled(true);
        change_tires_list.setMode(PullToRefreshBase.Mode.BOTH);
    }

    private void getdata() {
        Map<String,String> params = new HashMap<>();
        ChangeTiresBean changeTiresBean = new ChangeTiresBean(cmd,nowPage,carId,sort,isTop,explosionproof,
                snowfield,tireBrand,price,tireSize);
        String json = new Gson().toJson(changeTiresBean);
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
                ChangeTiresBean tiresBean = gson.fromJson(response,ChangeTiresBean.class);
                if (tiresBean.getResult().equals("1")){
                    ToastUtils.showMessageShort(context,tiresBean.getResultNote());
                }
                List<ChangeTiresBean.tireList> tireList = tiresBean.tireList;
                mList.addAll(tireList);
                mAdapter.setChangeTires(context,mList);
                change_tires_list.setAdapter(mAdapter);
                change_tires_list.onRefreshComplete();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_base_release:
                ToastUtils.showMessageShort(context,"跳转到切换规格");
                break;
            case R.id.linear_change_tires_screen:
                popupWindow = new FilterPopupWindow(ChangeTiresActivity.this);
                popupWindow.showFilterPopup(main);
                break;
        }
    }
}
