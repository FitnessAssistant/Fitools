package com.example.fitools.caizihuan;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.fitools.R;
import com.example.fitools.jiangshengda.MainActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicFragment extends Fragment {
    private RecyclerView recyclerView_title;
    private RecyclerView recyclerView_content;
    private List<DynamicTitleItem> titlelist = new ArrayList<>();
    private View dynamiclayout;
    private Context context;
    private CustomerGridLayoutManager mGridLayoutManager;
    private DynamicTitleAdapter titleAdapter;
    private LinearLayoutManager layoutManager;
    private DynamicContentAdapter contentAdapter;
    private List<DynamicContentItem>contentlist = new ArrayList<>();
    private MainActivity mainActivity;
    private ImageView img_addfriends;

    public DynamicFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(null == dynamiclayout){//防止Layout重复加载数据
            dynamiclayout = inflater.inflate(R.layout.czh_fragment_dynamic,container,false);
            context = this.getActivity().getApplicationContext();
            getViews();
            getData();//获取数据
            dynamictitleRecyelerview();//标题栏的recyclerview
            dynamiccontentRecyelerview();//动态内容的recyclerview
        }else {
            ViewGroup parent = (ViewGroup)dynamiclayout.getParent();
            if(null != parent){
                parent.removeView(dynamiclayout);
            }
        }

        return dynamiclayout;
    }

    private void getViews() {
        recyclerView_title = (RecyclerView)dynamiclayout.findViewById(R.id.dynamic_rv_title);
        recyclerView_content = (RecyclerView)dynamiclayout.findViewById(R.id.dynamic_rv_content);
        img_addfriends = (ImageView)dynamiclayout.findViewById(R.id.iv_addfriends);
        img_addfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /*
    * 获取数据
    **/
    private void getData() {
        titlelist.add(new DynamicTitleItem(0,"推荐"));
        titlelist.add(new DynamicTitleItem(1,"关注"));
        titlelist.add(new DynamicTitleItem(2,"附近"));
        contentlist.add(new DynamicContentItem(0,R.mipmap.find_img1,R.mipmap.mitao,"Leff","一天前","今天下雨了，我还要继续坚持减肥啊啊，因为瘦下去才能美美哒对不对。所以小仙女们要和我一起加油哦，，，一起变得美美哒！！！~"
        ,"20","20"));
        contentlist.add(new DynamicContentItem(1,R.mipmap.find_img1,R.mipmap.mitao,"Leff","一天前","今天下雨了，我还要继续坚持减肥啊啊，因为瘦下去才能美美哒对不对。所以小仙女们要和我一起加油哦，，，一起变得美美哒！！！~"
                ,"20","20"));
    }

    /*
    * 标题栏recycleview
    **/
    private void dynamictitleRecyelerview() {
        recyclerView_title.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_title.setLayoutManager(layoutManager);
        titleAdapter = new DynamicTitleAdapter(context,titlelist);
        recyclerView_title.setAdapter(titleAdapter);
        titleAdapter.setOnItemClickListener(new DynamicTitleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, TextView textView, int position) {
                switch (position){
                    case 0:
                        contentlist.clear();
                        contentlist.add(new DynamicContentItem(0,R.mipmap.find_img1,R.mipmap.mitao,"Leff","一天前","今天下雨了，我还要继续坚持减肥啊啊，因为瘦下去才能美美哒对不对。所以小仙女们要和我一起加油哦，，，一起变得美美哒！！！~"
                                ,"20","20"));
                        contentlist.add(new DynamicContentItem(1,R.mipmap.find_img1,R.mipmap.mitao,"Leff","一天前","今天下雨了，我还要继续坚持减肥啊啊，因为瘦下去才能美美哒对不对。所以小仙女们要和我一起加油哦，，，一起变得美美哒！！！~"
                                ,"20","20"));
                        contentAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        contentlist.clear();
                        contentlist.add(new DynamicContentItem(0,R.mipmap.find_img1,R.mipmap.mitao,"Leff","一天前","今天下雨了，我还要继续坚持减肥啊啊，因为瘦下去才能美美哒对不对。所以小仙女们要和我一起加油哦，，，一起变得美美哒！！！~"
                                ,"20","20"));
                        contentlist.add(new DynamicContentItem(1,R.mipmap.find_img1,R.mipmap.mitao,"Leff","一天前","今天下雨了，我还要继续坚持减肥啊啊，因为瘦下去才能美美哒对不对。所以小仙女们要和我一起加油哦，，，一起变得美美哒！！！~"
                                ,"20","20"));
                        contentAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        contentlist.clear();
                        contentlist.add(new DynamicContentItem(0,R.mipmap.find_img1,R.mipmap.mitao,"Leff","一天前","今天下雨了，我还要继续坚持减肥啊啊，因为瘦下去才能美美哒对不对。所以小仙女们要和我一起加油哦，，，一起变得美美哒！！！~"
                                ,"20","20"));
                        contentlist.add(new DynamicContentItem(1,R.mipmap.find_img1,R.mipmap.mitao,"Leff","一天前","今天下雨了，我还要继续坚持减肥啊啊，因为瘦下去才能美美哒对不对。所以小仙女们要和我一起加油哦，，，一起变得美美哒！！！~"
                                ,"20","20"));
                        contentAdapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /*
    * 动态recycleview
    * */
    private void dynamiccontentRecyelerview() {
        mGridLayoutManager = new CustomerGridLayoutManager(getActivity(),1);
        mGridLayoutManager.setScrollEnabled(false);
        recyclerView_content.setHasFixedSize(true);
        recyclerView_content.setLayoutManager(mGridLayoutManager);
        mGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        contentAdapter = new DynamicContentAdapter(context,contentlist);
        recyclerView_content.setAdapter(contentAdapter);
        recyclerView_content.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    public class CustomerGridLayoutManager extends GridLayoutManager {
        private boolean isScrollEnabled = true;

        private final String TAG = CustomerGridLayoutManager.class.getSimpleName();

        public CustomerGridLayoutManager(Context context, int spanCount) {
            super(context, spanCount);
        }

        public CustomerGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
            super(context, spanCount, orientation, reverseLayout);
        }

        @Override
        public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
            super.onMeasure(recycler, state, widthSpec, heightSpec);
        }

        public void setScrollEnabled(boolean flag) {
            isScrollEnabled = flag;
        }

        @Override
        public boolean canScrollVertically() {
            return isScrollEnabled && super.canScrollVertically();
        }
    }

}
