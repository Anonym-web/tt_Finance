package cn.parse.json;

public class Client {
    public static void main(String[] args) {
        // 调用parseJsonMethod方法（参数列表在parseJsonMethod中有详细介绍）
        try {
            ParseJsonMethod.parseJsonMethod("E:\\帆软日志\\2020-08-31-14_40_11.log",
                    "E://处理后的Json文件/paseJson.txt", "utf-8", "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
