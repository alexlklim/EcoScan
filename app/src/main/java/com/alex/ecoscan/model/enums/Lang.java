package com.alex.ecoscan.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum Lang {
    EN("English", "en"),
    PL("Polish", "pl"),
    UK("Ukrainian", "uk");

    private final String language;
    private final String code;

    Lang(String language, String code){
        this.language = language;
        this.code = code;
    }

    public String getLanguage(){
        return  language;
    }
    public String getCode(){
        return code;
    }

    public static String getCodeByLanguage(String language) {
        for (Lang lang : Lang.values()) {
            if (lang.getLanguage().equalsIgnoreCase(language)) {
                return lang.getCode();
            }
        }
        return null; // Return null if language is not found
    }

    public static String getLanguageByCode(String code) {
        for (Lang lang : Lang.values()) {
            if (lang.getCode().equalsIgnoreCase(code)) {
                return lang.getLanguage();
            }
        }
        return null; // Return null if code is not found
    }


    public static List<String> getListLang() {
        List<String> stringValues = new ArrayList<>();
        for (Lang lang : Lang.values()) {
            stringValues.add(lang.getLanguage());
        }
        return stringValues;
    }
}
