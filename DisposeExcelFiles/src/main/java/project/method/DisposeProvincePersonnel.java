package project.method;

import project.utility.ExcelUtilOptionalColumn;

import java.util.ArrayList;
import java.util.List;

public class DisposeProvincePersonnel {
    /**
     * 处理公司省份合作人员数据
     */
    public static List<String> disposeProvincePersonnel(String inputPath, int sheetIndex, int firstCellNum, int lastCellNum, String time) {
        // 存储处理后的数据
        List<String> disposeList = new ArrayList<>();
        try {
            List<StringBuilder> list = ExcelUtilOptionalColumn.excelUtil(inputPath, sheetIndex, firstCellNum, lastCellNum);
            // 开关
            int OFF = 0;
            // 递增
            int count = 1;
            for (StringBuilder line : list) {
                if (line.toString().startsWith("汇总")) {
                    OFF = 1;
                }
                if (line.toString().startsWith("|||")) {
                    OFF = 0;
                }
                if (OFF == 1) {
                    disposeList.add(String.valueOf(count).concat("|").concat(line.toString()).concat("|").concat(time));
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disposeList;
    }
}
