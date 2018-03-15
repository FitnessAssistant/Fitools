package com.example.fitools.caizihuan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.fitools.R;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 发现界面文章的Adapter
 */

public class DataItemAdapter extends RecyclerView.Adapter implements View.OnClickListener{
    public static enum ITEM_TYPE {
        ITEM_TYPE_One,
        ITEM_TYPE_Two
    }
    private Context context;
    private List<DataItem> hci = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener = null;

    public DataItemAdapter(Context context, List<DataItem> hci) {
        this.context = context;
        this.hci = hci;
    }
    public static interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE.ITEM_TYPE_One.ordinal()){

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.czh_find_rv_item1,parent,false);

            return new ViewHolder1(view);

        }else if(viewType == ITEM_TYPE.ITEM_TYPE_Two.ordinal()){

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.czh_find_rv_item2,parent,false);
            return new ViewHolder2(view);

        }
        return null;
    }


    public int getItemViewType(int position){

        return position  == 0 ? ITEM_TYPE.ITEM_TYPE_One.ordinal() : ITEM_TYPE.ITEM_TYPE_Two.ordinal();
    }

    @Override
    public void onClick(View view) {
        if(mOnItemClickListener != null){
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder1){

            ((ViewHolder1)holder).img.setImageResource(hci.get(position).getImgsrc());
            ((ViewHolder1)holder).titletv.setText(hci.get(position).getTitle());
            ((ViewHolder1)holder).contenttv.setText(hci.get(position).getContent());
            //将position保存在itemView的Tag中，以便点击时进行获取
            ((ViewHolder1)holder).itemView.setTag(position);

        }else if (holder instanceof ViewHolder2){
            ((ViewHolder2)holder).img.setImageResource(hci.get(position).getImgsrc());
            ((ViewHolder2)holder).titletv.setText(hci.get(position).getTitle());
            ((ViewHolder2)holder).contenttv.setText(hci.get(position).getContent());
            //将position保存在itemView的Tag中，以便点击时进行获取
            ((ViewHolder2)holder).itemView.setTag(position);
        }
    }
    //获取数据的数量
    @Override
    public int getItemCount() {return hci.size();}

    //自定义的ViewHolder,持有每个Item的所有界面元素
    public static class ViewHolder1 extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView titletv;
        public TextView contenttv;
        public ViewHolder1(View view){
            super(view);
            img = (ImageView)view.findViewById(R.id.find_item1_img);
            titletv = (TextView)view.findViewById(R.id.find_item1_title);
            contenttv = (TextView)view.findViewById(R.id.find_item1_content);
        }
    }

    //自定义的ViewHolder,持有每个Item的所有界面元素
    public static class ViewHolder2 extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView titletv;
        public TextView contenttv;
        public ViewHolder2(View view){
            super(view);
            img = (ImageView)view.findViewById(R.id.find_item2_img);
            titletv = (TextView)view.findViewById(R.id.find_item2_title);
            contenttv = (TextView)view.findViewById(R.id.find_item2_content);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){//Item点击事件
        this.mOnItemClickListener = listener;
    }
    public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener{//为了下拉刷新重写OnScrollListener
        private int previousTotal = 0;
        private boolean loading = true;
        int firstVisibleItem, visibleItemCount, totalItemCount;
        private int currentPage = 1;
        private GridLayoutManager mGridLayoutManager;
        public EndlessRecyclerOnScrollListener(GridLayoutManager gridLayoutManager){
            this.mGridLayoutManager = gridLayoutManager;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            visibleItemCount = recyclerView.getChildCount();
            totalItemCount = mGridLayoutManager.getItemCount();
            firstVisibleItem = mGridLayoutManager.findFirstVisibleItemPosition();
            if(loading){
                if(totalItemCount > previousTotal){
                    loading = false;
                    previousTotal = totalItemCount;
                }
            }
            if(!loading&&(totalItemCount-visibleItemCount) <= firstVisibleItem){
                currentPage++;
                onLoadMore(currentPage);
                loading = true;
            }
        }
        public abstract void onLoadMore(int currentPage);
    }
    /**
     * 防止内存泄漏
     * @param context
     * @param resId
     * @return
     */
    public Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        //获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }
}
