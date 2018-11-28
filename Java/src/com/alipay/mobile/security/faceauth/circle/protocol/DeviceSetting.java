package com.alipay.mobile.security.faceauth.circle.protocol;

/**
 * Created by yueweizyw on 17/3/6.
 */

public class DeviceSetting {

    //if displayAuto is true, the displayAngle will not be enabled.
    boolean displayAuto = true;
    int displayAngle = 90;

    //if cameraAuto is true ,the cameraID will not be enabled
    boolean cameraAuto = true;
    int cameraID = 1;

    //set  algorithm  input frame people pose angle.
    boolean algorithmAuto = true;
    int algorithmAngle = 270;

    int maxApiLevel = 100;

    int minApiLevel = 0;

    public boolean isDisplayAuto() {
        return displayAuto;
    }

    public void setDisplayAuto(boolean displayAuto) {
        this.displayAuto = displayAuto;
    }

    public int getDisplayAngle() {
        return displayAngle;
    }

    public void setDisplayAngle(int displayAngle) {
        this.displayAngle = displayAngle;
    }

    public boolean isCameraAuto() {
        return cameraAuto;
    }

    public void setCameraAuto(boolean cameraAuto) {
        this.cameraAuto = cameraAuto;
    }

    public int getCameraID() {
        return cameraID;
    }

    public void setCameraID(int cameraID) {
        this.cameraID = cameraID;
    }

    public int getAlgorithmAngle() {
        return algorithmAngle;
    }

    public void setAlgorithmAngle(int algorithmAngle) {
        this.algorithmAngle = algorithmAngle;
    }

    public boolean isAlgorithmAuto() {
        return algorithmAuto;
    }

    public void setAlgorithmAuto(boolean algorithmAuto) {
        this.algorithmAuto = algorithmAuto;
    }

    public int getMaxApiLevel() {
        return maxApiLevel;
    }

    public void setMaxApiLevel(int maxApiLevel) {
        this.maxApiLevel = maxApiLevel;
    }

    public int getMinApiLevel() {
        return minApiLevel;
    }

    public void setMinApiLevel(int minApiLevel) {
        this.minApiLevel = minApiLevel;
    }

    @Override
    public String toString() {
        return "DeviceSetting{" +
            "displayAuto=" + displayAuto +
            ", displayAngle=" + displayAngle +
            ", cameraAuto=" + cameraAuto +
            ", cameraID=" + cameraID +
            ", algorithmAuto=" + algorithmAuto +
            ", algorithmAngle=" + algorithmAngle +
            ", maxApiLevel=" + maxApiLevel +
            ", minApiLevel=" + minApiLevel +
            '}';
    }
}
