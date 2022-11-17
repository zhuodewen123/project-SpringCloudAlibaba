package com.zdw.common.config.thread;//package com.tcl.sc.baseserver.config.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步方法-线程池配置
 * @author 卓德文
 *
 */
@Configuration
@EnableAsync
public class ThreadPoolTaskConfig {

    /**
    *  1)默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务
    *  2)当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中
    *  3)当队列满了，就继续创建线程，当线程数量大于等于maxPoolSize后，开始使用拒绝策略拒绝
    */

    /**
     * 核心线程数（默认线程数）
     */
    private static final int corePoolSize = 8;

    /**
     * 最大线程数
     */
    private static final int maxPoolSize = 48;

    /**
     * 允许线程空闲时间（单位：默认为秒）
     */
    private static final int keepAliveTime = 10;

    /**
     * 缓冲队列大小
     */
    private static final int queueCapacity = 200;

    /**
     * 线程池名前缀
     */
    private static final String threadNamePrefix = "Thread-Task-";

    /**
     * 自定义线程池(Bean的名称默认为首字母小写的方法名)
     * @return
     */
    @Bean("commonTaskExecutor")
    public ThreadPoolTaskExecutor commonTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveTime);
        executor.setThreadNamePrefix(threadNamePrefix);

        //线程池对拒绝任务的处理策略--CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        //初始化
        executor.initialize();
        return executor;
    }

}