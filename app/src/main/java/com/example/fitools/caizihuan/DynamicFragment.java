package com.example.fitools.caizihuan;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private DynamicTitleAdapter classifyItemAdapter;
    private LinearLayoutManager classifytitlelayoutManager;
    private DynamicContentAdapter classifytitleItemAdapter;
    private List<DynamicContentItem>contentlist = new ArrayList<>();
    private MainActivity mainActivity;


    public DynamicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dynamiclayout = inflater.inflate(R.layout.czh_fragment_dynamic,container,false);
        context = this.getActivity().getApplicationContext();
        getViews();
        getData();//获取数据
        dynamictitleRecyelerview();//标题栏的recyclerview
        dynamiccontentRecyelerview();//动态内容的recyclerview
        return dynamiclayout;
    }

    private void dynamiccontentRecyelerview() {

    }

    private void dynamictitleRecyelerview() {

    }

    private void getData() {
        titlelist.add(new DynamicTitleItem("推荐"));
        titlelist.add(new DynamicTitleItem("关注"));
        titlelist.add(new DynamicTitleItem("附近"));
        /*for (int i = 0; i < imgsrc.length; i++) {
            DataItem data = new DataItem();
            data.setImgsrc(imgsrc[i]);
            data.setTitle(titles[i]);
            data.setContent(contents[i]);
            item_list.add(data);
        }*/
    }

    private void getViews() {
        recyclerView_title = (RecyclerView)dynamiclayout.findViewById(R.id.dynamic_rv_title);
        recyclerView_content = (RecyclerView)dynamiclayout.findViewById(R.id.dynamic_rv_content);
    }

    /*
    * 标题栏recyclerView
    * */
    private void initUI() {
        mainActivity = (MainActivity) this.getActivity();
        Context current_view = mainActivity.getApplicationContext();
        recyclerView_title.setLayoutManager(new LinearLayoutManager(current_view));
        DynamicTitleAdapter dynamicTitleAdapter = new DynamicTitleAdapter(current_view,titlelist);
        recyclerView_title.setAdapter(dynamicTitleAdapter);

        recyclerView_content.setLayoutManager(new LinearLayoutManager(current_view));
        DynamicContentAdapter dynamicContentAdapter = new DynamicContentAdapter(current_view,contentlist);
        recyclerView_content.setAdapter(dynamicContentAdapter);

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
