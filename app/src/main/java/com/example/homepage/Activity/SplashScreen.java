package com.example.homepage.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homepage.R;

public class SplashScreen extends AppCompatActivity {

    Animation topAnim,bottomAnim,shakeAnim;
    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        shakeAnim= AnimationUtils.loadAnimation(this,R.anim.shake);
        imageView=findViewById(R.id.simg);
        textView=findViewById(R.id.s_txt);

        imageView.setAnimation(topAnim);
        textView.setAnimation(bottomAnim);

//        AnimationSet s = new AnimationSet(false);//false means don't share interpolators
//        s.addAnimation(topAnim);
//        s.addAnimation(shakeAnim);
//        imageView.startAnimation(s);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();

            }

        },
        3000);
    }
}