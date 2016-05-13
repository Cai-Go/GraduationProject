package com.wzh.www.bean;

import cn.bmob.v3.BmobObject;

/**
 * 项目名称：Graduation Project
 * 作者： Created by WWW on 2016/4/21.
 * Date: 2016-04-21
 * Time: 10:28
 * 功能：
 */
public class Events extends BmobObject {
    private String eventsName;
    private String eventOrganizer;
    private String eventTime;
    private String eventStatus;
    private String eventContent;
    private String eventImage;
    private String eventLocation;
    private String eventLimitMember;
    private String logo;


    public String getEventsName() {
        return eventsName;
    }

    public void setEventsName(String eventsName) {
        this.eventsName = eventsName;
    }

    public String getEventOrganizer() {
        return eventOrganizer;
    }

    public void setEventOrganizer(String eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventLimitMember() {
        return eventLimitMember;
    }

    public void setEventLimitMember(String eventLimitMember) {
        this.eventLimitMember = eventLimitMember;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


}
