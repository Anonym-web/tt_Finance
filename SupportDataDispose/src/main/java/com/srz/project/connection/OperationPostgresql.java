package com.srz.project.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class OperationPostgresql {
    // connection对象
    private static Connection conn = null;
    // mysql驱动
    static String driver = "org.postgresql.Driver";

    /**
     * 初始化connection对象
     *
     * @param url      mysql数据库的url信息，格式：jdbc:mysql://ip:3306/数据库名
     * @param user     mysql数据库的用户名
     * @param password mysql数据库的密码
     * @return connection对象
     */
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

    /**
     * 添加数据到数据库
     *
     * @param conn      connection对象
     * @param tableName 数据库表名称
     * @param fields    字段名，如果有条字段，字段之间按照`|`分割
     * @param list      装载数据的集合
     */
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
            preparedStatement = conn.prepareStatement("INSERT INTO ".concat(tableName).concat("(".concat(fields)).concat(") VALUES(".concat(values.toString()).concat(")")));
            for (String line : list) {
                for (int i = 0; i < fields.split(",").length; i++) {
                    preparedStatement.setString(i + 1, line.split("\\|", -1)[i]);
                }
                // 批量添加数据
                preparedStatement.addBatch();
            }
            // 批量执行
            preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭资源
                assert preparedStatement != null;
                preparedStatement.close();
                conn.close();
                System.out.println("数据插入完成");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
