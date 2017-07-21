package com.lixin.luyuancarcity.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.FeedbackBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.dialog.ErrorDialog;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.SPUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 小火
 * Create time on  2017/7/17
 * My mailbox is 1403241630@qq.com
 */

public class FeedbackActivity extends BaseActivity{
    private EditText feedback;
    private Button btnFeedback;
    private String uid;
    private String cmd = "feedbackOption";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        hideBack(5);
        uid = (String) SPUtils.get(FeedbackActivity.this,"uid","");
        setTitleText("意见反馈",false);
        initView();
    }

    private void initView() {
        feedback = (EditText) findViewById(R.id.edi_feedback_edt_content);
        btnFeedback = (Button) findViewById(R.id.btn_feedback);
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = feedback.getText().toString().trim();
                if (TextUtils.isEmpty(content)){
                    setTips();
                    return;
                }
                getdata(content);
            }
        });
    }

    private void getdata(String content) {
        Map<String, String> params = new HashMap<>();
        FeedbackBean feedbackBean = new FeedbackBean(cmd,uid,content);
        String json = new Gson().toJson(feedbackBean);
        params.put("json", json);
        dialog1.show();
        OkHttpUtils//
                .post()//
                .url(context.getString(R.string.url))//
                .params(params)//
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dialog1.dismiss();
                        ToastUtils.showMessageShort(context, e.getMessage());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        dialog1.dismiss();
                        Log.i("response", "onResponse: "+ response.toString());
                        FeedbackBean replyBean = gson.fromJson(response, FeedbackBean.class);
                        if (replyBean.result.equals("1")) {
                            ToastUtils.showMessageShort(context, replyBean.resultNote);
                            return;
                        }
                        ToastUtils.showMessageShort(context, "意见反馈成功");
                    }
                });
    }

    private ErrorDialog mDialog;

    private void setTips() {
        if (mDialog == null) {
            mDialog = new ErrorDialog(FeedbackActivity.this, null);
        }
        mDialog.setTextView("请输入要反馈的内容");
        mDialog.show();
    }
}

