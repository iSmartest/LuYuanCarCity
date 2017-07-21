package com.lixin.luyuancarcity.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.AddAddressBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.dialog.ChangeAddressPopwindow;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.SPUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 小火
 * Create time on  2017/7/20
 * My mailbox is 1403241630@qq.com
 */

public class AddAddressActivity extends BaseActivity{
    private EditText mReceiverName,mReceiverPhone,mReceiverPostcode,mReceiverDetailAddress;
    private TextView mReceiverAddress,mSubmit;
    private CheckBox mChoose;
    private String cmd = "addAddress";
    private String uid;
    private int isDefault;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_add_address);
        uid = (String) SPUtils.get(context,"uid","");
        hideBack(5);
        setTitleText("添加发货地址",false);
        initView();
    }

    private void initView() {
        mReceiverName = (EditText) findViewById(R.id.text_receiver_name);
        mReceiverPhone = (EditText) findViewById(R.id.text_receiver_phone);
        mReceiverPostcode = (EditText) findViewById(R.id.text_receiver_postcode);
        mReceiverDetailAddress = (EditText) findViewById(R.id.edi_add_receiver_detail_address);
        mReceiverAddress = (TextView) findViewById(R.id.text_receiver_address);
        mReceiverAddress.setOnClickListener(this);
        mSubmit = (TextView) findViewById(R.id.text_add_address_submit);
        mSubmit.setOnClickListener(this);
        mChoose = (CheckBox) findViewById(R.id.ck_add_address_chose);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_receiver_address:
                ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(AddAddressActivity.this);
                mChangeAddressPopwindow.setAddress("河南", "郑州", "管城回族区");
                mChangeAddressPopwindow.showAtLocation(mReceiverAddress, Gravity.BOTTOM, 0, 0);
                mChangeAddressPopwindow.setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {
                    @Override
                    public void onClick(String province, String city, String area) {
                        Toast.makeText(AddAddressActivity.this, province + "-" + city + "-" + area, Toast.LENGTH_LONG).show();
                        mReceiverAddress.setText(province + city + area);
                    }
                });
                break;
            case R.id.text_add_address_submit:
                submit();
                break;
        }
    }

    private void submit() {
        String name = mReceiverName.getText().toString().trim();
        String tel = mReceiverPhone.getText().toString().trim();
        String postCode = mReceiverPostcode.getText().toString().trim();
        String address = mReceiverAddress.getText().toString().trim();
        String detailAddress = mReceiverDetailAddress.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            ToastUtils.showMessageShort(context,"您输入的收货人姓名");
        } if (TextUtils.isEmpty(tel)){
            ToastUtils.showMessageShort(context,"您输入的收货人联系电话");
        } if (TextUtils.isEmpty(postCode)){
            ToastUtils.showMessageShort(context,"您输入的收货人邮政编码");
        } if (TextUtils.isEmpty(address)){
            ToastUtils.showMessageShort(context,"您输入的收货人地址");
        }if (TextUtils.isEmpty(detailAddress)){
            ToastUtils.showMessageShort(context,"您输入的收货人详细地址");
        }if (mChoose.isChecked()){
            isDefault = 1;
        }else {
            isDefault = 0;
        }
        getAddAddress(name,tel,postCode,address,detailAddress,isDefault);
    }

    private void getAddAddress(String name, String tel, String postCode, String address, String detailAddress, int isDefault) {
        Map<String,String> params =  new HashMap<>();
        AddAddressBean addAddressBean = new AddAddressBean(cmd,name,uid,tel,postCode,address,detailAddress,isDefault);
        String json = new Gson().toJson(addAddressBean);
        params.put("json",json);
        dialog1.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showMessageShort(context,e.getMessage());
                dialog1.dismiss();
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                dialog1.dismiss();
                AddAddressBean addressBean = gson.fromJson(response,AddAddressBean.class);
                if (addressBean.result.equals("1")){
                    ToastUtils.showMessageShort(context,addressBean.resultNote);
                }
                ToastUtils.showMessageShort(context,"地址保存成功!");
            }
        });

    }
}
