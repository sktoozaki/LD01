package com.example.kk.ld01.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.kk.ld01.R;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

/**
 * Created by KK on 2015/11/27.
 */
public class NewTaskActivity extends AppCompatActivity
{
    private Toolbar mToolBar;
    private LinearLayout mLayout;
    private FloatingActionsMenu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        initViews();
    }

    private void initViews() {
        mToolBar= (Toolbar) findViewById(R.id.toolbar_newtaskA);
        mLayout= (LinearLayout) findViewById(R.id.linearL_newtaskA);

        mToolBar.setNavigationIcon(R.drawable.arrow_back);
        mToolBar.setTitle("New Task");
        //识别不了color文件中的配置？？？
        mToolBar.setTitleTextColor(0xffffffff);
        setSupportActionBar(mToolBar);

        //TODO 使用Joda-Time库的时间函数替代JAVA API
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_task_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
