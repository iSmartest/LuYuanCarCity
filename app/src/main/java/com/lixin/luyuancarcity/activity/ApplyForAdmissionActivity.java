package com.lixin.luyuancarcity.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.ApplyBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.dialog.ChangeAddressPopwindow;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 小火
 * Create time on  2017/7/17
 * My mailbox is 1403241630@qq.com
 */

public class ApplyForAdmissionActivity extends BaseActivity{
    private EditText mYourName,mCompanyName,mLegalPersonName,mDetailedAddress,mPhoneNumber,mChatQQ,mExplain;
    private TextView tvCategoryOne,tvCategoryTwo,mLocation;
    private LinearLayout mCategoryOne,mCategoryTwo;
    private ImageView mFront,mBehind,mHandheld,mCertificatesOne,mCertificatesTwo,mCertificatesThree;
    private CheckBox mChoose;
    private Button mSubmit;
    private String cmd = "applyCompany";
    private String certificateIdCardUp,certificateIdCardDown,certificateIdCardHand;
    private List<String> certificateCertificateImages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for_admission);
        hideBack(5);
        setTitleText("申请成为商家",false);
        initView();
    }

    private void initView() {
        mYourName = (EditText) findViewById(R.id.text_apply_your_name);
        mCompanyName = (EditText) findViewById(R.id.text_apply_company_name);
        mLegalPersonName = (EditText) findViewById(R.id.text_apply_legal_person_name);
        mDetailedAddress = (EditText) findViewById(R.id.text_apply_detailed_address);
        mPhoneNumber = (EditText) findViewById(R.id.text_apply_phone_number);
        mChatQQ = (EditText) findViewById(R.id.text_apply_chat_qq);
        mExplain = (EditText) findViewById(R.id.edi_apply_store_explain);
        tvCategoryOne = (TextView) findViewById(R.id.text_apply_category_one);
        tvCategoryTwo = (TextView) findViewById(R.id.text_apply_category_two);
        mLocation = (TextView) findViewById(R.id.text_apply_location);
        mLocation.setOnClickListener(this);
        mCategoryOne = (LinearLayout) findViewById(R.id.linear_apply_category_one);
        mCategoryOne.setOnClickListener(this);
        mCategoryTwo = (LinearLayout) findViewById(R.id.linear_text_apply_category_two);
        mCategoryTwo.setOnClickListener(this);
        mFront = (ImageView) findViewById(R.id.iv_apply_front_of_id_card);
        mBehind = (ImageView) findViewById(R.id.iv_apply_behind_of_id_card);
        mHandheld = (ImageView) findViewById(R.id.iv_apply_handheld_id_card);
        mCertificatesOne = (ImageView) findViewById(R.id.iv_apply_certificates_one);
        mCertificatesTwo = (ImageView) findViewById(R.id.iv_apply_certificates_two);
        mCertificatesThree= (ImageView) findViewById(R.id.iv_apply_certificates_three);
        mChoose = (CheckBox) findViewById(R.id.ck_apply_chose);
        mSubmit = (Button) findViewById(R.id.btn_apply_submit);
        mSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_apply_location:
                ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(ApplyForAdmissionActivity.this);
                mChangeAddressPopwindow.setAddress("河南", "郑州", "管城回族区");
                mChangeAddressPopwindow.showAtLocation(mLocation, Gravity.BOTTOM, 0, 0);
                mChangeAddressPopwindow.setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {
                    @Override
                    public void onClick(String province, String city, String area) {
                        // TODO Auto-generated method stub
                        Toast.makeText(ApplyForAdmissionActivity.this, province + "-" + city + "-" + area, Toast.LENGTH_LONG).show();
                        mLocation.setText(province + city + area);

                    }
                });
                break;
            case R.id.linear_apply_category_one:
                break;
            case R.id.linear_text_apply_category_two:
                break;
            case R.id.btn_apply_submit:
                submit();
                break;
        }
    }

    private void submit() {

        String certificateName = mYourName.getText().toString().trim();
        String certificateCompany = mCompanyName.getText().toString().trim();
        String certificateBusiness1 = tvCategoryOne.getText().toString().trim();
        String certificateLegalPerson = mLegalPersonName.getText().toString().trim();
        String certificateCompanyDetailAddress = mDetailedAddress.getText().toString().trim();
        String certificateAddress = mLocation.getText().toString().trim();
        String certificateTel = mPhoneNumber.getText().toString().trim();
        String certificateWeixinorqq = mChatQQ.getText().toString().trim();
        String certificateSupplement = mExplain.getText().toString().trim();



        if (TextUtils.isEmpty(certificateName)){
            ToastUtils.showMessageShort(ApplyForAdmissionActivity.this,"请输入您的姓名");
        } if (TextUtils.isEmpty(certificateCompany)){
            ToastUtils.showMessageShort(ApplyForAdmissionActivity.this,"请输入您的公司名称");
        } if (TextUtils.isEmpty(certificateTel)){
            ToastUtils.showMessageShort(ApplyForAdmissionActivity.this,"请输入您的联系方式");
        } if (TextUtils.isEmpty(certificateBusiness1)){
            ToastUtils.showMessageShort(ApplyForAdmissionActivity.this,"请输入您的经营类别");
        } if (TextUtils.isEmpty(certificateLegalPerson)){
            ToastUtils.showMessageShort(ApplyForAdmissionActivity.this,"请输入法人的姓名");
        } if (TextUtils.isEmpty(certificateWeixinorqq)){
            ToastUtils.showMessageShort(ApplyForAdmissionActivity.this,"请输入您的微信号或者QQ号码");
        } if (TextUtils.isEmpty(certificateSupplement)){
            ToastUtils.showMessageShort(ApplyForAdmissionActivity.this,"请输入您的店铺详细说明");
        } if (TextUtils.isEmpty(certificateCompanyDetailAddress)){
            ToastUtils.showMessageShort(ApplyForAdmissionActivity.this,"请输入您的详细地址");
        } if (TextUtils.isEmpty(certificateAddress)){
            ToastUtils.showMessageShort(ApplyForAdmissionActivity.this,"请选择您的所在地");
        } if (mChoose.isChecked()){
            getSubmitData(certificateName,certificateCompany,certificateBusiness1,certificateLegalPerson,certificateCompanyDetailAddress
                    ,certificateAddress,certificateTel,certificateWeixinorqq,certificateSupplement);
        }else {
            ToastUtils.showMessageShort(ApplyForAdmissionActivity.this,"您未同意服务协议，不能申请");
        }
    }

    private void getSubmitData(String certificateName, String certificateCompany, String certificateBusiness1, String certificateLegalPerson, String certificateCompanyDetailAddress, String certificateAddress, String certificateTel, String certificateWeixinorqq, String certificateSupplement) {
        Map<String,String> params =  new HashMap<>();
        ApplyBean applyBean = new ApplyBean(cmd,certificateName,certificateCompany,certificateBusiness1,certificateLegalPerson,certificateCompanyDetailAddress
                ,certificateAddress,certificateTel,certificateWeixinorqq,certificateIdCardUp,certificateIdCardDown,certificateIdCardHand,certificateCertificateImages
                ,certificateSupplement);
        String json = new Gson().toJson(applyBean);
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
                ApplyBean applyBean = gson.fromJson(response,ApplyBean.class);
                if (applyBean.result.equals("1")){
                    ToastUtils.showMessageShort(context,applyBean.resultNote);
                }
                ToastUtils.showMessageShort(context,"您的申请认证已提交!");
            }
        });

    }
}

