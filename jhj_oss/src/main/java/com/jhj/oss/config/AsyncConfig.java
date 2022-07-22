package com.jhj.oss.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author zhaohai
 * @date 2019/11/21
 */
@Slf4j
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    @Value("${thread.pool.corePoolSize}")
    int corePoolSize;

    @Value("${thread.pool.maxPoolSize}")
    int maxPoolSize;

    @Value("${thread.pool.keepAliveSeconds}")
    int keepAliveSeconds;

    @Value("${thread.pool.queueCapacity}")
    int queueCapacity;

    @Bean(name = "asyncTask")
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("Anno-Executor-Sale");
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setQueueCapacity(queueCapacity);
        executor.setRejectedExecutionHandler((Runnable r, ThreadPoolExecutor exe) -> {
            log.warn("Current executor :{}, queue full.","Anno-Executor-Sale");
        });
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            log.info("",ex);
            log.info("Form executor method by:{}, has uncaught exception:{}",method.getName(),ex.getMessage());
        };
    }



}
