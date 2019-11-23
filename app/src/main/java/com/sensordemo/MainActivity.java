package com.sensordemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button register;
    Button unregister;
    TextView textView;
    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);

    }

    private void init() {
        register = findViewById(R.id.register);
        unregister = findViewById(R.id.unregister);
        textView = findViewById(R.id.text);

    }


    private final ITrackingCallback iTrackingCallback = new ITrackingCallback.Stub() {
        @Override
        public void onOrientationUpdate(float x, float y, float z) throws RemoteException {

            Log.d(TAG, String.valueOf(x) + "  " + String.valueOf(y) + "  " + String.valueOf(z));

        }
    };

    ITracking tracking;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            tracking = ITracking.Stub.asInterface(iBinder);
            try {
                tracking.registerCallback(iTrackingCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            try {
                tracking.unregisterCallback(iTrackingCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.register:

                break;
            case R.id.unregister:

                break;
        }

    }


}
