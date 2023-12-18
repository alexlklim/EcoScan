package com.alex.ecoscan.managers;

import android.util.Log;

import com.alex.ecoscan.model.Code;

public class FilterMng {

//    public static Code filteringData(Code code, SettingsMng sm) {
//        if (!sm.isNonUniqueCodeAllow() && codeList.stream().anyMatch(c -> c.getCode().equals(code.getCode()))) {
//            return null;
//        }
//
//        if (sm.isCheckCodeLength()) {
//            int length = code.getCode().length();
//            if (sm.getCodeLength() != 0 && sm.getCodeLength() != length) return null;
//            if (sm.getCodeLengthMIN() != 0 && sm.getCodeLengthMIN() < length) return null;
//            if (sm.getCodeLengthMAX() != 0 && sm.getCodeLengthMAX() > length) return null;
//        }
//
//        if (sm.isDoAdvancedFilter()) {
//            String cod = code.getCode();
//            if (!sm.getCodePrefix().equals("") && !cod.startsWith(sm.getCodePrefix())) return null;
//            if (!sm.getCodeSuffix().equals("") && !cod.contains(sm.getCodeSuffix())) return null;
//            if (!sm.getCodeEnding().equals("") && !cod.endsWith(sm.getCodeEnding())) return null;
//            if (!sm.getCodeLabelType().equals("NONE") && !code.getLabelType().equals(sm.getCodeLabelType())) return null;
//        }
//        return code;
//    }
}
