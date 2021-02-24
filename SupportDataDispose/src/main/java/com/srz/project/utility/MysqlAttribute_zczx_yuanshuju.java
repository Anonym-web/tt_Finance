package com.srz.project.utility;

/**
 * 数据库：省份数据
 * 表：zczx_yuanshuju
 */
public enum MysqlAttribute_zczx_yuanshuju {
    url("jdbc:mysql://10.0.41.153:3306/省份数据"),
    user("root"),
    password("zytop@123"),
    tableName("zczx_yuanshuju_copy2"),
    fields("科目名称,部门档案名称,成本费用类型名称,方向1,期初余额,本期借方,本期贷方,借方累计,贷方累计,方向,期末余额,所属省份,所属地市,统计时间");

    private String attribute;

    MysqlAttribute_zczx_yuanshuju(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
