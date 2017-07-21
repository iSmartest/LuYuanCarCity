package com.lixin.luyuancarcity.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.CarInsuranceBean;
import com.lixin.luyuancarcity.bean.InsuranceCompanyResultBean;
import com.lixin.luyuancarcity.bean.MyPolicyResultBean;
import com.lixin.luyuancarcity.bean.RequestBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.utils.ImageManager;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.SPUtils;
import com.lixin.luyuancarcity.utils.StringUtils;
import com.lixin.luyuancarcity.utils.TimerUtil;
import com.lixin.luyuancarcity.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * Created by 小火
 * Create time on  2017/6/27
 * My mailbox is 1403241630@qq.com
 *
 * 低价车险
 */

public class LowPriceAutoInsuranceActivity extends BaseActivity{
    private LinearLayout mPolicy,mQuote;
    private Button mNext,btnCode;
    private List<InsuranceCompanyResultBean.insuranceCompanyList> mList;
    private String telephone;
    private ImageView mAdCompany;
    private EditText ediCarNumber,ediEngineNumber,ediFrameNumber,ediOwnerName,ediPhoneNumber,ediPhoneCode;
    private TextView mUploadDrivingLicense,mUploadIdCard;
    private CircleImageView mDrivingLicenseFrond,mDrivingLicenseBack,mIdCardFrond,mIdCardBack;
    private String code;
    private String uid;
    private String idCarFront;
    private String idCarBack;
    private String mLicenseFrond;
    private String mLicenseBack;
    private List<String> license;
    private List<String> cardID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_low_price_auto_insurance);
        uid = (String) SPUtils.get(LowPriceAutoInsuranceActivity.this,"uid","");
        idCarFront = (String) SPUtils.get(LowPriceAutoInsuranceActivity.this,"idCarFront","");
        idCarBack = (String) SPUtils.get(LowPriceAutoInsuranceActivity.this,"idCarBack","");
        mLicenseFrond = (String) SPUtils.get(LowPriceAutoInsuranceActivity.this,"licenseFrond","");
        mLicenseBack = (String) SPUtils.get(LowPriceAutoInsuranceActivity.this,"licenseBack","");
        license = new ArrayList<>();
        license.add(mLicenseFrond);
        license.add(mLicenseBack);
        cardID = new ArrayList<>();
        cardID.add(idCarFront);
        cardID.add(idCarBack);
        setTitleText("底价车险",false);
        hideBack(5);
        initView();
        mList = new ArrayList<>();
//        getdata();
    }
    private void initView() {
        mPolicy = (LinearLayout) findViewById(R.id.linear_my_policy);
        mQuote = (LinearLayout) findViewById(R.id.linear_phone_quote);
        mPolicy.setOnClickListener(this);
        mQuote.setOnClickListener(this);
        mNext = (Button) findViewById(R.id.btn_next);
        btnCode = (Button) findViewById(R.id.btn_verification_code);
        mNext.setOnClickListener(this);
        btnCode.setOnClickListener(this);
        mAdCompany = (ImageView) findViewById(R.id.imag_insurance_company);
        ediCarNumber = (EditText) findViewById(R.id.edi_add_car_number);
        ediEngineNumber = (EditText) findViewById(R.id.edi_add_engine_number);
        ediFrameNumber = (EditText) findViewById(R.id.edi_add_frame_number);
        ediOwnerName = (EditText) findViewById(R.id.edi_add_owner_name);
        ediPhoneNumber = (EditText) findViewById(R.id.edi_add_phone_number);
        ediPhoneCode = (EditText) findViewById(R.id.edi_add_phone_code);
        mUploadDrivingLicense = (TextView) findViewById(R.id.text_low_price_upload_driving_license);
        mUploadIdCard = (TextView) findViewById(R.id.text_low_price_upload_id_card);
        mDrivingLicenseFrond = (CircleImageView) findViewById(R.id.include_low_price_driving_license_frond);
        mDrivingLicenseBack = (CircleImageView) findViewById(R.id.include_low_price_driving_license_back);
        mIdCardFrond = (CircleImageView) findViewById(R.id.include_low_price_id_card_frond);
        mIdCardBack = (CircleImageView) findViewById(R.id.include_low_price_id_card_back);
    }


    private void getdata() {
        Map<String, String> params = new HashMap<>();
        RequestBean requestBean = new RequestBean("getCarInsuranceCompanyList");
        String json = new Gson().toJson(requestBean);
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
                InsuranceCompanyResultBean insuranceCompanyResultBean = gson.fromJson(response, InsuranceCompanyResultBean.class);
                if (insuranceCompanyResultBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, insuranceCompanyResultBean.resultNote);
                    return;
                }
                List<InsuranceCompanyResultBean.insuranceCompanyList> financialList = insuranceCompanyResultBean.insuranceCompanyList;
                mList.addAll(financialList);
                telephone = insuranceCompanyResultBean.getInsurancePhone();
                String img = insuranceCompanyResultBean.getAdvIcon();
                if (TextUtils.isEmpty(img)){
                    mAdCompany.setImageResource(R.drawable.image_fail_empty);
                }else {
                    ImageManager.imageLoader.displayImage(img,mAdCompany,ImageManager.options3);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_my_policy:
                startActivity(new Intent(LowPriceAutoInsuranceActivity.this,MyPolicyActivity.class));
                break;
            case R.id.btn_next:
//                submit();
                Intent intent = new Intent(LowPriceAutoInsuranceActivity.this,AutomobileInsuranceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CompanyList", (Serializable) mList);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.linear_phone_quote://可能需要一个弹窗
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_CALL);
                intent2.setData(Uri.parse("tel:" + telephone));
                startActivity(intent2);
                break;
            case R.id.btn_verification_code:
                String user_phone_number = ediPhoneNumber.getText().toString().trim();
                //验证手机号是否正确
                if (!StringUtils.isMatchesPhone(user_phone_number)){
                    ToastUtils.showMessageShort(context,"你输入的手机号格式不正确");
                    return;
                }
                //验证电话号码不能为空
                if (TextUtils.isEmpty(user_phone_number)){
                    ToastUtils.showMessageShort(context,"请输入手机号！");
                    return;
                }
                code = TimerUtil.getNum();
                sendSMS(user_phone_number,code);
                TimerUtil mTimerUtil = new TimerUtil(btnCode);
                mTimerUtil.timers();
        }
    }

    private void submit() {
        String carNumber = ediCarNumber.getText().toString().trim();
        String engineNumber = ediEngineNumber.getText().toString().trim();
        String frameNumber = ediFrameNumber.getText().toString().trim();
        String ownerName = ediOwnerName.getText().toString().trim();
        String phoneNumber = ediPhoneNumber.getText().toString().trim();
        String phoneCode = ediPhoneCode.getText().toString().trim();
        if (TextUtils.isEmpty(carNumber)){
            ToastUtils.showMessageShort(LowPriceAutoInsuranceActivity.this,"请输入车牌号码");
            return;
        }if (TextUtils.isEmpty(engineNumber)){
            ToastUtils.showMessageShort(LowPriceAutoInsuranceActivity.this,"请输入发动机号后6位");
            return;
        }if (TextUtils.isEmpty(frameNumber)){
            ToastUtils.showMessageShort(LowPriceAutoInsuranceActivity.this,"请输入车架号后6位");
            return;
        }if (TextUtils.isEmpty(ownerName)){
            ToastUtils.showMessageShort(LowPriceAutoInsuranceActivity.this,"请输入车主姓名");
            return;
        }if (TextUtils.isEmpty(phoneNumber)){
            ToastUtils.showMessageShort(LowPriceAutoInsuranceActivity.this,"请输入手机号码");
            return;
        }if (TextUtils.isEmpty(phoneCode)){
            ToastUtils.showMessageShort(LowPriceAutoInsuranceActivity.this,"请输入验证码");
            return;
        }if (!phoneCode.equals(code)) {
            ToastUtils.showMessageShort(LowPriceAutoInsuranceActivity.this, "验证码不正确");
            return;
        }
        getCarInsurance(carNumber,engineNumber,frameNumber,ownerName,phoneNumber);
    }

    private void getCarInsurance(String carNumber, String engineNumber, String frameNumber, String ownerName, String phoneNumber) {
        Map<String, String> params = new HashMap<>();
        CarInsuranceBean carInsuranceBean = new CarInsuranceBean("getCarInsurance",uid,carNumber,engineNumber,frameNumber,ownerName,phoneNumber
        ,license,cardID);
        String json = new Gson().toJson(carInsuranceBean);
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
                CarInsuranceBean carInsuranceBean = gson.fromJson(response, CarInsuranceBean.class);
                if (carInsuranceBean.result.equals("1")){
                    ToastUtils.showMessageShort(context, carInsuranceBean.resultNote);
                }
            }
        });
    }

    /**
     * 获取短信验证码
     * @param phone
     */
    public void sendSMS(String phone, String CODE) {
        OkHttpUtils.post().url("https://v.juhe.cn/sms/send?").addParams("mobile", phone).addParams("tpl_id", "34989").addParams("tpl_value", "%23code%23%3d" + CODE).addParams("key", "3ec1d3bbf41f49bc2fbeb89654338585").build().execute(new StringCallback() {
            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("error_code").equals("0")) {
                        ToastUtils.showMessageShort(LowPriceAutoInsuranceActivity.this,"短信已发送，请注意查收");
                    } else {
                        ToastUtils.showMessageShort(LowPriceAutoInsuranceActivity.this,obj.getString("reason"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Call call, Exception e, int id) {

            }
        });

    }
}
