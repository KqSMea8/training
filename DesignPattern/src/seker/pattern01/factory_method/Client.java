package seker.pattern01.factory_method;

public class Client
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        IFactory factory = new Factory1();
        IProduct product = factory.createProduct();
        product.productMethod();
        
        factory = new Factory2();
        product = factory.createProduct();
        product.productMethod();
    }
}
