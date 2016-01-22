package com.example.kk.ld01.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
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
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.example.kk.ld01.R;
import com.example.kk.ld01.models.TaskItem;
import com.example.kk.ld01.utils.LDResponse;
import com.github.clans.fab.FloatingActionMenu;

import java.util.Calendar;

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
    private TextView mTaskStartDateTView;
    private TextView mTaskEndDateTView;
    private TextView mTaskStartTimeTView;
    private TextView mTaskEndTimeTView;
    private LinearLayout mTaskStartTimeLL;
    private LinearLayout mTaskEndTimeLL;
    private LinearLayout mTaskTypeLL;
    private LinearLayout mTaskTypeOptionsLL;
    private ImageView mTaskTypeImg;
    private RadioGroup mTaskOptionsRadioGroup;

    private LDResponse ldResponse;
    private AVUser mUser;


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
        mTaskStartDateTView= (TextView) findViewById(R.id.task_start_date_tview_new_task_activity);
        mTaskEndDateTView= (TextView) findViewById(R.id.task_end_date_tview_new_task_activity);
        mTaskStartTimeTView= (TextView) findViewById(R.id.task_start_time_tview_new_task_activity);
        mTaskEndTimeTView= (TextView) findViewById(R.id.task_end_time_tview_new_task_activity);
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
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                doCheck();
                return true;
            }
        });


        mTaskStartTimeLL.setOnClickListener(this);
        mTaskEndTimeLL.setOnClickListener(this);
        mTaskTypeLL.setOnClickListener(this);

        //TODO 使用Joda-Time库的时间函数替代JAVA API
        initService();
    }

    private void doCheck() {
        if (TextUtils.isEmpty(mTaskTitle.getText().toString())) {
            Toast.makeText(NewTaskActivity.this,"任务标题不能为空",Toast.LENGTH_SHORT).show();
        } else {
            createTask();
        }
    }

    private void createTask() {
        TaskItem task=new TaskItem();
        task.setTaskTitle(mTaskTitle.getText().toString());
        task.setTaskContent(mTaskContent.getText().toString());
        task.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e==null)
                {
                    Toast.makeText(NewTaskActivity.this,"任务保存成功！",Toast.LENGTH_SHORT).show();
                }else {
                    Log.d("test","NewTaskActivity-createTask:"+e.getMessage());
                }
            }
        });
    }

    private void initService() {
        mUser=AVUser.getCurrentUser();

        //---------------------本地测试的分界线Begin----------------------
        /*HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET,
                "http://123.57.158.42:12306/newtask",
                new RequestCallBack<String>() {
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {

                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        ldResponse = new LDResponse(responseInfo.result);
                        if (ldResponse.getStatus() == 0) {
                            Toast.makeText(NewTaskActivity.this, "LDResponse Parsing", Toast.LENGTH_SHORT).show();
                            Log.d("test", "LDResponse Parsing");
                            bindData();
                        } else {
                            //TODO 异常处理
                            Toast.makeText(NewTaskActivity.this, "Not Parsing", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                    }
                });*/
        //---------------------本地测试的分界线End----------------------
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
        final Calendar calendar=Calendar.getInstance();
        switch (v.getId())
        {
            case R.id.task_type_ll_new_task_activity:
                mTaskTypeOptionsLL.setVisibility(View.VISIBLE);
                Toast.makeText(this,"task type",Toast.LENGTH_SHORT).show();
                break;

            case R.id.task_start_ll_new_task_activity:
                Toast.makeText(this,"task start",Toast.LENGTH_SHORT).show();
                new TimePickerDialog(NewTaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mTaskStartTimeTView.setText(hourOfDay + "时" + minute + "分");
                    }
                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();

                new DatePickerDialog(NewTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mTaskStartDateTView.setText(year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日");
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();


                break;

            case R.id.task_end_ll_new_task_activity:
                Toast.makeText(this,"task end",Toast.LENGTH_SHORT).show();

                new TimePickerDialog(NewTaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mTaskEndTimeTView.setText(hourOfDay + "时" + minute + "分");
                    }
                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();

                new DatePickerDialog(NewTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mTaskEndDateTView.setText(year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日");
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

                break;

            default:
                break;
        }
    }
}
