package com.wpi.walkermagnet_inspection.data.model;

/**
 * Created by abhishek on 12/27/2016.
 */

public class Magnet {
    /**
     */
    public static final String TABLE = "magnets";

    /**
     * Table Columns names
     *
     */
    public static final String KEY_MAGNET_ID = "magnet_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_IS_DELETE = "is_delete";

    /**
     * Defining Class Attributes
     *
     */
    private long mMagnetID;
    private String mMagnetName;
    private Boolean mIsDelete ;


    /**
     * Constructor
     *
     */
    public Magnet() {
    }
}
