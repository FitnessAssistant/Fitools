package com.example.fitools.jiangshengda;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;

import com.example.fitools.R;
import com.gc.flashview.FlashView;
import com.gc.flashview.constants.EffectConstants;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainFragment extends Fragment {
    //ListView
    private ArrayList<Options> ls = new ArrayList<Options>();
    private ListViewForScrollView lv;
    private OptionsAdapter mAdapter;
    private ArrayAdapter<String> adapter;
    //解决ListViewForScrollView而进行的操作
    private ScrollView sv;

    private View trainlayout;
    private FlashView trainfv;
    private ArrayList<String> imageUrls;
    private Context context;

    private void getData() {
        ls.clear();
        ls.add(new Options((long) 1, "高强度全身燃脂","7") );
        ls.add(new Options((long) 2, "暴汗燃脂八分钟","8") );
        ls.add(new Options((long) 3, "十分钟挺拔身姿","10") );
        ls.add(new Options((long) 4, "含胸驼背改善练习","12") );
    }


    public TrainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View trainlayout = inflater.inflate(R.layout.jsd_fragment_train, container, false);

        getData();

        mAdapter = new OptionsAdapter(getActivity(), ls);
        //解决ListViewForScrollView而进行的操作
        sv = (ScrollView) trainlayout.findViewById(R.id.Sv);
        sv.smoothScrollTo(0, 0);

        lv = (ListViewForScrollView) trainlayout.findViewById(R.id.Lv);
        lv.setAdapter(mAdapter);
        trainfv = (FlashView)trainlayout.findViewById(R.id.fit_banner_fv);

        context = this.getActivity().getApplicationContext();
        flashview();//轮播图
            /*refresh();//refresh页面刷新
            verticalScroll();//文字自动滚动
            timing();//计时器
            getData();//获取数据
            init();*/
        return trainlayout;
    }
    /**
     * 轮播图
     */
    public void flashview(){
        imageUrls = new ArrayList<String>();
        //imageUrls.get(R.mipmap.banner1);
        //imageUrls.get(R.mipmap.banner2);
        //imageUrls.get(R.mipmap.banner3);
        imageUrls.add("http://seopic.699pic.com/photo/00012/1518.jpg_wh1200.jpg");
        imageUrls.add("http://seopic.699pic.com/photo/00000/5551.jpg_wh1200.jpg");
        imageUrls.add("http://seopic.699pic.com/photo/00002/0052.jpg_wh1200.jpg");
        trainfv.setImageUris(imageUrls);
        trainfv.setEffect(EffectConstants.DEFAULT_EFFECT);
    }

}

