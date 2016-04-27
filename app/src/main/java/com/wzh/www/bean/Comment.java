package com.wzh.www.bean;

import cn.bmob.v3.BmobObject;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/27.
 * Date: 2016-04-27
 * Time: 10:12
 * 功能：评论
 */
public class Comment extends BmobObject {
    private String newsTitle;
    private String usersName;
    private  String commentContent;

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String userName) {
        this.usersName = userName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
