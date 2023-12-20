package com.alex.ecoscan.interfaces;

import com.alex.ecoscan.model.enums.Label;
import com.alex.ecoscan.model.enums.Lang;

import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface ISettingsMng {
    void setIsAllowNonUniqueCode(boolean bool);
    void setIsCheckLength(boolean isCheckLength);
    void setBlockLength(int length, int min, int max);
    void setIsAdvancedFilter(boolean isAdvancedFilter);
    void setBlockAdvancedFilter(String prefix, String suffix, String ending, HashSet<String> labels);

    void setIsAllowEditCode(boolean isAllowEditCode);
    void setIsAllowEditOrders(boolean isAllowEditOrders);
    void setIsAddGPSToCode(boolean isAddGPSToCode);
    void setIsEnableLogin(boolean isEnableLogin);

    void setIsSendData(boolean isSendData);
    void setIsAutoSynch(boolean isAutoSynch);
    void setServerAddress(String serverAddress);
    void setIsServerConfigured(boolean isServerConfigured);


    void setLang( String lang);
    void setIdentifier(int identifier);
    void setNewLogin(String login);
    void setNewPw(String pw);

    void setIsHideSynchData(boolean isHideSynchData);



    void setDefaultSettings();






}
