package seker.grammer.classes;

public class Access {
    public static void main(String[] args) {
        String a = "abc";
        String b = "abc";
        String c = new String("abc");
        String d = new String("abc");
        
        System.out.println(a==b);
        System.out.println(b==c);
        System.out.println(c==d);
        System.out.println(d==a);
        System.out.println(a.equals(b));
        System.out.println(b.equals(c));
        System.out.println(c.equals(d));
        System.out.println(d.equals(a));
    }
}

class A {
    D d;
    public class B {
        
    }
    protected class C {
        
    }
    private class D {
        
    }
}
