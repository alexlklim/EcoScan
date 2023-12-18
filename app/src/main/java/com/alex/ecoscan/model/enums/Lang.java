package com.alex.ecoscan.model.enums;

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

    public String getCodeByLanguage(String language){
        for (Lang lang: Lang.values()){
            if (language.equalsIgnoreCase(lang.getLanguage())){
                return code;
            }
        }
        return null;
    }
}
