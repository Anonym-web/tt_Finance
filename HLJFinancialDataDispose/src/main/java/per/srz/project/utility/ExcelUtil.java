package per.srz.project.utility;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    /**
     * 通过指定sheet页读取Excel文件的内容
     *
     * @param path 文件路径
     * @param name sheet页的名称
     * @return list 返回指定文件的sheet页上的内容
     */

    public static CopyOnWriteArrayList<String> excelFileRead(String path, String name, int num) {
        // 装载excel文件中的内容
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        // 创建workbook对象
        Workbook workbook = null;
        // 创建流
        InputStream stream = null;
        try {
            list.clear();
            stream = new FileInputStream(path);
            // 总行数
            int totalRows = 0;
            // 总列数
            int totalCells = 0;
            String fileType = path.split("\\.")[1];
            if (fileType.equals("xlsm")) {
                workbook = new XSSFWorkbook(stream);
                // 得到该xlsm文件的sheet页数
                int numOfSheets = workbook.getNumberOfSheets();
                for (int i = 0; i < numOfSheets; i++) {
                    Sheet sheet = workbook.getSheetAt(i);
                    // Excel的行数
                    totalRows = sheet.getPhysicalNumberOfRows();
                    // Excel的列数
                    if (totalRows >= 1 && sheet.getRow(0) != null)
                        totalCells = sheet.getRow(0).getPhysicalNumberOfCells() + num;
                    String sheetName = sheet.getSheetName();
                    if (sheetName.equals(name)) {
                        // 遍历行（去掉表头）
                        for (int cellNum = 1; cellNum < totalRows; cellNum++) {
                            // 遍历列
                            int count = 0;
                            StringBuffer line = new StringBuffer();
                            for (int rowNum = 0; rowNum < totalCells; rowNum++) {
                                // 获取指定行中的列的内容
                                Row row = sheet.getRow(cellNum);
                                if (row.getCell(rowNum) == null)
                                    row.createCell(rowNum).setCellValue("|");
                                else
                                    row.getCell(rowNum).setCellType(Cell.CELL_TYPE_STRING);
                                count++;
                                line.append(row.getCell(rowNum).getStringCellValue().concat("|"));
                                if (count == totalCells) {
                                    list.add(line.toString().substring(0, line.length() - 1));
                                    count = 0;
                                    line = new StringBuffer();
                                }
                            }
                        }
                    }
                }
            } else
                System.out.println("您输入的excel格式不正确");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
                workbook.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}