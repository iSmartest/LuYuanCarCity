package com.lixin.luyuancarcity.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.dialog.ProgressDialog;

/**
 * Created by 小火
 * Create time on  2017/6/23
 * My mailbox is 1403241630@qq.com
 */

public class BaseActivity extends FragmentActivity implements View.OnClickListener {
    private boolean override;
    protected Context context;
    protected Dialog dialog1;
    private static Dialog progressDlg;
    private Resources resource;
    private static final int REQUEST_CODE_PICK_CITY = 233;
    private TextView mCityLocation,mRelease;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //页面启动 记录日志
        setContentView(R.layout.activity_base);
        context = this;
        dialog1 = ProgressDialog.createLoadingDialog(context, "加载中.....");
        resource = context.getResources();
    }
    public void hideBack(final int show) {
        ImageView mBack = (ImageView) findViewById(R.id.Iv_base_back);
        ImageView mSearch = (ImageView) findViewById(R.id.Iv_base_search);
        ImageView mMessage = (ImageView) findViewById(R.id.Iv_base_message);
        LinearLayout mLeft = (LinearLayout) findViewById(R.id.ly_base_left);
        LinearLayout mRight = (LinearLayout) findViewById(R.id.ly_base_right);
        mCityLocation = (TextView) findViewById(R.id.text_city_location);
        mRelease = (TextView) findViewById(R.id.text_base_release);
        mRelease.setOnClickListener(this);
        TextView mTitleText = (TextView) findViewById(R.id.tv_base_titleText);
        RelativeLayout lay_bg = (RelativeLayout) findViewById(R.id.lay_bg);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseActivity.this, "点击了后退", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseActivity.this, "跳转到搜索页面", Toast.LENGTH_SHORT).show();
            }
        });
        mMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseActivity.this,"跳转到消息页面",Toast.LENGTH_SHORT).show();
            }
        });
        mCityLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(BaseActivity.this, CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
            }
        });

        switch (show){
            case 0://首页
                mBack.setVisibility(View.GONE);
                mRelease.setVisibility(View.GONE);
                mLeft.setVisibility(View.VISIBLE);
                mRight.setVisibility(View.VISIBLE);
                mCityLocation.setVisibility(View.VISIBLE);
                mTitleText.setVisibility(View.VISIBLE);
                mSearch.setVisibility(View.VISIBLE);
                mMessage.setVisibility(View.VISIBLE);
                break;
            case 1://门店、百科
                mLeft.setVisibility(View.GONE);
                mTitleText.setVisibility(View.VISIBLE);
                mRight.setVisibility(View.VISIBLE);
                mSearch.setVisibility(View.GONE);
                mRelease.setVisibility(View.GONE);
                mMessage.setVisibility(View.VISIBLE);
                break;
            case 2://论坛
                mLeft.setVisibility(View.GONE);
                mTitleText.setVisibility(View.VISIBLE);
                mRight.setVisibility(View.VISIBLE);
                mSearch.setVisibility(View.GONE);
                mRelease.setVisibility(View.VISIBLE);
                mMessage.setVisibility(View.GONE);
                break;
            case 3://百科
                mLeft.setVisibility(View.GONE);
                mTitleText.setVisibility(View.VISIBLE);
                mRight.setVisibility(View.VISIBLE);
                mSearch.setVisibility(View.GONE);
                mRelease.setVisibility(View.GONE);
                mMessage.setVisibility(View.VISIBLE);
                break;
            case 4://我的
                mLeft.setVisibility(View.GONE);
                mTitleText.setVisibility(View.GONE);
                mRight.setVisibility(View.VISIBLE);
                mSearch.setVisibility(View.GONE);
                mRelease.setVisibility(View.GONE);
                mMessage.setVisibility(View.VISIBLE);
                break;
            case 5:
                mRight.setVisibility(View.GONE);
                mTitleText.setVisibility(View.VISIBLE);
                mLeft.setVisibility(View.VISIBLE);
                mCityLocation.setVisibility(View.GONE);
                mBack.setVisibility(View.VISIBLE);
                break;
            case 6:
                mLeft.setVisibility(View.VISIBLE);
                mBack.setVisibility(View.VISIBLE);
                mCityLocation.setVisibility(View.GONE);
                mTitleText.setVisibility(View.VISIBLE);
                mRight.setVisibility(View.VISIBLE);
                mSearch.setVisibility(View.GONE);
                mRelease.setVisibility(View.VISIBLE);
                mMessage.setVisibility(View.GONE);
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {

    }
    protected void overrideOnKeyDown(boolean override) {
        this.override = override;
    }
    protected void back() {
        finish();
    }
    //    @Override
//    public void startActivity(Intent intent) {
//        super.startActivity(intent);
//        overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
//    }
//
//    @Override
//    public void startActivityForResult(Intent intent, int requestCode) {
//        super.startActivityForResult(intent, requestCode);
//        overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
//    }
//
//    @Override
//    public void startActivityFromChild(Activity child, Intent intent,
//                                       int requestCode) {
//        super.startActivityFromChild(child, intent, requestCode);
//        overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
//    }
//    private TipsDialog dialog;
//
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (override)
//            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//                if (dialog == null) {
//                    dialog = new TipsDialog(this, R.string.ok_to_exit_it, new TipsDialog.OnSureBtnClickListener() {
//                        @Override
//                        public void sure() {
//                            dialog.dismiss();
//                            finish();
//                            MyApplication.getApplication().exit();
//                        }
//                    });
//                }
//                dialog.show();
//            }
//        return super.onKeyDown(keyCode, event);
//    }
    public void setTitleText(String string,boolean show) {
        TextView titleTv = (TextView) findViewById(R.id.tv_base_titleText);
        ColorStateList csl1 = resource.getColorStateList(R.color.snow_white);
        ColorStateList csl2 = resource.getColorStateList(R.color.black);
        titleTv.setText(string);
        if (show) titleTv.setTextColor(csl1);
        else titleTv.setTextColor(csl2);

    }
    public void setRelease(String string){
        ColorStateList csl1 = resource.getColorStateList(R.color.snow_white);
        mRelease.setText(string);
        mRelease.setTextColor(csl1);
    }
    /**
     * 关闭进度对话框
     */
    public void dismissProgressDialog() {
        if (progressDlg != null && progressDlg.isShowing()) {
            progressDlg.dismiss();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK){
            if (data != null){
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                mCityLocation.setText(city);
            }
        }
    }
}

