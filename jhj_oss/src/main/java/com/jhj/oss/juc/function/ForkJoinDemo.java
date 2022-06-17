package com.jhj.oss.juc.function;

import java.util.concurrent.RecursiveTask;

/**
 * 分支合并
 * 特点：工作窃取
 *
 * @author Jeremy
 * @date 2022/6/17 10:47
 */
public class ForkJoinDemo extends RecursiveTask<Long> {


    private Long start;

    private Long end;
    private Long temp =10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    public void test () {

    }

    @Override
    protected Long compute() {
        if((end-start)<temp ){
            //分支合并并计算
            Long sum =0L;
            for (Long i = start; i <= end; i++) {
                sum +=i;
            }
            return sum;
        }else {//forkjoin
            long middle = (start + end)/2;
            ForkJoinDemo forkJoinDemo = new ForkJoinDemo(start, middle);
            forkJoinDemo.fork();//拆分任务，把任务压入线程队列
            ForkJoinDemo forkJoinDemo2 = new ForkJoinDemo(middle + 1, end);
            forkJoinDemo2.fork();
            return forkJoinDemo.join() + forkJoinDemo2.join();
        }


    }
}



