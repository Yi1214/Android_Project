package com.example.icbc.DataBase;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name="ItemBean")
public class ItemBean {
    @Column(name = "id", isId = true, autoGen = true)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private int type;   // type: title/item
    @Column(name = "group_num")
    private int group_num;   // 组名
    @Column(name = "app_sort")
    private int app_sort;    // 排序
    @Column(name = "img")
    private int img;         // item图片
    @Column(name = "redNum")
    private String redNum;   // 右上角气泡数字
    @Column(name = "show_flag")
    private int show_flag;   // fragmentItem右上角的+-号
    @Column(name = "url")
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
