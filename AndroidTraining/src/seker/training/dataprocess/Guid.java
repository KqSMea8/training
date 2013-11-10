/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.dataprocess;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author seker
 * @since 2013年11月9日
 */
public class Guid {
    
    public static String LABEL_GUID = "guid";
    
    public static String LABEL_ISPERMALINK = "isPermaLink";
    
    public static String LABEL_URL = "url";
    
    public boolean isPermaLink;
    
    public String url;

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(LABEL_ISPERMALINK, isPermaLink);
        json.put(LABEL_URL, url);
        return json;
    }
    
    @Override
    public String toString() {
        return "Guid [isPermaLink=" + isPermaLink + ", url=" + url + "]";
    }
}
