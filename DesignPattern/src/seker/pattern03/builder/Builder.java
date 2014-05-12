package seker.pattern03.builder;

import seker.pattern02.abstract_factory.IProduct1;
import seker.pattern02.abstract_factory.IProduct2;
import seker.pattern02.abstract_factory.IProduct3;

public class Builder implements IBuilder
{
    private ComplexProduct _product;
    
    public void newProduct() {
        _product = new ComplexProduct();
    }
    
    @Override
    public void buildModule1(IProduct1 module1)
    {
        _product.setModule1(module1);
    }

    @Override
    public void buildModule2(IProduct2 module2)
    {
        _product.setModule2(module2);
    }

    @Override
    public void buildModule3(IProduct3 module3)
    {
        _product.setModule3(module3);
    }
    
    public ComplexProduct getProduct() 
    {
        ComplexProduct product = _product;
        _product = null;
        return product;
    }
}