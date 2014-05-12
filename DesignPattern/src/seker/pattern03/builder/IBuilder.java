package seker.pattern03.builder;

import seker.pattern02.abstract_factory.IProduct1;
import seker.pattern02.abstract_factory.IProduct2;
import seker.pattern02.abstract_factory.IProduct3;

public interface IBuilder
{
    public void newProduct();
    
    public void buildModule1(IProduct1 module1);
    
    public void buildModule2(IProduct2 module2);
    
    public void buildModule3(IProduct3 module3);
    
    public ComplexProduct getProduct();
}
