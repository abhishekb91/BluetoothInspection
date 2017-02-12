package com.wpi.walkermagnet_inspection.data.repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.wpi.walkermagnet_inspection.data.DatabaseManager;
import com.wpi.walkermagnet_inspection.data.model.Configuration;
import com.wpi.walkermagnet_inspection.data.model.Magnet;
import com.wpi.walkermagnet_inspection.data.model.User;

import java.util.ArrayList;

/**
 * Created by abhishek on 12/27/2016.
 */

public class ConfigurationRepo {
    /**
     * Class name for Logging
     */
    private static String TAG = ConfigurationRepo.class.getSimpleName().toString();

    /**
     * Table creation query
     *
     * @return create table query
     */
    public static String createTable() {
        String query = "CREATE TABLE " + Configuration.TABLE + "("
                + Configuration.KEY_CONFIG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Configuration.KEY_USER_ID + " INTEGER,"
                + Configuration.KEY_ADDED_AT + " DATE DEFAULT CURRENT_TIMESTAMP,"
                + Configuration.KEY_LAST_MODIFIED + " DATE); ";

        return query;
    }

    /**
     * This function returns query for dummy configuration data
     *
     * @return insert query for configuration
     */
    public static String sampleData() {
        String query = "INSERT INTO " + Configuration.TABLE + " (`" + Configuration.KEY_CONFIG_ID + "`, `" + Configuration.KEY_USER_ID + "`) VALUES " +
                "(1,1), (2,1), (3,1), (4,1), (5,1);";

        return query;
    }

    /**
     * Table creation query
     *
     * @return create table query
     */
    public static String createTableConfigParameter() {
        String query = "CREATE TABLE " + Configuration.TABLE_CONFIG + "("
                + Configuration.KEY_CONFIG_ID + " INTEGER,"
                + Configuration.KEY_PARAMETER_ID + " INTEGER,"
                + Configuration.KEY_ADDED_AT + " DATE DEFAULT CURRENT_TIMESTAMP); ";

        return query;
    }

    /**
     * This function is used save the Magnet configuration in the system
     *
     * @param configurationId  id of the magnet
     * @param configParameters config parameters
     * @return true if saved successfully else false
     */
    public Boolean saveMagnetConfiguration(long configurationId, ArrayList configParameters) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.beginTransaction();

        //Delete existing data from the table
        String deleteQuery = "DELETE FROM `" + Configuration.TABLE_CONFIG + "` " +
                " WHERE `" + Configuration.KEY_CONFIG_ID + "`='" + configurationId + "';";

        db.execSQL(deleteQuery);

        //Add configuration into the table
        for (int i = 0; i < configParameters.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(Configuration.KEY_CONFIG_ID, configurationId);
            values.put(Configuration.KEY_PARAMETER_ID, (long) configParameters.get(i));

            // Inserting Row
            db.insert(Configuration.TABLE_CONFIG, null, values);
        }
        db.setTransactionSuccessful();
        db.endTransaction();

        DatabaseManager.getInstance().closeDatabase();

        return true;
    }
}
