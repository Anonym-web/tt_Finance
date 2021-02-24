package com.anonym.parsedome.model;

import java.util.HashMap;
import java.util.Map;

public enum SDDataEnum {

    province("province","山东省");
    private final String value;

    private final String description;

    SDDataEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String toString(){
        return description;
    }
    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    private static final Map<String, SDDataEnum> map = new HashMap<>();

    static {
        SDDataEnum[] array = SDDataEnum.values();
        for (SDDataEnum e : array) {
            map.put(e.getValue(), e);
        }
    }

    public static String forValue(String s) {
        return map.get(s).description;
    }
}
