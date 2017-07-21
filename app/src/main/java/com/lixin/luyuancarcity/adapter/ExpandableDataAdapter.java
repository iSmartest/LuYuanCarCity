package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.MaintenanceBean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/13
 * My mailbox is 1403241630@qq.com
 */

public class ExpandableDataAdapter extends BaseAdapter{
    private Context context;
    private  List<MaintenanceBean.maintainList.maintaingeneral> maintaingeneral;
    public ExpandableDataAdapter(Context context, List<MaintenanceBean.maintainList.maintaingeneral> maintaingeneral) {
        this.context = context;
        this.maintaingeneral = maintaingeneral;
    }

    @Override
    public int getCount() {
        return maintaingeneral == null ? 0 : maintaingeneral.size();
    }

    @Override
    public Object getItem(int position) {
        return maintaingeneral.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ExpandableDataViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_expandable_data,null);
        }
        return null;
    }
    class ExpandableDataViewHolder{

    }
}
