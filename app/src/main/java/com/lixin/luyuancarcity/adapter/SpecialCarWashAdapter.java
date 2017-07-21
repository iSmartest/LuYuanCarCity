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
import com.lixin.luyuancarcity.bean.SpecialCarWashBean;
import com.lixin.luyuancarcity.utils.ImageManager;

import java.util.List;

import static com.lixin.luyuancarcity.R.id.text_wash_car_store_name;

/**
 * Created by 小火
 * Create time on  2017/7/10
 * My mailbox is 1403241630@qq.com
 */

public class SpecialCarWashAdapter extends BaseAdapter{
    private Context context;
    private List<SpecialCarWashBean.clearInfo> mList;
    public void setSpecialCarWash(Context context, List<SpecialCarWashBean.clearInfo> mList) {
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
        final SpecialCarWashViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_special_car_wash,null);
            viewHolder = new SpecialCarWashViewHolder();
            viewHolder.mPicture = (ImageView) convertView.findViewById(R.id.iv_wash_car_store_picture);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.text_wash_car_store_name);
            viewHolder.mEvaluate = (TextView) convertView.findViewById(R.id.text_wash_car_store_evaluate);
            viewHolder.mOrder = (TextView) convertView.findViewById(R.id.text_wash_car_store_order);
            viewHolder.mNowPrice = (TextView) convertView.findViewById(R.id.text_wash_car_store_now_price);
            viewHolder.mOriginPrice = (TextView) convertView.findViewById(R.id.text_wash_car_store_origin_price);
            viewHolder.mLocation = (TextView) convertView.findViewById(R.id.text_wash_car_store_location);
            viewHolder.mDistance = (TextView) convertView.findViewById(R.id.text_wash_car_store_distance);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (SpecialCarWashViewHolder) convertView.getTag();
        }
        SpecialCarWashBean.clearInfo clearInfoList = mList.get(position);
        viewHolder.mName.setText(clearInfoList.getShopName());
        viewHolder.mEvaluate.setText(clearInfoList.getShopCommentNum());
        viewHolder.mOrder.setText(clearInfoList.getSellerNum());
        viewHolder.mNowPrice.setText(clearInfoList.getClearCarNowPrice());
        viewHolder.mOriginPrice.setText(clearInfoList.getClearCarOriginPrice());
        viewHolder.mLocation.setText(clearInfoList.getShopLocaltion());
        viewHolder.mDistance.setText(clearInfoList.getDistance());
        String img = clearInfoList.getShopIcon();
        if (TextUtils.isEmpty(img)){
            viewHolder.mPicture.setImageResource(R.drawable.image_fail_empty);
        }else {
            ImageManager.imageLoader.displayImage(img,viewHolder.mPicture,ImageManager.options3);
        }
        return convertView;
    }

    class SpecialCarWashViewHolder{
        ImageView mPicture;
        TextView mName,mEvaluate,mOrder,mNowPrice,mOriginPrice,mLocation,mDistance;
    }
}
