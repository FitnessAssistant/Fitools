package com.example.fitools.jiangshengda;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.fitools.R;

import java.util.ArrayList;
import java.util.List;

public class OptionsAdapter extends BaseAdapter {
    private Context context;
    private List<Options> loptions = new ArrayList<>();

    public OptionsAdapter(Context c, ArrayList<Options> ls) {
        context = c;
        loptions = ls;
    }

    @Override
    public int getCount() {
        return loptions.size();
    }

    @Override
    public Object getItem(int i) {
        return loptions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return loptions.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (null == view) {
            view = LayoutInflater.from(context).inflate(R.layout.jsd_list_options, null);
        }
        TextView OpName = (TextView) view.findViewById(R.id.fit_options_name);
        OpName.setText(loptions.get(i).getName());
        TextView OpTime = (TextView) view.findViewById(R.id.fit_options_time);
        OpTime.setText(loptions.get(i).getTime());
        RelativeLayout RlOptions = (RelativeLayout) view.findViewById(R.id.fit_options);

        return view;
    }

}