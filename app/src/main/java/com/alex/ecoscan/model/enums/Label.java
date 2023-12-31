package com.alex.ecoscan.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum Label {
    NONE("NONE"), CODE39("CODE39"), CODABAR("CODABAR"), CODE128("CODE128"), D2OF5("D2OF5"),
    IATA2OF5("IATA2OF5"), I2OF5("I2OF5"), CODE93("CODE93"), UPCA("UPCA"), UPCE0("UPCE0"),
    UPCE1("UPCE1"), EAN8("EAN8"), EAN13("EAN13"), MSI("MSI"), EAN128("EAN128"), TRIOPTIC39("TRIOPTIC39"),
    BOOKLAND("BOOKLAND"), COUPON("COUPON"), DATABAR_COUPON("DATABAR-COUPON"), ISBT128("ISBT128"), CODE32("CODE32"),
    PDF417("PDF417"), MICROPDF("MICROPDF"), TLC39("TLC39"), CODE11("CODE11"), MAXICODE("MAXICODE"), DATAMATRIX("DATAMATRIX"),
    QRCODE("QRCODE"), GS1_DATABAR("GS1-DATABAR"), GS1_DATABAR_LIM("GS1-DATABAR-LIM"), GS1_DATABAR_EXP("GS1-DATABAR-EXP"), USPOSTNET("USPOSTNET"),
    USPLANET("USPLANET"), UKPOSTAL("UKPOSTAL"), JAPPOSTAL("JAPPOSTAL"), AUSPOSTAL("AUSPOSTAL"), DUTCHPOSTAL("DUTCHPOSTAL"),
    FINNISHPOSTAL_4S("FINNISHPOSTAL-4S"), CANPOSTAL("CANPOSTAL"), CHINESE_2OF5("CHINESE-2OF5"), AZTEC("AZTEC"), MICROQR("MICROQR"), US4STATE("US4STATE"),
    US4STATE_FICS("US4STATE-FICS"), COMPOSITE_AB("COMPOSITE-AB"), COMPOSITE_C("COMPOSITE-C"), WEBCODE("WEBCODE"), SIGNATURE("SIGNATURE"), KOREAN_3OF5("KOREAN-3OF5"),
    MATRIX_2OF5("MATRIX-2OF5"), OCR("OCR"), HANXIN("HANXIN"), MAILMARK("MAILMARK"), MULTICODE_DATA_FORMAT("MULTICODE-DATA-FORMAT"),
    GS1_DATAMATRIX("GS1-DATAMATRIX"), GS1_QRCODE("GS1-QRCODE"), DOTCODE("DOTCODE"), GRIDMATRIX("GRIDMATRIX"), UNDEFINED("UNDEFINED");

    private final String labelName;

    Label (String labelName) {
        this.labelName = labelName;
    }

    public String getLabelName() {
        return labelName;
    }

    public static List<String> getListLabels() {
        List<String> stringValues = new ArrayList<>();
        for (Label label : Label.values()) {
            stringValues.add(label.getLabelName());
        }
        return stringValues;
    }

    public String getLabelByLabelName(String labelName) {
        for (Label label: Label.values()){
            if (labelName.equalsIgnoreCase(label.getLabelName())) {
                return labelName;
            }
        }
        return null;
    }
}
