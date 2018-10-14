package com.chi.marchoncitadel;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
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
    private int enemyCount = 2;
    private Friend friend;
    private boolean movingPlayer = false;
    private ArrayList<Star> stars = new ArrayList<Star>();
    private Boom boom;

    // a screenY holder
    int screenY;

    // counting number of missed enemy
    int countMiss;

    // indicator of coming enemy
    boolean flag;

    // indicator of game over
    private boolean isGameOver;


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

        boom = new Boom(context);

        friend = new Friend(context, screenX, screenY);

        this.screenY = screenY;
        countMiss = 0;
        isGameOver = false;
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
        boom.setX(-250);
        boom.setY(-250);

        for (Star generateStars: stars){
            generateStars.update(player.getSpeed());
        }



        for (int i = 0; i < enemyCount; i++){
            if (enemies[i].getY() == 0){
                flag = true;
            }
            enemies[i].update(player.getSpeed());
            if (Rect.intersects(player.getDetectCollision(),enemies[i].getDetectCollision())){
                boom.setX(enemies[i].getX());
                boom.setY(enemies[i].getY());
                enemies[i].setY(- 200);
            } else {
                if (flag) {
                    //if player's y coordinate is more than the enemies's x coordinate.i.e. enemy has just passed across the player
                    if (enemies[i].getDetectCollision().exactCenterY() >= Constants.SCREEN_HEIGHT) {
                        //increment countMisses
                        countMiss++;

                        //setting the flag false so that the else part is executed only when new enemy enters the screen
                        flag = false;
                        //if no of Misses is equal to 3, then game is over.
                        if (countMiss == 3) {
                        //setting playing false to stop the game.
                        playing = false;
                        isGameOver = true;
                        }
                    }
                }
            }

            friend.update(player.getSpeed());
            //checking for a collision between player and a friend
            if(Rect.intersects(player.getDetectCollision(),friend.getDetectCollision())){

                //displaying the boom at the collision
                boom.setX(friend.getX());
                boom.setY(friend.getY());
                //setting playing false to stop the game
                //playing = false;
                //setting the isGameOver true as the game is over
                //isGameOver = true;
            }
        }

        friend.update(player.getSpeed());


    }

    private void draw(){
        if (surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            //canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.battlefield),0,0,paint);
            paint.setColor(Color.WHITE);
            paint.setTextSize(20);

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

            canvas.drawBitmap(
                    boom.getBitmap(),
                    boom.getX(),
                    boom.getY(),
                    paint
            );

            canvas.drawBitmap(
                    friend.getBitmap(),
                    friend.getX(),
                    friend.getY(),
                    paint
            );

            if (isGameOver){
                paint.setTextSize(150);
                paint.setTextAlign(Paint.Align.CENTER);

                int yPos = (int)((canvas.getHeight()/2) - ((paint.descent() + paint.ascent())/2));
                canvas.drawText("Game Over", canvas.getWidth()/2, yPos, paint);
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
