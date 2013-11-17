/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.dataprocess.xml.pull;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import seker.common.utils.TextUtils;
import seker.training.dataprocess.Channel;
import seker.training.dataprocess.Guid;
import seker.training.dataprocess.Item;

/**
 * 
 * @author seker
 * @since 2013年11月9日
 */
public class PullXmlParser {
    /**
     * 
     * @param is
     * @return
     */
    public Channel parse(InputStream is) {
        Channel channel = null;
        // 构建XmlPullParserFactory
        try {
            XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
            // 获取XmlPullParser的实例
            XmlPullParser xmlPullParser = pullParserFactory.newPullParser();
            // 设置输入流 xml文件
            xmlPullParser.setInput(is, "UTF-8");

            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name = xmlPullParser.getName();
                switch (eventType) {
                case XmlPullParser.START_TAG:// 开始节点
                    if (TextUtils.equals(Channel.LABEL_CHANNEL, name)) {
                        channel = parseChannel(xmlPullParser);
                    }
                    break;
                case XmlPullParser.END_TAG:// 结束节点
                    break;
                default:
                    break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return channel;
    }
    
    private Channel parseChannel(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Channel channel = new Channel();
        boolean finish = false;
        int eventType = xmlPullParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT && !finish) {
            String name = xmlPullParser.getName();
            switch (eventType) {
            case XmlPullParser.START_TAG:// 开始节点
                if (TextUtils.equals(Channel.LABEL_TITLE, name)) {
                    channel.title = xmlPullParser.nextText();
                } else if (TextUtils.equals(Channel.LABEL_LINK, name)) {
                    channel.link = xmlPullParser.nextText();
                } else if (TextUtils.equals(Channel.LABEL_DESCRIPTION, name)) {
                    channel.description = xmlPullParser.nextText();
                } else if (TextUtils.equals(Channel.LABEL_PUBDATE, name)) {
                    channel.pubDate = xmlPullParser.nextText();
                } else if (TextUtils.equals(Channel.LABEL_LASTBUILDDATE, name)) {
                    channel.lastBuildDate = xmlPullParser.nextText();
                } else if (TextUtils.equals(Item.LABEL_ITEM, name)) {
                    Item item = parseItem(xmlPullParser);
                    channel.items.add(item);
                }
                break;
            case XmlPullParser.END_TAG:// 结束节点
                if (TextUtils.equals(Channel.LABEL_CHANNEL, name)) {
                    finish = true;
                }
                break;
            default:
                break;
            }
            eventType = xmlPullParser.next();
        }
        return channel;
    }
    
    private Item parseItem(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Item item = new Item();
        boolean finish = false;
        int eventType = xmlPullParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT && !finish) {
            String name = xmlPullParser.getName();
            switch (eventType) {
            case XmlPullParser.START_TAG:// 开始节点
                if (TextUtils.equals(Item.LABEL_TITLE, name)) {
                    item.title = xmlPullParser.nextText();
                } else if (TextUtils.equals(Item.LABEL_LINK, name)) {
                    item.link = xmlPullParser.nextText();
                } else if (TextUtils.equals(Item.LABEL_DESCRIPTION, name)) {
                    item.description = xmlPullParser.nextText();
                } else if (TextUtils.equals(Item.LABEL_PUBDATE, name)) {
                    item.pubDate = xmlPullParser.nextText();
                } else if (TextUtils.equals(Guid.LABEL_GUID, name)) {
                    item.guid = new Guid();
                    String value = xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(), Guid.LABEL_ISPERMALINK);
                    item.guid.isPermaLink = Boolean.parseBoolean(value);
                    item.guid.url = xmlPullParser.nextText();
                }
                break;
            case XmlPullParser.END_TAG:// 结束节点
                if (TextUtils.equals(Item.LABEL_ITEM, name)) {
                    finish = true;
                }
                break;
            default:
                break;
            }
            eventType = xmlPullParser.next();
        }
        return item;
    }
}
