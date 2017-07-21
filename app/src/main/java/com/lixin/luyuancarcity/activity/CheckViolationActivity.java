package com.lixin.luyuancarcity.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hhl.library.FlowTagLayout;
import com.hhl.library.OnTagSelectListener;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.adapter.TagAdapter;
import com.lixin.luyuancarcity.bean.CheckBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 小火
 * Create time on  2017/7/5
 * My mailbox is 1403241630@qq.com
 */

public class CheckViolationActivity extends BaseActivity {
    private TextView mProvinceAbbreviation, mIllegalAddress, mIllegalTime, mIllegalDec, mIllegal_money;
    private EditText mCarNumber, mEngineNumber, mFrameNumber;
    private Button mCheck, mViolations;
    private LinearLayout linear1, linear2, linear3;
    private List<CheckBean.illegalInfo> mList;
    private String tel;
    private String cmd = "findIllegal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_violation);
        mList = new ArrayList<>();
        hideBack(5);
        setTitleText("查违章", false);
        initView();
    }

    private void initView() {
        linear1 = (LinearLayout) findViewById(R.id.line01);
        linear2 = (LinearLayout) findViewById(R.id.line02);
        linear3 = (LinearLayout) findViewById(R.id.line03);
        linear1.setVisibility(View.VISIBLE);
        linear2.setVisibility(View.GONE);
        linear3.setVisibility(View.GONE);
        mProvinceAbbreviation = (TextView) findViewById(R.id.text_province_abbreviation);
        mIllegalAddress = (TextView) findViewById(R.id.text_illegal_address);
        mIllegalTime = (TextView) findViewById(R.id.text_illegal_time);
        mIllegalDec = (TextView) findViewById(R.id.text_illegal_dec);
        mIllegalDec = (TextView) findViewById(R.id.text_illegal_money);
        mCarNumber = (EditText) findViewById(R.id.edi_abbreviation_car_number);
        mEngineNumber = (EditText) findViewById(R.id.edi_abbreviation_engine_number);
        mFrameNumber = (EditText) findViewById(R.id.edi_abbreviation_frame_number);
        mCheck = (Button) findViewById(R.id.btn_check);
        mCheck = (Button) findViewById(R.id.btn_check);
        mViolations = (Button) findViewById(R.id.btn_telephone_handle_violations);
        mCheck.setOnClickListener(this);
        mViolations.setOnClickListener(this);
        mProvinceAbbreviation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_check:
                sumbit();
                break;
            case R.id.btn_telephone_handle_violations://可能需要弹窗
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + tel));
                startActivity(intent);
                break;
            case R.id.text_province_abbreviation:
                CommodityAttribute mCommodityAttribute = new CommodityAttribute(CheckViolationActivity.this);
                mCommodityAttribute.showAtLocation(mProvinceAbbreviation, Gravity.CENTER, 0, 0);
                break;
        }
    }

    private void sumbit() {
        String number = mCarNumber.getText().toString().trim();
        String engineNumber = mEngineNumber.getText().toString().trim();
        String frameNumber = mFrameNumber.getText().toString().trim();
        if (TextUtils.isEmpty(number)) {
            ToastUtils.showMessageShort(CheckViolationActivity.this, "请输入车牌号码");
        }
        if (TextUtils.isEmpty(engineNumber)) {
            ToastUtils.showMessageShort(CheckViolationActivity.this, "请输入发动机号");
        }
        if (TextUtils.isEmpty(frameNumber)) {
            ToastUtils.showMessageShort(CheckViolationActivity.this, "请输入车架号");
        }
        String carNumber = mProvinceAbbreviation.getText().toString().trim() + number;
        getCheckData(carNumber, engineNumber, frameNumber);
    }

    private void getCheckData(String carNumber, String engineNumber, String frameNumber) {
        Map<String, String> params = new HashMap<>();
        CheckBean checkBean = new CheckBean(cmd, carNumber, engineNumber, frameNumber);
        String json = new Gson().toJson(checkBean);
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
                CheckBean checkBean = gson.fromJson(response, CheckBean.class);
                if (checkBean.result.equals("1")) {
                    ToastUtils.showMessageShort(context, checkBean.resultNote);
                    return;
                }
                List<CheckBean.illegalInfo> illegalInfo = checkBean.illegalInfo;
                mList.addAll(illegalInfo);
                fillData(mList);
            }
        });
    }

    private void fillData(List<CheckBean.illegalInfo> mList) {
        if (mList.isEmpty()) {
            linear1.setVisibility(View.GONE);
            linear2.setVisibility(View.VISIBLE);
            linear3.setVisibility(View.GONE);
        } else {
            linear1.setVisibility(View.GONE);
            linear2.setVisibility(View.GONE);
            linear3.setVisibility(View.VISIBLE);
            mIllegalAddress.setText(mList.get(0).getAddress());
            mIllegalTime.setText(mList.get(0).getTime());
            mIllegalDec.setText(mList.get(0).getContent());
            mIllegal_money.setText(mList.get(0).getMoney());
            tel = mList.get(0).getTel();
        }
    }

    /**
     * 商品属性PopupWind
     */
    public class CommodityAttribute extends PopupWindow {
        private TagAdapter<String> mFlavorTagAdapter;
        private View view;
        private FlowTagLayout mFlavor;

        private String[] title = {"京","津","沪","渝","冀","晋","辽","吉","黑","苏","浙","皖","闽",
        "赣","鲁","豫","鄂","湘","粤","琼","川","贵","云","陕","甘","青","藏","桂","蒙","宁","新","港","澳","台"};
        List<String> mDataList = new ArrayList<>() ;
        public CommodityAttribute(final Activity context) {
            super(context);
            if (title.length >0){
                for (int i = 0; i <title.length; i++) {
                    mDataList.add(title[i]);
                }
            }
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.pop_province_abbreviation, null);
            mFlavor = (FlowTagLayout) view.findViewById(R.id.tf_flavor);
            mFlavorTagAdapter = new TagAdapter<>(context);
            mFlavor.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
            mFlavor.setAdapter(mFlavorTagAdapter);
            mFlavorTagAdapter.onlyAddAll(mDataList);
            mFlavor.setOnTagSelectListener(new OnTagSelectListener() {
                @Override
                public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                    if (selectedList != null && selectedList.size() > 0) {
                        StringBuilder sb = new StringBuilder();
                        for (int i : selectedList) {
                            sb.append(parent.getAdapter().getItem(i));
                        }
                        ToastUtils.showMessageShort(context, "选择了:" + sb.toString());
                        mProvinceAbbreviation.setText(sb.toString());
                    } else {
                        ToastUtils.showMessageShort(context, "没有选择属性");
                    }
                    dismiss();
                }
            });

            // 设置SelectPicPopupWindow的View
            this.setContentView(view);
            // 设置SelectPicPopupWindow弹出窗体的宽
            this.setWidth(ViewPager.LayoutParams.MATCH_PARENT);
            // 设置SelectPicPopupWindow弹出窗体的高
            this.setHeight(ViewPager.LayoutParams.WRAP_CONTENT);
            // 在PopupWindow里面就加上下面两句代码，让键盘弹出时，不会挡住pop窗口。
            this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
            this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            // 设置popupWindow以外可以触摸
            this.setOutsideTouchable(true);
            // 以下两个设置点击空白处时，隐藏掉pop窗口
            this.setFocusable(true);
            this.setBackgroundDrawable(new BitmapDrawable());
            // 设置popupWindow以外为半透明0-1 0为全黑,1为全白
            backgroundAlpha(0.3f);
            // 添加pop窗口关闭事件
            this.setOnDismissListener(new poponDismissListener());
            // 设置动画--这里按需求设置成系统输入法动画
            this.setAnimationStyle(R.style.AnimBottom);
            // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    int height = view.findViewById(R.id.pop_layout)
                            .getTop();
                    int y = (int) event.getY();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (y < height) {
                            dismiss();
                        }
                    }
                    return true;
                }
            });
        }
    }
    /**
     * PopouWindow设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        this.getWindow().setAttributes(lp);
    }

    /**
     * PopouWindow添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }

}