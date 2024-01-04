package com.alex.ecoscan.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.alex.ecoscan.interfaces.ISettingsMng;
import com.alex.ecoscan.model.enums.Lang;
import com.alex.ecoscan.model.utiles.Data;
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
        pref.edit().putBoolean(Data.IS_ALLOW_NON_UNIQUE_CODE, isAllowNonUniqueCode).apply();


    }

    @Override
    public void setIsCheckLength(boolean isCheckLength) {
        pref.edit().putBoolean(Data.IS_CHECK_LENGTH, isCheckLength).apply();
    }

    @Override
    public void setBlockLength(int length, int min, int max) {
        pref.edit().putInt(Data.LENGTH, length)
                .putInt(Data.LENGTH_MIN, min).putInt(Data.LENGTH_MAX, max)
                .apply();
    }

    @Override
    public void setIsAdvancedFilter(boolean isAdvancedFilter) {
        pref.edit().putBoolean(Data.IS_ADVANCED_FILTER, isAdvancedFilter).apply();
    }
    @Override
    public void setBlockAdvancedFilter(String prefix, String suffix, String ending, HashSet<String> labels) {
        pref.edit().putString(Data.PREFIX, prefix)
                .putString(Data.SUFFIX, suffix)
                .putString(Data.ENDING, ending)
                .putStringSet(Data.LABELS, labels)
                .apply();

    }

    @Override
    public void setIsAllowEditCode(boolean isAllowEditCode) {
        pref.edit().putBoolean(Data.IS_ALLOW_EDIT_CODE, isAllowEditCode).apply();

    }

    @Override
    public void setIsAllowEditOrders(boolean isAllowEditOrders) {
        pref.edit().putBoolean(Data.IS_ALLOW_EDIT_ORDER, isAllowEditOrders).apply();

    }

    @Override
    public void setIsAddGPSToCode(boolean isAddGPSToCode) {
        pref.edit().putBoolean(Data.IS_ADD_GPS, isAddGPSToCode).apply();

    }

    @Override
    public void setIsEnableLogin(boolean isEnableLogin) {
        pref.edit().putBoolean(Data.IS_ENABLE_LOGIN, isEnableLogin).apply();

    }

    @Override
    public void setIsSendData(boolean isSendData) {
        pref.edit().putBoolean(Data.IS_SENT_DATA, isSendData).apply();

    }

    @Override
    public void setIsAutoSynch(boolean isAutoSynch) {
        pref.edit().putBoolean(Data.IS_AUTO_SYNCH, isAutoSynch).apply();

    }

    @Override
    public void setServerAddress(String serverAddress) {
        pref.edit().putString(Data.SERVER_ADDRESS, serverAddress).apply();

    }
    @Override
    public void setIsServerConfigured(boolean isServerConfigured) {
        pref.edit().putBoolean(Data.IS_SERVER_CONFIGURED, isServerConfigured).apply();

    }

    @Override
    public void setLang(String lang) {
        pref.edit().putString(Data.LANG, lang).apply();
    }
    @Override
    public void setIdentifier(int identifier) {
        pref.edit().putInt(Data.IDENTIFIER, identifier).apply();}


    @Override
    public void setNewLogin(String login) {
        pref.edit().putString(Data.LOGIN, login).apply();

    }

    @Override
    public void setNewPw(String pw) {
        pref.edit().putString(Data.PW, pw).apply();

    }

    @Override
    public void setIsHideSynchData(boolean isHideSynchData) {
        pref.edit().putBoolean(Data.IS_HIDE_SYNCH_DATA, isHideSynchData).apply();

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
        setLang(Lang.EN.getCode());
        setIdentifier(RandomMng.getRandomIdentifier());
        setNewLogin("admin");
        setNewPw("admin");

        // data config
        setIsHideSynchData(false);
    }





    // getters
    public boolean isAllowNonUniqueCode() {
        return pref.getBoolean(Data.IS_ALLOW_NON_UNIQUE_CODE, true);
    }
    public boolean isCheckLength() {
        return pref.getBoolean(Data.IS_CHECK_LENGTH, false);
    }
    public int getLength() {
        return pref.getInt(Data.LENGTH, 0);
    }
    public int getLengthMAX() {
        return pref.getInt(Data.LENGTH_MAX, 0);
    }
    public int getLengthMIN() {
        return pref.getInt(Data.LENGTH_MIN, 0);
    }


    public boolean isAdvancedFilter() {
        return pref.getBoolean(Data.IS_ADVANCED_FILTER, false);
    }
    public String getPrefix() {
        return pref.getString(Data.PREFIX, "");
    }

    public String getSuffix() {
        return pref.getString(Data.SUFFIX, "");
    }

    public String getEnding() {
        return pref.getString(Data.ENDING, "");
    }
    public Set<String> getLabels() {
        return pref.getStringSet(Data.LABELS, new HashSet<>());
    }



    public boolean isAllowEditOrders() {
        return pref.getBoolean(Data.IS_ALLOW_EDIT_ORDER, false);
    }
    public boolean isAddGPS() {
        return pref.getBoolean(Data.IS_ADD_GPS, true);
    }
    public boolean isEnableLogin() {
        return pref.getBoolean(Data.IS_ENABLE_LOGIN, false);
    }



    public boolean isSentData() {
        return pref.getBoolean(Data.IS_SENT_DATA, false);
    }

    public boolean isAutoSynch() {
        return pref.getBoolean(Data.IS_AUTO_SYNCH, false);
    }
    public String getServerAddress() {
        return pref.getString(Util.URL, Util.URL);
    }
    public boolean isServerConfigured() {
        return pref.getBoolean(Data.IS_SERVER_CONFIGURED, false);
    }



    public int getIdentifier() {
        return pref.getInt(Data.IDENTIFIER, 0);
    }
    public String getLang() {
        return pref.getString(Data.LANG, Lang.EN.getCode());
    }


    public String getLogin() {
        return pref.getString(Data.LOGIN, "admin");
    }

    public String getPassword() {
        return pref.getString(Data.PW, "admin");
    }


    public boolean isHideSynchData() {
        return pref.getBoolean(Data.IS_HIDE_SYNCH_DATA, false);
    }


}
