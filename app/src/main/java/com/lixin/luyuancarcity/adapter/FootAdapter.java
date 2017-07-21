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
import com.lixin.luyuancarcity.bean.FootBean;
import com.lixin.luyuancarcity.utils.ImageManager;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/18
 * My mailbox is 1403241630@qq.com
 */

public class FootAdapter extends BaseAdapter{
    private Context context;
    private List<FootBean.footPrintList> mList;
    public void setMyFoot(Context context, List<FootBean.footPrintList> mList) {
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
        final FootViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_foot,null);
            viewHolder = new FootViewHolder();
            viewHolder.mPicture = (ImageView) convertView.findViewById(R.id.iv_my_foot_shop_picture);
            viewHolder.mTitle = (TextView) convertView.findViewById(R.id.text_my_foot_shop_name);
            viewHolder.mPrice = (TextView) convertView.findViewById(R.id.text_my_foot_shop_price);
            viewHolder.mTime = (TextView) convertView.findViewById(R.id.text_my_foot_time);
            viewHolder.mDelete = (ImageView) convertView.findViewById(R.id.iv_my_foot_delete);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (FootViewHolder) convertView.getTag();
        }
        FootBean.footPrintList footPrintList = mList.get(position);
        viewHolder.mTitle.setText(footPrintList.getCommodityTitle());
        viewHolder.mPrice.setText(footPrintList.getCommodityNewPrice());
        viewHolder.mTime.setText(footPrintList.getCommodityTime());
        String img = footPrintList.getCommodityIcon();
        if (TextUtils.isEmpty(img)) viewHolder.mPicture.setImageResource(R.drawable.image_fail_empty);
        else ImageManager.imageLoader.displayImage(img,viewHolder.mPicture,ImageManager.options3);
        return convertView;
    }

    class FootViewHolder{
        ImageView mPicture,mDelete;
        TextView mTitle,mPrice,mTime;
    }
}
