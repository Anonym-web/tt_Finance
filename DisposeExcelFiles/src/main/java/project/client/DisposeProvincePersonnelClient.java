package project.client;

import project.connection.OperationMySQL;
import project.database_property.ods_people_shengfen;
import project.method.DisposeProvincePersonnel;
import java.sql.Connection;
import java.util.List;

public class DisposeProvincePersonnelClient {
    public static void main(String[] args) {
        String time = "2020-11";
        List<String> disposeLine = DisposeProvincePersonnel.disposeProvincePersonnel("G:\\铁通工作\\交接的项目\\11月.xlsx", 0, 2, 9, time);
        // 连接数据库
        Connection projectBusinessConnection_1 = OperationMySQL.connectMySQL(ods_people_shengfen.url.getAttribute(), ods_people_shengfen.user.getAttribute(), ods_people_shengfen.password.getAttribute());
        // 插入数据
        OperationMySQL.insertMySQL(projectBusinessConnection_1, ods_people_shengfen.tableName.getAttribute(), ods_people_shengfen.fields.getAttribute(), disposeLine);
    }
}
