package com.anonym.parsedome.service;

import com.anonym.parsedome.util.ExcelUtilOptionalColumn;

import java.util.ArrayList;
import java.util.List;

public class DisposeProjectBusiness_2 {
    /**
     * 处理`工程业务表`中从AJ列到CP列的数据
     * 该方法可以截取从`firstCellNum`列到`lastCellNum`列的数据
     */
    public static List<String> projectBusiness_2(String inputPath, int sheetIndex, int firstCellNum, int lastCellNum, String time) {
        // 处理后的数据
        List<String> disposeData = new ArrayList<>();
        try {
            // 原数据
            List<StringBuilder> list = ExcelUtilOptionalColumn.excelUtil(inputPath, sheetIndex, firstCellNum, lastCellNum);
            // 获取第一列数据
            List<StringBuilder> firstLine = ExcelUtilOptionalColumn.excelUtil(inputPath, sheetIndex, 1, 2);
            // 开关
            int OFF = 0;
            // 次数
            int count = 0;
            for (StringBuilder line : list) {
                // 过滤表头
                if (line.toString().startsWith("当年新签订合同金额")) {
                    OFF = 1;
                }
                if (OFF == 1 && !line.toString().startsWith(",,,,,,,,,,,") && !line.toString().startsWith("当年新签订合同金额")) {
                    disposeData.add(firstLine.get(count).toString().replace("内蒙", "内蒙古").toString().concat(",").concat(line.toString().concat(",").concat(time)));
                }
                // 过滤表尾
                if (line.toString().startsWith(",,,,,,")) {
                    OFF = 0;
                }
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disposeData;
    }
}
