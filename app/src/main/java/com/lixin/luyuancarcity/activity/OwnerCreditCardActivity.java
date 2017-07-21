package com.lixin.luyuancarcity.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.OwnerCreditCardBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.dialog.ChangeAddressPopwindow;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 小火
 * Create time on  2017/7/14
 * My mailbox is 1403241630@qq.com
 */

public class OwnerCreditCardActivity extends BaseActivity{
    private TextView mLocation;
    private EditText mYourName,mYourIdCardNumber,mWorkUnit,mCardBank,mDetailedAddress;
    private ImageView mFrontOfIdCard,mBehindOfIdCard,mHandheldIdCard;
    private CheckBox mChoose;
    private Button mSubmit;
    private String certificateIdCardUp,certificateIdCardDown,certificateIdCardHand;
    private String cmd = "carerCreditCard";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_credit_card);
        hideBack(5);
        setTitleText("车主贷款",false);
        initView();
    }

    private void initView() {
        mLocation = (TextView) findViewById(R.id.text_owner_credit_card_location);
        mLocation.setOnClickListener(this);
        mYourName = (EditText) findViewById(R.id.text_owner_credit_card_your_name);
        mYourIdCardNumber = (EditText) findViewById(R.id.text_owner_credit_card_your_id_card_number);
        mWorkUnit = (EditText) findViewById(R.id.text_owner_credit_card_your_work_unit);
        mCardBank = (EditText) findViewById(R.id.text_owner_credit_card_bank);
        mDetailedAddress = (EditText) findViewById(R.id.text_owner_credit_card_detailed_address);
        mFrontOfIdCard = (ImageView) findViewById(R.id.iv_credit_card_front_of_id_card);
        mBehindOfIdCard = (ImageView) findViewById(R.id.iv_credit_card_behind_of_id_card);
        mHandheldIdCard = (ImageView) findViewById(R.id.iv_credit_card_handheld_id_card);
        mChoose = (CheckBox) findViewById(R.id.ck_shop_cart_choose);
        mSubmit = (Button) findViewById(R.id.btn_owner_credit_card_submit);
        mSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_owner_loan_location:
                ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(OwnerCreditCardActivity.this);
                mChangeAddressPopwindow.setAddress("河南", "郑州", "管城回族区");
                mChangeAddressPopwindow.showAtLocation(mLocation, Gravity.BOTTOM, 0, 0);
                mChangeAddressPopwindow.setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {
                    @Override
                    public void onClick(String province, String city, String area) {
                        // TODO Auto-generated method stub
                        Toast.makeText(OwnerCreditCardActivity.this, province + "-" + city + "-" + area, Toast.LENGTH_LONG).show();
                        mLocation.setText(province + city + area);

                    }
                });
                break;
            case R.id.btn_owner_loan_submit:
                submit();
                break;
        }
    }

    private void submit() {
        String certificateName = mYourName.getText().toString().trim();
        String certificateConpany = mWorkUnit.getText().toString().trim();
        String certificateAddress = mLocation.getText().toString().trim();
        String certificateDetailAddress = mDetailedAddress.getText().toString().trim();
        String certificateNumber = mYourIdCardNumber.getText().toString().trim();
        String creditCardType = mCardBank.getText().toString().trim();
        if (TextUtils.isEmpty(certificateName)){
            ToastUtils.showMessageShort(OwnerCreditCardActivity.this,"请输入您的姓名");
        } if (TextUtils.isEmpty(certificateNumber)){
            ToastUtils.showMessageShort(OwnerCreditCardActivity.this,"请输入您的身份证号码");
        } if (TextUtils.isEmpty(certificateConpany)){
            ToastUtils.showMessageShort(OwnerCreditCardActivity.this,"请输入您的工作单位");
        } if (TextUtils.isEmpty(certificateDetailAddress)){
            ToastUtils.showMessageShort(OwnerCreditCardActivity.this,"请输入您公司的详细地址");
        } if (TextUtils.isEmpty(certificateAddress)){
            ToastUtils.showMessageShort(OwnerCreditCardActivity.this,"请选择您的所在地");
        }if (TextUtils.isEmpty(creditCardType)){
            ToastUtils.showMessageShort(OwnerCreditCardActivity.this,"请选择您的信用卡机构");
        } if (mChoose.isChecked()){
            getSubmitData(certificateName,certificateNumber,certificateConpany,creditCardType,certificateDetailAddress
                    ,certificateAddress);
        }else {
            ToastUtils.showMessageShort(OwnerCreditCardActivity.this,"您未同意服务协议，不能申请");
        }
    }

    private void getSubmitData(String certificateName, String certificateNumber, String certificateConpany, String creditCardType, String certificateDetailAddress, String certificateAddress) {
        Map<String,String> params =  new HashMap<>();
        OwnerCreditCardBean ownerLoanBean = new OwnerCreditCardBean(cmd,certificateName,certificateConpany,certificateAddress,
                certificateDetailAddress,certificateNumber,creditCardType,certificateIdCardUp,certificateIdCardDown,certificateIdCardHand);
        String json = new Gson().toJson(ownerLoanBean);
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
                OwnerCreditCardBean ownerCreditCardBean = gson.fromJson(response,OwnerCreditCardBean.class);
                if (ownerCreditCardBean.result.equals("1")){
                    ToastUtils.showMessageShort(context,ownerCreditCardBean.getResultNote());
                }
                ToastUtils.showMessageShort(context,"您的申请认证已提交!");
            }
        });

    }

}
