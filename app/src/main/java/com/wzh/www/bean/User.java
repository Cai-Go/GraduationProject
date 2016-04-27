package com.wzh.www.bean;

import cn.bmob.v3.BmobUser;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/12.
 * Date: 2016-04-12
 * Time: 15:51
 * 功能：用户表
 */
public class User extends BmobUser {
    private String schoolname;
    private String sex;
    private String society;
    private String cademy;
    private String studentName;
    private String grade;


    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }


    public String getCademy() {
        return cademy;
    }

    public void setCademy(String cademy) {
        this.cademy = cademy;
    }


    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }
}
