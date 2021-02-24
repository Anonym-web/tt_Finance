package per.srz.project.method;

import per.srz.project.utility.ClearInfoForFile;
import per.srz.project.utility.ExcelUtil;
import per.srz.project.utility.GetFilePath;
import per.srz.project.utility.IOUtility;

import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataProcessingVerticalSharding {
    /**
     * 适用于对数据行进行垂直切分的场景
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

    public static void dataProcessingVerticalSharding(String inputPath, String outputPath, String codedFormat, String sheetName3, String field, int num, String date, Integer... splitNum) {
        // 汇总文件的输出路径
        String outputPathCollect = "";
        // 存储文件路径
        List<String> filePathList = new ArrayList<String>();
        // 存储文件内容
        List<String> lineList = new ArrayList<String>();
        BufferedWriter writer = null;
        String sheetName1 = "辅助表";
        String sheetName2 = "KTT0401营业收入明细表 ";
        if (System.getProperty("os.name").split(" ")[0].equals("Windows")) {
            outputPathCollect = outputPath.concat("\\").concat("黑龙江省份").concat("\\").concat(sheetName3.split("（")[0].split("\\(")[0].trim()).replace(" ", "").concat(date).concat(".txt");
            outputPath = outputPath.concat("\\").concat("黑龙江地市").concat("\\").concat(sheetName3.split("（")[0].split("\\(")[0].trim()).replace(" ", "").concat(date).concat(".txt");
        } else {
            outputPathCollect = outputPath.concat("/").concat("黑龙江省份").concat("/").concat(sheetName3.split("（")[0].split("\\(")[0].trim()).replace(" ", "").concat(date).concat(".txt");
            outputPath = outputPath.concat("/").concat("黑龙江地市").concat("/").concat(sheetName3.split("（")[0].split("\\(")[0].trim()).replace(" ", "").concat(date).concat(".txt");
        }
        // 清空文件内容
        ClearInfoForFile.clearInfoForFile(outputPathCollect);
        ClearInfoForFile.clearInfoForFile(outputPath);
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
            // 左半部分的内容
            List<String> columnsList1 = new ArrayList<String>();
            // 右半部分的内容
            List<String> columnsList2 = new ArrayList<String>();
            lineList = ExcelUtil.excelFileRead(filePath, sheetName3, num);
            // 开关
            boolean OFF = false;
            int OFFNum = 0;
            // 逐行遍历文件中的内容
            for (String line : lineList) {
                // 过滤掉表头和空行
                if (OFF && !line.replace("|", "").equals("")) {
                    if (OFFNum == 1) {
                        String jointStr1 = "";
                        for (int i = 0; i < (line.split("\\|", -1).length / 2) - 1; i++) {
                            if (i == (line.split("\\|", -1).length / 2) - 2) {
                                jointStr1 += line.replace("'", "").split("\\|", -1)[i].concat("|").concat(line.split("\\|", -1)[line.split("\\|", -1).length - 2]);
                                if (!jointStr1.startsWith("|||") && !jointStr1.startsWith("|合计") && !jointStr1.startsWith("||流入") && !jointStr1.startsWith("||支出") && !jointStr1.startsWith("||净额")) {
                                    columnsList1.add(jointStr1.concat("|").concat(appendfield));
                                }
                                break;
                            } else {
                                jointStr1 += line.replace("'", "").split("\\|", -1)[i].concat("|");
                            }
                        }
                        String jointStr2 = "";
                        for (int i = (line.split("\\|", -1).length / 2) - 1; i < (line.split("\\|", -1).length) - 2; i++) {
                            if (i == line.split("\\|", -1).length - 3) {
                                jointStr2 += line.replace("'", "").split("\\|", -1)[i].concat("|").concat(line.split("\\|", -1)[line.split("\\|", -1).length - 1]);
                                if (!jointStr2.startsWith("|||") && !jointStr2.startsWith("|合计")) {
                                    columnsList2.add(jointStr2.concat("|").concat(appendfield));
                                }
                                break;
                            } else {
                                jointStr2 += line.replace("'", "").split("\\|", -1)[i].concat("|");
                            }
                        }
                    } else if (OFFNum == 2) {
                        String jointStr1 = "";
                        for (int i = 0; i < line.split("\\|", -1).length / 2; i++) {
                            if (i == (line.split("\\|", -1).length / 2) - 1) {
                                jointStr1 += line.replace("'", "").split("\\|", -1)[i];
                                if (!jointStr1.startsWith("|||") && !jointStr1.startsWith("|合计")) {
                                    columnsList1.add(jointStr1.concat("|").concat(appendfield));
                                }
                                break;
                            } else {
                                jointStr1 += line.replace("'", "").split("\\|", -1)[i].concat("|");
                            }
                        }
                        String jointStr2 = "";
                        for (int i = (line.split("\\|", -1).length / 2); i < line.split("\\|", -1).length; i++) {
                            if (i == line.split("\\|", -1).length - 1) {
                                jointStr2 += line.replace("'", "").split("\\|", -1)[i];
                                if (!jointStr2.startsWith("|||") && !jointStr2.startsWith("|合计")) {
                                    columnsList2.add(jointStr2.concat("|").concat(appendfield));
                                }
                                break;
                            } else {
                                jointStr2 += line.replace("'", "").split("\\|", -1)[i].concat("|");
                            }
                        }
                    }
                } else if (line.startsWith(field)) {
                    // 列数
                    int columnsNum = line.split("\\|", -1).length;
                    for (int i = 1; i < columnsNum; i++) {
                        // 获取第一列在表头中第二次出现的列数
                        if (line.split("\\|", -1)[i].equals(line.split("\\|", -1)[0])) {
                            if (columnsNum % i != 0 && columnsNum / i == 2) {
                                OFFNum = 1;
                                break;
                            } else if (columnsNum % i == 0 && columnsNum / i == 2) {
                                OFFNum = 2;
                                break;
                            }
                        }
                    }
                    // 判断读取的行是否是表头
                    OFF = true;
                }
            }
            try {
                // 写入数据
                if (filePath.contains("汇总")) {
                    writer = IOUtility.bufferWriter(outputPathCollect, codedFormat);
                } else {
                    writer = IOUtility.bufferWriter(outputPath, codedFormat);
                }
                List<Integer> numlist = new ArrayList<Integer>();
                for (int i = 0; i < columnsList1.get(1).split("\\|", -1).length; i++) {
                    numlist.add(i);
                }
                for (int i = 0; i < splitNum.length; i++) {
                    numlist.remove(splitNum[i]);
                }
                for (String columns1 : columnsList1) {
                    String line = "";
                    for (Integer n : numlist) {
                        if (n == columnsList1.get(1).split("\\|", -1).length - 1) {
                            line += columns1.split("\\|", -1)[n];
                        } else {
                            line += columns1.split("\\|", -1)[n] + "|";
                        }
                    }
                    if (sheetName3.equals("KTT13Z成本费用表（按成本属性分类）(会铁通月13表)")) {
                        writer.append(line.replaceFirst("\\|", "").replaceFirst("\\|", ""));
                    } else {
                        writer.append(line);
                    }
                    writer.newLine();
                }
                for (String columns2 : columnsList2) {
                    String line = "";
                    for (Integer n : numlist) {
                        if (n == columnsList1.get(1).split("\\|", -1).length - 1) {
                            line += columns2.split("\\|", -1)[n];
                        } else {
                            line += columns2.split("\\|", -1)[n] + "|";
                        }
                    }
                    if (sheetName3.equals("KTT13Z成本费用表（按成本属性分类）(会铁通月13表)")) {
                        writer.append(line.replaceFirst("\\|", "").replaceFirst("\\|", ""));
                    } else {
                        writer.append(line);
                    }
                    writer.newLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    // 关闭资源
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(sheetName3.replace(" ", "") + "处理完成");
    }
}
