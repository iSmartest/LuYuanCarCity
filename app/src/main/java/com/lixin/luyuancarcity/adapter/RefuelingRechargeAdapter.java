package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.RefuelingRechargeBean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/3
 * My mailbox is 1403241630@qq.com
 */

public class RefuelingRechargeAdapter extends BaseAdapter {
    private Context context;
    private List<RefuelingRechargeBean.topUpList> mList;
    private RechargeInterface rechargeInterface;
    public void setRefuelingRecharge(Context context, List<RefuelingRechargeBean.topUpList> mList) {
        this.context = context;
        this.mList = mList;
    }
    public void setRechargeInterface(RechargeInterface rechargeInterface) {
        this.rechargeInterface = rechargeInterface;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final RefuelingRechargeViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_refueling_recharge,null);
            viewHolder = new RefuelingRechargeViewHolder();
            viewHolder.mMonth = (TextView) convertView.findViewById(R.id.text_month);
            viewHolder.mDiscount = (TextView) convertView.findViewById(R.id.text_discount);
            viewHolder.mNowRecharge = (TextView) convertView.findViewById(R.id.text_now_recharge);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (RefuelingRechargeViewHolder) convertView.getTag();
        }
        RefuelingRechargeBean.topUpList topUpList = mList.get(position);
        viewHolder.mMonth.setText(topUpList.getMouthTime());
        viewHolder.mDiscount.setText(topUpList.getDiscount());
        viewHolder.mNowRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rechargeInterface.nowRecharge(position);//暴露增加接口
            }
        });
        return convertView;
    }

   class RefuelingRechargeViewHolder {
        TextView mMonth,mDiscount,mNowRecharge;
   }
    /**
     * 立即充值的接口
     */
    public interface RechargeInterface {
        void nowRecharge(int position);
    }
}
