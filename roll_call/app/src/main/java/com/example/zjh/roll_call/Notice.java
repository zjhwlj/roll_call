package com.example.zjh.roll_call;

public class Notice {
    private  String imageId;
    private String name;
    private String subname;
    public Notice (String name , String subName ,String noticeId){
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
    public String getImageId() {
        return imageId;
    }
}
