package seker.training.ui.dialog;

import android.app.AlertDialog;
import android.os.Bundle;
import seker.common.BaseActivity;

public class AlertDialogTestActivity extends BaseActivity {

    private AlertDialog dialog;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        showAlertDialog();
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
        //builder.setMessage("系统定位当前缴费城市为杭州");
        //builder.setTitle("系统定位当前缴费城市为杭州");
        builder.setNegativeButton("退出", null);
        builder.setPositiveButton("确定", null);
        
        dialog = builder.create();
        dialog.setMessage("系统定位当前缴费城市为杭州");
        dialog.show();
    };
    
    @Override
    protected void onResume() {
        super.onResume();
        
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        });
    }
    
    @Override
    protected void onDestroy() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog != null && dialog.isShowing() && !isFinishing()){
                    dialog.dismiss();
                    dialog = null;
                }
            }
        });
        super.onDestroy();
    }
}
