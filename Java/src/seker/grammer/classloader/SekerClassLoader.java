/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.grammer.classloader;

/**
 * @author xinjian.lxj
 *
 */
public class SekerClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }
    
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
    
}
