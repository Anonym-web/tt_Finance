package com.srz.project.method;

import java.util.List;

/*
* 通过出发地、目的地来获取
* */
public class DisposeField {
    public static String disposeField(String field, String companyName, int num) {
        if (field.contains("、"))
            field = field.split("、", -1)[0];
        else if (field.contains("."))
            field = field.split("\\.", -1)[0];
        else if (field.contains("/"))
            field = field.split("/", -1)[0];
        else if (field.contains(","))
            field = field.split(",", -1)[0];
        else if (field.contains("，"))
            field = field.split("，", -1)[0];
        if ((field.substring(field.length() - 1).equals("东") || field.substring(field.length() - 1).equals("南") || field.substring(field.length() - 1).equals("西") || field.substring(field.length() - 1).equals("北")) && field.length() > 2)
            field = field.substring(0, field.length() - 1);
        if (field.substring(field.length() - 1).equals("站")) {
            field = field.substring(0, 2);
            if (field.equals("火车"))
                field = companyName.split("分公司", -1)[0].substring(companyName.split("分公司", -1)[0].length() - 2);
        }
        if (field.contains("（") || field.contains("）"))
            field = field.split("（")[0];
        else if (field.contains("(") || field.contains(")"))
            field = field.split("\\(")[0];
        if (field.contains("到"))
            field = field.split("到")[num];
        if (field.contains("支撑服务中心"))
            field = field.replace("支撑服务中心", "");
        if (field.contains("服务支撑中心"))
            field = field.replace("服务支撑中心", "");
        if (field.contains("支撑中心")) {
            field = field.replace("各支撑中心", "").replace("支撑中心", "");
            if (field.equals(""))
                field = companyName.split("分公司", -1)[0].substring(companyName.split("分公司", -1)[0].length() - 2);
        }
        if (field.contains("机场")) {
            field = field.replace("机场", "");
            if (field.length() != 2)
                field = field.substring(0, 2);
        }
        if (field.contains("分公司")) {
            field = field.replace("省分公司", "").replace("分公司", "").replace("铁通", "");
            if (field.equals(""))
                field = companyName.split("分公司", -1)[0].substring(companyName.split("分公司", -1)[0].length() - 2);
        }
        if (field.contains("营业厅"))
            field = field.replace("营业厅", "");
        if (field.contains("铁通公司"))
            field = field.replace("铁通公司", "");
        if (field.contains("市区")) {
            field = field.replace("区", "");
            if (field.length() < 3)
                field = companyName.split("分公司", -1)[0].substring(companyName.split("分公司", -1)[0].length() - 2);
        }
        if (field.contains("麻城"))
            field = field.replace("等", "").replace("城区", "");
        if (field.contains("等地"))
            field = field.substring(0, 2);
        if (field.contains("省公司") || field.contains("市经营部") || field.contains("开发区") || field.contains("酒店") || field.contains("培训基地"))
            field = companyName.split("分公司", -1)[0].substring(companyName.split("分公司", -1)[0].length() - 2);
        if (field.contains("经营部"))
            field = field.replace("经营部", "");
        if (field.contains("铁通"))
            field = field.replace("铁通", "");
        if (field.length() > 4)
            field = field.substring(0, 2);
        if (field.contains("湖北") || field.contains("汉口"))
            field = "武汉";
        return field;
    }

    public static String getField(List<String> areaData, List<String> cityData, List<String> provinceData, String field, String level, String companyName) {
        for (String area : areaData) {
            if (area.split("\\|", -1)[1].contains(field)) {
                level = area.split("\\|", -1)[0];
                break;
            }
        }
        for (String city : cityData) {
            if (city.split("\\|", -1)[1].contains(field)) {
                level = city.split("\\|", -1)[0];
                break;
            }
        }
        for (String province : provinceData) {
            if (province.split("\\|", -1)[1].contains(field)) {
                level = province.split("\\|", -1)[0];
                break;
            }
        }
        if (level.equals(""))
            field = companyName.split("分公司", -1)[0].substring(companyName.split("分公司", -1)[0].length() - 2);
        if (field.contains("湖北"))
            field = "武汉";
        for (String area : areaData) {
            if (area.split("\\|", -1)[1].contains(field)) {
                level = area.split("\\|", -1)[0];
                break;
            }
        }
        for (String city : cityData) {
            if (city.split("\\|", -1)[1].contains(field)) {
                level = city.split("\\|", -1)[0];
                break;
            }
        }
        for (String province : provinceData) {
            if (province.split("\\|", -1)[1].contains(field)) {
                level = province.split("\\|", -1)[0];
                break;
            }
        }
        return level;
    }
}
