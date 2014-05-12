package seker.grammer.mathematics;

/**
 * @author Lifeix
 *
 */
public class MathDemo {
    public static void main(String[] args) {
        // return (long/int) floor(d + 0.5d);
        System.out.println("Math.round(53.51)=" + Math.round(53.51));
        System.out.println("Math.round(53.50)=" + Math.round(53.50));
        System.out.println("Math.round(53.49)=" + Math.round(53.49));
        System.out.println("Math.round(-20.51)=" + Math.round(-20.51));
        System.out.println("Math.round(-20.50)=" + Math.round(-20.50));
        System.out.println("Math.round(-20.49)=" + Math.round(-20.49));
        
        System.out.println("Math.floor(53.54)=" + Math.floor(53.54));
        System.out.println("Math.floor(-20.50)=" + Math.floor(-20.50));
        
        System.out.println("Math.ceil(53.54)=" + Math.ceil(53.54));
        System.out.println("Math.ceil(-20.50)=" + Math.ceil(-20.50));
        
//        byte      1       -128 ~ 127
//        short     2       -32768 ~ 32767
//        int       4       -2147483648 ~ 2147483647   
//        long      8       -9223372036854775808 ~ 9223372036854774807
        
//        char              in Java, char is UTF-16

//        float     4       ±3.40282347E+38F (. 6-7 )
//        double    8       ±1.79769313486231570E+308 (. 15)

//        boolean 
        
//        boolean b = false;
//        int a = (int)b;
//        System.out.print(a);
    }
}
