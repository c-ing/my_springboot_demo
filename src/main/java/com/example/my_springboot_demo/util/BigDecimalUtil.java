package com.example.my_springboot_demo.util;

import java.math.BigDecimal;

/**
 * BigDecimal计算工具类
 */
public class BigDecimalUtil {

    /**
     * 金额除法计算，返回2位小数
     * @param b1
     * @param b2
     * @return
     */
    public static <T extends Number> BigDecimal safeDivide(T b1, T b2){
        return safeDivide(b1, b2, BigDecimal.ZERO);
    }



    /**
     * BigDecimal的除法运算封装，如果除数或者被除数为0，返回默认值
     * 默认返回小数位后2位，用于金额计算
     * @param b1
     * @param b2
     * @param defaultValue
     * @return
     */

    public static <T extends Number> BigDecimal safeDivide(T b1, T b2, BigDecimal defaultValue) {
        if (null == b1 ||  null == b2) {
            return defaultValue;
        }
        try {
            return BigDecimal.valueOf(b1.doubleValue()).divide(BigDecimal.valueOf(b2.doubleValue()), 2, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * BigDecimal的除法运算封装，如果除数或者被除数为0，返回默认值
     * @param b1
     * @param b2
     * @param scale  保留小数点
     * @param <T>
     * @return
     */
    public static <T extends Number> BigDecimal safeDivide(T b1, T b2, int scale) {
        if (null == b1 ||  null == b2) {
            return BigDecimal.ZERO;
        }
        try {
            return BigDecimal.valueOf(b1.doubleValue()).divide(BigDecimal.valueOf(b2.doubleValue()), scale, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }


    /**
     * BigDecimal的乘法运算封装
     * @param b1
     * @param b2
     * @return
     */
    public static <T extends Number> BigDecimal safeMultiply(T b1, T b2) {
        if (null == b1 ||  null == b2) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(b1.doubleValue()).multiply(BigDecimal.valueOf(b2.doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * BigDecimal的加法运算封装
     */
    public static BigDecimal safeAdd(BigDecimal b1, BigDecimal... bn) {
        if (null == b1) {
            b1 = BigDecimal.ZERO;
        }
        if (null != bn) {
            for (BigDecimal b : bn) {
                b1 = b1.add(null == b ? BigDecimal.ZERO : b);
            }
        }
        return b1;
    }

    public static void main(String[] args) {
        System.out.println(BigDecimalUtil.safeDivide(1, 0, 4));
    }

}
