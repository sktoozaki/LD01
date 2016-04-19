package com.example.kk.ld01.activities;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.example.kk.ld01.R;
import com.example.kk.ld01.models.TaskItem;
import com.example.kk.ld01.utils.LDResponse;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import org.joda.time.DateTime;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by KK on 2015/11/27.
 */
public class NewTaskActivity extends AppCompatActivity implements View.OnClickListener
{
    private Toolbar mToolBar;
    private LinearLayout mLayout;
    private FloatingActionMenu mMenu;
    private FloatingActionButton mContactFab;
    private TextView mTaskTitle;
    private TextView mTaskContent;
    private TextView mTaskStartDateTView;
    private TextView mTaskEndDateTView;
    private TextView mTaskStartTimeTView;
    private TextView mTaskEndTimeTView;
    private LinearLayout mTaskStartTimeLL;
    private LinearLayout mTaskEndTimeLL;
    private LinearLayout mTaskTypeLL;
    private ImageView mTaskTypeImg;
    private RadioGroup mTaskOptionsRadioGroup;
    private AlarmManager alarmManager;
    private TextView mAssignToTView;

    private LDResponse ldResponse;
    private AVUser avUser;

    
    private int yearStart;
    private int yearEnd;
    private int monthOfStartYear;
    private int monthOfEndYear;
    private int dayOfStartMonth;
    private int dayOfEndMonth;
    private int hourOfStartTime;
    private int hourOfEndTime;
    private int minuteOfStartHour;
    private int minuteOfEndHour;
    private DateTime dateTimeNow;
    private DateTime taskStartDateTime;
    private DateTime taskEndDateTime;
    private long exitTime = 0;
    private String TAG="NewTaskActivity";
    private final int GETCONTACT=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        initViews();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case GETCONTACT:
                // TODO: 2016/4/19 优化Fab关闭效果
                mMenu.close(false);
                Uri uri=data.getData();
                Cursor cursor=getContentResolver().query(uri, null, null, null, null);
                cursor.moveToFirst();
                String name=cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                mAssignToTView.setText(name);
                break;

            default:break;
        }
    }

    //禁用返回
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        // 连按两次返回键退出程序
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) < 2000) {
                finish();
            }
            exitTime = System.currentTimeMillis();
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("确认放弃编辑吗？");
            builder.setTitle("提示");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    NewTaskActivity.this.finish();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }
        return false;
    }

    private void initViews() {
        dateTimeNow=new DateTime();
        mToolBar= (Toolbar) findViewById(R.id.toolbar_newtaskA);
        mLayout= (LinearLayout) findViewById(R.id.linearL_newtaskA);
        mMenu= (FloatingActionMenu) findViewById(R.id.fab_menu_new_task_activity);
        mTaskTitle= (TextView) findViewById(R.id.task_title_new_task_activity);
        mTaskContent= (TextView) findViewById(R.id.task_content_new_task_activity);
        mTaskStartDateTView= (TextView) findViewById(R.id.task_start_date_tview_new_task_activity);
        mTaskEndDateTView= (TextView) findViewById(R.id.task_end_date_tview_new_task_activity);
        mTaskStartTimeTView= (TextView) findViewById(R.id.task_start_time_tview_new_task_activity);
        mTaskEndTimeTView= (TextView) findViewById(R.id.task_end_time_tview_new_task_activity);
        mTaskStartTimeLL= (LinearLayout) findViewById(R.id.task_start_ll_new_task_activity);
        mTaskEndTimeLL= (LinearLayout) findViewById(R.id.task_end_ll_new_task_activity);
        mTaskTypeLL= (LinearLayout) findViewById(R.id.task_type_ll_new_task_activity);
        mTaskTypeImg= (ImageView) findViewById(R.id.task_type_img_new_task_activity);
        mContactFab= (FloatingActionButton) findViewById(R.id.contact_fab_mainA);
        mAssignToTView= (TextView) findViewById(R.id.assign_to_tv_new_task_activity);
//        mFab.setColorNormal(0x000000);
        alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);

        mToolBar.setNavigationIcon(R.drawable.arrow_back);
        mToolBar.setTitle("New Task");
        mToolBar.setTitleTextColor(0xffffffff);
        setSupportActionBar(mToolBar);
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                doCheck();
                return true;
            }
        });

        //TODO 重新设计默认显示的时间,设置过往时间不可选
        mTaskStartDateTView.setText(dateTimeNow.toString("yyyy年MM月dd日"));
        mTaskStartTimeTView.setText(dateTimeNow.toString("HH:mm"));
        mTaskEndDateTView.setText(dateTimeNow.toString("yyyy年MM月dd日"));
        mTaskEndTimeTView.setText(dateTimeNow.toString("HH:mm"));

        mTaskStartTimeLL.setOnClickListener(this);
        mTaskEndTimeLL.setOnClickListener(this);
        mTaskTypeLL.setOnClickListener(this);
        mContactFab.setOnClickListener(this);
        initService();
    }

    //TODO 检查任务条目的有效性
    private void doCheck() {
        if (TextUtils.isEmpty(mTaskTitle.getText().toString())) {
            Toast.makeText(NewTaskActivity.this,"任务标题不能为空",Toast.LENGTH_SHORT).show();
        } else {
            createTask();
        }
    }

    private void createTask() {
        TaskItem task=new TaskItem();
        task.setUserName(avUser.getUsername());
        task.setTaskTitle(mTaskTitle.getText().toString());
        task.setTaskContent(mTaskContent.getText().toString());

        taskStartDateTime=new DateTime(yearStart,monthOfStartYear,dayOfStartMonth,hourOfStartTime,minuteOfStartHour);
        taskEndDateTime=new DateTime(yearEnd,monthOfEndYear,dayOfEndMonth,hourOfEndTime,minuteOfEndHour);


        task.setTaskStartDateTime(taskStartDateTime);
        task.setTaskEndDateTime(taskEndDateTime);
        task.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    Toast.makeText(NewTaskActivity.this, "任务保存成功！", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NewTaskActivity.this, MainActivity.class));
                    finish();
                } else {
                    Log.d("test", "NewTaskActivity-createTask:" + e.getMessage());
                }
            }
        });
        AlarmManager alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent alarmIntent=new Intent(NewTaskActivity.this,AlarmActivity.class);
        PendingIntent pi=PendingIntent.getActivity(NewTaskActivity.this,0,alarmIntent,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, taskStartDateTime.getMillis(), pi);
    }

    private void initService() {
        avUser =AVUser.getCurrentUser();
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
                Toast.makeText(NewTaskActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: "+s);
                Toast.makeText(NewTaskActivity.this, s,Toast.LENGTH_SHORT).show();
            }
        };

        Observable<String> observable=Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hi");
                subscriber.onNext("Jean");
                subscriber.onNext("KK");
                subscriber.onCompleted();
            }
        });



    }

    private void bindData() {

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
                break;

            //设置任务起始日期与时间
            case R.id.task_start_ll_new_task_activity:
                Toast.makeText(this,"task start",Toast.LENGTH_SHORT).show();
                new TimePickerDialog(NewTaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mTaskStartTimeTView.setText(hourOfDay + "时" + minute + "分");
                        hourOfStartTime=hourOfDay;
                        minuteOfStartHour=minute;
                        Intent intent=new Intent(NewTaskActivity.this,AlarmActivity.class);
                        PendingIntent pi=PendingIntent.getActivity(NewTaskActivity.this,0,intent,0);
                        alarmManager.set(AlarmManager.RTC_WAKEUP,taskStartDateTime.getMillis(),pi);
                    }
                },dateTimeNow.getHourOfDay(),dateTimeNow.getMinuteOfHour(),true).show();

                new DatePickerDialog(NewTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mTaskStartDateTView.setText(year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日");
                        yearStart=year;
                        monthOfStartYear=monthOfYear+1;
                        dayOfStartMonth=dayOfMonth;
                    }
                },dateTimeNow.minusYears(1).getYear(),dateTimeNow.minusMonths(1).getMonthOfYear(),dateTimeNow.getDayOfMonth()).show();
                break;

            //设置任务结束日期与时间
            case R.id.task_end_ll_new_task_activity:
                Toast.makeText(this,"task end",Toast.LENGTH_SHORT).show();
                new TimePickerDialog(NewTaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mTaskEndTimeTView.setText(hourOfDay + "时" + minute + "分");
                        hourOfEndTime=hourOfDay;
                        minuteOfEndHour=minute;
                    }
                },dateTimeNow.getHourOfDay(),dateTimeNow.getMinuteOfHour(),true).show();

                new DatePickerDialog(NewTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mTaskEndDateTView.setText(year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日");
                        yearEnd=year;
                        monthOfEndYear=monthOfYear+1;
                        dayOfEndMonth=dayOfMonth;
                    }
                },dateTimeNow.minusYears(1).getYear(),dateTimeNow.minusMonths(1).getMonthOfYear(),dateTimeNow.getDayOfMonth()).show();

                break;

            case R.id.contact_fab_mainA:
                startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI),GETCONTACT);

            default:
                break;
        }
    }
}
