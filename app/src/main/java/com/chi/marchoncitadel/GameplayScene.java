package com.chi.marchoncitadel;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

public class GameplayScene implements Scene {
    private Rect r = new Rect();
    private RectPlayer player;
    private Point playerPoint;
    private Citadel myCitadel;
    private Point myCitadelPoint;
    private Citadel enemyCitadel;
    private Point enemyCitadelPoint;
    private Citadel myMeleeBarrack;
    private Point myMeleeBarrackPoint;
    private Citadel myRangedBarrack;
    private Point myRangedBarrackPoint;
    private Citadel myMinerFactory;
    private Point myMinerFactoryPoint;
    private Citadel bottomMine;
    private Point bottomMinePoint;
    private Citadel topMine;
    private Point topMinePoint;
    private ObstacleManager obstacleManager;
    private Citadel topMeleeBarrack;
    private Point topMeleeBarrackPoint;
    private Citadel topRangedBarrack;
    private Point topRangedBarrackPoint;
    private Citadel topMinerFactory;
    private Point topMinerFactoryPoint;
    private boolean movingPlayer = false;
    private boolean gameOver = false;
    private long gameOverTime;

    public GameplayScene(){
        //set up troop
        player = new RectPlayer(new Rect(100,100,200,200), Color.GREEN);
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);

        //set up my buildings
        myCitadel = new Citadel(new Rect(0, 0, 200,200),Color.BLACK);
        myCitadelPoint = new Point(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT -50);
        myCitadel.update(myCitadelPoint);
        myMeleeBarrack = new Citadel(new Rect(100,100,200,200), Color.BLACK);
        myMeleeBarrackPoint = new Point(Constants.SCREEN_WIDTH/5, Constants.SCREEN_HEIGHT - 50);
        myMeleeBarrack.update(myMeleeBarrackPoint);
        myRangedBarrack = new Citadel(new Rect(100,100,200,200), Color.BLACK);
        myRangedBarrackPoint = new Point(Constants.SCREEN_WIDTH/3, Constants.SCREEN_HEIGHT - 50);
        myRangedBarrack.update(myRangedBarrackPoint);
        myMinerFactory = new Citadel(new Rect(100,100,200,200), Color.BLACK);
        myMinerFactoryPoint = new Point(5*Constants.SCREEN_WIDTH/6, Constants.SCREEN_HEIGHT - 50);
        myMinerFactory.update(myMinerFactoryPoint);
        bottomMine = new Citadel(new Rect(0,0,200,200), Color.YELLOW);
        bottomMinePoint = new Point(5*Constants.SCREEN_WIDTH/6, 5*Constants.SCREEN_HEIGHT/6);
        bottomMine.update(bottomMinePoint);


        //set up enemy citadel
        enemyCitadel = new Citadel(new Rect(0,0,200,200),Color.RED);
        enemyCitadelPoint = new Point(Constants.SCREEN_WIDTH/2,50);
        enemyCitadel.update(enemyCitadelPoint);
        topMine = new Citadel(new Rect(0,0,200,200), Color.YELLOW);
        topMinePoint = new Point(Constants.SCREEN_WIDTH/6, Constants.SCREEN_HEIGHT/6);
        topMine.update(topMinePoint);
        topMeleeBarrack = new Citadel(new Rect(100,100,200,200), Color.BLACK);
        topMeleeBarrackPoint = new Point(4*Constants.SCREEN_WIDTH/5, 50);
        topMeleeBarrack.update(topMeleeBarrackPoint);
        topRangedBarrack = new Citadel(new Rect(100,100,200,200), Color.BLACK);
        topRangedBarrackPoint = new Point(2*Constants.SCREEN_WIDTH/3, 50);
        topRangedBarrack.update(topRangedBarrackPoint);
        topMinerFactory = new Citadel(new Rect(100,100,200,200), Color.BLACK);
        topMinerFactoryPoint = new Point(Constants.SCREEN_WIDTH/6, 50);
        topMinerFactory.update(topMinerFactoryPoint);



        obstacleManager = new ObstacleManager(200, 350, 75,Color.BLACK);

    }

    public void reset(){
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager(200, 350, 75,Color.BLACK);
        movingPlayer = false;
    }

    @Override
    public void terminate(){
        SceneManager.ACTIVE_SCENE = 0;
    }

    //Touch control
    @Override
    public void receiveTouch(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (!gameOver && player.getRectangle().contains((int)event.getX(),(int)event.getY()))
                    movingPlayer = true;
                if (gameOver && System.currentTimeMillis() - gameOverTime >= 2000){
                    reset();
                    gameOver = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (!gameOver && movingPlayer)
                    playerPoint.set((int)event.getX(),(int) event.getY());
                break;
            case MotionEvent.ACTION_UP:
                movingPlayer = false;
                break;
        }
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawColor(Color.GRAY);

        myCitadel.draw(canvas);
        enemyCitadel.draw(canvas);
        myMinerFactory.draw(canvas);
        myRangedBarrack.draw(canvas);
        myMeleeBarrack.draw(canvas);
        bottomMine.draw(canvas);
        topMine.draw(canvas);
        topMeleeBarrack.draw(canvas);
        topMinerFactory.draw(canvas);
        topRangedBarrack.draw(canvas);
        player.draw(canvas);
        obstacleManager.draw(canvas);
        if (gameOver) {
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.MAGENTA);
            drawCentreText(canvas,paint,"Game Over");
        }
    }
    @Override
    public void update(){
        if (!gameOver){
            player.update(playerPoint);
            myCitadel.update(myCitadelPoint);
            obstacleManager.update();
            if (obstacleManager.playerCollide(player)){
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }
        }

    }
    public void drawCentreText(Canvas canvas, Paint paint, String text){
        paint.setTextAlign(Paint.Align.LEFT);

        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0,text.length(), r);
        float x = cWidth / 2f - r.width()/2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text,x,y,paint);

    }
}
