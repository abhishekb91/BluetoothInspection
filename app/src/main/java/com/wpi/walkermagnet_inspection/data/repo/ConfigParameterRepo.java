package com.wpi.walkermagnet_inspection.data.repo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wpi.walkermagnet_inspection.data.DatabaseManager;
import com.wpi.walkermagnet_inspection.data.model.ConfigParameters;
import com.wpi.walkermagnet_inspection.data.model.Magnet;

import java.util.ArrayList;

/**
 * Created by abhis on 2/1/2017.
 */

public class ConfigParameterRepo {
    /**
     * Class name for Logging
     */
    private static String TAG = ConfigParameterRepo.class.getSimpleName().toString();

    /**
     * Table creation query
     *
     * @return create table query
     */
    public static String createTable() {
        String query = "CREATE TABLE " + ConfigParameters.TABLE + "("
                + ConfigParameters.KEY_PARAMETER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ConfigParameters.KEY_PARAMETER_TITLE + " TEXT NOT NULL,"
                + Magnet.KEY_IS_DELETE + " INTEGER DEFAULT 0 ); ";

        return query;
    }

    /**
     * This function returns query for dummy magnet data
     *
     * @return
     */
    public static String sampleData() {
        String query = "INSERT INTO " + ConfigParameters.TABLE + " (`" + ConfigParameters.KEY_PARAMETER_ID + "`, `" + ConfigParameters.KEY_PARAMETER_TITLE + "`) VALUES " +
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
                " FROM " + ConfigParameters.TABLE + " as p;";

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    //Inserting data into list
                    parameters.add(new ConfigParameters(
                                    cursor.getLong(cursor.getColumnIndex(ConfigParameters.KEY_PARAMETER_ID)),
                                    cursor.getString(cursor.getColumnIndex(ConfigParameters.KEY_PARAMETER_TITLE))
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
}
