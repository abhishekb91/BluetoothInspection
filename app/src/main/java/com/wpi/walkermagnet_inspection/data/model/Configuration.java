package com.wpi.walkermagnet_inspection.data.model;

/**
 * Created by abhishek on 12/27/2016.
 */

public class Configuration {

    /**
     */
    public static final String TABLE = "configurations";
    public static final String TABLE_CONFIG = "configuration_parameter";

    /**
     * Table Columns names
     *
     */
    public static final String KEY_CONFIG_ID = "configuration_id";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_ADDED_AT = "added_at";
    public static final String KEY_LAST_MODIFIED = "last_modified";
    public static final String KEY_PARAMETER_ID = "parameter_id";


    /**
     * Defining Class Attributes
     *
     */
    private long mConfigID;
    private long mUserId;
    private String mAddedDate;
    private String mLastModified;


    /**
     * Constructor
     *
     */
    public Configuration() {
    }
}
