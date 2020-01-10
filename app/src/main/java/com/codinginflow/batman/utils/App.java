package com.codinginflow.batman.utils;


import android.app.Application;
import android.content.Context;

public class App extends Application {

    public static Context context;

    public void onCreate() {
        super.onCreate();
        try {
            context=getApplicationContext();
        } catch (Exception e) {
            HandelErrorTools.getInstance().setHandelError(e);
        }
    }

}