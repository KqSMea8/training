package seker.algorithm.math;

public class Binary {
    
    static int foo(int x) {
        int count=0;
        while(0!=x) {
            count++;
            x=x&(x-1);
            System.out.println(x);
        }
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println(foo(5678));
    }
}
