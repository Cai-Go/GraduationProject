package com.wzh.www.bean;

import cn.bmob.v3.BmobObject;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/27.
 * Date: 2016-04-27
 * Time: 14:22
 * 功能：消息通知
 */
public class Notifications extends BmobObject {
    private String notiTitle;
    private String notiSender;
    private String notiContent;

    public String getNotiTitle() {
        return notiTitle;
    }

    public void setNotiTitle(String notiTitle) {
        this.notiTitle = notiTitle;
    }

    public String getNotiSender() {
        return notiSender;
    }

    public void setNotiSender(String notiSender) {
        this.notiSender = notiSender;
    }

    public String getNotiContent() {
        return notiContent;
    }

    public void setNotiContent(String notiContent) {
        this.notiContent = notiContent;
    }
}
