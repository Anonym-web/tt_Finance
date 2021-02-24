package project.method;

import project.utility.ExcelUtil;
import project.utility.GetFilePath;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DisposeParameterData {
    /**
     * 处理`增添组织架构--x月台账数据（34家单位）`数据
     */
    public static List<String> disposeParameterData(String inputPath, int sheetIndex, int lastCellNum) throws Exception {
        // 处理后的结果
        List<String> res = new ArrayList<>();
        // 存储文件地址数据
        List<String> pathList = new ArrayList<>();
        GetFilePath.getFilePath(new File(inputPath), pathList);
        for (String path : pathList) {
            List<StringBuilder> list = ExcelUtil.excelUtil(path, sheetIndex, lastCellNum);
            int OFF = 0;
            for (StringBuilder line : list) {
                // 过滤表尾
                if (line.toString().contains("— %d —")) {
                    OFF = 0;
                }
                // 去除空数据
                if (OFF == 1 && !line.toString().replace("|", "").equals("")) {
                    // 去除数据中的换行
                    res.add(line.toString().replace("\n", "").replace("\r", ""));
                }
                // 过滤表头，并打印表头
                if (line.toString().contains("合计")) {
                    OFF = 1;
                }
            }
        }
        return res;
    }
}
