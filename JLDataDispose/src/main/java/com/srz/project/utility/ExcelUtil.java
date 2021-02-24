package com.srz.project.utility;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExcelUtil {
    // 输入流
    static InputStream inputStream;

    public static List<StringBuilder> excelUtil(String inputPath, int sheetIndex, int lastCellNum, int multiple) {
        // 存储excel数据
        CopyOnWriteArrayList<StringBuilder> line = new CopyOnWriteArrayList<StringBuilder>();
        try {
            inputStream = new FileInputStream(inputPath);
            Workbook workbook = WorkbookFactory.create(inputStream);
            FormulaEvaluator fe = workbook.getCreationHelper().createFormulaEvaluator();
            //获取第sheetIndex张表
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            if (sheet == null) {
                throw new IOException("传入的excel的第" + sheetIndex + "张表为空！");
            }
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row != null && row.getPhysicalNumberOfCells() != 0) {
                    // 获得行
                    //lastCellNum = sheet.getPhysicalNumberOfRows();
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    if (firstCellNum == 1)
                        firstCellNum = 0;
                    StringBuilder result = new StringBuilder();
                    //循环当前行
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
                        if (IsNumeric.isNumber(value)) {
                            if (sheetIndex == 0)
                                if (cellNum != 0)
                                    value = String.valueOf(Double.parseDouble(String.format("%.9f", Double.parseDouble(value))) * multiple);
                                else
                                    // 第一列为编号，不需要小数
                                    value = String.format("%.0f", Double.parseDouble(value));
                            else
                                value = String.format("%.1f", Double.parseDouble(value));
                        }
                        if (cellNum == lastCellNum - 1)
                            result.append(value);
                        else
                            result.append(value).append("|");
                    }
                    line.add(result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return line;
    }
}