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
import com.lixin.luyuancarcity.bean.StoreBean;
import com.lixin.luyuancarcity.utils.ImageManager;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/14
 * My mailbox is 1403241630@qq.com
 */

public class StoreAdapter extends BaseAdapter{
    private Context context;
    private List<StoreBean.shop> mList;
    public void setStoreList(Context context, List<StoreBean.shop> mList) {
        this.context = context;
        this.mList = mList;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final StoreViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_store,null);
            viewHolder = new StoreViewHolder();
            viewHolder.mPicture = (ImageView) convertView.findViewById(R.id.iv_store_car_picture);
            viewHolder.mTitle = (TextView) convertView.findViewById(R.id.text_store_name);
            viewHolder.mScore = (TextView) convertView.findViewById(R.id.text_store_score);
            viewHolder.mSalesNum = (TextView) convertView.findViewById(R.id.text_store_sales_num);
            viewHolder.mAddress = (TextView) convertView.findViewById(R.id.text_store_address);
            viewHolder.mDistance = (TextView) convertView.findViewById(R.id.text_store_distance);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (StoreViewHolder) convertView.getTag();
        }
        StoreBean.shop shopList = mList.get(position);
        viewHolder.mTitle.setText(shopList.getShopName());
        viewHolder.mScore.setText(shopList.getShopStar());
        viewHolder.mSalesNum.setText(shopList.getSellerNum());
        viewHolder.mAddress.setText(shopList.getShopLocaltion());
        viewHolder.mDistance.setText(shopList.getDistance());
        String img = shopList.getShopIcon();
        if (TextUtils.isEmpty(img)){
            viewHolder.mPicture.setImageResource(R.drawable.image_fail_empty);
        }else {
            ImageManager.imageLoader.displayImage(img,viewHolder.mPicture,ImageManager.options3);
        }
        return convertView;
    }
    class StoreViewHolder{
        ImageView mPicture;
        TextView mTitle,mScore,mSalesNum,mAddress,mDistance;

    }

}
