package com.example.fitools.jiangshengda;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.fitools.R;

public class RunCountdownActivity extends AppCompatActivity {
    private TextView countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsd_activity_run_countdown);
        countdown = (TextView) findViewById(R.id.count_down_text);
        hidestatusbar();
        CountDownTimer timer = new CountDownTimer(4*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                countdown.setText(" "+millisUntilFinished/1000+" ");
            }

            @Override
            public void onFinish() {
                countdown.setText("GO!");
                Intent i = new Intent(RunCountdownActivity.this,RunningActivity.class);
                startActivity(i);
                RunCountdownActivity.this.finish();
            }
        }.start();


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
