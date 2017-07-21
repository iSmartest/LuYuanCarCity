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
import com.lixin.luyuancarcity.bean.AutoInsuranceTypeBean;
import com.lixin.luyuancarcity.utils.ImageManager;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/6/27
 * My mailbox is 1403241630@qq.com
 */

public class AutoInsuranceTypeAdapter extends BaseAdapter{
    private Context context;
    private List<AutoInsuranceTypeBean.emergencys> mList;
    public void setAutoInsuranceType(Context context, List<AutoInsuranceTypeBean.emergencys> mList) {
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
        final ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_auto_insurance_type,null);
            viewHolder = new ViewHolder();
            viewHolder.mPicture = (ImageView) convertView.findViewById(R.id.img_insurance_picture);
            viewHolder.mInsuranceType = (TextView) convertView.findViewById(R.id.text_insurance_type);
            viewHolder.mInsuranceDec = (TextView) convertView.findViewById(R.id.text_insurance_dec);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        AutoInsuranceTypeBean.emergencys emergencys = mList.get(position);
        viewHolder.mInsuranceDec.setText(emergencys.getEmergencyDetail());
        viewHolder.mInsuranceType.setText(emergencys.getEmergencyTitle());
        String img = emergencys.getEmergencyIcon();
        if (TextUtils.isEmpty(img)){
            viewHolder.mPicture.setImageResource(R.drawable.image_fail_empty);
        }else {
            ImageManager.imageLoader.displayImage(img,viewHolder.mPicture,ImageManager.options3);
        }
        return convertView;
    }

    class ViewHolder{
        ImageView mPicture;
        TextView mInsuranceType, mInsuranceDec;

    }
}
