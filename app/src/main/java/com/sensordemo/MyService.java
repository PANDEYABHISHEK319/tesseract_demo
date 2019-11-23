package com.sensordemo;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {

    float x;
    float y;
    float z;
    SensorManager sensorManager;
    Sensor sensor;
    String TAG = getClass().getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        }

        return mBinder;
    }

    SensorEventListener eventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
            z = sensorEvent.values[2];

            try {
                if (iTrackingCallback != null) {
                    iTrackingCallback.onOrientationUpdate(x, y, z);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            Log.d(TAG, "  x: " + x + "  y: " + y + "  z: " + z);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    ITrackingCallback iTrackingCallback;
    private final ITracking.Stub mBinder = new ITracking.Stub() {
        @Override
        public void registerCallback(ITrackingCallback callback) throws RemoteException {

            if (sensor != null) {
                sensorManager.registerListener(eventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
                iTrackingCallback = callback;
            }


        }

        @Override
        public void unregisterCallback(ITrackingCallback callback) throws RemoteException {
            sensorManager.unregisterListener(eventListener);
            iTrackingCallback = null;

        }

    };


}
