package project.method;

import project.utility.GetFilePath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DisposeProvinceFiles {
    public static List<String> disposeProvinceFiles(String fileName) {
        // 存储处理后的数据
        List<String> disposeList = new ArrayList<String>();
        fileName = fileName.replace("/", "\\");
        // 存储文件夹下的子文件路径
        List<String> fileList = new ArrayList<String>();
        // 装载文件路径
        GetFilePath.getFilePath(new File(fileName), fileList);
        // 省份
        String province;
        // 地市
        String city;
        for (String line : fileList) {
            line = line.replace("/", "\\");
            // 过滤掉除了以“-area.json”结尾的路径
            if (line.split("\\\\").length == fileName.split("\\\\").length + 2 && line.endsWith("-area.json")) {
                province = line.split("\\\\")[line.split("\\\\").length - 2];
                // 对省份字段做特殊处理
                if (province.endsWith("省")) {
                    province = province.substring(0, province.length() - 1);
                } else if (province.endsWith("自治区")) {
                    province = province.replace("自治区", "");
                    province = province.replace("回", "");
                    province = province.replace("壮", "");
                    province = province.replace("维吾尔", "");
                    province = province.replace("族", "");
                } else {
                    province = province.replace("市", "");
                }
                // 对地市字段做特殊处理
                city = line.split("\\\\")[line.split("\\\\").length - 1].replace("-area.json", "");
                if (city.endsWith("市")) {
                    city = city.replace("市", "");
                } else if (city.endsWith("地区")) {
                    city = city.replace("地区", "");
                } else if (city.substring(city.length() - 1).equals("区")) {
                    city = city.replace("区", "");
                } else if (city.substring(city.length() - 1).equals("盟")) {
                    city = city.replace("盟", "");
                } else {
                    city = city.replace("自治州", "");
                }
                if (city.contains("大理")) {
                    city = "大理";
                } else if (city.contains("德宏")) {
                    city = "德宏";
                } else if (city.contains("怒江")) {
                    city = "怒江";
                } else if (city.contains("文山")) {
                    city = "文山";
                } else if (city.contains("楚雄")) {
                    city = "楚雄";
                } else if (city.contains("红河")) {
                    city = "红河";
                } else if (city.contains("西双版纳")) {
                    city = "西双版纳";
                } else if (city.contains("迪庆")) {
                    city = "迪庆";
                } else if (city.contains("延边")) {
                    city = "延边";
                } else if (city.contains("恩施")) {
                    city = "恩施";
                } else if (city.contains("湘西")) {
                    city = "湘西";
                } else if (city.contains("临夏")) {
                    city = "临夏";
                } else if (city.contains("甘南")) {
                    city = "甘南";
                } else if (city.contains("黔东")) {
                    city = "黔东";
                } else if (city.contains("黔南")) {
                    city = "黔南";
                } else if (city.contains("黔西南")) {
                    city = "黔西南";
                }
                disposeList.add(province + "|" + city);
            }
        }
        return disposeList;
    }
}
