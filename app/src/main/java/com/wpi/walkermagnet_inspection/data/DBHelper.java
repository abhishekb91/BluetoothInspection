package com.wpi.walkermagnet_inspection.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wpi.walkermagnet_inspection.app.App;
import com.wpi.walkermagnet_inspection.data.model.Magnet;
import com.wpi.walkermagnet_inspection.data.model.User;
import com.wpi.walkermagnet_inspection.data.repo.ConfigurationRepo;
import com.wpi.walkermagnet_inspection.data.repo.ParameterRepo;
import com.wpi.walkermagnet_inspection.data.repo.MagnetRepo;
import com.wpi.walkermagnet_inspection.data.repo.UserRepo;

/**
 * Created by abhishek on 12/27/2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "bluetoothInspection";

    private static final String TAG = DBHelper.class.getSimpleName().toString();

    public DBHelper() {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        db.execSQL(UserRepo.createTable());
        db.execSQL(MagnetRepo.createTable());
        db.execSQL(ParameterRepo.createTable());
        db.execSQL(ConfigurationRepo.createTable());
        db.execSQL(ConfigurationRepo.createTableConfigParameter());

        //Inserting Data into table
        db.execSQL(MagnetRepo.sampleData());
        db.execSQL(ParameterRepo.sampleData());
        db.execSQL(ConfigurationRepo.sampleData());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        // Drop table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Magnet.TABLE);

        onCreate(db);
    }
}
