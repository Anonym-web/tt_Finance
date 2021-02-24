package com.srz.project.utility;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExcelUtil {
    // 输入流
    static InputStream inputStream;

    public static List<StringBuilder> excelUtil(String inputPath, int sheetIndex, int lastCellNum) throws Exception {
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
                    StringBuilder result = new StringBuilder();
                    //循环当前行
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        String value = "";
                        if (null != fe.evaluate(cell)) {
                            // 1 代表设置单元个类型为文本格式
                            cell.setCellType(1);
                            // 去除字符串中的双引号
                            value = fe.evaluate(cell).formatAsString().replace("\"", "");
//                            if (IsNumeric.isNumber(value)) {
//                                value = String.format("%.2f", Double.parseDouble(value));
//                            }
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