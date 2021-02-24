package com.srz.project.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClearInfoForFile {
    /**
     * 清空文件内容
     * @param fileName
     */
    public static void clearInfoForFile(String fileName) {
        File file =new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
