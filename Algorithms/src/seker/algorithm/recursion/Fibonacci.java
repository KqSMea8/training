package seker.algorithm.recursion;


/**
 * 斐波那契数列
 * @author liuxinjian
 */
public class Fibonacci {
    
    /**
     * 斐波那契数列：递归的方式实现
     * @param n
     * @return
     * @throws Exception
     */
    public int fibonacciRecursion(int n) throws Exception {
        if (0 == n) {
            return 0;
        } else if (1 == n) {
            return 1;
        } else if (n >= 2) {
            return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2);
        } else {
            throw new Exception("negative can't fibonacci.");
        }
    }
    
    /**
     * 斐波那契数列：非递归的方式(循环；迭代)实现
     * @param n
     * @return
     * @throws Exception
     */
    public int fibonacciRecurrence(int n) throws Exception {
        if (0 == n) {
            return 0;
        } else if (1 == n) {
            return 1;
        } else if (n >= 2) {
            int f_1 = 0, f_2 = 1;
            int result = 0;
            for (int i = 2; i <= n; i ++) {
                result = f_1 + f_2;
                f_1 = f_2;
                f_2 = result;
            }
            return result;
        }else {
            throw new Exception("negative can't fibonacci.");
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        
        Fibonacci fibonacci = new Fibonacci();
        
        System.out.println(fibonacci.fibonacciRecursion(0));
        System.out.println(fibonacci.fibonacciRecursion(1));
        System.out.println(fibonacci.fibonacciRecursion(2));
        System.out.println(fibonacci.fibonacciRecursion(3));
        System.out.println(fibonacci.fibonacciRecursion(4));
        System.out.println(fibonacci.fibonacciRecursion(5));
        System.out.println(fibonacci.fibonacciRecursion(6));
        
        System.out.println();
        
        System.out.println(fibonacci.fibonacciRecurrence(0));
        System.out.println(fibonacci.fibonacciRecurrence(1));
        System.out.println(fibonacci.fibonacciRecurrence(2));
        System.out.println(fibonacci.fibonacciRecurrence(3));
        System.out.println(fibonacci.fibonacciRecurrence(4));
        System.out.println(fibonacci.fibonacciRecurrence(5));
        System.out.println(fibonacci.fibonacciRecurrence(6));
    }
    
}
