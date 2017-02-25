package me.ipcreator.recyclerviewtest;

import android.app.Application;
import android.content.Context;

/**
 * Created by user on 2/25/2017.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
       context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
