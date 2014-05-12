/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.algorithm.math;

/**
 * 
 * @author liuxinjian
 * @since 2013-1-24
 */
public final class Mathematics {

    /**
     * 辗转相除法求出这两个数的最大公约数
     * @param i
     * @param j
     * @return
     */
    public static final long greatestCommonDivisor(final long i, final long j) {
        long max = Math.max(i, j);
        long min = i + j - max;
        
        long k;
        while ((k = max % min) != 0) {
            max = min;
            min = k;
        }
        return (min);
    }
    
    /**
     * 可以先用辗转相除法求出这两个数的最大公约数，
     * 再用这两个数的乘积除以它们的最大公约数，就得到它们的最小公倍数。
     * @param m
     * @param n
     * @return
     */
    public static final long leastCommonMultiple(final long i, final long j) {
        return (i * j / greatestCommonDivisor(i, j));
    }
    
    public static void main(String[] args) {
        final long i = 63;
        final long j = -135;
        
        long x = greatestCommonDivisor(i, j);
        System.out.println(x);
        
        long y = leastCommonMultiple(i, j);
        System.out.println(y);
    }
}
