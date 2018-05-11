package com.example.fitools.jiangshengda;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fitools.caizihuan.DynamicFragment;
import com.example.fitools.caizihuan.FindFragment;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import com.example.fitools.R;

public class MainActivity extends AppCompatActivity {

    private GoogleApiClient client;
    public static final String USERID = "LoginActivity.USERID";
    private MyFragment mMy;
    private FindFragment mFind;
    private FitFragment mFit;
    private DynamicFragment mDynamic;
    private ImageView fitIv,findIv,dynamicIv,myIv;
    private TextView fitTv,findTv,dynamicTv,myTv;
    private LinearLayout mainll;
    private RelativeLayout fitRl,dynamicRl,findRl,myRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
        getViews();
        setListener();
        setFitPage();
        hidestatusbar();
        // 给各页面设置flag
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        switch (utils.flag){
            case 1://显示健身页面
                setFitPage();
                break;
            case 2://显示动态页面
                setDynamicPage();
                break;
            case 3://显示发现页面
                setFindPage();
                break;
            case 4://显示我的页面
                setMyPage();
                break;
        }
    }

    /**
     * 初始化Fragment
     */
    private void initFragments(){
        mDynamic = new DynamicFragment();
        mFit = new FitFragment();
        mFind = new FindFragment();
        mMy = new MyFragment();
    }

    /**
     * 设置健身界面
     */
    private void setFitPage(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_fl,mFit);
        fitIv.setImageResource(R.mipmap.fit_checked);
        fitTv.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black_icon));
        ft.commit();
        mainll.invalidate();
    }

    //切换到动态页面
    private void setDynamicPage() {
        clearSelection();
        //1、获取一个FragmentManager对象
        FragmentManager fm = getFragmentManager();
        //2、获取FragmentTransaction对象
        FragmentTransaction transaction = fm.beginTransaction();
        dynamicIv.setImageResource(R.mipmap.dynamic_checked);
        dynamicTv.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black_icon));
        if (mDynamic == null) {
            mDynamic = new DynamicFragment();
        }
        //3、设置页面
        transaction.replace(R.id.main_fl, mDynamic);
        //4、执行更改
        transaction.commit();
    }

    //切换到发现页面
    private void setFindPage() {
        clearSelection();
        //1、获取一个FragmentManager对象
        FragmentManager fm = getFragmentManager();
        //2、获取FragmentTransaction对象
        FragmentTransaction transaction = fm.beginTransaction();
        findIv.setImageResource(R.mipmap.find_checked);
        findTv.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black_icon));
        if (mFind == null) {
            mFind = new FindFragment();
        }
        //3、设置页面
        transaction.replace(R.id.main_fl, mFind);
        //4、执行更改
        transaction.commit();
    }

    //切换到我的页面
    private void setMyPage() {
        clearSelection();
        //1、获取一个FragmentManager对象
        FragmentManager fm = getFragmentManager();
        //2、获取FragmentTransaction对象
        FragmentTransaction transaction = fm.beginTransaction();
        if (mMy == null) {
            mMy = new MyFragment();
        }
        //3、设置页面
        transaction.replace(R.id.main_fl, mMy);
        myIv.setImageResource(R.mipmap.my_checked);
        myTv.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black_icon));
        //4、执行更改
        transaction.commit();
    }

    /**
     * 获取界面控件
     */
    private void getViews(){
        mainll = (LinearLayout)findViewById(R.id.main_ll);
        fitRl = (RelativeLayout)findViewById(R.id.main_fit_rl);
        dynamicRl = (RelativeLayout)findViewById(R.id.main_dynamic_rl);
        findRl = (RelativeLayout)findViewById(R.id.main_find_rl);
        myRl = (RelativeLayout)findViewById(R.id.main_my_rl);
        fitIv = (ImageView)findViewById(R.id.main_fit_img);
        dynamicIv = (ImageView)findViewById(R.id.main_dynamic_img);
        findIv = (ImageView)findViewById(R.id.main_find_img);
        myIv = (ImageView)findViewById(R.id.main_my_img);
        fitTv = (TextView)findViewById(R.id.main_fit_tv);
        dynamicTv = (TextView)findViewById(R.id.main_dynamic_tv);
        findTv = (TextView)findViewById(R.id.main_find_tv);
        myTv = (TextView)findViewById(R.id.main_my_tv);
    }
    /**
     * 注册事件监听器
     */
    private void setListener(){
        fitRl.setOnClickListener(new MyListener());
        dynamicRl.setOnClickListener(new MyListener());
        findRl.setOnClickListener(new MyListener());
        myRl.setOnClickListener(new MyListener());
    }
    public static void actionStartActivity(Context packageContext, String id){
        Intent i = new Intent(packageContext, MainActivity.class);
        i.putExtra(USERID, id);
        packageContext.startActivity(i);

    }
    /**
     * 定义底栏点击事件
     */
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            clearSelection();
            switch (v.getId()){
                case R.id.main_fit_rl:
                    ft.replace(R.id.main_fl,mFit);
                    fitIv.setImageResource(R.mipmap.fit_checked);
                    fitTv.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black_icon));
                    break;
                case R.id.main_dynamic_rl:
                    setDynamicPage();
                    break;
                case R.id.main_find_rl:
                    setFindPage();
                    break;
                case R.id.main_my_rl:
                    setMyPage();
                    break;
            }
            ft.commit();
            mainll.invalidate();
        }
    }
    /**
     * 清除因为上一次选择发生的变化
     */
    private void clearSelection(){
        fitIv.setImageResource(R.mipmap.fit_normal);
        fitTv.setTextColor(ContextCompat.getColor(this, R.color.gray_word));
        dynamicIv.setImageResource(R.mipmap.dynamic_normal);
        dynamicTv.setTextColor(ContextCompat.getColor(this, R.color.gray_word));
        findIv.setImageResource(R.mipmap.find_normal);
        findTv.setTextColor(ContextCompat.getColor(this, R.color.gray_word));
        myIv.setImageResource(R.mipmap.my_normal);
        myTv.setTextColor(ContextCompat.getColor(this, R.color.gray_word));
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
