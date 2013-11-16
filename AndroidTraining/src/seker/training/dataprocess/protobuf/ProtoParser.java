package seker.training.dataprocess.protobuf;

import java.io.IOException;
import java.io.InputStream;

import seker.training.dataprocess.protobuf.ChannelProto.Channel;

class ProtoParser {
    
    public Channel parse(InputStream is) {
        Channel channel = null;
        try {
            channel = Channel.parseFrom(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return channel;
    }
}
