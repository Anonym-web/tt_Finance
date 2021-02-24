package per.srz.project.method;

public class ThreadTask implements Runnable {
    // 文件输入路径
    static String inputPath;
    // 文件输出路径
    static String outputPath;
    // 文件编码格式
    static String codedFormat;
    // 指定日期
    static String date;

    // 参数说明详见Client方法
    public void run() {
        if (Thread.currentThread().getName().equals("pool-1-thread-1"))
            // KTT0401营业收入明细表
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT0401营业收入明细表 ", "栏次", 0, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-2"))
            // KTT0402营业收入明细表-分市场
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT0402营业收入明细表-分市场", "||辅助核算", 0, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-3"))
            // KTT0403营业收入明细表-中国铁路总公司
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT0403营业收入明细表-中国铁路总公司", "栏次", -1, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-4"))
            // KTT00 公司主要指标简表（计算生成）(会铁通月00表)
            DataProcessingAssignVerticalSharding.dataProcessingAssignVerticalSharding(inputPath, outputPath, codedFormat, "KTT00 公司主要指标简表（计算生成）(会铁通月00表)", "项  目", 11, date, 5);
        else if (Thread.currentThread().getName().equals("pool-1-thread-5"))
            // KTT01 资产负债表(会铁通月01表)
            DataProcessingVerticalSharding.dataProcessingVerticalSharding(inputPath, outputPath, codedFormat, "KTT01 资产负债表(会铁通月01表)", "项目", 1, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-6"))
            // KTT02 损益数据表(会铁通月02表)
            DataProcessingVerticalSharding.dataProcessingVerticalSharding(inputPath, outputPath, codedFormat, "KTT02 损益数据表(会铁通月02表)", "项目", 9, date, 2, 3);
        else if (Thread.currentThread().getName().equals("pool-1-thread-7"))
            // KTT03 现金流量分析表(会铁通月03表)
            DataProcessingVerticalSharding.dataProcessingVerticalSharding(inputPath, outputPath, codedFormat, "KTT03 现金流量分析表(会铁通月03表)", "项目", 2, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-8"))
            // KTT06 在建工程及转固情况表(会铁通月06表)
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT06 在建工程及转固情况表(会铁通月06表)", "|||金额", 12, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-9"))
            // KTT07 补充数据表(会铁通月07表)
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT07 补充数据表(会铁通月07表)", "税收及其他指标", 1, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-10"))
            // KTT08 应收、应付账款情况明细表(会铁通月08表)
            DataProcessingLevelSharding.dataProcessingLevelSharding(inputPath, outputPath, codedFormat, "KTT08 应收、应付账款情况明细表(会铁通月08表)", "||3个月以内（含3个月）", "项  目 名 称", 16, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-11"))
            // KTT09 货币资金情况补充表(会铁通月09表)
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT09 货币资金情况补充表(会铁通月09表)", "补充资料:", 1, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-12"))
            // KTT14增值税补充表(会铁通月14表)
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT14增值税补充表(会铁通月14表)", "项目", 3, date, 7, 8);
        else if (Thread.currentThread().getName().equals("pool-1-thread-13"))
            // KTT15管理口径费用统计表(会铁通月15表)
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT15管理口径费用统计表(会铁通月15表)", "项目", 0, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-14"))
            // KTT20表资本开资表-统计处
            DataProcessingVerticalSharding.dataProcessingVerticalSharding(inputPath, outputPath, codedFormat, "KTT20表资本开资表-统计处", "投资项目分类", 3, date, 5, 6);
        else if (Thread.currentThread().getName().equals("pool-1-thread-15"))
            // KTT21表新租赁准则损益还原表
            DataProcessingLevelSharding.dataProcessingLevelSharding(inputPath, outputPath, codedFormat, "KTT21表新租赁准则损益还原表", "||应付账款", "行号", 0, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-16"))
            // KTT22表新租赁准则下租赁业务关联方交易表
            DataProcessingLevelSharding.dataProcessingLevelSharding(inputPath, outputPath, codedFormat, "KTT22表新租赁准则下租赁业务关联方交易表 ", "资产负债表科目", "损益表科目", 0, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-17"))
            // KTT1001网络管理技术服务收支利情况表-分业务
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT1001网络管理技术服务收支利情况表-分业务", "项    目||行次", 1, date, 0);
        else if (Thread.currentThread().getName().equals("pool-1-thread-18"))
            // KTT1002网络管理技术服务收支利情况表-分市场
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT1002网络管理技术服务收支利情况表-分市场", "项    目||行次", 2, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-19"))
            // KTT1101工程建设与服务收支利情况表-分业务
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT1101工程建设与服务收支利情况表-分业务", "项     目||行次", 2, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-20"))
            // KTT1102工程建设与服务收支利情况表-分市场
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT1102工程建设与服务收支利情况表-分市场", "项     目||行次", 2, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-21"))
            // KTT1601市场营销服务收支利情况表-分业务
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT1601市场营销服务收支利情况表-分业务", "|||代售中小企业拓保业务", 2, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-22"))
            // KTT1602市场营销服务收支利情况表-分市场
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT1602市场营销服务收支利情况表-分市场", "项     目||行次", 2, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-23"))
            // KTT12Z人工成本情况表(会铁通月12表)
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT12Z人工成本情况表(会铁通月12表)", "|||网络管理技术服务", 1, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-24"))
            // KTT13Z成本费用表（按成本属性分类）(会铁通月13表)
            DataProcessingVerticalSharding.dataProcessingVerticalSharding(inputPath, outputPath, codedFormat, "KTT13Z成本费用表（按成本属性分类）(会铁通月13表)", "项目", 1, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-25"))
            // KTT17Z税费月报表（会铁通月17表）
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT17Z税费月报表（会铁通月17表）", "项目", 1, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-26"))
            // KTT18Z增值税补充表（会铁通月18表)
            DataProcessingAdditionalDirectly.dataProcessingAdditionalDirectly(inputPath, outputPath, codedFormat, "KTT18Z增值税补充表（会铁通月18表)", "项目", -6, date);
        else if (Thread.currentThread().getName().equals("pool-1-thread-27"))
            // KTT19Z集团内部交易明细表（中移铁通填列）
            DataProcessingLevelSharding.dataProcessingLevelSharding(inputPath, outputPath, codedFormat, "KTT19Z集团内部交易明细表（中移铁通填列）", "||||会计科目", "||类型", 0, date, 1);
        else if (Thread.currentThread().getName().equals("pool-1-thread-28"))
            // 收入分业务、市场校验-此表不打印
            DataProcessingCaptureMore.dataProcessingCaptureMore(inputPath, outputPath, codedFormat, "收入分业务、市场校验-此表不打印", "|分市场-分业务", 1, date);
    }
}

