package com.chi.marchoncitadel;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

public class GameActivity extends AppCompatActivity {
    //declaring gameView
    private GameView gameView;

    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        //Initialising game view object
        gameView = new GameView(this, size.x, size.y);

        //adding it to content view
        setContentView(gameView);
    }

    //pause game when activity is paused

    @Override
    protected void onPause(){
        super.onPause();
        gameView.pause();

    }

    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();

    }

}
