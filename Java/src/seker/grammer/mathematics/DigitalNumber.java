package seker.grammer.mathematics;

/**
 * @author Lifeix
 *
 */
public class DigitalNumber {
    public static void main(String[] args) {
        DigitalNumber digital = new DigitalNumber();
        digital.digital(943554685);
        digital.digital(943554685.0);
        digital.digital(943554685.0000);
        digital.digital(943585.000124);
        digital.digital(585.024);
    }
    
    public void digital(long digital) {
        int log10 = (int) Math.log10(digital);
        System.out.println("digital=" + digital + ", log10=" + log10);
        for (int i = log10; i >= 0; i --) {
            int base = (int) Math.pow(10, i);
            int temp = (int) (digital / base);
            digital = (int) (digital % base);
            System.out.print(temp + ", ");
        }
        System.out.println();
    }
    
    public void digital(double fdigital) {
        long digital = (long)fdigital;
        int log10 = (int) Math.log10(digital);
        System.out.println("fdigital=" + fdigital + ", log10=" + log10);
        for (int i = log10; i >= 0; i --) {
            int base = (int) Math.pow(10, i);
            int temp = (int) (digital / base);
            digital = (int) (digital % base);
            System.out.print(temp + ", ");
        }
        System.out.print("_._ ");
        fdigital = fdigital - Math.floor(fdigital);
        digital = (long) (fdigital * 100);
        for (int i = 1; i >= 0; i --) {
            int basee = (int) Math.pow(10, i);
            int temp = (int) (digital / basee);
            digital = (int) (digital % basee);
            System.out.print(temp + ", ");
        }
        System.out.println();
    }
}
