package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.InsuranceTypeDecBean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/6/28
 * My mailbox is 1403241630@qq.com
 */

public class InsuranceTypeDecAdapter extends BaseAdapter{
    private Context context;
    private List<InsuranceTypeDecBean.commodityParameters> mList;
    public void setInsuranceTypeDec(Context context, List<InsuranceTypeDecBean.commodityParameters> mList) {
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
        final ViewHolder viewHoldwer;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_insurance_dec,null);
            viewHoldwer = new ViewHolder();
            viewHoldwer.mParameter = (TextView) convertView.findViewById(R.id.text_insurance_dec_parameter);
            viewHoldwer.mParameterTypes = (TextView) convertView.findViewById(R.id.text_insurance_dec_parameter_types);
            convertView.setTag(viewHoldwer);
        }else {
            viewHoldwer = (ViewHolder) convertView.getTag();
        }
        InsuranceTypeDecBean.commodityParameters commodityParameters = mList.get(position);
        viewHoldwer.mParameterTypes.setText(commodityParameters.getParameterTypes());
        viewHoldwer.mParameter.setText(commodityParameters.getParameters());
        return convertView;
    }

    class ViewHolder {
        TextView mParameter,mParameterTypes;
    }
}
