package cn.jiguang.ah;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import cn.jpush.android.service.AlarmReceiver;

/* loaded from: classes.dex */
public final class g {
    private static volatile g a;
    private static final Object b = new Object();
    private Context c;
    private cn.jiguang.ao.a d = new h(this);
    private long e;

    private g() {
    }

    public static g a() {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    a = new g();
                }
            }
        }
        return a;
    }

    private void a(Context context) {
        this.c = context;
        cn.jiguang.ao.b.a().a(8000, cn.jiguang.sdk.impl.a.b * 1000, this.d);
    }

    private void b(Context context) {
        this.e = SystemClock.elapsedRealtime();
        if (!((Boolean) cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.a())).booleanValue()) {
            a.a(context);
            return;
        }
        try {
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmReceiver.class), 0));
        } catch (Throwable unused) {
            cn.jiguang.ai.a.g("AlarmHelper", "Cancel heartbeat alarm failed.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context) {
        cn.jiguang.ai.a.c("PeriodWorker", "periodTask...");
        b(context);
        cn.jiguang.sdk.impl.b.a(context, false, 0L);
        b.a().a(context, 19, 0, "periodTask");
        d.a(context, "periodtask", null);
    }

    public final void a(Context context, boolean z) {
        cn.jiguang.ai.a.e("PeriodWorker", "PeriodWorker resume");
        if (this.e > 0 && SystemClock.elapsedRealtime() > this.e + ((cn.jiguang.sdk.impl.a.b + 5) * 1000)) {
            cn.jiguang.ai.a.e("PeriodWorker", "schedule time is expired, execute now");
            a(context);
            c(context);
        } else if (!z) {
            cn.jiguang.ai.a.c("PeriodWorker", "need not change period task");
        } else {
            a(context);
            b(context);
        }
    }
}
