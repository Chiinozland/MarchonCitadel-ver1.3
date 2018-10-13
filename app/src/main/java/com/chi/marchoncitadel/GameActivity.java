package com.chi.marchoncitadel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity{
    //declaring gameview
    private GameView gameView;

    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Initialising game view object
        gameView = new GameView(this);

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
