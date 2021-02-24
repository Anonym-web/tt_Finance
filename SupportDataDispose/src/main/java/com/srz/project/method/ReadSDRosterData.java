package com.srz.project.method;

import com.srz.project.utility.ExcelUtil;
import com.srz.project.utility.SDCompanyList;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ReadSDRosterData {
    // 装载支撑数据
    static CopyOnWriteArrayList<StringBuilder> rosterList = new CopyOnWriteArrayList<>();
    // 装载处理后的支撑数据
    static CopyOnWriteArrayList<String> disposeRosterList = new CopyOnWriteArrayList<>();
    // 装载公司和省市的对应关系数据
    static List<String> companyList = new ArrayList<>();
    // 人员属性
    static String personnelAttribute = "";
    // 所属省份
    static String province = "";
    // 所属地市
    static String city = "";
    // 开关
    static int OFF = 0;

    /**
     * 处理山东花名册数据
     *
     * @param inputPath   文件输入路径，格式；盘符://文件夹名称
     * @param sheetIndex  sheet页索引
     * @param lastCellNum 处理文件的最后一列所在位置
     * @return CopyOnWriteArrayList<String>
     */
    public static CopyOnWriteArrayList<String> readRosterData(String inputPath, int sheetIndex, int lastCellNum, String time) {
        try {
            // 存储山东省中`2级部门`与`所属地市`的对应关系
            SDCompanyList.sDFiliale(companyList);
            // 省份
            province = companyList.get(0).split("\\|", -1)[1];
            // 存储支撑数据
            rosterList = (CopyOnWriteArrayList<StringBuilder>) ExcelUtil.excelUtil(inputPath, sheetIndex, lastCellNum);
            for (StringBuilder line : rosterList) {

                // 过滤表头
                if (line.toString().startsWith("2级部门"))
                    OFF = 1;
                if (line.toString().startsWith("(空白)") || line.toString().startsWith("总计"))
                    OFF = 0;
                if (OFF == 1 && !line.toString().startsWith("2级部门")) {
                    // 处理字段
                    String disposeLine;
                    // 共享字段
                    String shareLine;
                    // 过滤表尾
                    if (line.toString().split("\\|", -1)[0].equals("滨州分公司 汇总")) {
                        break;
                    }
                    // 判断`2级部门`的值是否为空。如果为空，则走else里的逻辑
                    if (!line.toString().split("\\|", -1)[0].equals("")) {
                        // 过滤掉`2级部门`包含`汇总`的数据
                        if (line.toString().split("\\|", -1)[0].contains("汇总")) {
                            continue;
                        }
                        // 将原数据前添加一个省份字段
                        disposeLine = province.concat("|").concat(line.toString());
                    } else {
                        disposeLine = province.concat("|").concat(city).concat(line.toString());
                    }
                    // 如果`4级部门`为空，则将`4级部门`的值替换为`其他`
                    if (disposeLine.split("\\|", -1)[2].equals("")) {
                        shareLine = disposeLine.split("\\|", -1)[0].concat("|").concat(disposeLine.split("\\|", -1)[1]).concat("|").concat("其他").concat("|");
                        // 如果`4级部门`的值的长度为2，则在`4级部门`的值后面添加`支撑服务中心`
                    } else if (disposeLine.split("\\|", -1)[2].length() == 2) {
                        shareLine = disposeLine.split("\\|", -1)[0].concat("|").concat(disposeLine.split("\\|", -1)[1]).concat("|").concat(disposeLine.split("\\|", -1)[2]).concat("支撑服务中心").concat("|");
                    } else {
                        shareLine = disposeLine.split("\\|", -1)[0].concat("|").concat(disposeLine.split("\\|", -1)[1]).concat("|").concat(disposeLine.split("\\|", -1)[2]).concat("|");
                    }
                    for (String company : companyList) {
                        // 获取`所属地市`字段的值
                        if (company.startsWith(shareLine.split("\\|", -1)[1])) {
                            shareLine = shareLine.replace(shareLine.split("\\|", -1)[1], company.split("\\|", -1)[1]);
                            city = shareLine.split("\\|", -1)[1];
                            break;
                        }
                    }
                    // 依次给`人员属性`赋值，将一行拆分为三列
                    for (int i = 3; i < 6; i++) {
                        // 依次获取`合同制`，`合作企业`，`派遣`三列的值
                        personnelAttribute = disposeLine.split("\\|", -1)[i];
                        // 如果值为小数，则转换为整数（仅保留小数点前面的值）
                        if (personnelAttribute.contains("."))
                            personnelAttribute = personnelAttribute.split("\\.")[0];
                        else
                            personnelAttribute = "0";
                        if (i == 3)
                            disposeRosterList.add(shareLine.concat("合同制").concat("|").concat(personnelAttribute).concat("|").concat(time));
                        else if (i == 4)
                            disposeRosterList.add(shareLine.concat("合作企业").concat("|").concat(personnelAttribute).concat("|").concat(time));
                        else
                            disposeRosterList.add(shareLine.concat("派遣").concat("|").concat(personnelAttribute).concat("|").concat(time));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("代码执行完成!");
        }
        return disposeRosterList;
    }
}