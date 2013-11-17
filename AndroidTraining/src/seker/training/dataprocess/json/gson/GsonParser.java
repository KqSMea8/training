package seker.training.dataprocess.json.gson;

import java.io.InputStream;

import seker.common.utils.StreamUtils;
import seker.training.dataprocess.Channel;

import com.google.gson.Gson;

public class GsonParser {
    
    public Channel parse(InputStream is) {
        String json = StreamUtils.streamToString(is);
        Channel channel = new Gson().fromJson(json, Channel.class);
        return channel;
    }
}
