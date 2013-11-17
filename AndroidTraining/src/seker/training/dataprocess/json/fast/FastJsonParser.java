package seker.training.dataprocess.json.fast;

import java.io.InputStream;

import seker.common.utils.StreamUtils;
import seker.training.dataprocess.Channel;

import com.alibaba.fastjson.JSON;

public class FastJsonParser {
    
    public Channel parse(InputStream is) {
        String json = StreamUtils.streamToString(is);
        Channel channel = JSON.parseObject(json, Channel.class);
        return channel;
    }
}
