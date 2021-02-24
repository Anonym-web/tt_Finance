package project.database_property;

public enum ods_people_shengfen {
    url("jdbc:postgresql://10.0.47.75:5432/cmtt?currentSchema=cmtt_test&stringtype=unspecified"),
    user("cmtt_test"),
    password("DF2Dp0Xq@147"),
    tableName("ods_people_shengfen"),
    fields("id,unit,total_employees_owned,contract_subtotal,contract_guard,contract_early_retirement,labor_dispatching,partnership,date");

    private String attribute;

    ods_people_shengfen(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
