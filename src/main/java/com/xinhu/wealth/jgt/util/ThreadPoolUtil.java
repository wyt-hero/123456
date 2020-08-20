package com.xinhu.wealth.jgt.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Configuration
public class ThreadPoolUtil {

    private static final Integer corePoolSize = 20; //核心线程

    private static final Integer maximumPoolSize = 100; //线程池大小

    private static final Long keepAliveTime = 2L; //超时时间

    private static final TimeUnit timeUnit = TimeUnit.SECONDS; //超时单位

    private static final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);

    @Bean
    public ThreadPoolExecutor ThreadPool() {
       ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, queue);
       executor.allowCoreThreadTimeOut(true);
       return executor;
    }

}
