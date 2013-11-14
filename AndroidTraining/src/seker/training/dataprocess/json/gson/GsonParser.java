package seker.training.dataprocess.json.gson;

import java.io.InputStream;

import seker.common.utils.StreamUtils;
import seker.training.dataprocess.Channel;

import com.google.gson.Gson;

class GsonParser {
    
    public Channel parse(InputStream is) {
        String gson = StreamUtils.streamToString(is);
        Channel channel = new Gson().fromJson(gson, Channel.class);
        return channel;
    }
}
