package com.example.kk.ld01.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.kk.ld01.R;

public class LaunchActivity extends Activity {
    private ImageView mLogo,mDot1,mDot2,mDot3;
    private LinearLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initViews();
    }

    private void initViews() {
        Animation animStart= AnimationUtils.loadAnimation(this, R.anim.fade);
        final LinearLayout mLayout=(LinearLayout) findViewById(R.id.launch_layout);
        animStart.setDuration(3000);
        animStart.setFillAfter(false);

        animStart.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationEnd(Animation animation)
            {
                Intent intent=new Intent(LaunchActivity.this, TitleActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationStart(Animation animation)
            {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
                // TODO Auto-generated method stub
            }
        });
        mLayout.setAnimation(animStart);
    }
}
