package seker.pattern02.abstract_factory;

public class FactoryA implements IFactory
{
    @Override
    public IProduct1 createProduct1()
    {
        return new ProductA1();
    }

    @Override
    public IProduct2 createProduct2()
    {
        return new ProductA2();
    }
    
    @Override
    public IProduct3 createProduct3()
    {
        return new ProductA3();
    }
}
