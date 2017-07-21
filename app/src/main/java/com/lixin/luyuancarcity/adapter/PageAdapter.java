package com.lixin.luyuancarcity.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.fragment.PageFragment;

import java.util.ArrayList;

/**
 * Created by 小火
 * Create time on  2017/7/15
 * My mailbox is 1403241630@qq.com
 */

public class PageAdapter extends FragmentPagerAdapter {
    private ArrayList<String> titleList;
    private Context context;
    public PageAdapter(FragmentManager fm, ArrayList<String> titleList, Context context) {
        super(fm);
        this.titleList = titleList;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
//        return null;
    }
//    public View getTabView(int position){
//        View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
//        TextView tv= (TextView) view.findViewById(R.id.textView);
//        tv.setText(titleList.get(position));
//        return view;
//    }

}

