package seker.grammer.string;

import java.lang.ref.WeakReference;
import java.util.Arrays;

public class Main {

    public static final int INT_20 = 20;

    /**
     * @param args
     */
    public static void main(String[] args) {
        String aa = "mobile-framework-framework";
        String[] aaa = aa.split("\\|");
        
        System.out.println("Yes".equalsIgnoreCase(null));
        
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

        byte[] bytes = new byte[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,};
        System.out.println(data2String(bytes));

        short[] shorts = new short[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,};
        System.out.println(data2String(shorts));

        int[] ints = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,};
        System.out.println(data2String(ints));
    }

    public static String data2String(byte[] data) {
        if (null != data && data.length > INT_20) {
            StringBuilder sb = new StringBuilder(INT_20 * 6);
            sb.append('[');
            sb.append(data[0]);
            for (int i = 1; i < INT_20; i++) {
                sb.append(", ");
                sb.append(data[i]);
            }
            sb.append(']');
            return sb.toString();
        } else {
            return Arrays.toString(data);
        }
    }

    public static String data2String(short[] data) {
        if (null != data && data.length > INT_20) {
            StringBuilder sb = new StringBuilder(INT_20 * 6);
            sb.append('[');
            sb.append(data[0]);
            for (int i = 1; i < INT_20; i++) {
                sb.append(", ");
                sb.append(data[i]);
            }
            sb.append(']');
            return sb.toString();
        } else {
            return Arrays.toString(data);
        }
    }

    public static String data2String(int[] data) {
        if (null != data && data.length > INT_20) {
            StringBuilder sb = new StringBuilder(INT_20 * 6);
            sb.append('[');
            sb.append(data[0]);
            for (int i = 1; i < INT_20; i++) {
                sb.append(", ");
                sb.append(data[i]);
            }
            sb.append(']');
            return sb.toString();
        } else {
            return Arrays.toString(data);
        }
    }
}
