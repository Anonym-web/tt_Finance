package project.database_property;

public enum engineering_business {
    url("jdbc:mysql://10.0.43.68:3306/TT_program"),
    user("root"),
    password("zytop@123"),
    tableName("`工程业务情况表`"),
    fields("`month`,`省分`,`收入`,`排名`,`累计收入`,`排名1`,`同比增长`,`排名2`,`全年收入计划`,`排名3`,`全年收入完成率`,`排名4`,`移动工程业务收入`,`非移收入`,`铁路工程业务收入`,`非移收入占比`,`排名5`,`累计成本`,`累计成本占收比`,`排名6`,`利润率`,`排名7`,`直接成本占收比`,`排名7/1`,`业务外包费`,`外包费占收比`,`排名8`,`应收账款`,`排名9`,`应收账款/年度收入`,`排名10`,`应付账款`,`排名11`,`当月回款金额`,`累计回款金额`,`排名12`");

//    url("jdbc:mysql://localhost:3366/TT_program"),
//    user("root"),
//    password("sun"),
//    tableName("`工程业务情况表`"),
//    fields("`month`,`省分`,`收入`,`排名`,`累计收入`,`排名1`,`同比增长`,`排名2`,`全年收入计划`,`排名3`,`全年收入完成率`,`排名4`,`移动工程业务收入`,`非移收入`,`铁路工程业务收入`,`非移收入占比`,`排名5`,`累计成本`,`累计成本占收比`,`排名6`,`利润率`,`排名7`,`直接成本占收比`,`排名7/1`,`业务外包费`,`外包费占收比`,`排名8`,`应收账款`,`排名9`,`应收账款/年度收入`,`排名10`,`应付账款`,`排名11`,`当月回款金额`,`累计回款金额`,`排名12`");


    private String attribute;

    engineering_business(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
