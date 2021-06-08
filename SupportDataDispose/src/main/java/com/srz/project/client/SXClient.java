package com.srz.project.client;

import com.srz.project.connection.OperationMySQL;
import com.srz.project.method.DisposeSXRawData;
import com.srz.project.method.DisposeSXSupportCenterData;
import com.srz.project.method.ReadSXRosterData;
import com.srz.project.method.TwiceDisposeSXData;
import com.srz.project.utility.MysqlAttribute_renyuan_shandong;
import com.srz.project.utility.MysqlAttribute_zczx_shandong;
import com.srz.project.utility.MysqlAttribute_zczx_shandong2;
import com.srz.project.utility.MysqlAttribute_zczx_ys_shandong;

import java.sql.Connection;
import java.util.concurrent.CopyOnWriteArrayList;

public class SXClient {
    public static void main(String[] args) {
        // 装载处理后的原数据
        CopyOnWriteArrayList<String> rawDataList = DisposeSXRawData.disposeSXRawData("G:\\铁通工作\\自己接到的\\省份数据\\陕西\\陕西四月\\陕西", 0, 3, "2021-04");
//
//        for (String s : rawDataList) {
//
//            System.out.println(s);
//        }
          // 连接数据库
//        Connection rawDataListConnection = OperationMySQL.connectMySQL(MysqlAttribute_zczx_ys_shandong.url.getAttribute(), MysqlAttribute_zczx_ys_shandong.user.getAttribute(), MysqlAttribute_zczx_ys_shandong.password.getAttribute());
        // 插入数据
//        OperationMySQL.insertMySQL(rawDataListConnection, MysqlAttribute_zczx_ys_shandong.tableName.getAttribute(), MysqlAttribute_zczx_ys_shandong.fields.getAttribute(), rawDataList);

//        // 装载处理后的支撑中心数据
//        CopyOnWriteArrayList<String> disposeList = DisposeSXSupportCenterData.disposeSXSupportCenterData(rawDataList);
//        for (String s : disposeList) {
//            System.out.println(s);
//        }
        // 连接数据库
//        Connection disposeListConnection = OperationMySQL.connectMySQL(MysqlAttribute_zczx_shandong.url.getAttribute(), MysqlAttribute_zczx_shandong.user.getAttribute(), MysqlAttribute_zczx_shandong.password.getAttribute());
        // 插入数据
//        OperationMySQL.insertMySQL(disposeListConnection, MysqlAttribute_zczx_shandong.tableName.getAttribute(), MysqlAttribute_zczx_shandong.fields.getAttribute(), disposeList);

//        // 二次处理
//        CopyOnWriteArrayList<String> TwiceDisposeList = TwiceDisposeSXData.twiceDisposeSXData(disposeList);
////
//        for (String s : TwiceDisposeList) {
//
//            System.out.println(s);
//        }
//        // 连接数据库
//        Connection TwiceDisposeListConnection = OperationMySQL.connectMySQL(MysqlAttribute_zczx_shandong2.url.getAttribute(), MysqlAttribute_zczx_shandong2.user.getAttribute(), MysqlAttribute_zczx_shandong2.password.getAttribute());
//        // 插入数据
//        OperationMySQL.insertMySQL(TwiceDisposeListConnection, MysqlAttribute_zczx_shandong2.tableName.getAttribute(), MysqlAttribute_zczx_shandong2.fields.getAttribute(), TwiceDisposeList);



        // 装载处理后的花名册数据  //注意花名册里面的代码可能需要改
//        CopyOnWriteArrayList<String> rosterList = ReadSXRosterData.readSXRosterData("G:\\铁通工作\\自己接到的\\省份数据\\陕西\\陕西四月\\陕西人员信息数据（4月)-全省汇总.xlsx", 6, "2021-04");
//        for (String s : rosterList) {
//            System.out.println(s);
//        }
//        System.out.println(rosterList.size());
//        // 连接数据库
//        Connection disposeRosterDataConnection = OperationMySQL.connectMySQL(MysqlAttribute_renyuan_shandong.url.getAttribute(), MysqlAttribute_renyuan_shandong.user.getAttribute(), MysqlAttribute_renyuan_shandong.password.getAttribute());
//        // 插入数据
//        OperationMySQL.insertMySQL(disposeRosterDataConnection, MysqlAttribute_renyuan_shandong.tableName.getAttribute(), MysqlAttribute_renyuan_shandong.fields.getAttribute(), rosterList);
    }
}
