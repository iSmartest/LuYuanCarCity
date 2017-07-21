package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.OpinionBean;
import com.lixin.luyuancarcity.view.MyGridView;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/11
 * My mailbox is 1403241630@qq.com
 */

public class GroupPurchaseAdapter extends BaseAdapter{
    private Context context;
    private List<OpinionBean.commodityCommentLists> mOpinionList;

    public void setOpinionList(Context context, List<OpinionBean.commodityCommentLists> mOpinionList) {
        this.context = context;
        this.mOpinionList = mOpinionList;
    }

    @Override
    public int getCount() {
        return mOpinionList == null ? 0 : mOpinionList.size();
    }

    @Override
    public Object getItem(int position) {
        return mOpinionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final GroupPurchaseViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group_purchase,null);
            viewHolder = new GroupPurchaseViewHolder();
            viewHolder.mRatingBar = (RatingBar) convertView.findViewById(R.id.rab_item_star);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.text_item_group_purchase_name);
            viewHolder.mTime = (TextView) convertView.findViewById(R.id.text_item_group_purchase_time);
            viewHolder.mComment = (TextView) convertView.findViewById(R.id.text_item_group_purchase_comment);
            viewHolder.mGridView = (MyGridView) convertView.findViewById(R.id.gridView3);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (GroupPurchaseViewHolder) convertView.getTag();
        }
        OpinionBean.commodityCommentLists commodityCommentLists = mOpinionList.get(position);
        float starNum = Float.parseFloat(commodityCommentLists.getCommentStarNum());
        viewHolder.mRatingBar.setRating(starNum);
        viewHolder.mName.setText(commodityCommentLists.getUserName());
        viewHolder.mTime.setText(commodityCommentLists.getUserComment());
        CommentPicsAdapter commentPicsAdapter = new CommentPicsAdapter(commodityCommentLists.getCommentPics(),context);
        viewHolder.mGridView.setAdapter(commentPicsAdapter);
        return convertView;
    }
    class GroupPurchaseViewHolder {
        RatingBar mRatingBar;
        TextView mName,mTime,mComment;
        MyGridView mGridView;
    }
}
