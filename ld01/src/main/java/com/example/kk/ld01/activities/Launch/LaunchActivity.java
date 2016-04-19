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

import butterknife.Bind;
import butterknife.ButterKnife;

public class LaunchActivity extends Activity {

    @Bind(R.id.launch_layout) RelativeLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        Animation animStart= AnimationUtils.loadAnimation(this, R.anim.fade);
        animStart.setDuration(3000);
        animStart.setFillAfter(false);

        animStart.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                AVUser currentUser = AVUser.getCurrentUser();

                if (currentUser != null) {
                    startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(LaunchActivity.this, TitleActivity.class));
                }
                finish();
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mLayout.setAnimation(animStart);
    }
}
