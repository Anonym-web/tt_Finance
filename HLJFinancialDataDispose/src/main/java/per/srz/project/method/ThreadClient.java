package per.srz.project.method;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadClient {
    /**
     * 以多线程的方式去执行方法
     */
    public static void main(String[] args) {
        // 开始时间（毫秒）
        long startTime = System.currentTimeMillis();
        // 结束时间（毫秒）
        long endTime = System.currentTimeMillis();
        // 线程数
        int threadCount = 28;
        String inputPath;
        String outputPath;
        String codedFormat;
        String date;
        if (System.getProperty("os.name").split(" ", -1)[0].equals("Windows")) {
            inputPath = "E://黑龙江财务数据";
            outputPath = "E://处理后的黑龙江财务数据";
            codedFormat = "utf-8";
            date = "2019-10";
        } else {
            inputPath = args[0];
            outputPath = args[1];
            codedFormat = args[2];
            date = args[3];
        }
        ThreadTask.inputPath = inputPath;
        ThreadTask.outputPath = outputPath;
        ThreadTask.codedFormat = codedFormat;
        ThreadTask.date = date;
        // 判断输入文件是否存在
        File inputFile = new File(inputPath);
        if (!inputFile.exists()) {
            System.out.println("输入文件路径不存在");
            return;
        }
        // 判断输出文件夹是否存在
        File provinceFile = new File(outputPath.concat("/").concat("黑龙江省份"));
        if (!provinceFile.exists()) {
            provinceFile.mkdirs();
            System.out.println("省份文件夹不存在，但已为你创建省份文件输出路径");
        }
        File prefecturalFile = new File(outputPath.concat("/").concat("黑龙江地市"));
        if (!prefecturalFile.exists()) {
            prefecturalFile.mkdirs();
            System.out.println("地市文件夹不存在，但已为你创建地市文件输出路径");
        }
        //拒绝策略为AbortPolic策略：元素添加失败时直接抛出异常
        ThreadPoolExecutor pool = new ThreadPoolExecutor(threadCount, Integer.MAX_VALUE, 5000, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < threadCount; i++) {
            pool.execute(new ThreadTask());
        }
        pool.shutdown();
        // 得到线程结束的时间
        while (true) {
            if (pool.isTerminated()) {
                break;
            }
            try {
                Thread.sleep(100);
                endTime = System.currentTimeMillis();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        // 时间差（秒）
        double timeDifference = (double) (endTime - startTime) / 1000;
        // 可能存在200毫秒范围内的误差
        System.out.println("此次任务用时" + timeDifference + "秒");
    }
}
