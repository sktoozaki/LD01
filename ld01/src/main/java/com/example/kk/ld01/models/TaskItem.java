package com.example.kk.ld01.models;

import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import org.joda.time.DateTime;

/**
 * Created by KK on 2015/11/25.
 */
@AVClassName("tasks")
public class TaskItem extends AVObject
{
    private String userName;
    private String taskTitle;
    private String taskContent;
    /*private DateTime taskStartDateTime;
    private DateTime taskEndDateTime;

    public DateTime getTaskStartDateTime() {
        return JSON.toJavaObject(JSON.parseObject(getString("taskStartDateTime")),DateTime.class);
    }

    public void setTaskStartDateTime(DateTime taskStartDateTime) {
        put("taskStartDateTime",JSON.toJSONString(taskStartDateTime));
    }

    public DateTime getTaskEndDateTime() {
        return JSON.toJavaObject(JSON.parseObject(getString("taskEndDateTime")),DateTime.class);
    }

    public void setTaskEndDateTime(DateTime taskEndDateTime) {
        put("taskEndDateTime", JSON.toJSONString(taskEndDateTime));
    }*/
    public String getUserName() {
        return getString("userName");
    }

    public void setUserName(String userName) {
        put("userName",userName);
    }

    public String getTaskTitle() {
        return getString("taskTitle");
    }

    public void setTaskTitle(String taskTitle) {
        put("taskTitle",taskTitle);
    }

    public String getTaskContent() {
        return getString("taskContent");
    }

    public void setTaskContent(String taskContent) {
        put("taskContent",taskContent);
    }

}
