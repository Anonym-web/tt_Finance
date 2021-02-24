package project.database_property;

public enum gc_business1 {
    url("jdbc:mysql://10.0.43.68:3306/TT_program"),
    user("root"),
    password("zytop@123"),
    tableName("gc_business1"),
    fields("省分,收入,收入排名,累计收入,累计收入排名,同比增长,同比增长排名,全年收入计划,全年收入计划排名,全年收入完成率,全年收入完成率排名,移动工程业务收入,非移收入,铁路工程业务收入,非移收入占比,非移收入排名,累计成本,累计成本占收比,累计成本排名,直接成本占收比,直接成本排名,业务外包费,外包费占收比,外包费排名,利润率,利润率排名,应收账款,应收账款排名,应收账款除年度收入,应收账款除年度收入排名,应付账款,应付账款排名,当月回款金额,累计回款金额,累计回款金额排名,统计时间");

//    url("jdbc:mysql://localhost:3366/TT_program"),
//    user("root"),
//    password("sun"),
//    tableName("gc_business1"),
//    fields("省分,收入,收入排名,累计收入,累计收入排名,同比增长,同比增长排名,全年收入计划,全年收入计划排名,全年收入完成率,全年收入完成率排名,移动工程业务收入,非移收入,铁路工程业务收入,非移收入占比,非移收入排名,累计成本,累计成本占收比,累计成本排名,直接成本占收比,直接成本排名,业务外包费,外包费占收比,外包费排名,利润率,利润率排名,应收账款,应收账款排名,应收账款除年度收入,应收账款除年度收入排名,应付账款,应付账款排名,当月回款金额,累计回款金额,累计回款金额排名,统计时间");

    private String attribute;

    gc_business1(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
