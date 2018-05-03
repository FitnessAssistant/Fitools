package com.example.fitools.jiangshengda;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.fitools.R;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class RunTopActivity extends AppCompatActivity {
    private ImageView back_img;
    private RunTopWeekendsFragment mWeekends;
    private GoogleApiClient client;
    private RunTopMonthsFragment mMonths;
    private LinearLayout runtop_ll;
    private RelativeLayout weekends_btn;
    private RelativeLayout months_btn;
    private ImageView weekends_line;
    private ImageView months_line;


    /**
     * 初始化Fragment
     */
    private void initFragments(){
        mWeekends = new RunTopWeekendsFragment();
        mMonths = new RunTopMonthsFragment();
    }

    /**
     * 设置周排行界面
     */
    private void setWeekendsPage(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.run_top_fl,mWeekends);
        ft.commit();
        weekends_line.setVisibility(View.VISIBLE);
        months_line.setVisibility(View.INVISIBLE);
        runtop_ll.invalidate();
    }

    //切换到月排行页面
    private void setMonthsPage() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (mMonths == null) {
            mMonths = new RunTopMonthsFragment();
        }
        //3、设置页面
        transaction.replace(R.id.run_top_fl, mMonths);
        //4、执行更改
        transaction.commit();
    }

    /**
     * 注册事件监听器
     */
    private void setListener(){
        weekends_btn.setOnClickListener(new RunTopActivity.MyListener());
        months_btn.setOnClickListener(new RunTopActivity.MyListener());
    }
    /**
     * 定义底栏点击事件
     */
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            switch (v.getId()){
                case R.id.runtop_weekends_btn:
                    ft.replace(R.id.run_top_fl,mWeekends);
                    weekends_line.setVisibility(View.VISIBLE);
                    months_line.setVisibility(View.INVISIBLE);
                    break;
                case R.id.runtop_month_btn:
                    setMonthsPage();
                    weekends_line.setVisibility(View.INVISIBLE);
                    months_line.setVisibility(View.VISIBLE);
                    break;
            }
            ft.commit();
            runtop_ll.invalidate();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsd_run_top);
        back_img = (ImageView) findViewById(R.id.jsd_run_top_return_btn);

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RunTopActivity.this.finish();
            }
        });

        runtop_ll = (LinearLayout) findViewById(R.id.run_top_ll);
        weekends_btn = (RelativeLayout) findViewById(R.id.runtop_weekends_btn);
        months_btn = (RelativeLayout) findViewById(R.id.runtop_month_btn);
        weekends_line = (ImageView) findViewById(R.id.run_weekends_bottomline);
        months_line = (ImageView) findViewById(R.id.run_months_bottomline);

        initFragments();
        setListener();
        setWeekendsPage();
        // 给各页面设置flag
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        switch (utils.flag){
            case 1://显示周排行页面
                setWeekendsPage();
                break;
            case 2://显示月排行页面
                setMonthsPage();
                break;
        }
    }
}
