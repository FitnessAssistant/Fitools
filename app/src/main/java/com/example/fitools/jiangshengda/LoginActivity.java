package com.example.fitools.jiangshengda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitools.R;

public class LoginActivity extends AppCompatActivity {
    private TextView newuser;
    private ImageView back;
    private TextView forgetpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsd_register);
        newuser = (TextView) findViewById(R.id.jsd_register_newuser_signup);
        back = (ImageView) findViewById(R.id.jsd_signup_return_btn);
        forgetpwd = (TextView) findViewById(R.id.jsd_register_forgetpasswd);
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.this.finish();
            }
        });
        forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(LoginActivity.this,ForgetPasswdActivity.class);
                startActivity(j);
            }
        });
    }
}
