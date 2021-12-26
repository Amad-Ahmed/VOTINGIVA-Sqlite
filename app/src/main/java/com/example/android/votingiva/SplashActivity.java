package com.example.android.votingiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.HandlerThread;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    ImageView splashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splashImage=findViewById(R.id.splash_image);
        Animation splash_anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_anim);

        splashImage.startAnimation(splash_anim);
        final Intent i=new Intent(SplashActivity.this,LoginActivity.class);

        Thread splashTimer=new Thread(){
            public void run(){
                try{
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    startActivity(i);
                    finish();
                }
            }
        };
        splashTimer.start();
    }
}