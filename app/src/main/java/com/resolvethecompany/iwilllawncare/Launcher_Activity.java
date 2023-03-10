package com.resolvethecompany.iwilllawncare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Launcher_Activity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        imageView = findViewById(R.id.imageView);
    }

    @Override
    protected void onStart(){
        super.onStart();

        /*Timer to change to login activity*/
        timerToActivityChange_Method();
    }
    private void timerToActivityChange_Method(){
        new CountDownTimer(3500, 1000) {

            public void onTick(long millisUntilFinished) {
                /**Load gif into image view*/
                Glide.with(Launcher_Activity.this)
                        .load(R.raw.grasscutting)
                        .into(imageView);
            }

            public void onFinish() {
                Intent toLogin =
                        new Intent(
                                Launcher_Activity.this,
                                Login_Activity.class);

                startActivity(toLogin);
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}