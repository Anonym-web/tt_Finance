package cn.parse.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import tt.connect.mysql.OperationMySQL;
import tt.utility.IOUtility;
import tt.utility.TimeUtility;

public class ParseJsonMethod {

    /**
     * 清洗逻辑
     *
     * @param inputPath（String）     文件输入路径
     * @param outputPath（String）    文件输出路径
     * @param inputCharSet（String）  输入文件的编码格式
     * @param outputCharSet（String） 输出文件的编码格式
     */
    public static void parseJsonMethod(String inputPath, String outputPath, String inputCharSet, String outputCharSet)
            throws Exception {
        List<String> list = new ArrayList<String>();
        // 存储文本中每一行数据
        List<String> line = new ArrayList<String>();
        // 读流
        BufferedReader reader;
        reader = IOUtility.bufferReader(inputPath, inputCharSet);
        // 写流
        BufferedWriter writer = IOUtility.bufferWriter(outputPath, outputCharSet);
        // 添加表头
        writer.append("Time|File|Name|Telephone|Role|Type");
        writer.newLine();
        String each_line;
        while (!((each_line = reader.readLine()) == null)) {
            if (!each_line.trim().equals(""))
                line.add(each_line);
        }
        for (String s : line) {
            if (!s.contains("terminal")) {
                System.out.println(s);
            }
            JSONObject json = JSONObject.fromObject(s);
            // 筛选掉username字段为null的情况
            if (json.getString("username").split("\\(", -1).length != 1 && !json.getString("displayName").equals("null")) {
                String file = TimeUtility.stampToDate(json.getString("time")).concat("|").concat(
                        json.getString("displayName").split("/", -1)[json.getString("displayName").split("/", -1).length - 1])
                        .concat("|").concat(json.getString("username").replace(")", "").split("\\(", -1)[0]).concat("|").concat(
                                json.getString("username").replace(")", "").split("\\(", -1)[1]).concat("|").concat(
                                json.getString("userrole"));
                if (json.getString("browser").contains("\"terminal\":\"APP\"")) {
                    // 手机端 0
                    writer.append(file.concat("|0"));
                    writer.newLine();
                    list.add(file.concat("|0"));
                } else {
                    // PC端 1
                    writer.append(file.concat("|1"));
                    writer.newLine();
                    list.add(file.concat("|1"));
                }
            }
        }
        System.out.println("文件处理完成");
//        // 连接数据库
//        Connection conn = OperationMySQL.connectMySQL();
//        // 插入数据
//        OperationMySQL.insertMySQL(conn, list);
//        // 关闭流
//        reader.close();
//        writer.close();
    }
}
