/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.algorithm.math;

/**
 * 如果一个正整数只有两个因子, 1和p，则称p为素数. 1不是素数
 * 
 * @author liuxinjian
 * @since 2013-1-24
 */
public class Prime {

    /**
     * 时间复杂度O(n).
     * 
     * @param n
     * @return
     */
    public static final boolean isPrime1(int n) {
        if (n < 2) {
            return false;
        }
        
        if (n == 2) {
            return true;
        }

        for (int i = 3; i < n; i ++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 改进, 去掉偶数的判断.
     * 
     * 时间复杂度O(n/2), 速度提高一倍.
     * 
     * @param n
     * @return
     */
    public static final boolean isPrime2(int n) {
        if (n < 2) {
            return false;
        }
        
        if (n == 2) {
            return true;
        }

        for (int i = 3; i < n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 进一步减少判断的范围
     * 定理: 如果n不是素数, 则n有满足1<d<=sqrt(n)的一个因子d.
     * 证明: 如果n不是素数, 则由定义n有一个因子d满足1<d<n.
     * 如果d大于sqrt(n), 则n/d是满足1<n/d<=sqrt(n)的一个因子.
     * 
     * 时间复杂度O(sqrt(n)/2), 速度提高O((n-sqrt(n))/2).
     * 
     * @param n
     * @return
     */
    public static final boolean isPrime3(int n) {
        if (n < 2) {
            return false;
        }
        
        if (n == 2) {
            return true;
        }

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
    

 
    /**
     * 4. 剔除因子中的重复判断.
     * 例如: 11%3 != 0 可以确定 11%(3*i) != 0.
     * 
     * 定理: 
     * 如果n不是素数, 则n有满足1<d<=sqrt(n)的一个"素数"因子d.
     * 
     * 证明: 
     * I1. 如果n不是素数, 则n有满足1<d<=sqrt(n)的一个因子d.
     * I2. 如果d是素数, 则定理得证, 算法终止.
     * I3. 令n=d, 并转到步骤I1.
     * 
     * 由于不可能无限分解n的因子, 因此上述证明的算法最终会停止.
     * @param primes
     * @param n
     * @return
     */
    // primes[i]是递增的素数序列: 2, 3, 5, 7, ...
    // 更准确地说primes[i]序列包含1->sqrt(n)范围内的所有素数
    public static final boolean isPrime4(int primes[], int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 0; primes[i] * primes[i] <= n; ++i) {
            if (n % primes[i] == 0) {
                return false;
            }
        }

        return true;
    }
    
    
 
    // 构造素数序列primes[]
    public static final void makePrimes(int primes[], int num) {
        primes[0] = 2;
        primes[1] = 3;
        for (int i = 5, cnt = 2; cnt < num; i += 2) {
            boolean flag = true;
            for (int j = 1; primes[j] * primes[j] <= i; ++j) {
                if (i % primes[j] == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                primes[cnt++] = i;
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        final int N = 20;
        
        // 查找前N个素数
        int primes[] = new int[N];
        makePrimes(primes, N);
        
        StringBuilder builder = new StringBuilder();
        for (int i = 0, n = primes.length; i < n; i++) {
            builder.append(primes[i]).append(", ");
        }
        System.out.println(builder.substring(0, builder.length() - 2));
    }

}
