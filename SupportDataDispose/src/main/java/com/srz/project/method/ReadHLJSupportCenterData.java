package com.srz.project.method;

import com.mysql.jdbc.StringUtils;
import com.srz.project.utility.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 处理黑龙江支撑中心原数据
 */
public class ReadHLJSupportCenterData {
    // 装载支撑数据
    static CopyOnWriteArrayList<StringBuilder> list = new CopyOnWriteArrayList<>();
    // 装载处理后的支撑数据
    static CopyOnWriteArrayList<String> rawDataList = new CopyOnWriteArrayList<>();
    // 存储文件路径
    static List<String> fileList = new ArrayList<>();
    // 存储省市级数据
    static List<String> provincesList = new ArrayList<>();
    // 所属省份
    static String province;
    // 所属类型
    static String type;
    // 统计时间
    static String time;
    // 开关
    static int OFF = 0;

    /**
     * 对黑龙江财务数据做初步处理
     *
     * @param inputPath   文件输入路径，格式；盘符://文件夹名称
     * @param sheetIndex  sheet页索引
     * @param lastCellNum 处理文件的最后一列所在位置
     * @param year        数据所在年份
     * @return CopyOnWriteArrayList<String>
     */
    public static CopyOnWriteArrayList<String> readHLJSupportCenterData(String inputPath, int sheetIndex, int lastCellNum, String year) {
        try {
            // 装载文件路径
            GetFilePath.getFilePath(new File(inputPath), fileList);
            for (String fileName : fileList) {
                fileName = fileName.replace("\\", "/");
                // 截取出文件名称
                String splitFileName = StringUtils.split(StringUtils.split(fileName, "/", true).get(2), ".", true).get(0);
                // 截取出文件类型
                type = splitFileName.substring(splitFileName.length() - 4, splitFileName.length() - 2);
                // 截取出月份
                time = year.concat("-").concat(splitFileName.substring(splitFileName.length() - 2));
                // 存储省级数据
                HLJCompanyList.hLJCompanyList(provincesList);
                for (String provinces : provincesList) {
                    // 获取省份字段
                    if (provinces.startsWith("省份")) {
                        province = StringUtils.split(provinces, "\\|", true).get(1);
                        break;
                    }
                }
                // 遍历读取excel文件
                list = (CopyOnWriteArrayList<StringBuilder>) ExcelUtil.excelUtil(fileName, sheetIndex, lastCellNum);
                for (StringBuilder line : list) {
                    // 过滤表头
                    if (line.toString().startsWith("摘要") || line.toString().startsWith("科目编码")) {
                        OFF = 1;
                    }
                    if (line.toString().startsWith("总计")) {
                        OFF = 0;
                    }
                    if (OFF == 1 && !line.toString().startsWith("摘要") && !line.toString().startsWith("科目编码")) {
                        // 去除累加字段
                        if (StringUtils.split(line.toString(), "\\|", true).get(0).equals("主体帐簿累计")) {
                            continue;
                        }
                        // 去除第一个字段
                        line.delete(0, 1);
                        // 追加字段
                        line.append("|").append(province).append("|").append(type).append("|").append(time);
                        line.replace(line.toString().split("\\|")[0].length() + line.toString().split("\\|")[1].length() + 2, line.toString().split("\\|")[0].length() + line.toString().split("\\|")[1].length() + line.toString().split("\\|")[2].length() + 3, line.toString().split("\\|")[2].concat("|").concat("|").concat("|").concat("|"));
                        rawDataList.add(line.toString().concat("|"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 提示代码执行完成
            System.out.println("代码执行完成！");
        }
        return rawDataList;
    }
}
