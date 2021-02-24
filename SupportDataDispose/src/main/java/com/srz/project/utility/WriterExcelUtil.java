package com.srz.project.utility;

import com.google.common.collect.Maps;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class WriterExcelUtil {
    /**
     * 数据写入Excel文件
     *
     * @param path   文件路径，包含文件全名，例如：D://demo.xls
     * @param name   sheet名称
     * @param titles 行标题列
     * @param values 数据集合，key为标题，value为数据
     */
    public static void writerExcel(String path, String name, List<String> titles, List<Map<String, Object>> values) {
        String style = path.substring(path.lastIndexOf(".")).toUpperCase(); // 从文件路径中获取文件的类型
        generateWorkbook(path, name, style, titles, values);
    }

    /**
     * 将数据写入指定path下的Excel文件中
     *
     * @param path    文件存储路径
     * @param name    sheet名
     * @param style   Excel类型
     * @param titles  标题串
     * @param _values 内容集
     */
    private static void generateWorkbook(String path, String name, String style, List<String> titles,
                                         List<Map<String, Object>> _values) {
        Workbook workbook = null;
        File fileF = new File(path);
        if (fileF.exists()) {
            try (FileInputStream fs = new FileInputStream(path)) {
                if ("XLS".equals(style.toUpperCase())) {
                    workbook = new HSSFWorkbook(fs);
                } else {
                    workbook = new XSSFWorkbook(fs);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 未找到工作表格时重新创建新的工作表格
        if (null == workbook) {
            if ("XLS".equals(style.toUpperCase())) {
                workbook = new HSSFWorkbook();
            } else {
                workbook = new XSSFWorkbook();
            }
        }

        // 生成一个表格
        Sheet sheet = null;
        // 文件存在的情况下 获取工作表格
        int index = 0; // 行号
        // 追加的情况下去尝试获取从第几行追加
        if (fileF.exists()) {
            sheet = workbook.getSheet(name);
            if (null != sheet) {
                index = sheet.getLastRowNum();
            }
        }
        // 如果不存在工作表格，则创建一个
        if (null == sheet) {
            if (null == name || "".equals(name)) {
                sheet = workbook.createSheet(); // name 为空则使用默认值
            } else {
                sheet = workbook.createSheet(name);
            }
        }

        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 创建标题行
        Row row = sheet.createRow(0);
        // 存储标题在Excel文件中的序号
        Map<String, Integer> titleOrder = Maps.newHashMap();
        for (int i = 0; i < titles.size(); i++) {
            Cell cell = row.createCell(i);
            String title = titles.get(i);
            cell.setCellValue(title);
            titleOrder.put(title, i);
        }

        // 写入正文
        for (Map<String, Object> stringObjectMap : _values) {
            index++; // 出去标题行，从第一行开始写
            row = sheet.createRow(index);
            for (Map.Entry<String, Object> map : stringObjectMap.entrySet()) {
                // 获取列名
                String title = map.getKey();
                // 根据列名获取序号
                int i = titleOrder.get(title);
                // 在指定序号处创建cell
                Cell cell = row.createCell(i);
                // 获取列的值
                Object object = map.getValue();
                // 判断object的类型
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (object instanceof Double) {
                    cell.setCellValue((Double) object);
                } else if (object instanceof Date) {
                    String time = simpleDateFormat.format((Date) object);
                    cell.setCellValue(time);
                } else if (object instanceof Calendar) {
                    Calendar calendar = (Calendar) object;
                    String time = simpleDateFormat.format(calendar.getTime());
                    cell.setCellValue(time);
                } else if (object instanceof Boolean) {
                    cell.setCellValue((Boolean) object);
                } else {
                    cell.setCellValue(object.toString());
                }
            }
        }
        /*
         * 写入到文件中
         */
        try {
            File file = new File(path);
            OutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception ignored) {
        }
        try {
            workbook.close();
        } catch (Exception ignored) {
        } finally {
            System.out.println("文件处理完成！");
        }
    }
}
