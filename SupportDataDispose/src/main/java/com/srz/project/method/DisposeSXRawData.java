package com.srz.project.method;

import com.srz.project.utility.ExcelUtil;
import com.srz.project.utility.GetFilePath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DisposeSXRawData {
    // 装载支撑数据
    static CopyOnWriteArrayList<StringBuilder> list = new CopyOnWriteArrayList<>();
    // 装载处理后的支撑数据
    static CopyOnWriteArrayList<String> rawDataList = new CopyOnWriteArrayList<>();
    // 存储文件路径
    static List<String> fileList = new ArrayList<>();
    // 所属省份
    static String province = "陕西省";
    // 所属地市
    static String city;
    // 部门档案名称
    static String archives = "";
    // 方向
    static String direction = "";
    // 期末余额
    static String endingBalance = "";

    /**
     * 对陕西财务数据做初步处理
     *
     * @param inputPath   文件输入路径，格式；盘符://文件夹名称
     * @param sheetIndex  sheet页索引
     * @param lastCellNum 处理文件的最后一列所在位置
     * @return CopyOnWriteArrayList<String>
     */
    public static CopyOnWriteArrayList<String> disposeSXRawData(String inputPath, int sheetIndex, int lastCellNum, String time) {
        try {
            // 装载文件路径
            GetFilePath.getFilePath(new File(inputPath), fileList);
            for (String fileName : fileList) {
                // 开关
                int OFF = 0;
                // 装载excel数据
                list = (CopyOnWriteArrayList<StringBuilder>) ExcelUtil.excelUtil(fileName, sheetIndex, lastCellNum);
                // 根据文件名获取`所属地市`的值
                if (!fileName.split("\\\\", -1)[fileName.split("\\\\", -1).length - 1].split("-", -1)[0].equals("省分本级") && !fileName.split("\\\\", -1)[fileName.split("\\\\", -1).length - 1].split("-", -1)[0].equals("中移建设"))
                    city = fileName.split("\\\\", -1)[fileName.split("\\\\", -1).length - 1].split("-", -1)[0].concat("市");
                else
                    city = fileName.split("\\\\", -1)[fileName.split("\\\\", -1).length - 1].split("-", -1)[0];
                // 所属类型
                String type = fileName.split("\\\\", -1)[fileName.split("\\\\", -1).length - 1].split("-", -1)[1].substring(0, 2);
                // 主体账簿名称
                String accountName;
                // 对`所属地市`字段的值做特殊处理
                if (city.equals("中移建设"))
                    accountName = "中移建设有限公司陕西分公司";
                else if (city.equals("省分本级") || city.equals("省分") || city.equals("本级"))
                    accountName = "中移铁通有限公司陕西本级";
                else
                    accountName = "中移铁通有限公司" + city.replace("市", "") + "分公司";
                for (StringBuilder line : list) {
                    if (line.toString().contains("科目名称"))
                        OFF = 1;
                    if (OFF == 1 && !line.toString().contains("科目名称") && !line.toString().trim().equals("") && !line.toString().equals("||||") && !line.toString().equals("|||")) {
                        if (line.toString().split("\\|", -1)[1].contains("子州"))
                            // `部门档案名称`中可能含有`子州`错别字，需要将其更正
                            archives = line.toString().split("\\|")[1].replace("子州", "子洲");
                        else
                            archives = line.toString().split("\\|", -1)[1];
                        // 如果`部门档案名称`字段值中不包含`营销服务中心`，则将`部门档案名称`的值设为`机关及其他部门`
                        if (!archives.contains("营销服务中心"))
                            archives = "机关及其他部门";
                        endingBalance = line.toString().split("\\|", -1)[2];
                        // 如果`期末余额`字段值为空，则默认为0
                        if (endingBalance.trim().equals(""))
                            endingBalance = "0.0";
                        rawDataList.add(accountName.concat("|").concat(line.toString().split("\\|", -1)[0]).concat("|").concat(archives).concat("||||||||||").concat(direction).concat("|").concat(endingBalance).concat("|").concat(province).concat("|").concat(type).concat("|").concat(time).concat("|"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("代码执行完成！");
        }
        return rawDataList;
    }
}
