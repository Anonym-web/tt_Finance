package com.anonym.parsedome.controller;


import com.anonym.parsedome.config.SnowflakeAlgorithm;
import com.anonym.parsedome.mapper.ttwhmis.master.net_bazaar_businessMapper;
import com.anonym.parsedome.mapper.ttwhmis.master.net_business_provinceMapper;
import com.anonym.parsedome.mapper.ttwhmis.master.net_financeMapper;
import com.anonym.parsedome.mapper.ttwhmis.master.net_province_bazaarMapper;
import com.anonym.parsedome.mapper.ttwhmis.salveof.net_bazaar_businessMapperTest;
import com.anonym.parsedome.mapper.ttwhmis.salveof.net_business_provinceMapperTest;
import com.anonym.parsedome.mapper.ttwhmis.salveof.net_financeMapperTest;
import com.anonym.parsedome.mapper.ttwhmis.salveof.net_province_bazaarMapperTest;
import com.anonym.parsedome.model.DataImport;
import com.anonym.parsedome.model.DateUtils;
import com.anonym.parsedome.model.ResultSet;
import com.anonym.parsedome.service.InterNetData;
import com.anonym.parsedome.util.InterfaceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CopyOnWriteArrayList;


@RestController
public class InterNetOperationController {

    public InterNetOperationController interNetTest;


    //打印日志
    private static Logger log = LoggerFactory.getLogger(InterNetOperationController.class);

    @Resource
    private net_financeMapper net_finance;

    @Resource
    private net_financeMapperTest net_financeTest;

    @Resource
    private net_business_provinceMapper net_business_province;

    @Resource
    private net_business_provinceMapperTest net_business_provinceTest;

    @Resource
    private net_province_bazaarMapper net_province_bazaar;

    @Resource
    private net_province_bazaarMapperTest net_province_bazaarTest;

    @Resource
    private net_bazaar_businessMapper net_bazaar_business;

    @Resource
    private net_bazaar_businessMapperTest net_bazaar_businessTest;

    @RequestMapping("/interNet")
    public ResultSet interNet(String path, int islocalhost, DataImport dataImport, HttpServletRequest request) throws Exception {
        if(!InterfaceUtil.pathJudgment(path,1)){
            /*证明路径不对*/
            throw new Exception("拒绝访问");
        }
        /*创建网络定期服务数据对象*/
        InterNetOperationController interNetTest = new InterNetOperationController();
        ResultSet resultSet = new ResultSet();
        /*分割path*/
        String[] pathTemp = path.split("@");
        int totalTemp = 0;
        try{
            //----------------------------------收入明细表解析开始-----------------------------------------------------
            int netFinance = interNetTest.net_finance(pathTemp[0],islocalhost,net_finance,net_financeTest);
            if(netFinance > 0){
                /*证明执行成功了*/
                totalTemp = totalTemp+1;
                log.info("===========================收入明细表导入成功==============================================");
            }
            //----------------------------------收入明细表解析结束-----------------------------------------------------

            //-------------------------------KTT1001网络管理技术服务收支利情况表-分业务(v1)开始-------------------------
            int netBusinessProvince = interNetTest.net_business_province(pathTemp[0],islocalhost,net_business_province,net_business_provinceTest);
            if(netBusinessProvince > 0){
                totalTemp = totalTemp+1;
                log.info("============KTT1001网络管理技术服务收支利情况表-分业务(v1)导入成功=========================");
            }
            //----------------------------KTT1001网络管理技术服务收支利情况表-分业务(v1)结束----------------------------

            //----------------------------KTT1001网络管理技术服务收支利情况表-分市场(v1)开始----------------------------
            int netProvinceBazaar = interNetTest.net_province_bazaar(pathTemp[0],islocalhost,net_province_bazaar,net_province_bazaarTest);
            if(netProvinceBazaar > 0){
                totalTemp = totalTemp+1;
                log.info("==================KTT1001网络管理技术服务收支利情况表-分市场(v1)=============================");
            }
            //----------------------------KTT1001网络管理技术服务收支利情况表-分市场(v1)结束----------------------------

            //----------------------------KTT1001网络管理技术服务收支利情况表-分业务分市场开始--------------------------
            int netBazaarBusiness = interNetTest.net_bazaar_business(pathTemp[1],islocalhost,net_bazaar_business,net_bazaar_businessTest);
            if(netBazaarBusiness > 0){
                totalTemp = totalTemp+1;
                log.info("====================KTT1001网络管理技术服务收支利情况表-分业务分市场=======================");
            }
            //----------------------------KTT1001网络管理技术服务收支利情况表-分业务分市场结束--------------------------

            if(totalTemp == 4){
                /*程序执行成功*/
                /*记录过程*/
                dataImport.setId(SnowflakeAlgorithm.uniqueLong());
                dataImport.setCreateTime(DateUtils.getDate());
                resultSet.setRetCode("1");
                resultSet.setDataRows("");
                return resultSet;
            }else{
                resultSet.setRetCode("0");
                resultSet.setRetVal("程序错误");
                return resultSet;
            }

        }catch(Exception e){
            e.printStackTrace();
            resultSet.setRetCode("0");
            resultSet.setRetVal(e.getMessage());
            return resultSet;
        }
    }

    /**
     * 收入明细表(v1)
     * @param path
     * @return
     * @throws Exception
     */
    public int net_finance(String path,int islocalhost,net_financeMapper net_financeTemp,net_financeMapperTest net_financeTestTemp) throws Exception {
        if(InterfaceUtil.pathJudgment(path,3)){
            throw new Exception("拒绝访问");
        }
        int netFinanceTemp = 0;
        //flag如果是1，解析的就是v1，flag如果是2，解析的就是v2，
        CopyOnWriteArrayList<String> sheet0ArrayList = InterNetData.networkManagementParse(path,0,39,1);
        String[][] arrayList = new String[sheet0ArrayList.size()][40];
        for (int i = 0;i < arrayList.length;i++) {
            String[] temp = sheet0ArrayList.get(i).split(",");
            for(int j = 0;j<temp.length;j++){
                arrayList[i][j] = temp[j];
            }
        }
       // 连接数据库net_finance （收入明细表 没有检查）
        if(islocalhost == 1){
            /*证明是localhost*/
            netFinanceTemp = net_financeTestTemp.insertBatch(arrayList);
        }else if(islocalhost == 2){
            /*证明是远端数据库*/
            netFinanceTemp = net_financeTemp.insertBatch(arrayList);
        }else{
            throw new Exception("是否导入本地出错");
        }
        if(netFinanceTemp <= 0){
            throw new Exception("netFinance出错");
        }
        return netFinanceTemp;
    }

    /**
     * KTT1001网络管理技术服务收支利情况表-分业务(v1)
     * @param path
     * @return
     * @throws Exception
     */
    public int net_business_province(String path,int islocalhost,net_business_provinceMapper net_business_provinceTemp,net_business_provinceMapperTest net_business_provinceTestTemp) throws Exception {
        if(InterfaceUtil.pathJudgment(path,3)){
            throw new Exception("拒绝访问");
        }
        int netBusinessProvinceTemp = 0;

            CopyOnWriteArrayList<String> sheet1ArrayList = InterNetData.networkManagementParse(path,1,39,1);
            // 连接数据库net_business_province KTT1001网络管理技术服务收支利情况表-分业务（有检查）
            String[][] arrayList = new String[sheet1ArrayList.size()][40];
            for (int i = 0;i < arrayList.length;i++) {
                String[] temp = sheet1ArrayList.get(i).split(",");
                for(int j = 0;j<temp.length;j++){
                    arrayList[i][j] = temp[j];
                }
            }
            if(islocalhost == 1){
                netBusinessProvinceTemp = net_business_provinceTestTemp.insertBatch(arrayList);
            }else if(islocalhost == 2){
                netBusinessProvinceTemp = net_business_provinceTemp.insertBatch(arrayList);
            }else{
                throw new Exception("是否本地导入出错");
            }
            if(netBusinessProvinceTemp <= 0){
                throw new Exception("netBusinessProvince出错");
            }
        return netBusinessProvinceTemp;
    }


    /**
     * KTT1001网络管理技术服务收支利情况表-分市场(v1)
     * @param path
     * @return
     * @throws Exception
     */
    public int net_province_bazaar(String path,int islocalhost,net_province_bazaarMapper net_province_bazaarTemp,net_province_bazaarMapperTest net_province_bazaarTestTemp) throws Exception {
        if(InterfaceUtil.pathJudgment(path,3)){
            throw new Exception("拒绝访问");
        }
        int netProvinceBazaarTemp = 0;

        CopyOnWriteArrayList<String> sheet2ArrayList = InterNetData.networkManagementParse(path,2,39,1);
        // 连接数据库net_province_bazaar KTT1001网络管理技术服务收支利情况表-分市场 （没有检查）
        String[][] arrayList = new String[sheet2ArrayList.size()][40];
        for (int i = 0;i < arrayList.length;i++) {
            String[] temp = sheet2ArrayList.get(i).split(",");
            for(int j = 0;j<temp.length;j++){
                arrayList[i][j] = temp[j];
            }
        }
        if(islocalhost == 1){
            netProvinceBazaarTemp = net_province_bazaarTestTemp.insertBatch(arrayList);
        }else if(islocalhost == 2){
            netProvinceBazaarTemp = net_province_bazaarTemp.insertBatch(arrayList);
        }else{
            throw new Exception("是否本地导入出错");
        }
        if(netProvinceBazaarTemp <= 0){
            throw new Exception("net_province_bazaar出错");
        }

        return netProvinceBazaarTemp;
    }


    /**
     * KTT1001网络管理技术服务收支利情况表-分业务分市场
     * @param path
     * @return
     * @throws Exception
     */
    public int net_bazaar_business(String path,int islocalhost,net_bazaar_businessMapper net_bazaar_businessTemp,net_bazaar_businessMapperTest net_bazaar_businessTestTemp) throws Exception {
        if(path.contains("@")){
            throw new Exception("拒绝访问");
        }
        int netBazaarBusinessTemp = 0;
        CopyOnWriteArrayList<String> v2sheet0ArrayList = InterNetData.networkManagementParse(path,0,11,2);
        String[][] arrayList = new String[v2sheet0ArrayList.size()][12];
        for (int i = 0;i < arrayList.length;i++) {
            String[] temp = v2sheet0ArrayList.get(i).split(",");
            for(int j = 0;j<temp.length;j++){
                arrayList[i][j] = temp[j];
            }
        }
        // 连接数据库net_bazaar_business   v2的 KTT1001网络管理技术服务收支利情况表-分业务分市场
        if(islocalhost == 1){
            netBazaarBusinessTemp = net_bazaar_businessTestTemp.insertBatch(arrayList);
        }else if(islocalhost == 2){
            netBazaarBusinessTemp = net_bazaar_businessTemp.insertBatch(arrayList);
        }else{
            throw new Exception("是否本地导入出错");
        }
        if(netBazaarBusinessTemp <= 0){
            throw new Exception("net_bazaar_business出错");
        }
        return netBazaarBusinessTemp;
    }
}
