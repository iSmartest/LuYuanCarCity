package com.lixin.luyuancarcity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.ForumAdapter;
import com.lixin.luyuancarcity.bean.ForumBean;
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
 * Create time on  2017/7/15
 * My mailbox is 1403241630@qq.com
 */

public class PageFragment extends BaseFragment {
    private View view;
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private PullToRefreshListView forum_list;
    private ForumAdapter mAdapter;
    private String cmd = "getFateCommentList";
    private int nowPage = 1;
    private int forumMenuid ;
    private List<ForumBean.fateList> mList;
    private String uid;
    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_page, container, false);
        mList = new ArrayList<>();
        uid = (String) SPUtils.get(getActivity(),"uid","");
        initView();
        return view;
    }

    private void initView() {
        forum_list = (PullToRefreshListView) view.findViewById(R.id.forum_list);
        mAdapter = new ForumAdapter();
        forum_list.setAdapter(mAdapter);
        forum_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
//                nowPage = 1;
//                mList.clear();
                getdata();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                nowPage ++;
                getdata();
            }
        });

        forum_list.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                ToastUtils.showMessageShort(getActivity(),"已经到底了");
            }
        });
        //在刷新时允许继续滑动
        forum_list.setScrollingWhileRefreshingEnabled(true);
        forum_list.setMode(PullToRefreshBase.Mode.BOTH);
        forum_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                startActivity(new Intent(getActivity(),StoreDecActivity.class));
            }
        });
    }

    private void getdata() {
        Map<String, String> params = new HashMap<>();
        ForumBean forumBean = new ForumBean(cmd,forumMenuid,nowPage,uid);
        String json = new Gson().toJson(forumBean);
        params.put("json", json);
        Log.i("MyShareActivity", "onResponse: " + json.toString());
        dialog.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showMessageShort(context, e.getMessage());
                dialog.dismiss();
                forum_list.onRefreshComplete();
            }
            @Override
            public void onResponse(String response, int id) {
                Log.i("MyShareActivity", "onResponse: " + response.toString());
                Gson gson = new Gson();
                dialog.dismiss();
                forum_list.onRefreshComplete();
                ForumBean forumBean = gson.fromJson(response, ForumBean.class);
                if (forumBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, forumBean.resultNote);
                    return;
                }
                if (forumBean.getTotalPage() < nowPage) {
                    ToastUtils.showMessageShort(context, "没有更多了");
                    return;
                }
                List<ForumBean.fateList> securitiesList = forumBean.fateList;
                mList.addAll(securitiesList);
                mAdapter.setForum(context,mList);
                forum_list.setAdapter(mAdapter);
                forum_list.onRefreshComplete();
            }
        });
    }

}
