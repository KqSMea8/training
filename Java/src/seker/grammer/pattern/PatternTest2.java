package seker.grammer.pattern;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lifeix
 *
 */
public class PatternTest2 {
    public static void main(String[] args) {
        System.out.println("ZOLOZ_T_E_ST_TEST_TEST".replaceAll("((T|t)(E|e)(S|s)(T|t))", "Tset"));
        System.out.println("ZOLOZ_T_E_ST_test_TESt".replaceAll("(T|t)(E|e)(S|s)(T|t)", "Tset"));
        System.out.println("ZOLOZ_T_E_ST_TSET_TSET".replaceAll("(T|t)(E|e)(S|s)(T|t)", "Tset"));

        System.out.println("W/uwkOp7AeUDACf5BDu+hVH7".replaceAll("(/|\\+)", "_"));
    }
}
