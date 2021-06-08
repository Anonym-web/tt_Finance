package com.srz.project.method;

import com.srz.project.utility.ExcelUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class DisposeSpectrometer {
    public static Map<String,String> parse(String path, int sheeetIndex, int lastCallNum) throws Exception {
        List<StringBuilder> totalParseList = new ArrayList<>();
        Map<String,String> returnMap = new HashMap<>();
        for (int sheetIndex = 0;sheeetIndex<3;sheeetIndex++) {
            List<StringBuilder> parseList = ExcelUtil.excelUtil(path,sheetIndex,lastCallNum);
            for (StringBuilder stringBuilder : parseList) {
                totalParseList.add(stringBuilder);
            }
        }
        for (StringBuilder stringBuilder : totalParseList) {
            String village = stringBuilder.toString().split("\\|")[0];
            String filter = stringBuilder.toString().split("\\|")[0];
            String gogogo = filter.replaceAll(".*[^\\d](?=(\\d+))","");
            String aaaString = filter.replaceAll(gogogo,"");
            String cityAndProvince = stringBuilder.toString().split("\\|")[1].concat("|").concat(stringBuilder.toString().split("\\|")[2]);
            if(village == null || village.equals("")){
                continue;
            }
            returnMap.put(aaaString,cityAndProvince+"|"+village);
        }
        return returnMap;
    }
}
