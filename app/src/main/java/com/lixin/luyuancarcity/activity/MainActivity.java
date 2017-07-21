package com.lixin.luyuancarcity.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.fragment.BaikeFragment;
import com.lixin.luyuancarcity.fragment.ForumFragment;
import com.lixin.luyuancarcity.fragment.HomeFragment;
import com.lixin.luyuancarcity.fragment.MineFragment;
import com.lixin.luyuancarcity.fragment.StoreFragment;

public class MainActivity extends BaseActivity {
    private String[] titles;
    private LinearLayout[] mLinearLayout;
    private TextView[] mTextView;
    private Fragment[] mFragments;
    private Fragment currentFragment = new Fragment();
    private int current = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTitle();
        initView();
        initFragment();
        refreshView();
    }
    private void initTitle() {
        titles = new String[5];
        titles[0] = "路远养车";
        titles[1] = "门店";
        titles[2] = "路友会";
        titles[3] = "汽车资讯";
        titles[4] = "我的";
    }

    private void initView() {
        mLinearLayout = new LinearLayout[5];
        mLinearLayout[0] = (LinearLayout) findViewById(R.id.iv_main_home);
        mLinearLayout[1] = (LinearLayout) findViewById(R.id.iv_main_store);
        mLinearLayout[2] = (LinearLayout) findViewById(R.id.iv_main_forum);
        mLinearLayout[3] = (LinearLayout) findViewById(R.id.iv_main_baike);
        mLinearLayout[4] = (LinearLayout) findViewById(R.id.iv_main_mine);

        mTextView = new TextView[5];
        mTextView[0] = (TextView) findViewById(R.id.text_main_home);
        mTextView[1] = (TextView) findViewById(R.id.text_main_store);
        mTextView[2] = (TextView) findViewById(R.id.text_main_forum);
        mTextView[3] = (TextView) findViewById(R.id.text_main_baike);
        mTextView[4] = (TextView) findViewById(R.id.text_main_mine);
    }
    private void initFragment() {
        mFragments = new Fragment[5];
        mFragments[0] = new HomeFragment();
        mFragments[1] = new StoreFragment();
        mFragments[2] = new ForumFragment();
        mFragments[3] = new BaikeFragment();
        mFragments[4] = new MineFragment();
        setCurrent(0);
    }
    private void refreshView() {
        for (int i = 0; i < mLinearLayout.length; i++) {
            mLinearLayout[i].setId(i);
            mLinearLayout[i].setOnClickListener(this);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 0:
                setCurrent(0);
                break;
            case 1:
                setCurrent(1);
                break;
            case 2:
                setCurrent(2);
                break;
            case 3:
                setCurrent(3);
                break;
            case 4:
                setCurrent(4);
                break;
            case R.id.text_base_release:
                Toast.makeText(MainActivity.this,"跳转到发布",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
    private void setCurrent(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!mFragments[position].isAdded()){
            transaction
                    .hide(currentFragment)
                    .add(R.id.activity_new_main_layout_content,mFragments[position]);
        }else {
            transaction
                    .hide(currentFragment)
                    .show(mFragments[position]);
        }
        currentFragment = mFragments[position];
        transaction.commit();


        mLinearLayout[position].setSelected(true);
        Resources resource = context.getResources();
        ColorStateList csl1 = resource.getColorStateList(R.color.black);
        ColorStateList csl2 = resource.getColorStateList(R.color.app_main_colour);
        for (int i = 0; i < mLinearLayout.length; i++) {
            if (i != position) {
                mLinearLayout[i].setSelected(false);
                mTextView[i].setTextColor(csl1);
            }else {
                mTextView[i].setTextColor(csl2);
            }
        }
        setTitleText(titles[position],true);
        if (position == 0){
            hideBack(0);
        }else if (position == 1){
            hideBack(1);
        }else if (position == 2){
            hideBack(2);
        }else if (position == 3){
            hideBack(3);
        }else if (position == 4){
            hideBack(4);
        }
        current = position;
    }
}
