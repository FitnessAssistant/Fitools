package com.example.fitools.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fitools.R;

import java.util.Calendar;

public class WelcomeTwoActivity extends AppCompatActivity {
    private TextView tvBirthDate;
    private CheckBox mancb;
    private CheckBox womencb;
    private RelativeLayout nextrl;
    public static String SEX = "SEX";
    public static String BIRTHDAY = "BIRTHDAY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome2);
        hidestatusbar();
        getViews();
        setListener();
    }
    void getViews(){
        tvBirthDate = (TextView)findViewById(R.id.welcome2_birthdayicon_tv);
        mancb = (CheckBox) findViewById(R.id.welcome2_man_cb);
        womencb = (CheckBox) findViewById(R.id.welcome2_women_cb);
        nextrl = (RelativeLayout)findViewById(R.id.welcome2_next_rl);
    }
    void setListener(){
        MyListener listener = new MyListener();
        tvBirthDate.setOnClickListener(listener);
        nextrl.setOnClickListener(listener);
        mancb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mancb.setTextColor(getResources().getColor(R.color.white));
                    womencb.setChecked(false);
                    womencb.setTextColor(getResources().getColor(R.color.gray_word));
                }else {
                    if (!womencb.isChecked()){
                        mancb.setChecked(true);
                    }
                }
            }
        });
        womencb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    womencb.setTextColor(getResources().getColor(R.color.white));
                    mancb.setChecked(false);
                    mancb.setTextColor(getResources().getColor(R.color.gray_word));
                }else {
                    if (!mancb.isChecked()){
                        womencb.setChecked(true);
                    }
                }
            }
        });
    }
    /**
     * 定义上栏点击事件
     */
    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.welcome2_birthdayicon_tv:
                    showDatePickDlg();
                    break;
                case R.id.welcome2_next_rl:
                    if (mancb.isChecked()){
                        SEX = "0";
                    }else {
                        SEX = "1";
                    }
                    BIRTHDAY = tvBirthDate.getText().toString();
                    Intent intent = new Intent(WelcomeTwoActivity.this, WelcomeThreeActivity.class);
                    startActivity(intent);
            }
        }
    }
    /*
     * 弹出日期选择框
     */
    protected void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(WelcomeTwoActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                WelcomeTwoActivity.this.tvBirthDate.setText(year + "年" + (monthOfYear+1) + "月" + dayOfMonth + "日");
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
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
