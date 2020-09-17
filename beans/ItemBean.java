package com.example.icbc.beans;

public class ItemBean {
    private int id;
    private String name;
    private int type;   // type: title/item
    private int group_num;   // 组名
    private int app_sort;    // 排序
    private int img;         // item图片
    private String redNum;   // 右上角气泡数字
    private int show_flag;   // fragmentItem右上角的+-号
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGroup_num() {
        return group_num;
    }

    public void setGroup_num(int group_num) {
        this.group_num = group_num;
    }

    public int getApp_sort() {
        return app_sort;
    }

    public void setApp_sort(int app_sort) {
        this.app_sort = app_sort;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getRedNum() {
        return redNum;
    }

    public void setRedNum(String redNum) {
        this.redNum = redNum;
    }

    public int getShow_flag() {
        return show_flag;
    }

    public void setShow_flag(int show_flag) {
        this.show_flag = show_flag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
