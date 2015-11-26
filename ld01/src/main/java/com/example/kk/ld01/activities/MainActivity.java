package com.example.kk.ld01.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;

import com.example.kk.ld01.R;
import com.example.kk.ld01.models.TaskItem;
import com.example.kk.ld01.utils.CommonAdapter;
import com.example.kk.ld01.utils.ViewHolder;
import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView mListView;
    private AddFloatingActionButton mFAB;
    private FloatingActionsMenu mMenu;
    private List<TaskItem> mTaskItemList;
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
        mMenu= (FloatingActionsMenu) findViewById(R.id.right_labels);

        mToolbar.setTitle(R.string.mainA_title);
        mToolbar.setSubtitle(R.string.mainA_subtitle);

        //识别不了color文件中的配置？？？
        mToolbar.setTitleTextColor(0xffffffff);
        mToolbar.setSubtitleTextColor(0xffffffff);
        setSupportActionBar(mToolbar);

        mTaskItemList=new ArrayList<TaskItem>();

        //TODO 获取服务中的Task

        for (int i=0;i<=10;i++)
        {
            mTaskItem=new TaskItem();
            mTaskItem.setTaskTitle("Task1");
            mTaskItem.setTaskContent("This is a test task");
            mTaskItemList.add(mTaskItem);
        }

        mListView.setAdapter(new CommonAdapter<TaskItem>(MainActivity.this, mTaskItemList,R.layout.taskitem) {
            @Override
            public void convert(ViewHolder holder, TaskItem taskItem) {
                holder.setText(R.id.task_title, taskItem.getTaskTitle());
                holder.setText(R.id.task_content, taskItem.getTaskContent());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_actions,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
