package com.wpi.walkermagnet_inspection.data.model;

/**
 * Created by abhishek on 12/27/2016.
 */

public class Configuration {

    /**
     */
    public static final String TABLE = "configurations";

    /**
     * Table Columns names
     *
     */
    public static final String KEY_CONFIG_ID = "user_id";
    public static final String KEY_MAGNET_ID = "magnet_id";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_ADDED_AT = "added_at";
    public static final String KEY_LAST_MODIFIED = "last_modified";

    /**
     * Defining Class Attributes
     *
     */
    private long mConfigID;
    private String mMagentId;
    private String mAddedDate;
    private String mLastModified;
    private Boolean mUserId;


    /**
     * Constructor
     *
     */
    public Configuration() {
    }
}
