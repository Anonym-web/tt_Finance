package com.anonym.parsedome.util;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExcelUtil {
    /**
     * 读取excel文件的数据，支持的格式：xlsx和xls
     *
     * @param inputPath   文件输入路径，格式：盘符://文件夹名称//具体文件名称
     * @param sheetIndex  处理的文件所在sheet页数（索引，从0开始）
     * @param lastCellNum 处理的文件中最后一列数据所在的列数
     * @return List<StringBuilder> 返回StringBuilder类型的list集合
     */

    // 输入流
    static InputStream inputStream;

    public static List<StringBuilder> excelUtil(String inputPath, int sheetIndex, int lastCellNum) throws Exception {
        // 存储excel数据
        CopyOnWriteArrayList<StringBuilder> line = new CopyOnWriteArrayList<>();
        try {
            inputStream = new FileInputStream(inputPath);
            Workbook workbook = WorkbookFactory.create(inputStream);
            FormulaEvaluator fe = workbook.getCreationHelper().createFormulaEvaluator();
            //获取第sheetIndex张表
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            // 如果读取的sheet页为空，则抛出异常
            if (sheet == null) {
                throw new IOException("传入的excel的第" + sheetIndex + "张表为空！");
            }
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row != null && row.getPhysicalNumberOfCells() != 0) {
//                    // 获得行（该方法可能不准确）
//                    lastCellNum = sheet.getPhysicalNumberOfRows();
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
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
                        if ((IsNumeric.isNumber(value.split("e", -1)[0]) || IsNumeric.isNumber(value.split("E", -1)[0])) && (value.split("e", -1).length != 1 || value.split("E", -1).length != 1)) {
                            /*System.out.println(value);
                            System.out.println(new BigDecimal(value).toPlainString());
                            System.out.println("--------------");*/
                            value = new BigDecimal(value).toPlainString();
                        }
                        // 判断值是否为数字，如果为数字的话将其转换为两位小数
                        if (IsNumeric.isNumber(value)) {
                            value = String.format("%.2f", Double.parseDouble(value));
                            if (value.equals("-0.00")){
                                value = "0.00";
                            }
                        }
                        // 将值进行拼接，以`|`为拼接符号
                        if (cellNum == lastCellNum - 1) {
                            result.append(value);
                        } else {
                            result.append(value).append(",");
                        }
                    }
                    // 将拼接好的字符号串添加到集合中
                    line.add(result);
                }
            }
        } catch (FileNotFoundException e) {
            // 异常处理
            throw new Exception("文件不正确!");
        } finally {
            // 关闭资源
            inputStream.close();
        }
        return line;
    }
}