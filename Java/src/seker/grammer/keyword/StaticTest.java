package seker.grammer.keyword;

/**
 * @author lifeix
 *
 */
public class StaticTest {
    private static StaticTest singleton = new StaticTest(5);
    
    private static int static_data = 1;
    
    private int data = -1;
    
    private StaticTest(int d) {
        data = d;
    }
    
    public static StaticTest getSingleTon() {
        return new StaticTest(6);
    }
    
    public static void main(String[] args) {
        StaticTest.getSingleTon();
    }
}
