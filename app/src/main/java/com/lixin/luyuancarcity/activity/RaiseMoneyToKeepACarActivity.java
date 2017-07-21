package com.lixin.luyuancarcity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.RaiseMoneyToKeepACarAdapter;
import com.lixin.luyuancarcity.utils.ToastUtils;

import static com.lixin.luyuancarcity.R.id.wash_store_list;

/**
 * Created by 小火
 * Create time on  2017/7/14
 * My mailbox is 1403241630@qq.com
 */

public class RaiseMoneyToKeepACarActivity extends BaseActivity{
    private PullToRefreshListView investment_list;
    private RaiseMoneyToKeepACarAdapter mAdapter;
    private int nowPage = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_money_to_keep_a_car);
        hideBack(6);
        setTitleText("赚钱养车",false);
        setRelease("我的理财");
        initView();
    }

    private void initView() {
        investment_list = (PullToRefreshListView) findViewById(R.id.investment_list);
        mAdapter = new RaiseMoneyToKeepACarAdapter();
        investment_list.setAdapter(mAdapter);
        investment_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(RaiseMoneyToKeepACarActivity.this, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                nowPage = 1;
//                mList.clear();
//                getdata();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                nowPage ++;
//                getdata();
            }
        });

        investment_list.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                ToastUtils.showMessageShort(RaiseMoneyToKeepACarActivity.this, "已经到底了");
            }
        });
        //在刷新时允许继续滑动
        investment_list.setScrollingWhileRefreshingEnabled(true);
        investment_list.setMode(PullToRefreshBase.Mode.BOTH);
        investment_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_base_release:
                Toast.makeText(RaiseMoneyToKeepACarActivity.this,"跳转到我的理财",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
