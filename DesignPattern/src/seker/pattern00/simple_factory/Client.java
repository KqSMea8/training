package seker.pattern00.simple_factory;

public class Client
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        IProduct product = SampleFactory.createProduct("Product1");
        product.productMethod();
        
        product = SampleFactory.createProduct("Product1");
        product.productMethod();
    }
}
