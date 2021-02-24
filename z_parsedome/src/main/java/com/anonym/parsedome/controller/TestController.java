package com.anonym.parsedome.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestController {
    public static void main(String[] args) throws IOException {
        //获取歌曲的地址
        URL url = new URL("https://y.qq.com/n/yqq/song/0033ufb53CHjFc.html");
        HttpURLConnection uc = (HttpURLConnection) url.openConnection();
        //用URL打开HTTP的连接资源
        InputStream is = uc.getInputStream();//获得输入流
        FileOutputStream fos = new FileOutputStream("D://1234.mp4");
        //写入的文件地址
        byte[] bytes = new byte[1024];
        int len =0;
        while ((len =is.read(bytes))!=0){
            fos.write(bytes,0,len);
        }//固定写入格式
        fos.close();//关闭输出流
        is.close();//关闭输入流
        uc.disconnect();//断开连接
    }
}
