package com.srz.project.utility;

/**
 * 数据库：财务
 * 表：provinces_income
 */
public enum MysqlAttribute_tt_dict {
    url("jdbc:postgresql://localhost:5432/postgres?binaryTransfer=False&forceBinary=False&reWriteBatchedInserts=true"),
    user("public"),
    password("sun"),
    tableName("tt_dict"),
    fields("project,city,province");

   /* url("jdbc:postgresql://10.0.47.75:5432/cmtt?binaryTransfer=False&forceBinary=False&reWriteBatchedInserts=true"),
    user("jiake"),
    password("DF2Dp0Xq@147"),
    tableName("tt_dict"),
    fields("project,city,province");*/

    private String attribute;

    MysqlAttribute_tt_dict(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}