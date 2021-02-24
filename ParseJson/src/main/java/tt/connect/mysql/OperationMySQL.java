package tt.connect.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 连接数据库的方法
 */
public class OperationMySQL {

    private static Connection conn = null;

    public static Connection connectMySQL() {
        // 加载驱动程序
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://10.0.43.68:3306/json文件处理";
        String user = "root";
        String password = "zytop@123";
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

    public static void insertMySQL(Connection conn, List<String> list) {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT INTO mis_log_data(check_time,check_path,person_name,person_tel,category,access_type) VALUES(?,?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(sql);
            for (String line : list) {
                preparedStatement.setString(1, line.split("\\|")[0].split(" ")[0]);
                preparedStatement.setString(2, line.split("\\|")[1]);
                preparedStatement.setString(3, line.split("\\|")[2]);
                preparedStatement.setString(4, line.split("\\|")[3]);
                preparedStatement.setString(5, line.split("\\|")[4]);
                preparedStatement.setString(6, line.split("\\|")[5]);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
