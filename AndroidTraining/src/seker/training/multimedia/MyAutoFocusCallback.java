package seker.training.multimedia;

import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;

public class MyAutoFocusCallback implements AutoFocusCallback {
    private Camera mCamera;
    public MyAutoFocusCallback(Camera camera) {
        mCamera = camera;
    }
    @Override
    public void onAutoFocus(boolean success, Camera camera) {
        mCamera.autoFocus(this);
    }
}
