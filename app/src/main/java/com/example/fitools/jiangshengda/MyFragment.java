package com.example.fitools.jiangshengda;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.fitools.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {
    private ImageView setting_btn;


    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.jsd_fragment_my, container, false);
        setting_btn = (ImageView) v.findViewById(R.id.my_setting);
        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

}
