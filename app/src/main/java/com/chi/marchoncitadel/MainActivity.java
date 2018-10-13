package com.chi.marchoncitadel;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.chi.marchoncitadel.control.GamePanel;

/**
 * MainActivity is going to create a fullscreen background to let the other class to draw and update.
 */
public class MainActivity extends Activity {

    private ImageButton playBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        playBtn = (ImageButton)findViewById(R.id.playBtn);

        //adding click listener to playBtn

        playBtn.setOnClickListener(this);


        //be back soon

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        Constants.SCREEN_WIDTH = dm.widthPixels;

        setContentView(new GamePanel(this));
    }
}
