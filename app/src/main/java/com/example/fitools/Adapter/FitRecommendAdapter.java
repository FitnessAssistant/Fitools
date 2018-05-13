package com.example.fitools.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class FitRecommendAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<FitRecommendItem> fritem = new ArrayList<>();
    public FitRecommendAdapter(Context context, List<FitRecommendItem> fritem) {
        this.context = context;
        this.fritem = fritem;
    }
    //创建新view，被LayoutManager所调用
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fit_recommenditem,parent,false);
        RecyclerView.ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).img.setImageBitmap(readBitMap(context, fritem.get(position).getImgsrc()));
        ((ViewHolder)holder).title.setText(fritem.get(position).getTitle());
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return fritem.size();
    }
    //自定义的ViewHolder，持有每个Item的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView title;
        public ViewHolder(View view) {
            super(view);
            img = (ImageView)view.findViewById(R.id.fit_recommenditem_img);
            title = (TextView)view.findViewById(R.id.fit_recommenditem_tv);
        }
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
