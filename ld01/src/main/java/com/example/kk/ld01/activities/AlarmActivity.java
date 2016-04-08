package com.example.kk.ld01.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kk.ld01.R;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class AlarmActivity extends AppCompatActivity {

    private String TAG="AlarmActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        initViews();
    }

    private void initViews() {
        new AlertDialog.Builder(this).setTitle("任务提醒")
                .setMessage("请查看任务")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlarmActivity.this.finish();
                    }
                });
        initService();
    }

    private void initService() {

    }
}
