package project.utility;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExcelUtil {
    // 输入流
    static InputStream inputStream;

    /**
     * 按列读取Excel文件
     *
     * @param inputPath   文件输入路径
     * @param sheetIndex  读取excel文件中的sheet页的索引
     * @param lastCellNum 读取excel文件的结束列数
     */
    public static List<StringBuilder> excelUtil(String inputPath, int sheetIndex, int lastCellNum) throws Exception {
        // 存储excel数据
        CopyOnWriteArrayList<StringBuilder> line = new CopyOnWriteArrayList<>();
        try {
            inputStream = new FileInputStream(inputPath);
            Workbook workbook = WorkbookFactory.create(inputStream);
            FormulaEvaluator fe = workbook.getCreationHelper().createFormulaEvaluator();
            // 获取第sheetIndex张表
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            if (sheet == null) {
                throw new IOException("传入的excel的第" + sheetIndex + "张表为空！");
            }
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row != null && row.getPhysicalNumberOfCells() != 0) {
//                    // 获得行
//                    lastCellNum = sheet.getPhysicalNumberOfRows();
                    // 获得当前行的开始列
//                    int firstCellNum = row.getFirstCellNum();
                    int firstCellNum = 0;
                    StringBuilder result = new StringBuilder();
                    // 循环当前行
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        String value = "";
                        // 读取到的是公式
                        if (cell != null && cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                            try {
                                value = String.valueOf(cell.getNumericCellValue());
                            } catch (IllegalStateException e) {
                                try {
                                    value = String.valueOf(cell.getRichStringCellValue());
                                } catch (Exception e1) {
                                    value = "0";
                                }
                            }
                            // 读到的不是公式
                        } else if (null != fe.evaluate(cell)) {
                            assert cell != null;
                            // 1 代表设置单元个类型为文本格式
                            cell.setCellType(1);
                            value = fe.evaluate(cell).formatAsString();
                        }
                        // 去除字符串中的双引号
                        value = value.replace("\"", "");
                        // 将用科学技术法表示的数据转换为正常数字类型
                        if ((value.split("e", -1).length != 1 || value.split("E", -1).length != 1) && (IsNumeric.isNumber(value.split("e", -1)[0])) || IsNumeric.isNumber(value.split("E", -1)[0])) {
                            value = new BigDecimal(value).toPlainString();
                        }
                        // 判断值是否为数字，如果为数字的话将其转换为两位小数
                        if (IsNumeric.isNumber(value)) {
                            value = String.format("%.6f", Double.parseDouble(value));
                        }
                        // 将小数点后全为0的数保留整数
                        if (value.split("\\.", -1).length > 1 && IsNumeric.isNumber(value)) {
                            if (value.split("\\.", -1)[1].replace("0", "").equals("")) {
                                value = value.split("\\.", -1)[0];
                            }
                        }
                        for (int i = 0; i < firstCellNum; i++) {
                            result.append("|");
                        }
                        if (cellNum == lastCellNum - 1) {
                            result.append(value);
                        } else {
                            result.append(value).append("|");
                        }
                    }
                    line.add(result);
                }
            }
        } catch (FileNotFoundException e) {
            throw new Exception("文件不正确!");
        } finally {
            inputStream.close();
        }
        return line;
    }
}