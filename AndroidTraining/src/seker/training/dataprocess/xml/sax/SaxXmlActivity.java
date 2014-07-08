/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.dataprocess.xml.sax;

import java.io.IOException;
import java.io.InputStream;

import seker.common.BaseActivity;
import seker.training.dataprocess.Channel;
import seker.training.dataprocess.ChannelAdapter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

/**
 * 
 * @author seker
 * @since 2013年11月9日
 */
public class SaxXmlActivity extends BaseActivity {
    
    public static final String TAG = "sax.xml";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try {
            InputStream is = getAssets().open("data/data.xml");
            Channel channel = new SaxXmlParser().parse(is);
            Log.i(TAG, channel+"");
            
//            try {
//                String json = channel.toJson().toString();
//                StreamUtils.streamToFile(new ByteArrayInputStream(json.getBytes()), new File("/sdcard/data.json"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            
//            ChannelProto.Channel.Builder cBuilder = ChannelProto.Channel.newBuilder();
//            cBuilder.setTitle(channel.title);
//            cBuilder.setLink(channel.link);
//            cBuilder.setDescription(channel.description);
//            cBuilder.setPubDate(channel.pubDate);
//            cBuilder.setLastBuildDate(channel.lastBuildDate);
//            for (Item item : channel.items) {
//                ChannelProto.Channel.Item.Builder iBuilder = ChannelProto.Channel.Item.newBuilder();
//                iBuilder.setTitle(item.title);
//                iBuilder.setLink(item.link);
//                iBuilder.setDescription(item.description);
//                iBuilder.setPubDate(item.pubDate);
//                
//                ChannelProto.Channel.Item.Guid.Builder gBuilder = ChannelProto.Channel.Item.Guid.newBuilder();
//                gBuilder.setUrl(item.guid.url);
//                gBuilder.setIsPermaLink(item.guid.isPermaLink);
//                
//                iBuilder.setGuid(gBuilder.build());
//                
//                cBuilder.addItem(iBuilder.build());
//            }
//            ChannelProto.Channel pChannel = cBuilder.build();
//            
//            FileOutputStream output = new FileOutputStream("/sdcard/data.pb");
//            pChannel.writeTo(output);
//            output.flush();
//            output.close();
            
            ListView listview = new ListView(this);
            ChannelAdapter adapter = new ChannelAdapter(channel);
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(adapter);
            setContentView(listview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
