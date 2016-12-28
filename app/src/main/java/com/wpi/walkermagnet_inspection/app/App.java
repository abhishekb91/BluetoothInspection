package com.wpi.walkermagnet_inspection.app;

import android.app.Application;
import android.content.Context;

import com.wpi.walkermagnet_inspection.data.DBHelper;
import com.wpi.walkermagnet_inspection.data.DatabaseManager;

/**
 * Created by abhishek on 12/27/2016.
 */

public class App extends Application {
    private static Context context;
    private static DBHelper dbHelper;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DBHelper();
        DatabaseManager.initializeInstance(dbHelper);
    }

    public static Context getContext(){
        return context;
    }
}
