package com.wzh.www.bean;

import cn.bmob.v3.BmobObject;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/15.
 * Date: 2016-04-15
 * Time: 09:32
 * 功能：
 */
public class Society extends BmobObject {
    private String societyname;
    private String info;
    private String schoolname;
    private String logo;
//    private String qrCode;
    private String societyMember;

    public String getSocietyMember() {
        return societyMember;
    }

    public void setSocietyMember(String societyMember) {
        this.societyMember = societyMember;
    }

    public String getSocirtyLeader() {
        return socirtyLeader;
    }

    public void setSocirtyLeader(String socirtyLeader) {
        this.socirtyLeader = socirtyLeader;
    }

    private String socirtyLeader;


    public String getSocietyname() {
        return societyname;
    }

    public void setSocietyname(String societyname) {
        this.societyname = societyname;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

//    public String getQrCode() {
//        return qrCode;
//    }
//
//    public void setQrCode(String qrCode) {
//        this.qrCode = qrCode;
//    }
}
