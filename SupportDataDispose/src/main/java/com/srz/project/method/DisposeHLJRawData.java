package com.srz.project.method;

import com.mysql.jdbc.StringUtils;
import com.srz.project.utility.ExcelUtil;
import com.srz.project.utility.GetFilePath;
import com.srz.project.utility.HLJCompanyList;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DisposeHLJRawData {
    // 存储省市级数据
    static List<String> provincesList = new ArrayList<>();
    // 装载支撑数据
    static CopyOnWriteArrayList<StringBuilder> list = new CopyOnWriteArrayList<>();
    // 装载处理后的支撑数据
    static CopyOnWriteArrayList<String> rawDataList = new CopyOnWriteArrayList<>();
    // 存储文件路径
    static List<String> fileList = new ArrayList<>();
    // 所属省份
    static String province;
    // 所属地市
    static String city;
    // 统计时间
    static String time;
    // 开关
    static int OFF = 0;
    // 中间字段
    static String middleFile;

    /**
     * 对黑龙江财务数据稍作处理
     *
     * @param inputPath   文件输入路径，格式：盘符://文件夹名称
     * @param sheetIndex  sheet页索引
     * @param lastCellNum 处理文件的最后一列所在位置
     * @return CopyOnWriteArrayList<String>
     */
    public static CopyOnWriteArrayList<String> disposeHLJRawData(String inputPath, int sheetIndex, int lastCellNum) {
        try {
            // 装载省市数据
            HLJCompanyList.hLJCompanyList(provincesList);
            // 装载文件路径
            GetFilePath.getFilePath(new File(inputPath), fileList);
            province = provincesList.get(0).split("\\|", -1)[1];
            for (String fileName : fileList) {
                // 装载excel数据
                list = (CopyOnWriteArrayList<StringBuilder>) ExcelUtil.excelUtil(fileName, sheetIndex, lastCellNum);
                for (StringBuilder line : list) {
                    // 获取时间
                    if (line.toString().startsWith("期间")) {
                        time = line.toString().split("\\|", -1)[0].split("-")[1].replace(".", "-");
                    }
                    // 过滤表头
                    if (line.toString().startsWith("摘要") || line.toString().startsWith("科目编码")) {
                        OFF = 1;
                    }
                    if (line.toString().startsWith("总计")) {
                        OFF = 0;
                    }
                    // 对数据处理的主要逻辑
                    if (OFF == 1 && !line.toString().startsWith("摘要") && !line.toString().startsWith("科目编码")) {
                        // 去除累加字段
                        if (StringUtils.split(line.toString(), "\\|", true).get(0).equals("主体帐簿累计")) {
                            continue;
                        }
                        // 去除第一个字段
                        line.delete(0, 1);
                        // 获取本条数据的`所属地市`字段的值
                        for (String provinceEntry : provincesList) {
                            if (provinceEntry.startsWith(line.toString().split("\\|", -1)[0])) {
                                city = provinceEntry.split("\\|", -1)[1];
                                break;
                            }
                        }
                        // 去除当前数据的第一个字段，即`主体账簿名称`
                        line.delete(0, line.toString().split("\\|", -1)[0].length() + 1);
                        line.replace(line.toString().split("\\|", -1)[0].length() + 1, line.toString().split("\\|", -1)[0].length() + line.toString().split("\\|", -1)[1].length() + 1, line.toString().split("\\|", -1)[1].concat("|"));
                        line.append("|").append(province).append("|").append(city).append("|").append(time);
                        // 对期末余额字段为空的值补0
                        if (line.toString().split("\\|", -1)[10].isEmpty()) {
                            middleFile = line.toString().replace("|".concat(line.toString().split("\\|", -1)[11]), "0|".concat(line.toString().split("\\|", -1)[11]));
                        } else {
                            middleFile = line.toString();
                        }
                        if (middleFile.split("\\|", -1)[1].contains("支撑服务中心")) {
                            rawDataList.add(middleFile);
                        } else if (!middleFile.split("\\|", -1)[1].contains("支撑服务中心") && city.equals("齐齐哈尔市")) {
                            if (middleFile.split("\\|", -1)[1].contains("电信局")) {
                                rawDataList.add(middleFile);
                            } else {
                                rawDataList.add(middleFile.replace(middleFile.split("\\|", -1)[1], "机关及其他部门"));
                            }
                        } else if (!middleFile.split("\\|", -1)[1].contains("支撑服务中心") && city.equals("佳木斯市")) {
                            if (middleFile.split("\\|", -1)[1].contains("支撑中心")) {
                                rawDataList.add(middleFile);
                            } else {
                                rawDataList.add(middleFile.replace(middleFile.split("\\|", -1)[1], "机关及其他部门"));
                            }
                        } else {
                            rawDataList.add(middleFile.replace(middleFile.split("\\|", -1)[1], "机关及其他部门"));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("代码执行完成！");
        }
        return rawDataList;
    }
}
