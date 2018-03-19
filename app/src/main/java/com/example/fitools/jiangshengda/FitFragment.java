package com.example.fitools.jiangshengda;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;

import com.example.fitools.R;
import com.example.fitools.caizihuan.DataItem;
import com.example.fitools.caizihuan.DataItemAdapter;
import com.gc.flashview.FlashView;
import com.gc.flashview.constants.EffectConstants;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FitFragment extends Fragment {
    //ListView
    private ArrayList<Options> ls = new ArrayList<Options>();
    private ListViewForScrollView lv;
    private OptionsAdapter mAdapter;
    private ArrayAdapter<String> adapter;
    //解决ListViewForScrollView而进行的操作
    private ScrollView sv;

    private View fitlayout;
    private FlashView fitfv;
    private ArrayList<String> imageUrls;
    private Context context;

    private void getData() {
        ls.add(new Options((long) 1, "高强度全身燃脂","7") );
        ls.add(new Options((long) 2, "暴汗燃脂八分钟","8") );
        ls.add(new Options((long) 3, "十分钟挺拔身姿","10") );
        ls.add(new Options((long) 4, "含胸驼背改善练习","12") );
    }

    public FitFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View fitlayout = inflater.inflate(R.layout.jsd_fragment_fit, null);
            getData();

            mAdapter = new OptionsAdapter(getContext(), ls);
            //解决ListViewForScrollView而进行的操作
            sv = (ScrollView) fitlayout.findViewById(R.id.Sv);
            sv.smoothScrollTo(0, 0);

            lv = (ListViewForScrollView) fitlayout.findViewById(R.id.Lv);
            lv.setAdapter(mAdapter);
            fitfv = (FlashView)fitlayout.findViewById(R.id.fit_banner_fv);

            context = this.getActivity().getApplicationContext();
            flashview();//轮播图
            /*refresh();//refresh页面刷新
            verticalScroll();//文字自动滚动
            timing();//计时器
            getData();//获取数据
            init();*/
        return fitlayout;
    }
     /**
     * 轮播图
     */
    public void flashview(){
        imageUrls = new ArrayList<String>();
        imageUrls.add("https://desk-fd.zol-img.com.cn/t_s1024x768c5/g4/M06/06/0B/Cg-4zFTv4zmINhM9ABX5yHpH5GYAAVpZgI6vOUAFfng315.jpg");
        imageUrls.add("https://desk-fd.zol-img.com.cn/t_s1024x768c5/g5/M00/02/09/ChMkJlbKzkeIAUnsAA6Bu85BPKMAALJJQGGbHMADoHT791.jpg");
        imageUrls.add("https://desk-fd.zol-img.com.cn/t_s1024x768c5/g4/M09/0C/09/Cg-4zFT2gkKIDefbAALTUAw6274AAWA2QMB-l0AAtNo521.jpg");
        fitfv.setImageUris(imageUrls);
        fitfv.setEffect(EffectConstants.DEFAULT_EFFECT);
    }
}
