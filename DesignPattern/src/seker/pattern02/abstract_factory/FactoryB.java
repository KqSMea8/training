package seker.pattern02.abstract_factory;

public class FactoryB implements IFactory
{
    @Override
    public IProduct1 createProduct1()
    {
        return new ProductB1();
    }

    @Override
    public IProduct2 createProduct2()
    {
        return new ProductB2();
    }
    
    @Override
    public IProduct3 createProduct3()
    {
        return new ProductB3();
    }
}
