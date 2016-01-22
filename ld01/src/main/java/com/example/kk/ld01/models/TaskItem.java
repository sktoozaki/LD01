package com.example.kk.ld01.models;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Created by KK on 2015/11/25.
 */
@AVClassName("tasks")
public class TaskItem extends AVObject
{
    private String userName;

    private String taskTitle;

    private String taskContent;
    public String getUserName() {
        return getString("username");
    }

    public void setUserName(String userName) {
        put("username",userName);
    }

    public String getTaskTitle() {
        return getString("tasktitle");
    }

    public void setTaskTitle(String taskTitle) {
        put("tasktitle",taskTitle);
    }

    public String getTaskContent() {
        return getString("taskcontent");
    }

    public void setTaskContent(String taskContent) {
        put("taskcontent",taskContent);
    }

}
