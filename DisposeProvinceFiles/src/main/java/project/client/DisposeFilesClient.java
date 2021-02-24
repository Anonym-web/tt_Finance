package project.client;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import project.method.DisposeProvinceFiles;
import project.utility.WriterExcelUtil;

import java.util.List;
import java.util.Map;

public class DisposeFilesClient {
    public static void main(String[] args) {
        List<String> disposeList = DisposeProvinceFiles.disposeProvinceFiles("G:\\铁通工作\\睿 交接的项目\\省份-地市");
        // 北京
        disposeList.add("北京|北区");
        disposeList.add("北京|昌延");
        disposeList.add("北京|南区");
        disposeList.add("北京|中心");
        disposeList.add("北京|通顺");
        // 上海
        disposeList.add("上海|宝山");
        disposeList.add("上海|崇明");
        disposeList.add("上海|奉贤");
        disposeList.add("上海|嘉定");
        disposeList.add("上海|金山");
        disposeList.add("上海|闽行");
        disposeList.add("上海|浦东");
        disposeList.add("上海|松江");
        disposeList.add("上海|东区");
        disposeList.add("上海|西区");
        disposeList.add("上海|南区");
        disposeList.add("上海|北区");
        // 四川
        disposeList.add("四川|凉山");
        // 天津
        disposeList.add("天津|滨海");
        // 香港
        disposeList.add("香港|香港");
        // 新疆
        disposeList.add("新疆|石河子");
        disposeList.add("新疆|博州");
        disposeList.add("新疆|昌吉");
        disposeList.add("新疆|巴州");
        disposeList.add("新疆|伊犁");
        // 云南
        disposeList.add("云南|版纳");
        // 重庆
        disposeList.add("重庆|渝东");
        disposeList.add("重庆|渝南");
        disposeList.add("重庆|渝西");
        disposeList.add("重庆|渝中南");
        disposeList.add("重庆|渝州");
        disposeList.add("重庆|重庆");

        // 字段名
        List<String> titles = Lists.newArrayList();
        titles.add("province");
        titles.add("city");
        // 值
        List<Map<String, Object>> values = Lists.newArrayList();
        for (String line : disposeList) {
            // 向对应字段中添加值（值需要跟titles的列名对应）
            Map<String, Object> map = Maps.newHashMap();
            // 截取第一个字段
            map.put("province", line.split("\\|", -1)[0]);
            // 截取第二个字段
            map.put("city", line.split("\\|", -1)[1]);
            values.add(map);
        }
        // 向path中名为name的sheet页中写入数据
        WriterExcelUtil.writerExcel("E://省份地市对应关系表.xlsx", "sheet1", titles, values);
    }
}
