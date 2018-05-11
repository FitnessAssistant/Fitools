package com.example.fitools.jiangshengda;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private static final String TAG = "LocationService";
    private RelativeLayout pause_btn;
    private Button contuine_btn;
    private Button finish_btn;
    private ImageView fog_of_war;
    private String meg="FogMap";
    private LatLng pos1= new LatLng(0.000000,0.000000),pos2;
    private float distance;
    private float avgspeed = (float) 0.000;
    private float sum_distance;
    private double longitude;
    private double latitude;
    private TextView speed_text;
    private int speed_int = 0;

    //声明AMapLocationClient类对象
    AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent k = new Intent(RunningActivity.this,MusicServer.class);
        startService(k);

        setContentView(R.layout.activity_running);
        pause_btn = (RelativeLayout) findViewById(R.id.button2);
        contuine_btn = (Button) findViewById(R.id.button3);
        finish_btn = (Button) findViewById(R.id.button4);
        speed_text = (TextView) findViewById(R.id.textView7);
        fog_of_war = (ImageView) findViewById(R.id.fog_of_war_map);
        init();
        setGetDistance();
        //这里应该放到回调函数里

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
    }

    @Override
    protected void onStart() {
        getPosition();
        super.onStart();
    }

    @Override
    protected void onStop(){
        Intent intent = new Intent(RunningActivity.this,MusicServer.class);
        stopService(intent);
        super.onStop();

    }

    public void getPosition() {
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    private void init(){
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
            Toast.makeText(RunningActivity.this,"成功启动定位！"+longitude+"和"+latitude, Toast.LENGTH_SHORT).show();
//            String getlongitude = String.valueOf(longitude);
//            String getlatitude = String.valueOf(latitude);
            setGetDistance();

        }
    };


    private void setGetDistance(){
        pos2 = new LatLng(longitude,latitude);
        if (pos2!=null){
            distance = AMapUtils.calculateLineDistance(pos1,pos2);
            pos1 = pos2;
            sum_distance += distance;
        }
        avgspeed = distance/1;
        //speed_int = (int)avgspeed;
        //speed_text.setText(speed_int);
    }


}
