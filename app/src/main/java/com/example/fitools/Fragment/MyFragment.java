package com.example.fitools.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fitools.Activity.LoginActivity;
import com.example.fitools.Activity.SettingActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.example.fitools.R;
import com.example.fitools.Utils.AppUtil;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {
    private ImageView setting_btn;
    private View v;
    private String userid;
    private String nickname;
    private String usersex;
    private String dynamicnumber;
    private String fansnumber;
    private String follownumber;
    private Context context;
    private TextView nicknametv;
    private LinearLayout infll;
    private TextView dynamicnumtv;
    private TextView fansnumtv;
    private TextView follownumtv;

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(null == v) {//防止Layout重复加载数据
            v = inflater.inflate(R.layout.fragment_my, container, false);
            context = this.getActivity().getApplicationContext();
            getViews();
            setting_btn = (ImageView) v.findViewById(R.id.my_setting);
            setting_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(),SettingActivity.class);
                    startActivity(intent);
                }
            });
            synhttprequest();
        }else {
            ViewGroup parent = (ViewGroup)v.getParent();
            if(null != parent){
                parent.removeView(v);
            }
        }

        // Inflate the layout for this fragment
        return v;
    }
    /**
     * 获取界面控件
     */
    private void getViews(){

        nicknametv = (TextView)v.findViewById(R.id.my_nicknametv);
        dynamicnumtv = (TextView)v.findViewById(R.id.my_dynamic);
        fansnumtv = (TextView)v.findViewById(R.id.my_fans);
        follownumtv = (TextView)v.findViewById(R.id.my_follow);
        infll = (LinearLayout)v.findViewById(R.id.User_information_bar);
    }
    public void synhttprequest(){
        AsyncHttpClient client = new AsyncHttpClient();
        String Url = "http://"+ AppUtil.JFinalServer.HOST+":"+AppUtil.JFinalServer.PORT+"/user/getInformation";
        RequestParams params = new RequestParams();
        params.add("user_id", LoginActivity.USER);
        client.get(Url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    nickname = response.getString("nick_name");
                    usersex = response.getString("user_sex");
                    dynamicnumber = response.getString("dynamic_number");
                    fansnumber = response.getString("fans_number");
                    follownumber = response.getString("follow_number");
                    setViews();
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

        });
    }
    /**
     * 给界面控件赋值
     */
    private void setViews(){
        nicknametv.setText(nickname);
        dynamicnumtv.setText(dynamicnumber);
        fansnumtv.setText(fansnumber);
        follownumtv.setText(follownumber);
        if(usersex.equals("0")){
            infll.setBackgroundResource(R.mipmap.my_bg_man);
        }else{
            infll.setBackgroundResource(R.mipmap.my_bg_women);
        }
    }
}
