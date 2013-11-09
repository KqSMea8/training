/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.dataprocess;

/**
 * 
 * @author seker
 * @since 2013年11月9日
 */
public class Guid {
    
    public static String LABEL_GUID = "guid";
    
    public static String LABEL_ISPERMALINK = "isPermaLink";
    
    public boolean isPermaLink;
    
    public String url;

    @Override
    public String toString() {
        return "Guid [isPermaLink=" + isPermaLink + ", url=" + url + "]";
    }
}
