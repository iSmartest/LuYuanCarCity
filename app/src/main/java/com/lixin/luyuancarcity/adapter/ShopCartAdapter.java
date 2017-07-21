package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.utils.ImageManager;

import java.util.List;

import static com.lixin.luyuancarcity.R.id.iv_show_pic;

/**
 * Created by 小火
 * Create time on  2017/7/17
 * My mailbox is 1403241630@qq.com
 */

public class ShopCartAdapter extends BaseAdapter{
    private Context context;
    private List<ShopingCartBean.commoditys> mList;
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    /**
     * 单选接口
     *
     * @param checkInterface
     */
    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    /**
     * 改变商品数量接口
     *
     * @param modifyCountInterface
     */
    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }
    public void setShopCart(Context context, List<ShopingCartBean.commoditys> mList) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ShopCartViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shop_cart,null);
            viewHolder = new ShopCartViewHolder();
            viewHolder.mChoose = (CheckBox) convertView.findViewById(R.id.ck_chose);
            viewHolder.mAdd = (ImageView) convertView.findViewById(R.id.iv_add);
            viewHolder.mSub = (ImageView) convertView.findViewById(R.id.iv_sub);
            viewHolder.mPicture = (ImageView) convertView.findViewById(R.id.iv_show_pic);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.tv_commodity_name);
            viewHolder.mPrice = (TextView) convertView.findViewById(R.id.tv_price);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.tv_fabric);
            viewHolder.mSaleNum = (TextView) convertView.findViewById(R.id.tv_pants);
            viewHolder.mShowNum = (TextView) convertView.findViewById(R.id.tv_show_num);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ShopCartViewHolder) convertView.getTag();
        }
        final ShopingCartBean.commoditys commoditys = mList.get(position);
        viewHolder.mName.setText(commoditys.getCommodityTitle());
        viewHolder.mPrice.setText(commoditys.getCommodityNewPrice());
        viewHolder.mFabric.setText(commoditys.getCommoditySize());
        viewHolder.mSaleNum.setText(commoditys.getCommoditysellerNum());
        viewHolder.mShowNum.setText(commoditys.getCommodityShooCarNum());
        String img = commoditys.getCommodityIcon();
        if (TextUtils.isEmpty(img)){
            viewHolder.mPicture.setImageResource(R.drawable.image_fail_empty);
        }else {
            ImageManager.imageLoader.displayImage(img,viewHolder.mPicture,ImageManager.options3);
        }
        viewHolder.mChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commoditys.setChoosed(((CheckBox) v).isChecked());
                checkInterface.checkGroup(position, ((CheckBox) v).isChecked());//向外暴露勾选接口
            }
        });
        viewHolder.mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doIncrease(position, viewHolder.mShowNum, viewHolder.mChoose.isChecked());//暴露增加接口
            }
        });
        viewHolder.mSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doDecrease(position, viewHolder.mShowNum, viewHolder.mChoose.isChecked());//暴露删减接口
            }
        });
        return convertView;
    }
    class ShopCartViewHolder {
        CheckBox mChoose;
        ImageView mPicture,mSub,mAdd;
        TextView mName,mPrice,mFabric,mSaleNum,mShowNum;

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


    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param position      组元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doIncrease(int position, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param position      组元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doDecrease(int position, View showCountView, boolean isChecked);

        /**
         * 删除子item
         *
         * @param position
         */
        void childDelete(int position);
    }
}
