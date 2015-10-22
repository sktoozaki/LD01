package com.example.kk.ld01;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.rey.material.widget.Button;

public class LoginActivity extends Activity {

    private Button btnSkip,btnSignIn;
    private ImageView imgLogo;

    // 定义小球的大小的常量
    static final float BALL_SIZE = 50F;
    // 定义小球从屏幕上方、下落到屏幕底端的总时间
    static final float FULL_TIME = 500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        imgLogo= (ImageView) findViewById(R.id.login_logo);
        btnSkip= (Button) findViewById(R.id.login_skip);
        btnSignIn= (Button) findViewById(R.id.login_signin);

        float startX=imgLogo.getX();
        float startY=imgLogo.getY();
        final ScaleAnimation animation=new ScaleAnimation(0.0f,1.4f,0.0f,1.4f, Animation.RELATIVE_TO_SELF,1f,Animation.RELATIVE_TO_SELF,1f);
        animation.setDuration(500);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgLogo.setAnimation(animation);
                animation.startNow();


               /* float startY=imgLogo.getY();
                float endY=startY-100;
                ValueAnimator ascendAnim= ObjectAnimator.ofFloat(imgLogo,"y",startY,endY);
                ascendAnim.setDuration(500);
                ascendAnim.setInterpolator(new AccelerateDecelerateInterpolator());
                AnimatorSet animatorSet=new AnimatorSet();
                animatorSet.play(ascendAnim);
                animatorSet.start();*/
            }
        });
    }

}

