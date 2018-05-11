package com.example.fitools.jiangshengda;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.fitools.R;

public class WelcomeFourActivity extends AppCompatActivity {
    private CheckBox hiitcb;
    private CheckBox musclecb;
    private CheckBox mouldingcb;
    private CheckBox healthcb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsd_welcome4);
        getViews();
        setListener();
        hidestatusbar();
    }
    void getViews(){
        hiitcb = (CheckBox)findViewById(R.id.welcome4_hiit_cb);
        musclecb = (CheckBox)findViewById(R.id.welcome4_muscle_cb);
        mouldingcb = (CheckBox)findViewById(R.id.welcome4_moulding_cb);
        healthcb = (CheckBox)findViewById(R.id.welcome4_health_cb);
    }
    void setListener(){
        Mylistener mylistener = new Mylistener();
        hiitcb.setOnCheckedChangeListener(mylistener);
        musclecb.setOnCheckedChangeListener(mylistener);
        mouldingcb.setOnCheckedChangeListener(mylistener);
        healthcb.setOnCheckedChangeListener(mylistener);

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
