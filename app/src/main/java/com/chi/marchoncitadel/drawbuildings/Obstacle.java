package com.chi.marchoncitadel.drawbuildings;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.chi.marchoncitadel.Constants;
import com.chi.marchoncitadel.GameObject;
import com.chi.marchoncitadel.spawnunits.RectPlayer;

public class Obstacle implements GameObject {
    private Rect rectangle;
    private Rect rectangle2;
    private int color;
    private int startX;
    private int playerGap;
    private Rect bottomMine;

    public Rect getRectangle(){
        return rectangle;
    }

//    public void incrementX (float x){
//        rectangle.left += x;
//        rectangle.right += x;
//        rectangle2.left += x;
//        rectangle2.right += x;
//
//    }

    public Obstacle(int rectHeight, int color, int startX, int startY, int playerGap){
        this.color = Color.BLUE;
        rectangle = new Rect(0,(Constants.SCREEN_HEIGHT-rectHeight)/2, startX, (Constants.SCREEN_HEIGHT+rectHeight)/2);
        rectangle2 = new Rect(startX+playerGap, (Constants.SCREEN_HEIGHT-rectHeight)/2, Constants.SCREEN_WIDTH,(Constants.SCREEN_HEIGHT+rectHeight)/2);


    }

    public boolean playerCollide(RectPlayer player){
        return Rect.intersects(rectangle, player.getRectangle())||Rect.intersects(rectangle2, player.getRectangle());
    }

    public boolean addGold(RectPlayer player){
        return Rect.intersects(bottomMine, player.getRectangle());
    }


    @Override
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
        canvas.drawRect(rectangle2,paint);
    }

    @Override
    public void update(){

    }
}
