package seker.io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * Created by seker on 28/4/18.
 */
public class YUVTest {

    public static final int COUNT = 50;
    static byte[] yuvCache = null;


    public static void main(String[] args) throws IOException {
        File file = new File("/Users/seker/log/Download/2018-04-28_01-19-31-380_infoReady_raw.bin");

        {
            long start = System.currentTimeMillis();
            for (int i = 0; i < COUNT; i ++) {
                byte[] data = FileUtils.readFileToByteArray(file);
                mirrorYUV420(data, 640, 480);
            }
            System.out.println(COUNT + " times, mirrorYUV420() cost " + (System.currentTimeMillis() - start) + " ms");
        }

        {
            long start = System.currentTimeMillis();
            for (int i = 0; i < COUNT; i ++) {
                byte[] data = FileUtils.readFileToByteArray(file);
                mirror(data, 640, 480);
            }
            System.out.println(COUNT + " times, mirror() cost " + (System.currentTimeMillis() - start) + " ms");
        }
    }


    private static byte[] mirrorYUV420(byte[] data, int width, int height) {
        if (yuvCache == null){
            yuvCache = new byte[width * height * 3 / 2];
        }
        int i = 0;
        for (int y = 0; y < height; y++) {
            for (int x = width - 1; x >= 0; x--) {
                yuvCache[i] = data[y * width + x];
                i++;
            }
        }

        for (int y = height; y < height*3/2; y++) {
            for (int x = width - 2; x >= 0; x-=2) {
                yuvCache[i] = data[y * width + x];
                i++;
                yuvCache[i] = data[y * width + x + 1];
                i++;
            }
        }
        System.arraycopy(yuvCache, 0, data, 0, yuvCache.length);
        return data;
    }

    private static void mirror(byte[] yuv_temp, int w,int h) {
        int i;

        int a, b;
        byte temp;
        //mirror y
        for (i = 0; i < h; i++){
            a= i * w;
            b= (i + 1) * w - 1;
            while (a < b) {
                temp= yuv_temp[a];
                yuv_temp[a]= yuv_temp[b];
                yuv_temp[b]= temp;
                a++;
                b--;
            }
        }
        //mirror u
        int uindex = w * h;
        for (i = 0; i < h / 2;i++) {
            a = i * w / 2;
            b= (i + 1) * w / 2 - 1;
            while (a < b) {
                temp= yuv_temp[a + uindex];
                yuv_temp[a+ uindex] = yuv_temp[b + uindex];
                yuv_temp[b+ uindex] = temp;
                a++;
                b--;
            }
        }
        //mirror v
        uindex= w * h / 4 * 5;
        for (i = 0; i < h / 2;i++) {
            a= i * w / 2;
            b= (i + 1) * w / 2 - 1;
            while (a < b) {
                temp= yuv_temp[a + uindex];
                yuv_temp[a+ uindex] = yuv_temp[b + uindex];
                yuv_temp[b+ uindex] = temp;
                a++;
                b--;
            }
        }
    }
}
