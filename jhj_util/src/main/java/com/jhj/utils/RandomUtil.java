package com.jhj.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.Random;

public class RandomUtil {

    public static String getRandomCharUtil(int count){
        char cha[]={'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y'};
        char ch[]=new char[count];
        for(int i=0;i< ch.length;i++)
        {
            int index;
            index=(int)(Math.random()*(cha.length));
            ch[i]=cha[index];
        }
        return Arrays.toString(ch);
    }

    public static int getRandomNumUtils(int count)
    {
        String s = RandomStringUtils.randomNumeric(count);
        return Integer.parseInt(s);
    }

    public static String getRandomNumStr(int count)
    {
        String s = RandomStringUtils.randomNumeric(count);
        return s;
    }

    public static int getRandomNumUtils(int start, int end)
    {
        return new Random().nextInt(end - start) + start;
    }
}
