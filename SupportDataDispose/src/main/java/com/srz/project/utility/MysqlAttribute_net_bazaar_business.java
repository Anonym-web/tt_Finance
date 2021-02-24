package com.srz.project.utility;

/**
 * 数据库：财务
 * 表：provinces_income
 */
public enum MysqlAttribute_net_bazaar_business {
    url("jdbc:mysql://10.0.43.68:3306/TT_WHMIS"),
    user("root"),
    password("zytop@123"),
    tableName("net_bazaar_business"),
    fields("科目编号,业务_科目,中国移动,中国电信,中国联通,中国铁路总公司,中国铁塔,地铁公司,其他市场,待分摊,合计,统计时间");

//    url("jdbc:mysql://localhost:3366/TT_WHMIS"),
//    user("root"),
//    password("sun"),
//    tableName("net_bazaar_business"),
//    fields("科目编号,业务_科目,中国移动,中国电信,中国联通,中国铁路总公司,中国铁塔,地铁公司,其他市场,待分摊,合计,统计时间");

    private String attribute;

    MysqlAttribute_net_bazaar_business(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}