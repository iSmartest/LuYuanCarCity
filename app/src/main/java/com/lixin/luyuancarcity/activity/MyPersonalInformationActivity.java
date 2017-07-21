package com.lixin.luyuancarcity.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lixin.luyuancarcity.R;
import com.lixin.luyuancarcity.bean.PwesonalInformationBean;
import com.lixin.luyuancarcity.budiler.StringCallback;
import com.lixin.luyuancarcity.utils.ImageManager;
import com.lixin.luyuancarcity.utils.ImageUtil;
import com.lixin.luyuancarcity.utils.OkHttpUtils;
import com.lixin.luyuancarcity.utils.SPUtils;
import com.lixin.luyuancarcity.utils.ToastUtils;
import com.lixin.luyuancarcity.view.RoundedImageView;
import com.zhy.base.fileprovider.FileProvider7;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 小火
 * Create time on  2017/7/20
 * My mailbox is 1403241630@qq.com
 */

public class MyPersonalInformationActivity extends BaseActivity{
    private RelativeLayout mAddressManagement, mNickName, mPhone;
    private TextView tvNickName, tvPhone, mSubmit;
    private LinearLayout mHeader;
    private AlertDialog builder; //底部弹出菜单
    private RoundedImageView iv_personal_icon;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    final static int CAMERAPRESS = 3;
    final static int ICONPRESS = 4;
    protected static Uri tempUri;
    private String uid;
    private String userIcon;
    private String nickName;
    private String sex;
    private int userSex;
    private String img;
    private Bitmap HeadPortrait;

    private static String[] PERMISSIONS_CAMERA_AND_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        uid = (String) SPUtils.get(MyPersonalInformationActivity.this, "uid", "");
        userIcon = (String) SPUtils.get(MyPersonalInformationActivity.this, "userIcon", "");
        nickName = (String) SPUtils.get(MyPersonalInformationActivity.this, "nickName", "");
        sex = (String) SPUtils.get(MyPersonalInformationActivity.this, "sex", "");
        img = (String) SPUtils.get(MyPersonalInformationActivity.this, "path", "");
        hideBack(5);
        setTitleText("个人信息",false);
        initView();

    }

    private void initView() {
        mAddressManagement = (RelativeLayout) findViewById(R.id.rl_personal_information_address_management);
        iv_personal_icon = (RoundedImageView) findViewById(R.id.a_my_info_iv_header);
        mAddressManagement.setOnClickListener(this);
        mNickName = (RelativeLayout) findViewById(R.id.rl_personal_information_nick_name);
        mNickName.setOnClickListener(this);
        mPhone = (RelativeLayout) findViewById(R.id.rl_personal_information_phone);
        mPhone.setOnClickListener(this);
        mHeader = (LinearLayout) findViewById(R.id.a_my_info_lay_header);
        mHeader.setOnClickListener(this);
        tvNickName = (TextView) findViewById(R.id.text_personal_information_nick_name);
        tvPhone = (TextView) findViewById(R.id.text_personal_information_phone);
        mSubmit = (TextView) findViewById(R.id.text_personal_information_submit);
        mSubmit.setOnClickListener(this);
        if (!TextUtils.isEmpty(userIcon)) {
            ImageManager.imageLoader.displayImage(userIcon, iv_personal_icon, ImageManager.options3);
        }
        if (!TextUtils.isEmpty(nickName)) {
            tvNickName.setText(nickName);
        }
        if (!TextUtils.isEmpty(sex)) {
            tvPhone.setText(sex);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_personal_information_address_management:
                Intent intent = new Intent(MyPersonalInformationActivity.this, MyAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.a_my_info_lay_header:
                showChoosePicDialog();
                break;
            case R.id.rl_personal_information_nick_name:
                Intent intent1 = new Intent(MyPersonalInformationActivity.this, ModifyNameActivity.class);
                startActivityForResult(intent1, 3001);
                break;
            case R.id.rl_personal_information_phone:

                break;
            case R.id.text_personal_information_submit:
                submit();
                break;
            case R.id.tv_album://相册
                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            ICONPRESS);

                }else {
                    startIcon();
                }

                break;
            case R.id.tv_photograph://拍照
                if ((ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) || ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,
                            PERMISSIONS_CAMERA_AND_STORAGE,
                            CAMERAPRESS);

                }else {
                    startCamera();
                }
                break;
            case R.id.tv_cancel://取消
                builder.dismiss();
                break;
        }
    }

    public void startIcon() {
        Intent openAlbumIntent = new Intent(
                Intent.ACTION_GET_CONTENT);
        openAlbumIntent.setType("image/*");
        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
        builder.dismiss();
    }

    public void startCamera() {
//        Intent openCameraIntent = new Intent(
//                MediaStore.ACTION_IMAGE_CAPTURE);
//        tempUri = Uri.fromFile(new File(Environment
//                .getExternalStorageDirectory(), "image.jpg"));
//        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
//        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
//        startActivityForResult(openCameraIntent, TAKE_PICTURE);

        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (openCameraIntent.resolveActivity(getPackageManager()) != null) {
            String filename = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA)
                    .format(new Date()) + "image.jpg";
            File file = new File(Environment.getExternalStorageDirectory(), filename);
            tempUri = FileProvider7.getUriForFile(this, file);
            openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
            startActivityForResult(openCameraIntent, TAKE_PICTURE);
            builder.dismiss();
        }
    }

    private void submit() {
        String nickname = tvNickName.getText().toString().trim();
        if (TextUtils.isEmpty(nickname)) {
            ToastUtils.showMessageLong(context, "请输入昵称");
            return;
        }
        String sex = tvPhone.getText().toString().trim();
        if (TextUtils.isEmpty(sex)) {
            ToastUtils.showMessageLong(context, "请输入手机号");
            return;
        }
//        getSubmitPersonalInformation(nickname, userSex, img);
        getSubmitPersonalInformation64(nickname, userSex, HeadPortrait);
    }
    private void getSubmitPersonalInformation64(final String nickname, final int userSex,Bitmap headPortrait) {
        Log.i("get", "getSubmitPersonalInformation");
        Map<String, String> params = new HashMap<>();
        final String json = "{\"cmd\":\"commitUserInfoDeatils\",\"uid\":\"" + uid + "\",\"file\":\"" + ImageUtil.bitmaptoString(headPortrait) + "\",\"userName\":\"" +
                "" + nickname + "\",\"userSex\":\"" + userSex + "\"}";
        params.put("json", json);
        dialog1.show();
        OkHttpUtils.post().url(context.getString(R.string.url)).params(params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showMessageLong(context, "网络异常");
                dialog1.dismiss();
            }
            @Override
            public void onResponse(String response, int id) {
                Log.i("jkahd", "onResponse: " + response);
                Gson gson = new Gson();
                PwesonalInformationBean baseBean = gson.fromJson(response, PwesonalInformationBean.class);
                if (baseBean.result.equals("1")) {
                    ToastUtils.showMessageLong(MyPersonalInformationActivity.this, baseBean.resultNote);
                }
//                SPUtils.put(MyPersonalInformationActivity.this,"userIcon",baseBean.getUserIconUrl());
//                SPUtils.put(MyPersonalInformationActivity.this,"nickName",nickname);
                if (userSex == 0){
                    SPUtils.put(MyPersonalInformationActivity.this,"sex","男");
                }else if (userSex == 1){
                    SPUtils.put(MyPersonalInformationActivity.this,"sex","女");
                }
                ToastUtils.showMessageLong(MyPersonalInformationActivity.this,"个人信息保存成功！");
                dialog1.dismiss();
                finish();
            }
        });
    }
//    private void getSubmitPersonalInformation(final String nickname, final int userSex,String img) {
//        File file1 = new File(img);
//        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//        if (!file1.exists()) {
//            Log.i("get", "file=" + "不存在");
//            builder.addPart(Headers.of(
//                    "Content-Disposition",
//                    "form-data; name=\"cmd\""), RequestBody.create(null, "commitUserInfoDeatils"));
//            builder.addPart(Headers.of(
//                    "Content-Disposition",
//                    "form-data; name=\"file\""), RequestBody.create(null, ""));
//            builder.addPart(Headers.of(
//                    "Content-Disposition",
//                    "form-data; name=\"uid\""), RequestBody.create(null, uid));
//            builder.addPart(Headers.of(
//                    "Content-Disposition",
//                    "form-data; name=\"userName\""), RequestBody.create(null, nickname));
//            builder.addPart(Headers.of(
//                    "Content-Disposition",
//                    "form-data; name=\"userSex\""), RequestBody.create(null, userSex + ""));
//        }else {
//            builder.addFormDataPart("file", file1.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file1));
//            builder.addPart(Headers.of(
//                    "Content-Disposition",
//                    "form-data; name=\"cmd\""), RequestBody.create(null, "commitUserInfoDeatils"));
//            builder.addPart(Headers.of(
//                    "Content-Disposition",
//                    "form-data; name=\"uid\""), RequestBody.create(null, uid));
//            builder.addPart(Headers.of(
//                    "Content-Disposition",
//                    "form-data; name=\"userName\""), RequestBody.create(null, nickname));
//            builder.addPart(Headers.of(
//                    "Content-Disposition",
//                    "form-data; name=\"userSex\""), RequestBody.create(null, userSex + ""));
//        }
//
//        dialog1.show();
//        RequestBody body = builder.build();
//        Request request = new Request.Builder()
//                .url("http://116.255.239.201:8080/marketsService/service.action?")
//                .post(body)
//                .build();
//        OkHttpClient client = new OkHttpClient();
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                ToastUtils.showMessageLong(context, "网络异常");
//                dialog1.dismiss();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                dialog1.dismiss();
//                String tempResponse = response.body().string();
//                Gson gson = new Gson();
//                final BaseBean baseBean = gson.fromJson(tempResponse, BaseBean.class);
//                Log.i("get", "getSubmitPersonalInformation: " + tempResponse);
//                if (baseBean.getResult().equals("1")) {
//                    ToastUtils.showMessageLong(MyPersonalInformationActivity.this, baseBean.getResultNote());
//                }
//                SPUtils.put(MyPersonalInformationActivity.this, "userIcon", baseBean.getUserIconUrl());
//                SPUtils.put(MyPersonalInformationActivity.this, "nickName", nickname);
//                if (userSex == 0) {
//                    SPUtils.put(MyPersonalInformationActivity.this, "sex", "男");
//                } else if (userSex == 1) {
//                    SPUtils.put(MyPersonalInformationActivity.this, "sex", "女");
//                }
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Message message = new Message();
//                        Bundle b = new Bundle();
//                        b.putString("resultNote", baseBean.getResult());
//                        message.setData(b);
//                        myHandler.sendMessage(message);
//                    }
//                }).start();
//            }
//        });
//    }
//
//    private Handler myHandler = new Handler() {
//        public void handleMessage(Message msg) {
//            Bundle b = msg.getData();
//            String resultNote = b.getString("resultNote");
//            if (resultNote.equals("0")) {
//                ToastUtils.showMessageShort(MyPersonalInformationActivity.this, "个人信息保存成功！");
//                finish();
//            }
//        }
//    };

    private void showChoosePicDialog() {
        builder = new AlertDialog.Builder(context, R.style.Dialog).create(); // 先得到构造器
        builder.show();
        LayoutInflater factory = LayoutInflater.from(context);
        View view = factory.inflate(R.layout.dialog_photo_upload, null);
        builder.getWindow().setContentView(view);
        TextView tv_album = (TextView) view.findViewById(R.id.tv_album);
        tv_album.setOnClickListener(this);
        TextView tv_photograph = (TextView) view.findViewById(R.id.tv_photograph);
        tv_photograph.setOnClickListener(this);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        Window dialogWindow = builder.getWindow();
        dialogWindow.setWindowAnimations(R.style.Dialog);
        dialogWindow.setGravity(Gravity.BOTTOM);//显示在底部
        WindowManager m = getWindowManager();
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        Point size = new Point();
        display.getSize(size);
        p.width = size.x;
        dialogWindow.setAttributes(p);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
        if (requestCode == 3001 && resultCode == 3002) {
            String result = data.getStringExtra("result");
            tvNickName.setText(result);
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     * @param
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            photo = ImageUtil.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
            iv_personal_icon.setImageBitmap(photo);
            Log.i("photo", "setImageToView: " + photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // 在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        String imagePath = ImageUtil.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.i("imagePath", "uploadPic: " + imagePath);
        if (imagePath != null) {
            // 拿着imagePath上传了
            img = imagePath;
            HeadPortrait = bitmap;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERAPRESS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                    //获取到了权限
                    startCamera();
                } else {
                    Toast.makeText(this, "对不起你没有同意该权限", Toast.LENGTH_LONG).show();
                }
                break;

            case ICONPRESS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                    //获取到了权限
                    startIcon();
                } else {
                    Toast.makeText(this, "对不起你没有同意该权限", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
