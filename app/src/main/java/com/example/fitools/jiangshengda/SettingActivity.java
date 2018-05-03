package com.example.fitools.jiangshengda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.fitools.R;

public class SettingActivity extends AppCompatActivity {
    private ImageView back_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsd_activity_setting);
        back_img = (ImageView) findViewById(R.id.jsd_setting_return_btn);

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            SettingActivity.this.finish();
            }
        });

    }
}
