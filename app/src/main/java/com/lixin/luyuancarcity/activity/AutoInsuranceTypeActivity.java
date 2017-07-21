package com.lixin.luyuancarcity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.AutoInsuranceTypeAdapter;
import com.lixin.luyuancarcity.bean.AutoInsuranceTypeBean;
import com.lixin.luyuancarcity.bean.RequestSecondBean;
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
 * Create time on  2017/6/27
 * My mailbox is 1403241630@qq.com
 */

public class AutoInsuranceTypeActivity extends BaseActivity{
    private String title;
    private String CompanyId;
    private List<AutoInsuranceTypeBean.emergencys> mList;
    private ListView auto_ins_type_list;
    private AutoInsuranceTypeAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_insurance_type);
        Intent intent = getIntent();
        title = intent.getStringExtra("CompanyName");
        CompanyId = intent.getStringExtra("CompanyId");
        setTitleText(title,false);
        hideBack(5);
        mList = new ArrayList<>();
        initView();
//        getdata();
    }

    private void initView() {
        auto_ins_type_list = (ListView) findViewById(R.id.auto_ins_type_list);
        mAdapter = new AutoInsuranceTypeAdapter();
        auto_ins_type_list.setAdapter(mAdapter);
        auto_ins_type_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AutoInsuranceTypeActivity.this,InsuranceTypeDecActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("emergencyid",mList.get(position).getEmergencyid());
                bundle.putString("title",mList.get(position).getEmergencyTitle());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    private void getdata() {
        Map<String, String> params = new HashMap<>();
        RequestSecondBean requestSecondBean = new RequestSecondBean("getCarInsuranceTypeList",CompanyId);
        String json = new Gson().toJson(requestSecondBean);
        params.put("json", json);
        Log.i("ShopDecActivity", "getdata: " + json);
        dialog1.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showMessageShort(context, e.getMessage());
                dialog1.dismiss();
            }
            @Override
            public void onResponse(String response, int id) {
                Log.i("ShopDecActivity", "onResponse: " + response.toString());
                Gson gson = new Gson();
                dialog1.dismiss();
                AutoInsuranceTypeBean autoInsuranceTypeBean = gson.fromJson(response, AutoInsuranceTypeBean.class);
                if (autoInsuranceTypeBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, autoInsuranceTypeBean.resultNote);
                    return;
                }
                List<AutoInsuranceTypeBean.emergencys> financialList = autoInsuranceTypeBean.emergencys;
                mList.addAll(financialList);
                mAdapter.setAutoInsuranceType(context,mList);
                auto_ins_type_list.setAdapter(mAdapter);
            }
        });
    }

}
