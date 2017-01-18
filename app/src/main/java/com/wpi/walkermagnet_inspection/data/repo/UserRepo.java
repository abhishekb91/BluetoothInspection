package com.wpi.walkermagnet_inspection.data.repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.wpi.walkermagnet_inspection.data.DatabaseManager;
import com.wpi.walkermagnet_inspection.data.model.User;

/**
 * Created by abhishek on 12/27/2016.
 */

public class UserRepo {
    /**
     * Class name for Logging
     */
    private static String TAG = UserRepo.class.getSimpleName().toString();

    /**
     * Empty Constructor
     */
    public UserRepo() {
    }

    /**
     * Table creation query
     *
     * @return create table query
     */
    public static String createTable() {
        String query = "CREATE TABLE " + User.TABLE + "("
                + User.KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + User.KEY_NAME + " TEXT NOT NULL,"
                + User.KEY_EMAIL + " TEXT NOT NULL,"
                + User.KEY_IS_DELETE + " INTEGER DEFAULT 0 ); ";

        return query;
    }

    /**
     * Function to insert user info to the application
     *
     * @param user User object containing user name and email
     * @return userId after he has been registered
     */
    public long insertUserInfo(User user) {
        long userId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(User.KEY_NAME, user.getUserName());
        values.put(User.KEY_EMAIL, user.getId());

        // Inserting Row
        userId = db.insert(User.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return userId;
    }
}
