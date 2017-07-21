package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.ForumBean;
import com.lixin.luyuancarcity.bean.MyReleaseBean;
import com.lixin.luyuancarcity.utils.ImageManager;
import com.lixin.luyuancarcity.view.MultiImageView;
import com.lixin.luyuancarcity.view.PraiseListView;
import com.lixin.luyuancarcity.view.RoundedImageView;
import com.lixin.luyuancarcity.weight.CommentListView;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/18
 * My mailbox is 1403241630@qq.com
 */

public class MyReleaseAdapter extends BaseAdapter{
    private Context context;
    private List<ForumBean.fateList> mList;
    public void setMyRelease(Context context, List<ForumBean.fateList> mList) {
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
        final MyReleaseViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_my_release,null);
            viewHolder = new MyReleaseViewHolder();
            viewHolder.commentList = (CommentListView) convertView.findViewById(R.id.my_release_commentList);
            viewHolder.praiseListView = (PraiseListView) convertView.findViewById(R.id.my_release_praiseListView);
            viewHolder.mUserIcon = (RoundedImageView) convertView.findViewById(R.id.iv_my_release_user_icon);
            viewHolder.mUserName = (TextView) convertView.findViewById(R.id.text_my_release_user_name);
            viewHolder.mTime = (TextView) convertView.findViewById(R.id.text_my_release_talk_time);
            viewHolder.mUserTalk = (TextView) convertView.findViewById(R.id.text_my_release_user_talk);
            viewHolder.mZanNum = (TextView) convertView.findViewById(R.id.my_release_zan_num);
            viewHolder.mPinLunNum = (TextView) convertView.findViewById(R.id.my_release_pinglun_num);
            viewHolder.multiImageView = (MultiImageView) convertView.findViewById(R.id.multiImageView);
            viewHolder.mZan = (ImageView) convertView.findViewById(R.id.my_release_zan);
            viewHolder.mPinglun = (ImageView) convertView.findViewById(R.id.my_release_pinglun);
            convertView.setTag(viewHolder);
        }else viewHolder = (MyReleaseViewHolder) convertView.getTag();
        ForumBean.fateList fateList = mList.get(position);
        viewHolder.mUserName.setText(fateList.getUserName());
        String img = fateList.getUserIcon();
        if (TextUtils.isEmpty(img))viewHolder.mUserIcon.setImageResource(R.drawable.image_fail_empty);
        else ImageManager.imageLoader.displayImage(img,viewHolder.mUserIcon,ImageManager.options3);
        viewHolder.mTime.setText(fateList.getDate());
        viewHolder.mUserTalk.setText(fateList.getContent());
        viewHolder.multiImageView.setList(fateList.getImgList());
        if (fateList.getFateZanList() != null  && fateList.getFateZanList().size() > 0){
            viewHolder.mZan.setImageResource(R.drawable.zan_yes);
            viewHolder.mZanNum.setText(fateList.getTumbNum());
            viewHolder.praiseListView.setDatas(fateList.getFateZanList());
            viewHolder.praiseListView.setVisibility(View.VISIBLE);
        }else {
            viewHolder.mZan.setImageResource(R.drawable.zan_no);
            viewHolder.mZanNum.setText(0);
            viewHolder.praiseListView.setVisibility(View.GONE);
        }
        if (fateList.getFateCommentList() != null && fateList.getFateCommentList().size() >0){
            viewHolder.mPinglun.setImageResource(R.drawable.pinglun_yes);
            viewHolder.mPinLunNum.setText(fateList.getCommentNum());
            viewHolder.commentList.setDatas(fateList.getFateCommentList());
            viewHolder.commentList.setVisibility(View.VISIBLE);
        }else {
            viewHolder.mPinglun.setImageResource(R.drawable.forum_no);
            viewHolder.mPinLunNum.setText(0);
            viewHolder.commentList.setVisibility(View.GONE);
        }
        return convertView;
    }



    class MyReleaseViewHolder {
        RoundedImageView mUserIcon;
        TextView mUserName,mTime,mUserTalk,mZanNum,mPinLunNum;
        MultiImageView multiImageView;
        ImageView mZan,mPinglun;
        PraiseListView praiseListView;
        CommentListView commentList;
    }
}
