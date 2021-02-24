package com.srz.project.utility;

/**
 * 数据库：财务
 * 表：provinces_income
 */
public enum MysqlAttribute_provinces_income {
    url("jdbc:mysql://10.0.43.68:3306/财务"),
    user("root"),
    password("zytop@123"),
    tableName("provinces_income"),
    fields("所属省份,所属地市,部门档案名称,总体科目名称,科目编号,一级科目名称,二级科目名称,三级科目名称,四级科目名称,五级科目名称,成本费用类型,成本投入市场类型,客户类型,科目余额,直接收支利,分摊综合收支利,所属类型,统计时间,成本类型明细,项目,是否为末级,项目辅助核算名称,交易类型名称");

    private String attribute;

    MysqlAttribute_provinces_income(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}