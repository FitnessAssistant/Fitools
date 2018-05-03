package com.example.fitools.jiangshengda;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.fitools.R;

public class RunFragment extends Fragment {
    private RelativeLayout start_run_rl;


    public RunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.jsd_fragment_run, container, false);

        start_run_rl = (RelativeLayout) v.findViewById(R.id.start_run_rl);
        start_run_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),RunCountdownActivity.class);
                startActivity(i);
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

}
