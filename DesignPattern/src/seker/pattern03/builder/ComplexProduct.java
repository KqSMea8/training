package seker.pattern03.builder;

import seker.pattern02.abstract_factory.IProduct1;
import seker.pattern02.abstract_factory.IProduct2;
import seker.pattern02.abstract_factory.IProduct3;

public class ComplexProduct
{
    protected IProduct1 _module1;
    protected IProduct2 _module2;
    protected IProduct3 _module3;
    
    public void setModule1(IProduct1 module1)
    {
        _module1 = module1;
    }
    
    public void setModule2(IProduct2 module2)
    {
        _module2 = module2;
    }
    
    public void setModule3(IProduct3 module3)
    {
        _module3 = module3;
    }
    
    public void productMethod()
    {
    }
}