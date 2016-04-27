package com.wzh.www.bean;


import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/12.
 * Date: 2016-04-12
 * Time: 12:24
 * 功能：
 */
public class News extends BmobObject {

    private String content;
    private String author;
    private String title;
    private String simpleContent;
    private String image;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String autor) {
        this.author = autor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSimplecontent() {
        return simpleContent;
    }

    public void setSimplecontent(String simplecontent) {
        this.simpleContent = simplecontent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
