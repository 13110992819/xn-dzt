package com.xnjr.mall.common;

import java.math.BigDecimal;

public class AmountUtil {
    public static Long mul(Long amount, double rate) {
        BigDecimal a = new BigDecimal(Double.toString(amount));
        BigDecimal b = new BigDecimal(Double.toString(rate));
        return a.multiply(b).longValue();
    }

    public static Long div(Long amount, double rate) {
        BigDecimal a = new BigDecimal(Double.toString(amount));
        BigDecimal b = new BigDecimal(Double.toString(rate));
        return a.divide(b).longValue();
    }

    public static void main(String[] args) {
        System.out.println(mul(100000L, 0.009));
        System.out.println(div(3L, 2.0));
    }
}
