package seker.pattern08.proxy;

public class RealSubject implements ISubject {

    @Override
    public void request() {
        System.out.println("运行业务功能");
    }

}
