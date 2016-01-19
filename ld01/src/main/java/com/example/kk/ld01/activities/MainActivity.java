package com.example.kk.ld01.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kk.ld01.R;
import com.example.kk.ld01.models.TaskItem;
import com.example.kk.ld01.utils.BaseViewHolder;
import com.example.kk.ld01.utils.CommonAdapter;
import com.example.kk.ld01.utils.LDResponse;
import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView mListView;
    private AddFloatingActionButton mFAB;
    private List<TaskItem> mTaskItemList;
    private TaskItem mTaskItem;
    private LDResponse ldResponse;
    private CommonAdapter listAdapter;
    private long mExitTime = 0;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initService();
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

        //---------------------本地测试的分界线Begin----------------------
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET,
                "http://123.57.158.42:12306/tasks",
                new RequestCallBack<String>() {
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {

                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        ldResponse = new LDResponse(responseInfo.result);
                        if (ldResponse.getStatus() == 0) {
                            Toast.makeText(MainActivity.this, "LDResponse Parsing", Toast.LENGTH_SHORT).show();
                            Log.d("test", "LDResponse Parsing");
                            bindData();
                        } else {
                            //TODO 异常处理
                            Toast.makeText(MainActivity.this, "Not Parsing", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                    }
                });
        //---------------------本地测试的分界线End----------------------


    }

    /**
     * 绑定数据
     */
    private void bindData() {
        gson=new Gson();

        /*mTaskItemList = new ArrayList<>();

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
        });*/

        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewTaskActivity.class));
            }
        });


        mTaskItemList=new ArrayList<>();
        try {
            mTaskItemList=gson.fromJson(ldResponse.getData().getJSONArray("tasks").toString(), new TypeToken<ArrayList<TaskItem>>() {
            }.getType());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("test", mTaskItemList.toString());
        /*BitmapUtils bitmapUtils=new BitmapUtils(this);
        bitmapUtils.display(mImageView, adURL);
        mListView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false,true));*/
        if (mTaskItemList==null)
        {
            Log.d("test", "itemlist为空");
        }else {
            Log.d("test", mTaskItemList.get(2).getTaskContent());
        }

        listAdapter=new CommonAdapter<TaskItem>(this,mTaskItemList,R.layout.taskitem) {
            @Override
            public void convert(BaseViewHolder helper, TaskItem item) {
                helper.setText(R.id.task_title,item.getTaskTitle())
                        .setText(R.id.task_content,item.getTaskContent());
            }


       /*     @Override
            protected BaseViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
                return super.getViewHolder(position, convertView, parent);
            }

            @Override
            public void convert(BaseViewHolder helper, ViewPagerNewItem item) {
                helper.setText(R.id.title_tv_item_viewpager_new, item.getNewTitle())
                        .setText(R.id.content_tv_item_viewpager_new, item.getNewContent())
                        .setText(R.id.price_tv_item_viewpager_new,item.getNewPrice().toString())
                        .setImageByUrl(R.id.product_img_item_viewpager_new, item.getNewImageURL());
//                        .setTextStyle(R.id.marketprice_tv_item_viewpager_new, "¥" + item.getNewMarketPrice().toString(),1);
            }*/
        };
        mListView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
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



        //TODO 功能化ToolBar

        //TODO 动态更新Menu

        //TODO 采用可编辑的抽屉菜单作为ListItem

        //TODO 添加CalendarView与下拉事件

        //TODO 获取服务中的Task

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
