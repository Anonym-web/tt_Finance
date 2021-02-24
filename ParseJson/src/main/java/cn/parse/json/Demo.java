package cn.parse.json;

import tt.utility.GetFilePath;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Demo {
    static BufferedWriter bw = null;

    public static void main(String[] args) throws IOException {
        Set<String> set = new HashSet<String>();
        // 读取文件夹中的文件路径
        List<String> filesList = new ArrayList<String>();
        GetFilePath.getFilePath(new File("E:\\日志文件\\documentserver\\converter\\out"), filesList);
        //写到你一个本机的地址
        bw = new BufferedWriter(new FileWriter("E:\\日志文件\\out\\out.txt"));
        for (String fileName : filesList) {
            //这个就写你的log日志地址
            List<String> allKey = getKey(fileName);
            for (int i = 0; i < allKey.size(); i++) {
                if (allKey.get(i).split("\"url\":\"").length < 2)
                    continue;
                if (i != allKey.size() - 1) {
                    String str = allKey.get(i).split("\"url\":\"")[1];
                    String s = str.split("\"")[0];
                    if (!s.trim().startsWith("https://www.51") && !s.trim().startsWith("http://sdk.51") && !s.trim().startsWith("https://51jianxie")) {
                        set.add(s);
                    }
                }
            }
        }
        for (String line : set) {
            bw.append(line);
            bw.newLine();
        }
        bw.close();
    }

    public static List<String> getKey(String text) throws IOException {
        List<String> list = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader(text));
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null) {
            if (line.split("nodeJS -").length >= 2)
                list.add(line.split("nodeJS -")[1]);
        }
        reader.close();
        return list;
    }
}
