package com.example.kk.ld01.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kk.ld01.R;
import com.github.clans.fab.FloatingActionMenu;

/**
 * Created by KK on 2015/11/27.
 */
public class NewTaskActivity extends AppCompatActivity implements View.OnClickListener
{

    private Toolbar mToolBar;
    private LinearLayout mLayout;
    private FloatingActionMenu mMenu;
    private TextView mTaskTitle;
    private TextView mTaskContent;
    private TextView mTaskStartTimeTView;
    private TextView mTaskEndTimeTView;
    private LinearLayout mTaskStartTimeLL;
    private LinearLayout mTaskEndTimeLL;
    private LinearLayout mTaskTypeLL;
    private LinearLayout mTaskTypeOptionsLL;
    private ImageView mTaskTypeImg;
    private RadioGroup mTaskOptionsRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        initViews();
    }

    private void initViews() {
        mToolBar= (Toolbar) findViewById(R.id.toolbar_newtaskA);
        mLayout= (LinearLayout) findViewById(R.id.linearL_newtaskA);
        mMenu= (FloatingActionMenu) findViewById(R.id.fab_menu_new_task_activity);
        mTaskTitle= (TextView) findViewById(R.id.task_title_new_task_activity);
        mTaskContent= (TextView) findViewById(R.id.task_content_new_task_activity);
        mTaskStartTimeTView= (TextView) findViewById(R.id.task_start_tview_new_task_activity);
        mTaskEndTimeTView= (TextView) findViewById(R.id.task_end_tview_new_task_activity);
        mTaskStartTimeLL= (LinearLayout) findViewById(R.id.task_start_ll_new_task_activity);
        mTaskEndTimeLL= (LinearLayout) findViewById(R.id.task_end_ll_new_task_activity);
        mTaskTypeLL= (LinearLayout) findViewById(R.id.task_type_ll_new_task_activity);
        mTaskTypeOptionsLL= (LinearLayout) findViewById(R.id.task_type_options_new_task_activity);
        mTaskTypeImg= (ImageView) findViewById(R.id.task_type_img_new_task_activity);
        mTaskOptionsRadioGroup= (RadioGroup) findViewById(R.id.task_options_rg_new_task_activity);

        mToolBar.setNavigationIcon(R.drawable.arrow_back);
        mToolBar.setTitle("New Task");
        //识别不了color文件中的配置？？？
        mToolBar.setTitleTextColor(0xffffffff);
        setSupportActionBar(mToolBar);

        mTaskStartTimeLL.setOnClickListener(this);
        mTaskEndTimeLL.setOnClickListener(this);
        mTaskTypeLL.setOnClickListener(this);

        //TODO 使用Joda-Time库的时间函数替代JAVA API
        initService();
    }

    private void initService() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_task_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.task_type_ll_new_task_activity:
                mTaskTypeOptionsLL.setVisibility(View.VISIBLE);
                Toast.makeText(this,"task type",Toast.LENGTH_SHORT).show();
                break;

            case R.id.task_start_ll_new_task_activity:
                Toast.makeText(this,"task start",Toast.LENGTH_SHORT).show();
                break;

            case R.id.task_end_ll_new_task_activity:
                Toast.makeText(this,"task end",Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
}
