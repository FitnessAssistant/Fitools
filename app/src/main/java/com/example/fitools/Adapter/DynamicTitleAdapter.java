package com.example.fitools.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.fitools.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 评价页评价分类的Adapter
 */

public class DynamicTitleAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<DynamicTitleItem> hci = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener = null;
    private List<Boolean> isClicks;//控件是否被点击，默认为false,如果被点击，改变值，控件根据值改变颜色

    public DynamicTitleAdapter(Context context, List<DynamicTitleItem> hci) {
        this.context = context;
        this.hci = hci;
        isClicks = new ArrayList<>();
        isClicks.add(true);
        for (int i = 1; i<hci.size(); i++){//设置默认加载标题
            isClicks.add(false);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, TextView textView, int position);
    }

    //创建新view，被LayoutManager所调用
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_title_item,parent,false);
        RecyclerView.ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    public void setmOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder)holder).title.setText(hci.get(position).getTitle());
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(((ViewHolder) holder).title);
        if(isClicks.get(position)){
            ((ViewHolder) holder).title.setBackground(context.getResources().getDrawable(R.drawable.dynamic_title_bg));
        }else {
            ((ViewHolder) holder).title.setBackground(null);

        }
        //如果设置了回调，则设置点击事件
        if(mOnItemClickListener != null){
            ((ViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    for(int i = 0; i<isClicks.size(); i++){
                        isClicks.set(i,false);
                    }
                    isClicks.set(position, true);
                    notifyDataSetChanged();
                    mOnItemClickListener.onItemClick(holder.itemView, ((ViewHolder) holder).title, position);
                }
            });
        }

    }

    /*
    * 获取数据数量
    * */
    @Override
    public int getItemCount() {
        return hci.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public ViewHolder(View view) {
            super(view);
            title = (TextView)view.findViewById(R.id.tv_dynamic_title);
        }
    }

}
