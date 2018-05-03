package com.example.fitools.jiangshengda;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fitools.R;

public class RunCountdownActivity extends AppCompatActivity {
    private TextView countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsd_activity_run_countdown);
        countdown = (TextView) findViewById(R.id.count_down_text);
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
}
