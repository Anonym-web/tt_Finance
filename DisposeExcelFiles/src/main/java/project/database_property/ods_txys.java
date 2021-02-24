package project.database_property;

public enum ods_txys {
    url("jdbc:postgresql://10.0.47.75:5432/cmtt?currentSchema=cmtt_test&stringtype=unspecified"),
    user("cmtt_test"),
    password("DF2Dp0Xq@147"),
    tableName("ods_txys_copy1"),
    fields("职位编码,职位名称,主族编码,主族名称,子族编码,子族名称,条线名称,条线建议");

    private String attribute;

    ods_txys(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
