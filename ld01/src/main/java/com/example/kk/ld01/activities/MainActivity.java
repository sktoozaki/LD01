package com.example.kk.ld01.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.example.kk.ld01.R;
import com.example.kk.ld01.models.TaskItem;
import com.example.kk.ld01.utils.BaseViewHolder;
import com.example.kk.ld01.utils.CommonAdapter;
import com.example.kk.ld01.utils.LDResponse;
import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView mListView;
    private AddFloatingActionButton mFAB;
    private TaskItem mTaskItem;
    private LDResponse ldResponse;
    private CommonAdapter listAdapter;
    private long mExitTime = 0;
    private Gson gson;
    private AVUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        getTasksByName(mUser.getUsername());
    }

    private void getTasksByName(String username) {
        AVQuery<TaskItem> query=AVObject.getQuery(TaskItem.class);
        query.whereEqualTo("username", username);
        query.findInBackground(new FindCallback<TaskItem>() {
            @Override
            public void done(List<TaskItem> list, AVException e) {
                if (e == null) {
                    Log.d("test",list.size()+"条数据");
                    bindTaskItem(list);
                } else {
                    Log.d("test","错误："+e.getMessage());
                }
            }
        });
       /* AVQuery<AVObject> query=new AVQuery<>("tasks");
        query.whereEqualTo("username", username);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    Log.d("test",list.size()+"条数据");
                    bindTaskItem(list);
                } else {
                    Log.d("test","错误："+e.getMessage());
                }
            }
        });*/
    }

    //禁用返回
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        // 连按两次返回键退出程序
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) < 2000) {
                finish();
                System.exit(0);
            }
            mExitTime = System.currentTimeMillis();
            Toast.makeText(this, "再按一次退出程序！", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    private void initService() {
        mUser=AVUser.getCurrentUser();
        if (mUser!=null){
            getTasksByName(mUser.getUsername());
        }else {
        }
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

        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewTaskActivity.class));
            }
        });

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        Toast.makeText(MainActivity.this, "action_search", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_filter:
                        Toast.makeText(MainActivity.this, "action_filter", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_settings:
                        Toast.makeText(MainActivity.this, "action_settings", Toast.LENGTH_SHORT).show();

                        break;
                }

                return true;
            }
        });



        //TODO 功能化ToolBar

        //TODO 动态更新Menu

        //TODO 采用可编辑的抽屉菜单作为ListItem

        //TODO 添加CalendarView与下拉事件

        //TODO 获取服务中的Task
        initService();
    }

    /**
     * 绑定数据
     */
    private void bindTaskItem(List<TaskItem> list) {
        gson=new Gson();

        /*try {
            mTaskItemList=gson.fromJson(ldResponse.getData().getJSONArray("tasks").toString(), new TypeToken<ArrayList<TaskItem>>() { }.getType());

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        Log.d("test", list.toString());
        if (list==null)
        {
            Log.d("test", "itemlist为空");
        }else {
            Log.d("test", list.get(2).getTaskContent());
        }

        listAdapter=new CommonAdapter<TaskItem>(this,list,R.layout.taskitem) {
            @Override
            public void convert(BaseViewHolder helper, TaskItem item) {
                helper.setText(R.id.task_title, item.getTaskTitle())
                        .setText(R.id.task_content, item.getTaskContent());
            }


        };
        mListView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
