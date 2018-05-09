package com.example.fitools.jiangshengda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.fitools.R;

public class RunningActivity extends AppCompatActivity {
    private RelativeLayout pause_btn;
    private Button contuine_btn;
    private Button finish_btn;
    private ImageView fog_of_war;
    private String meg="FogMap";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent k = new Intent(RunningActivity.this,MusicServer.class);
        startService(k);
        setContentView(R.layout.activity_running);
        pause_btn = (RelativeLayout) findViewById(R.id.button2);
        contuine_btn = (Button) findViewById(R.id.button3);
        finish_btn = (Button) findViewById(R.id.button4);
        init();
        fog_of_war = (ImageView) findViewById(R.id.fog_of_war_map);
        fog_of_war.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //利用bundle来存取数据
                Bundle bundle=new Bundle();
                bundle.putString("judge",meg);
                //再把bundle中的数据传给intent，以传输过去
                Intent i = new Intent(RunningActivity.this,MapActivity.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onStop(){
        Intent intent = new Intent(RunningActivity.this,MusicServer.class);
        stopService(intent);
        super.onStop();

    }

    private void init(){
        pause_btn.setVisibility(View.VISIBLE);
        contuine_btn.setVisibility(View.INVISIBLE);
        finish_btn.setVisibility(View.INVISIBLE);
    }
}
