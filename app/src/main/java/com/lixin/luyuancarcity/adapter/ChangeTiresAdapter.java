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
import com.lixin.luyuancarcity.bean.ChangeTiresBean;
import com.lixin.luyuancarcity.utils.ImageManager;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/21
 * My mailbox is 1403241630@qq.com
 */

public class ChangeTiresAdapter extends BaseAdapter{
    private Context context;
    private  List<ChangeTiresBean.tireList> mList;
    public void setChangeTires(Context context, List<ChangeTiresBean.tireList> mList) {
        this.context = context;
        this.mList = mList;
    }
    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public ChangeTiresBean.tireList getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ChangeTiresViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_change_tires,null);
            viewHolder = new ChangeTiresViewHolder();
            viewHolder.mPicture = (ImageView) convertView.findViewById(R.id.iv_change_tires_picture);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.tv_change_tires_name);
            viewHolder.mDec = (TextView) convertView.findViewById(R.id.tv_change_tires_dec);
            viewHolder.mSalesNum = (TextView) convertView.findViewById(R.id.tv_change_tires_sales_num);
            viewHolder.mOpinionNum = (TextView) convertView.findViewById(R.id.tv_change_tires_opinion_num);
            viewHolder.mPrice = (TextView) convertView.findViewById(R.id.tv_change_tires_price);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ChangeTiresViewHolder) convertView.getTag();
        }
        ChangeTiresBean.tireList tireList = mList.get(position);
        viewHolder.mName.setText(tireList.getCommodityTitle());
        viewHolder.mDec.setText(tireList.getCommodityDescription());
        viewHolder.mSalesNum.setText("已售" + tireList.getCommoditysellerNum() + "件");
        viewHolder.mOpinionNum.setText(tireList.getCommodityCommendNum() + "人评论");
        viewHolder.mPrice.setText(tireList.getCommodityNewPrice());
        String img = tireList.getCommodityIcon();
        if (TextUtils.isEmpty(img)){
            viewHolder.mPicture.setImageResource(R.drawable.image_fail_empty);
        }else {
            ImageManager.imageLoader.displayImage(img,viewHolder.mPicture,ImageManager.options3);
        }
        return convertView;
    }
    class ChangeTiresViewHolder {
        ImageView mPicture;
        TextView mName,mDec,mSalesNum,mOpinionNum,mPrice;
    }
}
