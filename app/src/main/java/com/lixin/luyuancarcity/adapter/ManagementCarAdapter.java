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
import com.lixin.luyuancarcity.bean.ManagementCarBean;
import com.lixin.luyuancarcity.utils.ImageManager;
import com.lixin.luyuancarcity.view.RoundedImageView;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/19
 * My mailbox is 1403241630@qq.com
 */

public class ManagementCarAdapter extends BaseAdapter{
    private Context context;
    private List<ManagementCarBean.carManagerList> mList;
    public void setManagmentCar(Context context, List<ManagementCarBean.carManagerList> mList) {
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
        final ManagementCarViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_managment_car,null);
            viewHolder = new ManagementCarViewHolder();
            viewHolder.mTitle = (TextView) convertView.findViewById(R.id.text_management_car_name);
            viewHolder.mDec = (TextView) convertView.findViewById(R.id.text_management_car_dec);
            viewHolder.mDefault = (TextView) convertView.findViewById(R.id.text_management_car_default);
            viewHolder.mSetDefault = (TextView) convertView.findViewById(R.id.text_yes_or_no_set_default);
            viewHolder.mDelete = (TextView) convertView.findViewById(R.id.tv_delete);
            viewHolder.mPicture = (RoundedImageView) convertView.findViewById(R.id.management_car_image);
            convertView.setTag(viewHolder);
        }else viewHolder = (ManagementCarViewHolder) convertView.getTag();
        ManagementCarBean.carManagerList carManagerList = mList.get(position);
        viewHolder.mTitle.setText(carManagerList.getCarTitle());
        viewHolder.mDec.setText(carManagerList.getCarDes());
        String img = carManagerList.getCarImg();
        if (TextUtils.isEmpty(img)){
            viewHolder.mPicture.setImageResource(R.drawable.image_fail_empty);
        }else {
            ImageManager.imageLoader.displayImage(img,viewHolder.mPicture,ImageManager.options3);
        }
        return convertView;
    }
    class ManagementCarViewHolder{
        TextView mTitle,mDec,mDefault,mSetDefault,mDelete;
        RoundedImageView mPicture;
    }
}
