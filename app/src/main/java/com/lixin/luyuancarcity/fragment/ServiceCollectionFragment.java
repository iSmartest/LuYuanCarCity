package com.lixin.luyuancarcity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lixin.luyuancarcity.R;

/**
 * Created by 小火
 * Create time on  2017/7/18
 * My mailbox is 1403241630@qq.com
 */

public class ServiceCollectionFragment extends BaseFragment{
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_service_collection,null);
        return view;
    }
}
