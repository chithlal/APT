package com.developer.chithlal.apt.root;

import android.app.Application;
import android.util.Log;

import static androidx.constraintlayout.widget.Constraints.TAG;

@SuppressWarnings("unused")
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: App");
    }
}
