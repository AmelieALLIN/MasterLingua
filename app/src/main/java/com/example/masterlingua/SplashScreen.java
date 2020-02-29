package com.example.masterlingua;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int SPLASH_TIME_OUT = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent premierePage = new Intent(getApplicationContext(), ChoisirCreationCarte.class);
                startActivity(premierePage);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
