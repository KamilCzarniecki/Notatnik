package com.example.notatnik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EasySplashScreen config= new EasySplashScreen(LoadingScreen.this)
                .withFullScreen()
                .withSplashTimeOut(1500)
                .withTargetActivity(MainActivity.class)
                .withBackgroundResource(R.color.black)
                .withBeforeLogoText("Notebook")
                .withAfterLogoText("Easy notes")
                .withLogo(R.drawable.icon);

        config.getAfterLogoTextView().setTextColor(Color.WHITE);
        config.getBeforeLogoTextView().setTextColor(Color.WHITE);
        Typeface font = ResourcesCompat.getFont(this,R.font.pacifico);
        config.getBeforeLogoTextView().setTypeface(font);
        //config.getAfterLogoTextView().setTypeface(font);
        View easySplashScreenView= config.create();
        setContentView(easySplashScreenView);
    }
}
