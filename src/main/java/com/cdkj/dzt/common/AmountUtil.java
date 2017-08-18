package com.cdkj.dzt.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountUtil {
    public static Long mul(Long amount, double rate) {
        BigDecimal a = new BigDecimal(Double.toString(amount));
        BigDecimal b = new BigDecimal(Double.toString(rate));
        return a.multiply(b).longValue();
    }

    public static Double add(double rate1, double rate2) {
        BigDecimal a = new BigDecimal(Double.toString(rate1));
        BigDecimal b = new BigDecimal(Double.toString(rate2));
        return a.add(b).doubleValue();
    }

    public static Double subtract(double rate1, double rate2) {
        BigDecimal a = new BigDecimal(Double.toString(rate1));
        BigDecimal b = new BigDecimal(Double.toString(rate2));
        return a.subtract(b).doubleValue();
    }

    public static Long mul(Double amount, Long number) {
        BigDecimal a = new BigDecimal(Double.toString(amount));
        BigDecimal b = new BigDecimal(Double.toString(number));
        return a.multiply(b).longValue();
    }

    public static double div(Double amount, Long number) {
        BigDecimal a = new BigDecimal(Double.toString(amount));
        BigDecimal b = new BigDecimal(Double.toString(number));
        return a.divide(b).doubleValue();
    }

    /**
     * 人民币进分
     * @param amount
     * @return 
     * @create: 2017年4月21日 下午2:02:10 
     * @history:
     */
    public static Long mulRmbJinFen(Long amount, double rate) {
        BigDecimal a = new BigDecimal(Double.toString(amount));
        BigDecimal b = new BigDecimal(Double.toString(rate));
        return rmbJinFen(a.multiply(b).doubleValue());
    }

    public static Long rmbJinFen(Double amount) {
        // 保留到元
        double result = div(amount, 10L);
        result = Math.ceil(result);
        return mul(result, 10L);
    }

    /**
     * 虚拟币进个位数
     * @param amount
     * @param rate
     * @return 
     * @create: 2017年4月21日 下午2:02:31 
     * @history:
     */
    public static Long mulXnbJin1(Long amount, double rate) {
        BigDecimal a = new BigDecimal(Double.toString(amount));
        BigDecimal b = new BigDecimal(Double.toString(rate));
        return xnbJin1(a.multiply(b).doubleValue());
    }

    public static Long xnbJin1(Double amount) {
        // 保留到元
        double divAmount = div(amount, 1000L);
        double result = Math.ceil(divAmount);
        return mul(result, 1000L);
    }

    public static Long div(Long amount, double rate) {
        BigDecimal a = new BigDecimal(Double.toString(amount));
        BigDecimal b = new BigDecimal(Double.toString(rate));
        return a.divide(b, RoundingMode.FLOOR).longValue();
    }

    public static void main(String[] args) {
        System.out.println(mul(100000L, 0.74));
    }
}
