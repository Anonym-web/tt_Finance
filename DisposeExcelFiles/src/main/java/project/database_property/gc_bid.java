package project.database_property;

public enum gc_bid {
    url("jdbc:mysql://10.0.43.68:3306/TT_program"),
    user("root"),
    password("zytop@123"),
    tableName("gc_bid"),
    fields("省分,投标数量,移动投标数量,移动外投标数量,其中铁路投标数量,总投资金额,移动投资金额,移动外投资金额,铁路投资金额,中标数量,中标率,移动中标数量,移动项目中标率,移动外中标数量,移动外项目中标率,其中铁路中标数量,铁路项目中标率,中标金额,中标份额,移动中标金额,移动中标份额,集客,家客,室分和WLAN,设备安装,线路管道,其他,移动外中标金额,移动外中标份额,铁路,电信,联通,铁塔,广电,铁通,其它,统计时间");


//    url("jdbc:mysql://localhost:3366/TT_program"),
//    user("root"),
//    password("sun"),
//    tableName("gc_bid"),
//    fields("省分,投标数量,移动投标数量,移动外投标数量,其中铁路投标数量,总投资金额,移动投资金额,移动外投资金额,铁路投资金额,中标数量,中标率,移动中标数量,移动项目中标率,移动外中标数量,移动外项目中标率,其中铁路中标数量,铁路项目中标率,中标金额,中标份额,移动中标金额,移动中标份额,集客,家客,室分和WLAN,设备安装,线路管道,其他,移动外中标金额,移动外中标份额,铁路,电信,联通,铁塔,广电,铁通,其它,统计时间");

    private String attribute;

    gc_bid(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
