package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.MaintenanceBean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/13
 * My mailbox is 1403241630@qq.com
 */

public class MaintenanceAdapter extends BaseAdapter{
    private Context context;

    private List<MaintenanceBean.maintainList> mList;

    public void setMaintenance(Context context, List<MaintenanceBean.maintainList> mList) {
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
        final MaintenanceViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_maintenance,null);
            viewHolder = new MaintenanceViewHolder();
            viewHolder.maintenance_gramrely = (TextView) convertView.findViewById(R.id.maintenance_gramrely);
            viewHolder.expandable_list = (ExpandableListView) convertView.findViewById(R.id.expandable_list);
            convertView.setTag(viewHolder);
        }else viewHolder = (MaintenanceViewHolder) convertView.getTag();
        MaintenanceBean.maintainList maintainList = mList.get(position);
        viewHolder.maintenance_gramrely.setText(maintainList.getMaintainName());
        ExpandableDataAdapter expandableDataAdapter = new ExpandableDataAdapter(context,maintainList.getMaintaingeneral());
        viewHolder.expandable_list.setGroupIndicator(null);

        viewHolder.expandable_list.setAdapter(expandableDataAdapter);
        return null;
    }

    class MaintenanceViewHolder{
        ExpandableListView expandable_list;
        TextView maintenance_gramrely;
    }
}
