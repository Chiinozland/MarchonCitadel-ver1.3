package com.chi.marchoncitadel;

import android.content.Context;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {
    //boolean to check if the game is playing or not
    volatile boolean playing;

    private Thread gameThread = null;

    //Class constructor
    public GameView(Context context){
        super(context);
    }

    @Override

    public void run(){
        while (playing){
            update();
            draw();
            control();
        }
    }

    private void update(){

    }

    private void draw(){

    }

    private void control(){
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void pause(){
        //set playing = false when the game is paused
        playing = false;
        try {
            //stop the thread
            gameThread.join();
        } catch (InterruptedException e){

        }

    }

    public void resume(){
        //set playing = true when the game is resumed
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
