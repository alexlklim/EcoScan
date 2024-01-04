package com.alex.ecoscan.model.utiles;

public class Data {

    // for Filter Configuration
    public static final String
            IS_ALLOW_NON_UNIQUE_CODE = "isAllowNonUniqueCode",
            IS_CHECK_LENGTH = "isCheckLength",
            LENGTH = "length", LENGTH_MAX = "lengthMAX", LENGTH_MIN = "lengthMIN",
            IS_ADVANCED_FILTER = "isAdvancedFilter",
            PREFIX = "prefix", SUFFIX = "suffix", ENDING = "ending", LABELS = "labels";


    // for Admin Configuration
    public static final String
            IS_ALLOW_EDIT_CODE = "isAllowEditCode", // edit during scanning (ScanActivity)
            IS_ALLOW_EDIT_ORDER = "isAllowEditOrder", // edit orders (only delete Code) (OrderActivity)
            IS_ADD_GPS = "isAddGPS", // add or not GPS (ScanActivity)
            IS_ENABLE_LOGIN = "isEnableLogin"; // add or not GPS (ScanActivity)

    // for Server Configuration
    public static final String
            IS_SENT_DATA = "isSendData", // it can be active only if serverConfigured = true
            IS_AUTO_SYNCH = "isAutoSynch",
            SERVER_ADDRESS = "serverAddress",
            IS_SERVER_CONFIGURED = "isServerConfigured"; // it is true if checkConnection ends with success


    // for Client configuration
    public static final String
            IDENTIFIER = "identifier",
            LANG = "lang",
            LOGIN = "login", PW = "pw";

    // for DataManagement Configuration
    public static final String
            IS_HIDE_SYNCH_DATA = "isHideSynchData";
}
