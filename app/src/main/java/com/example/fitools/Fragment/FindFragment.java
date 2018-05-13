package com.example.fitools.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.fitools.Activity.MainActivity;
import com.example.fitools.Activity.TrainTopActivity;
import com.example.fitools.Adapter.DataItem;
import com.example.fitools.Adapter.DataItemAdapter;
import com.gc.flashview.FlashView;
import com.example.fitools.R;
import com.gc.flashview.constants.EffectConstants;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment {
    private LinearLayout list;
    private View findlayout;
    private FlashView findfv;
    private ArrayList<String> imageUrls;
    private Context context;
    private ArrayList<DataItem> item_list;
    private String titles[] = new String[]{"你真的相信体重秤上的数字？","手把手教你吃掉自己的冬膘","这四种食物，身材好的人不吃"};//文章标题
    private String contents[] = new String[]{"为什么越练越重？这才是真正的原因！","今天不跟你讲什么控制食欲，低盐低脂的大空话，手把手教你定制........","那些身材好的人，到底每天吃什么东西？难道真有什么神奇的食物........"};//文章内容
    private int imgsrc[] = new int[]{R.mipmap.find_item4,R.mipmap.find_item5,R.mipmap.find_item6};//文章图片
    private RecyclerView mRecyclerView;
    private MainActivity mainActivity;


    public FindFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(null == findlayout){//防止Layout重复加载数据
            findlayout = inflater.inflate(R.layout.fragment_find,container,false);
            context = this.getActivity().getApplicationContext();
            getViews();
            flashview();//轮播图
            initDatas();//加载数据
            initUI();
            list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity(), TrainTopActivity.class);
                    startActivity(i);
                }
            });
            /*refresh();//refresh页面刷新
            verticalScroll();//文字自动滚动
            timing();//计时器
            getData();//获取数据
            init();*/
        }else {
            ViewGroup parent = (ViewGroup)findlayout.getParent();
            if(null != parent){
                parent.removeView(findlayout);
            }
        }
        return findlayout;
    }

    /*
    * 获取界面控件
    * */
    private void getViews() {
        list = (LinearLayout)findlayout.findViewById(R.id.list);
        findfv = (FlashView)findlayout.findViewById(R.id.find_banner_fv);
        mRecyclerView = (RecyclerView)findlayout.findViewById(R.id.find_recyclerView);
    }

    /*
    * 获取数据
    * */
    public void initDatas(){
        item_list = new ArrayList<DataItem>();
        for (int i = 0; i < imgsrc.length; i++) {
            DataItem data = new DataItem();
            data.setImgsrc(imgsrc[i]);
            data.setTitle(titles[i]);
            data.setContent(contents[i]);
            item_list.add(data);
        }
    }

    /**
     * 轮播图
     */
    public void flashview(){
        imageUrls = new ArrayList<String>();
        imageUrls.add("http://seopic.699pic.com/photo/00012/1518.jpg_wh1200.jpg");
        imageUrls.add("http://seopic.699pic.com/photo/00000/5551.jpg_wh1200.jpg");
        findfv.setImageUris(imageUrls);
        findfv.setEffect(EffectConstants.DEFAULT_EFFECT);
    }

    /*
    * 文章recyclerView
    * */
    private void initUI() {
        mainActivity = (MainActivity) this.getActivity();
        Context current_view = mainActivity.getApplicationContext();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(current_view));
        DataItemAdapter dataItemOneAdapter = new DataItemAdapter(current_view,item_list);
        mRecyclerView.setAdapter(dataItemOneAdapter);

    }
}
