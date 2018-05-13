package com.example.fitools.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;
import android.app.Activity;

import com.example.fitools.R;
import com.example.fitools.View.CircleImageView;
import com.example.fitools.Utils.AppUtil;

import org.json.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

public class EditPersonalActivity extends Activity {

    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    private static final String TAG = "EditPersonalActivity";
    protected static Uri tempUri;
    private ImageView ivToSetting;
    private RelativeLayout rlToDatePick, rlToSexPick, rlToHeader, rlToNickName;
    private TextView tvNickName, tvBirthDate, tvSexValue, tvCityValue, tvSave;
    private com.example.fitools.View.CircleImageView circleImageView;

    private AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int i, Header[] headers, byte[] bytes) {
            try {
                String str1 = new String(bytes, "UTF-8");
                MainActivity.actionStartActivity(EditPersonalActivity.this, str1);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal);
        getViews();
        setListener();
        hidestatusbar();
    }

    /*
    * 获取界面控件
    **/
    public void getViews() {
        ivToSetting = (ImageView) findViewById(R.id.czh_to_setting_btn);
        rlToDatePick = (RelativeLayout) findViewById(R.id.czh_birth_rl);
        rlToSexPick = (RelativeLayout) findViewById(R.id.czh_sex_rl);
        rlToHeader = (RelativeLayout) findViewById(R.id.czh_headerimg_rl);
        rlToNickName = (RelativeLayout) findViewById(R.id.czh_nickname_rl);
        tvNickName = (TextView) findViewById(R.id.czh_nickname_tv);
        tvBirthDate = (TextView) findViewById(R.id.czh_birth_tv);
        tvSexValue = (TextView) findViewById(R.id.czh_sex_tv);
        tvCityValue = (TextView) findViewById(R.id.czh_city_tv);
        tvSave = (TextView) findViewById(R.id.czh_savepersonal_tv);
        circleImageView = (CircleImageView) findViewById(R.id.CivHeader);
    }

    /*
    * 控件绑定监听事件
    * */
    private void setListener() {
        MyListener listener = new MyListener();
        ivToSetting.setOnClickListener(listener);
        rlToDatePick.setOnClickListener(listener);
        rlToSexPick.setOnClickListener(listener);
        rlToHeader.setOnClickListener(listener);
        rlToNickName.setOnClickListener(listener);
        tvSave.setOnClickListener(listener);
    }

    /**
     * 定义上栏点击事件
     */
    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.czh_to_setting_btn:
                    Intent intent = new Intent(EditPersonalActivity.this, com.example.fitools.Activity.SettingActivity.class);
                    startActivity(intent);
                    //设置跳转动画
                    overridePendingTransition(R.anim.czh_anim_slide_in_left, R.anim.czh_anim_slide_in_right);
                    break;
                case R.id.czh_birth_rl:
                    showDatePickDlg();
                    break;
                case R.id.czh_sex_rl:
                    showSexPickDlg();
                    break;
                case R.id.czh_headerimg_rl:
                    showChoosePicDialog(v);
                    break;
                case R.id.czh_nickname_rl:
                    showNickNameDialog();
                    break;
                case R.id.czh_savepersonal_tv:
                    synhttprequestedit();
                    break;
            }
        }
    }

    /*
    * 向后台提交编辑请求
    **/
    public void synhttprequestedit() {
        AsyncHttpClient client = new AsyncHttpClient();

        JSONObject jsonObject = new JSONObject();
        RequestParams params = new RequestParams();
        String Url = "http://" + AppUtil.JFinalServer.HOST + ":" + AppUtil.JFinalServer.PORT + "/user/edit_later";
        params.add("user_id", MainActivity.USERID);
        String newcity = null,newnickname = null;
        try {
            newnickname = new String(tvNickName.getText().toString().getBytes("UTF-8"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        params.add("user_nickname", newnickname);
        if (tvSexValue.getText().toString().equals("男")) {
            params.add("user_sex", "0");
        } else if (tvSexValue.getText().toString().equals("女")) {
            params.add("user_sex", "1");
        }
        params.add("user_birthday", tvBirthDate.getText().toString());
        client.get(Url, params, mHandler);
    }

    /*
    * 弹出日期选择框
    */
    protected void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(EditPersonalActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                EditPersonalActivity.this.tvBirthDate.setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    /*
   * 弹出性别选择框
   */
    protected void showSexPickDlg() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this); //定义一个AlertDialog
        builder.setTitle(" 修改性别");
        final String[] strarr = {"男","女"};
        String value = tvSexValue.getText().toString();

        int valueint = 2;
        if(value.equals("女")){
            valueint = 1;
        }else{
            valueint = 0;
        }
        builder.setSingleChoiceItems(strarr, valueint, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                tvSexValue.setText(strarr[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder.show();
    }

    /*
    * 弹出昵称输入框
    * */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showNickNameDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        final View view = View.inflate(this, R.layout.czh_dialoglayout, null);
        dialog.setView(view, 0, 0, 0, 0);
        Button btnOK = (Button) view.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        final EditText editText =(EditText) view.findViewById(R.id.nickname_et);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvNickName.setText(editText.getText().toString());
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();// 隐藏dialog
            }
        });
        dialog.show();
    }

    /**
     * 显示修改头像的对话框
     */
    public void showChoosePicDialog(View v) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        takePicture();
                        break;
                }
            }
        });
        builder.create().show();
    }
    private void takePicture() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 23) {
            // 需要申请动态权限
            int check = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        Intent openCameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment
                .getExternalStorageDirectory(), "image.jpg");
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= 24) {
            openCameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            tempUri = FileProvider.getUriForFile(EditPersonalActivity.this, "com.lt.uploadpicdemo.fileProvider", file);
        } else {
            tempUri = Uri.fromFile(new File(Environment
                    .getExternalStorageDirectory(), "image.jpg"));
        }
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
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
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Log.d(TAG,"setImageToView:"+photo);
            photo = com.example.fitools.Utils.ImageUtils.toRoundBitmap(photo); // 这个时候的图片已经被处理成圆形的了
            circleImageView.setImageBitmap(photo);
            uploadPic(photo);
        }
    }
    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了
        String imagePath = com.example.fitools.Utils.ImageUtils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.e("imagePath", imagePath+"");
        if(imagePath != null){
            // 拿着imagePath上传了
            // ...
            Log.d(TAG,"imagePath:"+imagePath);
        }
    }
    /**
     * 隐藏状态栏
     */
    private void hidestatusbar(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }
}
