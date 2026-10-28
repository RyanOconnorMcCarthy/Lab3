package com.example.lab3;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

public class Monitor extends Service implements SensorEventListener{
    SensorManager manager;
    Sensor accelerometer;
    boolean movementDetected = false;
    boolean firstReading = true;
    float lastX;
    float lastY;
    float lastZ;

    public Monitor() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];




        Log.d("Motion", "X: " + x + " Y: " + y + " Z: " + z);

        if (firstReading == true) {
            lastX = x;
            lastY = y;
            lastZ = z;
            firstReading = false;
            return;
        }

        float changeX = Math.abs(x - lastX);
        float changeY = Math.abs(y - lastY);
        float changeZ = Math.abs(z - lastZ);

        if(movementDetected == false && (changeX > 2 || changeY > 2 || changeZ > 2)){
        movementDetected = true;
        Intent intent = new Intent(this, MovementActivity.class);
        intent.putExtra("time", System.currentTimeMillis());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);}

        lastX = x;
        lastY = y;
        lastZ = z;

    }

    public void onDestroy(){
        manager.unregisterListener(this);
        super.onDestroy();
    }
}