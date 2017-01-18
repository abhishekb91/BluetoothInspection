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
    public static final String KEY_EMAIL = "email";
    public static final String KEY_IS_DELETE = "is_delete";

    /**
     * Defining Class Attributes
     *
     */
    private long mUserID;
    private String mUserName;
    private String mUserEmail;
    private Boolean mIsDelete ;


    /**
     * Constructor
     *
     */
    public User() {
    }

    /**
     * Getter and setter functions
     */
    public long getId() {
        return mUserID;
    }

    public void setId(long id) {
        mUserID = id;
        return;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String name) {
        mUserName = name;
        return;
    }

    public String getUserEmail() {
        return mUserEmail;
    }

    public void setmUserEmail(String email){
        mUserEmail = email;
        return;
    }
}
