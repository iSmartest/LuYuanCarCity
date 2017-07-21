package com.lixin.luyuancarcity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.activity.CarMallActivity;
import com.lixin.luyuancarcity.activity.ChangeTiresActivity;
import com.lixin.luyuancarcity.activity.CheckViolationActivity;
import com.lixin.luyuancarcity.activity.ChooseModelsActivity;
import com.lixin.luyuancarcity.activity.LowPriceAutoInsuranceActivity;
import com.lixin.luyuancarcity.activity.MaintenanceActivity;
import com.lixin.luyuancarcity.activity.MoreActivity;
import com.lixin.luyuancarcity.activity.OwnerCreditCardActivity;
import com.lixin.luyuancarcity.activity.OwnerLoanActivity;
import com.lixin.luyuancarcity.activity.RaiseMoneyToKeepACarActivity;
import com.lixin.luyuancarcity.activity.RefuelingRechargeActivity;
import com.lixin.luyuancarcity.activity.SpecialCarWashActivity;
import com.lixin.luyuancarcity.adapter.HomeAdapter;
import com.lixin.luyuancarcity.bean.HomeResultBean;
import com.lixin.luyuancarcity.bean.RequestBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.utils.ImageManager;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.SPUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 小火
 * Create time on  2017/6/23
 * My mailbox is 1403241630@qq.com
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener{
    private View headView,view;
    private PullToRefreshListView pullToRefreshListView;
    private ListView home_list;
    private HomeAdapter mAdapter;
    private ImageView[] imageView;
    private TextView[] mTextView;
    private ImageView mAddCar;
    private TextView mSettingCar;
    private String uid;
    private String myCar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frgment_home,container,false);
        uid = (String) SPUtils.get(getActivity(),"uid","");
        myCar = (String) SPUtils.get(getActivity(),"myCar","");
        initView();
        return view;
    }
    private void initView() {
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.home_list);
//        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mAddCar = (ImageView) view.findViewById(R.id.home_add_your_car);
        mSettingCar = (TextView) view.findViewById(R.id.text_setting_your_car);
        if (TextUtils.isEmpty(uid)&&TextUtils.isEmpty(myCar)){
            mSettingCar.setText("设置您的车型");
        }else mSettingCar.setText(myCar);
        mAddCar.setOnClickListener(this);
        home_list = (ListView) pullToRefreshListView.getRefreshableView();

        getHeadView();
        if (headView != null)
            home_list.addHeaderView(headView);
        mAdapter = new HomeAdapter();
        home_list.setAdapter(mAdapter);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
//                mList.clear();
//                getdata();
                getcomplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                mList.clear();
//                getdata();
                getcomplete();
            }
        });
        pullToRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                Toast.makeText(getActivity(), "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        //在刷新时允许继续滑动
//        pullToRefreshListView.setScrollingWhileRefreshingEnabled(true);

        home_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void getHeadView() {
        headView = LayoutInflater.from(getActivity()).inflate(R.layout.include_home_head, null);
        imageView = new ImageView[23];
        imageView[0] = (ImageView) headView.findViewById(R.id.image_home_head_00);
        imageView[1] = (ImageView) headView.findViewById(R.id.image_home_head_01);
        imageView[2] = (ImageView) headView.findViewById(R.id.image_home_head_02);
        imageView[3] = (ImageView) headView.findViewById(R.id.image_home_head_03);
        imageView[4] = (ImageView) headView.findViewById(R.id.image_home_head_04);
        imageView[5] = (ImageView) headView.findViewById(R.id.image_home_head_05);
        imageView[6] = (ImageView) headView.findViewById(R.id.image_home_head_06);
        imageView[7] = (ImageView) headView.findViewById(R.id.image_home_head_07);
        imageView[8] = (ImageView) headView.findViewById(R.id.image_home_head_08);
        imageView[9] = (ImageView) headView.findViewById(R.id.image_home_head_09);
        imageView[10] = (ImageView) headView.findViewById(R.id.image_home_head_10);
        imageView[11] = (ImageView) headView.findViewById(R.id.image_home_head_11);
        imageView[12] = (ImageView) headView.findViewById(R.id.image_home_head_12);
        imageView[13] = (ImageView) headView.findViewById(R.id.image_home_head_13);
        imageView[14] = (ImageView) headView.findViewById(R.id.image_home_head_14);
        imageView[15] = (ImageView) headView.findViewById(R.id.image_home_head_15);
        imageView[16] = (ImageView) headView.findViewById(R.id.image_home_head_16);
        imageView[17] = (ImageView) headView.findViewById(R.id.image_home_head_17);
        imageView[18] = (ImageView) headView.findViewById(R.id.image_home_head_18);
        imageView[19] = (ImageView) headView.findViewById(R.id.image_home_head_19);
        imageView[20] = (ImageView) headView.findViewById(R.id.image_home_head_20);
        imageView[21] = (ImageView) headView.findViewById(R.id.image_home_head_21);
        imageView[22] = (ImageView) headView.findViewById(R.id.image_home_head_22);
        mTextView = new TextView[25];
        mTextView[0] = (TextView) headView.findViewById(R.id.text_home_head_00);
        mTextView[1] = (TextView) headView.findViewById(R.id.text_home_head_01);
        mTextView[2] = (TextView) headView.findViewById(R.id.text_home_head_02);
        mTextView[3] = (TextView) headView.findViewById(R.id.text_home_head_03);
        mTextView[4] = (TextView) headView.findViewById(R.id.text_home_head_04);
        mTextView[5] = (TextView) headView.findViewById(R.id.text_home_head_05);
        mTextView[6] = (TextView) headView.findViewById(R.id.text_home_head_06);
        mTextView[7] = (TextView) headView.findViewById(R.id.text_home_head_07);
        mTextView[8] = (TextView) headView.findViewById(R.id.text_home_head_08);
        mTextView[9] = (TextView) headView.findViewById(R.id.text_home_head_09);
        mTextView[10] = (TextView) headView.findViewById(R.id.text_home_head_10);
        mTextView[11] = (TextView) headView.findViewById(R.id.text_home_head_11);
        mTextView[12] = (TextView) headView.findViewById(R.id.text_home_head_12);
        mTextView[13] = (TextView) headView.findViewById(R.id.text_home_head_13);
        mTextView[14] = (TextView) headView.findViewById(R.id.text_home_head_14);
        mTextView[15] = (TextView) headView.findViewById(R.id.text_home_head_15);
        mTextView[16] = (TextView) headView.findViewById(R.id.text_home_head_16);
        mTextView[17] = (TextView) headView.findViewById(R.id.text_home_head_17);
        mTextView[18] = (TextView) headView.findViewById(R.id.text_home_head_18);
        mTextView[19] = (TextView) headView.findViewById(R.id.text_home_head_19);
        mTextView[20] = (TextView) headView.findViewById(R.id.text_home_head_20);
        mTextView[21] = (TextView) headView.findViewById(R.id.text_home_head_21);
        mTextView[22] = (TextView) headView.findViewById(R.id.text_home_head_22);
        mTextView[23] = (TextView) headView.findViewById(R.id.text_home_head_23);
        mTextView[24] = (TextView) headView.findViewById(R.id.text_home_head_24);

        initData();
    }
    private void initData() {
        String[] bigBGs = new String[]{
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://img1.juimg.com/151201/330780-1512010Q13890.jpg",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png",
                "http://exchangedownloads.smarttech.com/public/content/ea/ea66f64d-be6a-40f8-8232-766f74f1027d/previews/medium/0001.png"


        };
        String[] title = new String[]{
            "0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","25"
        };

        for (int i = 0; i < imageView.length; i++) {
            String img = bigBGs[i];
            ImageManager.imageLoader.displayImage(img, imageView[i], ImageManager.options3);
            imageView[i].setOnClickListener(this);
            imageView[i].setId(i);
        }

        for (int i = 0; i < mTextView.length; i++) {
            mTextView[i].setText(title[i]);
        }
    }
    private void getdata() {
        Map<String, String> params = new HashMap<>();
        RequestBean requestBean = new RequestBean("getHomeList");
        String json = new Gson().toJson(requestBean);
        params.put("json", json);
        Log.i("ShopDecActivity", "getdata: " + json);
        dialog.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showMessageShort(context, e.getMessage());
                dialog.dismiss();
            }
            @Override
            public void onResponse(String response, int id) {
                Log.i("ShopDecActivity", "onResponse: " + response.toString());
                Gson gson = new Gson();
                dialog.dismiss();
                HomeResultBean homeResultBean = gson.fromJson(response, HomeResultBean.class);
                if (homeResultBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, homeResultBean.resultNote);
                    return;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_add_your_car:
                startActivity(new Intent(getActivity(),ChooseModelsActivity.class));
                break;
            case 0://低价车险
                startActivity(new Intent(getActivity(),LowPriceAutoInsuranceActivity.class));
                break;
            case 1:// 新车团购
                startActivity(new Intent(getActivity(),ChooseModelsActivity.class));
                break;
            case 2://维修保养
                startActivity(new Intent(getActivity(),MaintenanceActivity.class));
                break;
            case 3://加油充值
                startActivity(new Intent(getActivity(),RefuelingRechargeActivity.class));
                break;
            case 4://换轮胎
                startActivity(new Intent(getActivity(),ChangeTiresActivity.class));
                break;
            case 5://车品商城
                startActivity(new Intent(getActivity(),CarMallActivity.class));
                break;
            case 6://查违章
                startActivity(new Intent(getActivity(),CheckViolationActivity.class));
                break;
            case 7://特价洗车
                startActivity(new Intent(getActivity(),SpecialCarWashActivity.class));
                break;
            case 8://优惠券
                break;
            case 9://车主贷款
                startActivity(new Intent(getActivity(),OwnerLoanActivity.class));
                break;
            case 10://车主信用卡
                startActivity(new Intent(getActivity(),OwnerCreditCardActivity.class));
                break;
            case 11://赚钱养车
                startActivity(new Intent(getActivity(),RaiseMoneyToKeepACarActivity.class));
                break;
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 21:
                startActivity(new Intent(getActivity(),CarMallActivity.class));
                break;
            case 22:
                startActivity(new Intent(getActivity(),MoreActivity.class));
                break;

        }
    }

    public void getcomplete() {
        pullToRefreshListView.onRefreshComplete();
    }
}
