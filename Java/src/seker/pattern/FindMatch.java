package seker.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * Created by seker on 18/4/18.
 */
public class FindMatch {

    public static void main(String[] args) {
        // 要验证的字符串
        String str = "baike.xsoftlab.net";
        // 正则表达式规则
        String regEx = "baike.*";
        // 编译正则表达式
        Pattern pattern = compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 查找字符串中是否有匹配正则表达式的字符/字符串
        boolean rs = matcher.find();
        System.out.println(rs);



        getTeacherList("教师10(0010)\n"
            + "教师11(0011)\n"
            + "教师9(009)\n"
            + "教师12(0012)\n"
            + "教师13(0013)\n"
            + "教师14(0014)");

        getUi("{\n"
            + "  \"coll\": {\n"
            + "    \"TopText_blink\": \"眨眨眼\",\n"
            + "    \"topText_noface\": \"没有检测到脸\",\n"
            + "    \"topText_integrity\": \"把脸移入框内\",\n"
            + "    \"topText_angle\": \"请正对手机\",\n"
            + "    \"actionMode\": [\n"
            + "      \"7\",\n"
            + "      \"7\",\n"
            + "      \"7\",\n"
            + "      \"7\",\n"
            + "      \"7\",\n"
            + "      \"7\",\n"
            + "      \"7\",\n"
            + "      \"7\",\n"
            + "      \"7\",\n"
            + "      \"7\"\n"
            + "    ],\n"
            + "    \"bottomText\": \"请把脸放入框内保持不动\",\n"
            + "    \"TopText_max_rectwidth\": \"离远一点\",\n"
            + "    \"uploadMonitorPic\": 1,\n"
            + "    \"uploadBigPic\": true,\n"
            + "    \"light\": 204,\n"
            + "    \"topText_light\": \"脸部亮一点\",\n"
            + "    \"topText_stay\": \"请保持不动\",\n"
            + "    \"progressbar\": true,\n"
            + "    \"time\": 20,\n"
            + "    \"topText_rectwidth\": \"靠近一点\",\n"
            + "    \"retry\": 3,\n"
            + "    \"imageIndex\": 1,\n"
            + "    \"topText_blur\": \"再清晰一点\",\n"
            + "    \"topText_quality\": \"请调整姿势\"\n"
            + "  },\n"
            + "  \"ui\": \"992\",\n"
            + "  \"navi\": {\n"
            + "    \"enable\": false,\n"
            + "    \"url\": \"https://render.alipay.com/p/f/fd-j8l9yjja/index.html\"\n"
            + "  },\n"
            + "  \"upload\": {\n"
            + "    \"mode\": \"2.0\",\n"
            + "    \"desiredWidth\": 480,\n"
            + "    \"minquality\": 9,\n"
            + "    \"upload_compress_rate\": 0.8,\n"
            + "    \"collection\": [\n"
            + "      \"Pano\",\n"
            + "      \"Dark\"\n"
            + "    ],\n"
            + "    \"log_classifier\": \"1\"\n"
            + "  },\n"
            + "  \"noCamPermissionWords\": \"开通后才可以使用刷脸功能，进入免密时代\",\n"
            + "  \"sceneEnv\": {\n"
            + "    \"sceneCode\": \"ZolozID+ZolozTest+certify+face\",\n"
            + "    \"sceneType\": \"normal\"\n"
            + "  },\n"
            + "  \"sdkVersion\": \"1.0\",\n"
            + "  \"env\": 1,\n"
            + "  \"faceTips\": {\n"
            + "    \"systemErrorAlert\": {\n"
            + "      \"returnCode\": 205,\n"
            + "      \"rightButtonText\": \"我知道了\",\n"
            + "      \"title\": \"系统错误\"\n"
            + "    },\n"
            + "    \"systemVersionErrorAlert\": {\n"
            + "      \"returnCode\": 101,\n"
            + "      \"rightButtonText\": \"我知道了\",\n"
            + "      \"message\": \"刷脸仅在Android4.3及以上系统可用\",\n"
            + "      \"title\": \"当前系统不支持刷脸\"\n"
            + "    },\n"
            + "    \"limitAlert\": {\n"
            + "      \"returnCode\": 209,\n"
            + "      \"rightButtonText\": \"我知道了\",\n"
            + "      \"title\": \"本次操作失败\"\n"
            + "    },\n"
            + "    \"failAlert\": {\n"
            + "      \"returnCode\": 202,\n"
            + "      \"leftButtonText\": \"退出\",\n"
            + "      \"rightButtonText\": \"再试一次\",\n"
            + "      \"message\": \"提示:正对手机,更容易成功\",\n"
            + "      \"title\": \"刷脸失败\"\n"
            + "    },\n"
            + "    \"unsurpportAlert\": {\n"
            + "      \"returnCode\": 101,\n"
            + "      \"rightButtonText\": \"我知道了\",\n"
            + "      \"title\": \"当前设备不支持刷脸\"\n"
            + "    },\n"
            + "    \"cameraNoPermissionAlert\": {\n"
            + "      \"returnCode\": 100,\n"
            + "      \"leftButtonText\": \"退出\",\n"
            + "      \"rightButtonText\": \"立即开启\",\n"
            + "      \"message\": \"请查看当前应用是否有访问相机的权限，或返回重试\",\n"
            + "      \"title\": \"无法启动相机\"\n"
            + "    },\n"
            + "    \"networkErrorAlert\": {\n"
            + "      \"returnCode\": 207,\n"
            + "      \"leftButtonText\": \"退出\",\n"
            + "      \"rightButtonText\": \"再试一次\",\n"
            + "      \"title\": \"网络不给力\"\n"
            + "    },\n"
            + "    \"exitAlert\": {\n"
            + "      \"returnCode\": 202,\n"
            + "      \"leftButtonText\": \"取消\",\n"
            + "      \"rightButtonText\": \"确定\",\n"
            + "      \"message\": \"露个脸就能通过\",\n"
            + "      \"title\": \"确定退出吗?\"\n"
            + "    },\n"
            + "    \"interruptAlert\": {\n"
            + "      \"returnCode\": 202,\n"
            + "      \"leftButtonText\": \"退出\",\n"
            + "      \"rightButtonText\": \"再试一次\",\n"
            + "      \"title\": \"验证中断\"\n"
            + "    },\n"
            + "    \"timeoutAlert\": {\n"
            + "      \"returnCode\": 203,\n"
            + "      \"leftButtonText\": \"退出\",\n"
            + "      \"rightButtonText\": \"再试一次\",\n"
            + "      \"message\": \"提示:正对手机,更容易成功\",\n"
            + "      \"title\": \"操作超时\"\n"
            + "    }\n"
            + "  },\n"
            + "  \"algorithm\": {\n"
            + "    \"pose_distanceMax\": 6000,\n"
            + "    \"eyeHwratio\": 0.16,\n"
            + "    \"stack_time\": 2,\n"
            + "    \"threshold\": {\n"
            + "      \"DragonflyLiveness\": [\n"
            + "        0.7,\n"
            + "        0.9\n"
            + "      ],\n"
            + "      \"GeminiLiveness\": [\n"
            + "        0.25,\n"
            + "        0.9\n"
            + "      ]\n"
            + "    },\n"
            + "    \"blink\": 0,\n"
            + "    \"pose_pitch\": 0.2,\n"
            + "    \"yunqiQuality\": 4,\n"
            + "    \"pose_yaw\": 0.2,\n"
            + "    \"pose_gaussian\": 0.15,\n"
            + "    \"liveness_combinations\": \"DragonflyLiveness#GeminiLiveness\",\n"
            + "    \"minpose\": 0,\n"
            + "    \"pose_motion\": 0.2,\n"
            + "    \"pitchWeight\": 4,\n"
            + "    \"pose_pitchMin\": -0.2,\n"
            + "    \"pose_rectwidth\": 0.25,\n"
            + "    \"pose_integrity\": 0.9,\n"
            + "    \"disWeight\": 4,\n"
            + "    \"pose_light\": 0.16,\n"
            + "    \"yawWeight\": 1,\n"
            + "    \"pose_yawMin\": -0.17,\n"
            + "    \"log_level\": 0,\n"
            + "    \"pose_distanceMin\": 5000,\n"
            + "    \"differ\": 0.15,\n"
            + "    \"liveness_combination\": [\n"
            + "      \"DragonflyLiveness\",\n"
            + "      \"GeminiLiveness\"\n"
            + "    ],\n"
            + "    \"mouth\": 0,\n"
            + "    \"max_iod\": 0.4,\n"
            + "    \"facesize\": 50,\n"
            + "    \"stack_size\": 2,\n"
            + "    \"quality_min_quality\": 20,\n"
            + "    \"min_iod\": 0.18\n"
            + "  }\n"
            + "}");
    }

    /**
     * 从androidCfg字符串中提取ui的值
     *
     * @param androidCfg    渲染参数
     * @return              ui值
     */
    protected static int getUi(String androidCfg) {
        int ui = 0;
        try {
            Pattern pattern = compile("(?<=\\\"ui\\\":\\s?\"?)(\\d+)(?=\"?,)");
            Matcher matcher = pattern.matcher(androidCfg);
            if (matcher.find()) {
                String uiStr = matcher.group();
                System.out.print("uiStr=" + uiStr);

                ui = Integer.parseInt(uiStr);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.print("ui=" + ui);
        }

        return ui;
    }

    public static List<String> getTeacherList(String managers){
        List<String> ls = new ArrayList<String>();
        Pattern pattern = compile("(?<=\\()(.+?)(?=\\))");
        Matcher matcher = pattern.matcher(managers);
        while (matcher.find()) {
            ls.add(matcher.group());
        }
        return ls;
    }
}
