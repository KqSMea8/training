/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.dataprocess.xml.dom;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import seker.training.dataprocess.Channel;
import seker.training.dataprocess.Guid;
import seker.training.dataprocess.Item;

/**
 * 
 * @author seker
 * @since 2013年11月9日
 */
class DomXmlParser {
    /**
     * 
     * @param is
     * @return
     */
    public Channel parse(InputStream is) {
        Channel channel = null;
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(is);
            Element root = document.getDocumentElement();
            NodeList items = root.getElementsByTagName(Channel.LABEL_CHANNEL);
            if (null != items && items.getLength() > 0) {
                Element element = (Element) items.item(0);
                channel = parseChannel(element);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return channel;
    }
    
    private Channel parseChannel(Element element) {
        Channel channel = new Channel();
        
        NodeList items = element.getElementsByTagName(Channel.LABEL_TITLE);
        if (null != items && items.getLength() > 0) {
            channel.title = items.item(0).getTextContent();
        }
        
        items = element.getElementsByTagName(Channel.LABEL_DESCRIPTION);
        if (null != items && items.getLength() > 0) {
            channel.description = items.item(0).getTextContent();
        }
        
        items = element.getElementsByTagName(Channel.LABEL_LINK);
        if (null != items && items.getLength() > 0) {
            channel.link = items.item(0).getTextContent();
        }
        
        items = element.getElementsByTagName(Channel.LABEL_PUBDATE);
        if (null != items && items.getLength() > 0) {
            channel.pubDate = items.item(0).getTextContent();
        }
        
        items = element.getElementsByTagName(Channel.LABEL_LASTBUILDDATE);
        if (null != items && items.getLength() > 0) {
            channel.lastBuildDate = items.item(0).getTextContent();
        }
        
        items = element.getElementsByTagName(Item.LABEL_ITEM);
        if (null != items) {
            for (int i = 0, n = items.getLength(); i < n; i++) {
                Element e = (Element) items.item(0);
                Item item = parseItem(e);
                channel.items.add(item);
            }
        }
        return channel;
    }
    
    private Item parseItem(Element element) {
        Item item = new Item();
        
        NodeList items = element.getElementsByTagName(Item.LABEL_TITLE);
        if (null != items && items.getLength() > 0) {
            item.title = items.item(0).getTextContent();
        }
        
        items = element.getElementsByTagName(Item.LABEL_DESCRIPTION);
        if (null != items && items.getLength() > 0) {
            item.description = items.item(0).getTextContent();
        }
        
        items = element.getElementsByTagName(Item.LABEL_LINK);
        if (null != items && items.getLength() > 0) {
            item.link = items.item(0).getTextContent();
        }
        
        items = element.getElementsByTagName(Item.LABEL_PUBDATE);
        if (null != items && items.getLength() > 0) {
            item.pubDate = items.item(0).getTextContent();
        }
        
        items = element.getElementsByTagName(Guid.LABEL_GUID);
        if (null != items && items.getLength() > 0) {
            item.guid = new Guid();
            String value = items.item(0).getAttributes().getNamedItem(Guid.LABEL_ISPERMALINK).getTextContent();
            item.guid.isPermaLink = Boolean.parseBoolean(value);
            item.guid.url = items.item(0).getTextContent();
        }
        
        return item;
    }
}
