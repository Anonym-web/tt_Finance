package com.srz.project.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReadMysql {
    static String sql = null;
    static ResultSet resultSet = null;
    // 连接ip
    public static final String url = "jdbc:mysql://10.0.43.68/tianxin";
    // 驱动
    public static final String name = "com.mysql.jdbc.Driver";
    // 用户名
    public static final String user = "root";
    // 密码
    public static final String password = "zytop@123";

    public static Connection conn = null;
    public static PreparedStatement pst = null;

    public static List<String> readMysql(String tableName, int columnNum) {
        // 装载处理完后的数据
        List<String> disposeData = new ArrayList<String>();
        // SQL语句
        sql = "select * from ".concat(tableName);

        try {
            // 指定连接类型
            Class.forName(name);
            // 获取连接
            conn = DriverManager.getConnection(url, user, password);
            // 准备执行语句
            pst = conn.prepareStatement(sql);
            // 执行语句，得到结果集
            resultSet = pst.executeQuery();
            // 承接数据库处理后的数据
            String line = "";
            // 将数据库中的数据放入集合中
            while (resultSet.next()) {
                for (int i = 1; i < columnNum + 1; i++) {
                    if (i != columnNum)
                        line = line.concat(resultSet.getString(i)).concat("|");
                    else
                        line = line.concat(resultSet.getString(i));
                }
                line = line.replace(" ", "");
                disposeData.add(line);
                line = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭连接
                resultSet.close();
                conn.close();
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("文件读取完成！");
        }
        return disposeData;
    }
}
