package com.anonym.parsedome.controller;

import com.anonym.parsedome.mapper.ttwhmis.net_bazaar_businessMapper;
import com.anonym.parsedome.mapper.ttwhmis.net_business_provinceMapper;
import com.anonym.parsedome.mapper.ttwhmis.net_financeMapper;
import com.anonym.parsedome.mapper.ttwhmis.net_province_bazaarMapper;
import com.anonym.parsedome.service.InterNetData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/internet")
public class InterNetClientController {

    @Resource
    private net_financeMapper net_finance;

    @Resource
    private net_business_provinceMapper net_business_province;

    @Resource
    private net_province_bazaarMapper net_province_bazaar;

    @Resource
    private net_bazaar_businessMapper net_bazaar_business;

    /**
     * 解析v1中的收入明细
     * @return
     */
    @RequestMapping("/net_finance")
    public int net_finance() {
        int netFinanceTemp = 0;
        try{
            //        //flag如果是1，解析的就是v1，flag如果是2，解析的就是v2，
            CopyOnWriteArrayList<String> sheet0ArrayList = InterNetData.networkManagementParse("G:\\铁通工作\\自己接到的\\2020年12月网络管理技术服务定期数据V1.xlsx",0,39,1);
            String[][] arrayList = new String[sheet0ArrayList.size()][40];
            for (int i = 0;i < arrayList.length;i++) {
                String[] temp = sheet0ArrayList.get(i).split(",");
                for(int j = 0;j<temp.length;j++){
                    arrayList[i][j] = temp[j];
                }
            }
//        // 连接数据库net_finance （收入明细表 没有检查）
            netFinanceTemp = net_finance.insertBatch(arrayList);
            if(netFinanceTemp <= 0){
                throw new Exception("netFinance出错");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return netFinanceTemp;




        //-----------------------------------------------------------------------------------------------------------------------

//
    }


    /**
     * 解析v1中的分业务
     * @return
     * @throws Exception
     */
    @RequestMapping("/net_business_province")
    public int net_business_province(){
        int netBusinessProvinceTemp = 0;
        try {
            CopyOnWriteArrayList<String> sheet1ArrayList = InterNetData.networkManagementParse("G:\\铁通工作\\自己接到的\\2020年12月网络管理技术服务定期数据V1.xlsx",1,39,1);
            // 连接数据库net_business_province KTT1001网络管理技术服务收支利情况表-分业务（有检查）
            String[][] arrayList = new String[sheet1ArrayList.size()][40];
            for (int i = 0;i < arrayList.length;i++) {
                String[] temp = sheet1ArrayList.get(i).split(",");
                for(int j = 0;j<temp.length;j++){
                    arrayList[i][j] = temp[j];
                }
            }
            netBusinessProvinceTemp = net_business_province.insertBatch(arrayList);
            if(netBusinessProvinceTemp <= 0){
                throw new Exception("netBusinessProvince出错");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return netBusinessProvinceTemp;
    }


    /**
     * 解析v1中的分市场
     * @return
     * @throws Exception
     */
    @RequestMapping("/net_province_bazaar")
    public int net_province_bazaar() throws Exception {

        int netProvinceBazaarTemp = 0;
        try {
            CopyOnWriteArrayList<String> sheet2ArrayList = InterNetData.networkManagementParse("G:\\铁通工作\\自己接到的\\2020年12月网络管理技术服务定期数据V1.xlsx",2,39,1);
            // 连接数据库net_province_bazaar KTT1001网络管理技术服务收支利情况表-分市场 （没有检查）
            String[][] arrayList = new String[sheet2ArrayList.size()][40];
            for (int i = 0;i < arrayList.length;i++) {
                String[] temp = sheet2ArrayList.get(i).split(",");
                for(int j = 0;j<temp.length;j++){
                    arrayList[i][j] = temp[j];
                }
            }
            netProvinceBazaarTemp = net_province_bazaar.insertBatch(arrayList);
            if(netProvinceBazaarTemp <= 0){
                throw new Exception("net_province_bazaar出错");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return netProvinceBazaarTemp;
    }


    /**
     * 解决v2中的分业务分市场
     * @return
     * @throws Exception
     */
    @RequestMapping("/net_bazaar_business")
    public int net_bazaar_business() throws Exception {

        int netBazaarBusinessTemp = 0;
        try {
            CopyOnWriteArrayList<String> v2sheet0ArrayList = InterNetData.networkManagementParse("G:\\铁通工作\\自己接到的\\2020年12月网络管理技术服务定期数据V2.xlsx",0,11,2);
            String[][] arrayList = new String[v2sheet0ArrayList.size()][40];
            for (int i = 0;i < arrayList.length;i++) {
                String[] temp = v2sheet0ArrayList.get(i).split(",");
                for(int j = 0;j<temp.length;j++){
                    arrayList[i][j] = temp[j];
                }
            }
            // 连接数据库net_bazaar_business   v2的 KTT1001网络管理技术服务收支利情况表-分业务分市场
            netBazaarBusinessTemp = net_bazaar_business.insertBatch(arrayList);
            if(netBazaarBusinessTemp <= 0){
                throw new Exception("net_bazaar_business出错");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return netBazaarBusinessTemp;
    }
}