package per.srz.project.method;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutor {
    // 线程的核心线程数
    private int corePoolSize;
    // 线程允许的最大线程数
    private int maximumPoolSize;
    // 队伍队列，如果线程池达到了核心线程数，并且其他线程都处于活动状态的时候，则将新任务放入此队列。
    private BlockingQueue<Runnable> workQueue;
    // 在当前线程池中，线程总数大于核心线程数时，终止多余的空闲线程的时间。
    private long keepAliveTime;
    // 定制线程的创建过程
    private ThreadFactory threadFactory;
    /* 拒绝策略，当workQueue队满时，采取的措施。拒绝策略有如下几种：
     * ThreadPoolExecutor.AbortPolicy()  抛出java.util.concurrent.RejectedExecutionException异常
     * ThreadPoolExecutor.CallerRunsPolicy() 重试添加当前的任务，他会自动重复调用execute()方法
     * ThreadPoolExecutor.DiscardOldestPolicy() 抛弃旧的任务
     * ThreadPoolExecutor.DiscardPolicy() 抛弃当前的任务
     */
    private RejectedExecutionHandler handler;

    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler,
                              String inputPath,
                              String outputPath,
                              String codedFormat,
                              String date
    ) {
        if (corePoolSize < 0 ||
                maximumPoolSize <= 0 ||
                maximumPoolSize < corePoolSize ||
                keepAliveTime < 0)
            // 非法参数
            throw new IllegalArgumentException("非法参数");
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }

}
