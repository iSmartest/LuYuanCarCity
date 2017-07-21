package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.CarMallBean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/4
 * My mailbox is 1403241630@qq.com
 */

public class FirstAdapter extends BaseAdapter{
    private Context context;
    private List<CarMallBean.classifyMeun> classifyMeunList;
    private int defItem;//声明默认选中的项
    public void setFirst(Context context, List<CarMallBean.classifyMeun> classifyMeunList) {
        this.context = context;
        this.classifyMeunList = classifyMeunList;
    }
    @Override
    public int getCount() {
        return classifyMeunList == null ? 0 : classifyMeunList.size();
    }

    @Override
    public Object getItem(int position) {
        return classifyMeunList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void setDefSelect(int position) {
        this.defItem = position;
        Log.i("111", "setDefSelect: " + position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_text,null);
            viewHolder = new ViewHolder();
            viewHolder.mClass = (TextView) convertView.findViewById(R.id.text_class_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CarMallBean.classifyMeun classifyMeun = classifyMeunList.get(position);
        viewHolder.mClass.setText(classifyMeun.getMeunType());
        disposalView(position,convertView);
        return convertView;
    }

    private void disposalView(int position, View convertView) {
        Resources resource = context.getResources();
        ColorStateList csl1 = resource.getColorStateList(R.color.snow_white);
        ColorStateList csl2 = resource.getColorStateList(R.color.btn_login_color);
        if (position == defItem) {
            convertView.setBackgroundResource(R.color.btn_login_color);
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.mClass.setTextColor(csl1);
        }else {
            convertView.setBackgroundResource(R.color.snow_white);
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.mClass.setTextColor(csl2);
        }
    }
    class ViewHolder{
        TextView mClass;
    }
}
