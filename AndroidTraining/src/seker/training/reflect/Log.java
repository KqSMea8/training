package seker.training.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Log {
    
    public static class LogTest implements Runnable {
        @Override
        public void run() {
            Log.v("seker", "log.v()");
            Log.d("seker", "log.d()");
            Log.i("seker", "log.i()");
            Log.w("seker", "log.w()");
            Log.e("seker", "log.e()");
        }
    }

    public static void v(String tag, String msg) {
        try {
            Class<?> clazz = Class.forName("android.util.Log");
            Method m = clazz.getMethod("v", new Class[] {String.class, String.class});
            m.invoke(clazz, new Object[] {tag, msg});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    public static void d(String tag, String msg) {
        try {
            Class<?> clazz = Class.forName("android.util.Log");
            Method m = clazz.getMethod("d", new Class[] {String.class, String.class});
            m.invoke(clazz, new Object[] {tag, msg});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    public static void i(String tag, String msg) {
        try {
            Class<?> clazz = Class.forName("android.util.Log");
            Method m = clazz.getMethod("i", new Class[] {String.class, String.class});
            m.invoke(clazz, new Object[] {tag, msg});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    public static void w(String tag, String msg) {
        try {
            Class<?> clazz = Class.forName("android.util.Log");
            Method m = clazz.getMethod("w", new Class[] {String.class, String.class});
            m.invoke(clazz, new Object[] {tag, msg});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    public static void e(String tag, String msg) {
        try {
            Class<?> clazz = Class.forName("android.util.Log");
            Method m = clazz.getMethod("e", new Class[] {String.class, String.class});
            m.invoke(clazz, new Object[] {tag, msg});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
