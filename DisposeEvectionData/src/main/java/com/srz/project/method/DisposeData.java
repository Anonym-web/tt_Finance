package com.srz.project.method;

import com.srz.project.utility.CountTime;

import java.util.ArrayList;
import java.util.List;

/**
 * 为数据添加出差数的字段
 */
public class DisposeData {

    public static List<String> disposeData(List<String> list) {
        List<String> disposeList = new ArrayList<String>();
        try {
            // 费用明细条数
            int costBreakdownNum = 1;
            for (int i = 0; i < list.size(); i++) {
                if (costBreakdownNum < Integer.parseInt(list.get(i).split("\\|", -1)[6])) { // 处理相同报账单号中有多条数据中的除了第一条数据的数据
                    int num = Integer.parseInt(list.get(i - 1).split("\\|", -1)[6]);
                    // 判断当前行的费用明细条数是否为单数
                    if (num % 2 == 1 && (num + 1) == Integer.parseInt(list.get(i).split("\\|", -1)[6])) {
                        // 开始时间
                        String startTime = list.get(i - 1).split("\\|", -1)[9];
                        // 结束时间
                        String endTime = list.get(i).split("\\|", -1)[10];
                        // 过滤掉不规范数据
                        if (startTime.length() <= 10 || endTime.length() <= 10) {
                            disposeList.add(list.get(i - 1).concat("|"));
                            disposeList.add(list.get(i).concat("|"));
                            continue;
                        }
                        if (startTime.substring(0, 10).equals(endTime.substring(0, 10))) { // 出差当天返回的
                            // 出差天数
                            double travelDays;
                            if (CountTime.countHour(startTime, endTime) >= 8)
                                travelDays = 1.0;
                            else if (CountTime.countHour(startTime, endTime) >= 4 && CountTime.countHour(startTime, endTime) < 8)
                                travelDays = 0.5;
                            else
                                travelDays = 0.0;
                            disposeList.add(list.get(i - 1).concat("|").concat(Double.toString(travelDays)));
                        } else { // 出差当天不返回的
                            // 出差天数
                            double travelDays;
                            if (Integer.parseInt(startTime.substring(11, 13)) < 12) {
                                travelDays = 1.0;
                            } else {
                                travelDays = 0.5;
                            }
                            if (Integer.parseInt(endTime.substring(11, 13)) > 12) {
                                travelDays += 1.0;
                            } else {
                                travelDays += 0.5;
                            }
                            travelDays += CountTime.countDay(startTime, endTime);
                            disposeList.add(list.get(i - 1).concat("|").concat(Double.toString(travelDays)));
                        }
                        disposeList.add(list.get(i).concat("|"));
                    } else { // 处理那些有去无回的数据
                        if (i != list.size() - 1) {
                            if (list.get(i + 1).split("\\|")[6].equals("1"))
                                disposeList.add(list.get(i).concat("|"));
                        } else
                            disposeList.add(list.get(i).concat("|"));
                    }
                } else {
                    if (Integer.parseInt(list.get(i + 1).split("\\|", -1)[6]) == 1) { // 处理相同报账单号中只有一条数据的情况
                        // 开始时间
                        String startTime = list.get(i).split("\\|", -1)[9];
                        // 结束时间
                        String endTime = list.get(i).split("\\|", -1)[10];
                        // 过滤掉不规范数据
                        if (startTime.length() <= 10 || endTime.length() <= 10) {
                            disposeList.add(list.get(i).concat("|"));
                            continue;
                        }
                        if (startTime.substring(0, 10).equals(endTime.substring(0, 10))) { // 出差当天返回的
                            // 出差天数
                            double travelDays;
                            if (CountTime.countHour(startTime, endTime) >= 8)
                                travelDays = 1.0;
                            else if (CountTime.countHour(startTime, endTime) >= 4 && CountTime.countHour(startTime, endTime) < 8)
                                travelDays = 0.5;
                            else
                                travelDays = 0.0;
                            disposeList.add(list.get(i).concat("|").concat(Double.toString(travelDays)));
                        } else { // 出差当天不返回的
                            // 出差天数
                            double travelDays;
                            if (Integer.parseInt(startTime.substring(11, 13)) < 12) {
                                travelDays = 1.0;
                            } else {
                                travelDays = 0.5;
                            }
                            if (Integer.parseInt(endTime.substring(11, 13)) > 12) {
                                travelDays += 1.0;
                            } else {
                                travelDays += 0.5;
                            }
                            travelDays += CountTime.countDay(startTime, endTime);
                            disposeList.add(list.get(i).concat("|").concat(Double.toString(travelDays)));
                        }
                    }
                    costBreakdownNum = Integer.parseInt(list.get(i).split("\\|", -1)[6]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("文件执行完成！");
        }
        return disposeList;
    }
}
