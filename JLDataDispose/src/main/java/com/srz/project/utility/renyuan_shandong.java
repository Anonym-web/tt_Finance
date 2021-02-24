package com.srz.project.utility;

public enum renyuan_shandong {
    url("jdbc:mysql://10.0.43.68:3306/省份数据"),
    user("root"),
    password("zytop@123"),
    tableName("renyuan_shandong"),
    fields("所属省份,所属地市,部门档案名称,人员属性,人员数量,统计时间");

//    url("jdbc:mysql://localhost:3366/省份数据"),
//    user("root"),
//    password("sun"),
//    tableName("renyuan_shandong"),
//    fields("所属省份,所属地市,部门档案名称,人员属性,人员数量,统计时间");
    private String attribute;

    renyuan_shandong(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
