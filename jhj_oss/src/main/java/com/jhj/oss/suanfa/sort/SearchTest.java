package com.jhj.oss.suanfa.sort;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * 查找算法
 *
 * @author jeremyji
 * @date 2022年 12月06日 16:42:25
 */
public class SearchTest {

    private static Integer[] a = {0, 1, 16, 24, 35, 46, 56, 67, 78, 88, 99};
    private static Integer[] b = {0, 1, 16, 24, 35, 46, 56, 67, 78, 88, 99};

    private static Integer[] F;

    static {
        F = new Integer[20];
        for (int i = 0; i < 20; i++) {
            F[i] = getF(i);
        }
    }


    /**
     * 获取斐波那契数列
     *
     * @return
     */
    public static int getF(int i) {
        if (i == 0) {
            return 0;
        } else if (i == 1) {
            return 1;
        } else {
            return getF(i - 1) + getF(i - 2);
        }

    }

    /**
     * 二分
     */
    @Test
    public void binary() {
        int key = 68;
        Integer result = null;

        int low = 0;
        int high = a.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (key < a[mid]) {
                high = mid - 1;
            } else if (key > a[mid]) {
                low = mid + 1;
            } else {
                result = a[mid];
                break;
            }
        }
        System.out.println("result 结果：" + result);
    }

    /**
     * 插值查找
     */
    @Test
    public void insert() {
        int key = 67;
        Integer result = null;

        int low = 0;
        int high = a.length - 1;
        int mid;
        while (low <= high) {
            mid = low + (high - low) * (key - a[low]) / (a[high] - a[low]);
            if (key < a[mid]) {
                high = mid - 1;
            } else if (key > a[mid]) {
                low = mid + 1;
            } else {
                result = a[mid];
                break;
            }
        }
        System.out.println("result 结果：" + result);
    }

    @Test
    public void fibonacci() {
        Stream.of(F).forEach(System.out::println);
        int key = 68;
        int n = a.length - 1;
        Integer result = null;

        int low = 0;
        int high = 0;
        int mid = 0;
        int i = 0;
        int k = 0;
        while (n > F[k] - 1) {
            k++;
        }

    }

}
