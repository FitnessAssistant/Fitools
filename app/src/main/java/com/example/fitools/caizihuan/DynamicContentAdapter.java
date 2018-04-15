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
 * 评价页评价分类的Adapter
 */

public class DynamicContentAdapter extends RecyclerView.Adapter implements View.OnClickListener{
    private Context context;
    private List<DynamicContentItem> hci = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener = null;

    public DynamicContentAdapter(Context context, List<DynamicContentItem> hci) {
        this.context = context;
        this.hci = hci;
    }
    public static interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    //创建新view,被LayoutManager所调用
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.czh_dynamic_content_item, parent, false);
        RecyclerView.ViewHolder vh = new ViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return vh;
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
        ((ViewHolder)holder).img.setImageBitmap(readBitMap(context,hci.get(position).getImgsrc()));
        ((ViewHolder)holder).name.setText(hci.get(position).getName());
        ((ViewHolder)holder).time.setText(hci.get(position).getTime());
        ((ViewHolder)holder).estimate.setText(hci.get(position).getEstimate());
        ((ViewHolder)holder).estimate_number.setText(hci.get(position).getComment_number());
        ((ViewHolder)holder).zan_number.setText(hci.get(position).getZan_number());
        //将position保存在itemView的Tag中，以便点击时进行获取
        ((ViewHolder)holder).itemView.setTag(position);
    }
    //获取数据的数量
    @Override
    public int getItemCount() {return hci.size();}
    //自定义的ViewHolder,持有每个Item的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public ImageView img_header;
        public ImageView img_comment;
        public ImageView img_zan;
        public ImageView img_share;
        public TextView name;
        public TextView time;
        public TextView estimate;
        public TextView estimate_number;
        public TextView zan_number;
        public ViewHolder(View view){
            super(view);
            img = (ImageView)view.findViewById(R.id.iv_content);
            img_header = (ImageView)view.findViewById(R.id.user_header);
            img_comment = (ImageView)view.findViewById(R.id.icon_comment);
            img_zan = (ImageView)view.findViewById(R.id.icon_zan);
            img_share = (ImageView)view.findViewById(R.id.icon_share);
            name = (TextView)view.findViewById(R.id.name);
            time = (TextView)view.findViewById(R.id.time);
            estimate = (TextView)view.findViewById(R.id.tv_content);
            estimate_number = (TextView)view.findViewById(R.id.comment_number);
            zan_number = (TextView)view.findViewById(R.id.zan_number);
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
