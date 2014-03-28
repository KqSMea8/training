package seker.training.memory;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

public class MemoryTest implements Runnable {

    private static final int MB = 1048576;

    private final static String TAG = "seker";

    private final Context mContext;

    public MemoryTest(Context context) {
        mContext = context;
    }

    @SuppressLint("NewApi")
    @Override
    public void run() {
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        StringBuilder buf = new StringBuilder();
        buf.append("mi.availMem=").append(mi.availMem / MB).append(", ");
        buf.append("mi.threshold=").append(mi.threshold / MB).append(", ");
        buf.append("mi.totalMem=").append(mi.totalMem / MB).append(", ");
        buf.append("mi.lowMemory=").append(mi.lowMemory);
        Log.d(TAG, "MemoryInfo=" + buf.toString());

        buf.delete(0, buf.length());
        buf.append("TotalMemory=" + TotalMemory()).append(", ");
        buf.append("FreeMemory=" + FreeMemory()).append(", ");
        buf.append("BusyMemory=" + BusyMemory());
        Log.d(TAG, "RootDirectory= " + buf.toString());
        
        buf.delete(0, buf.length());
        buf.append("TotalMemory=" + DeviceMemory.getInternalStorageSpace()).append(", ");
        buf.append("FreeMemory=" + DeviceMemory.getInternalFreeSpace()).append(", ");
        buf.append("BusyMemory=" + DeviceMemory.getInternalUsedSpace());
        Log.d(TAG, "DeviceMemory= " + buf.toString());
    }

    public long TotalMemory() {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        long Total = (statFs.getBlockCount() * statFs.getBlockSize()) / MB;
        return Total;
    }

    public long FreeMemory() {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        long Free = (statFs.getAvailableBlocks() * statFs.getBlockSize()) / MB;
        return Free;
    }

    public long BusyMemory() {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        long Total = (statFs.getBlockCount() * statFs.getBlockSize()) / MB;
        long Free = (statFs.getAvailableBlocks() * statFs.getBlockSize()) / MB;
        long Busy = Total - Free;
        return Busy;
    }
}
