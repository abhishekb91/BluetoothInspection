package com.wpi.walkermagnet_inspection.data.repo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.wpi.walkermagnet_inspection.data.DatabaseManager;
import com.wpi.walkermagnet_inspection.data.model.Configuration;
import com.wpi.walkermagnet_inspection.data.model.Parameters;
import com.wpi.walkermagnet_inspection.data.model.Magnet;
import java.util.ArrayList;

/**
 * Created by abhishek on 2/1/2017.
 */

public class ParameterRepo {

    /**
     * Class name for Logging
     */
    private static String TAG = ParameterRepo.class.getSimpleName().toString();

    /**
     * Table creation query
     *
     * @return create table query
     */
    public static String createTable() {
        String query = "CREATE TABLE " + Parameters.TABLE + "("
                + Parameters.KEY_PARAMETER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Parameters.KEY_PARAMETER_TITLE + " TEXT NOT NULL,"
                + Magnet.KEY_IS_DELETE + " INTEGER DEFAULT 0 ); ";

        return query;
    }

    /**
     * This function returns query for dummy parameter data
     *
     * @return insert query for parameter table
     */
    public static String sampleData() {
        String query = "INSERT INTO " + Parameters.TABLE + " (`" + Parameters.KEY_PARAMETER_ID + "`, `" + Parameters.KEY_PARAMETER_TITLE + "`) VALUES " +
                "(1,'Voltage'), (2,'Current'), (3,'Duty Cycle'), (4,'Max Current'), (5,'Min Current');";

        return query;
    }

    /**
     * This function is used to get list of all magnets in the application
     *
     * @return List of all magnets
     */
    public ArrayList parameters() {

        ArrayList parameters = new ArrayList();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = " SELECT p.* " +
                " FROM " + Parameters.TABLE + " as p;";

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    //Inserting data into list
                    parameters.add(new Parameters(
                            cursor.getLong(cursor.getColumnIndex(Parameters.KEY_PARAMETER_ID)),
                            cursor.getString(cursor.getColumnIndex(Parameters.KEY_PARAMETER_TITLE))
                            )
                    );

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while getting cuisine information");
        } finally {
            cursor.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        cursor.close();

        return parameters;
    }

    /**
     * This function is used to get all the parameters selected for a configuration
     *
     * @param config_id is the id of the magnet configuration
     * @return
     */
    public ArrayList configuration_parameter(long config_id) {
        ArrayList config_parameter = new ArrayList();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = " SELECT cp.* " +
                " FROM " + Configuration.TABLE_CONFIG + " as cp" +
                " WHERE cp." + Configuration.KEY_CONFIG_ID + " = '" + config_id + "';";

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    //Inserting data into list
                    config_parameter.add(cursor.getLong(cursor.getColumnIndex(Configuration.KEY_PARAMETER_ID)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while getting configuration parameter information");
        } finally {
            cursor.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        cursor.close();

        return config_parameter;
    }

}
