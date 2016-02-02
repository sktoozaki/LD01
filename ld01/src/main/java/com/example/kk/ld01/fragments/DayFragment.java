package com.example.kk.ld01.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.example.kk.ld01.R;
import com.example.kk.ld01.activities.NewTaskActivity;
import com.example.kk.ld01.models.TaskItem;
import com.example.kk.ld01.utils.BaseViewHolder;
import com.example.kk.ld01.utils.CommonAdapter;
import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.github.clans.fab.FloatingActionButton;

import org.joda.time.DateTime;

import java.util.Iterator;
import java.util.List;

/**
 * Created by KK on 2016/1/29.
 */

public class DayFragment extends Fragment {
    private ListView mListView;
    private TextView mTextView;
    private AddFloatingActionButton mAddFab;
    private FloatingActionButton mFab;
    private AVUser mUser;

    private DateTime dateTime;
    private List<TaskItem> taskList;
    private CommonAdapter<TaskItem> listAdapter;
    private long exitTime = 0;
    private DateTime dateTimeNow;
    private List<DateTime> dateTimeThisWeek;

    public DayFragment(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_day, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initService();
    }

    /*初始化服务请求*/
    private void initService() {
        mUser = AVUser.getCurrentUser();
        if (mUser != null) {
            getTasks(mUser.getUsername(), dateTime);
        } else {
        }
    }

    /*根据用户名和DateTime获取任务*/
    private void getTasks(String username, final DateTime dateTime) {
        AVQuery<TaskItem> query = AVObject.getQuery(TaskItem.class);
        query.whereEqualTo("userName", username);
        //TODO 测试通过DayOfYear来获取任务
        Log.d("test", "getTasksOf " + dateTime.getDayOfYear());
        query.findInBackground(new FindCallback<TaskItem>() {
            @Override
            public void done(List<TaskItem> list, AVException e) {
                if (e == null) {
                    Log.d("test", "getTasks:" + list.size() + "条数据");
                    Iterator<TaskItem> tasksIter = list.iterator();
                    while (tasksIter.hasNext()) {
                        TaskItem task = tasksIter.next();
                        if (task.getTaskStartDateTime().getDayOfYear() != dateTime.getDayOfYear()) {
                            tasksIter.remove();
                        }
                    }
                    bindTaskItem(list);
                } else {
                    Log.d("test", "错误：" + e.getMessage());
                }
            }
        });
    }

    /*绑定任务数据*/
    private void bindTaskItem(List<TaskItem> list) {
        if (list.size() < 1) {
            mTextView.setText("没有任务");
        } else {
            Log.d("test", "itemlist不为空");
            taskList = list;
            listAdapter = new CommonAdapter<TaskItem>(getActivity(), list, R.layout.taskitem) {
                @Override
                public void convert(BaseViewHolder helper, TaskItem item) {
                    helper.setText(R.id.task_title, item.getTaskTitle())
                            .setText(R.id.task_content, item.getTaskContent())
                            .setText(R.id.task_time, item.getTaskStartDateTime().toString("HH:mm"));
                }

            };
            mListView.setAdapter(listAdapter);
            listAdapter.notifyDataSetChanged();
        }
    }

    private void initViews() {
        mListView = (ListView) getView().findViewById(R.id.listview_day);
        mTextView = (TextView) getView().findViewById(R.id.textview_day);
        mAddFab = (AddFloatingActionButton) getView().findViewById(R.id.fab_day);
        ;


        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NewTaskActivity.class));
            }
        });
    }
}
