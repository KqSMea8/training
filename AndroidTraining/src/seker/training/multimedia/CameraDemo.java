package seker.training.multimedia;

import java.io.IOException;

import seker.common.BaseActivity;
import seker.common.utils.LogUtils;
import seker.training.R;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class CameraDemo extends BaseActivity {
    private SurfaceView mSuface;
    private Camera mCamera;
    private SurfaceHolder mHolder;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.camera_demo);
        
        mSuface = (SurfaceView) findViewById(R.id.surface);
        mHolder = mSuface.getHolder();
        mHolder.addCallback(new Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()));
                }
                
                try {
                    mCamera.setPreviewDisplay(mHolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mCamera.startPreview();
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()));
                }
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()));
                }
            }
        });
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        mCamera = Camera.open();
        mCamera.setDisplayOrientation(90);
    }
    
    @Override
    protected void onPause() {
        mCamera.stopPreview();
        mCamera.release();
        super.onPause();
    }
}
