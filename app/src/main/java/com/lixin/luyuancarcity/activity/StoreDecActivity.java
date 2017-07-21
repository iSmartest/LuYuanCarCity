package com.lixin.luyuancarcity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.GroupDiscountAdapter;
import com.lixin.luyuancarcity.adapter.GroupPurchaseAdapter;
import com.lixin.luyuancarcity.bean.OpinionBean;
import com.lixin.luyuancarcity.bean.WashCarStoreDecBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;
import com.lixin.luyuancarcity.view.ImageSlideshow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 小火
 * Create time on  2017/7/11
 * My mailbox is 1403241630@qq.com
 */

public class StoreDecActivity extends BaseActivity implements GroupDiscountAdapter.CheckInterface {
    private View headView;
    private PullToRefreshListView pullToRefreshListView;
    private ListView store_dec_list,group_discount_list;
    private GroupPurchaseAdapter mAdapter;
    private GroupDiscountAdapter groupDiscountAdapter;
    private ImageSlideshow mGallery;
    private ImageView mBack;
    private TextView mStoreName,mStoreOpenTime,mStoreAddress,mStoreEvaluate,mStoreOrder,headScore,headTotalOpinion;
    private LinearLayout mStoreDetails,mStoreNavigation,mStoreReceiveCoupons,mChangeTires,mDoMaintenance,mUserOpinion;
    private RatingBar headStar;
    private String shopid;
    private int nowPage = 1;
    private List<WashCarStoreDecBean.commoditys> mList;
    private List<OpinionBean.commodityCommentLists> mOpinionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash_car_store_dec);
        initView();
//        getdata();
//        getOpinionData();
    }



    private void initView() {
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.wash_car_store_dec_list);
        store_dec_list = (ListView) pullToRefreshListView.getRefreshableView();
        getHeadView();
        if (headView != null)
            store_dec_list.addHeaderView(headView);
        mAdapter = new GroupPurchaseAdapter();
        store_dec_list.setAdapter(mAdapter);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(StoreDecActivity.this, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                // 仅刷新评价的数据
//                mList.clear();
//                getdata();
//
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                mList.clear();
//                getdata();
//
            }
        });
        pullToRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                ToastUtils.showMessageShort(context,"已经到底了");
            }
        });

    }

    private void getHeadView() {
        headView = LayoutInflater.from(context).inflate(R.layout.include_wash_car_store_details,null);
        mGallery = (ImageSlideshow) headView.findViewById(R.id.img_store_gallery);
        mBack = (ImageView) headView.findViewById(R.id.iv_wash_car_store_dec_back);
        mBack.setOnClickListener(this);
        mStoreName = (TextView) headView.findViewById(R.id.text_wash_car_store_dec_name);
        mStoreOpenTime = (TextView) headView.findViewById(R.id.text_store_open_time);
        mStoreDetails = (LinearLayout) headView.findViewById(R.id.linear_wash_car_store_details);
        mStoreDetails.setOnClickListener(this);
        mStoreNavigation = (LinearLayout) headView.findViewById(R.id.linear_wash_car_store_navigation);
        mStoreNavigation.setOnClickListener(this);
        mStoreAddress = (TextView) headView.findViewById(R.id.text_wash_car_store_address);
        mStoreEvaluate = (TextView) headView.findViewById(R.id.text_wash_car_store_dec_evaluate);
        mStoreOrder = (TextView) headView.findViewById(R.id.text_wash_car_store_dec_order);
        mStoreReceiveCoupons = (LinearLayout) headView.findViewById(R.id.linear_wash_car_store_receive_coupons);
        mStoreReceiveCoupons.setOnClickListener(this);
        group_discount_list = (ListView) headView.findViewById(R.id.group_discount_list);
        mChangeTires = (LinearLayout) headView.findViewById(R.id.linear_wash_car_store_change_tires);
        mChangeTires.setOnClickListener(this);
        mDoMaintenance = (LinearLayout) headView.findViewById(R.id.linear_wash_car_store_do_maintenance);
        mDoMaintenance.setOnClickListener(this);
        mUserOpinion = (LinearLayout) headView.findViewById(R.id.linear_wash_car_store_user_opinion);
        mUserOpinion.setOnClickListener(this);
        headStar = (RatingBar) headView.findViewById(R.id.rab_head_star);
        headScore = (TextView) headView.findViewById(R.id.text_head_score);
        headTotalOpinion = (TextView) headView.findViewById(R.id.text_head_total_opinion);
        groupDiscountAdapter = new GroupDiscountAdapter();
        groupDiscountAdapter.setCheckInterface(this);
        group_discount_list.setAdapter(groupDiscountAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_wash_car_store_dec_back:
                finish();
                break;
            case R.id.linear_wash_car_store_details:
                //跳转到洗车店详情页面（WebView）
                break;
            case R.id.linear_wash_car_store_navigation:
                //跳转到导航页面
                break;
            case R.id.linear_wash_car_store_receive_coupons:
                //跳转到获取优惠券页面
                startActivity(new Intent(StoreDecActivity.this,MyCouponActivity.class));
                break;
            case R.id.linear_wash_car_store_change_tires:
                //跳转到换轮胎页面

                break;
            case R.id.linear_wash_car_store_do_maintenance:
                //跳转到做保养页面
                break;
            case R.id.linear_wash_car_store_user_opinion:
                //跳转到用户评价页面
                break;
        }

    }

    private void getdata() {
        Map<String, String> params = new HashMap<>();
        WashCarStoreDecBean washCarStoreDecBean = new WashCarStoreDecBean("getShopDetailInfo",shopid);
        String json = new Gson().toJson(washCarStoreDecBean);
        params.put("json", json);
        Log.i("ShopDecActivity", "getdata: " + json);
        dialog1.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showMessageShort(context, e.getMessage());
                dialog1.dismiss();
            }
            @Override
            public void onResponse(String response, int id) {
                Log.i("ShopDecActivity", "onResponse: " + response.toString());
                Gson gson = new Gson();
                dialog1.dismiss();
                WashCarStoreDecBean washCarStoreDecBean = gson.fromJson(response, WashCarStoreDecBean.class);
                if (washCarStoreDecBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, washCarStoreDecBean.resultNote);
                    return;
                }
                List<WashCarStoreDecBean.commoditys> commodityslist = washCarStoreDecBean.commoditys;
                mList.addAll(commodityslist);
                groupDiscountAdapter.setShopBeanList(StoreDecActivity.this,mList);
                group_discount_list.setAdapter(groupDiscountAdapter);
                pullToRefreshListView.onRefreshComplete();
            }
        });
    }
    private void getOpinionData() {
        Map<String, String> params = new HashMap<>();
        OpinionBean opinionBean = new OpinionBean("getCommoditysCommentsInfo",shopid,nowPage);
        String json = new Gson().toJson(opinionBean);
        params.put("json", json);
        Log.i("ShopDecActivity", "getdata: " + json);
        dialog1.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showMessageShort(context, e.getMessage());
                dialog1.dismiss();
            }
            @Override
            public void onResponse(String response, int id) {
                Log.i("ShopDecActivity", "onResponse: " + response.toString());
                Gson gson = new Gson();
                dialog1.dismiss();
                OpinionBean opinionBean = gson.fromJson(response, OpinionBean.class);
                if (opinionBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, opinionBean.resultNote);
                    return;
                }
                List<OpinionBean.commodityCommentLists> commodityCommentLists = opinionBean.commodityCommentLists;
                mOpinionList.addAll(commodityCommentLists);
                mAdapter.setOpinionList(StoreDecActivity.this,mOpinionList);
                store_dec_list.setAdapter(mAdapter);
                pullToRefreshListView.onRefreshComplete();
            }
        });
    }
    @Override
    public void checkGroup(int position, boolean isChecked) {
        mList.get(position).setChoosed(isChecked);
        groupDiscountAdapter.notifyDataSetChanged();
    }
}
