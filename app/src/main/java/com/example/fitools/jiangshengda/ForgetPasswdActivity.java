package com.example.fitools.jiangshengda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.fitools.R;

public class ForgetPasswdActivity extends AppCompatActivity {
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsd_forget_pawd);
        back = (ImageView) findViewById(R.id.jsd_forgetpasswd_return_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgetPasswdActivity.this.finish();
            }
        });
    }
}
