package seker.training.dataprocess.json.android;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import seker.common.utils.StreamUtils;
import seker.training.dataprocess.Channel;
import seker.training.dataprocess.Guid;
import seker.training.dataprocess.Item;

public class AndroidJsonParser {
    
    public Channel parse(InputStream is) {
        Channel channel = null;
        String str = StreamUtils.streamToString(is);
        try {
            JSONObject json = new JSONObject(str);
            channel = parseChannel(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return channel;
    }
    
    private Channel parseChannel(JSONObject json) throws JSONException {
        Channel channel = new Channel();
        channel.title = json.optString(Channel.LABEL_TITLE);
        channel.link = json.optString(Channel.LABEL_LINK);
        channel.description = json.optString(Channel.LABEL_DESCRIPTION);
        channel.pubDate = json.optString(Channel.LABEL_PUBDATE);
        channel.lastBuildDate = json.optString(Channel.LABEL_LASTBUILDDATE);
        JSONArray jsons = json.optJSONArray(Item.LABEL_JSON_ITEMS);
        for (int i = 0, n = jsons.length(); i < n; i++) {
            Item item = parseItem(jsons.getJSONObject(i));
            channel.items.add(item);
        }
        return channel;
    }
    
    private Item parseItem(JSONObject json) throws JSONException {
        Item item = new Item();
        item.title = json.optString(Item.LABEL_TITLE);
        item.link = json.optString(Item.LABEL_LINK);
        item.description = json.optString(Item.LABEL_DESCRIPTION);
        item.pubDate = json.optString(Item.LABEL_PUBDATE);
        
        JSONObject guid = json.getJSONObject(Guid.LABEL_GUID);
        item.guid = new Guid();
        String value = guid.optString(Guid.LABEL_ISPERMALINK);
        item.guid.isPermaLink = Boolean.parseBoolean(value);
        item.guid.url = guid.optString(Guid.LABEL_URL);
        return item;
        
    }
}
