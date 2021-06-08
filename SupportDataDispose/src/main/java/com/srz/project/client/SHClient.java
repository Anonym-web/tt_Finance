package com.srz.project.client;

import com.srz.project.connection.OperationMySQL;
import com.srz.project.method.DisposeSHSupportCenterData;
import com.srz.project.method.ReadSHRosterData;
import com.srz.project.method.ReadSHSupportCenterData;
import com.srz.project.utility.MysqlAttribute_renyuan_shandong;
import com.srz.project.utility.MysqlAttribute_zczx_shandong;
import com.srz.project.utility.MysqlAttribute_zczx_ys_shandong;

import java.sql.Connection;
import java.util.concurrent.CopyOnWriteArrayList;

public class SHClient {
    public static void main(String[] args) {
        // 处理原数据
        CopyOnWriteArrayList<String> rawDataList = ReadSHSupportCenterData.readSHSupportCenterData("G:\\铁通工作\\自己接到的\\省份数据\\上海\\2021.02", 0, 0);

        for (String s : rawDataList) {
            System.out.println(s
            );
        }
//         连接数据库
        Connection rawDataListConnection = OperationMySQL.connectMySQL(MysqlAttribute_zczx_ys_shandong.url.getAttribute(), MysqlAttribute_zczx_ys_shandong.user.getAttribute(), MysqlAttribute_zczx_ys_shandong.password.getAttribute());
        // 插入数据
        OperationMySQL.insertMySQL(rawDataListConnection, MysqlAttribute_zczx_ys_shandong.tableName.getAttribute(), MysqlAttribute_zczx_ys_shandong.fields.getAttribute(), rawDataList);

//         处理处理后的原数据
        CopyOnWriteArrayList<String> disposeList = DisposeSHSupportCenterData.disposeSHSupportCenterData(rawDataList);
        // 连接数据库
        Connection disposeListConnection = OperationMySQL.connectMySQL(MysqlAttribute_zczx_shandong.url.getAttribute(), MysqlAttribute_zczx_shandong.user.getAttribute(), MysqlAttribute_zczx_shandong.password.getAttribute());
        // 插入数据
        OperationMySQL.insertMySQL(disposeListConnection, MysqlAttribute_zczx_shandong.tableName.getAttribute(), MysqlAttribute_zczx_shandong.fields.getAttribute(), disposeList);

        // 处理花名册的数据
        CopyOnWriteArrayList<String> disposeRosterData = ReadSHRosterData.readSHRosterData("C:\\Users\\Administrator\\Desktop\\人员信息模版（上海）.xlsx", 0, 7, "2021-02");
        // 连接数据库
        Connection disposeRosterDataConnection = OperationMySQL.connectMySQL(MysqlAttribute_renyuan_shandong.url.getAttribute(), MysqlAttribute_renyuan_shandong.user.getAttribute(), MysqlAttribute_renyuan_shandong.password.getAttribute());
        // 插入数据
        OperationMySQL.insertMySQL(disposeRosterDataConnection, MysqlAttribute_renyuan_shandong.tableName.getAttribute(), MysqlAttribute_renyuan_shandong.fields.getAttribute(), disposeRosterData);
    }
}
