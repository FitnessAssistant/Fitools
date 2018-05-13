package com.example.fitools.Activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.example.fitools.R;
import com.example.fitools.Utils.AppUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WelcomeFourActivity extends AppCompatActivity {
    private CheckBox hiitcb;
    private CheckBox musclecb;
    private CheckBox mouldingcb;
    private CheckBox healthcb;
    private RelativeLayout nextrl;
    private String str2;
    public static String GOALS = "GOALS";
    private AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler(){
        @Override
        public void onSuccess(int i, Header[] headers, byte[] bytes) {
            try {
                String str1 = new String(bytes,"UTF-8");
                MainActivity.actionStartActivity(WelcomeFourActivity.this, str1);

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
        setContentView(R.layout.welcome4);
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd H-m-ss ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        str2 = formatter.format(curDate);
        System.out.println(str2 +"hhhhhhhhhhhhhhhhhhhhhhhh");
        getViews();
        setListener();
        hidestatusbar();
    }
    void getViews(){
        hiitcb = (CheckBox)findViewById(R.id.welcome4_hiit_cb);
        musclecb = (CheckBox)findViewById(R.id.welcome4_muscle_cb);
        mouldingcb = (CheckBox)findViewById(R.id.welcome4_moulding_cb);
        healthcb = (CheckBox)findViewById(R.id.welcome4_health_cb);
        nextrl = (RelativeLayout) findViewById(R.id.welcome4_next_rl);
    }
    void setListener(){
        Mylistener mylistener = new Mylistener();
        hiitcb.setOnCheckedChangeListener(mylistener);
        musclecb.setOnCheckedChangeListener(mylistener);
        mouldingcb.setOnCheckedChangeListener(mylistener);
        healthcb.setOnCheckedChangeListener(mylistener);
        nextrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hiitcb.isChecked()){
                    GOALS = "0";
                }else if (musclecb.isChecked()){
                    GOALS = "1";
                }else if (mouldingcb.isChecked()){
                    GOALS = "2";
                }else if (healthcb.isChecked()){
                    GOALS = "3";
                }
                Thread thread = new Thread(){
                    @Override
                    public void run(){
                        super.run();
                        synhttprequestwellogin();
                    }
                };
                thread.start();
            }
        });

    }
    class Mylistener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()){
                case R.id.welcome4_hiit_cb:
                    if (isChecked){
                        hiitcb.setTextColor(getResources().getColor(R.color.white));
                        musclecb.setChecked(false);
                        mouldingcb.setChecked(false);
                        healthcb.setChecked(false);
                        musclecb.setTextColor(getResources().getColor(R.color.gray_word));
                        mouldingcb.setTextColor(getResources().getColor(R.color.gray_word));
                        healthcb.setTextColor(getResources().getColor(R.color.gray_word));
                    }else {
                        if (!(healthcb.isChecked()||musclecb.isChecked()||mouldingcb.isChecked())){
                            hiitcb.setChecked(true);
                        }
                    }
                    break;
                case R.id.welcome4_muscle_cb:
                    if (isChecked){
                        musclecb.setTextColor(getResources().getColor(R.color.white));
                        hiitcb.setChecked(false);
                        mouldingcb.setChecked(false);
                        healthcb.setChecked(false);
                        hiitcb.setTextColor(getResources().getColor(R.color.gray_word));
                        mouldingcb.setTextColor(getResources().getColor(R.color.gray_word));
                        healthcb.setTextColor(getResources().getColor(R.color.gray_word));
                    }else {
                        if (!(hiitcb.isChecked()||healthcb.isChecked()||mouldingcb.isChecked())){
                            musclecb.setChecked(true);
                        }
                    }
                    break;
                case R.id.welcome4_moulding_cb:
                    if (isChecked){
                        mouldingcb.setTextColor(getResources().getColor(R.color.white));
                        musclecb.setChecked(false);
                        hiitcb.setChecked(false);
                        healthcb.setChecked(false);
                        musclecb.setTextColor(getResources().getColor(R.color.gray_word));
                        hiitcb.setTextColor(getResources().getColor(R.color.gray_word));
                        healthcb.setTextColor(getResources().getColor(R.color.gray_word));
                    }else {
                        if (!(hiitcb.isChecked()||musclecb.isChecked()||healthcb.isChecked())){
                            mouldingcb.setChecked(true);
                        }
                    }
                    break;
                case R.id.welcome4_health_cb:
                    if (isChecked){
                        healthcb.setTextColor(getResources().getColor(R.color.white));
                        musclecb.setChecked(false);
                        mouldingcb.setChecked(false);
                        hiitcb.setChecked(false);
                        musclecb.setTextColor(getResources().getColor(R.color.gray_word));
                        mouldingcb.setTextColor(getResources().getColor(R.color.gray_word));
                        hiitcb.setTextColor(getResources().getColor(R.color.gray_word));
                    }else {
                        if (!(hiitcb.isChecked()||musclecb.isChecked()||mouldingcb.isChecked())){
                            healthcb.setChecked(true);
                        }
                    }
                    break;
            }
        }
    }
    public void synhttprequestwellogin(){
        AsyncHttpClient client = new AsyncHttpClient();
        String Url = "http://"+ AppUtil.JFinalServer.HOST+":"+AppUtil.JFinalServer.PORT+"/user/edit1";
        RequestParams params = new RequestParams();
        params.add("user_id", LoginActivity.USER);
        params.add("nick_name", WelcomeOneActivity.NICKNAME);
        params.add("user_sex", WelcomeTwoActivity.SEX);
        params.add("birthday", "1996-4-21");
        params.add("user_height",WelcomeThreeActivity.HEIGHT);
        params.add("user_weight",WelcomeThreeActivity.WEIGHT);
        params.add("user_goals",WelcomeFourActivity.GOALS);
        params.add("time",str2);
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
