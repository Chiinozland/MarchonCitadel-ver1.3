package com.chi.marchoncitadel.control;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.chi.marchoncitadel.drawbuildings.GameplayScene;

import java.util.ArrayList;

/**
 * SceneManager focus on controlling the units, including troops and buildings.
 */
public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    public static int ACTIVE_SCENE;

    public SceneManager(){
        ACTIVE_SCENE = 0;
        scenes.add(new GameplayScene());
    }

    public void receiveTouch(MotionEvent event){
        scenes.get(ACTIVE_SCENE).receiveTouch(event);
    }

    public void update(){
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas){
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }

}
