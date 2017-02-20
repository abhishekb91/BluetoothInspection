package com.wpi.walkermagnet_inspection.data.repo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wpi.walkermagnet_inspection.data.DatabaseManager;
import com.wpi.walkermagnet_inspection.data.model.Magnet;
import com.wpi.walkermagnet_inspection.data.model.User;

import java.util.ArrayList;

import static android.R.attr.password;


/**
 * Created by abhishek on 12/27/2016.
 */

public class MagnetRepo {

    /**
     * Class name for Logging
     */
    private static String TAG = MagnetRepo.class.getSimpleName().toString();

    /**
     * Table creation query
     *
     * @return create table query
     */
    public static String createTable() {
        String query = "CREATE TABLE " + Magnet.TABLE + "("
                + Magnet.KEY_MAGNET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Magnet.KEY_USER_ID + " INTEGER NOT NULL,"
                + Magnet.KEY_NAME + " TEXT NOT NULL,"
                + Magnet.KEY_CONFIG_ID + " INTEGER NOT NULL,"
                + Magnet.KEY_IS_DELETE + " INTEGER DEFAULT 0 ); ";

        Log.d("Query", query);

        return query;
    }

    /**
     * This function returns query for dummy magnet data
     *
     * @return magnet insert query
     */
    public static String sampleData() {
        String query = "INSERT INTO " + Magnet.TABLE + " (`" + Magnet.KEY_MAGNET_ID + "`,`" + Magnet.KEY_USER_ID + "`, `" + Magnet.KEY_NAME + "`, `" + Magnet.KEY_CONFIG_ID + "`) VALUES " +
                "(1,1,'Magnet Controller One',1), (2,1,'Magnet Controller Two',2), (3,1,'Magnet Controller Three',3), (4,1,'Magnet Controller Four',4), (5,1,'Magnet Controller Five',5);";

        return query;
    }

    /**
     * This function is used to get list of all magnets in the application
     *
     * @param userId User's Id
     * @return List of all magnets
     */
    public ArrayList magnets(long userId) {

        ArrayList magnets = new ArrayList();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = " SELECT m.* " +
                " FROM " + Magnet.TABLE + " as m" +
                " WHERE m." + Magnet.KEY_USER_ID + "=" + userId;

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    //Inserting data into list
                    magnets.add(new Magnet(
                                    cursor.getLong(cursor.getColumnIndex(Magnet.KEY_MAGNET_ID)),
                                    cursor.getString(cursor.getColumnIndex(Magnet.KEY_NAME))
                            )
                    );

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while getting magnets information");
        } finally {
            cursor.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        cursor.close();

        return magnets;
    }

    /**
     * Returns Magnet information
     *
     * @param id id of the magnet controller
     * @return magnet information
     */
    public Magnet magnet(long id) {

        //Creating Magnet Object
        Magnet magnet = new Magnet(id);

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = " SELECT m.* " +
                " FROM " + Magnet.TABLE + " as m" +
                " WHERE m." + Magnet.KEY_MAGNET_ID + " = '" + id + "';";

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    magnet.setMagnetName(cursor.getString(cursor.getColumnIndex(Magnet.KEY_NAME)));
                    magnet.setConfigurationId(cursor.getLong(cursor.getColumnIndex(Magnet.KEY_CONFIG_ID)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while getting user information");
        } finally {
            cursor.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        cursor.close();
        return magnet;
    }

    /**
     * Removes the Magnet controller from the User's List
     *
     * @param magnetId controller id
     * @param userId   Id of the User
     * @return true if successfully removed else return false
     */
    public Boolean remove(long magnetId, long userId) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String deleteQuery = "DELETE FROM " + Magnet.TABLE +
                " WHERE " + Magnet.KEY_USER_ID + "=" + userId +
                " AND " + Magnet.KEY_MAGNET_ID + "=" + magnetId;

        db.execSQL(deleteQuery);

        DatabaseManager.getInstance().closeDatabase();

        return true;
    }
}
