package per.srz.project.method;

import per.srz.project.utility.ClearInfoForFile;
import per.srz.project.utility.ExcelUtil;
import per.srz.project.utility.GetFilePath;
import per.srz.project.utility.IOUtility;

import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataProcessingCaptureMore {
    /**
     * 仅适用于“收入分业务、市场校验-此表不打印”sheet页的处理规则
     *
     * @param inputPath   文件输入路径  /data/heilongjiang-In/cs
     * @param outputPath  文件输出路径  /data/heilongjiang-Out
     * @param codedFormat 输出文件的编码格式   utf-8
     * @param date        处理文件的日期 2019-10
     * @assignParameters sheetName3 处理的sheet页名称（空格不可省略）
     * @assignParameters field 依据此变量判断表头所在位置
     * @assignParameters num 表头最后隐藏列数
     * @optionalParameters splitNum 去除列的索引（可选择多列）
     */

    public static void dataProcessingCaptureMore(String inputPath, String outputPath, String codedFormat, String sheetName3, String field, int num, String date, Integer... splitNum) {
        // 汇总文件的输出路径1
        String outputPathCollect = "";
        // 汇总文件的输出路径2
        String outputPathCollect2 = "";
        // 汇总文件的输出路径3
        String outputPathCollect3 = "";
        // 汇总文件的输出路径4
        String outputPathCollect4 = "";
        // 地市文件的输出路径2
        String outputPath2 = "";
        // 地市文件的输出路径3
        String outputPath3 = "";
        // 地市文件的输出路径4
        String outputPath4 = "";
        // 存储文件路径
        List<String> filePathList = new ArrayList<String>();
        // 存储文件数据
        List<String> lineList = new ArrayList<String>();
        BufferedWriter writer = null;
        BufferedWriter writerAddition_2 = null;
        BufferedWriter writerAddition_3 = null;
        BufferedWriter writerAddition_4 = null;
        String sheetName1 = "辅助表";
        String sheetName2 = "KTT0401营业收入明细表 ";
        if (System.getProperty("os.name").split(" ")[0].equals("Windows")) {
            outputPathCollect = outputPath.concat("\\").concat("黑龙江省份").concat("\\").concat(sheetName3.split("（")[0].split("\\(")[0].trim()).replace(" ", "").concat(date).concat(".txt");
            outputPathCollect2 = outputPathCollect.replace(date.concat(".txt"), "_附表1_".concat(date.concat(".txt")));
            outputPathCollect3 = outputPathCollect.replace(date.concat(".txt"), "_附表2_".concat(date.concat(".txt")));
            outputPathCollect4 = outputPathCollect.replace(date.concat(".txt"), "_附表3_".concat(date.concat(".txt")));
            outputPath = outputPath.concat("\\").concat("黑龙江地市").concat("\\").concat(sheetName3.split("（")[0].split("\\(")[0].trim()).replace(" ", "").concat(date).concat(".txt");
            outputPath2 = outputPath.replace(date.concat(".txt"), "_附表1_".concat(date.concat(".txt")));
            outputPath3 = outputPath.replace(date.concat(".txt"), "_附表2_".concat(date.concat(".txt")));
            outputPath4 = outputPath.replace(date.concat(".txt"), "_附表3_".concat(date.concat(".txt")));
        } else {
            outputPathCollect = outputPath.concat("/").concat("黑龙江省份").concat("/").concat(sheetName3.split("（")[0].split("\\(")[0].trim()).replace(" ", "").concat(date).concat(".txt");
            outputPathCollect2 = outputPathCollect.replace(date.concat(".txt"), "_附表1_".concat(date.concat(".txt")));
            outputPathCollect3 = outputPathCollect.replace(date.concat(".txt"), "_附表2_".concat(date.concat(".txt")));
            outputPathCollect4 = outputPathCollect.replace(date.concat(".txt"), "_附表3_".concat(date.concat(".txt")));
            outputPath = outputPath.concat("/").concat("黑龙江地市").concat("/").concat(sheetName3.split("（")[0].split("\\(")[0].trim()).replace(" ", "").concat(date).concat(".txt");
            outputPath2 = outputPath.replace(date.concat(".txt"), "_附表1_".concat(date.concat(".txt")));
            outputPath3 = outputPath.replace(date.concat(".txt"), "_附表2_".concat(date.concat(".txt")));
            outputPath4 = outputPath.replace(date.concat(".txt"), "_附表3_".concat(date.concat(".txt")));
        }
        // 清空文件内容
        ClearInfoForFile.clearInfoForFile(outputPathCollect);
        ClearInfoForFile.clearInfoForFile(outputPath);
        ClearInfoForFile.clearInfoForFile(outputPathCollect2);
        ClearInfoForFile.clearInfoForFile(outputPath2);
        ClearInfoForFile.clearInfoForFile(outputPathCollect3);
        ClearInfoForFile.clearInfoForFile(outputPath3);
        ClearInfoForFile.clearInfoForFile(outputPathCollect4);
        ClearInfoForFile.clearInfoForFile(outputPath4);
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
            // 开关
            boolean OFF = false;
            try {
                if (filePath.contains("汇总")) {
                    writer = IOUtility.bufferWriter(outputPathCollect, codedFormat);
                    writerAddition_2 = IOUtility.bufferWriter(outputPathCollect2, codedFormat);
                    writerAddition_3 = IOUtility.bufferWriter(outputPathCollect3, codedFormat);
                    writerAddition_4 = IOUtility.bufferWriter(outputPathCollect4, codedFormat);
                } else {
                    writer = IOUtility.bufferWriter(outputPath, codedFormat);
                    writerAddition_2 = IOUtility.bufferWriter(outputPath2, codedFormat);
                    writerAddition_3 = IOUtility.bufferWriter(outputPath3, codedFormat);
                    writerAddition_4 = IOUtility.bufferWriter(outputPath4, codedFormat);
                }
                for (String line : lineList) {
                    if (OFF && !line.replace("|", "").equals("")) {
                        if (!line.split("\\|", -1)[0].concat(line.split("\\|", -1)[1]).trim().equals("")) {
                            writer.append(line.split("\\|", -1)[0].concat("|").concat(line.split("\\|", -1)[1]).concat("|").concat(appendfield));
                            writer.newLine();
                        }
                        if (!line.split("\\|", -1)[2].concat(line.split("\\|", -1)[3]).trim().equals("")) {
                            writerAddition_2.append(line.split("\\|", -1)[2].concat("|").concat(line.split("\\|", -1)[3]).concat("|").concat(appendfield));
                            writerAddition_2.newLine();
                        }
                        if (!line.split("\\|", -1)[4].concat(line.split("\\|", -1)[5]).trim().equals("")) {
                            writerAddition_3.append(line.split("\\|", -1)[4].concat("|").concat(line.split("\\|", -1)[5]).concat("|").concat(appendfield));
                            writerAddition_3.newLine();
                        }
                        if (!line.split("\\|", -1)[7].concat(line.split("\\|", -1)[8]).trim().equals("")) {
                            writerAddition_4.append(line.split("\\|", -1)[7].concat("|").concat(line.split("\\|", -1)[8]).concat("|").concat(appendfield));
                            writerAddition_4.newLine();
                        }
                    }
                    if (line.startsWith(field)) {
                        OFF = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    // 关闭资源
                    writer.close();
                    writerAddition_2.close();
                    writerAddition_3.close();
                    writerAddition_4.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(sheetName3.replace(" ", "") + "处理完成");
    }
}
