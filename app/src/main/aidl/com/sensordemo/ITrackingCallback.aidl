// ITrackingCallback.aidl
package com.sensordemo;
import com.sensordemo.ITracking;

// Declare any non-default types here with import statements

interface ITrackingCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void onOrientationUpdate(float x, float y, float z);

}
