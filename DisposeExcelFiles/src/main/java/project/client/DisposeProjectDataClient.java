package project.client;

import project.connection.OperationMySQL;
import project.database_property.*;
import project.method.DisposeEngineeringBusiness;
import project.method.DisposeProjectBusinessData;
import project.method.DisposeProjectBusiness_1;
import project.method.DisposeProjectBusiness_2;
import project.utility.GetFilePath;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DisposeProjectDataClient {
    public static void main(String[] args) {
        // excel路径
        //统计每个公司的实际花费的费用，包括取暖费，交通费
        String excelPath = "G:\\铁通工作\\自己接到的\\12月工程业务";
        List<String> fileList = new ArrayList<>();
        GetFilePath.getFilePath(new File(excelPath), fileList);
        String time = "2020-12";
        List<String> projectBusiness_1 = null;
        for (String fileName : fileList) {

            String temp = null;
            // 时间
            String t = fileName.split("\\\\", -1)[fileName.split("\\\\").length - 1].split("月")[0].split("年")[1];
            if (Integer.valueOf(fileName.split("\\\\", -1)[fileName.split("\\\\").length - 1].split("月")[0].split("年")[1]) < 10) {
                temp = "0" + fileName.split("\\\\", -1)[fileName.split("\\\\").length - 1].split("月")[0].split("年")[1];
                time = time.split("-")[0].concat("-").concat(temp);
            } else {
                time = time.split("-")[0].concat("-").concat(fileName.split("\\\\", -1)[fileName.split("\\\\").length - 1].split("月")[0].split("年")[1]);
            }

            // 工程业务表1（gc_business1（A-AJ列））
//            projectBusiness_1 = DisposeProjectBusiness_1.projectBusiness_1(fileName, 0, 36, time);
//            for (String line : projectBusiness_1) {
//                System.out.println(line);
//            }
//
//           //连接数据库
//            Connection projectBusinessConnection_1 = OperationMySQL.connectMySQL(gc_business1.url.getAttribute(), gc_business1.user.getAttribute(), gc_business1.password.getAttribute());
//          // 插入数据
//            OperationMySQL.insertMySQL(projectBusinessConnection_1, gc_business1.tableName.getAttribute(), gc_business1.fields.getAttribute(), projectBusiness_1);

            // 工程业务表2（gc_business2（AK-CP列））
//            List<String> projectBusiness_2 = DisposeProjectBusiness_2.projectBusiness_2(fileName, 0, 36, 94, time);
//            for (String s : projectBusiness_2) {
//                System.out.println(s);
//            }
//        // 连接数据库
//          Connection projectBusinessConnection_2 = OperationMySQL.connectMySQL(gc_business2.url.getAttribute(), gc_business2.user.getAttribute(), gc_business2.password.getAttribute());
//        // 插入数据
//          OperationMySQL.insertMySQL(projectBusinessConnection_2, gc_business2.tableName.getAttribute(), gc_business2.fields.getAttribute(), projectBusiness_2);


            // 收入汇总表（gc_income_summary）
//            List<String> incomeSummary = DisposeProjectBusinessData.disposeProjectBusinessData(fileName, 1, 50, "|本月收入", "总计", time);
//            for (String s : incomeSummary) {
//                System.out.println(s);
//            }
//          // 连接数据库
//            Connection incomeSummaryConnection = OperationMySQL.connectMySQL(gc_income_summary.url.getAttribute(), gc_income_summary.user.getAttribute(), gc_income_summary.password.getAttribute());
//          // 插入数据
//            OperationMySQL.insertMySQL(incomeSummaryConnection, gc_income_summary.tableName.getAttribute(), gc_income_summary.fields.getAttribute(), incomeSummary);

            // 投中标明细（gc_bid）

//            List<String> bidDetail = DisposeProjectBusinessData.disposeProjectBusinessData(fileName, 3, 36, "省分|", "|||||||||", time);
//            for (String s : bidDetail) {
//                System.out.println(s);
//            }
//            // 连接数据库
//            Connection bidDetailConnection = OperationMySQL.connectMySQL(gc_bid.url.getAttribute(), gc_bid.user.getAttribute(), gc_bid.password.getAttribute());
//            // 插入数据
//            OperationMySQL.insertMySQL(bidDetailConnection, gc_bid.tableName.getAttribute(), gc_bid.fields.getAttribute(), bidDetail);


            // 合同汇总表-客户（gc_contract_customer）-----------------------------------------------------------------------------------------------------
//            List<String> contractSummaryClient = DisposeProjectBusinessData.disposeProjectBusinessData(fileName, 2, 52, "省分|", "|||||||||", time);
//            for (String s : contractSummaryClient) {
//                System.out.println(s);
//            }
//          // 连接数据库
//            Connection contractSummaryClientConnection = OperationMySQL.connectMySQL(gc_contract_customer.url.getAttribute(), gc_contract_customer.user.getAttribute(), gc_contract_customer.password.getAttribute());
//          // 插入数据
//            OperationMySQL.insertMySQL(contractSummaryClientConnection, gc_contract_customer.tableName.getAttribute(), gc_contract_customer.fields.getAttribute(), contractSummaryClient);


            // 合同储备情况（gc_contract_reserve）
//            List<String> contractReserves = DisposeProjectBusinessData.disposeProjectBusinessData(fileName, 7, 15, "省分|", "|||||||||", time);
//            for (String contractReserve : contractReserves) {
//                System.out.println(contractReserve);
//            }
//          // 连接数据库
//            Connection contractReservesConnection = OperationMySQL.connectMySQL(gc_contract_reserve.url.getAttribute(), gc_contract_reserve.user.getAttribute(), gc_contract_reserve.password.getAttribute());
//          // 插入数据
//            OperationMySQL.insertMySQL(contractReservesConnection, gc_contract_reserve.tableName.getAttribute(), gc_contract_reserve.fields.getAttribute(), contractReserves);


            // 工程业务情况表（gc_business1（A-AJ列），会对这个表中的部分字段进行排序）
//            List<String> engineeringBusiness = DisposeEngineeringBusiness.engineeringBusiness(fileName, 0, 36, time);
//            for (int i = 0; i < engineeringBusiness.size(); i++) {
//                if (engineeringBusiness.get(i).contains("总计")) {
//                    System.out.println(engineeringBusiness.get(i));
//                    engineeringBusiness.set(i, engineeringBusiness.get(i).replaceAll("\\|\\|", "|0|").replace(engineeringBusiness.get(i).split("\\|", -1)[engineeringBusiness.get(i).split("\\|", -1).length - 2].concat("|"), engineeringBusiness.get(i).split("\\|", -1)[engineeringBusiness.get(i).split("\\|", -1).length - 2].concat("|0")));
//                    System.out.println(engineeringBusiness.get(i));
//                }
//            }
//            for (int i = 0; i < engineeringBusiness.size(); i++) {
//                // 将集合中为空的元素替换为0
//                if (engineeringBusiness.get(i).contains("||")) {
//                    engineeringBusiness.set(i, engineeringBusiness.get(i).replaceAll("\\|\\|", "|0|").replaceAll("\\|\\|", "|0|"));
//                }
//                if (engineeringBusiness.get(i).split("\\|", -1)[engineeringBusiness.get(i).split("\\|", -1).length - 1].equals("")) {
//                    engineeringBusiness.set(i, engineeringBusiness.get(i).concat("0"));
//
//                }
//            }
//            for (String business : engineeringBusiness) {
//                System.out.println(business);
//            }
//            // 连接数据库
//            Connection engineeringBusinessConnection = OperationMySQL.connectMySQL(engineering_business.url.getAttribute(), engineering_business.user.getAttribute(), engineering_business.password.getAttribute());
//            // 插入数据
//            OperationMySQL.insertMySQL(engineeringBusinessConnection, engineering_business.tableName.getAttribute(), engineering_business.fields.getAttribute(), engineeringBusiness);


            // KTT1101工程建设与服务收支利情况表-分业务（gc_construction_service）***************************
//            List<String> projectConstruction = DisposeProjectBusinessData.disposeProjectBusinessData(fileName, 8, 38, "||中移铁通有限公司(总分组织架构)|", "|||||||||", time);
//            for (int i = 0; i < projectConstruction.size(); i++) {
//                if (projectConstruction.get(i).split("\\|", -1)[0].equals("")) {
//                    projectConstruction.set(i, projectConstruction.get(i).replaceFirst("\\|", projectConstruction.get(i - 1).split("\\|", -1)[0].concat("|")));
//                }
//            }
//
//            for (String s : projectConstruction) {
//                System.out.println(s);
//            }
        // 连接数据库
//        Connection projectConstructionConnection = OperationMySQL.connectMySQL(gc_construction_service.url.getAttribute(), gc_construction_service.user.getAttribute(), gc_construction_service.password.getAttribute());
//        // 插入数据
//        OperationMySQL.insertMySQL(projectConstructionConnection, gc_construction_service.tableName.getAttribute(), gc_construction_service.fields.getAttribute(), projectConstruction);
        }
    }
}
