/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.dataprocess.xml.sax;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import seker.training.dataprocess.Channel;

/**
 * 
 * @author seker
 * @since 2013年11月9日
 */
public class SaxXmlParser {
    /**
     * 
     * @param is
     * @return
     */
    public Channel parse(InputStream is) {
        Channel channel = null;
        
        SAXParser parser = null;
        try {
            
            SAXParserFactory factoty = SAXParserFactory.newInstance();
            //构建SAXParser
            parser = factoty.newSAXParser();
            //实例化  DefaultHandler对象
            SaxXmlParseHandler handler = new SaxXmlParseHandler();
            //调用parse()方法
            parser.parse(is, handler);
            
            channel = handler.getResult();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return channel;
    }
}
