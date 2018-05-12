package com.example.fitools.jiangshengda;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitools.R;
import com.example.fitools.shenyue.AppUtil;
import com.example.fitools.shenyue.CountdownTextView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;

public class SignupActivity extends AppCompatActivity {
    private EditText accountet;
    private EditText passwordet;
    private EditText identifyet;
    private Button signupbt;
    private CheckBox passwordcb;
    private TextView identifycodetv;
    private CountdownTextView timetv;
    int utilbt = 0;
    private AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler(){
        @Override
        public void onSuccess(int i, Header[] headers, byte[] bytes) {
            try {
                String str1 = new String(bytes,"UTF-8");
                if (str1.equals("注册成功")){
                    LoginActivity.actionStartActivity(SignupActivity.this,"ONE");
                }else {
                    Toast.makeText(getApplicationContext(),str1, Toast.LENGTH_SHORT).show();
                }
            }catch (UnsupportedEncodingException e){
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
        setContentView(R.layout.jsd_signup);
        getViews();
        setListener();
        hidestatusbar();
    }
    /**
     * 获得界面控件
     */
    private void getViews(){
        accountet = (EditText)findViewById(R.id.signup_account_et);
        passwordet = (EditText)findViewById(R.id.signup_password_et);
        identifyet = (EditText)findViewById(R.id.signup_identify_et);
        signupbt = (Button)findViewById(R.id.signup_sign_btn);
        passwordcb = (CheckBox)findViewById(R.id.signup_password_cb);
        identifycodetv = (TextView) findViewById(R.id.signup_identifycode_bt);
        timetv = (CountdownTextView)findViewById(R.id.signup_identifytimer_tv);
    }
    /**
     * 注册监听器
     */
    private void setListener(){
        identifycodetv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timetv.getVisibility() == View.INVISIBLE){
                    timetv.setVisibility(View.VISIBLE);
                    timetv.init(2,"倒计时",60);
                    timetv.start(100);
                }
            }
        });
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SignupActivity.this.finish();
//            }
//        });
        passwordcb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //选择状态 显示明文--设置为可见的密码
                    passwordet.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD
                    passwordet.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        accountet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if((!accountet.getText().toString().trim().equals(""))&&(!passwordet.getText().toString().trim().equals(""))&&(!identifyet.getText().toString().trim().equals(""))){
                    signupbt.setTextColor(ContextCompat.getColor(SignupActivity.this,R.color.colorWhite));
                    utilbt = 1;
                }else {
                    signupbt.setTextColor(ContextCompat.getColor(SignupActivity.this,R.color.gray_word));
                    utilbt = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passwordet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if((!accountet.getText().toString().trim().equals(""))&&(!passwordet.getText().toString().trim().equals(""))&&(!identifyet.getText().toString().trim().equals(""))){
                    signupbt.setTextColor(ContextCompat.getColor(SignupActivity.this,R.color.colorWhite));
                    utilbt = 1;
                }else {
                    signupbt.setTextColor(ContextCompat.getColor(SignupActivity.this,R.color.gray_word));
                    utilbt = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        identifyet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if((!accountet.getText().toString().trim().equals(""))&&(!passwordet.getText().toString().trim().equals(""))&&(!identifyet.getText().toString().trim().equals(""))){
                    signupbt.setTextColor(ContextCompat.getColor(SignupActivity.this,R.color.colorWhite));
                    utilbt = 1;
                }else {
                    signupbt.setTextColor(ContextCompat.getColor(SignupActivity.this,R.color.gray_word));
                    utilbt = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        signupbt.setOnTouchListener(new Button.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if(utilbt == 0){

                    }else {
                        signupbt.setBackgroundColor(ContextCompat.getColor(SignupActivity.this, R.color.xblack));
                    }
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    if(utilbt == 0){

                    }else {
                        signupbt.setBackgroundColor(ContextCompat.getColor(SignupActivity.this, R.color.sblack));
                        Thread thread = new Thread(){
                            @Override
                            public void run(){
                                super.run();
                                synhttprequestsignup();
                            }
                        };
                        thread.start();
                    }

                }
                return false;
            }
        });
    }
    public void synhttprequestsignup(){
        AsyncHttpClient client = new AsyncHttpClient();
        String Url = "http://"+ AppUtil.JFinalServer.HOST+":"+AppUtil.JFinalServer.PORT+"/user/regist";
        RequestParams params = new RequestParams();
        params.add("user_name", accountet.getText().toString());
        params.add("user_pwd", passwordet.getText().toString());
        client.get(Url, params, mHandler);
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
