package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.RecyclerViewBean;
import com.lixin.luyuancarcity.utils.ImageManager;

import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/21
 * My mailbox is 1403241630@qq.com
 */

public class CarFilesAdapte extends PagerAdapter {
    private Context context;
    private List<RecyclerViewBean> data;
    public CarFilesAdapte(Context context, List<RecyclerViewBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.item_recyclerview, null);
        ImageView adapter_iv_icon = (ImageView) view.findViewById(R.id.adapter_iv_icon);
        String img = data.get(position).getImageUrl();
        ImageManager.imageLoader.displayImage(img,adapter_iv_icon,ImageManager.options3);
        TextView adapter_tv_name = (TextView) view.findViewById(R.id.adapter_tv_name);
        adapter_tv_name.setText(data.get(position).getName());
        container.addView(view);
        return view;
    }

}
