package seker.grammer.initialize;

class Parent {

    // 静态变量
    public static String sStaticField = "父类--静态变量";

    // 变量
    public String mField = "父类--变量";

    // 静态初始化块
    static {
        System.out.println(sStaticField);
        System.out.println("父类--静态初始化块");
    }

    // 初始化块
    {
        System.out.println(mField);
        System.out.println("父类--初始化块");
    }
    
    // 构造
    public Parent() {
        System.out.println("父类--构造器");
        init();
    }
    
    protected void init() {
        System.out.println(mField.length());
    }
}

class Child extends Parent {
    
    // 静态变量
    public static String sStaticField = "子类--静态变量";

    // 变量
    public String mField = "子类--变量";
    
    // 静态初始化块
    static {
        System.out.println(sStaticField);
        System.out.println("子类--静态初始化块");
    }
    
    // 初始化块
    {
        System.out.println(mField);
        System.out.println("子类--初始化块");
    }
    
    // 构造器
    public Child() {
        System.out.println("子类--构造器");
        init();
    }
    
    @Override
    protected void init() {
        System.out.println(mField.length());
    }
}

public class Initialize {
    public static void main(String args[]) {
        new Child();
    }
}
