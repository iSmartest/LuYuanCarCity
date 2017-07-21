package com.lixin.luyuancarcity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.ClassListAdapter;
import com.lixin.luyuancarcity.bean.ClassListBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 小火
 * Create time on  2017/5/16
 * My mailbox is 1403241630@qq.com
 */

public class ClassListActivity extends BaseActivity{
    private ImageView mBack,mSearch,mDown01,mDown02,mDown03,mUp01,mUp02,mUp03;
    private EditText ediSearch;
    private TextView tvComprehensive,tvSalesVolume,tvPrice;
    private PullToRefreshListView list_class;
    private ClassListAdapter classListAdapter;
    private LinearLayout mComprehensive,mSalesVolume,mPrice;
    private int meunType;
    private int meunSort;
    private String meunid;
    private int nowPage = 1;
    private List<ClassListBean.commoditys> mList;
    public String cmd = "getClassifyListInfo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        Intent intent = getIntent();
        meunid = intent.getStringExtra("meunid");
        mList = new ArrayList<>();
        meunType = 0;
        meunSort = 0;
        initView();
        getdata();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.iv_back);
        mBack.setOnClickListener(this);
        ediSearch = (EditText) findViewById(R.id.a_class_edt_search);
        ediSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    if (TextUtils.isEmpty(ediSearch.getText().toString().trim())){
                        ToastUtils.showMessageShort(ClassListActivity.this,"请输入关键词");
                    }else {
                        String mSearchKey = ediSearch.getText().toString().trim();
                        mList.clear();
                        getSearchData(mSearchKey);
                    }
                    return true;
                }
                return false;
            }
        });
        mSearch = (ImageView) findViewById(R.id.im_class_search);
        mSearch.setOnClickListener(this);
        mComprehensive = (LinearLayout) findViewById(R.id.linear_class_list_comprehensive);
        mComprehensive.setOnClickListener(this);
        mSalesVolume = (LinearLayout) findViewById(R.id.linear_class_list_sales_volume);
        mSalesVolume.setOnClickListener(this);
        mPrice = (LinearLayout) findViewById(R.id.linear_class_list_price);
        mPrice.setOnClickListener(this);
        tvComprehensive = (TextView) findViewById(R.id.text_comprehensive);
        tvSalesVolume = (TextView) findViewById(R.id.text_sales_volume);
        tvPrice = (TextView) findViewById(R.id.text_price);
        mDown02 = (ImageView) findViewById(R.id.iv_tag_down02);
        mDown03 = (ImageView) findViewById(R.id.iv_tag_down03);
        mUp02= (ImageView) findViewById(R.id.iv_tag_up02);
        mUp03= (ImageView) findViewById(R.id.iv_tag_up03);
        list_class = (PullToRefreshListView) findViewById(R.id.list_class);
        classListAdapter = new ClassListAdapter();
        list_class.setAdapter(classListAdapter);

        list_class.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                nowPage = 1;
                mList.clear();
                getdata();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                nowPage++;
                getdata();
            }
        });
        list_class.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                Toast.makeText(getApplication(), "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        //在刷新时允许继续滑动
        list_class.setScrollingWhileRefreshingEnabled(true);
        list_class.setMode(PullToRefreshBase.Mode.BOTH);

        list_class.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ClassListActivity.this,ShopDecActivity.class);
                intent.putExtra("rotateid",mList.get(position-1).commodityid);
                intent.putExtra("rotateIcon",mList.get(position-1).commodityIcon);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.linear_class_list_comprehensive:
                meunid = "";
                getdata();
                tvComprehensive.setTextColor(getResources().getColor(R.color.btn_login_color));
                tvSalesVolume.setTextColor(getResources().getColor(R.color.black));
                tvPrice.setTextColor(getResources().getColor(R.color.black));
                mDown02.setVisibility(View.VISIBLE);
                mDown03.setVisibility(View.VISIBLE);
                mUp02.setVisibility(View.GONE);
                mUp03.setVisibility(View.GONE);
                break;
            case R.id.linear_class_list_sales_volume:
                tvComprehensive.setTextColor(getResources().getColor(R.color.black));
                tvSalesVolume.setTextColor(getResources().getColor(R.color.btn_login_color));
                tvPrice.setTextColor(getResources().getColor(R.color.black));
                mDown03.setVisibility(View.VISIBLE);
                mUp03.setVisibility(View.GONE);
                if (meunType == 0){
                    if (meunSort == 0){
                        meunSort =1;
                        mUp02.setVisibility(View.VISIBLE);
                        mDown02.setVisibility(View.GONE);
                        tvSalesVolume.setTextColor(getResources().getColor(R.color.btn_login_color));
                        mList.clear();
                        getdata();
                    }else {
                        meunSort = 0;
                        mUp02.setVisibility(View.GONE);
                        mDown02.setVisibility(View.VISIBLE);
                        tvSalesVolume.setTextColor(getResources().getColor(R.color.black));
                        mList.clear();
                        getdata();
                    }
                }else {
                    meunType = 0;
                    if (meunSort == 0){
                        meunSort = 1;
                        mUp02.setVisibility(View.VISIBLE);
                        mDown02.setVisibility(View.GONE);
                        tvSalesVolume.setTextColor(getResources().getColor(R.color.btn_login_color));
                        mList.clear();
                        getdata();
                    }else {
                        mUp02.setVisibility(View.GONE);
                        mDown02.setVisibility(View.VISIBLE);
                        tvSalesVolume.setTextColor(getResources().getColor(R.color.black));
                        meunSort = 0;
                        mList.clear();
                        getdata();
                    }
                }
                break;
            case R.id.linear_class_list_price:
                tvComprehensive.setTextColor(getResources().getColor(R.color.black));
                tvSalesVolume.setTextColor(getResources().getColor(R.color.black));
                tvPrice.setTextColor(getResources().getColor(R.color.btn_login_color));
                mDown02.setVisibility(View.VISIBLE);
                mUp02.setVisibility(View.GONE);
                if (meunType == 0){
                    if (meunSort == 0){
                        meunSort =1;
                        mUp03.setVisibility(View.VISIBLE);
                        mDown03.setVisibility(View.GONE);
                        tvPrice.setTextColor(getResources().getColor(R.color.btn_login_color));
                        mList.clear();
                        getdata();
                    }else {
                        mUp03.setVisibility(View.GONE);
                        mDown03.setVisibility(View.VISIBLE);
                        tvPrice.setTextColor(getResources().getColor(R.color.black));
                        meunSort = 0;
                        mList.clear();
                        getdata();
                    }
                }else {
                    meunType = 0;
                    if (meunSort == 0){
                        mUp03.setVisibility(View.VISIBLE);
                        mDown03.setVisibility(View.GONE);
                        tvPrice.setTextColor(getResources().getColor(R.color.btn_login_color));
                        meunSort =1;
                        mList.clear();
                        getdata();
                    }else {
                        mUp03.setVisibility(View.GONE);
                        mDown03.setVisibility(View.VISIBLE);
                        tvPrice.setTextColor(getResources().getColor(R.color.black));
                        meunSort = 0;
                        mList.clear();
                        getdata();
                    }
                }
                break;

            case R.id.im_class_search:
                if (TextUtils.isEmpty(ediSearch.getText().toString().trim())){
                    ToastUtils.showMessageShort(ClassListActivity.this,"请输入关键词");
                }else {
                    String mSearchKey = ediSearch.getText().toString().trim();
                    mList.clear();
                    getSearchData(mSearchKey);
                }
                break;
        }
    }
    //请求参数
    private void getdata() {
        Map<String, String> params = new HashMap<>();
        ClassListBean classListBean = new ClassListBean(cmd,nowPage,meunid,meunType,meunSort);
        String json = new Gson().toJson(classListBean);
        params.put("json", json);
        Log.i("ShopDecActivity", "getdata: " + json);
        dialog1.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showMessageShort(context, e.getMessage());
                dialog1.dismiss();
                list_class.onRefreshComplete();
            }
            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                dialog1.dismiss();
                list_class.onRefreshComplete();
                ClassListBean classListBean = gson.fromJson(response, ClassListBean.class);
                if (classListBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, classListBean.resultNote);
                    return;
                }
                if (classListBean.totalPage < nowPage) {
                    ToastUtils.showMessageShort(context, "没有更多了");
                    return;
                }
                List<ClassListBean.commoditys> commodityslist = classListBean.commoditys;
                mList.addAll(commodityslist);
                classListAdapter.setShopBeanList(ClassListActivity.this,mList);
                list_class.setAdapter(classListAdapter);
                list_class.onRefreshComplete();
            }
        });
    }


    private void getSearchData(String mSearchKey) {
        Map<String, String> params = new HashMap<>();
        ClassListBean classListBean = new ClassListBean(cmd,nowPage,mSearchKey);
        String json = new Gson().toJson(classListBean);
        params.put("json", json);
        Log.i("ShopDecActivity", "getdata: " + json);
        dialog1.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showMessageShort(context, e.getMessage());
                dialog1.dismiss();
                list_class.onRefreshComplete();
            }
            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                dialog1.dismiss();
                list_class.onRefreshComplete();
                ClassListBean classListBean = gson.fromJson(response, ClassListBean.class);
                if (classListBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, classListBean.resultNote);
                    return;
                }
                if (classListBean.totalPage < nowPage) {
                    ToastUtils.showMessageShort(context, "没有更多了");
                    return;
                }
                List<ClassListBean.commoditys> commodityslist = classListBean.commoditys;
                mList.addAll(commodityslist);
                classListAdapter.setShopBeanList(ClassListActivity.this,mList);
                list_class.setAdapter(classListAdapter);
                list_class.onRefreshComplete();
            }
        });
    }
}
