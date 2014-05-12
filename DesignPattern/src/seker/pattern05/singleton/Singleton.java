package seker.pattern05.singleton;

public class Singleton
{
    private int _data = 0;
    private static Singleton _instance;
    private Singleton()
    {
        System.out.println("new Singleton()");
    }
    
    public static synchronized Singleton getSingleton()
    {
        //String name = Thread.currentThread().getName();
        //System.out.println(String.format("%s: in", name));
        if (null == _instance)
        {
            _instance = new Singleton();
        }
        //System.out.println(String.format("%s: out", name));
        return _instance;
    }
    
    public synchronized void setData(int data)
    {
        String name = Thread.currentThread().getName();
        System.out.println(String.format("%s: _data = %d, data = %d", name, _data, data));
        _data = data;
    }
}
