package com.srz.project.method;

import com.srz.project.utility.ExcelUtil;
import com.srz.project.utility.SHCompanyList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ReadSHRosterData {
    // 装载支撑数据
    static CopyOnWriteArrayList<StringBuilder> rosterList = new CopyOnWriteArrayList<>();
    // 装载处理后的支撑数据
    static CopyOnWriteArrayList<String> disposeRosterList = new CopyOnWriteArrayList<>();
    // 装载公司和省市的对应关系数据
    static List<String> companyList = new ArrayList<>();
    // 省份
    static String province = "";
    // 地市
    static String city = "";

    /**
     * 处理山东花名册数据
     *
     * @param inputPath   文件输入路径，格式；盘符://文件夹名称
     * @param sheetIndex  sheet页索引
     * @param lastCellNum 处理文件的最后一列所在位置
     * @param time        `统计时间`
     * @return CopyOnWriteArrayList<String>
     */
    public static CopyOnWriteArrayList<String> readSHRosterData(String inputPath, int sheetIndex, int lastCellNum, String time) {
        try {
            // 存储上海中`2级部门`与`所属地市`的对应关系
            SHCompanyList.sHCompanyList(companyList);
            // 省份
            province = companyList.get(0).split("\\|")[1];
            rosterList = (CopyOnWriteArrayList<StringBuilder>) ExcelUtil.excelUtil(inputPath, sheetIndex, lastCellNum);
            for (StringBuilder line : rosterList) {
                // 所属地市
                city = line.toString().split("\\|")[2];
                // 过滤表头
                if (line.toString().startsWith("所属省份"))
                    continue;
                // 依次给`人员属性`赋值，将一行拆分为三列
                for (int i = 3; i < 6; i++) {
                    String disposeLine = province.concat("|").concat(line.toString().split("\\|")[1]).concat("|").concat(city);
                    if (i == 3)
                        disposeLine = disposeLine.concat("|合同制|".concat(line.toString().split("\\|")[i].split("\\.")[0].concat("|")).concat(time));
                    if (i == 4)
                        disposeLine = disposeLine.concat("|合作企业|".concat(line.toString().split("\\|")[i].split("\\.")[0].concat("|")).concat(time));
                    if (i == 5)
                        disposeLine = disposeLine.concat("|派遣|".concat(line.toString().split("\\|")[i].split("\\.")[0].concat("|")).concat(time));
                    disposeRosterList.add(disposeLine);
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
