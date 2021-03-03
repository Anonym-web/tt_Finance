package com.anonym.parsedome.service;

import com.anonym.parsedome.util.ExcelUtil;

import java.util.ArrayList;
import java.util.List;

public class DisposeEngineeringBusiness {
    /**
     * 处理`工程业务表`中从A列到AJ列的数据，对应数据库中的`工程业务情况表`
     */
    public static List<String> engineeringBusiness(String inputPath, int sheetIndex, int lastCellNum, String time) {
        // 处理后的数据
        List<String> disposeData = new ArrayList<>();
        try {
            // 原数据
            List<StringBuilder> list = ExcelUtil.excelUtil(inputPath, sheetIndex, lastCellNum);
            // 开关
            int OFF = 0;
            // 中间字段
            String middleField = "";
            for (StringBuilder line : list) {
                if (OFF == 1 && !line.toString().startsWith(",,") && !line.toString().equals("")) {
                    // 将第一个字段去除
                    middleField = line.toString().replaceFirst(line.toString().split(",", -1)[0].concat(","), "").replace("内蒙", "内蒙古");
                    // 累计成本
                    String accumulativeCost = middleField.split(",", -1)[17].concat(",").concat(middleField.split(",", -1)[18]);
                    // 利润率
                    String profitMargin = middleField.split(",", -1)[24].concat(",").concat(middleField.split(",", -1)[25]);
                    middleField = middleField.replaceFirst(profitMargin.concat(","), "").replaceFirst(accumulativeCost, accumulativeCost.concat(",").concat(profitMargin));
                    disposeData.add(time.concat(",").concat(middleField));
                }
                // 过滤表头
                if (line.toString().startsWith(",,收入")) {
                    OFF = 1;
                }
                // 过滤表尾
                if (line.toString().startsWith(",,,")) {
                    OFF = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disposeData;
    }
}
