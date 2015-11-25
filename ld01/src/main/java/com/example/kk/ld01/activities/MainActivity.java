package com.example.kk.ld01.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.kk.ld01.R;
import com.example.kk.ld01.models.TaskItem;
import com.example.kk.ld01.utils.ExtendedListView;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView mListView;
    private QuickAdapter mAdapter;
    private SimpleAdapter simpleAdapter;
    private TaskItem mTaskItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {

        mToolbar= (Toolbar) findViewById(R.id.toolbar_mainA);
        mListView= (ListView) findViewById(R.id.listview_mainA);

        mToolbar.setTitle(R.string.mainA_title);
        mToolbar.setSubtitle(R.string.mainA_subtitle);

        //识别不了color文件中的配置？？？
        mToolbar.setTitleTextColor(0xffffffff);
        mToolbar.setSubtitleTextColor(0xffffffff);
        setSupportActionBar(mToolbar);

        mTaskItem=new TaskItem();
        mTaskItem.setTaskTitle("Task1");
        mTaskItem.setTaskContent("This is a test task");

        mAdapter=new QuickAdapter<TaskItem>(this,R.layout.taskitem) {
            @Override
            protected void convert(BaseAdapterHelper helper, TaskItem item) {
                helper.setText(R.id.task_title,mTaskItem.getTaskTitle());
                helper.setText(R.id.task_content,mTaskItem.getTaskContent());
                helper.setImageResource(R.id.task_category,R.drawable.travel);
                helper.setImageResource(R.id.task_priority,R.drawable.priority_green);
            }
        };
        mListView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_actions,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
