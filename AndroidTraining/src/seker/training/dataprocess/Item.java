/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.dataprocess;

/**
 * 
 * @author seker
 * @since 2013年11月9日
 */
public class Item {
    public static String LABEL_ITEM = "item";

    public static String LABEL_TITLE = "title";
    public static String LABEL_LINK = "link";
    public static String LABEL_DESCRIPTION = "description";
    public static String LABEL_PUBDATE = "pubDate";

    public String title;
    public String link;
    public String description;
    public String pubDate;
    public Guid guid;
    
    @Override
    public String toString() {
        return "Item [title=" + title + ", link=" + link + ", description=" + description + ", pubDate=" + pubDate
                + ", guid=" + guid + "]";
    }
}
