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
import com.lixin.luyuancarcity.bean.InsuranceCompanyResultBean;
import com.lixin.luyuancarcity.utils.ImageManager;

import java.util.ArrayList;

/**
 * Created by 小火
 * Create time on  2017/6/27
 * My mailbox is 1403241630@qq.com
 */

public class AutomobileInsuranceAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<? extends InsuranceCompanyResultBean.insuranceCompanyList> mList;
    public AutomobileInsuranceAdapter(Context context, ArrayList<? extends InsuranceCompanyResultBean.insuranceCompanyList> mList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_auto_insurance,null);
            viewHolder = new ViewHolder();
            viewHolder.mCompanyLogo = (ImageView) convertView.findViewById(R.id.img_company_logo);
            viewHolder.mCompanyName = (TextView) convertView.findViewById(R.id.text_company_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        InsuranceCompanyResultBean.insuranceCompanyList insuranceCompanyList = mList.get(position);
        viewHolder.mCompanyName.setText(insuranceCompanyList.getCompanyName());
        String img = insuranceCompanyList.getCompanyLogo();
        if (TextUtils.isEmpty(img)) {
            viewHolder.mCompanyLogo.setImageResource(R.drawable.image_fail_empty);
        }else {
            ImageManager.imageLoader.displayImage(img,viewHolder.mCompanyLogo,ImageManager.options3);
        }
        return convertView;
    }
    class ViewHolder {
        ImageView mCompanyLogo;
        TextView mCompanyName;
    }
}
