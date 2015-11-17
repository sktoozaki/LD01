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
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
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

        final ScaleAnimation scaleAnimation=new ScaleAnimation(1f,0.6f,1f,0.6f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);

        final TranslateAnimation translateAnimation=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,
                0.0f,Animation.RELATIVE_TO_SELF,0.5f);
        translateAnimation.setDuration(500);
        translateAnimation.setFillAfter(true);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));

//                imgLogo.setAnimation(scaleAnimation);
//                imgLogo.startAnimation(scaleAnimation);

//                imgLogo.setAnimation(translateAnimation);
//                imgLogo.startAnimation(translateAnimation);

//                scaleAnimation.startNow();
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

