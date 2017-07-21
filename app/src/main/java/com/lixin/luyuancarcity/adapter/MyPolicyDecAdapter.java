package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.MyPolicyDecBean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/6/29
 * My mailbox is 1403241630@qq.com
 */

public class MyPolicyDecAdapter extends BaseAdapter{
    private List<MyPolicyDecBean.financialDetail> mList;
    private Context context;
    public void setMyPolicyDec(Context context, List<MyPolicyDecBean.financialDetail> mList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_my_policy_dec,null);
            viewHolder = new ViewHolder();
            viewHolder.mTitle = (TextView) convertView.findViewById(R.id.text_my_policy_emergency_title);
            viewHolder.mNumer = (TextView) convertView.findViewById(R.id.text_my_policy_order_numer);
            viewHolder.mDes = (TextView) convertView.findViewById(R.id.text_my_policy_emergency_des);
            viewHolder.mIsInsurance = (TextView) convertView.findViewById(R.id.text_my_policy_is_insurance);
            viewHolder.mLines = (TextView) convertView.findViewById(R.id.text_my_policy_emergency_lines);
            viewHolder.mCost = (TextView) convertView.findViewById(R.id.text_my_policy_emergency_cost);
            viewHolder.mLimit = (TextView) convertView.findViewById(R.id.text_my_policy_financial_limit);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MyPolicyDecBean.financialDetail financialDetail = mList.get(position);
        viewHolder.mTitle.setText(financialDetail.getEmergencyTitle());
        viewHolder.mNumer.setText(financialDetail.getOrderNumer());
        viewHolder.mDes.setText(financialDetail.getEmergencydes());
        viewHolder.mDes.setText("被保险人：" + financialDetail.getIsInsurancer());
        viewHolder.mLines.setText("保障额度：" + financialDetail.getEmergencylines());
        viewHolder.mCost.setText("保险费用：" + financialDetail.getEmergencycost());
        viewHolder.mLimit.setText("理财期限：" + financialDetail.getFinancialStartLimit() + "至" + financialDetail.getFinancialEndLimit());
        return convertView;
    }
    class ViewHolder{
        TextView mTitle,mNumer,mDes,mIsInsurance,mLines,mCost,mLimit;
    }
}
