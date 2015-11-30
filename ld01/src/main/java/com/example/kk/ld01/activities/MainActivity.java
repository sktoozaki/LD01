package com.example.kk.ld01.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kk.ld01.R;
import com.example.kk.ld01.models.TaskItem;
import com.example.kk.ld01.utils.CommonAdapter;
import com.example.kk.ld01.utils.ViewHolder;
import com.getbase.floatingactionbutton.AddFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView mListView;
    private AddFloatingActionButton mFAB;
    private List<TaskItem> mTaskItemList;
    private TaskItem mTaskItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar_mainA);
        mListView = (ListView) findViewById(R.id.listview_mainA);
        mFAB= (AddFloatingActionButton) findViewById(R.id.fab_mainA);

        mToolbar.setTitle(R.string.mainA_title);
        mToolbar.setSubtitle(R.string.mainA_subtitle);

        //识别不了color文件中的配置？？？
        mToolbar.setTitleTextColor(0xffffffff);
        mToolbar.setSubtitleTextColor(0xffffffff);
        setSupportActionBar(mToolbar);

        mTaskItemList = new ArrayList<TaskItem>();

        //TODO 功能化ToolBar

        //TODO 动态更新Menu

        //TODO 采用可编辑的抽屉菜单作为ListItem

        //TODO 添加CalendarView与下拉事件

        //TODO 获取服务中的Task

        for (int i = 0; i <= 10; i++) {
            mTaskItem = new TaskItem();
            mTaskItem.setTaskTitle("Task1");
            mTaskItem.setTaskContent("This is a test task");
            mTaskItemList.add(mTaskItem);
        }

        mListView.setAdapter(new CommonAdapter<TaskItem>(MainActivity.this, mTaskItemList, R.layout.taskitem) {
            @Override
            public void convert(ViewHolder holder, TaskItem taskItem) {
                holder.setText(R.id.task_title, taskItem.getTaskTitle());
                holder.setText(R.id.task_content, taskItem.getTaskContent());
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this,TaskDetailActivity.class));
            }
        });

        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NewTaskActivity.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
