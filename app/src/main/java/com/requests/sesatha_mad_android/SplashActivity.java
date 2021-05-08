package com.requests.sesatha_mad_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    private Handler WaitHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        WaitHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Go to next main activity.
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

                finish();
            }
        },1600);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WaitHandler.removeCallbacksAndMessages(null);
    }
}