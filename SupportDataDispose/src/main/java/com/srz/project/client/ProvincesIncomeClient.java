package com.srz.project.client;

import java.io.BufferedWriter;
import java.sql.Connection;

import com.srz.project.connection.OperationMySQL;
import com.srz.project.method.DisposeProvincesData;
import com.srz.project.method.ReadProvincesData;
import com.srz.project.utility.IOUtility;
import com.srz.project.utility.MysqlAttribute_provinces_income;

import java.util.concurrent.CopyOnWriteArrayList;

public class ProvincesIncomeClient {
    public static void main(String[] args) {
        // 存储原数据
        CopyOnWriteArrayList<String> rawDataList = ReadProvincesData.readProvincesData("E:\\辅助余额表", 0, 13, "2020-09");
        // 存储处理后的支撑数据
        CopyOnWriteArrayList<String> disposeList = DisposeProvincesData.disposeProvincesData(rawDataList);
//        // 将处理结果写入文件中
//        try {
//            BufferedWriter writer = IOUtility.bufferWriter("E://disposeFiles/a.txt", "utf-8");
//            // 添加表头
//            writer.append("所属省份|所属地市|部门档案名称|总体科目名称|科目编号|一级科目名称|二级科目名称|三级科目名称|四级科目名称|五级科目名称|成本费用类型|成本投入市场类型|客户类型|科目余额|直接收支利|分摊综合收支利|所属类型|统计时间|成本类型明细|项目|是否为末级|项目辅助核算名称|交易类型名称");
//            writer.newLine();
//            for (String line : disposeList) {
//                writer.append(line);
//                writer.newLine();
//            }
//            // 关闭资源
//            writer.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        // 连接数据库
//        Connection rawDataListConnection = OperationMySQL.connectMySQL(MysqlAttribute_provinces_income.url.getAttribute(), MysqlAttribute_provinces_income.user.getAttribute(), MysqlAttribute_provinces_income.password.getAttribute());
//        // 插入数据
//        OperationMySQL.insertMySQL(rawDataListConnection, MysqlAttribute_provinces_income.tableName.getAttribute(), MysqlAttribute_provinces_income.fields.getAttribute(), disposeList);
    }
}
