package com.srz.project.method;

import com.srz.project.utility.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ReadSHSupportCenterData {
    // 装载支撑数据
    static CopyOnWriteArrayList<StringBuilder> list = new CopyOnWriteArrayList<>();
    // 装载处理后的支撑数据
    static CopyOnWriteArrayList<String> rawDataList = new CopyOnWriteArrayList<>();
    // 装载公司和省市的对应关系数据
    static List<String> companyList = new ArrayList<>();
    // 存储文件路径
    static List<String> fileList = new ArrayList<>();
    // 存储省市级数据
    static List<String> provincesList = new ArrayList<>();
    // 所属省份
    static String province;
    // 所属地市
    static String city;
    // 所属类型
    static String type;
    // 统计时间
    static String time;
    // 类型明细
    static String typeDetail;

    /**
     * 对上海财务数据做初步处理
     *
     * @param inputPath   文件输入路径，格式；盘符://文件夹名称
     * @param sheetIndex  sheet页索引
     * @param lastCellNum 处理文件的最后一列所在位置（该方法中对lastCellNum做了特殊处理，默认成本类型文件为14列，收入类型为13列）
     * @return CopyOnWriteArrayList<String>
     */
    public static CopyOnWriteArrayList<String> readSHSupportCenterData(String inputPath, int sheetIndex, int lastCellNum) {
            try {
            // 装载文件路径
            GetFilePath.getFilePath(new File(inputPath), fileList);
            for (String fileName : fileList) {
                // 开关
                int OFF = 0;
                // 如果文件路径用\\分割，则将\\变为/
                fileName = fileName.replace("\\", "/");
                // 截取文件名获取类型
                type = fileName.split("/", -1)[fileName.split("/", -1).length - 1].substring(2, 4);
                // 截取文件名获取类型明细
                typeDetail = fileName.split("/", -1)[fileName.split("/", -1).length - 1].split("-", -1)[1].split("\\.", -1)[0].concat(type);
                // 存储省级数据
                SHCompanyList.sHCompanyList(provincesList);
                // 省份
                province = provincesList.get(0).split("\\|", -1)[1];
                // 根据文件类型调整列数
                if (type.equals("成本")) {
                    lastCellNum = 13;
                } else if (type.equals("收入")) {
                    lastCellNum = 12;
                }
                // 遍历读取excel文件
                list = (CopyOnWriteArrayList<StringBuilder>) ExcelUtil.excelUtil(fileName, sheetIndex, lastCellNum);
                for (StringBuilder line : list) {
                    // 获取时间
                    if (line.toString().startsWith("期间")) {
                        time = line.toString().split("\\|", -1)[0].split("-", -1)[1].replace(".", "-");
                    }
                    // 过滤表头
                    if (line.toString().startsWith("摘要") || line.toString().startsWith("科目编码")) {
                        OFF = 1;
                    }
                    if (line.toString().startsWith("总计")) {
                        OFF = 0;
                    }
                    if (OFF == 1 && !line.toString().startsWith("摘要") && !line.toString().startsWith("科目编码")) {
                        if (line.toString().split("\\|", -1)[0] == null || line.toString().split("\\|", -1)[0].trim().equals("")) {
                            String disposeLine;
                            // 去除第一个字段
                            disposeLine = line.toString().replaceFirst(line.toString().split("\\|", -1)[0].concat("\\|"), "");
                            // 追加字段
                            disposeLine = disposeLine.concat("|").concat(province).concat("|").concat(type).concat("|").concat(time);
                            // 装载上海市中`部门档案名称`与`所属地市`的对应关系
                            SHCompanyList.sHCompanyList(companyList);
                            // 获取`所属地市`的值
                            for (String company : companyList) {
                                if (company.startsWith(disposeLine.split("\\|", -1)[1])) {
                                    city = company.split("\\|", -1)[1];
                                    break;
                                }
                            }
                            if (type.equals("收入")) {
                                disposeLine = disposeLine.replace(disposeLine.split("\\|", -1)[1].concat("|"), disposeLine.split("\\|", -1)[1].concat("|||")).concat("|").concat(typeDetail);
                                rawDataList.add(city.concat("|").concat(disposeLine));
                            } else if (type.equals("成本")) {
                                disposeLine = disposeLine.replace(disposeLine.split("\\|", -1)[3].concat("|").concat(disposeLine.split("\\|", -1)[4]), disposeLine.split("\\|", -1)[3].concat("||").concat(disposeLine.split("\\|", -1)[4])).concat("|").concat(typeDetail);
                                rawDataList.add(city.concat("|").concat(disposeLine));
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
}