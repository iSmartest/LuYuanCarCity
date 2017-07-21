package com.lixin.luyuancarcity.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.MaintenanceBean;
import com.lixin.luyuancarcity.bean.ModifyPassword;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.SPUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 小火
 * Create time on  2017/7/19
 * My mailbox is 1403241630@qq.com
 */

public class ModifyPasswordActivity extends BaseActivity{
    private EditText mOldPassword,mNewPassword,mSurePassword;
    private TextView mSubmit;
    private String cmd = "changePassword";
    private String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        uid = (String) SPUtils.get(context,"uid","");
        hideBack(5);
        setTitleText("修改密码",false);
        initView();
    }

    private void initView() {
        mOldPassword = (EditText) findViewById(R.id.edit_old_password);
        mNewPassword = (EditText) findViewById(R.id.edit_new_password);
        mSurePassword = (EditText) findViewById(R.id.edit_sure_password);
        mSubmit = (TextView) findViewById(R.id.text_sure_submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private void submit() {
        String oldPassword = mOldPassword.getText().toString().trim();
        String newPassword = mNewPassword.getText().toString().trim();
        String surePassword = mSurePassword.getText().toString().trim();
        if (TextUtils.isEmpty(oldPassword)){
            ToastUtils.showMessageShort(context,"请输入当前密码");
        }else if (TextUtils.isEmpty(newPassword)){
            ToastUtils.showMessageShort(context,"请输入新密码");
        }else if (TextUtils.isEmpty(surePassword)){
            ToastUtils.showMessageShort(context,"再一次输入新密码");
        }else if (newPassword.equals(surePassword)){
            ToastUtils.showMessageShort(context,"两次输入的新密码不一致");
        }else {
            getSubmit(oldPassword,newPassword);
        }
    }

    private void getSubmit(String oldPassword, String newPassword) {
        Map<String,String> params = new HashMap<>();
        ModifyPassword modifyPassword = new ModifyPassword(cmd,uid,oldPassword,newPassword);
        String json = new Gson().toJson(modifyPassword);
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
                ModifyPassword  mdifyPasswordBean = gson.fromJson(response, ModifyPassword.class);
                dialog1.dismiss();
                if (mdifyPasswordBean.result.equals("1")){
                    ToastUtils.showMessageShort(context,mdifyPasswordBean.resultNote);
                }
                ToastUtils.showMessageShort(context,"密码修改成功");
            }
        });
    }
}
