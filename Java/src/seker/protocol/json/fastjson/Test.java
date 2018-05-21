package seker.protocol.json.fastjson;

import com.alibaba.fastjson.JSON;

/**
 * @author xinwen
 * @since 2018-01-17
 */
public class Test {

    public static void main(String[] args) {
        String jsonStr = "{\\\\\"fixedMobileNum\\\\\":\\\\\"mobile-4\\\\\"}";

        String json = "{\n"
            + "  \"a\": 6\n"
            + "}";

        A a = JSON.parseObject(json, A.class);


        System.out.print(a.toString());
    }

    static class A {
        public float a;

        @Override
        public String toString() {
            return "A{" +
                "a=" + a +
                '}';
        }
    }
}
