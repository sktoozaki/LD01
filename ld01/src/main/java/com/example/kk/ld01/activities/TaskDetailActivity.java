package com.example.kk.ld01.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import com.example.kk.ld01.R;

public class TaskDetailActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        initViews();
    }

    private void initViews() {
        mToolbar= (Toolbar) findViewById(R.id.toolbar_task_detailA);
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
