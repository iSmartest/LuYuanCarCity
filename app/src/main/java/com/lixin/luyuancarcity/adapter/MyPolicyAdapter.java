package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.MyPolicyResultBean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/6/27
 * My mailbox is 1403241630@qq.com
 */

public class MyPolicyAdapter extends BaseAdapter{
    private Context context;
    private List<MyPolicyResultBean.financialList> mList;
    public void setMyPolicy(Context context, List<MyPolicyResultBean.financialList> mList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_my_policy,null);
            viewHolder = new ViewHolder();
            viewHolder.mName = (TextView) convertView.findViewById(R.id.text_my_policy_name);
            viewHolder.mLimits = (TextView) convertView.findViewById(R.id.text_my_policy_limits);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MyPolicyResultBean.financialList financialList = mList.get(position);
        viewHolder.mName .setText(financialList.getEmergencyTitle());
        viewHolder.mLimits.setText("保障期限：" + financialList.getFinancialStartLimit() + "-" + financialList.getFinancialEndLimit());

        return convertView;
    }

    class ViewHolder{
        TextView mName,mLimits;
    }
}
