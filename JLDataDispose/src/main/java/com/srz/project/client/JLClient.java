package com.srz.project.client;

import com.srz.project.connection.OperationMySQL;
import com.srz.project.method.DisposeJLData;
import com.srz.project.utility.zczx_shandong;
import com.srz.project.method.DisposeJLSupportCenterData;
import com.srz.project.method.ReadJLRosterData;
import com.srz.project.utility.zczx_jilin;
import com.srz.project.utility.renyuan_shandong;

import java.sql.Connection;
import java.util.List;

public class JLClient {
    public static void main(String[] args) {
        // 存储处理后的吉林支撑中心数据（zczx_shandong）
//        List<String> jLSupportData = DisposeJLSupportCenterData.disposeJLSupportCenterData("G:\\铁通工作\\自己接到的\\省份数据\\吉林\\2020-12\\2020年12月收支利台帐-支撑中心-汇总（发总部）.xlsx", 0, 357, "2020-12");
//        for (String jLSupportDatum : jLSupportData) {
//            System.out.println(jLSupportDatum);
//        }
//        // 连接数据库
//        Connection jLSupportConnection = OperationMySQL.connectMySQL(zczx_shandong.url.getAttribute(), zczx_shandong.user.getAttribute(), zczx_shandong.password.getAttribute());
//        // 插入数据
//        OperationMySQL.insertMySQL(jLSupportConnection, zczx_shandong.tableName.getAttribute(), zczx_shandong.fields.getAttribute(), jLSupportData);
        // 存储处理后的吉林支撑中心数据（zczx_jilin）
//        List<String> jLData = DisposeJLData.disposeJLData("G:\\铁通工作\\自己接到的\\省份数据\\吉林\\2020-12\\2020年12月收支利台帐-支撑中心-汇总（发总部）.xlsx", 0, 357, "2020-12");
//
//        for (String jLDatum : jLData) {
//            System.out.println(jLDatum);
//        }
//        // 连接数据库
//        Connection jLDataConnection = OperationMySQL.connectMySQL(zczx_jilin.url.getAttribute(), zczx_jilin.user.getAttribute(), zczx_jilin.password.getAttribute());
//        // 插入数据
//        OperationMySQL.insertMySQL(jLDataConnection, zczx_jilin.tableName.getAttribute(), zczx_jilin.fields.getAttribute(), jLData);
        // 存储处理后的花名册数据
//        List<String> disposeRosterList = new ReadJLRosterData().readJLRosterData("G:\\铁通工作\\睿 交接的项目\\省份文件  SupportDataDispose 工程\\吉林数据\\九月\\花名册.xlsx", "2020-12");
//        // 连接数据库
//        Connection disposeRosterConnection = OperationMySQL.connectMySQL(renyuan_shandong.url.getAttribute(), renyuan_shandong.user.getAttribute(), renyuan_shandong.password.getAttribute());
//        // 插入数据
//        OperationMySQL.insertMySQL(disposeRosterConnection, renyuan_shandong.tableName.getAttribute(), renyuan_shandong.fields.getAttribute(), disposeRosterList);
    }
}