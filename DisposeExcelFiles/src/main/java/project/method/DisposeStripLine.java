package project.method;

import project.utility.ExcelUtil;
import project.utility.ExcelUtilOptionalColumn;
import java.util.ArrayList;
import java.util.List;

public class DisposeStripLine {
    public static List<String> disposeStripLine(String inputPath, int sheetIndex, int lastCellNum) {
        // 存储处理后的数据
        List<String> disposeData = new ArrayList<>();
        try {
            // 读取excel文件数据
            List<StringBuilder> list = ExcelUtil.excelUtil(inputPath, sheetIndex, lastCellNum);
            // 读取条线名称数据
            List<StringBuilder> lineNameList = ExcelUtilOptionalColumn.excelUtil(inputPath, sheetIndex, lastCellNum, lastCellNum + 1);
            // 读取条线建议数据
            List<StringBuilder> lineAdviceList = ExcelUtilOptionalColumn.excelUtil(inputPath, sheetIndex, lastCellNum + 2, lastCellNum + 3);
            // 开关
            int OFF = 0;
            // 索引
            int index = 0;
            // 条线名称
            String lineName = "";
            // 条线建议
            String lineAdvice = "";
            for (StringBuilder line : list) {
                // 过滤表尾
                if (line.toString().startsWith("|||")) {
                    OFF = 1;
                }
                if (OFF == 1) {
                    // 条线名称
                    lineName = lineNameList.get(index).toString();
                    // 当条线建议的值为不显示条线树时，将条线建议的值设为空
                    lineAdvice = lineAdviceList.get(index).toString().replace("不显示条线树", "");
                    // 如果条线名称和条线建议都有值或者条线名称为空时，将条线建议的值赋给条线名称
                    if ((lineName.trim().length() > 0 && lineAdvice.trim().length() > 0) || lineName.trim().length() < 1) {
                        lineName = lineAdvice;
                    }
                    disposeData.add(line.toString().concat("|").concat(lineName).concat("|").concat(lineAdvice));
                }
                // 过滤表头
                if (line.toString().startsWith("职位编码")) {
                    OFF = 1;
                }
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("文件处理完成");
        }
        return disposeData;
    }
}
