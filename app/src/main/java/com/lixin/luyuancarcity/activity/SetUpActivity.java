package com.lixin.luyuancarcity.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.dialog.TipsDialog;
import com.lixin.luyuancarcity.tool.DataCleanManager;
import com.lixin.luyuancarcity.tool.UpdateManager;
import com.lixin.luyuancarcity.utils.SPUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;

/**
 * Created by 小火
 * Create time on  2017/7/17
 * My mailbox is 1403241630@qq.com
 */

public class SetUpActivity extends BaseActivity{
    private LinearLayout mModifyPassword,mVersionUpdate,mClearCache;
    private TextView mSure,mCacheSize;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        hideBack(5);
        setTitleText("设置",false);
        initView();

    }
    private void initView() {
        mModifyPassword = (LinearLayout) findViewById(R.id.linear_my_setting_modify_password);
        mModifyPassword.setOnClickListener(this);
        mVersionUpdate = (LinearLayout) findViewById(R.id.linear_my_setting_version_update);
        mVersionUpdate.setOnClickListener(this);
        mClearCache = (LinearLayout) findViewById(R.id.linear_my_setting_clear_cache);
        mClearCache.setOnClickListener(this);
        mSure = (TextView) findViewById(R.id.text_my_setting_sure);
        mSure.setOnClickListener(this);
        mCacheSize = (TextView) findViewById(R.id.text_my_setting_clear_cache_size);
        try {
            mCacheSize.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_my_setting_modify_password:
                startActivity(new Intent(context, ModifyPasswordActivity.class));
                break;
            case R.id.linear_my_setting_version_update:
                int i = getVerCode();
                if (1 > i) {
                    // 这里来检测版本是否需要更新
                    UpdateManager mUpdateManager = new UpdateManager(this);
                    mUpdateManager.checkUpdateInfo();
                } else {
                    Toast.makeText(this, "已经是最新版本了无需更新", 0).show();
                }
                break;
            case R.id.linear_my_setting_clear_cache:
                new Thread(new clearCache()).start();
                break;
            case R.id.text_my_setting_sure:
                TipsDialog dialog = new TipsDialog(SetUpActivity.this,R.string.log_out, new TipsDialog.OnSureBtnClickListener() {
                    @Override
                    public void sure() {
                        SPUtils.put(context,"uid","");//用户ID
                        SPUtils.put(context,"phoneNum","");//手机号码
                        SPUtils.put(context,"isLogin",false);//登录状态
                        ToastUtils.showMessageShort(context,"已安全退出账号");
                        MyApplication.openActivity(context,LoginActivity.class);
                        finish();
                    }
                });
                dialog.show();
                break;
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    ToastUtils.showMessageLong(SetUpActivity.this,"清理完成");
                    try {
                        mCacheSize.setText(DataCleanManager.getTotalCacheSize(SetUpActivity.this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
    };
    class clearCache implements Runnable {
        @Override
        public void run() {
            try {
                DataCleanManager.clearAllCache(SetUpActivity.this);
                Thread.sleep(3000);
                if (DataCleanManager.getTotalCacheSize(SetUpActivity.this).startsWith("0")) {
                    handler.sendEmptyMessage(0);
                }
            } catch (Exception e) {
                return;
            }
        }
    }

    // 获取当前应用的版本号
    public int getVerCode() {
        int verCode = -1;
        try {
            verCode = getPackageManager().getPackageInfo("com.lixin.foodmarket", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return verCode;
    }
}
