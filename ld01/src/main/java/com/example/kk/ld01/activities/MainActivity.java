package com.example.kk.ld01.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.kk.ld01.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {

        mToolbar= (Toolbar) findViewById(R.id.toolbar_mainA);

        mToolbar.setTitle(R.string.mainA_title);
        mToolbar.setSubtitle(R.string.mainA_subtitle);

        //识别不了color文件中的配置？？？
        mToolbar.setTitleTextColor(0xffffffff);
        mToolbar.setSubtitleTextColor(0xffffffff);
        setSupportActionBar(mToolbar);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_actions,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
