package seker.pattern08.proxy;

public class ProxySubject implements ISubject {

    private ISubject mRealSubject;
    
    @Override
    public void request() {
        if (null == mRealSubject) {
            mRealSubject = new RealSubject();
        }
        
        System.out.println("代理调用业务方法开始：");
        mRealSubject.request();
        System.out.println("代理调用业务方法完毕！");

    }

}
