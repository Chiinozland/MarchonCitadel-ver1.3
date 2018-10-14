package com.chi.marchoncitadel;

import java.util.Random;

public class Star {
    private int x;
    private int y;
    private int speed;

    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    public Star (int screenX, int screenY){
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;

        Random generator = new Random();
        speed = generator.nextInt(10);

        x = generator.nextInt(maxX);
        y = generator.nextInt(maxY);
    }

    public void update(int playerSpeed){
        y += playerSpeed;
        y += speed;

        if (y > Constants.SCREEN_HEIGHT){
            y = minY;
            Random generator = new Random();
            x = generator.nextInt(maxX);
            speed = generator.nextInt(15);
        }
    }

    public float getStarWidth(){
        float minY = 1.0f;
        float maxY = 4.0f;
        Random rand = new Random();
        float finalY = rand.nextFloat() * (maxY - minY) + minY;
        return finalY;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
