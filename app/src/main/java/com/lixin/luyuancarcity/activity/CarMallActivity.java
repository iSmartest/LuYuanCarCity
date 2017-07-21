package com.lixin.luyuancarcity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.FirstAdapter;
import com.lixin.luyuancarcity.adapter.GridAdapter;
import com.lixin.luyuancarcity.bean.CarMallBean;
import com.lixin.luyuancarcity.bean.CarStyleBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.utils.ImageManager;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
/**
 * Created by 小火
 * Create time on  2017/7/4
 * My mailbox is 1403241630@qq.com
 */

public class CarMallActivity extends BaseActivity{
    private ImageView mBack;
    private ListView firstList;
    private GridView secondGrid;
    private EditText keySearch;
    private ImageView mImage, mSearch;
    private FirstAdapter firstAdapter;
    private GridAdapter secondAdapter;
    private List<CarMallBean.classifyMeun> classifyMeunList;
    private List<CarMallBean.classifyMeun.meun> meunList;
    private int defClass1;
    private String rotateid;
    private String rotateIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_mall);
        initView();
//        getdata();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.car_mall_back);
        firstList = (ListView) findViewById(R.id.first_list);
        firstList.setVerticalScrollBarEnabled(false);
        mImage = (ImageView) findViewById(R.id.iv_class_icon);
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(CarMallActivity.this, ShopDecActivity.class);
//                intent.putExtra("rotateid", rotateid);
//                intent.putExtra("rotateIcon", rotateIcon);
//                startActivity(intent);
            }
        });
        secondGrid = (GridView) findViewById(R.id.second_list);
        keySearch = (EditText) findViewById(R.id.a_key_edt_search);
        keySearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    if (TextUtils.isEmpty(keySearch.getText().toString().trim())) {
                        ToastUtils.showMessageShort(CarMallActivity.this, "请输入关键词");
                    } else {
//                        Intent intent = new Intent(CarMallActivity.this, SearchShopActivity.class);
//                        intent.putExtra("searchKey", keySearch.getText().toString().trim());
//                        startActivity(intent);
                    }
                    return true;
                }
                return false;
            }
        });
        mSearch = (ImageView) findViewById(R.id.im_search);
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(),SearchClassActivity.class);
                if (TextUtils.isEmpty(keySearch.getText().toString().trim())) {
                    ToastUtils.showMessageShort(CarMallActivity.this, "请输入关键词");
                } else {
//                    Intent intent = new Intent(CarMallActivity.this, SearchShopActivity.class);
//                    intent.putExtra("searchKey", keySearch.getText().toString().trim());
//                    startActivity(intent);
                }
            }
        });
        firstAdapter = new FirstAdapter();
        firstAdapter.setDefSelect(defClass1);
        firstAdapter.notifyDataSetChanged();
        firstList.setAdapter(firstAdapter);
        firstList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                firstAdapter.setDefSelect(position);
                MyApplication.temp = position;
                firstAdapter.notifyDataSetChanged();
                secondAdapter.setGrid(CarMallActivity.this, classifyMeunList.get(position).getMeun());
                secondGrid.setAdapter(secondAdapter);
                String img = classifyMeunList.get(position).getCommodityIcon();
                if (TextUtils.isEmpty(img)) {
                    mImage.setImageResource(R.drawable.image_fail_empty);
                } else {
                    ImageManager.imageLoader.displayImage(img, mImage, ImageManager.options3);
                }
                rotateid = classifyMeunList.get(position).getCommodityid();
                rotateIcon = classifyMeunList.get(position).getCommodityIcon();
                meunList = classifyMeunList.get(position).getMeun();
            }
        });
        secondAdapter = new GridAdapter(CarMallActivity.this);
        secondGrid.setAdapter(secondAdapter);
        secondGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CarMallActivity.this, ClassListActivity.class);
                intent.putExtra("meunid", meunList.get(position).getMeunid());
                startActivity(intent);
            }
        });
    }
    private void getdata() {
        Map<String, String> params = new HashMap<>();
        CarMallBean carMallBean = new CarMallBean("getCommodityClassifyInfo");
        String json = new Gson().toJson(carMallBean);
        params.put("json", json);
        Log.i("ShopDecActivity", "getdata: " + json);
        dialog1.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showMessageLong(context, "网络异常");
                dialog1.dismiss();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i("ClassFragment", "response: " + response.toString());
                Gson gson = new Gson();
                dialog1.dismiss();
                CarMallBean carMallBean = gson.fromJson(response, CarMallBean.class);
                if (carMallBean.getResult().equals("1")) {
                    ToastUtils.showMessageLong(CarMallActivity.this, carMallBean.getResultNote());
                }
                List<CarMallBean.classifyMeun> classifyMeun = carMallBean.classifyMeun;
                classifyMeunList.addAll(classifyMeun);
                firstAdapter.setFirst(CarMallActivity.this, classifyMeunList);
                firstList.setAdapter(firstAdapter);
                secondAdapter.setGrid(CarMallActivity.this, classifyMeunList.get(defClass1).getMeun());
                secondGrid.setAdapter(secondAdapter);
                String img = classifyMeunList.get(defClass1).getCommodityIcon();
                if (TextUtils.isEmpty(img)) {
                    mImage.setImageResource(R.drawable.image_fail_empty);
                } else {
                    ImageManager.imageLoader.displayImage(img, mImage, ImageManager.options3);
                }
                rotateid = classifyMeunList.get(defClass1).getCommodityid();
                rotateIcon = classifyMeunList.get(defClass1).getCommodityIcon();
                meunList = classifyMeunList.get(defClass1).getMeun();
            }
        });
    }
}
