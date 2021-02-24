package com.srz.project.method;

import com.srz.project.utility.ExcelUtil;
import com.srz.project.utility.GetFilePath;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class VerifyFiles {
    /**
     * 获取文件表头，方便查看文件是否符合处理要求
     */
    public static void verifyFiles(String inputPath, int sheetIndex, int lastCellNum) throws Exception {
        // 存储文件路径
        List<String> fileList = new ArrayList<>();
        GetFilePath.getFilePath(new File(inputPath), fileList);
        // 存储表头和对应的文件
        Map<String, String> map = new HashMap<>();
        for (String fileName : fileList) {
            // 装载支撑数据
            CopyOnWriteArrayList<StringBuilder> list = (CopyOnWriteArrayList<StringBuilder>) ExcelUtil.excelUtil(fileName, sheetIndex, lastCellNum);
            for (StringBuilder line : list) {
                if (line.toString().startsWith("||收入")) {
                    map.put(fileName.split("\\\\", -1)[fileName.split("\\\\", -1).length - 1], line.toString());
                    break;
                }
            }
        }
        for (String key : map.keySet()) {
            System.out.println("表头：\t".concat(map.get(key)).concat("\t->\t").concat("文件名：\t").concat(key));
        }
    }

    public static void main(String[] args) throws Exception {
        VerifyFiles.verifyFiles("C:\\Users\\Administrator\\Desktop\\2020年1-11月工程业务简表汇总", 0, 94);
    }
}
