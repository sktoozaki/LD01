package com.example.kk.ld01.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SignUpCallback;
import com.example.kk.ld01.R;
import com.rey.material.widget.Button;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    private EditText mUsermame;
    private EditText mPassword;
    private EditText mRePassword;
    private Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();
    }

    private void initViews() {
        mUsermame = (EditText) findViewById(R.id.username_signupA_edt);
        mPassword = (EditText) findViewById(R.id.password_signinA_edt);
        mRePassword = (EditText) findViewById(R.id.repassword_signinA_edt);
        mSubmit = (Button) findViewById(R.id.submit_signupA_btn);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doValidate();
            }
        });
    }

    private void doRegister() {
       AVUser user=new AVUser();
        user.setUsername(mUsermame.getText().toString());
        user.setPassword(mPassword.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e==null){
                    Toast.makeText(SignUpActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                }else {
                    Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void isRegistered() {
        AVQuery<AVObject> query=new AVQuery<AVObject>("_User");
        query.whereEqualTo("users",mUsermame.getText().toString());
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e==null){
                    Toast.makeText(SignUpActivity.this, "该用户名已被注册，请重新输入", Toast.LENGTH_SHORT).show();
                }else {
                    doRegister();
                }
            }
        });
    }

    private void doValidate() {
        if (mUsermame.getText().toString().length()<3)
        {
            Toast.makeText(SignUpActivity.this, "用户名不能少于3个字符", Toast.LENGTH_SHORT).show();
        }else {
            if (mPassword.getText().toString().length()<6)
            {
                Toast.makeText(SignUpActivity.this, "密码不能少于6个字符", Toast.LENGTH_SHORT).show();
            }else {
                if (TextUtils.equals(mPassword.getText().toString(),mRePassword.getText().toString()))
                {
                    doRegister();
                }else {
                    Toast.makeText(SignUpActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
