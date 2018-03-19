package com.example.fitools.jiangshengda;

/**
 * Created by Santa on 2018/3/15.
 */

public class Options {
    private String Name;
    private String Time;

    public Options(long id,String name,String time){
        this.id = id;
        Name = name;
        Time = time;
    }

    private long id;

    public long getId(){ return id; }

    public void setId(long id){ this.id = id;}

    public String getName(){return Name;}

    public void setName(String name) {
        Name = name;
    }

    public String getTime(){return Time;}

    public void setTime(String time){Time = time;}
}


