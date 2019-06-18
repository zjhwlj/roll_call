package com.example.zjh.roll_call;

public class Notice {
    private  int imageId;
    private String name;
    private String subname;
    public Notice (String name , String subName ,int noticeId){
        this.name = name;
        this.subname = subName;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }
    public String getsubName() {
        return subname;
    }
    public int getImageId() {
        return imageId;
    }
}
