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
public class Item {
    public static final String LABEL_JSON_ITEMS = "items";
    
    public static final String LABEL_ITEM = "item";

    public static final String LABEL_TITLE = "title";
    public static final String LABEL_LINK = "link";
    public static final String LABEL_DESCRIPTION = "description";
    public static final String LABEL_PUBDATE = "pubDate";

    public String title;
    public String link;
    public String description;
    public String pubDate;
    public Guid guid;
    
    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(LABEL_TITLE, title);
        json.put(LABEL_LINK, link);
        json.put(LABEL_DESCRIPTION, description);
        json.put(LABEL_PUBDATE, pubDate);
        json.put(Guid.LABEL_GUID, guid.toJson());
        return json;
    }
    
    @Override
    public String toString() {
        return "Item [title=" + title + ", link=" + link + ", description=" + description + ", pubDate=" + pubDate
                + ", guid=" + guid + "]";
    }
}
