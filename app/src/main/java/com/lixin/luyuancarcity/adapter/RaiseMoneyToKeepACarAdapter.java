package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lixin.luyuancarcity.R;

/**
 * Created by 小火
 * Create time on  2017/7/14
 * My mailbox is 1403241630@qq.com
 */

public class RaiseMoneyToKeepACarAdapter extends BaseAdapter{
    private Context context;
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final RaiseMoneyToKeepViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_raise_money_to_keep_a_car,null);
        }
        return null;
    }
    class RaiseMoneyToKeepViewHolder{

    }
}
