package seker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author lifeix
 *
 */
public class Main {
    public static final String LX_END   = "(\\\\n)+|(\\s)+";
    
    public static void main(String[] args) {
        
        System.out.println("0".compareTo("1"));
        System.out.println("0".compareTo("2"));
        System.out.println("1".compareTo("2"));
        System.out.println("00".compareTo("01"));
        System.out.println("00".compareTo("02"));
        System.out.println("01".compareTo("02"));
        System.out.println("10".compareTo("01"));
        System.out.println("2".compareTo("0"));
        System.out.println("2".compareTo("1"));
        
        System.out.println("q2".compareTo("p1"));
        
        /*
        String str = "  \n  ";
        boolean b = Pattern.matches(LX_END, str);
        System.out.print(b);
        */
        
        List<String> list = new ArrayList<String>(10);
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        
        Iterator<String> iter = list.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
            iter.remove();
        }
    }
}
