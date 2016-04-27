package com.wzh.www.bean;

import cn.bmob.v3.BmobObject;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/21.
 * Date: 2016-04-21
 * Time: 10:34
 * 功能：
 */
public class SocietyOrder extends BmobObject{
    private String name;
    private String studentId;
    private String societyName;
    private String reason;
    private String mClass;
    private String grade;
    private String sex;
    private String qq;
    private String phone;
    private String cademy;


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getmClass() {
        return mClass;
    }

    public void setmClass(String mClass) {
        this.mClass = mClass;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCademy() {
        return cademy;
    }

    public void setCademy(String cademy) {
        this.cademy = cademy;
    }
}
