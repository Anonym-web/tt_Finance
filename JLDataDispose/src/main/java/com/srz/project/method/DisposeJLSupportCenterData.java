package com.srz.project.method;

import com.srz.project.utility.ExcelUtil;
import com.srz.project.utility.JLCompanyList;

import java.util.ArrayList;
import java.util.List;

public class DisposeJLSupportCenterData {

    public static List<String> disposeJLSupportCenterData(String inputPath, int sheetIndex, int lastCellNum, String time) {
        // 处理后的数据
        List<String> disposeSupportCenterData = new ArrayList<String>();
        // 读取吉林支撑中心数据
        List<StringBuilder> supportCenterData = ExcelUtil.excelUtil(inputPath, sheetIndex, lastCellNum, 10000);
        // 开关
        boolean OFF = false;
        // 所属省份
        String provinceName = "吉林省";
        // 所属地市
        String cityName;
        // 地市属性
        List<String> cityList = new ArrayList<String>();
        cityList.add("长春市|48");
        cityList.add("吉林市|51");
        cityList.add("延边市|48");
        cityList.add("四平市|36");
        cityList.add("通化市|36");
        cityList.add("白城市|33");
        cityList.add("辽源市|24");
        cityList.add("松原市|36");
        cityList.add("白山市|42");
        // 部门档案名称数据
        List<String> departmentArchivesName = new ArrayList<String>();
        // 装载部门档案名称的数据
        JLCompanyList.jLCompanyList(departmentArchivesName);
        // 科目名称明细表
        List<StringBuilder> subjectNameList = ExcelUtil.excelUtil("G:\\铁通工作\\睿 交接的项目\\省份文件  SupportDataDispose 工程\\吉林数据\\科目名称.xls", 0, 3, 1);
        // 支撑中心名称
        String supportName = "";
        // 次数
        int count = 0;
        for (StringBuilder line : supportCenterData) {
            // 过滤表头以及无用字段
            if (OFF && !line.toString().split("\\|", -1)[0].equals("161.0") && !line.toString().split("\\|", -1)[0].equals("162.0")) {
                // 索引
                int index = 3;
                // 科目编码
                String subjectCode = line.toString().split("\\|")[0];
                // 项目
                String item = line.toString().split("\\|")[1];
                for (int i = 0; i < cityList.size(); i++) {
                    if (i == 0)
                        supportName = "科目编码|项目|行号|2020年收支利台帐合计（地市+工程）|||||||核对||2020年合计|||长春-宽城|||长春-二道|||长春-绿园|||长春-南关|||长春-朝阳|||长春-农安|||长春-德惠|||长春-榆树|||长春-九台|||长春-双阳|||长春-呼叫中心|||长春-工程部|||";
                    if (i == 1)
                        supportName = "科目编码|项目|行号|2020年收支利台帐合计（地市+工程）|||||||核对||2020年合计|||吉林市-昌一|||吉林市-昌二|||吉林市-船一|||吉林市-船二|||吉林市-龙潭|||吉林市-丰满|||吉林市-永吉|||吉林市-舒兰|||吉林市-蛟河|||吉林市-桦甸|||吉林市-磐石|||吉林市-呼叫中心|||吉林市-工程部|||";
                    if (i == 2)
                        supportName = "科目编码|项目|行号|2020年收支利台帐合计（地市+工程）|||||||核对||2020年合计|||延边-河南|||延边-河北|||延边-敦化|||延边-珲春|||延边-图们|||延边-龙井|||延边-和龙|||延边-汪清|||延边-安图|||延边-白河|||延边-呼叫中心|||延边-工程部|||";
                    if (i == 3)
                        supportName = "科目编码|项目|行号|2020年收支利台帐合计（地市+工程）|||||||核对||2020年合计|||四平-双辽|||四平-公主岭|||四平-梨树|||四平-伊通|||四平-铁西|||四平-铁东|||四平-呼叫中心|||四平-工程部|||";
                    if (i == 4)
                        supportName = "科目编码|项目|行号|2020年收支利台帐合计（地市+工程）|||||||核对||2020年合计|||通化-城区|||通化-辉南|||通化-柳河|||通化-集安|||通化-通化县|||通化-梅河口|||通化-呼叫中心|||通化-工程部|||";
                    if (i == 5)
                        supportName = "科目编码|项目|行号|2020年收支利台帐合计（地市+工程）|||||||核对||2020年合计|||白城-洮北|||白城-镇赉|||白城-洮南|||白城-通榆|||白城-大安|||白城-呼叫中心|||白城-工程部|||";
                    if (i == 6)
                        supportName = "科目编码|项目|行号|2020年收支利台帐合计（地市+工程）|||||||核对||2020年合计|||辽源-城区|||辽源-东丰|||辽源-东辽|||辽源-工程部|||";
                    if (i == 7)
                        supportName = "科目编码|项目|行号|2020年收支利台帐合计（地市+工程）|||||||核对||2020年合计|||松原-江南|||松原-江北|||松原-前郭|||松原-乾安|||松原-长岭|||松原-扶余|||松原-呼叫中心|||松原-工程部|||";
                    if (i == 8)
                        supportName = "科目编码|项目|行号|2020年收支利台帐合计（地市+工程）|||||||核对||2020年合计|||白山-城东|||白山-城郊|||白山-城西|||白山-江源|||白山-临江|||白山-抚松|||白山-靖宇|||白山-长白|||白山-呼叫中心|||白山-工程部||";
                    cityName = cityList.get(i).split("\\|", -1)[0];
                    // 共享字段
                    String commonField = "|||";
                    // 记录上一次的index
                    int lastIndex = index;
                    index += Integer.parseInt(cityList.get(i).split("\\|", -1)[1]);
                    // 所属类型
                    String type = "";
                    // 通过科目编码来获取所属类型
                    if (subjectCode.split("\\|", -1)[0].length() != 0) { // 如果科目编码不为空
                        // 科目编号
                        subjectCode = subjectCode.split("\\|", -1)[0].split("\\.", -1)[0];
                        if (subjectCode.startsWith("60"))
                            type = "收入";
                        else
                            type = "成本";
                    } else // 如果科目编号为空，则去总体科目表中去查找
                        for (StringBuilder subjectNameLine : subjectNameList) {
                            if (!subjectNameLine.toString().startsWith("会计科目") && !subjectNameLine.toString().startsWith("||") && !subjectNameLine.toString().startsWith("科目编码")) {
                                if (item.trim().equals(subjectNameLine.toString().split("\\|", -1)[1].trim())) {
                                    subjectCode = subjectNameLine.toString().split("\\|", -1)[0].split("\\.", -1)[0];
                                    if (subjectCode.startsWith("60"))
                                        type = "收入";
                                    else if (subjectCode.equals(""))
                                        type = "";
                                    else
                                        type = "成本";
                                }
                            }
                        }
                    // 科目名称
                    String subjectName = "";
                    // 第一级科目名称
                    String firstSubjectName = "";
                    // 第二级科目名称
                    String secondSubjectName = "";
                    // 第三级科目名称
                    String thirdlySubjectName = "";
                    // 第四级科目名称
                    String fourthlySubjectName = "";
                    // 第五级科目名称
                    String fifthSubjectName = "";
                    for (StringBuilder subjectNameLine : subjectNameList) {
                        // 过滤表头
                        if (!subjectNameLine.toString().startsWith("会计科目") && !subjectNameLine.toString().startsWith("||") && !subjectNameLine.toString().startsWith("科目编码")) {
                            if (subjectNameLine.toString().split("\\|")[0].split("\\.")[0].equals(subjectCode)) {
                                // 科目名称
                                subjectName = subjectNameLine.toString().split("\\|", -1)[2];
                                // 获取一、二、三、四、五级科目
                                if (subjectName.split("\\\\", -1).length == 2) {
                                    firstSubjectName = subjectName.split("\\\\", -1)[1];
                                } else if (subjectName.split("\\\\", -1).length == 3) {
                                    firstSubjectName = subjectName.split("\\\\", -1)[1];
                                    secondSubjectName = subjectName.split("\\\\", -1)[2];
                                } else if (subjectName.split("\\\\", -1).length == 4) {
                                    firstSubjectName = subjectName.split("\\\\", -1)[1];
                                    secondSubjectName = subjectName.split("\\\\", -1)[2];
                                    thirdlySubjectName = subjectName.split("\\\\", -1)[3];
                                } else if (subjectName.split("\\\\", -1).length == 5) {
                                    firstSubjectName = subjectName.split("\\\\", -1)[1];
                                    secondSubjectName = subjectName.split("\\\\", -1)[2];
                                    thirdlySubjectName = subjectName.split("\\\\", -1)[3];
                                    fourthlySubjectName = subjectName.split("\\\\", -1)[4];
                                } else if (subjectName.split("\\\\", -1).length == 6) {
                                    firstSubjectName = subjectName.split("\\\\", -1)[1];
                                    secondSubjectName = subjectName.split("\\\\", -1)[2];
                                    thirdlySubjectName = subjectName.split("\\\\", -1)[3];
                                    fourthlySubjectName = subjectName.split("\\\\", -1)[4];
                                    fifthSubjectName = subjectName.split("\\\\", -1)[5];
                                }
                            }
                        }
                    }
                    for (int k = lastIndex; k < index; k++) {
                        if (line.toString().split("\\|", -1)[0].contains("."))
                            continue;
                        if (k == index - 1)
                            commonField = commonField.concat(line.toString().split("\\|", -1)[k]);
                        else
                            commonField = commonField.concat(line.toString().split("\\|", -1)[k]).concat("|");
                    }
                    // 是否为末级
                    String doubleInsurance;
                    if (supportCenterData.get(count + 1).toString().split("\\|", -1)[0].split("\\.", -1)[0].length() > subjectCode.length())
                        doubleInsurance = "否";
                    else
                        doubleInsurance = "";
                    // 处理支撑中心数据
                    for (int supportNameNum = 0; supportNameNum < (commonField.split("\\|", -1).length - 15) / 3; supportNameNum++) {
                        if (subjectCode.equals(""))
                            doubleInsurance = "否";
                        if (item.trim().equals("十六、净利润（不含利润调整项目）入)"))
                            // 分摊原则
                            doubleInsurance = "否";
                        String name = supportName.split("\\|", -1)[15 + (supportNameNum * 3)];
                        // 获取支撑中心名称
                        for (String archivesName : departmentArchivesName) {
                            if (archivesName.startsWith(name)) {
                                name = archivesName.split("\\|", -1)[1];
                                break;
                            }
                        }
                        // 科目余额
                        String accountBalance = commonField.split("\\|", -1)[15 + (supportNameNum * 3)];
                        // 直接收支利
                        String directProfit = commonField.split("\\|", -1)[15 + (supportNameNum * 3) + 1];
                        // 分摊综合收支利
                        String apportionProfit = commonField.split("\\|", -1)[15 + (supportNameNum * 3) + 2];
                        if (accountBalance.replace("-", "").equals("") || accountBalance.equals(""))
                            accountBalance = "0.0";
                        if (accountBalance.replace("-", "").equals("") || directProfit.equals(""))
                            directProfit = "0.0";
                        if (accountBalance.replace("-", "").equals("") || apportionProfit.equals(""))
                            apportionProfit = "0.0";
                        // 遇到6711则为负
                        if (subjectCode.equals("6711")) {
                            if (!accountBalance.equals("0.0"))
                                accountBalance = String.valueOf(Double.parseDouble(accountBalance) * -1);
                            if (!directProfit.equals("0.0"))
                                directProfit = String.valueOf(Double.parseDouble(directProfit) * -1);
                            if (!apportionProfit.equals("0.0"))
                                apportionProfit = String.valueOf(Double.parseDouble(apportionProfit) * -1);
                        }
                        if (item.trim().equals("4.2IDC业务收入") || item.trim().equals("十七、以前年度损益调整"))
                            doubleInsurance = "";
                        if (!name.contains("*") && !item.equals(""))
                            disposeSupportCenterData.add(provinceName.concat("|").concat(cityName).concat("|").concat(name).concat("|").concat(subjectName).concat("|").concat(subjectCode).concat("|").concat(firstSubjectName).concat("|").concat(secondSubjectName).concat("|").concat(thirdlySubjectName).concat("|").concat(fourthlySubjectName).concat("|").concat(fifthSubjectName).concat("|").concat("").concat("|").concat("").concat("|").concat("").concat("|").concat(String.valueOf(Double.parseDouble(accountBalance))).concat("|").concat(String.valueOf(Double.parseDouble(directProfit))).concat("|").concat(String.valueOf(Double.parseDouble(apportionProfit))).concat("|").concat(type).concat("|").concat(time).concat("|").concat("").concat("|").concat(item).concat("|").concat(doubleInsurance));
                    }
                    if (i == cityList.size() - 1)
                        index = 3;
                }
            }
            // 过滤表头
            if (line.toString().replace(" ","").startsWith("|||小计|网服|工程"))
                OFF = true;
            if (line.toString().startsWith("|二十、净利润"))
                OFF = false;
            count++;
        }
        return disposeSupportCenterData;
    }
}
