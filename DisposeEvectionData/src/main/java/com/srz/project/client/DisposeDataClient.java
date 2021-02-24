package com.srz.project.client;

import com.google.common.collect.Maps;
import com.srz.project.method.DisposeData;
import com.srz.project.method.PresetDisposeData;
import com.srz.project.method.SecondaryDisposeData;
import com.srz.project.utility.WriterExcelUtil;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;

public class DisposeDataClient {
    public static void main(String[] args) {
        List<String> presetData;
        // 预处理
//        for(int i = 0;i<7;i++){

            presetData = PresetDisposeData.presetDisposeData("G:\\铁通工作\\睿 交接的项目\\铁通出差文件 DisposeDataClient\\12月接受\\新建 XLSX 工作表.xlsx", 0, 24);
//        }
        // 一次处理
        List<String> disposeData = DisposeData.disposeData(presetData);
        // 二次处理
        List<String> secondaryData = SecondaryDisposeData.secondaryDisposeData(disposeData);
        try {
            // 字段名
            List<String> titles = Lists.newArrayList();
            titles.add("报账日期");
            titles.add("报帐单号");
            titles.add("公司代码");
            titles.add("报账人");
            titles.add("事由");
            titles.add("本次付款金额");
            titles.add("费用明细条数");
            titles.add("出发地");
            titles.add("目的地");
            titles.add("出发时间");
            titles.add("结束时间");
            titles.add("发票类型");
            titles.add("发票金额");
            titles.add("交通费");
            titles.add("交通费标准");
            titles.add("住宿费标准");
            titles.add("含税金额");
            titles.add("报销类型");
            titles.add("补助标准");
            titles.add("出差天数");
            titles.add("人数");
            titles.add("出差补助");
            titles.add("其他费用");
            titles.add("含税金额总计");
            // 值
            List<Map<String, Object>> values = Lists.newArrayList();
            for (String line : secondaryData) {
                // 向对应字段中添加值（值需要跟titles的列名对应）
                Map<String, Object> map = Maps.newHashMap();
                // 截取第一个字段
                map.put("报账日期", line.split("\\|", -1)[0]);
                map.put("报帐单号", line.split("\\|", -1)[1]);
                map.put("公司代码", line.split("\\|", -1)[2]);
                map.put("报账人", line.split("\\|", -1)[3]);
                map.put("事由", line.split("\\|", -1)[4]);
                map.put("本次付款金额", line.split("\\|", -1)[5]);
                map.put("费用明细条数", line.split("\\|", -1)[6]);
                map.put("出发地", line.split("\\|", -1)[7]);
                map.put("目的地", line.split("\\|", -1)[8]);
                map.put("出发时间", line.split("\\|", -1)[9]);
                map.put("结束时间", line.split("\\|", -1)[10]);
                map.put("发票类型", line.split("\\|", -1)[11]);
                map.put("发票金额", line.split("\\|", -1)[12]);
                map.put("交通费", line.split("\\|", -1)[13]);
                map.put("交通费标准", line.split("\\|", -1)[14]);
                map.put("住宿费标准", line.split("\\|", -1)[15]);
                map.put("含税金额", line.split("\\|", -1)[16]);
                map.put("报销类型", line.split("\\|", -1)[17]);
                map.put("补助标准", line.split("\\|", -1)[18]);
                map.put("出差天数", line.split("\\|", -1)[19]);
                map.put("人数", line.split("\\|", -1)[20]);
                map.put("出差补助", line.split("\\|", -1)[21]);
                map.put("其他费用", line.split("\\|", -1)[22]);
                map.put("含税金额总计", line.split("\\|", -1)[23]);
                values.add(map);
            }
            // 向`E://新建 Microsoft Excel 工作表.xlsx`中名为`Test`的sheet页中写入数据 不用导入
            WriterExcelUtil.writerExcel("E://新建 Microsoft Excel 工作表.xlsx", "Test", titles, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
