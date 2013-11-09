/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.dataprocess.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import seker.common.utils.TextUtils;
import seker.training.dataprocess.Channel;
import seker.training.dataprocess.Guid;
import seker.training.dataprocess.Item;
import android.util.Log;

/**
 * 
 * @author seker
 * @since 2013年11月9日
 */
public class ChannelParseHandler extends DefaultHandler {
    
    private StringBuilder buffer = new StringBuilder();
    
    private Channel mChannel = null;
    private Item mItem = null;
    private Guid mGuid = null;
    
    private static final int STATE_INVALIDE = -1;
    
    private static final int STATE_CHANEL = 0;
    private static final int STATE_CHANEL_TITLE = 1;
    private static final int STATE_CHANEL_LINK = 2;
    private static final int STATE_CHANEL_DESCRIPTION = 3;
    private static final int STATE_CHANEL_PUBDATE = 4;
    private static final int STATE_CHANEL_LASTBUILDDATE = 5;
    
    private static final int STATE_ITEM = 10;
    private static final int STATE_ITEM_TITLE = 11;
    private static final int STATE_ITEM_LINK = 12;
    private static final int STATE_ITEM_DESCRIPTION = 13;
    private static final int STATE_ITEM_PUBDATE = 14;
    private static final int STATE_GUID = 15;
    
    private int mState = STATE_INVALIDE;
    
    public Channel getResult() {
        return mChannel;
    }
    
    /**
     * 
     * @param uri
     * @param localName
     * @param qName
     * @return
     */
    private int rectifyStateByQName(String uri, String localName, String qName) {
        int state = STATE_INVALIDE;
        if (TextUtils.equals(qName, Channel.LABEL_CHANNEL)) {
            state = STATE_CHANEL;
        } else if (TextUtils.equals(qName, Item.LABEL_ITEM)) {
            state = STATE_ITEM;
        } else if (TextUtils.equals(qName, Guid.LABEL_GUID)) {
            state = STATE_GUID;
        }  else if (TextUtils.equals(qName, Channel.LABEL_LASTBUILDDATE)) {
            state = STATE_CHANEL_LASTBUILDDATE;
        } else {
            if (mState < STATE_ITEM) {
                if (TextUtils.equals(qName, Channel.LABEL_TITLE)) {
                    state = STATE_CHANEL_TITLE;
                } else if (TextUtils.equals(qName, Channel.LABEL_LINK)) {
                    state = STATE_CHANEL_LINK;
                } else if (TextUtils.equals(qName, Channel.LABEL_DESCRIPTION)) {
                    state = STATE_CHANEL_DESCRIPTION;
                } else if (TextUtils.equals(qName, Channel.LABEL_PUBDATE)) {
                    state = STATE_CHANEL_PUBDATE;
                }
            } else {
                if (TextUtils.equals(qName, Item.LABEL_TITLE)) {
                    state = STATE_ITEM_TITLE;
                } else if (TextUtils.equals(qName, Item.LABEL_LINK)) {
                    state = STATE_ITEM_LINK;
                } else if (TextUtils.equals(qName, Item.LABEL_DESCRIPTION)) {
                    state = STATE_ITEM_DESCRIPTION;
                } else if (TextUtils.equals(qName, Item.LABEL_PUBDATE)) {
                    state = STATE_ITEM_PUBDATE;
                }
            }
        }
        return state;
    }
    
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        if (SaxXmlActivity.LOG) {
            Log.i(SaxXmlActivity.TAG, "startDocument()");
        }
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (SaxXmlActivity.LOG) {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            for (int i =0, n = attributes.getLength(); i < n; i++) {
                builder.append(attributes.getQName(i)).append("=").append(attributes.getValue(i)).append(",");
            }
            builder.append("]");
            Log.i(SaxXmlActivity.TAG, String.format("startElement(uri=%s, localName=%s, qName=%s, attributes=%s)", uri, localName, qName, builder.toString()));
        }
        
        mState = rectifyStateByQName(uri, localName, qName);
        switch (mState) {
        case STATE_CHANEL:
            mChannel = new Channel();
            break;
        case STATE_ITEM:
            mItem = new Item();
            mChannel.items.add(mItem);
            break;
        case STATE_GUID:
            mGuid = new Guid();
            mItem.guid = mGuid;
            mGuid.isPermaLink = Boolean.parseBoolean(attributes.getValue(Guid.LABEL_ISPERMALINK));
            break;
        default:
            break;
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        buffer.append(ch, start, length);
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String characters = buffer.toString().trim();
        if (SaxXmlActivity.LOG) {
            Log.i(SaxXmlActivity.TAG, String.format("characters(characters=%s)", characters));
        }
        mState = rectifyStateByQName(uri, localName, qName);
        switch (mState) {
        case STATE_CHANEL_TITLE:
            mChannel.title = characters;
            break;
        case STATE_CHANEL_LINK:
            mChannel.link = characters;
            break;
        case STATE_CHANEL_DESCRIPTION:
            mChannel.description = characters;
            break;
        case STATE_CHANEL_PUBDATE:
            mChannel.pubDate = characters;
            break;
        case STATE_CHANEL_LASTBUILDDATE:
            mChannel.lastBuildDate = characters;
            break;
        case STATE_ITEM_TITLE:
            mItem.title = characters;
            break;
        case STATE_ITEM_LINK:
            mItem.link = characters;
            break;
        case STATE_ITEM_DESCRIPTION:
            mItem.description = characters;
            break;
        case STATE_ITEM_PUBDATE:
            mItem.pubDate = characters;
            break;
        case STATE_GUID:
            mGuid.url = characters;
            break;
        default:
            break;
        }
        buffer.delete(0, buffer.length());
        
        if (SaxXmlActivity.LOG) {
            Log.i(SaxXmlActivity.TAG, String.format("endElement(uri=%s, localName=%s, qName=%s)", uri, localName, qName));
        }
        super.endElement(uri, localName, qName);
    }
    
    @Override
    public void endDocument() throws SAXException {
        if (SaxXmlActivity.LOG) {
            Log.i(SaxXmlActivity.TAG, "endDocument()");
        }
        super.endDocument();
    }
}
