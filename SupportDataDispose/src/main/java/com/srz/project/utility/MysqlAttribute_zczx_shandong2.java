package com.srz.project.utility;

/**
 * 数据库：省份数据
 * 表：zczx_shandong2
 */
public enum MysqlAttribute_zczx_shandong2 {
    url("jdbc:mysql://10.0.43.68:3306/省份数据"),
    user("root"),
    password("Gdferh_Dwer2@rYdqs"),
    tableName("zczx_shandong2"),
    fields("所属省份,所属地市,部门档案名称,总体科目名称,科目编号,一级科目名称,二级科目名称,三级科目名称,四级科目名称,五级科目名称,成本费用类型,成本投入市场类型,客户类型,科目余额,直接收支利,分摊综合收支利,所属类型,统计时间,成本类型明细,项目,是否为末级");

//    url("jdbc:mysql://localhost:3366/省份数据"),
//    user("root"),
//    password("sun"),
//    tableName("zczx_shandong2"),
//    fields("所属省份,所属地市,部门档案名称,总体科目名称,科目编号,一级科目名称,二级科目名称,三级科目名称,四级科目名称,五级科目名称,成本费用类型,成本投入市场类型,客户类型,科目余额,直接收支利,分摊综合收支利,所属类型,统计时间,成本类型明细,项目,是否为末级");

    private String attribute;

    MysqlAttribute_zczx_shandong2(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
