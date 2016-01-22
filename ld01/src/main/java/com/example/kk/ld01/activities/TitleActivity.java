package com.example.kk.ld01.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kk.ld01.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class TitleActivity extends Activity {

    private Button btnSignup,btnSignin;
    private HttpUtils httpUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        initViews();
    }

    private void initViews() {
        btnSignup = (Button) findViewById(R.id.signup_titleA_btn);
        btnSignin= (Button) findViewById(R.id.signin_titleA_btn);
        httpUtils=new HttpUtils();
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TitleActivity.this, SignUpActivity.class));
                finish();
               /* httpUtils.send(HttpRequest.HttpMethod.GET, "http://123.57.158.42:12306/register", new RequestCallBack<String>() {

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Toast.makeText(TitleActivity.this,responseInfo.result,Toast.LENGTH_SHORT ).show();
                        startActivity(new Intent(TitleActivity.this, SignUpActivity.class));
                        finish();
                    }
                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(TitleActivity.this,"Error",Toast.LENGTH_SHORT ).show();
                    }
                });*/
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TitleActivity.this, SignInActivity.class));
                finish();
               /* httpUtils.send(HttpRequest.HttpMethod.GET, "http://123.57.158.42:12306/login", new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Toast.makeText(TitleActivity.this,responseInfo.result,Toast.LENGTH_SHORT ).show();
                        startActivity(new Intent(TitleActivity.this, SignInActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(TitleActivity.this,"Error",Toast.LENGTH_SHORT ).show();
                    }
                });*/
            }
        });
    }
}
