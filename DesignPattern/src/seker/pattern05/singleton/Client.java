package seker.pattern05.singleton;

class SingletonTest implements Runnable
{
    private int _index;
    
    public SingletonTest(int index)
    {
        _index = index;
    }

    @Override
    public void run()
    {
        Singleton.getSingleton().setData(_index);
    }
}

public class Client
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        for (int i = 0; i < 10; i ++)
        {
            new Thread(new SingletonTest(i)).start();
        }
    }
}
