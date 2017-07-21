package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.MyOrderBean;
import com.lixin.luyuancarcity.utils.ImageManager;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/18
 * My mailbox is 1403241630@qq.com
 */

public class OrderlistAdapter extends BaseAdapter {
    private Context context;
    private List<MyOrderBean.orders.orderCommodity> orderCommodity;
    public OrderlistAdapter(List<MyOrderBean.orders.orderCommodity> orderCommodity, Context context) {
        this.context = context;
        this.orderCommodity = orderCommodity;
    }
    @Override
    public int getCount() {
        return orderCommodity == null ? 0 : orderCommodity.size();
    }
    @Override
    public Object getItem(int position) {
        return orderCommodity.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order_content,null);
            viewHolder = new ViewHolder();
            viewHolder.comm_title = (TextView) convertView.findViewById(R.id.comm_title);
            viewHolder.comm_price = (TextView) convertView.findViewById(R.id.comm_price);
            viewHolder.comm_number = (TextView) convertView.findViewById(R.id.comm_number);
            viewHolder.comm_total_price = (TextView) convertView.findViewById(R.id.comm_total_price);
            viewHolder.comm_icon = (ImageView) convertView.findViewById(R.id.comm_icon);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MyOrderBean.orders.orderCommodity mList = orderCommodity.get(position);
        viewHolder.comm_title.setText(mList.getCommodityTitle());

        viewHolder.comm_price.setText("￥" + mList.getCommodityNewPrice());
        viewHolder.comm_number.setText("×" + mList.getCommodityBuyNum());
//        viewHolder.comm_total_price.setText(mList.getCommodityDescription());
        String url = mList.getCommodityIcon();
        if (TextUtils.isEmpty(url)){
            viewHolder.comm_icon.setImageResource(R.drawable.image_fail_empty);
        }else{
            ImageManager.imageLoader.displayImage(url,viewHolder.comm_icon,ImageManager.options3);
        }
        return convertView;
    }
    private class ViewHolder{
        TextView comm_title;
        TextView comm_price;
        TextView comm_number;
        TextView comm_total_price;
        ImageView comm_icon;
    }
}

