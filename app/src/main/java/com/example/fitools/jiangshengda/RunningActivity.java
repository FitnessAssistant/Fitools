package com.example.fitools.jiangshengda;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.example.fitools.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RunningActivity extends Activity {
    private int con_music = 1;
    private int index=0;
//    private FragmentManager manager;
    private Fragment RunFragment;
    private String speed_str;
    private TextView run_signal_text;
    private long mRecordTime;
    private TextView per_text;
    private String distance_str;
    private TextView calorie_text;
    private Chronometer timer;
    private int signal = 0;
    private static final String TAG = "LocationService";
    private RelativeLayout pause_btn;
    private Button contuine_btn;
    private Button finish_btn;
    private ImageView fog_of_war;
    private String meg = "FogMap";
    private LatLng pos1 = new LatLng(0.000000, 0.000000);
    private LatLng pos2;
    //private float distance=0.00f;
    private float avgspeed = 0.00f;
    private float sum_distance = 0.00f;
    private double longitude;
    private double latitude;
    private TextView speed_text;
    private int distance_int = 0;
    private int speed_int = 0;
    private int i = 0;
    private TextView distance_text;
    //private float sum_distance_two=0.00f;

    //声明AMapLocationClient类对象
    AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    //private float distance_two=0.00f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        timer = (Chronometer) findViewById(R.id.timer);
        pause_btn = (RelativeLayout) findViewById(R.id.button2);
        contuine_btn = (Button) findViewById(R.id.button3);
        finish_btn = (Button) findViewById(R.id.button4);
        speed_text = (TextView) findViewById(R.id.textView7);
        distance_text = (TextView) findViewById(R.id.textView11);
        fog_of_war = (ImageView) findViewById(R.id.fog_of_war_map);
        calorie_text = (TextView) findViewById(R.id.textView2);
        per_text = (TextView) findViewById(R.id.textView6);
        run_signal_text = (TextView) findViewById(R.id.textView);
        hidestatusbar();
        //设置三个按钮的可见性
        init();
        //计时器开始计时
        timer.setBase(SystemClock.elapsedRealtime());//计时器清零
        int hour = (int) ((SystemClock.elapsedRealtime() - timer.getBase()) / 1000 / 60);
        timer.setFormat("0" + String.valueOf(hour) + ":%s");
        timer.start();


        pause_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause_btn.setVisibility(View.INVISIBLE);
                contuine_btn.setVisibility(View.VISIBLE);
                finish_btn.setVisibility(View.VISIBLE);
                stopTimer();
                run_signal_text.setText("跑步暂停");
                index = 1;
                Intent intent = new Intent(RunningActivity.this, MusicServer.class);
                intent.putExtra("pause",index);
                startService(intent);
            }
        });
        contuine_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer();
                pause_btn.setVisibility(View.VISIBLE);
                contuine_btn.setVisibility(View.INVISIBLE);
                finish_btn.setVisibility(View.INVISIBLE);
                run_signal_text.setText("跑步中");
            }
        });
        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RunningActivity.this.finish();
            }
        });
        fog_of_war.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //利用bundle来存取数据
                Bundle bundle = new Bundle();
                bundle.putString("judge", meg);
                //再把bundle中的数据传给intent，以传输过去
                Intent i = new Intent(RunningActivity.this, MapActivity.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        Log.i(TAG, "start LocationService!");
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
//        mLocationOption.setOnceLocation(true);
//        mLocationOption.setOnceLocationLatest(true);
        //给定位客户端对象设置定位参数
        mLocationOption.setInterval(6000);
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    protected void onStop() {
//        stopTimer();
//        sendRunMeg();
        Intent intent = new Intent(RunningActivity.this, MusicServer.class);
        stopService(intent);
        if (null != mLocationClient) {
            mLocationClient.onDestroy();
        }
        super.onStop();
    }

    private void init() {
        pause_btn.setVisibility(View.VISIBLE);
        contuine_btn.setVisibility(View.INVISIBLE);
        finish_btn.setVisibility(View.INVISIBLE);
    }

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {

        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation == null) {
                Log.i(TAG, "amapLocation is null!");
                return;
            }
            if (amapLocation.getErrorCode() != 0) {
                Log.i(TAG, "amapLocation has exception errorCode:" + amapLocation.getErrorCode());
                return;
            }

            longitude = amapLocation.getLongitude();//获取经度
            latitude = amapLocation.getLatitude();//获取纬度
            //Toast.makeText(RunningActivity.this, "成功定位！" + longitude + "和" + latitude, Toast.LENGTH_SHORT).show();
//            String getlongitude = String.valueOf(longitude);
//            String getlatitude = String.valueOf(latitude);
            //启动跑步音乐服务
            if (signal == 0) {
                pos1 = new LatLng(latitude, longitude);
            } else {
                setRunMusic();
                judgeChangeMusic();
                getCalorie();
            }
            signal++;
        }
    };


    private void setRunMusic() {
        pos2 = new LatLng(latitude, longitude);
        //double distance_double = AMapUtils.calculateLineDistance(pos1, pos2);
        double distance_double = getDistance(pos1, pos2);
        float distance1 = (float) Math.abs(distance_double);
        distance1 = Math.round(distance1);
        pos1 = pos2;
        sum_distance = sum_distance + distance1;
        distance_str = String.valueOf(sum_distance / 1000);
        distance_text.setText(distance_str);
        avgspeed = ((distance1 / 6) * 60) / 1000;
        speed_str = String.valueOf(avgspeed);
        speed_text.setText(speed_str);
//        if (distance1 > 0) {
//            holder.distance.setText(Utils.getDisDsrc(distance1));
//        } else {
//            holder.distance.setText("");
//        }

//        distance_two = (float) (Math.round(distance * 100)) / 100;
        //Toast.makeText(RunningActivity.this,"double distance_double:"+distance_double+"距离" + distance1 + "总距离 " + sum_distance, Toast.LENGTH_SHORT).show();

//        distance_int = (int) sum_distance_two;
//        distance_text.setText(distance_int);
//        float distance_two = (float) (Math.round(distance * 1000000)) / 1000000;
//        avgspeed = (distance_two / 2) * 60;
//        if (avgspeed > 0) {
//
//        }


//        Bundle bundle_speed = new Bundle();
//        bundle_speed.putFloat("avgspeed",avgspeed);
//        Intent sendSpeed = new Intent(RunningActivity.this,MusicServer.class);
//        sendSpeed.putExtras(bundle_speed);

//        speed_int = (int) avgspeed;
//        speed_text.setText(speed_int);

    }

    private void judgeChangeMusic() {
        Intent k = new Intent(RunningActivity.this, MusicServer.class);
        startService(k);
        if (avgspeed > 0.15) {
            con_music = 0;
            Intent intent = new Intent(RunningActivity.this, MusicServer.class);
            intent.putExtra("change_music",con_music);
        }
    }

    private void getCalorie() {
        float distance_tmp = (sum_distance * 60)/1000;
        String calorie_str = String.valueOf(distance_tmp);
        calorie_text.setText(calorie_str);
        //float per_tmp = (distance_tmp/100)*100;
        per_text.setText(calorie_str+"%");
    }

    private void startTimer(){
        if (mRecordTime != 0) {
            timer.setBase(timer.getBase() + (SystemClock.elapsedRealtime() - mRecordTime));
        } else {
            timer.setBase(SystemClock.elapsedRealtime());
        }
        timer.start();
    }

    private void stopTimer(){
        timer.stop();
        mRecordTime = SystemClock.elapsedRealtime();
    }

    private void sendRunMeg(){
        RunFragment = new RunFragment();
//        manager = getFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
        Bundle sendRunMeg = new Bundle();
        sendRunMeg.putString("distance",distance_str);
        sendRunMeg.putString("speed",speed_str);
        String time_str = String.valueOf(mRecordTime);
        sendRunMeg.putString("time",time_str);
        RunFragment.setArguments(sendRunMeg);
//        transaction.add(R.id., fragment);
//        transaction.commit();

    }

    public double getDistance(LatLng start, LatLng end) {

        double lon1 = (Math.PI / 180) * start.longitude;
        double lon2 = (Math.PI / 180) * end.longitude;
        double lat1 = (Math.PI / 180) * start.latitude;
        double lat2 = (Math.PI / 180) * end.latitude;
        //System.out.println("start  " + start.longitude + "2:" + start.latitude + "end   " + end.longitude + "2:" + end.latitude);
        // 地球半径
        double R = 6371;
        // 两点间距离 km，如果想要米的话，结果*1000就可以了
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;
        return d * 1000;
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
