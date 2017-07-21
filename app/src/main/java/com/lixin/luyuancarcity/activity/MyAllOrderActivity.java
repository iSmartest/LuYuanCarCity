package com.lixin.luyuancarcity.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.fragment.AllOrderFragment;
import com.lixin.luyuancarcity.fragment.WaitEvaluateFragment;
import com.lixin.luyuancarcity.fragment.WaitInstallFragment;
import com.lixin.luyuancarcity.fragment.WaitPaymentFragment;
import com.lixin.luyuancarcity.fragment.WaitReceiptFragment;
import com.lixin.luyuancarcity.fragment.WaitRefundFragment;
import com.lixin.luyuancarcity.view.LazyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/17
 * My mailbox is 1403241630@qq.com
 */

public class MyAllOrderActivity extends BaseActivity{
    private LazyViewPager viewPager;// 页卡内容
    private List<Fragment> fragments;// Tab页面列表
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private int selectedColor, unSelectedColor;//是否选择显示的颜色
    private static final int pageSize = 6;//页卡总数
    private int temp;
    private ImageView[] mImageView;
    private TextView[] mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_all_order);
        hideBack(5);
        setTitleText("我的订单",false);
        Intent intent = getIntent();
        String result  = intent.getStringExtra("orderState");
        temp = Integer.valueOf(result);
        initView();
        InitImageView(temp);
    }

    private void initView() {
        mTextView = new TextView[6];
        mImageView = new ImageView[6];
        mTextView[0] = (TextView) findViewById(R.id.tv_all_order);
        mTextView[1] = (TextView) findViewById(R.id.tv_wait_payment);
        mTextView[2] = (TextView) findViewById(R.id.tv_wait_goods);
        mTextView[3] = (TextView) findViewById(R.id.tv_wait_install);
        mTextView[4]= (TextView) findViewById(R.id.tv_wait_evaluate);
        mTextView[5]= (TextView) findViewById(R.id.tv_wait_after_sale_refund);
        mImageView[0] = (ImageView) findViewById(R.id.my_order_cursor0);
        mImageView[1] = (ImageView) findViewById(R.id.my_order_cursor1);
        mImageView[2] = (ImageView) findViewById(R.id.my_order_cursor2);
        mImageView[3] = (ImageView) findViewById(R.id.my_order_cursor3);
        mImageView[4] = (ImageView) findViewById(R.id.my_order_cursor4);
        mImageView[5] = (ImageView) findViewById(R.id.my_order_cursor5);
        mTextView[0].setText("全部");
        mTextView[1].setText("待支付");
        mTextView[2].setText("待收货");
        mTextView[3].setText("待安装");
        mTextView[4].setText("待评价");
        mTextView[5].setText("退款售后");
        viewPager = (LazyViewPager) findViewById(R.id.vPager);
        fragments = new ArrayList<Fragment>();
        fragments.add(new AllOrderFragment());
        fragments.add(new WaitPaymentFragment());
        fragments.add(new WaitReceiptFragment());
        fragments.add(new WaitInstallFragment());
        fragments.add(new WaitEvaluateFragment());
        fragments.add(new WaitRefundFragment());
        viewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager(),
                fragments));
        viewPager.setCurrentItem(temp);
        for (int i = 0; i < mTextView.length; i++) {
            mTextView[i].setId(i);
            mTextView[i].setOnClickListener(new MyOnClickListener(i));
        }
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        setSelect(temp);
    }
    /**
     * 初始化动画，这个就是页卡滑动时，下面的横线也滑动的效果，在这里需要计算一些数据
     */
    public void setSelect(int position){
        selectedColor = getResources().getColor(R.color.btn_login_color);
        unSelectedColor = getResources().getColor(R.color.black);
        for (int i = 0; i < mTextView.length; i++) {
            if (i != position) {
                mTextView[i].setTextColor(unSelectedColor);
                mImageView[i].setVisibility(View.INVISIBLE);
            }else {
                mTextView[i].setTextColor(selectedColor);
                mImageView[i].setVisibility(View.VISIBLE);
            }
        }
    }
    private void InitImageView(int temp) {
        bmpW = BitmapFactory.decodeResource(getResources(),
                R.drawable.tab_selected_bg).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / pageSize - bmpW) / 2;// 计算偏移量--(屏幕宽度/页卡总数-图片实际宽度)/2
        // = 偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        mImageView[temp].setImageMatrix(matrix);
    }

    /**
     * 头标点击监听
     */
    private class MyOnClickListener implements View.OnClickListener {
        private int index = 0;
        public MyOnClickListener(int i) {
            index = i;
        }
        public void onClick(View v) {
            switch (index) {
                case 0:
                    setSelect(0);
                    break;
                case 1:
                    setSelect(1);
                    break;
                case 2:
                    setSelect(2);
                    break;
                case 3:
                    setSelect(3);
                    break;
                case 4:
                    setSelect(4);
                    break;
                case 5:
                    setSelect(5);
                    break;
            }
            viewPager.setCurrentItem(index);
        }

    }

    /**
     * 为选项卡绑定监听器
     */
    public class MyOnPageChangeListener implements LazyViewPager.OnPageChangeListener {

        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
        int two = one * 2;// 页卡1 -> 页卡3 偏移量
        int three = one * 3;// 页卡1 -> 页卡4 偏移量


        public void onPageScrollStateChanged(int index) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageSelected(int index) {
//            Animation animation = new TranslateAnimation(one * currIndex, one
//                    * index, 0, 0);// 显然这个比较简洁，只有一行代码。
//            currIndex = index;
//            animation.setFillAfter(true);// True:图片停在动画结束位置
//            animation.setDuration(300);
            switch (index) {
                case 0:
                    setSelect(0);
                    break;
                case 1:
                    setSelect(1);
                    break;
                case 2:
                    setSelect(2);
                    break;
                case 3:
                    setSelect(3);
                    break;
                case 4:
                    setSelect(4);
                    break;
                case 5:
                    setSelect(5);
                    break;
            }
        }
    }
    /**
     * 定义适配器
     */
    class myPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public myPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        /**
         * 得到每个页面
         */
        @Override
        public Fragment getItem(int arg0) {
            return (fragmentList == null || fragmentList.size() == 0) ? null
                    : fragmentList.get(arg0);
        }

        /**
         * 每个页面的title
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

        /**
         * 页面的总个数
         */
        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }
}


