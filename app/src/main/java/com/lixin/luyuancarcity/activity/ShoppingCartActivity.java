package com.lixin.luyuancarcity.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.ShopCartAdapter;
import com.lixin.luyuancarcity.adapter.ShopingCartBean;
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

public class ShoppingCartActivity extends BaseActivity implements ShopCartAdapter.CheckInterface,ShopCartAdapter.ModifyCountInterface{
    private PullToRefreshListView shop_cart_list;
    private CheckBox mAllChoose;
    private TextView mTotalPrice;
    private TextView mSure;
    private ShopCartAdapter shopCartAdapter;
    private int nowPage = 1;
    private String cmd = "getShoppingCart";
    private String uid;
    private List<ShopingCartBean.commoditys> mList;
    private int totalPrice = 0;// 购买的商品总价
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        uid = (String) SPUtils.get(context,"uid","");
        mList = new ArrayList<>();
        hideBack(5);
        setTitleText("购物车",false);
        initView();
    }

    private void initView() {
        mAllChoose = (CheckBox) findViewById(R.id.ck_fragment_shop_all_chose);
        mTotalPrice = (TextView) findViewById(R.id.text_fragment_shop_total_price);
        mSure = (TextView) findViewById(R.id.text_shop_car_sure);
        mSure.setOnClickListener(this);
        shop_cart_list = (PullToRefreshListView) findViewById(R.id.shop_cart_list);
        shopCartAdapter = new ShopCartAdapter();
        shopCartAdapter.setCheckInterface(this);
        shopCartAdapter.setModifyCountInterface(this);
        shop_cart_list.setAdapter(shopCartAdapter);
        shop_cart_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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
        shop_cart_list.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                ToastUtils.showMessageShort(context, "已经到底了");

            }
        });
        //在刷新时允许继续滑动
        shop_cart_list.setScrollingWhileRefreshingEnabled(true);
        shop_cart_list.setMode(PullToRefreshBase.Mode.BOTH);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_shop_car_sure:
//                List<ShopingCartBean.commoditys> list = new ArrayList<>();
//                for (int i = 0; i < mList.size() ; i++) {
//                    ShopingCartBean.commoditys shopList = mList.get(i);
//                    if (shopList.isChoosed){
//                        GenerateOrderBean.commoditys comm = new GenerateOrderBean.commoditys(shopList.getCommodityid(),
//                                shopList.getCommodityTitle(),shopList.getCommodityIcon()
//                                ,shopList.getCommodityFirstParam(),shopList.getCommoditySecondParam(),shopList.getCommodityNewPrice(),shopList.getCommodityShooCarNum());
//                        list.add(comm);
//                    }
//                }
//                if (!list.isEmpty()){
//                    getOrderData(list);
//                }else {
//                    ToastUtils.showMessageLong(context,"未选中任何商品!");
//                }
                break;
        }
    }

    private void getdata() {
        Map<String,String> params = new HashMap<>();
        ShopingCartBean shopingCartBean = new ShopingCartBean(cmd,uid,nowPage);
        String json = new Gson().toJson(shopingCartBean);
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
                ShopingCartBean shopingCartBean = gson.fromJson(response,ShopingCartBean.class);
                if (shopingCartBean.result.equals("1")){
                    ToastUtils.showMessageShort(context,shopingCartBean.resultNote);
                }
                List<ShopingCartBean.commoditys> maintainList = shopingCartBean.commoditys;
                mList.addAll(maintainList);
                shopCartAdapter.setShopCart(context,mList);
                shop_cart_list.setAdapter(shopCartAdapter);
                shop_cart_list.onRefreshComplete();
            }
        });
    }
    public void statistics() {
        totalPrice = 0;
        for (int i = 0; i < mList.size(); i++) {
            ShopingCartBean.commoditys commoditys = mList.get(i);
            if (commoditys.isChoosed()) {
                totalPrice += Integer.parseInt(mList.get(i).commodityNewPrice) * Integer.parseInt(mList.get(i).getCommodityShooCarNum());
            }
        }
        mTotalPrice.setText(totalPrice + "");
    }
    /**
     * 遍历list集合
     *
     * @return
     */
    private boolean isAllCheck() {
        for (ShopingCartBean.commoditys group : mList) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }
    @Override
    public void checkGroup(int position, boolean isChecked) {
        mList.get(position).setChoosed(isChecked);
        if (isAllCheck())
            mAllChoose.setChecked(true);
        else
            mAllChoose.setChecked(false);
        shopCartAdapter.notifyDataSetChanged();
        statistics();
    }

    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
        ShopingCartBean.commoditys commoditys = mList.get(position);
        int currentCount = Integer.parseInt(commoditys.getCommodityShooCarNum());
        Log.i("currentCount", "doIncrease: " + currentCount);
        currentCount++;
        String num = String.valueOf(currentCount);
        commoditys.setCommodityShooCarNum(num);
        ((TextView) showCountView).setText(num);
        shopCartAdapter.notifyDataSetChanged();
        statistics();
    }

    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
        ShopingCartBean.commoditys commoditys = mList.get(position);
        int currentCount = Integer.parseInt(commoditys.getCommodityShooCarNum());
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        String num = String.valueOf(currentCount);
        commoditys.setCommodityShooCarNum(num);
        ((TextView) showCountView).setText(num);
        shopCartAdapter.notifyDataSetChanged();
        statistics();
    }

    @Override
    public void childDelete(int position) {

    }
}
