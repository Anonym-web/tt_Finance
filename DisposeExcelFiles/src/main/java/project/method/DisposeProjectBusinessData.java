package project.method;

import project.utility.ExcelUtil;

import java.util.ArrayList;
import java.util.List;

public class DisposeProjectBusinessData {
    /**
     * 处理`收入汇总表`、`投中标明细`、`合同汇总表-客户`、`合同储备情况`数据的方法
     */
    public static List<String> disposeProjectBusinessData(String inputPath, int sheetIndex, int lastCellNum, String header, String footer, String time) {
        // 处理后的数据
        List<String> disposeData = new ArrayList<>();
        try {
            // 原数据
            List<StringBuilder> list = ExcelUtil.excelUtil(inputPath, sheetIndex, lastCellNum);
            // 开关
            int OFF = 0;
            for (StringBuilder line : list) {
                if (OFF == 1 && !line.toString().startsWith("||") && !line.toString().equals("")) {
                    disposeData.add(line.toString().replaceFirst("内蒙", "内蒙古").replace("-0", "0").concat("|").concat(time));
                }
                // 过滤表头
                if (line.toString().startsWith(header)) {
                    OFF = 1;
                }
                // 过滤表尾
                if (line.toString().startsWith(footer)) {
                    OFF = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disposeData;
    }
}
