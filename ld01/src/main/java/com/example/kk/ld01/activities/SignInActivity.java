package com.example.kk.ld01.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.example.kk.ld01.R;
import com.rey.material.widget.Button;

public class SignInActivity extends Activity {
    private EditText mUsermame;
    private EditText mPassword;
    private Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initViews();
    }


    private void initViews() {
        mUsermame = (EditText) findViewById(R.id.username_signinA_edt);
        mPassword = (EditText) findViewById(R.id.password_signinA_edt);
        mSubmit= (Button) findViewById(R.id.signin_signinA_btn);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doValidate();
            }
        });
    }

    private void doValidate() {
        if (mUsermame.getText().toString().length()<3)
        {
            Toast.makeText(SignInActivity.this, "用户名输入有误", Toast.LENGTH_SHORT).show();
        }else {
            if (mPassword.getText().toString().length()<6)
            {
                Toast.makeText(SignInActivity.this, "密码输入有误", Toast.LENGTH_SHORT).show();
            }else {
                doLogin();
            }
        }
    }

    private void doLogin() {
        AVUser.logInInBackground(mUsermame.getText().toString(), mPassword.getText().toString(), new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e==null){
                    Toast.makeText(SignInActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignInActivity.this,MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(SignInActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
