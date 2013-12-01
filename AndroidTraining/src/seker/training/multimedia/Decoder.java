package seker.training.multimedia;

public class Decoder {
    
    private static volatile Decoder sInstance;
    
    private Decoder() {
        
    }
    
    public static Decoder getInstance() {
        if (null == sInstance) {
            synchronized (Decoder.class) {
                if (null == sInstance) {
                    sInstance = new Decoder();
                }
            }
        }
        return sInstance;
    }
    
    public String decode(byte[] data, int w, int h, int sw, int sh) {
        byte[] sdata = new byte[sw * sh * 4];
        
        int index = 0;
        int sindex = 0;
        int halfh = (h - sh) / 2;
        int halfw = (w - sw) / 2;
        for (int i = halfh; i < h - halfh; i++) {
            for (int j = halfw; j < w - halfw; j++) {
                index = i * w  + j;
                
                sdata[sindex++] = data[index++];
                sdata[sindex++] = data[index++];
                sdata[sindex++] = data[index++];
                sdata[sindex++] = data[index++];
            }
        }
        
        return null;
    }

}
