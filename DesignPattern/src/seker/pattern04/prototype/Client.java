package seker.pattern04.prototype;

public class Client
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        AbstractSpoon spoon = new SoupSpoon(); 
        System.out.println(spoon.getSpoonName());
        
        AbstractSpoon spoon2 = (AbstractSpoon) spoon.clone();
        System.out.println(spoon2.getSpoonName());
    }
}
