// ITrackingCallback.aidl
package com.sensordemo;
import com.sensordemo.ITrackingCallback;

// Declare any non-default types here with import statements

interface ITracking {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void registerCallback(ITrackingCallback callback);
     void unregisterCallback(ITrackingCallback callback);
}
