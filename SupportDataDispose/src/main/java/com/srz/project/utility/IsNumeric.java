package com.srz.project.utility;

public class IsNumeric {
    /**
     * 判断字符串是否为数字（包括小数、负数）
     * 规则不完善
     *
     * @param str 输入的字符串
     * @return boolean 输入字符串如果是数字则true，否则false
     */
    public static boolean isNumber(String str) {
        String reg = "-[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)?";
        return str.matches(reg) && !str.equals("");
    }
}
