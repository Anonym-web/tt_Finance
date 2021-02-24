package com.srz.project.method;

import com.srz.project.utility.IsNumeric;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DisposeProvincesData {
    // 存储处理后的支撑数据
    static CopyOnWriteArrayList<String> disposeList = new CopyOnWriteArrayList<>();
    // 部门档案名称
    static String departmentFileName = "";
    // 项目辅助核算名称
    static String projectAccounting = "";
    // 交易类型名称
    static String transactionType = "";
    // 科目名称
    static String courseTitle = "";
    // 方向
    static String direction = "";
    // 期末余额
    static Double endingBalance = 0.0;
    // 省份
    static String province = "";
    // 地市
    static String city = "";
    // 类型（成本、收入）
    static String type = "收入";
    // 成本费用类型
    static String costType = "";
    // 成本投入市场类型
    static String costMarketType = "";
    // 客户类型
    static String clientType = "";
    // 统计月份
    static String time = "";

    /**
     * 再次处理辅助余额表的数据
     *
     * @param rawDataList 初步处理辅助余额表的结果集
     * @return CopyOnWriteArrayList<String>
     */
    public static CopyOnWriteArrayList<String> disposeProvincesData(List<String> rawDataList) {
        for (String line : rawDataList) {
            // 科目名称
            courseTitle = line.split("\\|")[0];
            // 项目辅助核算名称
            projectAccounting = line.split("\\|")[1];
            // 交易类型名称
            transactionType = line.split("\\|")[2];
            // 方向
            direction = line.split("\\|")[10];
            // 省份
            province = line.split("\\|")[12];
            // 客户类型
            clientType = line.split("\\|")[3];
            // 期末余额字段为空，则补0
            if (!IsNumeric.isNumber(line.split("\\|")[11])) {
                endingBalance = 0.0;
            } else {
                endingBalance = Double.parseDouble(line.split("\\|")[11]);
            }
            // 统计月份
            time = line.split("\\|")[14];
            StringBuilder courseTitleLine = new StringBuilder();
            for (int i = 0; i < courseTitle.split("\\\\").length; i++)
                if (i != courseTitle.split("\\\\").length - 1)
                    courseTitleLine.append(courseTitle.split("\\\\")[i].concat("|"));
                else {
                    courseTitleLine.append(courseTitle.split("\\\\")[i]);
                    for (int k = 0; k < 6 - courseTitle.split("\\\\").length; k++) {
                        courseTitleLine.append("|");
                    }
                }
            // 判断借贷关系
            if (courseTitle.split("\\\\")[0].substring(0, 2).equals("60")) {
                if (direction.equals("借")) {
                    endingBalance = endingBalance * -1;
                }
            } else if (courseTitle.split("\\\\")[0].substring(0, 2).equals("64") || courseTitle.split("\\\\")[0].substring(0, 2).equals("66") || courseTitle.split("\\\\")[0].substring(0, 2).equals("67")) {
                if (direction.equals("贷")) {
                    endingBalance = endingBalance * -1;
                }
            }
            // 往集合中添加字段
            String disposeLine = province.concat("|").concat(city).concat("|").concat(departmentFileName).concat("|").concat(courseTitle).concat("|").concat(courseTitleLine.toString()).concat("|").concat(costType).concat("|").concat(costMarketType).concat("|").concat(clientType).concat("|").concat(endingBalance.toString()).concat("|||").concat(type).concat("|").concat(time).concat("||||").concat(projectAccounting).concat("|").concat(transactionType);
            disposeList.add(disposeLine.replace("\n", "").replace("\r", ""));
            // 每个循环结束后，清空该变量
            courseTitleLine.delete(0, courseTitleLine.length() - 1);
        }
        return disposeList;
    }
}
