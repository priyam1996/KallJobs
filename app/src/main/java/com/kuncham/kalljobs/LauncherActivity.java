package com.kuncham.kalljobs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        getSupportActionBar().hide();
        TimerTask ts=new TimerTask() {
            @Override
            public void run() {
                Intent i=new Intent(LauncherActivity.this,HomePage.class);
                startActivity(i);
                finish();
            }
        };

        Timer timer=new Timer();
        timer.schedule(ts,3000);

    }
}
