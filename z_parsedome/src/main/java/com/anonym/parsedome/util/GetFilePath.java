package com.anonym.parsedome.util;

import java.io.File;
import java.util.List;

public class GetFilePath {
    /**
     * 获取指定目录下的文件路径（该方法可以读取文件夹中的内部文件夹中的文件）
     *
     * @param file     文件输入路径，格式：盘符://文件夹名称
     * @param fileList 装载文件路径的集合
     */

    public static void getFilePath(File file, List<String> fileList) {
        // 判断是否文件夹
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            assert files != null;
            for (File f : files) {
                // 去除正在打开的文件（注意：文件名中不要包含`~$`）
                if (f.isFile() && !f.toString().contains("~$") && !f.toString().contains(".tgz") && !f.toString().contains(".zip")) {
                    fileList.add(f.toString());
                } else {
                    getFilePath(f, fileList);
                }
            }
        }
    }
}
