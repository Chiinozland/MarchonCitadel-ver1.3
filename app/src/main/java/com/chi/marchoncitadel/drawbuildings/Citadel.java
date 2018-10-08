package com.chi.marchoncitadel.drawbuildings;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.chi.marchoncitadel.GameObject;

/**
 * This class is the set up of the citadel base.
 */
public class Citadel implements GameObject {

    private  Rect citadel;
    private int color;
    public Rect addCitadel(){
        return citadel;
    }

    public Citadel(Rect citadel, int color){
        this.citadel = citadel;
        this.color = color;

    }
    @Override
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(citadel, paint);
    }

    @Override
    public void update(){

    }

    public void update(Point point){
        citadel.set(point.x - citadel.width()/2,point.y - citadel.height()/2,point.x + citadel.width()/2, point.y + citadel.height()/2 );
    }
}
