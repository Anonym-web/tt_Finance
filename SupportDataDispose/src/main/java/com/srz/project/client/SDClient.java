package com.srz.project.client;

import com.srz.project.connection.OperationMySQL;
import com.srz.project.method.DisposeSDSupportCenterData;
import com.srz.project.method.ReadSDRosterData;
import com.srz.project.method.ReadSDSupportCenterData;
import com.srz.project.utility.IOUtility;
import com.srz.project.utility.MysqlAttribute_renyuan_shandong;
import com.srz.project.utility.MysqlAttribute_zczx_shandong;
import com.srz.project.utility.MysqlAttribute_zczx_ys_shandong;

import java.io.BufferedWriter;
import java.sql.Connection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SDClient {
    public static void main(String[] args) {
        // 存储原数据
//        CopyOnWriteArrayList<String> rawDataList = ReadSDSupportCenterData.readSDSupportCenterData("G:\\铁通工作\\自己接到的\\省份数据\\山东\\2021-02", 0, 14);
//        for (String line : rawDataList) {
//            System.out.println(line);
//        }
//        // 连接数据库
//        Connection rawDataListConnection = OperationMySQL.connectMySQL(MysqlAttribute_zczx_ys_shandong.url.getAttribute(), MysqlAttribute_zczx_ys_shandong.user.getAttribute(), MysqlAttribute_zczx_ys_shandong.password.getAttribute());
////        // 插入数据
//        OperationMySQL.insertMySQL(rawDataListConnection, MysqlAttribute_zczx_ys_shandong.tableName.getAttribute(), MysqlAttribute_zczx_ys_shandong.fields.getAttribute(), rawDataList);
        // 存储处理后的支撑数据
//        CopyOnWriteArrayList<String> disposeList = DisposeSDSupportCenterData.disposeSDSupportCenterData(rawDataList);
//        try {
//            BufferedWriter writer = IOUtility.bufferWriter("G:\\铁通工作\\睿 交接的项目\\省份文件  SupportDataDispose 工程\\2021-2月.txt", "utf-8");
//            writer.append("所属省份|所属地市|部门档案名称|总体科目名称|科目编号|一级科目名称|二级科目名称|三级科目名称|四级科目名称|五级科目名称|成本费用类型|成本投入市场类型|客户类型|科目余额|直接收支利|分摊综合收支利|所属类型|统计时间|成本类型明细|项目|是否为末级");
//            writer.newLine();
//            for (String line : disposeList) {
//                writer.append(line);
//                writer.newLine();
//            }
//            writer.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        ReadSDSupportCenterData.readText("G:\\铁通工作\\交接的项目\\省份文件\\a.txt");

        // 连接数据库  上面的别走数据库了，请生成txt手动导入
//        Connection disposeListConnection = OperationMySQL.connectMySQL(MysqlAttribute_zczx_shandong.url.getAttribute(), MysqlAttribute_zczx_shandong.user.getAttribute(), MysqlAttribute_zczx_shandong.password.getAttribute());
//        // 插入数据
//        OperationMySQL.insertMySQL(disposeListConnection, MysqlAttribute_zczx_shandong.tableName.getAttribute(), MysqlAttribute_zczx_shandong.fields.getAttribute(), disposeList);

        // 存储处理后的花名册的数据
        CopyOnWriteArrayList<String> disposeRosterData = ReadSDRosterData.readRosterData("G:\\铁通工作\\睿 交接的项目\\省份文件  SupportDataDispose 工程\\山东数据\\山东十月\\花名册\\2020年10月花名册.xls", 3, 5, "2021-02");
        for (String disposeRosterDatum : disposeRosterData) {
            System.out.println(disposeRosterDatum);
        }
//        // 连接数据库
        Connection disposeRosterDataConnection = OperationMySQL.connectMySQL(MysqlAttribute_renyuan_shandong.url.getAttribute(), MysqlAttribute_renyuan_shandong.user.getAttribute(), MysqlAttribute_renyuan_shandong.password.getAttribute());
        // 插入数据
        OperationMySQL.insertMySQL(disposeRosterDataConnection, MysqlAttribute_renyuan_shandong.tableName.getAttribute(), MysqlAttribute_renyuan_shandong.fields.getAttribute(), disposeRosterData);
    }
}
