package com.example.fitools.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.fitools.R;

public class WelcomeThreeActivity extends AppCompatActivity {
    private EditText statureet;
    private EditText weightet;
    private RelativeLayout nextrl;
    public static String HEIGHT = "HEIGHT";
    public  static String WEIGHT = "WEIGHT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome3);
        getViews();
        setlistener();
        hidestatusbar();
    }
    void getViews(){
        statureet = (EditText)findViewById(R.id.welcome3_stature_et);
        weightet = (EditText)findViewById(R.id.welcome3_weight_et);
        nextrl = (RelativeLayout)findViewById(R.id.welcome3_next_rl);
    }
    void setlistener(){
        nextrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HEIGHT = statureet.getText().toString();
                WEIGHT = weightet.getText().toString();
                if ((!HEIGHT.equals(""))&&(!WEIGHT.equals(""))){
                    Intent intent = new Intent(WelcomeThreeActivity.this,WelcomeFourActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(WelcomeThreeActivity.this,"身高、体重不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
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
