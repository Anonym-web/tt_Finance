package per.srz.project.method;

import per.srz.project.utility.ClearInfoForFile;
import per.srz.project.utility.ExcelUtil;
import per.srz.project.utility.GetFilePath;
import per.srz.project.utility.IOUtility;

import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataProcessingAdditionalDirectly {
    /**
     * 适用于直接追加后缀的数据格式
     *
     * @param inputPath   文件输入路径  /data/heilongjiang-In/cs
     * @param outputPath  文件输出路径  /data/heilongjiang-Out
     * @param codedFormat 输出文件的编码格式   utf-8
     * @param date        处理文件的日期 2019-10
     * @param sheetName3 处理的sheet页名称（空格不可省略）
     * @param field 依据此变量判断表头所在位置
     * @param num 表头最后隐藏列数
     * @param splitNum 去除列的索引（可选择多列）
     */

    public static void dataProcessingAdditionalDirectly(String inputPath, String outputPath, String codedFormat, String sheetName3, String field, int num, String date, Integer... splitNum) {
        // 汇总文件的输出路径
        String outputPathCollect = "";
        // 存储文件路径
        List<String> filePathList = new ArrayList<String>();
        // 存储文件数据
        List<String> lineList;
        BufferedWriter writer = null;
        String sheetName1 = "辅助表";
        String sheetName2 = "KTT0401营业收入明细表 ";
        // 拼接输出文件路径
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
            lineList = ExcelUtil.excelFileRead(filePath, sheetName3, num);
            List<Integer> numlist = new ArrayList<Integer>();
            for (int i = 0; i < lineList.get(1).split("\\|", -1).length; i++) {
                numlist.add(i);
            }
            for (int i = 0; i < splitNum.length; i++) {
                numlist.remove(splitNum[i]);
            }
            // 开关
            boolean OFF = false;
            try {
                if (filePath.contains("汇总")) {
                    writer = IOUtility.bufferWriter(outputPathCollect, codedFormat);
                } else {
                    writer = IOUtility.bufferWriter(outputPath, codedFormat);
                }
                for (String line : lineList) {
                    if (OFF && !line.replace("|", "").equals("")) {
                        // 针对“KTT09 货币资金情况补充表(会铁通月09表)”和“KTT1001网络管理技术服务收支利情况表-分业务”设立的规则
                        if (!line.startsWith("注：支出账户指纳入决算的所有账户性质为支出类的银行账户，包括运营支出户、基建支出户、纳税专户、工资专户、社保专户、补充养老专户等。") && !line.startsWith("|校验|") && !line.startsWith("||||") && !line.startsWith("资产指标：") && !line.startsWith("||辅助核算为【C1】【客户类型】【01】【0201】【0202】【0203】【0206】【0205】【0207】【0298】")) {
                            String splitLine = "";
                            for (Integer n : numlist) {
                                splitLine += line.split("\\|", -1)[n] + "|";
                            }
                            if (sheetName3.equals("KTT14增值税补充表(会铁通月14表)")) {
                                writer.append(splitLine.replaceFirst("\\|", "").replaceFirst("\\|", "").replace("\n", "").concat(appendfield));
                            } else if (sheetName3.equals("KTT15管理口径费用统计表(会铁通月15表)")) {
                                writer.append(splitLine.replaceFirst("\\|", "").replaceFirst("\\|", "").replaceFirst("\\|", "").replace("\n", "").concat(appendfield));
                            } else {
                                writer.append(splitLine.replace("\n", "").concat(appendfield));
                            }
                            writer.newLine();
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(sheetName3.replace(" ", "") + "处理完成");
    }
}
