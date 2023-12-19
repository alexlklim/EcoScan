package com.alex.ecoscan.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.alex.ecoscan.interfaces.ISettingsMng;
import com.alex.ecoscan.model.enums.Lang;
import com.alex.ecoscan.model.utiles.Db;
import com.alex.ecoscan.model.utiles.Util;

import java.util.HashSet;
import java.util.Set;

public class SettingsMng implements ISettingsMng {
    private final SharedPreferences pref;
    public static final String PREF_NAME = Util.APP_NAME;

    public SettingsMng(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    @Override
    public void setIsAllowNonUniqueCode(boolean isAllowNonUniqueCode) {
        pref.edit().putBoolean(Db.IS_ALLOW_NON_UNIQUE_CODE, isAllowNonUniqueCode).apply();


    }

    @Override
    public void setIsCheckLength(boolean isCheckLength) {
        pref.edit().putBoolean(Db.IS_CHECK_LENGTH, isCheckLength).apply();
    }

    @Override
    public void setBlockLength(int length, int min, int max) {
        pref.edit().putInt(Db.LENGTH, length)
                .putInt(Db.LENGTH_MIN, min).putInt(Db.LENGTH_MAX, max)
                .apply();
    }

    @Override
    public void setIsAdvancedFilter(boolean isAdvancedFilter) {
        pref.edit().putBoolean(Db.IS_ADVANCED_FILTER, isAdvancedFilter).apply();
    }
    @Override
    public void setBlockAdvancedFilter(String prefix, String suffix, String ending, HashSet<String> labels) {
        pref.edit().putString(Db.PREFIX, prefix)
                .putString(Db.SUFFIX, suffix)
                .putString(Db.ENDING, ending)
                .putStringSet(Db.LABELS, labels)
                .apply();

    }

    @Override
    public void setIsAllowEditCode(boolean isAllowEditCode) {
        pref.edit().putBoolean(Db.IS_ALLOW_EDIT_CODE, isAllowEditCode).apply();

    }

    @Override
    public void setIsAllowEditOrders(boolean isAllowEditOrders) {
        pref.edit().putBoolean(Db.IS_ALLOW_EDIT_ORDER, isAllowEditOrders).apply();

    }

    @Override
    public void setIsAddGPSToCode(boolean isAddGPSToCode) {
        pref.edit().putBoolean(Db.IS_ADD_GPS, isAddGPSToCode).apply();

    }

    @Override
    public void setIsEnableLogin(boolean isEnableLogin) {
        pref.edit().putBoolean(Db.IS_ENABLE_LOGIN, isEnableLogin).apply();

    }

    @Override
    public void setIsSendData(boolean isSendData) {
        pref.edit().putBoolean(Db.IS_SENT_DATA, isSendData).apply();

    }

    @Override
    public void setIsAutoSynch(boolean isAutoSynch) {
        pref.edit().putBoolean(Db.IS_AUTO_SYNCH, isAutoSynch).apply();

    }

    @Override
    public void setServerAddress(String serverAddress) {
        pref.edit().putString(Db.SERVER_ADDRESS, serverAddress).apply();

    }

    @Override
    public void setClientConfig(int identifier, String lang) {
        pref.edit().putInt(Db.IDENTIFIER, identifier)
                .putString(Db.LANG, lang)
                .apply();
    }
    @Override
    public void setIdentifier(int identifier) {
        pref.edit().putInt(Db.IDENTIFIER, identifier).apply();}


    @Override
    public void setNewLogin(String login) {
        pref.edit().putString(Db.LOGIN, login).apply();

    }

    @Override
    public void setNewPw(String pw) {
        pref.edit().putString(Db.PW, pw).apply();

    }

    @Override
    public void setIsHideSynchData(boolean isHideSynchData) {
        pref.edit().putBoolean(Db.IS_HIDE_SYNCH_DATA, isHideSynchData).apply();

    }




    @Override
    public void setDefaultSettings() {
        // filter config
        setIsAllowNonUniqueCode(false);
        setIsCheckLength(false);
        setBlockLength(0, 0, 0);
        setIsAdvancedFilter(false);
        setBlockAdvancedFilter("", "", "", new HashSet<String>());

        // admin config
        setIsAllowEditCode(false);
        setIsAllowEditOrders(false);
        setIsAddGPSToCode(true);
        setIsEnableLogin(false);

        // server config
        setIsSendData(false);
        setIsAutoSynch(false);
        setServerAddress(Util.URL);

        // client config
        setClientConfig(RandomMng.getRandomIdentifier(), Lang.EN.getCode());
        setNewLogin("admin");
        setNewPw("admin");

        // data config
        setIsHideSynchData(false);
    }





    // getters
    public boolean isAllowNonUniqueCode() {
        return pref.getBoolean(Db.IS_ALLOW_NON_UNIQUE_CODE, true);
    }
    public boolean isCheckLength() {
        return pref.getBoolean(Db.IS_CHECK_LENGTH, false);
    }
    public int getLength() {
        return pref.getInt(Db.LENGTH, 0);
    }
    public int getLengthMAX() {
        return pref.getInt(Db.LENGTH_MAX, 0);
    }
    public int getLengthMIN() {
        return pref.getInt(Db.LENGTH_MIN, 0);
    }


    public boolean isAdvancedFilter() {
        return pref.getBoolean(Db.IS_ADVANCED_FILTER, false);
    }
    public String getPrefix() {
        return pref.getString(Db.PREFIX, "");
    }

    public String getSuffix() {
        return pref.getString(Db.SUFFIX, "");
    }

    public String getEnding() {
        return pref.getString(Db.ENDING, "");
    }
    public Set<String> getLabels() {
        return pref.getStringSet(Db.LABELS, new HashSet<>());
    }


    public boolean isAllowEditCode() {
        return pref.getBoolean(Db.IS_ALLOW_EDIT_CODE, false);
    }
    public boolean isAllowEditOrders() {
        return pref.getBoolean(Db.IS_ALLOW_EDIT_ORDER, false);
    }
    public boolean isAddGPS() {
        return pref.getBoolean(Db.IS_ADD_GPS, true);
    }
    public boolean isEnableLogin() {
        return pref.getBoolean(Db.IS_ENABLE_LOGIN, false);
    }



    public boolean isSentData() {
        return pref.getBoolean(Db.IS_SENT_DATA, false);
    }

    public boolean isAutoSynch() {
        return pref.getBoolean(Db.IS_AUTO_SYNCH, false);
    }
    public String getServerAddress() {
        return pref.getString(Util.URL, Util.URL);
    }
    public boolean isServerConfigured() {
        return pref.getBoolean(Db.IS_SERVER_CONFIGURED, false);
    }



    public int getIdentifier() {
        return pref.getInt(Db.IDENTIFIER, 0);
    }
    public String getLang() {
        return pref.getString(Db.LANG, Lang.EN.getCode());
    }


    public String getLogin() {
        return pref.getString(Db.LOGIN, "admin");
    }

    public String getPassword() {
        return pref.getString(Db.PW, "admin");
    }


    public boolean isHideSynchData() {
        return pref.getBoolean(Db.IS_HIDE_SYNCH_DATA, false);
    }


}
