package com.lixin.luyuancarcity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.activity.ApplyForAdmissionActivity;
import com.lixin.luyuancarcity.activity.CarFilesActivity;
import com.lixin.luyuancarcity.activity.FeedbackActivity;
import com.lixin.luyuancarcity.activity.LoginActivity;
import com.lixin.luyuancarcity.activity.MyAllOrderActivity;
import com.lixin.luyuancarcity.activity.MyCollectionActivity;
import com.lixin.luyuancarcity.activity.MyCouponActivity;
import com.lixin.luyuancarcity.activity.MyFootActivity;
import com.lixin.luyuancarcity.activity.MyPersonalInformationActivity;
import com.lixin.luyuancarcity.activity.MyReleaseActivity;
import com.lixin.luyuancarcity.activity.RecommendFriendsActivity;
import com.lixin.luyuancarcity.activity.SetUpActivity;
import com.lixin.luyuancarcity.activity.ShoppingCartActivity;
import com.lixin.luyuancarcity.utils.SPUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;
import com.lixin.luyuancarcity.view.RoundedImageView;

/**
 * Created by 小火
 * Create time on  2017/6/23
 * My mailbox is 1403241630@qq.com
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    private View view;
    private View[] funcViews = new View[16];
    private String[] funcTxts;
    private String uid;
    private String userIcon;
    private String nickName;
    private int[] bigBGs = new int[]{
            R.drawable.m_shopping_cat,
            R.drawable.m_collection,
            R.drawable.m_footprint,
            R.drawable.m_my_release,
            R.drawable.m_coupon,
            R.drawable.m_wait_payment,
            R.drawable.m_wait_goods_receipt,
            R.drawable.m_wait_install,
            R.drawable.m_wait_evaluate,
            R.drawable.m_is_after_sale_refund,
            R.drawable.m_car_files,
            R.drawable.recommend_friends,
            R.drawable.m_feedback,
            R.drawable.m_about_us,
            R.drawable.m_apply_for_admission,
            R.drawable.m_setting
    };
    private LinearLayout mAllOrder;
    private RoundedImageView head_image ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frgment_mine,container,false);
        uid = (String) SPUtils.get(getActivity(),"uid","");
        userIcon = (String) SPUtils.get(getActivity(),"userIcon","");
        nickName = (String) SPUtils.get(getActivity(),"nickName","");
        initView();
        return view;
    }

    private void initView() {
        mAllOrder = (LinearLayout) view.findViewById(R.id.linear_main_all_order);
        mAllOrder.setOnClickListener(this);
        head_image = (RoundedImageView) view.findViewById(R.id.head_image);
        head_image.setOnClickListener(this);
        funcTxts = getActivity().getResources().getStringArray(R.array.mine_functions);
        funcViews[0] = view.findViewById(R.id.text_shopping_cart);
        funcViews[1] = view.findViewById(R.id.text_collection);
        funcViews[2] = view.findViewById(R.id.text_footprint);
        funcViews[3] = view.findViewById(R.id.text_my_release);
        funcViews[4] = view.findViewById(R.id.text_coupon);
        funcViews[5] = view.findViewById(R.id.text_wait_pay_money);
        funcViews[6] = view.findViewById(R.id.text_wait_goods_receipt);
        funcViews[7] = view.findViewById(R.id.text_wait_install);
        funcViews[8] = view.findViewById(R.id.text_wait_evaluate);
        funcViews[9] = view.findViewById(R.id.text_wait_after_sale_refund);
        funcViews[10] = view.findViewById(R.id.text_car_files);
        funcViews[11] = view.findViewById(R.id.text_recommend_friends);
        funcViews[12] = view.findViewById(R.id.text_feedback);
        funcViews[13] = view.findViewById(R.id.text_about_us);
        funcViews[14] = view.findViewById(R.id.text_apply_for_admission);
        funcViews[15] = view.findViewById(R.id.text_setting);
        for (int i = 0; i < funcViews.length; i++) {
            ImageView imageView = (ImageView) funcViews[i]
                    .findViewById(R.id.include_mine_imagetext_view_image);
            TextView textView = (TextView) funcViews[i]
                    .findViewById(R.id.include_mine_imagetext_textview_text);
            textView.setText(funcTxts[i]);
            imageView.setImageResource(bigBGs[i]);
            funcViews[i].setOnClickListener(this);
            funcViews[i].setId(i);
        }
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.head_image:
                    if (TextUtils.isEmpty((CharSequence) SPUtils.get(getActivity(), "uid", ""))) {
                        Intent intent12 = new Intent();
                        intent12.setClass(getActivity(), LoginActivity.class);
                        startActivity(intent12);
                    } else {
                        Intent intent13 = new Intent(getActivity(), MyPersonalInformationActivity.class);
                        startActivityForResult(intent13,2);
                    }
                    break;
                case 0://购物车
                    ToastUtils.showMessageShort(getActivity(),"购物车");
                    if (TextUtils.isEmpty(uid)){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }else {
                        startActivity(new Intent(getActivity(),ShoppingCartActivity.class));
                    }
                    break;
                case 1://收藏
                    ToastUtils.showMessageShort(getActivity(),"收藏");
                    if (TextUtils.isEmpty(uid)){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }else {
                        startActivity(new Intent(getActivity(),MyCollectionActivity.class));
                    }
                    break;
                case 2://足迹
                    ToastUtils.showMessageShort(getActivity(),"足迹");
                    if (TextUtils.isEmpty(uid)){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }else {
                        startActivity(new Intent(getActivity(),MyFootActivity.class));
                    }

                    break;
                case 3://我的发布
                    ToastUtils.showMessageShort(getActivity(),"我的发布");
                    if (TextUtils.isEmpty(uid)){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }else {
                        startActivity(new Intent(getActivity(),MyReleaseActivity.class));
                    }
                    break;
                case 4://优惠券
                    ToastUtils.showMessageShort(getActivity(),"优惠券");
                    if (TextUtils.isEmpty(uid)){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }else {
                        startActivity(new Intent(getActivity(),MyCouponActivity.class));
                    }
                    break;
                case R.id.linear_main_all_order://全部订单
                    ToastUtils.showMessageShort(getActivity(),"全部订单");
                    if (TextUtils.isEmpty(uid)){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }else {
                        Intent intent3 = new Intent(getActivity(),MyAllOrderActivity.class);
                        intent3.putExtra("orderState","0");
                        startActivity(intent3);
                    }
                    break;
                case 5://待付款
                    ToastUtils.showMessageShort(getActivity(),"待付款");
                    if (TextUtils.isEmpty(uid)){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }else {
                        Intent intent3 = new Intent(getActivity(),MyAllOrderActivity.class);
                        intent3.putExtra("orderState","1");
                        startActivity(intent3);
                    }
                    break;
                case 6://待收货
                    ToastUtils.showMessageShort(getActivity(),"待收货");
                    if (TextUtils.isEmpty(uid)){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }else {
                        Intent intent3 = new Intent(getActivity(),MyAllOrderActivity.class);
                        intent3.putExtra("orderState","2");
                        startActivity(intent3);
                    }
                    break;
                case 7://待安装
                    ToastUtils.showMessageShort(getActivity(),"待安装");
                    if (TextUtils.isEmpty(uid)){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }else {
                        Intent intent3 = new Intent(getActivity(),MyAllOrderActivity.class);
                        intent3.putExtra("orderState","3");
                        startActivity(intent3);
                    }
                    break;
                case 8://待评价
                    ToastUtils.showMessageShort(getActivity(),"待评价");
                    if (TextUtils.isEmpty(uid)){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }else {
                        Intent intent3 = new Intent(getActivity(),MyAllOrderActivity.class);
                        intent3.putExtra("orderState","4");
                        startActivity(intent3);
                    }
                    break;
                case 9://售后退款
                    ToastUtils.showMessageShort(getActivity(),"售后退款");
                    if (TextUtils.isEmpty(uid)){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }else {
                        Intent intent3 = new Intent(getActivity(),MyAllOrderActivity.class);
                        intent3.putExtra("orderState","5");
                        startActivity(intent3);
                    }
                    break;
                case 10://爱车档案
                    ToastUtils.showMessageShort(getActivity(),"爱车档案");
                    if (TextUtils.isEmpty(uid)){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }else {
                        startActivity(new Intent(getActivity(),CarFilesActivity.class));
                    }
                    break;
                case 11://推荐好友
                    ToastUtils.showMessageShort(getActivity(),"推荐好友");
                    if (TextUtils.isEmpty(uid)){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }else {
                        startActivity(new Intent(getActivity(),RecommendFriendsActivity.class));
                    }
                    break;
                case 12://意见反馈
                    ToastUtils.showMessageShort(getActivity(),"意见反馈");
                    startActivity(new Intent(getActivity(),FeedbackActivity.class));
                    break;
                case 13://关于我们
                    ToastUtils.showMessageShort(getActivity(),"关于我们");
                    break;
                case 14://申请成为商家
                    ToastUtils.showMessageShort(getActivity(),"申请成为商家");
                    if (TextUtils.isEmpty(uid)){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }else {
                        startActivity(new Intent(getActivity(),ApplyForAdmissionActivity.class));
                    }
                    break;
                case 15://跳转到设置
                    ToastUtils.showMessageShort(getActivity(),"设置");
                    startActivity(new Intent(getActivity(),SetUpActivity.class));
                    break;
            }
    }
}
