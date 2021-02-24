package com.srz.project.method;

import com.srz.project.utility.ExcelUtil;
import com.srz.project.utility.GetFilePath;
import com.srz.project.utility.JLCompanyList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadJLRosterData {
    public List<String> readJLRosterData(String inputPath, String time) {
        // 存储公司和省市的对应关系数据
        List<String> companyList = new ArrayList<String>();
        JLCompanyList.jLCompanyList(companyList);
        // 省份
        String province = companyList.get(0).split("\\|", -1)[1];
        // 存储处理后的花名册数据
        List<StringBuilder> rosterList;
        // 存储处理后的花名册数据
        List<String> disposeRosterList = new ArrayList<String>();
        rosterList = ExcelUtil.excelUtil(inputPath, 0, 8, 1);
        // 判断读取的行是不是表头。0是，1不是
        int OFF = 0;
        // 地市
        String city = "";
        for (StringBuilder line : rosterList) {
            // 部门档案名称
            String recordName;
            // 过滤无用行
            if (line.toString().startsWith("|合计") || line.toString().startsWith("122.0") || line.toString().startsWith(" ||"))
                OFF = 0;
            if (OFF == 1 && !line.toString().equals("|||")) {
                if (line.toString().split("\\|", -1)[1].split("-", -1)[0].length() == 2)
                    city = line.toString().split("\\|", -1)[1].split("-", -1)[0].concat("市");
                else
                    city = line.toString().split("\\|", -1)[1].split("-", -1)[0];
                if (!line.toString().split("\\|", -1)[1].split("-")[1].equals("呼叫中心") && !line.toString().split("\\|", -1)[1].split("-")[1].equals("工程部"))
                    if (!city.equals("吉林市"))
                        recordName = line.toString().split("\\|", -1)[1].replace(city.replace("市", "").concat("-"), "").concat("支撑中心");
                    else
                        recordName = line.toString().split("\\|", -1)[1].replace(city.concat("-"), "").concat("支撑中心");
                else
                    recordName = city.replace("市", "").concat(line.toString().split("\\|", -1)[1].split("-")[1]);
                String str = "";
                if (city.contains("市"))
                    str = province.concat("|").concat(city).concat("|").concat(recordName).concat("|");
                else
                    str = province.concat("|").concat(city.concat("市")).concat("|").concat(recordName).concat("|");
                if (line.toString().split("\\|", -1)[1].startsWith(city.replace("市", ""))) {
                    for (int i = 0; i < 2; i++) {
                        if (i == 0) {
                            if (line.toString().split("\\|", -1)[4].equals(""))
                                disposeRosterList.add(str.concat("合同制").concat("|").concat("0.0").concat("|").concat(time));
                            else
                                disposeRosterList.add(str.concat("合同制").concat("|").concat(String.valueOf(Math.round(Double.parseDouble(line.toString().split("\\|", -1)[4])))).concat("|").concat(time));
                        }
                        if (i == 1) {
                            if (line.toString().split("\\|", -1)[7].equals(""))
                                disposeRosterList.add(str.concat("合作企业").concat("|").concat("0.0").concat("|").concat(time));
                            else
                                disposeRosterList.add(str.concat("合作企业").concat("|").concat(String.valueOf(Math.round(Double.parseDouble(line.toString().split("\\|", -1)[7])))).concat("|").concat(time));
                        }
                    }
                }
            }
            // 过滤表头
            if (line.toString().contains("直接人员|分摊人员"))
                OFF = 1;

        }
        return disposeRosterList;
    }
}
