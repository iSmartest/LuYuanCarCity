package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.CarStyleBean;
import com.lixin.luyuancarcity.bean.SortModel;
import com.lixin.luyuancarcity.utils.ImageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/6/27
 * My mailbox is 1403241630@qq.com
 */

public class SortAdapter extends BaseAdapter implements SectionIndexer {
    private List<SortModel> list = null;
    private Context mContext;
    List<CarStyleBean.carsSelectList> carsSelect = new ArrayList<>();
    public SortAdapter(Context mContext, List<SortModel> list) {
        this.mContext = mContext;
        this.list = list;
    }
    public void setData(List<CarStyleBean.carsSelectList> carsSelect){
        this.carsSelect = carsSelect;
    }
    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     * @param list
     */
    public void updateListView(List<SortModel> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position-1);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final SortModel mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.sort_item, null);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.text_sort_content);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
            viewHolder.imPicture = (ImageView) view.findViewById(R.id.iv_sort_car_picture);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if(position == getPositionForSection(section)){
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(mContent.getSortLetters());
        }else{
            viewHolder.tvLetter.setVisibility(View.GONE);
        }

        viewHolder.tvTitle.setText(this.list.get(position).getName());
        for (int i = 0; i < carsSelect.size(); i++) {
            if ((this.list.get(position).getName()).equals(carsSelect.get(i).getCarName())){
                ImageManager.imageLoader.displayImage(carsSelect.get(i).getCarleader(),viewHolder.imPicture,ImageManager.options3);
            }
        }

        return view;

    }



    final static class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;
        ImageView imPicture;
    }


    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 提取英文的首字母，非英文字母用#代替。
     *
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        String  sortStr = str.trim().substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}
