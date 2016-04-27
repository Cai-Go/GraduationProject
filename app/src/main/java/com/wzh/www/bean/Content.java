package com.wzh.www.bean;

import java.io.Serializable;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/7.
 * Date: 2016-04-07
 * Time: 10:54
 */
public class Content implements Serializable {
    private String title;
    private String content;

    public Content(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}
