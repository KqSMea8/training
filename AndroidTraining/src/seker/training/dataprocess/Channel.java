/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.dataprocess;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author seker
 * @since 2013年11月9日
 */
public class Channel {
    public static String LABEL_CHANNEL = "channel";

    public static String LABEL_TITLE = "title";
    public static String LABEL_LINK = "link";
    public static String LABEL_DESCRIPTION = "description";
    public static String LABEL_PUBDATE = "pubDate";
    public static String LABEL_LASTBUILDDATE = "lastBuildDate";
    
    public String title;
    public String link;
    public String description;
    public String pubDate;
    public String lastBuildDate;
    public List<Item> items = new ArrayList<Item>();
    
    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(LABEL_TITLE, title);
        json.put(LABEL_LINK, link);
        json.put(LABEL_DESCRIPTION, description);
        json.put(LABEL_PUBDATE, pubDate);
        json.put(LABEL_LASTBUILDDATE, lastBuildDate);
        
        JSONArray jsons = new JSONArray();
        for (Item item : items) {
            jsons.put(item.toJson());
        }
        json.put(Item.LABEL_ITEM + "s", jsons);
        
        return json;
    }
    
    @Override
    public String toString() {
        return "Channel [title=" + title + ", link=" + link + ", description=" + description + ", pubDate=" + pubDate
                + ", lastBuildDate=" + lastBuildDate + ", items=" + items + "]";
    }
}
