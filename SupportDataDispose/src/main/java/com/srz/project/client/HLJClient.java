package com.srz.project.client;

import com.srz.project.connection.OperationMySQL;
import com.srz.project.method.DisposeHLJRawData;
import com.srz.project.method.DisposeHLJSupportCenterData;
import com.srz.project.method.ReadHLJSupportCenterData;
import com.srz.project.method.ReadSXRosterData;
import com.srz.project.utility.MysqlAttribute_renyuan_shandong;
import com.srz.project.utility.MysqlAttribute_zczx_shandong;
import com.srz.project.utility.MysqlAttribute_zczx_ys_shandong;
import com.srz.project.utility.MysqlAttribute_zczx_yuanshuju;

import java.sql.Connection;
import java.util.concurrent.CopyOnWriteArrayList;

public class HLJClient {

    public static void main(String[] args) {
        // 装载黑龙江原数据
        CopyOnWriteArrayList<String> rawDataList = ReadHLJSupportCenterData.readHLJSupportCenterData(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
//        // 连接数据库
//        Connection rawDataListConnection = OperationMySQL.connectMySQL(MysqlAttribute_zczx_ys_shandong.url.getAttribute(), MysqlAttribute_zczx_ys_shandong.user.getAttribute(), MysqlAttribute_zczx_ys_shandong.password.getAttribute());
//        // 插入数据
//        OperationMySQL.insertMySQL(rawDataListConnection, MysqlAttribute_zczx_ys_shandong.tableName.getAttribute(), MysqlAttribute_zczx_ys_shandong.fields.getAttribute(), rawDataList);
//        // 存储处理后的支撑数据
//        CopyOnWriteArrayList<String> disposeList = DisposeHLJSupportCenterData.disposeHLJSupportCenterData(rawDataList);
//        // 连接数据库
//        Connection disposeListConnection = OperationMySQL.connectMySQL(MysqlAttribute_zczx_shandong.url.getAttribute(), MysqlAttribute_zczx_shandong.user.getAttribute(), MysqlAttribute_zczx_shandong.password.getAttribute());
//        // 插入数据
//        OperationMySQL.insertMySQL(disposeListConnection, MysqlAttribute_zczx_shandong.tableName.getAttribute(), MysqlAttribute_zczx_shandong.fields.getAttribute(), disposeList);
//        // 存储处理后的花名册的数据
//        CopyOnWriteArrayList<String> disposeRosterData = ReadSXRosterData.readSXRosterData("E://合并-汉中人员数据(3月).xlsx", 0, "2020-02");
//        // 连接数据库
//        Connection disposeRosterDataConnection = OperationMySQL.connectMySQL(MysqlAttribute_renyuan_shandong.url.getAttribute(), MysqlAttribute_renyuan_shandong.user.getAttribute(), MysqlAttribute_renyuan_shandong.password.getAttribute());
//        // 插入数据
//        OperationMySQL.insertMySQL(disposeRosterDataConnection, MysqlAttribute_renyuan_shandong.tableName.getAttribute(), MysqlAttribute_renyuan_shandong.fields.getAttribute(), disposeRosterData);
//        // 装载黑龙江原数据的集合
//        CopyOnWriteArrayList<String> acrossRawDataList = DisposeHLJRawData.disposeHLJRawData("E://黑龙江", 0, 12);
//        // 连接数据库
//        Connection acrossRawDataListConnection = OperationMySQL.connectMySQL(MysqlAttribute_zczx_yuanshuju.url.getAttribute(), MysqlAttribute_zczx_yuanshuju.user.getAttribute(), MysqlAttribute_zczx_yuanshuju.password.getAttribute());
//        // 插入数据
//        OperationMySQL.insertMySQL(acrossRawDataListConnection, MysqlAttribute_zczx_yuanshuju.tableName.getAttribute(), MysqlAttribute_zczx_yuanshuju.fields.getAttribute(), acrossRawDataList);
    }
}
