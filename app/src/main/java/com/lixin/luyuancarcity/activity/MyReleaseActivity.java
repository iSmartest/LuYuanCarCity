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
import com.lixin.luyuancarcity.adapter.MyReleaseAdapter;
import com.lixin.luyuancarcity.bean.ForumBean;
import com.lixin.luyuancarcity.bean.MyReleaseBean;
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

public class MyReleaseActivity extends BaseActivity {
    private MyReleaseAdapter mAdapter;
    private PullToRefreshListView my_release_list;
    private String cmd = "getMyPublic";
    private String uid;
    private int nowPage = 1;
    private List<ForumBean.fateList> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_release);
        uid = (String) SPUtils.get(context,"uid","");
        mList = new ArrayList<>();
        hideBack(5);
        setTitleText("我的发布",false);
        initView();
    }

    private void initView() {

        my_release_list = (PullToRefreshListView) findViewById(R.id.my_release_list);
        mAdapter = new MyReleaseAdapter();
        my_release_list.setAdapter(mAdapter);
        my_release_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(MyReleaseActivity.this, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
//                mList.clear();
//                getdata();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                mList.clear();
                getdata();

            }
        });
        my_release_list.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                ToastUtils.showMessageShort(MyReleaseActivity.this,"已经到底了");

            }
        });
        //在刷新时允许继续滑动
        my_release_list.setScrollingWhileRefreshingEnabled(true);
        my_release_list.setMode(PullToRefreshBase.Mode.BOTH);
        my_release_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void getdata() {
        Map<String,String> params = new HashMap<>();
        ForumBean forumBean = new ForumBean(cmd,uid,nowPage);
        String json = new Gson().toJson(forumBean);
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
                ForumBean forumBean = gson.fromJson(response,ForumBean.class);
                if (forumBean.result.equals("1")){
                    ToastUtils.showMessageShort(context,forumBean.resultNote);
                }
                List<ForumBean.fateList> maintainList = forumBean.fateList;
                mList.addAll(maintainList);
                mAdapter.setMyRelease(context,mList);
                my_release_list.setAdapter(mAdapter);
                my_release_list.onRefreshComplete();
            }
        });
    }
}
