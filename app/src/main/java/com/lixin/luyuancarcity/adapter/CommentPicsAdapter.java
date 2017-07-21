package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.utils.ImageManager;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/12
 * My mailbox is 1403241630@qq.com
 */

public class CommentPicsAdapter extends BaseAdapter{
    private Context context;
    private List<String> commentPics;
    public CommentPicsAdapter(List<String> commentPics, Context context) {
        this.commentPics = commentPics;
        this.context = context;
    }

    @Override
    public int getCount() {
        return commentPics == null ? 0 : commentPics.size();
    }

    @Override
    public Object getItem(int position) {
        return commentPics.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_comment_grid,null);
            vh = new ViewHolder();
            vh.commentPics = (ImageView) convertView.findViewById(R.id.ima_grid_comment_picture);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        String img = commentPics.get(position);
        if (TextUtils.isEmpty(img)){
            vh.commentPics.setImageResource(R.drawable.image_fail_empty);
        }else {
            ImageManager.imageLoader.displayImage(img,vh.commentPics,ImageManager.options3);
        }
        return convertView;
    }
    class ViewHolder {
        ImageView commentPics;
    }
}
