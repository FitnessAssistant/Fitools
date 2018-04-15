package com.example.fitools.caizihuan;

/**
 * Created by DELL on 2018/3/14.
 * 动态item对象
 */
public class DynamicContentItem {
    private int id;
    private int imgsrc;//发表照片
    private int imgsrc_header;//用户头像
    private String name;//用户名
    private String time;//发表时间
    private String estimate;//发表内容
    private String comment_number;//评论数量
    private String zan_number;//点赞数量

    public DynamicContentItem(int id, int imgsrc, int imgsrc_header, String name, String time, String estimate, String comment_number, String zan_number) {
        this.id = id;
        this.imgsrc = imgsrc;
        this.imgsrc_header = imgsrc_header;
        this.name = name;
        this.time = time;
        this.estimate = estimate;
        this.comment_number = comment_number;
        this.zan_number = zan_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(int imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }

    public int getImgsrc_header() {
        return imgsrc_header;
    }

    public void setImgsrc_header(int imgsrc_header) {
        this.imgsrc_header = imgsrc_header;
    }

    public String getComment_number() {
        return comment_number;
    }

    public void setComment_number(String comment_number) {
        this.comment_number = comment_number;
    }

    public String getZan_number() {
        return zan_number;
    }

    public void setZan_number(String zan_number) {
        this.zan_number = zan_number;
    }
}
