package seker.protocol.json.fastjson;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class Test2 {

    public static void main(String[] args) {

        System.out.println(JSON.toJSONString(new Bean()));

        Bean2 bean2 = new Bean2();
        bean2.extInfo.put("int_", 12);
        bean2.extInfo.put("string_", "str");
        System.out.println(JSON.toJSONString(bean2));
    }
}

class Bean {
    public String name = "test";
    public int age = 18;
    public String extInfo;
}

class Bean2 {
    public String name = "test";
    public int age = 18;
    public Map extInfo = new HashMap();
}
