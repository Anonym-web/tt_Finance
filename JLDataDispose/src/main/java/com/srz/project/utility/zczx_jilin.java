package com.srz.project.utility;

public enum zczx_jilin {
    url("jdbc:mysql://10.0.43.68:3306/省份数据"),
    user("root"),
    password("zytop@123"),
    tableName("zczx_jilin"),
    fields("所属省份,所属地市,部门档案名称,科目余额,直接收支利,分摊综合收支利,所属类型,统计时间,项目,台帐小计,台帐网服,台帐工程,台帐市场,台帐计量,台帐其他,台帐综合,核对直接,核对综合,合计小计,合计直接收支利,合计分摊综合收支利,行号,分摊原则");
//    url("jdbc:mysql://localhost:3366/省份数据"),
//    user("root"),
//    password("sun"),
//    tableName("zczx_jilin"),
//    fields("所属省份,所属地市,部门档案名称,科目余额,直接收支利,分摊综合收支利,所属类型,统计时间,项目,台帐小计,台帐网服,台帐工程,台帐市场,台帐计量,台帐其他,台帐综合,核对直接,核对综合,合计小计,合计直接收支利,合计分摊综合收支利,行号,分摊原则");
    private String attribute;

    zczx_jilin(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
