package com.srz.project.utility;

import java.io.*;

public class IOUtility {

    /**
     * 定义写流
     *
     * @param outputPath（String）
     *            文件输出路径
     * @param codedFormat（String）
     *            文件编码格式
     * @return BufferedWriter（String） BufferedWriter对象
     */
    public static BufferedWriter bufferWriter(String outputPath, String codedFormat) throws Exception {
        // 写流（以指定编码格式写入指定文件路径）
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath,true), codedFormat));
        return bufferedWriter;
    }

    /**
     * 定义读流
     *
     * @param inputPath（String）
     *            文件输入路径
     * @param codedFormat（String）
     *            文件编码格式
     * @return BufferedReader（String） BufferedReader对象
     */
    public static BufferedReader bufferReader(String inputPath, String codedFormat) throws Exception {
        // 读流（指定读取文件路径和编码格式）
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputPath), codedFormat));
        return bufferedReader;
    }
}
