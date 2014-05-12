/**
 * 
 */
package seker.grammer.mathematics;

import java.util.Random;

/**
 * @author seker
 *
 */
public class RandomNumber {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());
        int number1 = 0;
        double number2 = 0;
        for (int i = 1; i <= 100; i ++) {
            number1 = Math.abs(random.nextInt());
            number2 = Math.random();
            System.out.println(String.format("%3d: %2d, %10d, %8f", i, number1 % 2, number1, number2));
        }
        
        
        
    }

}
