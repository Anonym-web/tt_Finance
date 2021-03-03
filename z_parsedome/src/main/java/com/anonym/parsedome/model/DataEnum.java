package com.anonym.parsedome.model;

import java.util.HashMap;
import java.util.Map;

public enum DataEnum {

    interNet("interNet","网络管理技术服务定期数据"),
    engineeringBusiness("engineeringBusiness","工程业务");

    private final String value;

    private final String description;

    DataEnum(String value, String description) {
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

    private static final Map<String, DataEnum> map = new HashMap<>();

    static {
        DataEnum[] array = DataEnum.values();
        for (DataEnum e : array) {
            map.put(e.getValue(), e);
        }
    }

    public static String forValue(String s) {
        return map.get(s).description;
    }
}
