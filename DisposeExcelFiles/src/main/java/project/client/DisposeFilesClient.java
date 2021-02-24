package project.client;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import project.method.DisposeFiles;
import project.utility.WriterExcelUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DisposeFilesClient {
    public static void main(String[] args) {

        //统计各个部门在的位置，包括省和市
        List<String> disposeData = DisposeFiles.disposeFiles("G:\\铁通工作\\睿 交接的项目\\自建组织架构 DisposeFilesClient\\自建组织架构与人员中心映射表.xlsx", 0, 2);
        List<String> result = new ArrayList<>();
        // 如果第二个字段中含有`工程`、`建设`、`工建`的话，则在这条数据的后面追加一个2做标识；否则加1
        for (String line : disposeData) {
            if (line.split("\\|", -1)[1].contains("工程") || line.split("\\|", -1)[1].contains("建设") || line.split("\\|", -1)[1].contains("工建")) {
                result.add(line.concat("|2"));
            } else {
                result.add(line.concat("|1"));
            }
        }
        // 字段名
        List<String> titles = Lists.newArrayList();
        titles.add("org_id");
        titles.add("org_full_path");
        titles.add("省");
        titles.add("市");
        titles.add("铁通/建设");
        // 值
        List<Map<String, Object>> values = Lists.newArrayList();
        for (String line : result) {
            // 向对应字段中添加值（值需要跟titles的列名对应）
            Map<String, Object> map = Maps.newHashMap();
            // 截取第一个字段
            map.put("org_id", line.split("\\|", -1)[0]);
            // 截取第二个字段
            map.put("org_full_path", line.split("\\|", -1)[1]);
            // 截取第三个字段。。。
            map.put("省", line.split("\\|", -1)[2]);
            map.put("市", line.split("\\|", -1)[3]);
            map.put("铁通/建设", line.split("\\|", -1)[4]);
            values.add(map);
        }
        // 向`E://新建 Microsoft Excel 工作表.xlsx`中名为`Test`的sheet页中写入数据 不用导入
        WriterExcelUtil.writerExcel("E://新建 Microsoft Excel 工作表.xlsx", "Test", titles, values);
    }
}
