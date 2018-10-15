package com.chi.marchoncitadel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.media.Image;

import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.chi.marchoncitadel.control.GamePanel;

/**
 * MainActivity is going to create a fullscreen background to let the other class to draw and update.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton playBtn;
    private ImageButton scoreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        playBtn = findViewById(R.id.playBtn);
        scoreBtn = findViewById(R.id.scoreBtn);

        //adding click listener to buttons

        playBtn.setOnClickListener(this);
        scoreBtn.setOnClickListener(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        Constants.SCREEN_WIDTH = dm.widthPixels;

    }

    @Override
    public void onClick(View v) {
        if (v == playBtn) {
            startActivity(new Intent(MainActivity.this, GameActivity.class));
        }
        if (v == scoreBtn){
            startActivity(new Intent(MainActivity.this, HighScoreActivity.class));
        }
    }
}
