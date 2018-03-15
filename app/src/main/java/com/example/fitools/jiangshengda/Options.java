package com.example.fitools.jiangshengda;

/**
 * Created by Santa on 2018/3/15.
 */

public class Options {
    private String Name;
    private int Time;

    public Options(long id,String name,int time){
        this.id = id;
        Name = name;
        Time = time;
    }

    private long id;

    public long getId(){ return id; }
}


