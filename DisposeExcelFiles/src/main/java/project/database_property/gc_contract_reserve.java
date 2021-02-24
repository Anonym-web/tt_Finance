package project.database_property;

public enum gc_contract_reserve {
    url("jdbc:mysql://10.0.43.68:3306/TT_program"),
    user("root"),
    password("zytop@123"),
    tableName("gc_contract_reserve"),
    fields("省分,2020全年收入计划,计算预警值,2017年合同平均兑现率,2018年合同平均兑现率,2019年已执行完合同兑现率,平均兑现率,计算预警值1,2020年签订合同,2019年在执行合同,已签订订单金额,兑现率,19年在执行合同剩余合同额,现有合同存量,合同储备缺口,统计时间");

//    url("jdbc:mysql://localhost:3366/TT_program"),
//    user("root"),
//    password("sun"),
//    tableName("gc_contract_reserve"),
//    fields("省分,2020全年收入计划,计算预警值,2017年合同平均兑现率,2018年合同平均兑现率,2019年已执行完合同兑现率,平均兑现率,计算预警值1,2020年签订合同,2019年在执行合同,已签订订单金额,兑现率,19年在执行合同剩余合同额,现有合同存量,合同储备缺口,统计时间");

    private String attribute;

    gc_contract_reserve(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
