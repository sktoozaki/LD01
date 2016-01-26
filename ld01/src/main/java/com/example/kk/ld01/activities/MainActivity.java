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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView mListView;
    private AddFloatingActionButton mFAB;
    private RadioGroup mWeekRG;
    private RadioButton mMonRB,mTueRB,mWedRB,mThuRB,mFriRB,mSatRB,mSunRB;
    private AVUser mUser;

    private List<TaskItem> taskList;
    private TaskItem taskItem;
    private LDResponse ldResponse;
    private CommonAdapter listAdapter;
    private long exitTime = 0;
    private DateTime dateTimeNow;
    private int todayOfMonth;
    private List<DateTime> dateTimeThisWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        dateTimeNow=new DateTime();
        dateTimeThisWeek= getDateTimeThisWeek(dateTimeNow);

        Log.d("test","Week:"+dateTimeNow.getDayOfWeek());
        Log.d("test","Day:"+dateTimeNow.getDayOfMonth());

        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        mListView = (ListView) findViewById(R.id.listview_main);
        mFAB= (AddFloatingActionButton) findViewById(R.id.fab_main);

        mWeekRG= (RadioGroup) findViewById(R.id.week_rg_main);
        mMonRB= (RadioButton) findViewById(R.id.mon_rb_main);
        mTueRB= (RadioButton) findViewById(R.id.tue_rb_main);
        mWedRB= (RadioButton) findViewById(R.id.wed_rb_main);
        mThuRB= (RadioButton) findViewById(R.id.thu_rb_main);
        mFriRB= (RadioButton) findViewById(R.id.fri_rb_main);
        mSatRB= (RadioButton) findViewById(R.id.sat_rb_main);
        mSunRB= (RadioButton) findViewById(R.id.sun_rb_main);

        mMonRB.setText("Mon\n"+ dateTimeThisWeek.get(0).getDayOfMonth());
        mTueRB.setText("Tue\n"+ dateTimeThisWeek.get(1).getDayOfMonth());
        mWedRB.setText("Wed\n"+ dateTimeThisWeek.get(2).getDayOfMonth());
        mThuRB.setText("Thu\n"+ dateTimeThisWeek.get(3).getDayOfMonth());
        mFriRB.setText("Fri\n"+ dateTimeThisWeek.get(4).getDayOfMonth());
        mSatRB.setText("Sat\n"+ dateTimeThisWeek.get(5).getDayOfMonth());
        mSunRB.setText("Sun\n"+ dateTimeThisWeek.get(6).getDayOfMonth());
        setFlag(dateTimeNow);

        mWeekRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.mon_rb_main:

                        break;
                    case R.id.tue_rb_main:

                        break;
                    case R.id.wed_rb_main:

                        break;
                    case R.id.thu_rb_main:

                        break;
                    case R.id.fri_rb_main:

                        break;
                    case R.id.sat_rb_main:

                        break;
                    case R.id.sun_rb_main:

                        break;
                }
            }
        });

        mToolbar.setTitle(R.string.mainA_title);
        mToolbar.setSubtitle(dateTimeNow.toString("yyyy年MM月dd日"));

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
                        mUser.logOut();
                        finish();
                        System.exit(0);
                        break;
                }

                return true;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,TaskDetailActivity.class);
                Bundle data=new Bundle();
                data.putSerializable("taskItem",taskList.get(position));
                intent.putExtras(data);
                startActivity(intent);
            }
        });



        //TODO 功能化ToolBar

        //TODO 动态更新Menu

        //TODO 采用可编辑的抽屉菜单作为ListItem

        //TODO 添加CalendarView与下拉事件

        //TODO 获取服务中的Task
        initService();
    }

    //获取本周每一天的DateTime
    private List<DateTime> getDateTimeThisWeek(DateTime dateTimeNow) {
        DateTime thisMonday=dateTimeNow.minusDays(dateTimeNow.getDayOfWeek()-1);
        List<DateTime> list=new ArrayList<>();
        for (int i=0;i<=6;i++)
        {
            list.add(i,thisMonday.plusDays(i));
        }
        return list;
    }

    private void setFlag(DateTime dateTime) {
        switch (dateTime.getDayOfWeek())
        {
            case 1:
                mMonRB.setChecked(true);
                break;
            case 2:
                mTueRB.setChecked(true);
                break;
            case 3:
                mWedRB.setChecked(true);
                break;
            case 4:
                mThuRB.setChecked(true);
                break;
            case 5:
                mFriRB.setChecked(true);
                break;
            case 6:
                mSatRB.setChecked(true);
                break;
            case 7:
                mSunRB.setChecked(true);
                break;
        }
    }

    private int[] setDays(DateTime dateTime) {
        int[] days=new int[7];
        //计算出当前时刻本周一的值
        DateTime thisMonday=dateTime.minusDays(dateTime.getDayOfWeek() - 1);
        for (int i=0;i<=6;i++)
        {
            days[i]=thisMonday.plusDays(i).getDayOfMonth();
        }
        return days;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initService();
    }

    private void getTasksByName(String username) {
        AVQuery<TaskItem> query=AVObject.getQuery(TaskItem.class);
        query.whereEqualTo("userName", username);
        query.findInBackground(new FindCallback<TaskItem>() {
            @Override
            public void done(List<TaskItem> list, AVException e) {
                if (e == null) {
                    Log.d("test", list.size() + "条数据");
                    bindTaskItem(list);
                } else {
                    Log.d("test", "错误：" + e.getMessage());
                }
            }
        });
    }

    private void getTasks(String username,DateTime dateTime) {
        AVQuery<TaskItem> query=AVObject.getQuery(TaskItem.class);
        query.whereEqualTo("userName", username);
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
    }


    //禁用返回
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        // 连按两次返回键退出程序
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) < 2000) {
                finish();
                System.exit(0);
            }
            exitTime = System.currentTimeMillis();
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

    /**
     * 绑定数据
     */
    private void bindTaskItem(List<TaskItem> list) {
        if (list==null)
        {
            Log.d("test", "itemlist为空");
        }else {
            Log.d("test", "itemlist不为空");
            taskList=list;
        }

        listAdapter=new CommonAdapter<TaskItem>(this,list,R.layout.taskitem) {
            @Override
            public void convert(BaseViewHolder helper, TaskItem item) {
                helper.setText(R.id.task_title, item.getTaskTitle())
                        .setText(R.id.task_content, item.getTaskContent())
                        .setText(R.id.task_time,item.getTaskStartDateTime().toString("HH:mm"));
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
