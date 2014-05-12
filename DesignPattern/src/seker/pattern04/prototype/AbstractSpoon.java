/*
 * Copyright (C) 2012 Baidu Inc. All rights reserved.
 */
package seker.pattern04.prototype;

/**
 * 
 * @author liuxinjian
 * @since 2012-8-28
 */
public abstract class AbstractSpoon implements Cloneable 
{
    protected String _spoonName;

    public void setSpoonName(String spoonName) 
    {
        _spoonName = spoonName;
    }

    public String getSpoonName() 
    {
        return _spoonName;
    }

    @Override
    public Object clone() 
    {
        Object object = null;
        try 
        {
            object = super.clone();
        } 
        catch (CloneNotSupportedException exception) 
        {
            System.err.println("AbstractSpoon is not Cloneable");
        }
        return object;
    }
}
