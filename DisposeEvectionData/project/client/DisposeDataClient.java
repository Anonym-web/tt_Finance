package com.srz.project.client;

import com.srz.project.method.DisposeData;
import com.srz.project.method.PresetDisposeData;
import com.srz.project.method.SecondaryDisposeData;
import com.srz.project.utility.ClearInfoForFile;
import com.srz.project.utility.IOUtility;

import java.io.BufferedWriter;
import java.util.List;

public class DisposeDataClient {
    public static void main(String[] args) {
        BufferedWriter writer = null;
        // 预处理（"E://铁通出差文件//无标题.xls"）
        List<String> presetData = PresetDisposeData.presetDisposeData(args[0], 0, 23);
        // 一次处理
        List<String> disposeData = DisposeData.disposeData(presetData);
        // 二次处理
        List<String> secondaryData = SecondaryDisposeData.secondaryDisposeData(disposeData);
        try {
            // "E:\\处理后的铁通出差文件\\disposeFile.txt"
            // "UTF-8"
            ClearInfoForFile.clearInfoForFile(args[1]);
            writer = IOUtility.bufferWriter(args[1], args[2]);
            writer.append("报账日期|报帐单号|公司代码|报账人|事由|本次付款金额|费用明细条数|出发地|目的地|出发时间|结束时间|交通工具|发票类型|发票金额|交通费|含税金额|报销类型|补助标准|出差天数|人数|出差补助|其他费用|含税金额总计|计算天数|是否审计巡视|出差类型|天数误差值|实际补助标准|出差标准误差|补助差值|重点");
            writer.newLine();
            for (String line : secondaryData) {
                writer.append(line);
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert writer != null;
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
