package seker.grammer.string;

import java.lang.ref.WeakReference;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        WeakReference<String> wr = new WeakReference<String>((String) null);
        System.out.println(wr.get());
        
        String newStr = "80";
        String oldStr = "8";
        System.out.println(newStr.compareTo(oldStr) > 0);
        
        newStr = "800000";
        oldStr = "80";
        System.out.println(newStr.compareTo(oldStr) > 0);
        
        newStr = "800010";
        oldStr = "800009";
        System.out.println(newStr.compareTo(oldStr) > 0);
        
        int a = Integer.valueOf("899999");
        System.out.println(a);
    }
}
