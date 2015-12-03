package com.example.kk.ld01.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kk.ld01.R;

public class TitleActivity extends Activity {

    private Button btnSignup,btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        initViews();
    }

    private void initViews() {
        btnSignup = (Button) findViewById(R.id.signup_titleA_btn);
        btnSignin= (Button) findViewById(R.id.signin_titleA_btn);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TitleActivity.this,SignInActivity.class));
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TitleActivity.this,SignUpActivity.class));
            }
        });
    }
}
