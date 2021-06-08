package com.srz.project.client;

import com.srz.project.connection.OperationPostgresql;
import com.srz.project.method.DisposeSpectrometer;
import com.srz.project.utility.MysqlAttribute_tt_dict;
import com.srz.project.utility.WriterExcelUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class OpticalSplitterClient {
    public static void main(String[] args) {
        try {
            List<String> arrayList = new ArrayList<>();
            Map<String,String> Spectrometer = DisposeSpectrometer.parse("G:\\铁通工作\\自己接到的\\分业务-应付-收入 海波龙解析 文浩提供\\分光器字典表\\全量分光器\\全量分光器解析.xlsx",0,3);
            for (String s : Spectrometer.keySet()) {
                /*map-key*/
                String key = s;
                String value = Spectrometer.get(key);
                arrayList.add(key+"|"+value);
            }
            for (String s : arrayList) {
                System.out.println(s);
            }
            /*Connection connection = OperationPostgresql.connectMySQL(MysqlAttribute_tt_dict.url.getAttribute(),MysqlAttribute_tt_dict.user.getAttribute(),MysqlAttribute_tt_dict.password.getAttribute());
            OperationPostgresql.insertMySQL(connection,MysqlAttribute_tt_dict.tableName.getAttribute(),MysqlAttribute_tt_dict.fields.getAttribute(),arrayList);*/
            File file = new File("E:\\localhost.txt");
            Writer out = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(out);
            for (String s : arrayList) {
                bw.write(s);
                bw.newLine();
                bw.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
