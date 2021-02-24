package per.srz.project.method;

import per.srz.project.utility.ClearInfoForFile;
import per.srz.project.utility.ExcelUtil;
import per.srz.project.utility.GetFilePath;
import per.srz.project.utility.IOUtility;

import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataProcessingAssignVerticalSharding {
    /**
     * 适用于对指定列进行垂直切分的场景
     *
     * @param inputPath   文件输入路径  /data/heilongjiang-In/cs
     * @param outputPath  文件输出路径  /data/heilongjiang-Out
     * @param codedFormat 输出文件的编码格式   utf-8
     * @param date        处理文件的日期 2019-10
     * @param sheetName3 处理的sheet页名称（空格不可省略）
     * @param field 依据此变量判断表头所在位置
     * @param num 表头最后隐藏列数
     * @param splitNum 指定切分列的索引
     */

    public static void dataProcessingAssignVerticalSharding(String inputPath, String outputPath, String codedFormat, String sheetName3, String field, int num, String date, int splitNum) {
        // 汇总文件的输出路径
        String outputPathCollect = "";
        // 地市附表文件的输出路径
        String outputPath2 = "";
        // 省份附表文件的输出路径
        String outputPathCollect2 = "";
        // 存储文件路径
        List<String> filePathList = new ArrayList<String>();
        // 存储文件数据
        List<String> lineList = new ArrayList<String>();
        BufferedWriter writer = null;
        BufferedWriter writerAddition = null;
        String sheetName1 = "辅助表";
        String sheetName2 = "KTT0401营业收入明细表 ";
        if (System.getProperty("os.name").split(" ")[0].equals("Windows")) {
            outputPathCollect = outputPath.concat("\\").concat("黑龙江省份").concat("\\").concat(sheetName3.split("（")[0].split("\\(")[0].trim()).replace(" ", "").concat(date).concat(".txt");
            outputPathCollect2 = outputPathCollect.replace(date.concat(".txt"), "_附表_".concat(date.concat(".txt")));
            outputPath = outputPath.concat("\\").concat("黑龙江地市").concat("\\").concat(sheetName3.split("（")[0].split("\\(")[0].trim()).replace(" ", "").concat(date).concat(".txt");
        } else {
            outputPathCollect = outputPath.concat("/").concat("黑龙江省份").concat("/").concat(sheetName3.split("（")[0].split("\\(")[0].trim()).replace(" ", "").concat(date).concat(".txt");
            outputPathCollect2 = outputPathCollect.replace(date.concat(".txt"), "_附表_".concat(date.concat(".txt")));
            outputPath = outputPath.concat("/").concat("黑龙江地市").concat("/").concat(sheetName3.split("（")[0].split("\\(")[0].trim()).replace(" ", "").concat(date).concat(".txt");
        }
        outputPath2 = outputPath.replace(date.concat(".txt"), "_附表_".concat(date.concat(".txt")));
        // 清空文件内容
        ClearInfoForFile.clearInfoForFile(outputPathCollect);
        ClearInfoForFile.clearInfoForFile(outputPath);
        ClearInfoForFile.clearInfoForFile(outputPathCollect2);
        ClearInfoForFile.clearInfoForFile(outputPath2);
        // 获取文件路径
        GetFilePath.getFilePath(new File(inputPath), filePathList);
        // 依次处理文件夹中的文件
        for (String filePath : filePathList) {
            // 拼接字符串
            String appendfield = AppendField.dataDispose(filePath, sheetName1, sheetName2);
            // 判断文件是否属于指定日期
            if (!date.replace("-", "").equals(appendfield.split("\\|", -1)[appendfield.split("\\|", -1).length - 1].replace("-", ""))) {
                continue;
            }
            lineList = ExcelUtil.excelFileRead(filePath, sheetName3, num);
            // 左半部分的内容
            List<String> columnsList1 = new ArrayList<String>();
            // 右半部分的内容
            List<String> columnsList2 = new ArrayList<String>();
            // 开关
            boolean OFF = false;
            for (String line : lineList) {
                if (OFF && !line.replace("|", "").equals("")) {
                    StringBuilder column1 = new StringBuilder();
                    for (int i = 0; i < splitNum; i++) {
                        if (i == splitNum - 1) {
                            column1.append(line.split("\\|", -1)[i].trim()).append("|").append(appendfield);
                            if (!column1.toString().startsWith("注")) {
                                columnsList1.add(column1.toString());
                            }
                        } else {
                            column1.append(line.split("\\|", -1)[i]).append("|");
                        }
                    }
                    StringBuilder column2 = new StringBuilder();
                    for (int i = splitNum; i < line.split("\\|", -1).length; i++) {
                        if (i == line.split("\\|", -1).length - 1) {
                            column2.append(line.split("\\|", -1)[i]).append("|").append(appendfield);
                            if (!column2.toString().startsWith("注") && !column2.toString().startsWith("|||||")) {
                                columnsList2.add(column2.toString());
                            }
                        } else {
                            column2.append(line.split("\\|", -1)[i]).append("|");
                        }
                    }
                }
                if (line.startsWith(field)) {
                    OFF = true;
                }
            }
            try {
                if (filePath.contains("汇总")) {
                    writer = IOUtility.bufferWriter(outputPathCollect, codedFormat);
                    writerAddition = IOUtility.bufferWriter(outputPathCollect2, codedFormat);
                } else {
                    writer = IOUtility.bufferWriter(outputPath, codedFormat);
                    writerAddition = IOUtility.bufferWriter(outputPath2, codedFormat);
                }
                for (String columns1 : columnsList1) {
                    writer.append(columns1);
                    writer.newLine();
                }
                for (String columns2 : columnsList2) {
                    writerAddition.append(columns2);
                    writerAddition.newLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    // 关闭资源
                    writer.close();
                    assert writerAddition != null;
                    writerAddition.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(sheetName3.replace(" ", "") + "处理完成");
    }
}
