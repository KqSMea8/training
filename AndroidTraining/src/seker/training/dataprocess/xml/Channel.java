/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.dataprocess.xml;

import java.util.List;

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
    public List<Item> items;
}
