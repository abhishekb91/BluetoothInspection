package com.wpi.walkermagnet_inspection.data.model;

/**
 * Created by abhishek on 12/27/2016.
 */

public class User {
    /**
     */
    public static final String TABLE = "users";

    /**
     * Table Columns names
     *
     */
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_IS_DELETE = "is_delete";

    /**
     * Defining Class Attributes
     *
     */
    private long mUserID;
    private String mUserName;
    private Boolean mIsDelete ;


    /**
     * Constructor
     *
     */
    public User() {
    }
}
