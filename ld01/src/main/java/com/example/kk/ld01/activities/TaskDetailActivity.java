package com.example.kk.ld01.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.example.kk.ld01.R;
import com.example.kk.ld01.models.TaskItem;

public class TaskDetailActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTitleTV;
    private TextView mContentTV;
    private TextView mStartTimeTV;
    private TextView mEndTimeTV;

    private TaskItem taskItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        initViews();
    }

    private void initViews() {
        taskItem= (TaskItem) getIntent().getSerializableExtra("taskItem");

        mTitleTV= (TextView) findViewById(R.id.title_tv_detail);
        mContentTV= (TextView) findViewById(R.id.content_tv_detail);
        mStartTimeTV= (TextView) findViewById(R.id.start_time_tv_detail);
        mEndTimeTV= (TextView) findViewById(R.id.end_time_tv_detail);
        mToolbar= (Toolbar) findViewById(R.id.toolbar_task_detailA);

        mTitleTV.setText(taskItem.getTaskTitle());
        mContentTV.setText(taskItem.getTaskContent());
        mStartTimeTV.setText(taskItem.getTaskStartDateTime().toString("HH:mm"));
        mEndTimeTV.setText(taskItem.getTaskEndDateTime().toString("HH:mm"));

        mToolbar.setNavigationIcon(R.drawable.arrow_back);
        mToolbar.setLogo(R.drawable.note_toolbar);
        mToolbar.setTitle("Task");
        mToolbar.setTitleTextColor(0xffffffff);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.task_detail_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
