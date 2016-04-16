package com.cely404.nomad.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//Splash Screen used while data loads from application
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
        finish();
    }
}