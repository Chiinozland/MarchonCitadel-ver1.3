package com.chi.marchoncitadel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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

    public Enemy(Context context, int screenX, int screenY) {
        //getting bitmap from drawable resource
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);

        //initializing min and max coordinates
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;

        //generating a random coordinate to add enemy
        Random generator = new Random();
        speed = generator.nextInt(6) + 10;
        y = 0;
        x = generator.nextInt(maxX) - bitmap.getWidth();

    }
    public void update(int playerSpeed) {
        //increasing y coordinate so that enemy will move top to bottom
        y += playerSpeed;
        y += speed;
        //if the enemy reaches the bottom edge
        if (y > maxY + bitmap.getHeight()) {
            //adding the enemy again to the top edge
            Random generator = new Random();
            speed = generator.nextInt(10) + 10;
            y = minY;
            x = generator.nextInt(maxX) - bitmap.getWidth();
        }
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
