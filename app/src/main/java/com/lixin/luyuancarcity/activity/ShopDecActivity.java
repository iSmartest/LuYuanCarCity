package com.lixin.luyuancarcity.activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Matrix;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.fragment.DecFragment;
import com.lixin.luyuancarcity.fragment.OpinionFragment;
import com.lixin.luyuancarcity.fragment.ShopFragment;
import com.lixin.luyuancarcity.view.LazyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/5
 * My mailbox is 1403241630@qq.com
 */

public class ShopDecActivity extends FragmentActivity {
    private LazyViewPager viewPager;// 页卡内容
    private ImageView imageView1,imageView2,imageView3;// 动画图片
    private TextView text_shop,text_dec,text_opinion;// 选项名称
    private List<Fragment> fragments;// Tab页面列表
    private int offset = 0;// 动画图片偏移量
    private int bmpW;// 动画图片宽度
    private int selectedColor, unSelectedColor;//是否选择显示的颜色
    private int type;//选择页卡
    private static final int pageSize = 3;//页卡总数
    private int temp;
    private String rotateid;
    private String rotateIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_dec);
        Intent intent = getIntent();
        rotateid = intent.getStringExtra("rotateid");
        rotateIcon  = intent.getStringExtra("rotateid");
        temp = 0;
        initView();
        InitViewPager(temp);
        InitImageView(temp);
    }

    private void initView() {
        selectedColor = getResources().getColor(R.color.btn_login_color);
        unSelectedColor = getResources().getColor(R.color.black);
    }


    /**
     * 初始化Viewpager页
     */
    private void InitViewPager(int temp) {
        imageView1 = (ImageView) findViewById(R.id.cursor1);
        imageView2 = (ImageView) findViewById(R.id.cursor2);
        imageView3 = (ImageView) findViewById(R.id.cursor3);
        viewPager = (LazyViewPager) findViewById(R.id.vPager);
        fragments = new ArrayList<Fragment>();
        fragments.add(new ShopFragment());
        fragments.add(new DecFragment());
        fragments.add(new OpinionFragment());
        viewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager(),
                fragments));
        //页面定位
        viewPager.setCurrentItem(temp);
        //初始化头标
        text_shop = (TextView) findViewById(R.id.text_shop);
        text_dec = (TextView) findViewById(R.id.text_dec);
        text_opinion = (TextView) findViewById(R.id.text_opinion);
        text_shop.setText("商品");
        text_dec.setText("详情");
        text_opinion.setText("评价");
        //头标定位
        if (temp == 0){
            text_shop.setTextColor(selectedColor);
            text_dec.setTextColor(unSelectedColor);
            text_opinion.setTextColor(unSelectedColor);

            imageView1.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageView3.setVisibility(View.INVISIBLE);
        }else if (temp == 1){
            text_shop.setTextColor(unSelectedColor);
            text_dec.setTextColor(selectedColor);
            text_opinion.setTextColor(unSelectedColor);

            imageView1.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageView3.setVisibility(View.INVISIBLE);
        }else if (temp == 2){
            text_shop.setTextColor(unSelectedColor);
            text_dec.setTextColor(unSelectedColor);
            text_opinion.setTextColor(selectedColor);

            imageView1.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageView3.setVisibility(View.VISIBLE);
        }

        text_shop.setOnClickListener(new MyOnClickListener(0));
        text_dec.setOnClickListener(new MyOnClickListener(1));
        text_opinion.setOnClickListener(new MyOnClickListener(2));

        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 初始化动画，这个就是页卡滑动时，下面的横线也滑动的效果，在这里需要计算一些数据
     */

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
        if (temp == 0){
            imageView1.setImageMatrix(matrix);// 设置动画初始位置
        }else if (temp == 1){
            imageView2.setImageMatrix(matrix);// 设置动画初始位置
        }else if (temp == 2){
            imageView3.setImageMatrix(matrix);// 设置动画初始位置
        }
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
                    text_shop.setTextColor(selectedColor);
                    text_dec.setTextColor(unSelectedColor);
                    text_opinion.setTextColor(unSelectedColor);

                    imageView1.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.INVISIBLE);
                    imageView3.setVisibility(View.INVISIBLE);
                    type = 0;
                    break;
                case 1:
                    text_shop.setTextColor(unSelectedColor);
                    text_dec.setTextColor(selectedColor);
                    text_opinion.setTextColor(unSelectedColor);


                    imageView1.setVisibility(View.INVISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                    imageView3.setVisibility(View.INVISIBLE);
                    type = 1;
                    break;
                case 2:
                    text_shop.setTextColor(unSelectedColor);
                    text_dec.setTextColor(unSelectedColor);
                    text_opinion.setTextColor(selectedColor);


                    imageView1.setVisibility(View.INVISIBLE);
                    imageView2.setVisibility(View.INVISIBLE);
                    imageView3.setVisibility(View.VISIBLE);
                    type = 2;
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
                    text_shop.setTextColor(selectedColor);
                    text_dec.setTextColor(unSelectedColor);
                    text_opinion.setTextColor(unSelectedColor);

                    imageView1.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.INVISIBLE);
                    imageView3.setVisibility(View.INVISIBLE);
                    type=0;
                    break;
                case 1:
                    text_shop.setTextColor(unSelectedColor);
                    text_dec.setTextColor(selectedColor);
                    text_opinion.setTextColor(unSelectedColor);


                    imageView1.setVisibility(View.INVISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                    imageView3.setVisibility(View.INVISIBLE);
                    type=1;
                    break;
                case 2:
                    text_shop.setTextColor(unSelectedColor);
                    text_dec.setTextColor(unSelectedColor);
                    text_opinion.setTextColor(selectedColor);


                    imageView1.setVisibility(View.INVISIBLE);
                    imageView2.setVisibility(View.INVISIBLE);
                    imageView3.setVisibility(View.VISIBLE);
                    type=2;
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
