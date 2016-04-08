package com.example.kk.ld01.activities.Launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.avos.avoscloud.AVUser;
import com.example.kk.ld01.R;
import com.example.kk.ld01.activities.MainActivity;
import com.example.kk.ld01.activities.TitleActivity;

public class LaunchActivity extends Activity {
    private ImageView mLogo,mDot1,mDot2,mDot3;
    private RelativeLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initViews();
    }

    private void initViews() {
        Animation animStart= AnimationUtils.loadAnimation(this, R.anim.fade);
        mLayout=(RelativeLayout) findViewById(R.id.launch_layout);
        animStart.setDuration(3000);
        animStart.setFillAfter(false);

        animStart.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                AVUser currentUser = AVUser.getCurrentUser();
//                startActivity(new Intent(LaunchActivity.this, TitleActivity.class));

                if (currentUser != null) {
                    startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(LaunchActivity.this, TitleActivity.class));
                }
                finish();
            }

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }
        });
        mLayout.setAnimation(animStart);
    }
}