package com.wpi.walkermagnet_inspection.data.model;

/**
 * Created by abhis on 2/1/2017.
 */

public class ConfigParameters {
    /**
     */
    public static final String TABLE = "parameters";

    /**
     * Table Columns names
     */
    public static final String KEY_PARAMETER_ID = "parameter_id";
    public static final String KEY_PARAMETER_TITLE = "title";
    public static final String KEY_IS_DELETE = "is_delete";

    /**
     * Defining Class Attributes
     */
    private long mParameterID;
    private String mTitle;
    private Boolean mIsDelete;


    /**
     * Constructor
     */
    public ConfigParameters(long id, String name) {
        mParameterID = id;
        mTitle = name;
    }

    /**
     * Getters and Setters methods
     */

    public long getId() {
        return mParameterID;
    }

    public void setId(long id) {
        mParameterID = id;
        return;
    }

    public String getParameterTitle() {
        return mTitle;
    }

    public void setParameterTitle(String title) {
        mTitle = title;
        return;
    }
}
