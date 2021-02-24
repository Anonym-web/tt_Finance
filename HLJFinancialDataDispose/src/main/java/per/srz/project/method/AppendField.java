package per.srz.project.method;

import per.srz.project.utility.ExcelUtil;
import per.srz.project.utility.HLJFileList;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class AppendField {
    // 装载名为辅助表的sheet页的数据
    static CopyOnWriteArrayList<String> assistList = new CopyOnWriteArrayList<String>();
    // 装载名为KTT0401 的sheet页的数据
    static CopyOnWriteArrayList<String> KTT0401List = new CopyOnWriteArrayList<String>();
    // 装载黑龙江省市名
    static CopyOnWriteArrayList<String> provinceData = new CopyOnWriteArrayList<String>();
    // 装载公司名和本公司对应的公司代码的数据
    static ConcurrentHashMap<String, String> companyCodeList = new ConcurrentHashMap<String, String>();
    // 所属省份
    static String province = "";
    // 所属地市
    static String city = "";
    // 公司代码
    static String companyCode = "";
    // 所属公司
    static String companyName = "";
    // 统计月份
    static String date = "";

    /**
     * 得到拼接的字符串
     *
     * @param path       Excel文件的输入路径
     * @param sheetName1 sheet页名称1
     * @param sheetName2 sheet页名称2
     * @return 需要拼接的字符串
     */

    public static String dataDispose(String path, String sheetName1, String sheetName2) {
        assistList = ExcelUtil.excelFileRead(path, sheetName1, 0);
            for (String line : assistList) {
                companyCodeList.put(line.split("\\|", -1)[0], line.split("\\|", -1)[1]);
            }
        KTT0401List = ExcelUtil.excelFileRead(path, sheetName2, 0);
        for (String line : KTT0401List) {
            if (line.startsWith("编制单位")) {
                String fileName = "";
                HLJFileList.HLJProvinceData(provinceData);
                // 文件名
                if (System.getProperty("os.name").split(" ")[0].equals("Windows")) {
                    fileName = path.split("\\\\", -1)[path.split("\\\\", -1).length - 1].split("\\.", -1)[0].replace(line.split("\\|", -1)[3].split("年", -1)[1], "").replace("（没有损益）", "").trim();
                } else {
                    fileName = path.split("/", -1)[path.split("/", -1).length - 1].split("\\.", -1)[0].replace(line.split("\\|", -1)[3].split("年", -1)[1], "").replace("（没有损益）", "").trim();
                }
                for (String list : provinceData) {
                    if (list.startsWith(fileName)) {
                        // 所属地市
                        city = list.split("\\|", -1)[1];
                    } else if (list.startsWith("省份")) {
                        // 所属省份
                        province = list.split("\\|", -1)[1];
                    }
                }
                // 所属公司
                companyName = line.split("\\|", -1)[0].replace("编制单位：", "");
                // 公司代码
                for (Map.Entry<String, String> code : companyCodeList.entrySet()) {
                    if (companyName.equals(code.getKey())) {
                        companyCode = code.getValue();
                    }
                }
                // 统计月份
                if (Integer.parseInt(line.split("\\|", -1)[3].split("年")[1].replace("月", "")) >= 10) {
                    date = line.split("\\|", -1)[3].replace("年", "-").replace("月", "");
                } else {
                    date = line.split("\\|", -1)[3].replace("年", "-0").replace("月", "");
                }
                break;
            }
        }
        // 返回拼接字符串
        if (path.contains("汇总")) {
            return province.concat("|").concat(companyName).concat("|").concat(companyCode).concat("|").concat(date);
        } else {
            return province.concat("|").concat(city).concat("|").concat(companyName).concat("|").concat(companyCode).concat("|").concat(date);
        }
    }
}
