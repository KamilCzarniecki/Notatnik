package com.example.notatnik;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EasySplashScreen config= new EasySplashScreen(LoadingScreen.this)
                .withFullScreen()
                .withSplashTimeOut(5000)
                .withTargetActivity(MainActivity.class)
                .withBackgroundResource(R.drawable.background_note)
                .withBeforeLogoText("Notebook")
                .withAfterLogoText("Easy notes")
                .withLogo(R.drawable.iconi);

        config.getAfterLogoTextView().setTextColor(Color.WHITE);
        config.getBeforeLogoTextView().setTextColor(Color.WHITE);
        View easySplashScreenView= config.create();
        setContentView(easySplashScreenView);
    }
}
