package com.example.fitools.Fragment;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.fitools.R;
import com.example.fitools.Utils.utils;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class FitFragment extends Fragment {

    private TrainFragment mTrain;
    private GoogleApiClient client;
    private RunFragment mRun;
    private LinearLayout fitll;
    private RelativeLayout exercise_btn;
    private RelativeLayout run_btn;
    private ImageView train_line;
    private ImageView run_line;
    private View fitlayout;

    /**
     * 初始化Fragment
     */
    private void initFragments(){
        mTrain = new TrainFragment();
        mRun = new RunFragment();
    }

    /**
     * 设置锻炼界面
     */
    private void setTrainPage(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fit_fl,mTrain);
        ft.commit();
        train_line.setVisibility(View.VISIBLE);
        run_line.setVisibility(View.INVISIBLE);
        fitll.invalidate();
    }

    //切换到跑步页面
    private void setRunPage() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (mRun == null) {
            mRun = new RunFragment();
        }
        //3、设置页面
        transaction.replace(R.id.fit_fl, mRun);
        //4、执行更改
        transaction.commit();
    }

    /**
     * 注册事件监听器
     */
    private void setListener(){
        exercise_btn.setOnClickListener(new MyListener());
        run_btn.setOnClickListener(new MyListener());
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
                case R.id.exercise_btn:
                    ft.replace(R.id.fit_fl,mTrain);
                    train_line.setVisibility(View.VISIBLE);
                    run_line.setVisibility(View.INVISIBLE);
                    break;
                case R.id.run_btn:
                    setRunPage();
                    train_line.setVisibility(View.INVISIBLE);
                    run_line.setVisibility(View.VISIBLE);
                    break;
            }
            ft.commit();
            fitll.invalidate();
        }
    }

    public FitFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(null == fitlayout) {//防止Layout重复加载数据
            fitlayout = inflater.inflate(R.layout.fragment_fit, null);

            fitll = (LinearLayout) fitlayout.findViewById(R.id.fit_ll);
            exercise_btn = (RelativeLayout) fitlayout.findViewById(R.id.exercise_btn);
            run_btn = (RelativeLayout) fitlayout.findViewById(R.id.run_btn);
            train_line = (ImageView) fitlayout.findViewById(R.id.exercise_bottomline);
            run_line = (ImageView) fitlayout.findViewById(R.id.run_bottomline);

            initFragments();
            setListener();
            setTrainPage();
            // 给各页面设置flag
            client = new GoogleApiClient.Builder(getActivity()).addApi(AppIndex.API).build();
            switch (utils.flag){
                case 1://显示锻炼页面
                    setTrainPage();
                    break;
                case 2://显示跑步页面
                    setRunPage();
                    break;
            }
        }else {
            ViewGroup parent = (ViewGroup)fitlayout.getParent();
            if(null != parent){
                parent.removeView(fitlayout);
            }
        }

        return fitlayout;
    }

}
