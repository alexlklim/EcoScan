package com.alex.ecoscan.model.utiles;

public class Db {
    public static final String PREF_NAME = "EcoScanSettings";

    private static final String LANG = "lang";

    private static final String LOGIN = "login", PW = "pw";

    // for Filter Configuration
    private static final String
            IS_ALLOW_NON_UNIQUE_CODE = "isAllowNonUniqueCode",
            IS_CHECK_LENGTH = "isCheckCodeLength",
            LENGTH = "length",
            LENGTH_MAX = "lengthMAX",
            LENGTH_MIN = "lengthMIN",
            IS_ADVANCED_FILTER = "isAdvancedFilter",
            PREFIX = "prefix",
            SUFFIX = "suffix",
            ENDING = "ending",
            LABELS = "labels";

    // for Server Configuration
    private static final String
            ID = "identifier",
            IS_SERVER_CONFIGURED = "isServerConfigured",
            IS_AUTO_SYNCH = "isAutoSynch",
            SERVER_ADDRESS = "serverAddress";

    // for Admin Configuration
    private static final String

            IS_ALLOW_EDIT_CODE = "isAllowEditCode", // edit during scanning (ScanActivity)
            IS_ALLOW_EDIT_ORDER = "isAllowEditOrder", // edit orders (only delete Code) (OrderActivity)
            IS_ADD_GPS = "isAddGPS"; // add or not GPS (ScanActivity)

}
