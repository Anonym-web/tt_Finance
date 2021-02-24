package per.srz.project.main;

import per.srz.project.method.*;

import java.io.File;

public class Client {
    /**
     * 单线程
     */
    public static void main(String[] args) {
        // 开始时间（毫秒）
        long startTime = System.currentTimeMillis();
        if (System.getProperty("os.name").split(" ", -1)[0].equals("Windows")) {
            String inputPath = "E://黑龙江财务数据";
            // 判断输入文件是否存在
            File inputFile = new File(inputPath);
            if (!inputFile.exists()) {
                System.out.println("输入文件路径不存在");
                return;
            }
            String outputPath = "E://处理后的黑龙江财务数据";
            // 判断输出文件夹是否存在
            File provinceFile = new File(outputPath.concat("/").concat("黑龙江省份"));
            if (!provinceFile.exists()) {
                provinceFile.mkdirs();
                System.out.println("省份文件夹不存在，但已为你创建省份文件输出路径");
            }
            File prefecturalFile = new File(outputPath.concat("/").concat("黑龙江地市"));
            if (!prefecturalFile.exists()) {
                prefecturalFile.mkdirs();
                System.out.println("地市文件夹不存在，但已为你创建地市文件输出路径");
            }
            String codedFormat = "utf-8";
            String date = "2019-10";
//            // KTT0401营业收入明细表
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT0401营业收入明细表 ", "栏次", 0, date);
//            // KTT0402营业收入明细表-分市场
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT0402营业收入明细表-分市场", "||辅助核算", 0, date);
//            // KTT0403营业收入明细表-中国铁路总公司
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT0403营业收入明细表-中国铁路总公司", "栏次", -1, date);
            // KTT00 公司主要指标简表（计算生成）(会铁通月00表)
            DataProcessingAssignVerticalSharding.dataProcessingAssignVerticalSharding(inputPath, outputPath, codedFormat, "KTT00 公司主要指标简表（计算生成）(会铁通月00表)", "项  目", 11, date, 5);
//            // KTT01 资产负债表(会铁通月01表)
//            DataProcessingVerticalSharding.dataProcessingVerticalSharding(inputPath, outputPath, codedFormat, "KTT01 资产负债表(会铁通月01表)", "项目", 1, date);
//            // KTT02 损益数据表(会铁通月02表)
//            DataProcessingVerticalSharding.dataProcessingVerticalSharding(inputPath, outputPath, codedFormat, "KTT02 损益数据表(会铁通月02表)", "项目", 9, date, 2, 3);
//            // KTT03 现金流量分析表(会铁通月03表)
//            DataProcessingVerticalSharding.dataProcessingVerticalSharding(inputPath, outputPath, codedFormat, "KTT03 现金流量分析表(会铁通月03表)", "项目", 2, date);
//            // KTT06 在建工程及转固情况表(会铁通月06表)
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT06 在建工程及转固情况表(会铁通月06表)", "|||金额", 12, date);
//            // KTT07 补充数据表(会铁通月07表)
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT07 补充数据表(会铁通月07表)", "税收及其他指标", 1, date);
//            // KTT08 应收、应付账款情况明细表(会铁通月08表)
//            DataProcessingLevelSharding.dataProcessingLevelSharding(inputPath, outputPath, codedFormat, "KTT08 应收、应付账款情况明细表(会铁通月08表)", "||3个月以内（含3个月）", "项  目 名 称", 16, date);
//            // KTT09 货币资金情况补充表(会铁通月09表)
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT09 货币资金情况补充表(会铁通月09表)", "补充资料:", 1, date);
//            // KTT14增值税补充表(会铁通月14表)
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT14增值税补充表(会铁通月14表)", "项目", 3, date, 7, 8);
//            // KTT15管理口径费用统计表(会铁通月15表)
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT15管理口径费用统计表(会铁通月15表)", "项目", 3, date, 8, 9);
//            // KTT20表资本开资表-统计处
//            DataProcessingVerticalSharding.dataProcessingVerticalSharding(inputPath, outputPath, codedFormat, "KTT20表资本开资表-统计处", "投资项目分类", 3, date, 5, 6);
//            // KTT21表新租赁准则损益还原表
//            DataProcessingLevelSharding.dataProcessingLevelSharding(inputPath, outputPath, codedFormat, "KTT21表新租赁准则损益还原表", "||应付账款", "行号", 0, date);
//            // KTT22表新租赁准则下租赁业务关联方交易表
//            DataProcessingLevelSharding.dataProcessingLevelSharding(inputPath, outputPath, codedFormat, "KTT22表新租赁准则下租赁业务关联方交易表 ", "资产负债表科目", "损益表科目", 0, date);
//            // KTT99-表内表间勾稽关系校验检查-此表不打印
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT99-表内表间勾稽关系校验检查-此表不打印", "项目", 0, date);
//            // KTT1001网络管理技术服务收支利情况表-分业务
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT1001网络管理技术服务收支利情况表-分业务", "项    目||行次", 1, date, 0);
//            // KTT1002网络管理技术服务收支利情况表-分市场
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT1002网络管理技术服务收支利情况表-分市场", "项    目||行次", 1, date, 0);
//            // KTT1101工程建设与服务收支利情况表-分业务
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT1101工程建设与服务收支利情况表-分业务", "项     目||行次", 1, date, 0);
//            // KTT1102工程建设与服务收支利情况表-分市场
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT1102工程建设与服务收支利情况表-分市场", "项     目||行次", 1, date, 0);
//            // KTT1601市场营销服务收支利情况表-分业务
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT1601市场营销服务收支利情况表-分业务", "|||代售中小企业拓保业务", 1, date, 0);
//            // KTT1602市场营销服务收支利情况表-分市场
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT1602市场营销服务收支利情况表-分市场", "项     目||行次", 1, date, 0);
//            // KTT2301表计量-分业务
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT2301表计量-分业务", "项    目||行次", 1, date, 0);
//            // KTT2302表计量-分市场
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT2302表计量-分市场", "项    目||行次", 1, date, 0);
//            // KTT2401表其他-分业务
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT2401表其他-分业务", "项    目||行次", 1, date, 0);
//            // KTT2402表其他不含销售-分市场
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT2402表其他不含销售-分市场", "项    目||行次", 1, date, 0);
//            // KTT12Z人工成本情况表(会铁通月12表)
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT12Z人工成本情况表(会铁通月12表)", "|||网络管理技术服务", 1, date, 2);
//            // KTT13Z成本费用表（按成本属性分类）(会铁通月13表)
//            DataProcessingVerticalSharding.dataProcessingVerticalSharding(inputPath, outputPath, codedFormat, "KTT13Z成本费用表（按成本属性分类）(会铁通月13表)", "项目", 1, date, 3, 4, 5);
//            // KTT17Z税费月报表（会铁通月17表）
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT17Z税费月报表（会铁通月17表）", "项目", 1, date);
//            // KTT18Z增值税补充表（会铁通月18表)
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT18Z增值税补充表（会铁通月18表)", "项目", -6, date);
//            // KTT19Z集团内部交易明细表（中移铁通填列）
//            DataProcessingLevelSharding.dataProcessingLevelSharding(inputPath, outputPath, codedFormat, "KTT19Z集团内部交易明细表（中移铁通填列）", "||||会计科目", "||类型", 0, date, 1);
//            // 收入分业务、市场校验-此表不打印
//            DataProcessingCaptureMore.dataProcessingCaptureMore(inputPath, outputPath, codedFormat, "收入分业务、市场校验-此表不打印", "|分市场-分业务", 1, date);
//            // 收入核算合理性校验-此表不打印
//            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "收入核算合理性校验-此表不打印", "||家庭客户", 0, date);
            // 结束时间（毫秒）
            long endTime = System.currentTimeMillis();
            // 时间差（秒）
            double timeDifference = (double) (endTime - startTime) / 1000;
            // 可能存在200毫秒范围内的误差
            System.out.println("此次任务用时" + timeDifference + "秒");
        } else if (System.getProperty("os.name").split(" ", -1)[0].equals("Linux")) {
            // KTT0401营业收入明细表
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT0401营业收入明细表 ", "栏次", 0, args[3]);
            // KTT0402营业收入明细表-分市场
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT0402营业收入明细表-分市场", "||辅助核算", 0, args[3]);
            // KTT0403营业收入明细表-中国铁路总公司
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2],"KTT0403营业收入明细表-中国铁路总公司", "栏次", -1, args[3]);
            // KTT00 公司主要指标简表（计算生成）(会铁通月00表)
            DataProcessingAssignVerticalSharding.dataProcessingAssignVerticalSharding(args[0], args[1], args[2],"KTT00 公司主要指标简表（计算生成）(会铁通月00表)", "项  目", 11, args[3], 5);
            // KTT01 资产负债表(会铁通月01表)
            DataProcessingVerticalSharding.dataProcessingVerticalSharding(args[0], args[1], args[2],"KTT01 资产负债表(会铁通月01表)", "项目", 1, args[3]);
            // KTT02 损益数据表(会铁通月02表)
            DataProcessingVerticalSharding.dataProcessingVerticalSharding(args[0], args[1], args[2], "KTT02 损益数据表(会铁通月02表)", "项目", 9, args[3], 2, 3);
            // KTT03 现金流量分析表(会铁通月03表)
            DataProcessingVerticalSharding.dataProcessingVerticalSharding(args[0], args[1], args[2], "KTT03 现金流量分析表(会铁通月03表)", "项目", 2, args[3]);
            // KTT06 在建工程及转固情况表(会铁通月06表)
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT06 在建工程及转固情况表(会铁通月06表)", "|||金额", 12, args[3]);
            // KTT07 补充数据表(会铁通月07表)
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT07 补充数据表(会铁通月07表)", "税收及其他指标", 1, args[3]);
            // KTT08 应收、应付账款情况明细表(会铁通月08表)
            DataProcessingLevelSharding.dataProcessingLevelSharding(args[0], args[1], args[2], "KTT08 应收、应付账款情况明细表(会铁通月08表)", "||3个月以内（含3个月）", "项  目 名 称", 16, args[3]);
            // KTT09 货币资金情况补充表(会铁通月09表)
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT09 货币资金情况补充表(会铁通月09表)", "补充资料:", 1, args[3]);
            // KTT14增值税补充表(会铁通月14表)
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT14增值税补充表(会铁通月14表)", "项目", 3, args[3], 7, 8);
            // KTT15管理口径费用统计表(会铁通月15表)
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT15管理口径费用统计表(会铁通月15表)", "项目", 3, args[3], 8, 9);
            // KTT20表资本开资表-统计处
            DataProcessingVerticalSharding.dataProcessingVerticalSharding(args[0], args[1], args[2], "KTT20表资本开资表-统计处", "投资项目分类", 3, args[3], 5, 6);
            // KTT21表新租赁准则损益还原表
            DataProcessingLevelSharding.dataProcessingLevelSharding(args[0], args[1], args[2], "KTT21表新租赁准则损益还原表", "||应付账款", "行号", 0, args[3]);
            // KTT22表新租赁准则下租赁业务关联方交易表
            DataProcessingLevelSharding.dataProcessingLevelSharding(args[0], args[1], args[2], "KTT22表新租赁准则下租赁业务关联方交易表 ", "资产负债表科目", "损益表科目", 0, args[3]);
            // KTT99-表内表间勾稽关系校验检查-此表不打印
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT99-表内表间勾稽关系校验检查-此表不打印", "项目", 0, args[3]);
            // KTT1001网络管理技术服务收支利情况表-分业务
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT1001网络管理技术服务收支利情况表-分业务", "项    目||行次", 1, args[3], 0);
            // KTT1002网络管理技术服务收支利情况表-分市场
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2],"KTT1002网络管理技术服务收支利情况表-分市场", "项    目||行次", 1, args[3], 0);
            // KTT1101工程建设与服务收支利情况表-分业务
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2],"KTT1101工程建设与服务收支利情况表-分业务", "项     目||行次", 1, args[3], 0);
            // KTT1102工程建设与服务收支利情况表-分市场
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT1102工程建设与服务收支利情况表-分市场", "项     目||行次", 1, args[3], 0);
            // KTT1601市场营销服务收支利情况表-分业务
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT1601市场营销服务收支利情况表-分业务", "|||代售中小企业拓保业务", 1, args[3], 0);
            // KTT1602市场营销服务收支利情况表-分市场
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT1602市场营销服务收支利情况表-分市场", "项     目||行次", 1, args[3], 0);
            // KTT2301表计量-分业务
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT2301表计量-分业务", "项    目||行次", 1, args[3], 0);
            // KTT2302表计量-分市场
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT2302表计量-分市场", "项    目||行次", 1, args[3], 0);
            // KTT2401表其他-分业务
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT2401表其他-分业务", "项    目||行次", 1, args[3], 0);
            // KTT2402表其他不含销售-分市场
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT2402表其他不含销售-分市场", "项    目||行次", 1, args[3], 0);
            // KTT12Z人工成本情况表(会铁通月12表)
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT12Z人工成本情况表(会铁通月12表)", "|||网络管理技术服务", 1, args[3], 2);
            // KTT13Z成本费用表（按成本属性分类）(会铁通月13表)
            DataProcessingVerticalSharding.dataProcessingVerticalSharding(args[0], args[1], args[2], "KTT13Z成本费用表（按成本属性分类）(会铁通月13表)", "项目", 1, args[3], 3, 4, 5);
            // KTT17Z税费月报表（会铁通月17表）
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT17Z税费月报表（会铁通月17表）", "项目", 1, args[3]);
            // KTT18Z增值税补充表（会铁通月18表)
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "KTT18Z增值税补充表（会铁通月18表)", "项目", -6, args[3]);
            // KTT19Z集团内部交易明细表（中移铁通填列）
            DataProcessingLevelSharding.dataProcessingLevelSharding(args[0], args[1], args[2], "KTT19Z集团内部交易明细表（中移铁通填列）", "||||会计科目", "||类型", 0, args[3], 1);
            // 收入分业务、市场校验-此表不打印
            DataProcessingCaptureMore.dataProcessingCaptureMore(args[0], args[1], args[2], "收入分业务、市场校验-此表不打印", "|分市场-分业务", 1, args[3]);
            // 收入核算合理性校验-此表不打印
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(args[0], args[1], args[2], "收入核算合理性校验-此表不打印", "||家庭客户", 0, args[3]);
        }
    }
}
