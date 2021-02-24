package com.srz.project.method;

import com.srz.project.utility.ExcelUtil;
import com.srz.project.utility.GetFilePath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.srz.project.utility.ExcelUtil.excelUtil;

public class ReadYszkSubtotalData {
    // 存储文件路径
    static List<String> fileList = new ArrayList<>();

    public static List<String> yszkSubTotal(String inputPath,int sheetIndex,int lastCellNum) {

        List<StringBuilder> list = new ArrayList<>();
        GetFilePath.getFilePath(new File(inputPath), fileList);
        List<String> returnList = new ArrayList<>();
        try {
            for (String filename : fileList) {
                int OFF = 0;
                //拿到文件名称
                filename = filename.replace("\\","/");
                String documentName = filename.split("/",-1)[filename.split("/", -1).length - 1];
                //获取临时时间
                String timeTemp = documentName.split("\\.")[0].split("表")[1];
                //截取时间
                String time = timeTemp.substring(0,4).concat("-")+timeTemp.substring(4);

                //获取数据
                list = ExcelUtil.excelUtil(filename,sheetIndex,lastCellNum);
                for (StringBuilder line : list) {
                    if(line.toString().startsWith("中移铁通") || line.toString().startsWith("编码")){
                        OFF = 1;
                        continue;
                    }
                    if(OFF == 1){
                        String lineString = line.toString().concat("|").concat(time);
                        returnList.add(lineString);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnList;
    }
}
