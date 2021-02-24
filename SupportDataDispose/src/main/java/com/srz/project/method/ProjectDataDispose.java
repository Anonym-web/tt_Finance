package com.srz.project.method;

import com.srz.project.utility.ExcelUtil;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProjectDataDispose {
    public static void projectDataDispose(String inputPath, int sheetIndex, int lastCellNum) {
        try {
            // 装载工程应收账款数据
            CopyOnWriteArrayList<StringBuilder> list = (CopyOnWriteArrayList<StringBuilder>) ExcelUtil.excelUtil(inputPath, sheetIndex, lastCellNum);
            // 排序后的集合
            List<String> sortLine = new ArrayList<>();
            // 开关，用于过滤表头
            int OFF = 0;
            for (StringBuilder line : list) {
                if (OFF == 1)
                    // 获取这两列的值→ 合同/订单编码 	 合同/订单名称
                    sortLine.add(line.toString().split("\\|", -1)[10].concat("|").concat(line.toString().split("\\|", -1)[11]));
                // 过滤表头
                if (line.toString().startsWith("序号|"))
                    OFF = 1;
            }
            // 排序
            Collections.sort(sortLine);
            // 合并相同元素后的集合
            Map<String, List<String>> mergeMap = new HashMap<>();
            for (String s : sortLine) {
                List<String> values = new ArrayList<>();
                if (mergeMap.containsKey(s.split("\\|", -1)[0])) {
                    values = mergeMap.get(s.split("\\|", -1)[0]);
                    boolean boo = true;
                    for (String line : values) {
//                        System.out.println(line);
                        if (line.equals(s.split("\\|", -1)[1])) {
                            boo = false;
                            break;
                        }
                    }
                    if (boo) {
                        values.add(s.split("\\|", -1)[1]);
                    }
                    mergeMap.replace(s.split("\\|", -1)[0], values);
                } else {
                    values.add(s.split("\\|", -1)[1]);
                    mergeMap.put(s.split("\\|", -1)[0], values);
                }
            }
            for (String key : mergeMap.keySet()) {
                for (String value : mergeMap.get(key)) {
                    System.out.println(value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
