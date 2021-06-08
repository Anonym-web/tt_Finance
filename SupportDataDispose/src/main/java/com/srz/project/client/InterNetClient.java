package com.srz.project.client;

import com.srz.project.connection.OperationMySQL;
import com.srz.project.method.InterNetData;
import com.srz.project.utility.MysqlAttribute_net_bazaar_business;
import com.srz.project.utility.MysqlAttribute_net_business_province;
import com.srz.project.utility.MysqlAttribute_net_finance;
import com.srz.project.utility.MysqlAttribute_net_province_bazaar;

import java.sql.Connection;
import java.util.Calendar;
import java.util.concurrent.CopyOnWriteArrayList;

public class InterNetClient {

    public static void main(String[] args) {

        //flag如果是1，解析的就是v1，flag如果是2，解析的就是v2，
//        CopyOnWriteArrayList<String> sheet0ArrayList = InterNetData.networkManagementParse("G:\\铁通工作\\自己接到的\\网络管理服务定期数据\\2021-04\\2021年4月网络管理技术服务定期数据V1.xlsx",0,39,1);
//        for (String s : sheet0ArrayList) {
//            System.out.println(s);
//        }
//        // 连接数据库net_finance （收入明细表 没有检查）
//        Connection rawDataListConnection = OperationMySQL.connectMySQL(MysqlAttribute_net_finance.url.getAttribute(), MysqlAttribute_net_finance.user.getAttribute(), MysqlAttribute_net_finance.password.getAttribute());
//        // 插入数据net_finance
//        OperationMySQL.insertMySQL(rawDataListConnection, MysqlAttribute_net_finance.tableName.getAttribute(), MysqlAttribute_net_finance.fields.getAttribute(), sheet0ArrayList);


//        CopyOnWriteArrayList<String> sheet1ArrayList = InterNetData.networkManagementParse("G:\\铁通工作\\自己接到的\\网络管理服务定期数据\\2021-04\\2021年4月网络管理技术服务定期数据V1.xlsx",1,39,1);
//        for (String s : sheet1ArrayList) {
//            System.out.println(s);
//        }
//        // 连接数据库net_business_province KTT1001网络管理技术服务收支利情况表-分业务（有检查）
//        Connection rawDataListConnection = OperationMySQL.connectMySQL(MysqlAttribute_net_business_province.url.getAttribute(), MysqlAttribute_net_business_province.user.getAttribute(), MysqlAttribute_net_business_province.password.getAttribute());
//        // 插入数据net_business_province_copy
//        OperationMySQL.insertMySQL(rawDataListConnection, MysqlAttribute_net_business_province.tableName.getAttribute(), MysqlAttribute_net_business_province.fields.getAttribute(), sheet1ArrayList);



//        CopyOnWriteArrayList<String> sheet2ArrayList = InterNetData.networkManagementParse("G:\\铁通工作\\自己接到的\\网络管理服务定期数据\\2021-04\\2021年4月网络管理技术服务定期数据V1.xlsx",2,39,1);
//        for (String s : sheet2ArrayList) {
//            System.out.println(s);
//        }
//        // 连接数据库net_province_bazaar KTT1001网络管理技术服务收支利情况表-分市场 （没有检查）
//        Connection rawDataListConnection = OperationMySQL.connectMySQL(MysqlAttribute_net_province_bazaar.url.getAttribute(), MysqlAttribute_net_province_bazaar.user.getAttribute(), MysqlAttribute_net_province_bazaar.password.getAttribute());
//        // 插入数据net_province_bazaar_copy1
//        OperationMySQL.insertMySQL(rawDataListConnection, MysqlAttribute_net_province_bazaar.tableName.getAttribute(), MysqlAttribute_net_province_bazaar.fields.getAttribute(), sheet2ArrayList);


        //-----------------------------------------------------------------------------------------------------------------------

        CopyOnWriteArrayList<String> sheet0ArrayList = InterNetData.networkManagementParse("G:\\铁通工作\\自己接到的\\网络管理服务定期数据\\2021-04\\2021年4月网络管理技术服务定期数据V2.xlsx",0,11,2);
        for (String s : sheet0ArrayList) {
            System.out.println(s);
        }
        // 连接数据库net_bazaar_business   v2的 KTT1001网络管理技术服务收支利情况表-分业务分市场
        Connection rawDataListConnection = OperationMySQL.connectMySQL(MysqlAttribute_net_bazaar_business.url.getAttribute(), MysqlAttribute_net_bazaar_business.user.getAttribute(), MysqlAttribute_net_bazaar_business.password.getAttribute());
        // 插入数据 net_bazaar_business
        OperationMySQL.insertMySQL(rawDataListConnection, MysqlAttribute_net_bazaar_business.tableName.getAttribute(), MysqlAttribute_net_bazaar_business.fields.getAttribute(), sheet0ArrayList);



        /*CopyOnWriteArrayList<String> copyOnWriteArrayList = InterNetData.networkManagementParse("G:\\铁通工作\\交接的项目\\省份文件\\网络管理技术服务定期数据\\九月\\2020年9月网络管理技术服务定期数据V1.xlsx",0,39);
        for (String s : copyOnWriteArrayList) {
            System.out.println(s);
        }*/
    }
}