package com.srz.project.method;

import com.srz.project.utility.HLJCompanyList;
import com.srz.project.utility.IsNumeric;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DisposeHLJSupportCenterData {
    // 存储处理后的支撑数据
    static CopyOnWriteArrayList<String> disposeList = new CopyOnWriteArrayList<>();
    // 存储二次处理后的支撑数据
    static CopyOnWriteArrayList<String> twiceDisposeList = new CopyOnWriteArrayList<>();
    // 装载公司和省市的对应关系数据
    static List<String> companyList = new ArrayList<>();
    // 部门档案名称
    static String departmentFileName = "";
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
    static String type = "";
    // 成本费用类型
    static String costType = "";
    // 成本投入市场类型
    static String costMarketType = "";
    // 客户类型
    static String clientType = "";
    // 统计月份
    static String time = "";

    /**
     * 再次处理黑龙江财务数据
     *
     * @param rawDataList 初步处理后的黑龙江财务数据集合
     * @return CopyOnWriteArrayList<String>
     */
    public static CopyOnWriteArrayList<String> disposeHLJSupportCenterData(List<String> rawDataList) {
        // 存储黑龙江省中`部门档案名称`与`所属地市`的对应关系
        HLJCompanyList.hLJCompanyList(companyList);
        // 获取省份
        province = companyList.get(0).split("\\|", -1)[1];
        // 循环初步处理的每一条数据
        for (String line : rawDataList) {
            // 获取`地市`字段的值
            for (String company : companyList) {
                if (company.startsWith(line.split("\\|", -1)[0])) {
                    city = company.split("\\|", -1)[1];
                    break;
                }
            }
            // 部门档案名称
            departmentFileName = line.split("\\|", -1)[2];
            // 科目名称
            courseTitle = line.split("\\|", -1)[1];
            // 方向
            direction = line.split("\\|", -1)[12];
            // 成本费用类型
            costType = line.split("\\|", -1)[3];
            //  成本投入市场类型
            costMarketType = line.split("\\|", -1)[4];
            // 客户类型
            clientType = line.split("\\|", -1)[5];
            // 期末余额（如果为空，则默认为0）
            if (!IsNumeric.isNumber(line.split("\\|", -1)[13])) {
                endingBalance = 0.0;
            } else {
                endingBalance = Double.parseDouble(line.split("\\|", -1)[13]);
            }
            // 类型（成本、收入）
            type = line.split("\\|", -1)[15];
            // 统计月份
            time = line.split("\\|", -1)[16];
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
            // 通过借贷关系去判断`期末余额`的正负
            // 可以简单理解：如果`科目编号`是以60开头的，那么借负贷正；否则借正贷负
            if (courseTitle.split("\\\\", -1)[0].substring(0, 2).equals("60")) {
                if (direction.equals("借")) {
                    endingBalance = endingBalance * -1;
                }
            } else if (courseTitle.split("\\\\", -1)[0].substring(0, 2).equals("64") || courseTitle.split("\\\\", -1)[0].substring(0, 2).equals("66") || courseTitle.split("\\\\", -1)[0].substring(0, 2).equals("67")) {
                if (direction.equals("贷")) {
                    endingBalance = endingBalance * -1;
                }
            }
            // 拼接字段
            disposeList.add(province.concat("|").concat(city).concat("|").concat(departmentFileName).concat("|").concat(courseTitle).concat("|").concat(courseTitleLine.toString()).concat("|").concat(costType).concat("|").concat(costMarketType).concat("|").concat(clientType).concat("|").concat(endingBalance.toString()).concat("|").concat(type).concat("|").concat(time));
            // 清空值
            courseTitleLine.delete(0, courseTitleLine.length() - 1);
        }
        /*
         * 对处理后的值做特殊处理
         * 如果`部门档案名称`的值中包含`支撑服务中心`，则不对本条数据进行处理
         * 如果`所属地市`的值为`齐齐哈尔市`，而且`部门档案名称`的值中包含`电信局`，则不对本条数据进行处理
         * 如果`所属地市`的值为`佳木斯市`，而且`部门档案名称`的值中包含`支撑中心`，则不对本条数据进行处理
         * 除了上面的三种类型的数据不作处理外，其他数据的`部门档案名称`的值为`机关及其他部门`
         */
        for (String line : disposeList) {
            if (line.split("\\|", -1)[2].contains("支撑服务中心")) {
                twiceDisposeList.add(line.concat("|"));
            } else if (line.split("\\|", -1)[1].equals("齐齐哈尔市")) {
                if (line.split("\\|", -1)[2].contains("电信局")) {
                    twiceDisposeList.add(line.concat("|"));
                } else {
                    twiceDisposeList.add(line.replace(line.split("\\|", -1)[2], "机关及其他部门").concat("|"));
                }
            } else if (line.split("\\|", -1)[1].equals("佳木斯市")) {
                if (line.split("\\|", -1)[2].contains("支撑中心")) {
                    twiceDisposeList.add(line.concat("|"));
                } else {
                    twiceDisposeList.add(line.replace(line.split("\\|", -1)[2], "机关及其他部门").concat("|"));
                }
            } else {
                twiceDisposeList.add(line.replace(line.split("\\|", -1)[2], "机关及其他部门").concat("|"));
            }
        }
        return twiceDisposeList;
    }
}
