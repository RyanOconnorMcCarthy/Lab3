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

    }

    public void onDestroy(){
        manager.unregisterListener(this);
        super.onDestroy();
    }
}