package com.example.kk.ld01.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.example.kk.ld01.R;

public class SignInActivity extends Activity {
    private Button btnSignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initViews();
    }


    private void initViews() {
        btnSignin= (Button) findViewById(R.id.signin_signinA_btn);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this,MainActivity.class ));
            }
        });
    }
}
