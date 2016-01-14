package com.example.kk.ld01.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kk.ld01.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.rey.material.widget.Button;

import javax.xml.datatype.Duration;

public class LoginActivity extends Activity {

    private Button btnSkip,btnSignIn;
    private ImageView imgLogo;

    private HttpUtils httpUtils;

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

        httpUtils=new HttpUtils();



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpUtils.send(HttpRequest.HttpMethod.GET, "http://192.168.13.69:12306", new RequestCallBack<String>() {

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Toast.makeText(LoginActivity.this,responseInfo.toString(),Toast.LENGTH_SHORT ).show();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_SHORT ).show();
                    }
                });

//                startActivity(new Intent(LoginActivity.this,MainActivity.class));

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

