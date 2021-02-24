package com.srz.project.utility;

/**
 * 数据库：财务
 * 表：provinces_income
 */
public enum MysqlAttribute_net_province_bazaar {
    url("jdbc:mysql://10.0.43.68:3306/TT_WHMIS"),
    user("root"),
    password("zytop@123"),
    tableName("net_province_bazaar"),
    fields("`科目编号`,`市场_科目`,`中移铁通有限公司(总分组织架构)`,`中移铁通有限公司总部(汇总)`,`中移铁通有限公司北京分公司(合并)`,`中移铁通有限公司天津分公司(合并)`,`中移铁通有限公司河北分公司(合并)`,`中移铁通有限公司山西分公司(合并)`,`中移铁通有限公司内蒙古分公司(合并)`,`中移铁通有限公司辽宁分公司(合并)`,`中移铁通有限公司吉林分公司(合并)`,`中移铁通有限公司黑龙江分公司(合并)`,`中移铁通有限公司上海分公司(汇总)`,`中移铁通有限公司江苏分公司(汇总)`,`中移铁通有限公司浙江分公司(合并)`,`中移铁通有限公司安徽分公司(合并)`,`中移铁通有限公司福建分公司(合并)`,`中移铁通有限公司江西分公司(合并)`,`中移铁通有限公司山东分公司(合并)`,`中移铁通有限公司河南分公司(合并)`,`中移铁通有限公司湖北分公司(合并)`,`中移铁通有限公司湖南分公司(合并)`,`中移铁通有限公司广东分公司(合并)`,`中移铁通有限公司广西分公司(合并)`,`中移铁通有限公司海南分公司(合并)`,`中移铁通有限公司重庆分公司(合并)`,`中移铁通有限公司四川分公司(合并)`,`中移铁通有限公司贵州分公司(合并)`,`中移铁通有限公司云南分公司(合并)`,`中移铁通有限公司陕西分公司(合并)`,`中移铁通有限公司甘肃分公司(合并)`,`中移铁通有限公司青海分公司(汇总)`,`中移铁通有限公司宁夏分公司(合并)`,`中移铁通有限公司新疆分公司(合并)`,`中移铁通有限公司北京通信设备维护中心(合并)`,`中移铁通(香港)有限公司(合并)`,`中移铁通工程公司`,`中移建设四川工程分公司`,`四川中移通信技术工程有限公司`,`统计时间`");

//    url("jdbc:mysql://localhost:3366/TT_WHMIS"),
//    user("root"),
//    password("sun"),
//    tableName("net_province_bazaar"),
//    fields("`科目编号`,`市场_科目`,`中移铁通有限公司(总分组织架构)`,`中移铁通有限公司总部(汇总)`,`中移铁通有限公司北京分公司(合并)`,`中移铁通有限公司天津分公司(合并)`,`中移铁通有限公司河北分公司(合并)`,`中移铁通有限公司山西分公司(合并)`,`中移铁通有限公司内蒙古分公司(合并)`,`中移铁通有限公司辽宁分公司(合并)`,`中移铁通有限公司吉林分公司(合并)`,`中移铁通有限公司黑龙江分公司(合并)`,`中移铁通有限公司上海分公司(汇总)`,`中移铁通有限公司江苏分公司(汇总)`,`中移铁通有限公司浙江分公司(合并)`,`中移铁通有限公司安徽分公司(合并)`,`中移铁通有限公司福建分公司(合并)`,`中移铁通有限公司江西分公司(合并)`,`中移铁通有限公司山东分公司(合并)`,`中移铁通有限公司河南分公司(合并)`,`中移铁通有限公司湖北分公司(合并)`,`中移铁通有限公司湖南分公司(合并)`,`中移铁通有限公司广东分公司(合并)`,`中移铁通有限公司广西分公司(合并)`,`中移铁通有限公司海南分公司(合并)`,`中移铁通有限公司重庆分公司(合并)`,`中移铁通有限公司四川分公司(合并)`,`中移铁通有限公司贵州分公司(合并)`,`中移铁通有限公司云南分公司(合并)`,`中移铁通有限公司陕西分公司(合并)`,`中移铁通有限公司甘肃分公司(合并)`,`中移铁通有限公司青海分公司(汇总)`,`中移铁通有限公司宁夏分公司(合并)`,`中移铁通有限公司新疆分公司(合并)`,`中移铁通有限公司北京通信设备维护中心(合并)`,`中移铁通(香港)有限公司(合并)`,`中移铁通工程公司`,`中移建设四川工程分公司`,`四川中移通信技术工程有限公司`,`统计时间`");

    private String attribute;

    MysqlAttribute_net_province_bazaar(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}