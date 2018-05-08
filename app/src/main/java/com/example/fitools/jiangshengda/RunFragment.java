package com.example.fitools.jiangshengda;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.fitools.R;

import java.util.ArrayList;
import java.util.List;

public class RunFragment extends Fragment {
    private String meg="NomoralMap";
    private ImageView map_btn;
    private LinearLayout run_xml;
    private View view_run,view_calorie,view_distance,view_time;
    private ViewPager viewPager;
    private ArrayList<View> viewList;//view数组
    private RelativeLayout start_run_rl;
    private MyPagerAdapter mAdapter;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.jsd_fragment_run,null);
        run_xml = (LinearLayout) v.findViewById(R.id.run_xml);
        viewPager = (ViewPager) v.findViewById(R.id.run_viewpager);
        map_btn = (ImageView) v.findViewById(R.id.run_map_btn);
        start_run_rl = (RelativeLayout) v.findViewById(R.id.start_run_rl);
        //view_run = inflater.inflate(R.layout.jsd_viewpager_run,null);
//        view_calorie = inflater.inflate(R.layout.jsd_viewpager_calorie,null);
//        view_distance = inflater.inflate(R.layout.jsd_viewpager_distance,null);
//        view_time = inflater.inflate(R.layout.jsd_viewpager_time,null);

        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(inflater.inflate(R.layout.jsd_viewpager_run,null,false));
        viewList.add(inflater.inflate(R.layout.jsd_viewpager_calorie,null,false));
        viewList.add(inflater.inflate(R.layout.jsd_viewpager_distance,null,false));
        viewList.add(inflater.inflate(R.layout.jsd_viewpager_time,null,false));
//        viewList.add(view_run);
//        viewList.add(view_calorie);
//        viewList.add(view_distance);
//        viewList.add(view_time);
        mAdapter = new MyPagerAdapter(viewList);
        viewPager.setAdapter(mAdapter);
        /**
         * 滑动监听器OnPageChangeListener
         *  OnPageChangeListener这个接口需要实现三个方法：onPageScrollStateChanged，onPageScrolled ，onPageSelected
         *      1、onPageScrollStateChanged(int arg0) 此方法是在状态改变的时候调用。
         *          其中arg0这个参数有三种状态（0，1，2）
         *              arg0 ==1的时表示正在滑动，arg0==2的时表示滑动完毕了，arg0==0的时表示什么都没做
         *              当页面开始滑动的时候，三种状态的变化顺序为1-->2-->0
         *      2、onPageScrolled(int arg0,float arg1,int arg2) 当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直被调用。
         *          其中三个参数的含义分别为：
         *              arg0 :当前页面，及你点击滑动的页面
         *              arg1:当前页面偏移的百分比
         *              arg2:当前页面偏移的像素位置
         *      3、onPageSelected(int arg0) 此方法是页面跳转完后被调用，arg0是你当前选中的页面的Position（位置编号）
         */

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        run_xml.setBackgroundResource(R.mipmap.run_bg2);
                        break;
                    case 1:
                        run_xml.setBackgroundResource(R.mipmap.run_bg8);
                        break;
                    case 2:
                        run_xml.setBackgroundResource(R.mipmap.run_bg4);
                        break;
                    case 3:
                        run_xml.setBackgroundResource(R.mipmap.run_bg10);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //利用bundle来存取数据
                Bundle bundle=new Bundle();
                bundle.putString("judge",meg);
                //再把bundle中的数据传给intent，以传输过去
                Intent j = new Intent(getActivity(),MapActivity.class);
                j.putExtras(bundle);
                startActivity(j);
            }
        });
        start_run_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),RunCountdownActivity.class);
                startActivity(i);
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

}
