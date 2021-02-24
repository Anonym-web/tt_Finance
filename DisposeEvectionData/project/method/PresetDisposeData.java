package com.srz.project.method;

import com.srz.project.utility.ExcelUtil;
import com.srz.project.utility.IsNumeric;

import java.util.ArrayList;
import java.util.List;

/*
 * 对数据的编号进行预处理
 * */
public class PresetDisposeData {
    public static List<String> presetDisposeData(String inputPath, int sheetIndex, int lastCellNum) {
        List<String> presetData = new ArrayList<String>();
        try {
            // 接收excel数据
            List<StringBuilder> list = ExcelUtil.excelUtil(inputPath, sheetIndex, lastCellNum);
            // 开关
            boolean OFF = false;
            // 费用明细条数
            int num = 0;
            for (int i = 0; i < list.size() - 1; i++) {
                if (OFF) {
                    if (list.get(i).toString().split("\\|", -1)[1].equals(list.get(i + 1).toString().split("\\|", -1)[1])) {
                        num++;
                        String line = "";
                        for (int count = 0; count < list.get(i).toString().split("\\|", -1).length; count++) {
                            if (count == 6)
                                line = line.concat(String.valueOf(num)).concat("|");
                            else if (count == list.get(i).toString().split("\\|", -1).length - 1)
                                line = line.concat(list.get(i).toString().split("\\|", -1)[count]);
                            else
                                line = line.concat(list.get(i).toString().split("\\|", -1)[count]).concat("|");
                        }
                        presetData.add(line);
                    } else {
                        num++;
                        String line = "";
                        for (int count = 0; count < list.get(i).toString().split("\\|", -1).length; count++) {
                            if (count == 6)
                                line = line.concat(String.valueOf(num)).concat("|");
                            else if (count == list.get(i).toString().split("\\|", -1).length - 1)
                                line = line.concat(list.get(i).toString().split("\\|", -1)[count]);
                            else
                                line = line.concat(list.get(i).toString().split("\\|", -1)[count]).concat("|");
                        }
                        presetData.add(line);
                        num = 0;
                    }
                }
                // 过滤表头
                if (list.get(i).toString().startsWith("报账日期|报帐单号"))
                    OFF = true;
            }
            if (IsNumeric.isNumber(list.get(list.size() - 1).toString().split("\\|", -1)[6]) || IsNumeric.isNumber(list.get(list.size() - 2).toString().split("\\|", -1)[6])) {
                if (list.get(list.size() - 1).toString().split("\\|", -1)[1].equals(list.get(list.size() - 2).toString().split("\\|", -1)[1])) {
                    String count = list.get(list.size() - 2).toString().split("\\|", -1)[6];
                    count = String.valueOf(Integer.parseInt(count) + 1);
                    String line = "";
                    for (int i = 0; i < list.get(list.size() - 1).toString().split("\\|", -1).length; i++) {
                        if (i == 6)
                            line = line.concat(count).concat("|");
                        else if (i == list.get(list.size() - 1).toString().split("\\|", -1).length - 1)
                            line = line.concat(list.get(list.size() - 1).toString().split("\\|", -1)[i]);
                        else
                            line = line.concat(list.get(list.size() - 1).toString().split("\\|", -1)[i]).concat("|");
                    }
                    presetData.add(line);
                } else {
                    presetData.add(list.get(list.size() - 1).toString());
                }
            } else {
                presetData.add(list.get(list.size() - 1).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return presetData;
    }
}
