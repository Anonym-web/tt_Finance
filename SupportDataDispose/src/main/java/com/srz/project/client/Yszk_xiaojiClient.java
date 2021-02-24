package com.srz.project.client;

import com.srz.project.connection.OperationMySQL;
import com.srz.project.connection.OperationPostgresql;
import com.srz.project.method.ReadYszkSubtotalData;
import com.srz.project.utility.MysqlAttribute_tt_yszk;

import java.net.ConnectException;
import java.sql.Connection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Yszk_xiaojiClient {

    public static void main(String[] args) {
        List<String> copyOnWriteArrayList = ReadYszkSubtotalData.yszkSubTotal("G:\\铁通工作\\自己接到的\\2012应收",0,38);
        for (String s : copyOnWriteArrayList) {
            System.out.println(s);
        }
//        Connection connect = OperationPostgresql.connectMySQL(MysqlAttribute_tt_yszk.url.getAttribute(),MysqlAttribute_tt_yszk.user.getAttribute(),MysqlAttribute_tt_yszk.password.getAttribute());
//        OperationPostgresql.insertMySQL(connect,MysqlAttribute_tt_yszk.tableName.getAttribute(),MysqlAttribute_tt_yszk.fields.getAttribute(),copyOnWriteArrayList);

    }
}
