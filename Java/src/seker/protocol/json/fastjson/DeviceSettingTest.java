package seker.protocol.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alipay.zoloz.hardware.DeviceSetting;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeviceSettingTest {
    public static void main(String[] args)  {
        String json = "{\"algorithmAngle\":90,\"cameraID\":0,\"minApiLevel\":16,\"displayAuto\":true,\"maxApiLevel\":101,\"algorithmAuto\":false,\"cameraAuto\":true,\"displayAngle\":90}";

        DeviceSetting deviceSetting = JSON.parseObject(json, DeviceSetting.class);
        System.out.println("deviceSetting=" + deviceSetting);

        com.alipay.mobile.security.faceauth.circle.protocol.DeviceSetting bioDeviceSetting =
                JSON.parseObject(json, com.alipay.mobile.security.faceauth.circle.protocol.DeviceSetting.class);
        System.out.println("bioDeviceSettings=" + bioDeviceSetting);


        json = "[{\"algorithmAngle\":90,\"cameraID\":0,\"minApiLevel\":16,\"displayAuto\":true,\"maxApiLevel\":101,\"algorithmAuto\":false,\"cameraAuto\":true,\"displayAngle\":90}]";

        System.out.println("");
        List<DeviceSetting> deviceSettings = JSON.parseArray(json, DeviceSetting.class);
        System.out.println("deviceSettings=" + Arrays.toString(deviceSettings.toArray()));

        List<com.alipay.mobile.security.faceauth.circle.protocol.DeviceSetting> bioDeviceSettings
                = JSON.parseArray(json, com.alipay.mobile.security.faceauth.circle.protocol.DeviceSetting.class);
        System.out.println("bioDeviceSettings=" + Arrays.toString(bioDeviceSettings.toArray()));
    }
}
