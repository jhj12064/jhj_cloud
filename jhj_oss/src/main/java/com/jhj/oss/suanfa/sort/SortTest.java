package com.jhj.oss.suanfa.sort;


import org.junit.Test;

import java.util.Arrays;

/**
 * https://blog.csdn.net/weixin_44531966/article/details/116464294
 *
 * @author Jeremy
 * @date 2022/6/6 15:58
 */
public class SortTest {

    private int[] a = {46, 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};


    private void swap(int[] a, int i, int j) {
        int i1 = a[i];
        a[i] = a[j];
        a[j] = i1;
    }

    @Test
    public void maopao1() {
        for (int i = 0; i < a.length; i++) {
            for (int j = a.length - 1; j > i; j--) {
                //从后面比较两个数
                if (a[j] < a[j - 1]) {
                    //交换
                    swap(a, j, j - 1);
                }
            }
        }
        Arrays.stream(a).forEach(System.out::println);
    }

    /**
     * 优化，如果某次循环一次交换都没有，说明已经排序完成了
     */
    @Test
    public void maopao2() {
        boolean flag = true;
        for (int i = 0; i < a.length && flag; i++) {
            flag = false;
            for (int j = a.length - 1; j > i; j--) {
                if (a[j] < a[j - 1]) {
                    //交换
                    swap(a, j, j - 1);
                    flag = true;
                }
            }
        }
        Arrays.stream(a).forEach(System.out::println);
    }

    /**
     * 选择排序
     */
    @Test
    public void select1() {
        for (int i = 0; i < a.length - 1; i++) {
            int index = i;//标记第一个为待比较的数
            for (int j = i + 1; j < a.length; j++) { //然后从后面遍历与第一个数比较
                if (a[j] < a[index]) {  //如果小,就交换最小值
                    index = j;//保存最小元素的下标
                }
            }
            //找到最小值后，将最小的值放到第一的位置，进行下一遍循环
            if (a[index] < a[i]) {
                swap(a, i, index);
            }
        }
        System.out.println(Arrays.toString(a));//[2, 3, 4, 5, 15, 19, 26, 27, 36, 38, 44, 46, 47, 48, 50]
    }

    /**
     * 插入排序,像打牌一样，后两个一次比较
     */
    @Test
    public void insert1() {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j >= 1; j--) { //然后从后面遍历与第一个数比较
                if (a[j - 1] > a[j]) {
                    swap(a, j - 1, j);
                }
            }
        }
        System.out.println(Arrays.toString(a));//[2, 3, 4, 5, 15, 19, 26, 27, 36, 38, 44, 46, 47, 48, 50]
    }


}



