package com.jhj.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author Jeremy
 */
public class CalculateUtils {

    static final DecimalFormat df2DecimalPlaces = new DecimalFormat("0.00");

    public static Integer add(Integer a, Integer b) {
        if (a == null) a = 0;
        if (b == null) b = 0;
        return a + b;
    }

    public static Integer division(int a, int b) {
        if (b == 0) return 0;
        return a / b;
    }

    /**
     * @Description: 保留两位小数
     * @Param: * @param Integer a(分子) ,Integer(分母)
     * @Return: double
     * @Date: 2021/10/11
     */
    public static double get2DecimalPlaces(Integer a, Integer b) {
        if (a == null || b == null) return 0;
        return b == 0 ? 0 : new BigDecimal((float) a / b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double getPercent2DecimalPlaces(Integer a, Integer b) {
        if (a == null || b == null) return 0;
        a = a * 100;
        return b == 0 ? 0 : new BigDecimal((float) a / b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double getChangePercent2DecimalPlaces(Integer a, Integer b) {
        if (a == null || b == null) return 0;
        if (b == 0) return 0;
        return b == 0 ? 0 : new BigDecimal((float) ((a - b) * 100) / b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double get2DecimalPlaces(Double a, Double b) {
        if (a == null || b == null) return 0;
        return b == 0 ? 0 : new BigDecimal(a / b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double get2DecimalPlaces(Long a, Long b) {
        if (a == null || b == null) return 0;
        return b == 0 ? 0 : new BigDecimal((float) a / b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double get2DecimalPlaces(Double a, Integer b) {
        if (a == null || b == null) return 0;
        return b == 0 ? 0 : new BigDecimal(a / b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double getPercent2DecimalPlaces(Double a, Double b) {
        if (a == null || b == null) return 0;
        a = a * 100;
        return b == 0 ? 0 : new BigDecimal(a / b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double getPercent2DecimalPlaces(Long a, Long b) {
        if (a == null || b == null) return 0;
        a = a * 100;
        return b == 0 ? 0 : new BigDecimal((float) a / b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double getChangePercent2DecimalPlaces(Double a, Double b) {
        if (a == null || b == null) return 0;
        if (b == 0) return 0;
        return b == 0 ? 0 : new BigDecimal(((a - b) * 100) / b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * @Description:保留两位小数
     * @Param: * @param Double
     * @Return: String
     * @Date: 2021/9/7
     */
    public static double get2DecimalPlaces(Double i) {
        return i == null ? 0 : new BigDecimal(i).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static BigDecimal divideBigDecimal (BigDecimal a, BigDecimal b) {
        if(a == null
                || b == null
                || a.compareTo(BigDecimal.ZERO ) <=0
                || b.compareTo(BigDecimal.ZERO ) <=0
        ){
            return BigDecimal.ZERO;
        }
        return a.divide(b, 2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal divideBigDecimalPercent (BigDecimal a, BigDecimal b) {
        if(a == null
                || b == null
                || a.compareTo(BigDecimal.ZERO ) <=0
                || b.compareTo(BigDecimal.ZERO ) <=0
        ){
            return BigDecimal.ZERO;
        }
        return a.multiply(new BigDecimal(100)).divide(b, 2, BigDecimal.ROUND_HALF_UP);
    }



    /**
     * BigDecimal格式化，保留两位小数
     *
     * @param a
     * @return
     */
    public static BigDecimal formatBigDecimal(BigDecimal a, int scale) {
        if (a == null) {
            a = BigDecimal.ZERO;
        }
        return a.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 金钱格式化，保留 scale 位小数
     * Double类型有点问题，9->9.0
     *
     * @param obj
     */
    public static void format(Object obj, int scale) {
        try {
            Class aClass = obj.getClass();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), aClass);
                // 获取get方法
                Method getMethod = pd.getReadMethod();
                // 获取set方法
                Method setMethod = pd.getWriteMethod();
                // 通过get方法获取到值
                Object o = getMethod.invoke(obj);
                if (o != null) {
                    if(o instanceof BigDecimal){
                        BigDecimal bigDecimal = (BigDecimal)o;
                        setMethod.invoke(obj, formatBigDecimal(bigDecimal, scale));
                    }
                    if(o instanceof Double){
                        Double d = (Double)o;
                        setMethod.invoke(obj, formatBigDecimal(new BigDecimal(String.valueOf(d)), scale).doubleValue());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
