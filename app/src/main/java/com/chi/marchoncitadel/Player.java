package com.chi.marchoncitadel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Player implements GameObject{
    //Bitmap to get character from image
    private Bitmap bitmap;
    private Rect melee;
    private Point playerPoint;
    private int maxX = Constants.SCREEN_WIDTH;
    private int maxY = Constants.SCREEN_HEIGHT;
    private int minX = 0;
    private int minY = 0;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    //motion speed of the character
    private int speed = 0;
    private boolean boosting;
    private final int GRAVITY = -10;
    private Rect detectCollision;




    //constructor
    public Player(Context context, int screenX, int screenY){
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4);
        speed = 1;
        //getting bitmap from drawable resource
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.melee);

        maxX = screenX - bitmap.getWidth();
        minX = 0;
        maxY = screenY - bitmap.getHeight();
        minY = 0;
        boosting = false;

        detectCollision = new Rect(playerPoint.x, playerPoint.y,bitmap.getWidth(),bitmap.getHeight());

    }

    public void setBoosting(){
        boosting = true;
    }

    public void stopBoosting(){
        boosting = false;
    }

    @Override
    public void draw(Canvas canvas) {


    }

    public void update(){
        if (boosting){
            speed += 2;
        } else {
            speed -= 5;
        }
        if (speed > MAX_SPEED){
            speed = MAX_SPEED;
        }
        if (speed < MIN_SPEED){
            speed = MIN_SPEED;
        }

        playerPoint.x -= speed + GRAVITY;
        if (playerPoint.x < minX){
            playerPoint.x = minX;
        }
        if (playerPoint.x > maxX){
            playerPoint.x = maxX;
        }if (playerPoint.y < minY){
            playerPoint.y = minY;
        }
        if (playerPoint.y > maxY){
            playerPoint.y = maxY;
        }

        detectCollision.left = playerPoint.x;
        detectCollision.top = playerPoint.y;
        detectCollision.right = playerPoint.x + bitmap.getWidth();
        detectCollision.bottom = playerPoint.y + bitmap.getHeight();
    }


    public Rect getDetectCollision(){
        return detectCollision;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
    public Point getPlayerPoint(){
        return playerPoint;
    }


    public int getSpeed() {
        return speed;
    }
}
