package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/11
 * My mailbox is 1403241630@qq.com
 */

public class AreAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    public AreAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView==null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.group_item_view1, null);
            holder=new ViewHolder();
            convertView.setTag(holder);
            holder.groupItem1 = (TextView) convertView.findViewById(R.id.groupItem1);
        }
        else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.groupItem1.setTextColor(Color.BLACK);
        holder.groupItem1.setText(list.get(position));
        return convertView;
    }
    static class ViewHolder {
        TextView groupItem1;
    }
}
