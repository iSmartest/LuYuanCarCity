package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.activity.MyApplication;
import com.lixin.luyuancarcity.bean.ForumBean;
import com.lixin.luyuancarcity.utils.ImageManager;
import com.lixin.luyuancarcity.view.MultiImageView;
import com.lixin.luyuancarcity.view.PraiseListView;
import com.lixin.luyuancarcity.view.RoundedImageView;
import com.lixin.luyuancarcity.weight.CommentListView;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/15
 * My mailbox is 1403241630@qq.com
 */

public class ForumAdapter extends BaseAdapter{
    private Context context;
    private List<ForumBean.fateList> mList;
    public void setForum(Context context, List<ForumBean.fateList> mList) {
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
        final ForumViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_forum,null);
            viewHolder = new ForumViewHolder();
            viewHolder.mIcon = (RoundedImageView) convertView.findViewById(R.id.iv_user_icon);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.text_user_name);
            viewHolder.mTime = (TextView) convertView.findViewById(R.id.text_talk_time);
            viewHolder.mComment = (TextView) convertView.findViewById(R.id.text_user_talk);
            viewHolder.mZanNum = (TextView) convertView.findViewById(R.id.zan_num);
            viewHolder.pinglun_num = (TextView) convertView.findViewById(R.id.pinglun_num);
            viewHolder.multiImageView = (MultiImageView) convertView.findViewById(R.id.multiImageView);
            viewHolder.zan = (ImageView) convertView.findViewById(R.id.zan);
            viewHolder.pinglun = (ImageView) convertView.findViewById(R.id.pinglun);
            viewHolder.praiseListView = (PraiseListView) convertView.findViewById(R.id.praiseListView);
            viewHolder.commentList = (CommentListView) convertView.findViewById(R.id.commentList);
            viewHolder.digCommentBody = (LinearLayout) convertView.findViewById(R.id.digCommentBody);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ForumViewHolder) convertView.getTag();
        }
        final ForumBean.fateList fateList = mList.get(position);
        viewHolder.mName.setText(fateList.getUserName());
        viewHolder.mTime.setText(fateList.getDate());
        viewHolder.mComment.setText(fateList.getContent());
        viewHolder.mZanNum.setText(fateList.getTumbNum());
        viewHolder.pinglun_num.setText(fateList.getCommentNum());
        String img = fateList.getUserIcon();
        ImageManager.imageLoader.displayImage(img,viewHolder.mIcon,ImageManager.options3);
        boolean hasFavort = fateList.hasFavort();
        boolean hasComment = fateList.hasComment();
        if(hasFavort || hasComment){
            if(hasFavort){//处理点赞列表
                viewHolder.praiseListView.setOnItemClickListener(new PraiseListView.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        String userName = fateList.getFateZanList().get(position).getUserName();
                        String userId = fateList.getFateZanList().get(position).getUserId();
                        Toast.makeText(MyApplication.getContext(), userName + " &id = " + userId, Toast.LENGTH_SHORT).show();
                    }
                });
                viewHolder.praiseListView.setDatas( fateList.getFateZanList());
                viewHolder.praiseListView.setVisibility(View.VISIBLE);
            }else{
                viewHolder.praiseListView.setVisibility(View.GONE);
            }

            if(hasComment){//处理评论列表
                viewHolder.commentList.setOnItemClickListener(new CommentListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int commentPosition) {
//                        CommentItem commentItem = commentsDatas.get(commentPosition);
//                        if(DatasUtil.curUser.getId().equals(commentItem.getUser().getId())){//复制或者删除自己的评论
//
//                            CommentDialog dialog = new CommentDialog(context, presenter, commentItem, circlePosition);
//                            dialog.show();
//                        }else{//回复别人的评论
//                            if(presenter != null){
//                                CommentConfig config = new CommentConfig();
//                                config.circlePosition = circlePosition;
//                                config.commentPosition = commentPosition;
//                                config.commentType = CommentConfig.Type.REPLY;
//                                config.replyUser = commentItem.getUser();
//                                presenter.showEditTextBody(config);
//                            }
//                        }
                    }
                });
                viewHolder.commentList.setOnItemLongClickListener(new CommentListView.OnItemLongClickListener() {
                    @Override
                    public void onItemLongClick(int commentPosition) {
                        //长按进行复制或者删除
//                        CommentItem commentItem = commentsDatas.get(commentPosition);
//                        CommentDialog dialog = new CommentDialog(context, presenter, commentItem, circlePosition);
//                        dialog.show();
                    }
                });
                viewHolder.commentList.setDatas(fateList.getFateCommentList());
                viewHolder.commentList.setVisibility(View.VISIBLE);

            }else {
                viewHolder.commentList.setVisibility(View.GONE);
            }
            viewHolder.digCommentBody.setVisibility(View.VISIBLE);
        }else{
            viewHolder.digCommentBody.setVisibility(View.GONE);
        }
        viewHolder.multiImageView.setList(fateList.getImgList());
        return convertView;
    }

    class ForumViewHolder {
        RoundedImageView mIcon;
        TextView mName,mTime,mComment,mZanNum,pinglun_num;
        MultiImageView multiImageView;
        ImageView zan,pinglun;
        PraiseListView praiseListView;
        CommentListView commentList;
        LinearLayout digCommentBody;
    }
}
