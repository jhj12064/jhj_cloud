package com.jhj.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordNoUtil {
    private static final String timePormat = "yyyyMMddHHmmssSSS";
    private static final String timePormat2 = "mmssSSS";


    public static String createResourceNo(int count){
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(timePormat);
        String no_f = sdf.format(currentDate);
        no_f = no_f  + RandomUtil.getRandomNumStr(count);
        return no_f;
    }

}
