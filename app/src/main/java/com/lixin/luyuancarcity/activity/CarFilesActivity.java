package com.lixin.luyuancarcity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.CarFilesAdapte;
import com.lixin.luyuancarcity.bean.RecyclerViewBean;
import com.lixin.luyuancarcity.view.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小火
 * Create time on  2017/7/17
 * My mailbox is 1403241630@qq.com
 */

public class CarFilesActivity extends BaseActivity{
    private ViewPager viewPager;
    private CarFilesAdapte mAdapter;
    private String[] strImgs = {"http://image.mifengkong.cn/qianba/organization_id_45/57c3fa36f38c1957331842.png",
            "http://image.mifengkong.cn/qianba/organization_id_58/585a26cada217270984611.png",
            "http://image.mifengkong.cn/qianba/organization_id_/57a1ccec91fe4958589462.png",
            "http://image.mifengkong.cn/qianba/organization_id_36/579f00415e54f658392338.png",
            "http://image.mifengkong.cn/qianba/organization_id_45/57c3fa36f38c1957331842.png",
            "http://image.mifengkong.cn/qianba/organization_id_58/585a26cada217270984611.png",
            "http://image.mifengkong.cn/qianba/organization_id_/57a1ccec91fe4958589462.png",
            "http://image.mifengkong.cn/qianba/organization_id_36/579f00415e54f658392338.png"};

    private String[] names = {"张三", "李四", "王五", "李刚", "小明", "小红", "24K纯帅", "豆豆"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_files);
        hideBack(6);
        setTitleText("爱车档案",false);
        setRelease("管理车型");
        initView();
        initEvent();
    }

    public List<RecyclerViewBean> getData() {
        List<RecyclerViewBean> list = new ArrayList<>();
        RecyclerViewBean data;
        for (int i = 0; i < strImgs.length; i++) {
            data = new RecyclerViewBean();
            data.setId(i);
            data.setImageUrl(strImgs[i]);
            data.setName(names[i]);
            list.add(data);
        }
        return list;
    }
    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mAdapter = new CarFilesAdapte(CarFilesActivity.this,getData());
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(8);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_base_release:
                startActivity(new Intent(CarFilesActivity.this,ManagementCarActivity.class));
                break;
        }
    }

    private void initEvent() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("jiji", "当前页面: " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
