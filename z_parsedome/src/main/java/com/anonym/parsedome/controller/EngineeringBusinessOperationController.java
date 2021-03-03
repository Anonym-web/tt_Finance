package com.anonym.parsedome.controller;


import com.anonym.parsedome.mapper.ttprogram.master.*;
import com.anonym.parsedome.mapper.ttprogram.salveof.*;
import com.anonym.parsedome.model.DataImport;
import com.anonym.parsedome.model.ResultSet;
import com.anonym.parsedome.service.DisposeEngineeringBusiness;
import com.anonym.parsedome.service.DisposeProjectBusinessData;
import com.anonym.parsedome.service.DisposeProjectBusiness_1;
import com.anonym.parsedome.service.DisposeProjectBusiness_2;
import com.anonym.parsedome.util.GetFilePath;
import com.anonym.parsedome.util.InterfaceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EngineeringBusinessOperationController {

    @Resource
    private engineering_businessMapper engineeringBusinessMapper;

    @Resource
    private engineering_businessMapperTest engineeringBusinessMapperTest;

    @Resource
    private gc_bidMapper gcBidMapper;

    @Resource
    private gc_bidMapperTest gcBidMapperTest;

    @Resource
    private gc_business1Mapper gcBusiness1Mapper;

    @Resource
    private gc_business1MapperTest gcBusiness1MapperTest;

    @Resource
    private gc_business2Mapper gcBusiness2Mapper;

    @Resource
    private gc_business2MapperTest gcBusiness2MapperTest;

    @Resource
    private gc_construction_serviceMapper gcConstructionServiceMapper;

    @Resource
    private gc_construction_serviceMapperTest gcConstructionServiceMapperTest;

    @Resource
    private gc_contract_customerMapper gcContractCustomerMapper;

    @Resource
    private gc_contract_customerMapperTest gcContractCustomerMapperTest;

    @Resource
    private gc_contract_reserveMapper gcContractReserveMapper;

    @Resource
    private gc_contract_reserveMapperTest gcContractReserveMapperTest;

    @Resource
    private gc_income_summaryMapper gcIncomeSummaryMapper;

    @Resource
    private gc_income_summaryMapperTest gcIncomeSummaryMapperTest;



    /*引入日志*/
    private static Logger log = LoggerFactory.getLogger(EngineeringBusinessOperationController.class);

    @RequestMapping("/engineeringBusiness")
    public ResultSet engineeringBusiness(String path, int islocalhost,DataImport dataImport,String time){
        ResultSet resultSet = new ResultSet();
        int totalTemp = 0;
        // excel路径
        //统计每个公司的实际花费的费用，包括取暖费，交通费
        String excelPath = path;
        List<String> fileList = new ArrayList<>();
        GetFilePath.getFilePath(new File(excelPath), fileList);
        try {
            for (String fileName : fileList) {
                //===================================工程业务总表A-AJ列开始================================================
                int gcBusiness1 = gc_business1(fileName,islocalhost,time,gcBusiness1Mapper,gcBusiness1MapperTest);
                if(gcBusiness1 > 0){
                    /*证明执行成功了*/
                    totalTemp = totalTemp+1;
                    log.info("=======================工程业务总表A-AJ列导入成功=======================================");
                }
                //===================================工程业务总表A-AJ列结束================================================


                //===================================工程业务总表AK-CP列开始===============================================
                int gcBusiness2 = gc_business2(fileName,islocalhost,time,gcBusiness2Mapper,gcBusiness2MapperTest);
                if(gcBusiness2 > 0){
                    /*证明执行成功了*/
                    totalTemp = totalTemp+1;
                    log.info("===========================工程业务总表AK-CP列导入成功=================================");
                }
                //===================================工程业务总表AK-CP列结束===========================================


                //=======================收入汇总表开始================================================================
                int gcIncomeSummary = gc_income_summary(fileName,islocalhost,time,gcIncomeSummaryMapper,gcIncomeSummaryMapperTest);
                if(gcIncomeSummary > 0){
                    totalTemp = totalTemp+1;
                    log.info("=======================收入汇总表导入成功==============================================");
                }
                //==============================收入汇总表结束=========================================================


                //===============================投中标明细开始=========================================================
                int gcBid = gc_bid(fileName,islocalhost,time,gcBidMapper,gcBidMapperTest);
                if(gcBid > 0){
                    totalTemp = totalTemp+1;
                    log.info("=======================投中标明细导入成功==============================================");
                }
                //===============================投中标明细结束=========================================================


                //===============================合同汇总表-客户开始====================================================
                int gcContractCustomer = gc_contract_customer(fileName,islocalhost,time,gcContractCustomerMapper,gcContractCustomerMapperTest);
                if(gcContractCustomer > 0){
                    totalTemp = totalTemp+1;
                    log.info("=======================合同汇总表-客户导入成功=========================================");
                }

                //===============================合同汇总表-客户结束====================================================


                //===============================合同储备情况开始=======================================================
                int gcContractReserve = gc_contract_reserve(fileName,islocalhost,time,gcContractReserveMapper,gcContractReserveMapperTest);
                if(gcContractReserve > 0){
                    totalTemp = totalTemp+1;
                    log.info("========================合同储备情况导入成功===========================================");
                }
                //===============================合同储备情况结束=======================================================


                //===============================工程业务情况总表重新解析开始===========================================
                int engineeringBusiness = engineering_business(fileName,islocalhost,time,engineeringBusinessMapper,engineeringBusinessMapperTest);
                if(engineeringBusiness > 0){
                    totalTemp = totalTemp+1;
                    log.info("=====================工程业务情况总表重新解析导入成功==================================");
                }
                //===============================工程业务情况总表重新解析结束===========================================


                //=====================KTT1101工程建设与服务收支利情况表-分业务开始=====================================
                int gcConstructionService = gc_construction_service(fileName,islocalhost,time,gcConstructionServiceMapper,gcConstructionServiceMapperTest);
                if(gcConstructionService > 0){
                    totalTemp = totalTemp+1;
                    log.info("==============KTT1101工程建设与服务收支利情况表-分业务导入成功=========================");
                }
                //=====================KTT1101工程建设与服务收支利情况表-分业务结束=====================================
            }
        }catch (Exception e){
            resultSet.setRetCode("0");
            resultSet.setRetVal(e.getMessage());
            return resultSet;
        }

        return resultSet;
    }

    public int gc_business1(String path,int islocalhost,String time,gc_business1Mapper gcBusiness1Mapper,gc_business1MapperTest gcBusiness1MapperTest) throws Exception {
        int gcBusiness1 = 0;
        // 工程业务表1（gc_business1（A-AJ列））
        List<String> projectBusiness_1 = DisposeProjectBusiness_1.projectBusiness_1(path, 0, 36, time);
        for (String line : projectBusiness_1) {
            System.out.println(line);
        }
        String[][] arryList = InterfaceUtil.parseArrayListToArray(projectBusiness_1,36);
        if(islocalhost == 1){
            gcBusiness1 = gcBusiness1MapperTest.insertBatch(arryList);
        }else if(islocalhost == 2){
            gcBusiness1 = gcBusiness1Mapper.insertBatch(arryList);
        }else{
            throw new Exception("gcBusiness1出现错误");
        }
        return gcBusiness1;
    }

    public int gc_business2(String path,int islocalhost,String time,gc_business2Mapper gcBusiness2Mapper,gc_business2MapperTest gcBusiness2MapperTest) throws Exception {
        int gcBusiness2 = 0;
        // 工程业务表2（gc_business2（AK-CP列））
            List<String> projectBusiness_2 = DisposeProjectBusiness_2.projectBusiness_2(path, 0, 36, 94, time);
            for (String s : projectBusiness_2) {
                System.out.println(s);
            }
        String[][] arryList = InterfaceUtil.parseArrayListToArray(projectBusiness_2,60);
        if(islocalhost == 1){
            gcBusiness2 = gcBusiness2MapperTest.insertBatch(arryList);
        }else if(islocalhost == 2){
            gcBusiness2 = gcBusiness2Mapper.insertBatch(arryList);
        }else{
            throw new Exception("gcBusiness2出现错误");
        }
        return gcBusiness2;
    }

    public int gc_income_summary(String path,int islocalhost,String time,gc_income_summaryMapper gcIncomeSummaryMapper,gc_income_summaryMapperTest gcIncomeSummaryMapperTest) throws Exception {
        int gcIncomeSummary = 0;
        // 收入汇总表（gc_income_summary）
            List<String> incomeSummary = DisposeProjectBusinessData.disposeProjectBusinessData(path, 1, 50, ",本月收入", "总计", time);
            for (String s : incomeSummary) {
                System.out.println(s);
            }
        String[][] arryList = InterfaceUtil.parseArrayListToArray(incomeSummary,51);
        if(islocalhost == 1){
            gcIncomeSummary = gcIncomeSummaryMapperTest.insertBatch(arryList);
        }else if(islocalhost == 2){
            gcIncomeSummary = gcIncomeSummaryMapper.insertBatch(arryList);
        }else{
            throw new Exception("gcIncomeSummary出现错误");
        }
        return gcIncomeSummary;
    }
    public int gc_bid(String path,int islocalhost,String time,gc_bidMapper gcBidMapper,gc_bidMapperTest gcBidMapperTest) throws Exception {
        int gcBid = 0;
        // 投中标明细（gc_bid）

            List<String> bidDetail = DisposeProjectBusinessData.disposeProjectBusinessData(path, 3, 36, "省分,", ",,,,,,,,,", time);
            for (String s : bidDetail) {
                System.out.println(s);
            }
        String[][] arryList = InterfaceUtil.parseArrayListToArray(bidDetail,37);
        if(islocalhost == 1){
            gcBid = gcBidMapperTest.insertBatch(arryList);
        }else if(islocalhost == 2){
            gcBid = gcBidMapper.insertBatch(arryList);
        }else{
            throw new Exception("gcBid出现错误");
        }

        return gcBid;
    }
    public int gc_contract_customer(String path,int islocalhost,String time,gc_contract_customerMapper gcContractCustomerMapper,gc_contract_customerMapperTest gcContractCustomerMapperTest) throws Exception {
        int gcContractCustomer = 0;
        // 合同汇总表-客户（gc_contract_customer）----------------------------------------
            List<String> contractSummaryClient = DisposeProjectBusinessData.disposeProjectBusinessData(path, 2, 52, "省分,", ",,,,,,,,,", time);
            for (String s : contractSummaryClient) {
                System.out.println(s);
            }
        String[][] arryList = InterfaceUtil.parseArrayListToArray(contractSummaryClient,53);
        if(islocalhost == 1){
            gcContractCustomer = gcContractCustomerMapperTest.insertBatch(arryList);
        }else if(islocalhost == 2){
            gcContractCustomer = gcContractCustomerMapper.insertBatch(arryList);
        }else{
            throw new Exception("gcContractCustomer出现错误");
        }
        return gcContractCustomer;
    }
    public int gc_contract_reserve(String path,int islocalhost,String time,gc_contract_reserveMapper gcContractReserveMapper,gc_contract_reserveMapperTest gcContractReserveMapperTest) throws Exception {
        int gcContractReserve = 0;
        // 合同储备情况（gc_contract_reserve）
            List<String> contractReserves = DisposeProjectBusinessData.disposeProjectBusinessData(path, 7, 15, "省分,", ",,,,,,,,,", time);
            for (String contractReserve : contractReserves) {
                System.out.println(contractReserve);
            }
        String[][] arryList = InterfaceUtil.parseArrayListToArray(contractReserves,16);
        if(islocalhost == 1){
            gcContractReserve = gcContractReserveMapperTest.insertBatch(arryList);
        }else if(islocalhost == 2){
            gcContractReserve = gcContractReserveMapper.insertBatch(arryList);
        }else{
            throw new Exception("gcContractReserve出现错误");
        }

        return gcContractReserve;
    }
    public int engineering_business(String path,int islocalhost,String time,engineering_businessMapper engineeringBusinessMapper,engineering_businessMapperTest engineeringBusinessMapperTest) throws Exception {
        int engineeringBusinessTemp = 0;
        // 工程业务情况表（gc_business1（A-AJ列），会对这个表中的部分字段进行排序）
            List<String> engineeringBusiness = DisposeEngineeringBusiness.engineeringBusiness(path, 0, 36, time);
            for (int i = 0; i < engineeringBusiness.size(); i++) {
                if (engineeringBusiness.get(i).contains("总计")) {
                    System.out.println(engineeringBusiness.get(i));
                    engineeringBusiness.set(i, engineeringBusiness.get(i).replaceAll(",,", ",0,").replace(engineeringBusiness.get(i).split(",", -1)[engineeringBusiness.get(i).split(",", -1).length - 2].concat(","), engineeringBusiness.get(i).split(",", -1)[engineeringBusiness.get(i).split(",", -1).length - 2].concat(",0")));
                    System.out.println(engineeringBusiness.get(i));
                }
            }
            for (int i = 0; i < engineeringBusiness.size(); i++) {
                // 将集合中为空的元素替换为0
                if (engineeringBusiness.get(i).contains(",,")) {
                    engineeringBusiness.set(i, engineeringBusiness.get(i).replaceAll(",,", ",0,").replaceAll(",,", ",0,"));
                }
                if (engineeringBusiness.get(i).split(",", -1)[engineeringBusiness.get(i).split(",", -1).length - 1].equals("")) {
                    engineeringBusiness.set(i, engineeringBusiness.get(i).concat("0"));

                }
            }
            for (String business : engineeringBusiness) {
                System.out.println(business);
            }
        String[][] arryList = InterfaceUtil.parseArrayListToArray(engineeringBusiness,36);
        if(islocalhost == 1){
            engineeringBusinessTemp = engineeringBusinessMapperTest.insertBatch(arryList);
        }else if(islocalhost == 2){
            engineeringBusinessTemp = engineeringBusinessMapper.insertBatch(arryList);
        }else{
            throw new Exception("engineeringBusinessTemp出现错误");
        }
        return engineeringBusinessTemp;
    }
    public int gc_construction_service(String path,int islocalhost,String time,gc_construction_serviceMapper gcConstructionServiceMapper,gc_construction_serviceMapperTest gcConstructionServiceMapperTest) throws Exception {
        int gcConstructionService = 0;
        // KTT1101工程建设与服务收支利情况表-分业务（gc_construction_service）***************************
            List<String> projectConstruction = DisposeProjectBusinessData.disposeProjectBusinessData(path, 8, 38, ",,中移铁通有限公司(总分组织架构),", ",,,,,,,,,", time);
            for (int i = 0; i < projectConstruction.size(); i++) {
                if (projectConstruction.get(i).split(",", -1)[0].equals("")) {
                    projectConstruction.set(i, projectConstruction.get(i).replaceFirst(",", projectConstruction.get(i - 1).split(",", -1)[0].concat(",")));
                }
            }

            for (String s : projectConstruction) {
                System.out.println(s);
            }
        String[][] arryList = InterfaceUtil.parseArrayListToArray(projectConstruction,39);
        if(islocalhost == 1){
            gcConstructionService = gcConstructionServiceMapperTest.insertBatch(arryList);
        }else if(islocalhost == 2){
            gcConstructionService = gcConstructionServiceMapper.insertBatch(arryList);
        }else{
            throw new Exception("gcConstructionService出现错误");
        }
        return gcConstructionService;
    }
}
