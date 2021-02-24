package com.srz.project.method;

import com.srz.project.utility.ExcelUtil;
import com.srz.project.utility.IsNumeric;
import com.srz.project.utility.JLCompanyList;

import java.util.ArrayList;
import java.util.List;

public class DisposeJLData {

    public static List<String> disposeJLData(String inputPath, int sheetIndex, int lastCellNum, String time) {
        // 处理后的支撑中心数据
        List<String> disposeSupportDataList = new ArrayList<String>();
        // 部门档案名称数据
        List<String> departmentArchivesName = new ArrayList<String>();
        // 装载部门档案名称的数据
        JLCompanyList.jLCompanyList(departmentArchivesName);
        // 读取吉林支撑中心数据
        List<StringBuilder> supportCenterData = ExcelUtil.excelUtil(inputPath, sheetIndex, lastCellNum, 1);
        // 科目名称明细表
        List<StringBuilder> subjectNameList = ExcelUtil.excelUtil("G:\\铁通工作\\睿 交接的项目\\省份文件  SupportDataDispose 工程\\吉林数据\\科目名称.xls", 0, 3, 1);
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
        // 所属省份
        String provinceName = "吉林省";
        // 所属地市
        String cityName;
        // 支撑中心名称
        String supportName = "";
        // 开关
        boolean OFF = false;
        // 行号
        int rowNum = 1;
        for (StringBuilder line : supportCenterData) {
            // 科目余额
            String accountBalance = "0.0";
            // 直接收支利
            String directProfit = "0.0";
            // 分摊综合收支利
            String apportionProfit = "0.0";
            // 分摊原则
            String doubleInsurance = "";
            if (OFF && !line.toString().split("\\|", -1)[0].equals("161.0") && !line.toString().split("\\|", -1)[0].equals("162.0")) {
                // 索引
                int index = 3;
                // 科目编码
                String subjectCode = line.toString().split("\\|")[0];
                // 项目
                String item = line.toString().split("\\|")[1];
                for (int i = 0; i < cityList.size(); i++) {
                    // 台账小计
                    String parameterSubtotal = "0.0";
                    // 台账网服
                    String parameterWebService = "0.0";
                    // 台账工程
                    String parameterProject = "0.0";
                    // 台账市场
                    String parameterBazaar = "0.0";
                    // 台账计量
                    String parameterMeasure = "0.0";
                    // 台账其他
                    String parameterElse = "0.0";
                    // 台账综合
                    String parameterSynthesize = "0.0";
                    // 核对直接
                    String checkDirectly = "0.0";
                    // 核对综合
                    String checkSynthesize = "0.0";
                    // 合计小计
                    String aggregateSubtotal = "0.0";
                    // 合计直接收支利
                    String aggregateDirectProfit = "0.0";
                    // 合计分摊综合收支利
                    String aggregateApportionProfit = "0.0";
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
                    for (int k = lastIndex; k < index; k++) {
                        if (line.toString().split("\\|", -1)[0].contains("."))
                            continue;
                        if (k == index - 1)
                            commonField = commonField.concat(line.toString().split("\\|", -1)[k]);
                        else
                            commonField = commonField.concat(line.toString().split("\\|", -1)[k]).concat("|");
                    }
                    // 台账开始索引
                    int parameterStartIndex = 0;
                    // 台账结束索引
                    int parameterEndIndex = 0;
                    // 核对开始索引
                    int checkStartIndex = 0;
                    // 核对结束索引
                    int checkEndIndex = 0;
                    // 合计开始索引
                    int aggregateStartIndex = 0;
                    // 合计结束索引
                    int aggregateEndIndex = 0;
                    // 获取特殊支撑中心的开始、结束索引的位置
                    for (int fieldNum = 0; fieldNum < supportName.split("\\|", -1).length; fieldNum++) {
                        // 台账
                        if (supportName.split("\\|", -1)[fieldNum].contains("收支利台帐合计（地市+工程）"))
                            parameterStartIndex = fieldNum;
                        if (supportName.split("\\|", -1)[fieldNum].equals("核对"))
                            parameterEndIndex = fieldNum;
                        // 核对
                        if (supportName.split("\\|", -1)[fieldNum].equals("核对"))
                            checkStartIndex = fieldNum;
                        if (supportName.split("\\|", -1)[fieldNum].contains("年合计"))
                            checkEndIndex = fieldNum;
                        // 合计
                        if (supportName.split("\\|", -1)[fieldNum].contains("年合计"))
                            aggregateStartIndex = fieldNum;
                        if (supportName.split("\\|", -1)[fieldNum].contains(cityName.replace("市", "")))
                            aggregateEndIndex = fieldNum;
                    }
                    // 获取2020年收支利台帐合计（地市+工程）各个字段的值
                    for (int num = parameterStartIndex; num < parameterEndIndex; num++) {
                        if (num == parameterStartIndex)
                            parameterSubtotal = commonField.split("\\|", -1)[num];
                        if (num == parameterStartIndex + 1)
                            parameterWebService = commonField.split("\\|", -1)[num];
                        if (num == parameterStartIndex + 2)
                            parameterProject = commonField.split("\\|", -1)[num];
                        if (num == parameterStartIndex + 3)
                            parameterBazaar = commonField.split("\\|", -1)[num];
                        if (num == parameterStartIndex + 4)
                            parameterMeasure = commonField.split("\\|", -1)[num];
                        if (num == parameterStartIndex + 5)
                            parameterElse = commonField.split("\\|", -1)[num];
                        if (num == parameterStartIndex + 6)
                            parameterSynthesize = commonField.split("\\|", -1)[num];
                    }
                    if (parameterSubtotal.trim().equals(""))
                        parameterSubtotal = "0.0";
                    if (parameterWebService.trim().equals(""))
                        parameterWebService = "0.0";
                    if (parameterProject.trim().equals(""))
                        parameterProject = "0.0";
                    if (parameterBazaar.trim().equals(""))
                        parameterBazaar = "0.0";
                    if (parameterMeasure.trim().equals(""))
                        parameterMeasure = "0.0";
                    if (parameterElse.trim().equals(""))
                        parameterElse = "0.0";
                    if (parameterSynthesize.trim().equals(""))
                        parameterSynthesize = "0.0";
                    String str = provinceName.concat("|").concat(cityName).concat("|");
                    if (!item.equals(""))
                        disposeSupportDataList.add(str.concat("收支利台帐合计（地市+工程）").concat("|").concat("0.0").concat("|").concat("0.0").concat("|").concat("0.0").concat("|").concat(type).concat("|").concat(time).concat("|").concat(item).concat("|").concat(parameterSubtotal).concat("|").concat(parameterWebService).concat("|").concat(parameterProject).concat("|").concat(parameterBazaar).concat("|").concat(parameterMeasure).concat("|").concat(parameterElse).concat("|").concat(parameterSynthesize).concat("|0.0|0.0|0.0|0.0|0.0").concat("|").concat(String.valueOf(rowNum)).concat("|").concat(doubleInsurance));
                    // 核对
                    for (int num = checkStartIndex; num < checkEndIndex; num++) {
                        if (num == checkStartIndex)
                            checkDirectly = commonField.split("\\|", -1)[num];
                        if (num == checkStartIndex + 1)
                            checkSynthesize = commonField.split("\\|", -1)[num];
                    }
                    if (!IsNumeric.isNumber(checkDirectly) || checkDirectly.trim().equals(""))
                        checkDirectly = "0.0";
                    if (!IsNumeric.isNumber(checkSynthesize) || checkSynthesize.trim().equals(""))
                        checkSynthesize = "0.0";
                    if (!item.equals(""))
                        disposeSupportDataList.add(str.concat("核对").concat("|").concat("0.0").concat("|").concat("0.0").concat("|").concat("0.0").concat("|").concat(type).concat("|").concat(time).concat("|").concat(item).concat("|").concat("0.0|0.0|0.0|0.0|0.0|0.0|0.0|").concat(checkDirectly).concat("|").concat(checkSynthesize).concat("|").concat("0.0|0.0|0.0|").concat(String.valueOf(rowNum)).concat("|").concat(doubleInsurance));
                    // 2020年合计
                    for (int num = aggregateStartIndex; num < aggregateEndIndex; num++) {
                        if (num == aggregateStartIndex)
                            aggregateSubtotal = commonField.split("\\|", -1)[num];
                        if (num == aggregateStartIndex + 1)
                            aggregateDirectProfit = commonField.split("\\|", -1)[num];
                        if (num == aggregateStartIndex + 2)
                            aggregateApportionProfit = commonField.split("\\|", -1)[num];
                    }
                    if (!IsNumeric.isNumber(aggregateSubtotal) || aggregateSubtotal.trim().equals(""))
                        aggregateSubtotal = "0.0";
                    if (!IsNumeric.isNumber(aggregateDirectProfit) || aggregateDirectProfit.trim().equals(""))
                        aggregateDirectProfit = "0.0";
                    if (!IsNumeric.isNumber(aggregateApportionProfit) || aggregateApportionProfit.trim().equals(""))
                        aggregateApportionProfit = "0.0";
                    if (!item.equals(""))
                        disposeSupportDataList.add(str.concat("合计").concat("|").concat("0.0").concat("|").concat("0.0").concat("|").concat("0.0").concat("|").concat(type).concat("|").concat(time).concat("|").concat(item).concat("|").concat("0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|").concat(aggregateSubtotal).concat("|").concat(aggregateDirectProfit).concat("|").concat(aggregateApportionProfit).concat("|").concat(String.valueOf(rowNum)).concat("|").concat(doubleInsurance));
                    // 处理支撑中心数据
                    for (int supportNameNum = 0; supportNameNum < (commonField.split("\\|", -1).length - 15) / 3; supportNameNum++) {
                        String name = supportName;
                        name = name.split("\\|", -1)[15 + (supportNameNum * 3)];
                        // 获取支撑中心名称
                        for (String archivesName : departmentArchivesName) {
                            if (archivesName.startsWith(name))
                                name = archivesName.split("\\|", -1)[1];
                        }
                        // 科目余额
                        accountBalance = commonField.split("\\|", -1)[15 + (supportNameNum * 3)];
                        // 直接收支利
                        directProfit = commonField.split("\\|", -1)[15 + (supportNameNum * 3) + 1];
                        // 分摊综合收支利
                        apportionProfit = commonField.split("\\|", -1)[15 + (supportNameNum * 3) + 2];
                        if (accountBalance.trim().equals("") || !IsNumeric.isNumber(accountBalance))
                            accountBalance = "0.00";
                        if (directProfit.trim().equals("") || !IsNumeric.isNumber(directProfit))
                            directProfit = "0.00";
                        if (apportionProfit.trim().equals("") || !IsNumeric.isNumber(apportionProfit))
                            apportionProfit = "0.00";
                        // zczx_jilin
                        if (!name.contains("*") && !item.equals(""))
                            disposeSupportDataList.add(str.concat(name).concat("|").concat(String.valueOf(Double.parseDouble(accountBalance))).concat("|").concat(String.valueOf(Double.parseDouble(directProfit))).concat("|").concat(String.valueOf(Double.parseDouble(apportionProfit))).concat("|").concat(type).concat("|").concat(time).concat("|").concat(item).concat("|").concat("0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0").concat("|").concat(String.valueOf(rowNum)).concat("|").concat(doubleInsurance));
                    }
                }
                rowNum++;
            }
            // 过滤表头
            if (line.toString().startsWith("|||小计|网服|工程"))
                OFF = true;
            if (line.toString().startsWith("|二十、净利润"))
                OFF = false;
        }
        return disposeSupportDataList;
    }
}