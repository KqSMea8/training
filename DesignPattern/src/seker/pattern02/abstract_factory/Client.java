package seker.pattern02.abstract_factory;

public class Client
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        IFactory factoryA = new FactoryA();
        
        IProduct1 product1 = factoryA.createProduct1();
        product1.productMethod1();
        IProduct2 product2 = factoryA.createProduct2();
        product2.productMethod2();
        IProduct3 product3 = factoryA.createProduct3();
        product3.productMethod3();
        
        IFactory factoryB = new FactoryB();
        
        product1 = factoryB.createProduct1();
        product1.productMethod1();
        product2 = factoryB.createProduct2();
        product2.productMethod2();
        product3 = factoryB.createProduct3();
        product3.productMethod3();
    }
}
