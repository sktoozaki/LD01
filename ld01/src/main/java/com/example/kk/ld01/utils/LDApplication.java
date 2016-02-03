package com.example.kk.ld01.utils;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;

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
        x.Ext.setDebug(true);
        AVObject.registerSubclass(TaskItem.class);
        AVOSCloud.initialize(this, "CcdwJeS2XKTr3eOscyOKwVmx-gzGzoHsz", "p8TWYwkySzwc1Ch47JgrmA4A");
        Intent intent=new Intent();
        intent.setAction(".services.AlarmService");
        startService(intent);
    }
}
