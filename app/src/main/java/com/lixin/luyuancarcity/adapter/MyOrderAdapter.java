package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.MyOrderBean;
import com.lixin.luyuancarcity.utils.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/18
 * My mailbox is 1403241630@qq.com
 */

public class MyOrderAdapter extends BaseAdapter{
    private Context context;
    private List<MyOrderBean.orders> mList;
    private String uid;
    private String mMoney;
    private int nowPage = 1;
    private String orderState;
    private double freight;
    private double shopFreight;
    public void setMyOrder(Context context, List<MyOrderBean.orders> mList, String uid,String orderState) {
        this.context = context;
        this.mList = mList;
        this.uid = uid;
        this.orderState = orderState;
    }
    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_my_order_list,null);
            viewHolder = new ViewHolder();
            viewHolder.order_number = (TextView) convertView.findViewById(R.id.order_number);
            viewHolder.order_state = (TextView) convertView.findViewById(R.id.order_state);
            viewHolder.total_price = (TextView) convertView.findViewById(R.id.total_price);
            viewHolder.order_totl = (TextView) convertView.findViewById(R.id.order_totl);
            viewHolder.commodity_lv = (ListView) convertView.findViewById(R.id.commodity_lv);
            viewHolder.bt_pay = (Button) convertView.findViewById(R.id.bt_pay);
            viewHolder.bt_delete = (Button) convertView.findViewById(R.id.bt_delete);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final MyOrderBean.orders ordersList = mList.get(position);
        viewHolder.order_number.setText(ordersList.getOrderId());
        viewHolder.total_price.setText(ordersList.getOderTotalPrice());
        OrderlistAdapter commodityAdapter = new OrderlistAdapter(ordersList.getOrderCommodity(),context);
        viewHolder.commodity_lv.setAdapter(commodityAdapter);
        Utility.setListViewHeightBasedOnChildren(viewHolder.commodity_lv);
        switch (orderState){
            case "1":
                viewHolder.order_state.setText("待付款");
                //删除订单
                viewHolder.bt_delete.setVisibility(View.VISIBLE);
                viewHolder.bt_pay.setText("去付款");
                viewHolder.bt_delete.setText("取消订单");
                viewHolder.total_price.setVisibility(View.GONE);
                viewHolder.order_totl.setVisibility(View.GONE);
                viewHolder.bt_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int type = 0;

                    }
                });
                viewHolder.bt_pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            case "2":
                viewHolder.order_state.setText("待发货");
                //确认收货
                viewHolder.bt_pay.setText("我要退款");
                viewHolder.bt_pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                viewHolder.commodity_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
                break;
            case "3":
                viewHolder.order_state.setText("待收货");
                //确认收货
                viewHolder.bt_pay.setText("确认订单");
                viewHolder.bt_pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                viewHolder.commodity_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
                break;
            case "4":
                viewHolder.order_state.setText("已完成");
                //确认收货
                viewHolder.bt_pay.setText("删除订单");
                viewHolder.bt_pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int type = 1;

                    }
                });
                viewHolder.commodity_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
                break;
            case "5":
                viewHolder.order_state.setText(ordersList.getOrderState());
                //确认收货
                viewHolder.bt_pay.setText("查看退款详情");
                viewHolder.bt_pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
        }
        return convertView;
    }

    class ViewHolder{
        TextView order_number;
        TextView order_state;
        TextView total_price;
        TextView order_totl;
        ListView commodity_lv;
        Button bt_pay;
        Button bt_delete;
    }
}
