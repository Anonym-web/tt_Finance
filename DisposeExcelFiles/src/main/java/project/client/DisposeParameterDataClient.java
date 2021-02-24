package project.client;

import project.method.DisposeParameterData;
import project.utility.IOUtility;
import java.io.BufferedWriter;
import java.util.List;

public class DisposeParameterDataClient {
    public static void main(String[] args) throws Exception {


        /*记录，每个sheet页都会生成一个excel，需要4+遍*/
        //统计每个公司签的一些合同，以及付款的类型比如租赁费，还有一些合同的金额
        List<String> disposeData = DisposeParameterData.disposeParameterData("G:\\铁通工作\\交接的项目\\处理excel数据\\文件\\增添组织架构--9月台账数据（34家单位）", 1, 35);
        for (String line : disposeData) {
            System.out.println(line);
        }
//        BufferedWriter writer = IOUtility.bufferWriter("E://3.txt", "UTF-8");
//        for (String line : disposeData) {
//            writer.append(line);
//            writer.newLine();
//        }
//        writer.close();
    }
}
