package com.srz.project.method;

import com.srz.project.utility.IsNumeric;
import com.srz.project.utility.SHCompanyList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DisposeSHSupportCenterData {
    // 存储处理后的支撑数据
    static CopyOnWriteArrayList<String> disposeList = new CopyOnWriteArrayList<>();
    // 装载公司和省市的对应关系数据
    static List<String> companyList = new ArrayList<>();
    // 部门档案名称
    static String departmentFileName;
    // 科目名称
    static String courseTitle;
    // 方向
    static String direction;
    // 期末余额
    static Double endingBalance = 0.0;
    // 所属省份
    static String province;
    // 所属地市
    static String city;
    // 类型（成本、收入）
    static String type;
    // 成本费用类型
    static String costType;
    // 成本投入市场类型
    static String costMarketType;
    // 客户类型
    static String clientType;
    // 统计月份
    static String time;
    // 类型明细
    static String typeDetail;

    /**
     * 再次处理上海财务数据
     *
     * @param rawDataList 初步处理后的山东财务数据集合
     * @return CopyOnWriteArrayList<String>
     */
    public static CopyOnWriteArrayList<String> disposeSHSupportCenterData(List<String> rawDataList) {
        // 装载上海市中`部门档案名称`与`所属地市`的对应关系
        SHCompanyList.sHCompanyList(companyList);
        // 获取`所属省份`的值
        province = companyList.get(0).split("\\|", -1)[1];
        for (String line : rawDataList) {
            // 所属地市
            city = line.split("\\|", -1)[0];
            // 部门档案名称
            departmentFileName = line.split("\\|", -1)[2];
            // 科目名称
            courseTitle = line.split("\\|", -1)[1];
            // 方向
            direction = line.split("\\|", -1)[12];
            // 成本费用类型
            costType = line.split("\\|", -1)[3];
            // 成本投入市场类型
            costMarketType = line.split("\\|", -1)[4];
            // 客户类型
            clientType = line.split("\\|", -1)[5];
            // 如果`期末余额`为空，则默认为0
            if (!IsNumeric.isNumber(line.split("\\|", -1)[13])) {
                endingBalance = 0.0;
            } else {
                endingBalance = Double.parseDouble(line.split("\\|", -1)[13]);
            }
            // 类型（成本、收入）
            type = line.split("\\|", -1)[15];
            // 统计月份
            time = line.split("\\|", -1)[16];
            // 类型明细
            typeDetail = line.split("\\|", -1)[17];
            // 将`科目名称`字段的值划分为5级
            StringBuilder courseTitleLine = new StringBuilder();
            for (int i = 0; i < courseTitle.split("\\\\", -1).length; i++) {
                if (i != courseTitle.split("\\\\", -1).length - 1) {
                    courseTitleLine.append(courseTitle.split("\\\\", -1)[i].concat("|"));
                } else {
                    courseTitleLine.append(courseTitle.split("\\\\", -1)[i]);
                    for (int k = 0; k < 6 - courseTitle.split("\\\\", -1).length; k++) {
                        courseTitleLine.append("|");
                    }
                }
            }
            // 按照`期末余额`的借贷关系分别处理
            if (courseTitle.split("\\\\", -1)[0].substring(0, 2).equals("60")) {
                if (direction.equals("借"))
                    endingBalance = endingBalance * -1;
            } else if (courseTitle.split("\\\\", -1)[0].substring(0, 2).equals("61") || courseTitle.split("\\\\", -1)[0].substring(0, 2).equals("63") || courseTitle.split("\\\\", -1)[0].substring(0, 2).equals("64") || courseTitle.split("\\\\", -1)[0].substring(0, 2).equals("66") || courseTitle.split("\\\\", -1)[0].substring(0, 2).equals("67")) {
                if (direction.equals("贷"))
                    endingBalance = endingBalance * -1;
            }
            disposeList.add(province.concat("|").concat(city).concat("|").concat(departmentFileName).concat("|").concat(courseTitle).concat("|").concat(courseTitleLine.toString()).concat("|").concat(costType).concat("|").concat(costMarketType).concat("|").concat(clientType).concat("|").concat(endingBalance.toString()).concat("|0.0|0.0|").concat(type).concat("|").concat(time).concat("|").concat(typeDetail).concat("||"));
            courseTitleLine.delete(0, courseTitleLine.length() - 1);
        }
        return disposeList;
    }
}
