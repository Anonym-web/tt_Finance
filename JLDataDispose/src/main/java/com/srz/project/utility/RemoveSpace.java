package com.srz.project.utility;

public class RemoveSpace {
    /*
     * 去除字符串左侧空格
     */
    public static String removeSpace(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                str = str.substring(i, str.length());
                break;
            }
        }
        return str;
    }
}
