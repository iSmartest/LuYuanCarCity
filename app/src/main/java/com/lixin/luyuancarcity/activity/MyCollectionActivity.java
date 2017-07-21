package com.lixin.luyuancarcity.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.fragment.BikeCollectionFragment;
import com.lixin.luyuancarcity.fragment.ServiceCollectionFragment;
import com.lixin.luyuancarcity.fragment.ShopCollectionFragment;

/**
 * Created by 小火
 * Create time on  2017/7/17
 * My mailbox is 1403241630@qq.com
 */

public class MyCollectionActivity extends BaseActivity{
    private TextView[] mTextView;
    private ImageView[] mImagView;
    private Fragment[] mFragments;
    private Fragment currentFragment = new Fragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        hideBack(5);
        setTitleText("我的收藏",false);
        initView();
        initFragment();
        refreshView();
    }

    private void initView() {
        mTextView = new TextView[3];
        mTextView[0] = (TextView) findViewById(R.id.tv_my_collection_shop);
        mTextView[1] = (TextView) findViewById(R.id.tv_my_collection_service);
        mTextView[2] = (TextView) findViewById(R.id.tv_my_collection_bike);
        mImagView = new ImageView[3];
        mImagView[0] = (ImageView) findViewById(R.id.my_collection_cursor);
        mImagView[1] = (ImageView) findViewById(R.id.my_collection_cursor01);
        mImagView[2] = (ImageView) findViewById(R.id.my_collection_cursor02);
    }

    private void initFragment() {
        mFragments = new Fragment[3];
        mFragments[0] = new ShopCollectionFragment();
        mFragments[1] = new ServiceCollectionFragment();
        mFragments[2] = new BikeCollectionFragment();
        setCurrent(0);
    }

    private void refreshView() {
        for (int i = 0; i < mTextView.length; i++) {
            mTextView[i].setId(i);
            mTextView[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 0:
                setCurrent(0);
                break;
            case 1:
                setCurrent(1);
                break;
            case 2:
                setCurrent(2);
                break;
        }
    }
    private void setCurrent(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!mFragments[position].isAdded()){
            transaction
                    .hide(currentFragment)
                    .add(R.id.activity_my_collection_layout_content,mFragments[position]);
        }else {
            transaction
                    .hide(currentFragment)
                    .show(mFragments[position]);
        }
        currentFragment = mFragments[position];
        transaction.commit();
        mTextView[position].setSelected(true);
        Resources resource = context.getResources();
        ColorStateList csl1 = resource.getColorStateList(R.color.black);
        ColorStateList csl2 = resource.getColorStateList(R.color.btn_login_color);
        for (int i = 0; i < mTextView.length; i++) {
            if (i != position) {
                mTextView[i].setSelected(false);
                mTextView[i].setTextColor(csl1);
                mImagView[i].setVisibility(View.INVISIBLE);
            }else {
                mTextView[i].setTextColor(csl2);
                mImagView[i].setVisibility(View.VISIBLE);
            }
        }
    }
}
