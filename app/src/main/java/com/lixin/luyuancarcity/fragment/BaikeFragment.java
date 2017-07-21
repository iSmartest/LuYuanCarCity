package com.lixin.luyuancarcity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.BaikeAdapter;


import java.util.ArrayList;

/**
 * Created by 小火
 * Create time on  2017/6/23
 * My mailbox is 1403241630@qq.com
 */

public class BaikeFragment extends BaseFragment{
    private View view;
    private ArrayList<String> titleList = new ArrayList<String>() {{
        add("省油方案");
        add("买车宝典");
        add("保养须知");
        add("保养须知");
        add("保养须知");
        add("保养须知");
        add("保养须知");
        add("保养须知");
        add("保养须知");
        add("保养须知");
    }};
    private TabLayout tabLayout;
    private ViewPager viewpager;
    private BaikeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frgment_baike,container,false);
        initView();
        return view;
    }

    private void initView() {
        tabLayout = (TabLayout) view.findViewById(R.id.baike_tabs);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager1);
        adapter = new BaikeAdapter(getActivity().getSupportFragmentManager(), titleList,getActivity());
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}
