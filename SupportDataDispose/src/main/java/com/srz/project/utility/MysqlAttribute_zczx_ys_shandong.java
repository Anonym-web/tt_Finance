package com.srz.project.utility;

/**
 * 数据库：省份数据
 * 表：zczx_ys_shandong
 */
public enum MysqlAttribute_zczx_ys_shandong {
    url("jdbc:mysql://10.0.43.68:3306/省份数据"),
    user("root"),
    password("Gdferh_Dwer2@rYdqs"),
    tableName("zczx_ys_shandong"),
    fields("主体帐簿名称,科目名称,部门档案名称,成本费用类型,成本投入市场类型,客户类型,方向1,期初余额,本期借方,本期贷方,借方累计,贷方累计,方向,期末余额,所属省份,所属类型,统计时间,成本类型明细");

//    url("jdbc:mysql://localhost:3366/省份数据"),
//    user("root"),
//    password("sun"),
//    tableName("zczx_ys_shandong"),
//    fields("主体帐簿名称,科目名称,部门档案名称,成本费用类型,成本投入市场类型,客户类型,方向1,期初余额,本期借方,本期贷方,借方累计,贷方累计,方向,期末余额,所属省份,所属类型,统计时间,成本类型明细");

    private String attribute;

    MysqlAttribute_zczx_ys_shandong(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
