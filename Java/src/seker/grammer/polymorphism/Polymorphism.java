package seker.grammer.polymorphism;

/**
 * @author Lifeix
 *
 */
public class Polymorphism 
{
    public void f()
    {
        System.out.println("public Polymorphism.f()");
    }
    
//    public static void main(String[] args)
//    {
//        Polymorphism po = new Derived();
//        po.f();
//    }
}

class Derived extends Polymorphism
{
    public void f()
    {
        System.out.println("public Derived.f()");
    }
    
    public static void main(String[] args)
    {
        Polymorphism po = (Polymorphism)new Derived();
        po.f();
    }
}

