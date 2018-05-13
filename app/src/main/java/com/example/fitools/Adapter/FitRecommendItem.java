package com.example.fitools.Adapter;

public class FitRecommendItem {
    private int id;
    private int imgsrc;
    private String title;

    public FitRecommendItem(int id, int imgsrc, String title) {
        this.id = id;
        this.imgsrc = imgsrc;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public int getImgsrc() {
        return imgsrc;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImgsrc(int imgsrc) {
        this.imgsrc = imgsrc;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
