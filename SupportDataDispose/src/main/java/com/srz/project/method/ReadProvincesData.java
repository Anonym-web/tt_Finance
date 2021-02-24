package com.srz.project.method;

import com.srz.project.utility.ExcelUtil;
import com.srz.project.utility.GetFilePath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ReadProvincesData {
    // 装载支撑数据
    static CopyOnWriteArrayList<StringBuilder> list = new CopyOnWriteArrayList<>();
    // 装载处理后的支撑数据
    static CopyOnWriteArrayList<String> rawDataList = new CopyOnWriteArrayList<>();
    // 存储文件路径
    static List<String> fileList = new ArrayList<>();
    // 所属省份
    static String province;
    // 所属类型
    static String type = "收入";

    /**
     * 处理辅助余额表的数据
     *
     * @param inputPath   文件输入路径，格式；盘符://文件夹名称
     * @param sheetIndex  sheet页索引
     * @param lastCellNum 处理文件的最后一列所在位置
     * @param time        时间，格式：2020-01
     * @return CopyOnWriteArrayList<String>
     */
    public static CopyOnWriteArrayList<String> readProvincesData(String inputPath, int sheetIndex, int lastCellNum, String time) {
        try {
            // 装载文件路径
            GetFilePath.getFilePath(new File(inputPath), fileList);
            for (String fileName : fileList) {
                province = fileName.split("\\\\", 0)[fileName.split("\\\\", 0).length - 1].split("\\.", 0)[0];
                // 开关
                int OFF = 0;
                fileName = fileName.replace("\\", "/");
                // 遍历读取excel文件
                list = (CopyOnWriteArrayList<StringBuilder>) ExcelUtil.excelUtil(fileName, sheetIndex, lastCellNum);
                for (StringBuilder line : list) {
                    if (line.toString().startsWith("摘要") || line.toString().startsWith("科目编码")) {
                        OFF = 1;
                    }
                    if (line.toString().startsWith("总计")) {
                        OFF = 0;
                    }
                    if (OFF == 1 && !line.toString().startsWith("摘要") && !line.toString().startsWith("科目编码")) {
                        String disposeLine = line.toString().replaceFirst(line.toString().split("\\|")[0].concat("\\|"), "");
                        // 追加字段
                        disposeLine = disposeLine.concat("|").concat(province).concat("|").concat(type).concat("|").concat(time);
                        rawDataList.add(disposeLine);
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
