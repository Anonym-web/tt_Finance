package com.anonym.parsedome.service;

import com.anonym.parsedome.model.SDDataEnum;
import com.anonym.parsedome.util.ExcelUtil;
import com.anonym.parsedome.util.GetFilePath;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ReadSDSupportCenterData {
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

    /**
     * 对山东财务数据做初步处理
     *
     * @param inputPath   文件输入路径，格式；盘符://文件夹名称
     * @param sheetIndex  sheet页索引
     * @param lastCellNum 处理文件的最后一列所在位置（该方法中对lastCellNum做了特殊处理，默认成本类型文件为14列，收入类型为13列）
     * @return CopyOnWriteArrayList<String>
     */
    public static CopyOnWriteArrayList<String> readSDSupportCenterData(String inputPath, int sheetIndex, int lastCellNum) {
        try {
            // 装载文件路径
            GetFilePath.getFilePath(new File(inputPath), fileList);
            for (String fileName : fileList) {
                // 开关
                int OFF = 0;
                fileName = fileName.replace("\\", "/");
                type = fileName.split("/", -1)[fileName.split("/", -1).length - 1].split("月", -1)[1].substring(0, 2);
                // 存储省级数据
                province = SDDataEnum.forValue("province");
                // 根据文件类型调整列数
                if (type.equals("成本")) {
                    lastCellNum = 14;
                } else if (type.equals("收入")) {
                    lastCellNum = 13;
                }
                // 遍历读取excel文件
                list = (CopyOnWriteArrayList<StringBuilder>) ExcelUtil.excelUtil(fileName, sheetIndex, lastCellNum);
                for (StringBuilder line : list) {
                    // 获取时间值
                    if (line.toString().startsWith("期间")) {
                        time = line.toString().split(",", -1)[0].split("-", -1)[1].replace(".", "-");
                    }
                    // 过滤表头
                    if (line.toString().startsWith("摘要") || line.toString().startsWith("科目编码")) {
                        OFF = 1;
                    }
                    if (line.toString().startsWith("总计")) {
                        OFF = 0;
                    }
                    if (OFF == 1 && !line.toString().startsWith("摘要") && !line.toString().startsWith("科目编码")) {
                        // 去除第一个字段
                        String disposeLine = line.toString().replaceFirst(line.toString().split(",", -1)[0].concat(","), "");
                        // 追加字段
                        disposeLine = disposeLine.concat(",").concat(province).concat(",").concat(type).concat(",").concat(time);
                        // 过滤掉`摘要`（excel原文件中的第一个字段）不为空的数据
                        if (line.toString().split(",", -1)[0].trim().equals("")) {
                            if (type.equals("收入")) {
                                // 在`部门档案名称`后面加两个空值（为了保证数据格式跟数据库对应上）
                                disposeLine = disposeLine.replace(disposeLine.split(",", -1)[2].concat(","), disposeLine.split(",", -1)[2].concat(",").concat(",").concat(","));
                                rawDataList.add(disposeLine.concat(","));
                            } else if (type.equals("成本")) {
                                // 在`成本投入市场类型名称`后面加一个空值（为了保证数据格式跟数据库对应上）
                                disposeLine = disposeLine.replace(disposeLine.split(",", -1)[4].concat(","), disposeLine.split(",", -1)[4].concat(",").concat(","));
                                rawDataList.add(disposeLine.concat(","));
                            }
                        }
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

    public static String readText(File file) {

        String result = "";
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
                        String s = null;
                        while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                                 result = result + "\n" +s;
                             }
                        br.close();
                     }catch(Exception e){
                        e.printStackTrace();
            }
        return result;
    }
}