package tt.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IOUtility {
    /**
     * 定义写流
     *
     * @param outputPath（String）  文件输出路径
     * @param codedFormat（String） 文件编码格式
     * @return BufferedWriter（String） BufferedWriter对象
     */
    public static BufferedWriter bufferWriter(String outputPath, String codedFormat) throws Exception {
        // 写流（以指定编码格式写入指定文件路径）
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath), codedFormat));
        return bw;
    }

    /**
     * 定义读流
     *
     * @param inputPath（String）   文件输入路径
     * @param codedFormat（String） 文件编码格式
     * @return BufferedReader（String） BufferedReader对象
     */
    public static BufferedReader bufferReader(String inputPath, String codedFormat) throws Exception {
        // 读流（指定读取文件路径和编码格式）
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputPath), codedFormat));
        return br;
    }
}
