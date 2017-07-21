package com.lixin.luyuancarcity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.AutomobileInsuranceAdapter;
import com.lixin.luyuancarcity.bean.InsuranceCompanyResultBean;
import java.util.ArrayList;

/**
 * Created by 小火
 * Create time on  2017/6/27
 * My mailbox is 1403241630@qq.com
 *
 * 车险
 */

public class AutomobileInsuranceActivity extends BaseActivity{
    private ArrayList<? extends InsuranceCompanyResultBean.insuranceCompanyList> mList;
    private ListView auto_ins_list;
    private AutomobileInsuranceAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_insurance);
        Intent intent = getIntent();
        mList = intent.getParcelableArrayListExtra("CompanyList");
        setTitleText("车险",false);
        hideBack(5);
        initView();
    }

    private void initView() {
        auto_ins_list = (ListView) findViewById(R.id.auto_ins_list);
        mAdapter = new AutomobileInsuranceAdapter(context,mList);
        auto_ins_list.setAdapter(mAdapter);
        auto_ins_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AutomobileInsuranceActivity.this,AutoInsuranceTypeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CompanyName",mList.get(position).getCompanyName());
                bundle.putString("CompanyId",mList.get(position).getCompanyid());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
