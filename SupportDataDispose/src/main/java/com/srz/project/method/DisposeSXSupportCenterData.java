package com.srz.project.method;

import com.srz.project.utility.IsNumeric;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DisposeSXSupportCenterData {
    // 存储处理后的支撑数据
    static CopyOnWriteArrayList<String> disposeList = new CopyOnWriteArrayList<String>();
    // 部门档案名称
    static String departmentFileName;
    // 科目名称
    static String courseTitle;
    // 方向
    static String direction;
    // 期末余额
    static Double endingBalance = 0.0;
    // 省份
    static String province = "陕西省";
    // 地市
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
     * 再次处理陕西财务数据
     *
     * @param rawDataList 初步处理后的陕西财务数据集合
     * @return CopyOnWriteArrayList<String>
     */
    public static CopyOnWriteArrayList<String> disposeSXSupportCenterData(List<String> rawDataList) {
        for (String line : rawDataList) {
            // 对`主体账簿名称`的值做特殊处理
            if (line.split("\\|", -1)[0].equals("中移建设有限公司陕西分公司"))
                city = "中移建设";
            else if (line.split("\\|", -1)[0].equals("中移铁通有限公司陕西本级"))
                city = "本级";
            else
                city = line.split("\\|", -1)[0].replace("中移铁通有限公司", "").substring(0, 2).concat("市");
            // 部门档案名称
            departmentFileName = line.split("\\|", -1)[2];
            // 科目名称
            courseTitle = line.split("\\|", -1)[1];
            // 方向
            direction = "|";
            // 成本费用类型
            costType = line.split("\\|", -1)[3];
            // 成本投入市场类型
            costMarketType = line.split("\\|", -1)[4];
            // 客户类型
            clientType = line.split("\\|", -1)[5];
            // 如果`期末余额`字段的值不是数字，则默认为空
            if (!IsNumeric.isNumber(line.split("\\|", -1)[13]))
                endingBalance = 0.0;
            else
                endingBalance = Double.parseDouble(line.split("\\|", -1)[13]);
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
            disposeList.add(province.concat("|").concat(city).concat("|").concat(departmentFileName).concat("|").concat(courseTitle).concat("|").concat(courseTitleLine.toString()).concat("|").concat(costType).concat("|").concat(costMarketType).concat("|").concat(clientType).concat("|").concat(endingBalance.toString()).concat("|0.0|0.0|").concat(type).concat("|").concat(time).concat("|").concat(typeDetail).concat("||"));
            courseTitleLine.delete(0, courseTitleLine.length() - 1);
        }
        return disposeList;
    }
}
