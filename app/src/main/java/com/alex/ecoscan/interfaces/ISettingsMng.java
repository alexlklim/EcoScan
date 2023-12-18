package com.alex.ecoscan.interfaces;

import com.alex.ecoscan.model.enums.Label;
import com.alex.ecoscan.model.enums.Lang;

import java.io.StringReader;
import java.util.List;
import java.util.Set;

public interface ISettingsMng {
    void setIsAllowNonUniqueCode(boolean bool);
    void setIsCheckLength(boolean isCheckLength, int length, int min, int max);
    void setIsAdvancedFilter(boolean isAdvancedFilter,
                             String prefix, String suffix, String ending, Set<String> labels);

    void setIsAllowEditCode(boolean isAllowEditCode);
    void setIsAllowEditOrders(boolean isAllowEditOrders);
    void setIsAddGPSToCode(boolean isAddGPSToCode);
    void setIsEnableLogin(boolean isEnableLogin);

    void setIsSendData(boolean isSendData);
    void setIsAutoSynch(boolean isAutoSynch);
    void setServerAddress(String serverAddress);

    void setClientConfig(int identifier, String lang);
    void setIdentifier(int identifier);
    void setNewLogin(String login);
    void setNewPw(String pw);

    void setIsHideSynchData(boolean isHideSynchData);



    void setDefaultSettings();






}
