package com.example.kk.ld01.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;
import com.example.kk.ld01.R;
import com.example.kk.ld01.fragments.DayFragment;
import com.example.kk.ld01.models.TaskItem;
import com.example.kk.ld01.utils.CommonAdapter;

import org.joda.time.DateTime;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private DayFragment mDayFragment;
    private AVUser mUser;
    private List<TaskItem> taskList;
    private CommonAdapter listAdapter;
    private long exitTime = 0;
    private DateTime dateTimeNow;
    private List<DateTime> dateTimeThisWeek;

    private enum days {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRADAY, SATURDAY, SUNDAY
    }

    ;

    @ViewInject(R.id.framelayout_main)
    private LinearLayout mFrame;

    @ViewInject(R.id.toolbar_main)
    private Toolbar mToolbar;

    @ViewInject(R.id.week_rg_main)
    private RadioGroup mWeekRG;

/*
    @ViewInject(R.id.listview_main)
    private ListView mListView;
*/

    @ViewInject(R.id.mon_rb_main)
    private RadioButton mMonRB;
    @ViewInject(R.id.tue_rb_main)
    private RadioButton mTueRB;
    @ViewInject(R.id.wed_rb_main)
    private RadioButton mWedRB;
    @ViewInject(R.id.thu_rb_main)
    private RadioButton mThuRB;
    @ViewInject(R.id.fri_rb_main)
    private RadioButton mFriRB;
    @ViewInject(R.id.sat_rb_main)
    private RadioButton mSatRB;
    @ViewInject(R.id.sun_rb_main)
    private RadioButton mSunRB;

/*
    @Event(value = R.id.fab_main)
    private void onFabClick(View view){
        startActivity(new Intent(MainActivity.this, NewTaskActivity.class));
    }
*/

    /*@Event(value = R.id.listview_main,type = AdapterView.OnItemClickListener.class)
    private void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Intent intent=new Intent(MainActivity.this,TaskDetailActivity.class);
        Bundle data=new Bundle();
        data.putSerializable("taskItem",taskList.get(position));
        intent.putExtras(data);
        startActivity(intent);
    }*/

    @Event(value = R.id.toolbar_main, type = Toolbar.OnMenuItemClickListener.class)
    private void onMenuItemClick(MenuItem item) {
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        initService();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*初始化UI*/
    private void initViews() {
        dateTimeNow = new DateTime();
        dateTimeThisWeek = getDateTimeThisWeek(dateTimeNow);

        Log.d("test", "Week:" + dateTimeNow.getDayOfWeek());
        Log.d("test", "Day:" + dateTimeNow.getDayOfMonth());

        mMonRB.setText("Mon\n" + dateTimeThisWeek.get(0).getDayOfMonth());
        mTueRB.setText("Tue\n" + dateTimeThisWeek.get(1).getDayOfMonth());
        mWedRB.setText("Wed\n" + dateTimeThisWeek.get(2).getDayOfMonth());
        mThuRB.setText("Thu\n" + dateTimeThisWeek.get(3).getDayOfMonth());
        mFriRB.setText("Fri\n" + dateTimeThisWeek.get(4).getDayOfMonth());
        mSatRB.setText("Sat\n" + dateTimeThisWeek.get(5).getDayOfMonth());
        mSunRB.setText("Sun\n" + dateTimeThisWeek.get(6).getDayOfMonth());
        setFlag(dateTimeNow);

        mToolbar.setTitle(R.string.mainA_title);
        mToolbar.setSubtitle(dateTimeNow.toString("yyyy年MM月dd日"));

        //BUG:识别不了color文件中的配置
        mToolbar.setTitleTextColor(0xffffffff);
        mToolbar.setSubtitleTextColor(0xffffffff);
        setSupportActionBar(mToolbar);

        mWeekRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.mon_rb_main:
                        Log.d("test", "mon");
//                        getTasks(mUser.getUsername(), dateTimeThisWeek.get(0));
                        loadFragment(dateTimeThisWeek.get(0));
                        break;
                    case R.id.tue_rb_main:
//                        getTasks(mUser.getUsername(), dateTimeThisWeek.get(1));
                        loadFragment(dateTimeThisWeek.get(1));
                        break;
                    case R.id.wed_rb_main:
//                        getTasks(mUser.getUsername(), dateTimeThisWeek.get(2));
                        loadFragment(dateTimeThisWeek.get(2));
                        break;
                    case R.id.thu_rb_main:
//                        getTasks(mUser.getUsername(), dateTimeThisWeek.get(3));
                        loadFragment(dateTimeThisWeek.get(3));
                        break;
                    case R.id.fri_rb_main:
//                        getTasks(mUser.getUsername(), dateTimeThisWeek.get(4));
                        loadFragment(dateTimeThisWeek.get(4));
                        break;
                    case R.id.sat_rb_main:
//                        getTasks(mUser.getUsername(), dateTimeThisWeek.get(5));
                        loadFragment(dateTimeThisWeek.get(5));
                        break;
                    case R.id.sun_rb_main:
//                        getTasks(mUser.getUsername(), dateTimeThisWeek.get(6));
                        loadFragment(dateTimeThisWeek.get(6));
                        break;
                    default:
                        break;
                }
            }
        });


        //TODO 功能化ToolBar

        //TODO 动态更新Menu

        //TODO 采用可编辑的抽屉菜单作为ListItem

        //TODO 添加CalendarView与下拉事件

        //TODO 获取服务中的Task

        loadFragment(dateTimeNow);
    }

    /*初始化服务请求*//*
    private void initService() {
        mUser=AVUser.getCurrentUser();
        if (mUser!=null){
            getTasks(mUser.getUsername(),dateTimeNow);
        }else {
        }
    }*/

    /*加载特定DateTime的Fragment*/
    private void loadFragment(DateTime datetime) {
        DayFragment mDayFragment = new DayFragment(datetime);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout_main, mDayFragment);
        transaction.commit();
    }

    /*获取本周每一天的DateTime*/
    private List<DateTime> getDateTimeThisWeek(DateTime dateTimeNow) {
        DateTime thisMonday = dateTimeNow.minusDays(dateTimeNow.getDayOfWeek() - 1);
        List<DateTime> list = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            list.add(i, thisMonday.plusDays(i));
        }
        return list;
    }

    /*设置今天的Flag高亮*/
    private void setFlag(DateTime dateTime) {
        switch (dateTime.getDayOfWeek()) {
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

    /*根据用户名和DateTime获取任务*//*
    private void getTasks(String username,DateTime dateTime) {
        AVQuery<TaskItem> query=AVObject.getQuery(TaskItem.class);
        query.whereEqualTo("userName", username);
        //TODO 通过Fragment来划分每周显示的任务列表
        //TODO 测试通过DayOfYear来获取任务
//        query.whereEqualTo("taskStartDateTime.getDayOfWeek()", dateTime.getDayOfWeek());
        query.include("taskStartDateTime.getDayOfWeek()");
        Log.d("test", "getTasksOf " + dateTime.getDayOfYear());
        query.findInBackground(new FindCallback<TaskItem>() {
            @Override
            public void done(List<TaskItem> list, AVException e) {
                if (e == null) {
                    Log.d("test", "getTasks:" + list.size() + "条数据");
                    for (TaskItem task : list) {
                        Log.d("test", "日期"+task.getTaskStartDateTime().getDayOfYear());
                    }
                    bindTaskItem(list);
                } else {
                    Log.d("test", "错误：" + e.getMessage());
                }
            }
        });
    }*/

    /*绑定任务数据*//*
    private void bindTaskItem(List<TaskItem> list) {
        if (list==null)
        {
            Toast.makeText(MainActivity.this, "今天还没有设定任务喔", Toast.LENGTH_SHORT).show();
        }else {
            Log.d("test", "itemlist不为空");
            taskList=list;

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
    }*/
}
