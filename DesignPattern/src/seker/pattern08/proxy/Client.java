package seker.pattern08.proxy;

public class Client {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ISubject subject = new ProxySubject();
        subject.request();
    }

}
