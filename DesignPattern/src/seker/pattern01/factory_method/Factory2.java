package seker.pattern01.factory_method;

public class Factory2 implements IFactory
{
    @Override
    public IProduct createProduct()
    {
        return new Product2();
    }
}
