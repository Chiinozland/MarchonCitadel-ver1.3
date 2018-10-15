package com.chi.marchoncitadel;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class OrientationData implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor magnometer;

    private float[] accelOutput;
    private float[] magOutput;
    private float[] orientation;

    public float[] getOrientation(){
        return orientation;
    }

    private float[] startOrientation = null;

    public float[] getStartOrientation(){
        return startOrientation;
    }

    public void newGame(){
        startOrientation = null;
    }

    public OrientationData(){
        sensorManager = (SensorManager)Constants.CURRENT_CONTEXT.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public void register(){
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, magnometer, SensorManager.SENSOR_DELAY_GAME);

    }

    public void pause(){
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override
    public void onSensorChanged(SensorEvent event){
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            accelOutput = event.values;
        else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            magOutput = event.values;
        if (accelOutput != null && magOutput != null){
            float[] R = new float[9];
            float[] I = new float[9];
            boolean success = SensorManager.getRotationMatrix(R,I,accelOutput,magOutput);
            if (success){
                SensorManager.getOrientation(R, orientation);
                if (startOrientation == null){
                    startOrientation = new float [orientation.length];
                    System.arraycopy(orientation, 0, startOrientation, 0, orientation.length);
                    //startOrientation = orientation;
                }
            }
        }
    }
}
