package com.cdkj.dzt.common;

import java.math.BigDecimal;

public class AmountUtil {
    public static Long mul(Long amount, double rate) {
        BigDecimal a = new BigDecimal(Double.toString(amount));
        BigDecimal b = new BigDecimal(Double.toString(rate));
        return a.multiply(b).longValue();
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

    public static void main(String[] args) {
        System.out.println(mul(100000L, 0.009));
    }
}
