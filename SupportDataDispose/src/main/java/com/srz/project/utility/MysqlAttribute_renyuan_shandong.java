package com.srz.project.utility;

/**
 * 数据库：省份数据
 * 表：renyuan_shandong
 */
public enum MysqlAttribute_renyuan_shandong {
    url("jdbc:mysql://10.0.43.68:3306/省份数据"),
    user("root"),
    password("Gdferh_Dwer2@rYdqs"),
    tableName("renyuan_shandong"),
    fields("所属省份,所属地市,部门档案名称,人员属性,人员数量,统计时间");

//    url("jdbc:mysql://localhost:3366/省份数据"),
//    user("root"),
//    password("sun"),
//    tableName("renyuan_shandong"),
//    fields("所属省份,所属地市,部门档案名称,人员属性,人员数量,统计时间");

    private String attribute;

    MysqlAttribute_renyuan_shandong(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
