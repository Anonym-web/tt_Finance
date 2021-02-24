package tt.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetFilePath {
    /**
     * 获取指定目录下的文件路径
     *
     * @param file（File）           文件的根路径
     * @param fileList（list<File>） 装载文件路径的集合
     */

    public static void getFilePath(File file, List<String> fileList) {
        // 判断是否文件夹
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files.length > 0) {
                for (File f : files) {
                    // 去除正在打开的文件
                    if (f.isFile() && f.toString().indexOf("~$") == -1) {
                        fileList.add(f.toString());
                    } else {
                        getFilePath(f, fileList);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String> fileList = new ArrayList<String>();
        getFilePath(new File("E://黑龙江"), fileList);
        for(String line : fileList) {
            System.out.println(line);
        }
    }
}
