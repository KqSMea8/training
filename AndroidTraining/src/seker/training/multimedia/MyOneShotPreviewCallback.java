package seker.training.multimedia;

import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;

public class MyOneShotPreviewCallback implements PreviewCallback {

    int w =720;
    int h = 280;
    int sw = 400;
    int sh = 400;
    
    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
//        String result = Decoder.getInstance().decode(data);
//        if (null == result) {
//            camera.setOneShotPreviewCallback(this);
//        } else {
//            
//        }
    }

}
