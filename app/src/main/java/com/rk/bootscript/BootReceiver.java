package com.rk.bootscript;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == null) return;
        // 延迟12秒，等系统、网络、root就绪，和你之前脚本sleep 12对应
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            new Thread(() -> {
                try {
                    // root执行我们的启动脚本
                    Process p = Runtime.getRuntime().exec(new String[]{"su", "-c", "sh /data/start_rgb.sh"});
                    p.waitFor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }, 12000);
    }
}
