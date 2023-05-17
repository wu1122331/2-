package cn.jiguang.ah;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import cn.jpush.android.service.AlarmReceiver;

/* loaded from: classes.dex */
public final class a {
    public static void a(Context context) {
        long j = cn.jiguang.sdk.impl.a.c * 1000;
        long currentTimeMillis = System.currentTimeMillis() + j;
        cn.jiguang.ai.a.e("AlarmHelper", "Reset heartbeat alarm, wait " + j + "ms.");
        try {
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmReceiver.class), 0);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            if (Build.VERSION.SDK_INT >= 19) {
                alarmManager.setWindow(0, currentTimeMillis, 0L, broadcast);
            } else {
                alarmManager.setInexactRepeating(0, currentTimeMillis, j, broadcast);
            }
        } catch (Throwable th) {
            cn.jiguang.ai.a.h("AlarmHelper", "can't trigger alarm cause by exception:" + th);
        }
    }
}
