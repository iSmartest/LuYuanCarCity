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
import com.lixin.luyuancarcity.bean.ClassListBean;
import com.lixin.luyuancarcity.utils.ImageManager;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/5
 * My mailbox is 1403241630@qq.com
 */

public class ClassListAdapter extends BaseAdapter {
    private Context context;
    private List<ClassListBean.commoditys> mList;
    public void setShopBeanList(Context context, List<ClassListBean.commoditys> mList) {
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
        final ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_class,null);
            viewHolder = new ViewHolder();
            viewHolder.shopPicture = (ImageView) convertView.findViewById(R.id.iv_shop_picture);
            viewHolder.title = (TextView) convertView.findViewById(R.id.text_shop_name);
            viewHolder.nowPrice = (TextView) convertView.findViewById(R.id.text_shop_price);
//            viewHolder.originalPrice = (TextView) convertView.findViewById(R.id.text_shop_original_price);
            viewHolder.soldNum = (TextView) convertView.findViewById(R.id.text_have_sold_num);
            viewHolder.commentNum = (TextView) convertView.findViewById(R.id.text_have_comment_num);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ClassListBean.commoditys commoditys = mList.get(position);
        viewHolder.title.setText(commoditys.commodityTitle + "/" + commoditys.getCommodityDescription());
        viewHolder.nowPrice.setText(commoditys.commodityNewPrice);
//        viewHolder.originalPrice.setText("￥" + commoditys.commodityOriginalPrice);
        viewHolder.soldNum.setText("已售" + commoditys.getCommoditysellerNum() + "件");
        viewHolder.commentNum.setText(commoditys.getCommodityCommendNum() + "人评论");
//        viewHolder.originalPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);//设置中划线并加清晰
        String img = commoditys.commodityIcon;
        if (TextUtils.isEmpty(img)){
            viewHolder.shopPicture.setImageResource(R.drawable.image_fail_empty);
        }else {
            ImageManager.imageLoader.displayImage(img,viewHolder.shopPicture,ImageManager.options3);
        }
        return convertView;
    }
    class ViewHolder{
        ImageView shopPicture;
        TextView title,nowPrice,originalPrice,soldNum,commentNum;

    }
}

