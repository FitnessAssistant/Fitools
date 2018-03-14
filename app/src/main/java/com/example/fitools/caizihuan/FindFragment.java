package com.example.fitools.caizihuan;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.fitools.jiangshengda.MainActivity;
import com.gc.flashview.FlashView;
import com.example.fitools.R;
import com.gc.flashview.constants.EffectConstants;
import android.support.v7.widget.RecyclerView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment {
    private View findlayout;
    private FlashView findfv;
    private ArrayList<String> imageUrls;
    private Context context;
    private ArrayList<DataItemOne> item_list;
    private String titles[] = new String[]{"你真的相信体重秤上的数字？","手把手教你吃掉自己的冬膘","这四种食物，身材好的人不吃"};//文章标题
    private String contents[] = new String[]{"为什么越练越重？这才是真正的原因！","今天不跟你讲什么控制食欲，低盐低脂的大空话，手把手教你定制........","那些身材好的人，到底每天吃什么东西？难道真有什么神奇的食物........"};//文章内容
    private int imgsrc[] = new int[]{R.mipmap.find_item1,R.mipmap.find_item2,R.mipmap.find_item3};//文章图片
    private RecyclerView mRecyclerView;
    private MainActivity mainActivity;


    public FindFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(null == findlayout){//防止Layout重复加载数据
            findlayout = inflater.inflate(R.layout.czh_fragment_find,container,false);
            context = this.getActivity().getApplicationContext();
            getViews();
            flashview();//轮播图
            initDatas();//加载数据
            initUI();
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
        findfv = (FlashView)findlayout.findViewById(R.id.find_banner_fv);
        mRecyclerView = (RecyclerView)findlayout.findViewById(R.id.find_recyclerView);
    }

    /*
    * 获取数据
    * */
    public void initDatas(){
        item_list = new ArrayList<DataItemOne>();
        for (int i = 0; i < imgsrc.length; i++) {
            DataItemOne data = new DataItemOne();
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
        imageUrls.add("https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E9%A3%8E%E6%99%AF&step_word=&hs=0&pn=29&spn=0&di=20161291720&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=1878067600%2C3935137756&os=369595147%2C3358620400&simid=0%2C0&adpicid=0&lpn=0&ln=1981&fr=&fmq=1520945376538_R&fm=&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=13&oriquery=&objurl=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F9922720e0cf3d7ca7533225ef91fbe096a63a9d4.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Frwtxtg_z%26e3Bv54AzdH3Fri5p5v5ry6t2ipAzdH3F8n00nmba9&gsm=0&rpstart=0&rpnum=0");
        imageUrls.add("http://www.315ph.com/mobile/data/afficheimg/1499993512075070666.jpg");
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
        DataItemOneAdapter dataItemOneAdapter = new DataItemOneAdapter(current_view,item_list);
        mRecyclerView.setAdapter(dataItemOneAdapter);

    }
}
