package com.example.fitools.caizihuan;

/**
 * Created by DELL on 2018/3/14.
 * 动态界面 标题栏 对象
 */
public class DynamicTitleItem {
    int id;
    String title;

    public DynamicTitleItem(int id,String title){
        this.id = id;
        this.title = title;
    }

    public DynamicTitleItem(){
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
