package com.example.kk.ld01.utils;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.example.kk.ld01.models.TaskItem;

import org.xutils.x;

/**
 * Created by KK on 2015/12/4.
 */
public class LDApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        AVObject.registerSubclass(TaskItem.class);
        AVOSCloud.initialize(this, "CcdwJeS2XKTr3eOscyOKwVmx-gzGzoHsz", "p8TWYwkySzwc1Ch47JgrmA4A");
    }
}
