package com.lixin.luyuancarcity.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.OwnerLoanBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.dialog.ChangeAddressPopwindow;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 小火
 * Create time on  2017/7/13
 * My mailbox is 1403241630@qq.com
 */

public class OwnerLoanActivity extends BaseActivity{
    private TextView mLocation;
    private EditText mYourName,mYourIdCardNumber,mYourPhone,mLoanAmount,mLoanAmountYear,
            mLoanAmountMouth,mAlipay,mWX,mCardBank,mBankCardAccountNumber,mDetailedAddress;
    private ImageView mFrontOfIdCard,mBehindOfIdCard,mHandheldIdCard;
    private CheckBox mChoose;
    private Button mSubmit;
    private String certificateIdCardUp,certificateIdCardDown,certificateIdCardHand;
    private String cmd = "carerLoanCer";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_loan);
        hideBack(5);
        setTitleText("车主贷款",false);
        initView();
    }

    private void initView() {
        mLocation = (TextView) findViewById(R.id.text_owner_loan_location);
        mLocation.setOnClickListener(this);
        mYourName = (EditText) findViewById(R.id.text_owner_loan_your_name);
        mYourIdCardNumber = (EditText) findViewById(R.id.text_owner_loan_your_id_card_number);
        mYourPhone = (EditText) findViewById(R.id.text_owner_loan_your_phone);
        mLoanAmount = (EditText) findViewById(R.id.text_owner_loan_amount);
        mLoanAmountYear = (EditText) findViewById(R.id.text_owner_loan_amount_year);
        mLoanAmountMouth = (EditText) findViewById(R.id.text_owner_loan_amount_mouth);
        mAlipay = (EditText) findViewById(R.id.text_owner_loan_alipay);
        mWX = (EditText) findViewById(R.id.text_owner_loan_wx);
        mCardBank = (EditText) findViewById(R.id.text_owner_loan_bank_card_bank);
        mBankCardAccountNumber = (EditText) findViewById(R.id.text_owner_loan_bank_card_account_number);
        mDetailedAddress = (EditText) findViewById(R.id.text_owner_loan_detailed_address);
        mFrontOfIdCard = (ImageView) findViewById(R.id.iv_front_of_id_card);
        mBehindOfIdCard = (ImageView) findViewById(R.id.iv_behind_of_id_card);
        mHandheldIdCard = (ImageView) findViewById(R.id.iv_handheld_id_card);
        mChoose = (CheckBox) findViewById(R.id.ck_shop_cart_chose);
        mSubmit = (Button) findViewById(R.id.btn_owner_loan_submit);
        mSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_owner_loan_location:
                ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(OwnerLoanActivity.this);
                mChangeAddressPopwindow.setAddress("河南", "郑州", "管城回族区");
                mChangeAddressPopwindow.showAtLocation(mLocation, Gravity.BOTTOM, 0, 0);
                mChangeAddressPopwindow.setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {
                    @Override
                    public void onClick(String province, String city, String area) {
                        // TODO Auto-generated method stub
                        Toast.makeText(OwnerLoanActivity.this, province + "-" + city + "-" + area, Toast.LENGTH_LONG).show();
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
        String certificateNumber = mYourIdCardNumber.getText().toString().trim();
        String certificateTel = mYourPhone.getText().toString().trim();
        String certificateLoanMoney = mLoanAmount.getText().toString().trim();
        String certificateYear = mLoanAmountYear.getText().toString().trim();
        String certificateMouth = mLoanAmountMouth.getText().toString().trim();
        String certificateZhifubao = mAlipay.getText().toString().trim();
        String certificateWeixin = mWX.getText().toString().trim();
        String certificateBackCard = mBankCardAccountNumber.getText().toString().trim();
        String certificateBack = mCardBank.getText().toString().trim();
        String certificateDetailAddress = mDetailedAddress.getText().toString().trim();
        String certificateAddress = mLocation.getText().toString().trim();
        if (TextUtils.isEmpty(certificateName)){
            ToastUtils.showMessageShort(OwnerLoanActivity.this,"请输入您的姓名");
        } if (TextUtils.isEmpty(certificateNumber)){
            ToastUtils.showMessageShort(OwnerLoanActivity.this,"请输入您的身份证号码");
        } if (TextUtils.isEmpty(certificateTel)){
            ToastUtils.showMessageShort(OwnerLoanActivity.this,"请输入您的联系方式");
        } if (TextUtils.isEmpty(certificateLoanMoney)){
            ToastUtils.showMessageShort(OwnerLoanActivity.this,"请输入您的贷款金额");
        } if (TextUtils.isEmpty(certificateYear)){
            ToastUtils.showMessageShort(OwnerLoanActivity.this,"请输入您的贷款期限年份");
        } if (TextUtils.isEmpty(certificateMouth)){
            ToastUtils.showMessageShort(OwnerLoanActivity.this,"请输入您的贷款期限月份");
        } if (TextUtils.isEmpty(certificateZhifubao)){
            ToastUtils.showMessageShort(OwnerLoanActivity.this,"请输入您的支付宝账号");
        } if (TextUtils.isEmpty(certificateWeixin)){
            ToastUtils.showMessageShort(OwnerLoanActivity.this,"请输入您的微信账号");
        } if (TextUtils.isEmpty(certificateBackCard)){
            ToastUtils.showMessageShort(OwnerLoanActivity.this,"请输入您的银行卡账号");
        } if (TextUtils.isEmpty(certificateBack)){
            ToastUtils.showMessageShort(OwnerLoanActivity.this,"请输入您的银行卡开户行");
        } if (TextUtils.isEmpty(certificateDetailAddress)){
            ToastUtils.showMessageShort(OwnerLoanActivity.this,"请输入您的详细地址");
        } if (TextUtils.isEmpty(certificateAddress)){
            ToastUtils.showMessageShort(OwnerLoanActivity.this,"请选择您的所在地");
        } if (mChoose.isChecked()){
            getSubmitData(certificateName,certificateNumber,certificateTel,certificateLoanMoney,certificateYear
            ,certificateMouth,certificateZhifubao,certificateWeixin,certificateBackCard,certificateBack,certificateDetailAddress
            ,certificateAddress);
        }else {
            ToastUtils.showMessageShort(OwnerLoanActivity.this,"您未同意服务协议，不能申请");
        }
    }

    private void getSubmitData(String certificateName, String certificateNumber, String certificateTel,
                               String certificateLoanMoney, String certificateYear, String certificateMouth,
                               String certificateZhifubao, String certificateWeixin, String certificateBackCard,
                               String certificateBack, String certificateDetailAddress, String certificateAddress)
    {
        Map<String,String> params =  new HashMap<>();
        OwnerLoanBean ownerLoanBean = new OwnerLoanBean(cmd,certificateName,certificateNumber,certificateTel,certificateLoanMoney,certificateYear
        ,certificateMouth,certificateZhifubao,certificateWeixin,certificateBackCard,certificateBack,certificateAddress,certificateDetailAddress
        ,certificateIdCardUp,certificateIdCardDown,certificateIdCardHand);
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
                OwnerLoanBean ownerLoanBean = gson.fromJson(response,OwnerLoanBean.class);
                if (ownerLoanBean.result.equals("1")){
                    ToastUtils.showMessageShort(context,ownerLoanBean.getResultNote());
                }
                ToastUtils.showMessageShort(context,"您的申请认证已提交!");
            }
        });

    }
}
