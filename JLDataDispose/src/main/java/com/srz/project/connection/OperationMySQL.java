package com.srz.project.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class OperationMySQL {

    private static Connection conn = null;
    static String driver = "com.mysql.jdbc.Driver";

    public static Connection connectMySQL(String url, String user, String password) {
        // 加载驱动程序
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            if (!conn.isClosed()) {
                System.out.println("成功连接数据库");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void insertMySQL(Connection conn, String tableName, String fields, List<String> list) {
        PreparedStatement preparedStatement = null;
        try {
            StringBuilder values = new StringBuilder();
            for (int i = 0; i < fields.split(",").length; i++) {
                if (i != fields.split(",").length - 1) {
                    values.append("?,");
                } else {
                    values.append("?");
                }
            }
            String sql = "INSERT INTO ".concat(tableName).concat("(".concat(fields)).concat(") VALUES(".concat(values.toString()).concat(")"));
            preparedStatement = conn.prepareStatement(sql);
            for (String line : list) {
                for (int i = 0; i < fields.split(",").length; i++) {
                    preparedStatement.setString(i + 1, line.split("\\|", -1)[i]);
                }
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                conn.close();
                System.out.println("数据插入完成");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
