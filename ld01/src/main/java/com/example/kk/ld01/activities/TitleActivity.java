package com.example.kk.ld01.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.kk.ld01.R;

public class TitleActivity extends Activity {

    private Button btnSkip,btnSignin;
    private Scene mAScene,mBScene;
    private FrameLayout mSceneRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        initViews();
    }

    private void initViews() {
        mSceneRoot= (FrameLayout) findViewById(R.id.scene_root);
        mAScene=Scene.getSceneForLayout(mSceneRoot,R.layout.a_scene,this);
        mBScene=Scene.getSceneForLayout(mSceneRoot,R.layout.b_scene,this);


        btnSkip= (Button) findViewById(R.id.skip_titleA_btn);
        btnSignin= (Button) findViewById(R.id.signin_titleA_btn);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TitleActivity.this,SignInActivity.class));
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionInflater inflater=TransitionInflater.from(TitleActivity.this);
                Transition mChangeBounds=inflater.inflateTransition(R.transition.changebounds_trans);
                TransitionManager.go(mBScene,mChangeBounds);
            }
        });
    }
}
