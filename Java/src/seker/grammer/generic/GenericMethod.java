package seker.grammer.generic;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by seker on 19/1/18.
 */
public class GenericMethod {

    private HashMap<String, Thread> map = new HashMap<String, Thread>();

    class A extends Thread {

    }

    class B extends A {

    }

    class C extends B {

    }

    class D extends C {

    }

    class E extends C {

    }

    public <I extends Runnable, L extends I> I putBioService(Class<L> clazz) {
        I i = null;
        try {
            Method method = getClass().getDeclaredMethod("putBioService", Class.class);
            Type[] types = method.getGenericParameterTypes();
            String interfaceName = types[0].getClass().getName();

            try {
                i = clazz.newInstance();
                map.put(interfaceName, (Thread) i);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static void main(String[] args) {
        GenericMethod genericMethod = new GenericMethod();
        genericMethod.putBioService(E.class);
        genericMethod.putBioService(D.class);
    }
}
