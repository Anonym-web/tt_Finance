package com.srz.project.method;

import com.srz.project.utility.IsNumeric;
import com.srz.project.utility.ReadMysql;

import java.util.ArrayList;
import java.util.List;

/*
* 二次处理，添加后面的六个字段
* */
public class SecondaryDisposeData {
    public static List<String> secondaryDisposeData(List<String> disposeList) {
        // 处理后的数据
        List<String> secondaryDisposeList = new ArrayList<String>();
        // 省级数据
        List<String> provinceData = ReadMysql.readMysql("Model_Provinces", 2);
        // 市级数据
        List<String> cityData = ReadMysql.readMysql("Model_Cities", 3);
        // 区域级数据
        List<String> areaData = ReadMysql.readMysql("Model_Areas", 4);
        for (String line : disposeList) {
            // 事由
            String cause = line.split("\\|", -1)[4];
            if (cause.contains("学习") || cause.contains("培训"))
                line = line.concat("|是");
            else
                line = line.concat("|");
            // 公司名称（必要时可以通过公司名称来提取出发地点）
            String companyName = line.split("\\|", -1)[2].trim();
            // 出发地
            String starting = line.split("\\|", -1)[7].trim();
            // 目的地
            String destination = line.split("\\|", -1)[8].trim();
            // 出发地级别
            String startingLevel = "";
            // 目的地级别
            String destinationLevel = "";
            if (starting.equals("") || starting.equals("~") || destination.equals("") || destination.equals("~"))
                line = line.concat("||||||");
            else {
                // 处理出发地和目的地字段
                starting = companyName.split("分公司", -1)[0].substring(companyName.split("分公司", -1)[0].length() - 2);
                if (starting.contains("湖北"))
                    starting = "武汉";
                destination = DisposeField.disposeField(destination, companyName, 1);
                // 获取出发地和目的地的级别
                startingLevel = DisposeField.getField(areaData, cityData, provinceData, starting, startingLevel, companyName);
                destinationLevel = DisposeField.getField(areaData, cityData, provinceData, destination, destinationLevel, companyName);
                boolean boo = startingLevel.substring(0, 2).equals(destinationLevel.substring(0, 2));
                if (boo && destinationLevel.length() == 4 && destinationLevel.substring(2, 4).equals("01"))
                    line = line.concat("|省内省会");
                else if ((destinationLevel.length() == 4 && destinationLevel.substring(2, 4).equals("01")) || (destinationLevel.equals("11") || destinationLevel.equals("12") || destinationLevel.equals("31") || destinationLevel.equals("50")))
                    if (line.split("\\|", -1)[4].contains("审计"))
                        line = line.concat("|审计省外");
                    else
                        line = line.concat("|一般省会/直辖市");
                else if (!boo)
                    if (line.split("\\|", -1)[4].contains("审计"))
                        line = line.concat("|审计省外");
                    else
                        line = line.concat("|省外出差");
                else if (line.split("\\|", -1)[24].equals("是"))
                    line = line.concat("|省内培训");
                else if ((destinationLevel.length() == 6 && startingLevel.length() >= 4) && startingLevel.substring(0, 4).equals(destinationLevel.substring(0, 4)))
                    line = line.concat("|市内区县");
                else if (destinationLevel.length() == 6)
                    line = line.concat("|省内其他市县");
                else if (destinationLevel.length() == 4)
                    line = line.concat("|省内地市级");
                // 天数误差值
                if (IsNumeric.isNumber(line.split("\\|", -1)[18]) && IsNumeric.isNumber(line.split("\\|", -1)[23]))
                    line = line.concat("|").concat(String.valueOf(Double.parseDouble(line.split("\\|", -1)[23]) - Double.parseDouble(line.split("\\|", -1)[18])));
                else
                    line = line.concat("|");
                // 实际补助标准
                if (line.split("\\|", -1)[25].equals("省内省会"))
                    line = line.concat("|80");
                else if (line.split("\\|", -1)[25].equals("审计省外"))
                    line = line.concat("|120");
                else if (line.split("\\|", -1)[25].equals("一般省会/直辖市"))
                    line = line.concat("|100");
                else if (line.split("\\|", -1)[25].equals("省外出差"))
                    line = line.concat("|100");
                else if (line.split("\\|", -1)[25].equals("省内培训"))
                    line = line.concat("|0");
                else if (line.split("\\|", -1)[25].equals("市内区县"))
                    line = line.concat("|50");
                else if (line.split("\\|", -1)[25].equals("省内其他市县"))
                    line = line.concat("|80");
                else if (line.split("\\|", -1)[25].equals("省内地市级"))
                    line = line.concat("|80");
                else
                    line = line.concat("|");
                // 出差标准误差
                if (IsNumeric.isNumber(line.split("\\|", -1)[17]) && IsNumeric.isNumber(line.split("\\|", -1)[27]))
                    if (Double.parseDouble(line.split("\\|", -1)[17]) - Double.parseDouble(line.split("\\|", -1)[27]) == 0)
                        line = line.concat("|0");
                    else if (Double.parseDouble(line.split("\\|", -1)[17]) - Double.parseDouble(line.split("\\|", -1)[27]) > 0)
                        line = line.concat("|").concat(String.valueOf(Double.parseDouble(line.split("\\|", -1)[17]) - Double.parseDouble(line.split("\\|", -1)[27])));
                    else
                        line = line.concat("|").concat(String.valueOf((Double.parseDouble(line.split("\\|", -1)[17]) - Double.parseDouble(line.split("\\|", -1)[27])) * -1));
                else
                    line = line.concat("|");
                // 补助差值
                if (IsNumeric.isNumber(line.split("\\|", -1)[19]) && IsNumeric.isNumber(line.split("\\|", -1)[23]) && IsNumeric.isNumber(line.split("\\|", -1)[27]) && IsNumeric.isNumber(line.split("\\|", -1)[20]))
                    line = line.concat("|").concat(String.valueOf((Double.parseDouble(line.split("\\|", -1)[19]) * Double.parseDouble(line.split("\\|", -1)[23]) * Double.parseDouble(line.split("\\|", -1)[27])) - Double.parseDouble(line.split("\\|", -1)[20])));
                else
                    line = line.concat("|");
                // 警告
                if (IsNumeric.isNumber(line.split("\\|", -1)[29]) && Double.parseDouble(line.split("\\|", -1)[29]) < 0)
                    line = line.concat("|重点");
                else
                    line = line.concat("|");
            }
            secondaryDisposeList.add(line);
        }
        return secondaryDisposeList;
    }
}
