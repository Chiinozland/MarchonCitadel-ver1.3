package com.chi.marchoncitadel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

public class Enemy {
    private Bitmap bitmap;

    private int x;
    private int y;
    private int speed = 1;
    private int maxX;
    private int minX;

    private int maxY;
    private int minY;

    private Rect detectCollision;

    public Enemy(Context context, int screenX, int screenY) {
        //getting bitmap from drawable resource
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);

        //initializing min and max coordinates
        maxX = Constants.SCREEN_WIDTH;
        maxY = Constants.SCREEN_HEIGHT;
        minX = 0;
        minY = 0;

        //generating a random coordinate to add enemy
        Random generator = new Random();
        speed = generator.nextInt(6) + 10;
        y = 0;
        x = generator.nextInt(maxX) - bitmap.getWidth();
        detectCollision = new Rect(x, y, bitmap.getWidth(),bitmap.getHeight());
    }


    public void update(int playerSpeed) {
        //increasing y coordinate so that enemy will move top to bottom

        y += speed;
        //if the enemy reaches the bottom edge
        if (y > maxY + bitmap.getHeight()) {
            //adding the enemy again to the top edge
            Random generator = new Random();
            speed = generator.nextInt(10) + 10;
            y = minY;
            x = generator.nextInt(maxX) - bitmap.getWidth();
        }



        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setX(int x) { this.x = x; }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
}
