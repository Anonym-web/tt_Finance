package com.srz.project.method;

import com.srz.project.utility.ExcelUtil;

import java.util.concurrent.CopyOnWriteArrayList;

public class DisposeFineReportData {
    public static void disposeFineReportData(String fileName, int sheetIndex, int lastCellNum) {
        try {
            // 遍历读取excel文件
            CopyOnWriteArrayList<StringBuilder> list = (CopyOnWriteArrayList<StringBuilder>) ExcelUtil.excelUtil(fileName, sheetIndex, lastCellNum);
            for (StringBuilder line : list) {
                if (!line.toString().split("\\|", -1)[1].startsWith("孟祥爱") && !line.toString().split("\\|", -1)[1].startsWith("柳亚飞") && !line.toString().split("\\|", -1)[1].startsWith("张舒平") && !line.toString().split("\\|", -1)[1].startsWith("冯秋蓉") && !line.toString().split("\\|", -1)[1].startsWith("史睿泽") && !line.toString().split("\\|", -1)[1].startsWith("潘仕平") && !line.toString().split("\\|", -1)[1].startsWith("张文浩") && !line.toString().split("\\|", -1)[1].startsWith("王玮珂") && !line.toString().split("\\|", -1)[1].startsWith("王振浩") && !line.toString().split("\\|", -1)[1].startsWith("郝鹏") && !line.toString().split("\\|", -1)[1].startsWith("赵莎莎") && !line.toString().split("\\|", -1)[1].startsWith("王彦") && !line.toString().split("\\|", -1)[1].startsWith("孙磊") && !line.toString().split("\\|", -1)[1].startsWith("张天鑫") && !line.toString().split("\\|", -1)[1].startsWith("李咏絮")) {
                    if (!line.toString().split("\\|", -1)[2].contains(",") && !line.toString().split("\\|", -1)[2].contains("分公司") && !line.toString().split("\\|", -1)[2].contains("中移工程公司")) {
                        System.out.println(line.toString().concat("|").concat("本部"));
                    } else {
                        System.out.println(line.toString().concat("|").concat(line.toString().split("\\|", -1)[2].substring(0, 2)).concat("省"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        disposeFineReportData("E://帆软日志//rizhiout0720.xls", 0, 4);
    }
}
