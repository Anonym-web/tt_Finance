package project.client;

import project.connection.OperationMySQL;
import project.method.DisposeStripLine;
import project.database_property.ods_txys;
import java.sql.Connection;
import java.util.List;

public class DisposeStripLineClient {
    public static void main(String[] args) {
        List<String> disposeData = DisposeStripLine.disposeStripLine("G:\\铁通工作\\交接的项目\\职位主族子族关系表—赵鑫1206.xlsx", 0, 6);
//        // 连接数据库
//        Connection projectBusinessConnection_1 = OperationMySQL.connectMySQL(ods_txys.url.getAttribute(), ods_txys.user.getAttribute(), ods_txys.password.getAttribute());
//        // 插入数据
//        OperationMySQL.insertMySQL(projectBusinessConnection_1, ods_txys.tableName.getAttribute(), ods_txys.fields.getAttribute(), disposeData);
    }
}
