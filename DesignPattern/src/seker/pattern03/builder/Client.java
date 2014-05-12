package seker.pattern03.builder;

public class Client
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        IBuilder builder = new Builder();
        builder.newProduct();
        builder.buildModule1(null);
        builder.buildModule2(null);
        builder.buildModule3(null);
        ComplexProduct product = builder.getProduct();
        product.productMethod();
    }
}
