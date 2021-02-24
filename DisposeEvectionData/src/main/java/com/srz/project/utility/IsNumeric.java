package com.srz.project.utility;

public class IsNumeric {
    /**
     * 判断字符串是否为数字（包括小数、负数）
     */
    public static boolean isNumber(String str) {
        String reg = "-?[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }
}
