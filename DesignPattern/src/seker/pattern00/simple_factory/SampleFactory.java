package seker.pattern00.simple_factory;

public class SampleFactory
{
    public static IProduct createProduct(String strProductName)
    {
        IProduct product = null;
        if ("Product1".equals(strProductName))
        {
            product = new Product1();
        }
        else if ("Product2".equals(strProductName))
        {
            product = new Product2();
        }
        return product;
    }
}
