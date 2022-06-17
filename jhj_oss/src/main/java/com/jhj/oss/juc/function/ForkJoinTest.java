package com.jhj.oss.juc.function;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * 三种求和方式
 * @author Jeremy
 * @date 2022/6/17 11:11
 */
public class ForkJoinTest {

    public static void main(String[] args) throws Exception {
        test3();
    }

    //9037
    public static void test1(){
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for (Long i = 1L; i < 1000000000; i++) {
            sum +=i;
        }
        long end = System.currentTimeMillis();
        System.out.println("zongshijian:" + (end -start));
    }
//用forkjoin 7717
    public static void test2() throws Exception, InterruptedException {
        Long sun = 0L;
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 1000000000L);
       // forkJoinPool.execute(task);//执行
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long aLong = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("zongshijian:" + (end -start));
    }

    //stream并行流 132
    public static void test3(){
        Long sun = 0L;
        long start = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(0L, 1000000000L).parallel().reduce(0, Long::sum);
        System.out.println(reduce);
        long end = System.currentTimeMillis();
        System.out.println("zongshijian:" + (end -start));
    }
}
