package com.srz.project.method;

import com.srz.project.utility.ExcelUtil;
import java.util.concurrent.CopyOnWriteArrayList;

public class ReadSXRosterData {
    // 装载支撑数据
    static CopyOnWriteArrayList<StringBuilder> rosterList = new CopyOnWriteArrayList<>();
    // 装载处理后的支撑数据
    static CopyOnWriteArrayList<String> disposeRosterList = new CopyOnWriteArrayList<>();
    // 省份
    static String province = "陕西省";
    // 地市
    static String city = "";
    // 开关
    static int OFF = 0;

    /**
     * 处理山东花名册数据
     *
     * @param inputPath   文件输入路径，格式；盘符://文件夹名称
     * @param lastCellNum 处理文件的最后一列所在位置
     * @param time        `统计时间`
     * @return CopyOnWriteArrayList<String>
     */
    public static CopyOnWriteArrayList<String> readSXRosterData(String inputPath, int lastCellNum, String time) {
        try {
            // sheet页数量
            for (int k = 1; k < 13; k++) {
                // 读取陕西的花名册数据
                rosterList = (CopyOnWriteArrayList<StringBuilder>) ExcelUtil.excelUtil(inputPath, k, lastCellNum);
                // 拿到第二条数据，并获取`所属地市`字段的值（第一条为表头）
                if (rosterList.get(1).toString().split("\\|", -1)[0].equals("省分公司"))
                    city = "本级";
                else if (rosterList.get(1).toString().split("\\|", -1)[0].equals("中移建设"))
                    city = "中移建设";
                else
                    city = rosterList.get(1).toString().substring(0, 2).concat("市");
                // 部门档案名称
                String recordName;
                // 对`部门档案名称`做特殊处理
                for (StringBuilder line : rosterList) {
                    if (line.toString().split("\\|", -1)[1].contains("营销服务中心"))
                        recordName = line.toString().split("\\|", -1)[1];
                    else
                        recordName = "机关及其他部门";
                    if (recordName.equals("市区营销服务中心"))
                        recordName = "铜川市区营销服务中心";
                    // 过滤表尾
                    if (line.toString().startsWith("|合计") || line.toString().startsWith("合计") || line.toString().equals("||||"))
                        OFF = 0;
                    if (OFF == 1)
                        // 依次给`人员属性`赋值，将一行拆分为三列
                        for (int i = 0; i < 3; i++) {
                            String staffNum;
                            if (i == 0) {
                                if (line.toString().split("\\|", -1)[2].equals(""))
                                    staffNum = "0";
                                else
                                    staffNum = line.toString().split("\\|", -1)[2].replace(".00", "");
                                disposeRosterList.add(province.concat("|").concat(city).concat("|").concat(recordName).concat("|").concat("合同制").concat("|").concat(staffNum).concat("|").concat(time));
                            } else if (i == 1) {
                                if (line.toString().split("\\|", -1)[3].equals(""))
                                    staffNum = "0";
                                else
                                    staffNum = line.toString().split("\\|", -1)[3].replace(".00", "");
                                disposeRosterList.add(province.concat("|").concat(city).concat("|").concat(recordName).concat("|").concat("合作企业").concat("|").concat(staffNum).concat("|").concat(time));
                            } else {
                                if (line.toString().split("\\|", -1)[4].equals(""))
                                    staffNum = "0";
                                else
                                    staffNum = line.toString().split("\\|", -1)[4].replace(".00", "");
                                disposeRosterList.add(province.concat("|").concat(city).concat("|").concat(recordName).concat("|").concat("派遣").concat("|").concat(staffNum).concat("|").concat(time));
                            }
                        }
                    // 过滤表头
                    if (line.toString().startsWith("2级部门"))
                        OFF = 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("代码执行完成!");
        }
        return disposeRosterList;
    }
}