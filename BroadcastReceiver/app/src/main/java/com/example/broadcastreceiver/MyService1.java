package com.example.broadcastreceiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService1 extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() { super.onCreate(); }
    @Override
    public void onStart(Intent intent, int startId) {
        Log.e("<<MyService1-onStart>>", "I am alive-1!");
        Log.e("<<MyService1-onStart>>", "I did something");
    }
    @Override
    public void onDestroy() { Log.e("<<MyService1-onDestroy>>", "I am dead-1"); }
}
