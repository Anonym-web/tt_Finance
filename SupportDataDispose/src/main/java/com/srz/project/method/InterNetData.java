package com.srz.project.method;

import com.srz.project.utility.ExcelUtil;
import com.srz.project.utility.GetFilePath;
import com.srz.project.utility.XlsmUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InterNetData {

    // 存储文件路径
    static List<String> fileList = new ArrayList<>();
    // 装载支撑数据
    static CopyOnWriteArrayList<StringBuilder> list = new CopyOnWriteArrayList<>();

    // 装载处理后的支撑数据
    static CopyOnWriteArrayList<String> rawDataList = new CopyOnWriteArrayList<>();

    public static CopyOnWriteArrayList<String> networkManagementParse(String inputPath, int sheetIndex, int lastCellNum,int flag) {
        try{
            /*//获取文件路径
            GetFilePath.getFilePath(new File(inputPath), fileList);*/

            //for (String fileName : fileList) {
                // 开关
                int OFF = 0;
                inputPath = inputPath.replace("\\", "/");
                //得到文件名
                String s = inputPath.split("/",-1)[inputPath.split("/", -1).length - 1];
                String time = s.split("月",-1)[0];
                //得到月份
                String month = time.split("年",-1)[1];
                /*判断月份是否大于10*/
                if(Integer.valueOf(month) < 10){
                    month = 0+month;
                }
                /*统计时间*/
                String staticsTime = time.split("年",-1)[0].concat("-").concat(month);
//                String staticsTime = "2020-03";
                /*读取文件*/
                list = (CopyOnWriteArrayList<StringBuilder>) ExcelUtil.excelUtil(inputPath,sheetIndex,lastCellNum);
                for (StringBuilder line : list) {
                    if(flag == 1){
                        //过滤表头
                        if(line.toString().startsWith("||中移铁通")){
                            OFF = 1;
                        }
                        //过滤表尾
                        if (sheetIndex == 0 && line.toString().startsWith("检查")){
                            OFF = 0;
                        }
                        //v1表
                        if(OFF == 1 && !line.toString().startsWith("||中移铁通") && !line.toString().startsWith("||")){
                            /*如果开关打开了，并且开头正确*/
                            //获取编号
                            String number = line.toString().split("\\|",-1)[1];
                            String lineConcat = line.toString().concat("|").concat(staticsTime);
                            rawDataList.add(lineConcat);
                        }
                    }else if(flag == 2){
                        //过滤表头
                        if(line.toString().startsWith("||中国移动")){
                            OFF = 1;
                        }
                        //过滤表尾
//                        if (sheetIndex == 0 && line.toString().startsWith("检查")){
//                            OFF = 0;
//                        }
                        //v2表
                        if(OFF == 1 && !line.toString().startsWith("||中国移动")){
//                            System.out.println(line);
                            /*如果开关打开了，并且开头正确*/
                            //获取编号
                            //String number = line.split("\\|",-1)[1];
                            String lineConcat = line.toString().concat("|").concat(staticsTime);
                            rawDataList.add(lineConcat);
                        }
                    }
                }
           // }

        }catch (Exception e){
            e.printStackTrace();
        }
        return rawDataList;
    }
}
