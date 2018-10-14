package com.chi.marchoncitadel;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {
    //boolean to check if the game is playing or not
    volatile boolean playing;
    private Thread gameThread = null;

    private Player player;
    private Point playerPoint;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Enemy[] enemies;
    private int enemyCount = 3;
    private boolean movingPlayer = false;
    private ArrayList<Star> stars = new ArrayList<Star>();


    //Class constructor
    public GameView(Context context, int screenX, int screenY){
        super(context);

        //initialising player object
        player = new Player(context, screenX, screenY);

        //initialising drawing object
        surfaceHolder = getHolder();
        paint = new Paint();

        int starNums = 100;
        for (int i =0; i < starNums; i++){
            Star generateStars = new Star(screenX, screenY);
            stars.add(generateStars);
        }

        enemies = new Enemy[enemyCount];
        for (int i = 0; i < enemyCount; i++){
            enemies[i] = new Enemy(context,screenX,screenY);
        }
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
        player.update();
        for (Star generateStars: stars){
            generateStars.update(player.getSpeed());
        }
        for (int i = 0; i < enemyCount; i++){
            enemies[i].update(player.getSpeed());
        }

    }

    private void draw(){
        if (surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            //canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.battlefield),0,0,paint);
            paint.setColor(Color.WHITE);

            for (Star generateStars: stars){
                paint.setStrokeWidth(generateStars.getStarWidth());
                canvas.drawPoint(generateStars.getX(),generateStars.getY(),paint);
            }

            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getPlayerPoint().x,
                    player.getPlayerPoint().y,
                    paint);

            for (int i = 0; i < enemyCount; i++){
                canvas.drawBitmap(enemies[i].getBitmap(), enemies[i].getX(), enemies[i].getY(),paint);
            }
            surfaceHolder.unlockCanvasAndPost(canvas);

        }

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

    @Override

    public boolean onTouchEvent(MotionEvent motionEvent){
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                player.setBoosting();

                break;

            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                break;
        }
        return true;
    }
}
