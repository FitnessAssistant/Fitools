package com.example.fitools.jiangshengda;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitools.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FitFragment extends Fragment {


    public FitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.jsd_fragment_fit, container, false);
    }

}
