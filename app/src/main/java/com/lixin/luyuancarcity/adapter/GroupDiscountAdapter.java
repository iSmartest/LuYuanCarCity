package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.WashCarStoreDecBean;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/11
 * My mailbox is 1403241630@qq.com
 */

public class GroupDiscountAdapter extends BaseAdapter{
    private Context context;
    private List<WashCarStoreDecBean.commoditys> mList;
    private CheckInterface checkInterface;
    public void setShopBeanList(Context context, List<WashCarStoreDecBean.commoditys> mList) {
        this.context = context;
        this.mList = mList;
    }
    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final GroupDiscountViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group_discount,null);
            viewHolder = new GroupDiscountViewHolder();
            viewHolder.mChoose = (CheckBox) convertView.findViewById(R.id.ck_shop_discount_choose);
            viewHolder.mShopName = (TextView) convertView.findViewById(R.id.tv_shop_discount_commodity_name);
            viewHolder.mNowPrice = (TextView) convertView.findViewById(R.id.tv_shop_discount_now_price);
            viewHolder.mOriginal = (TextView) convertView.findViewById(R.id.tv_shop_discount_original_param);
            viewHolder.mDiscountDec = (TextView) convertView.findViewById(R.id.tv_shop_discount_dec);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (GroupDiscountViewHolder) convertView.getTag();

        }
        final WashCarStoreDecBean.commoditys commoditys = mList.get(position);
        viewHolder.mChoose.setChecked(commoditys.isChoosed());
        viewHolder.mShopName.setText(commoditys.getCommodityTitle());
        viewHolder.mNowPrice.setText(commoditys.getCommodityNewPrice());
        viewHolder.mOriginal.setText(commoditys.getCommodityOriginalPrice());
        viewHolder.mDiscountDec.setText(commoditys.getCommodityDescription());
        //单选框按钮
        viewHolder.mChoose.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        commoditys.setChoosed(((CheckBox) v).isChecked());
                        checkInterface.checkGroup(position, ((CheckBox) v).isChecked());//向外暴露接口
                    }
                }
        );
        return convertView;
    }
    class GroupDiscountViewHolder{
        CheckBox mChoose;
        TextView mShopName,mNowPrice,mOriginal,mDiscountDec;
    }
    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param position  元素位置
         * @param isChecked 元素选中与否
         */
        void checkGroup(int position, boolean isChecked);
    }
}
