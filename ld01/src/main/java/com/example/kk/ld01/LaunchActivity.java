package com.example.kk.ld01;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class LaunchActivity extends Activity {
    private ImageView mLogo,mDot1,mDot2,mDot3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initViews();
    }

    private void initViews() {


    }
}
