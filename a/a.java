package cn.jiguang.a;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jiguang.ac.d;
import cn.jiguang.api.JAnalyticsAction;
import cn.jiguang.as.g;
import cn.jpush.android.service.DataShare;
import cn.jpush.android.service.JCommonService;
import cn.jpush.android.service.PushReceiver;

/* loaded from: classes.dex */
public final class a {
    public static Context a = null;
    public static JAnalyticsAction b = null;
    public static boolean c = false;
    public static String d = "JCore";
    public static boolean e = false;
    public static boolean f = false;
    private static Boolean i;
    private static Boolean j;
    private static ServiceConnection k = new b();
    public static boolean g = true;
    private static boolean l = false;
    public static String h = "";
    private static boolean m = false;

    public static Context a(Context context) {
        if (a == null && context != null) {
            a = context.getApplicationContext();
        }
        return a;
    }

    public static void a(Context context, String str, Bundle bundle) {
        cn.jiguang.ar.a.a("SDK_INIT", new c(context, false, str, bundle));
    }

    public static void a(Context context, boolean z, long j2) {
        try {
            Bundle bundle = new Bundle();
            bundle.putBoolean("force", z);
            bundle.putLong("delay_time", j2);
            a(context, "tcp_a2", bundle);
        } catch (Throwable th) {
            d.g("JCoreGobal", "sendHeartBeat error:" + th);
        }
    }

    public static boolean a() {
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0029, code lost:
        if (r3.getApplicationInfo().targetSdkVersion <= 28) goto L7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(android.content.Context r3, boolean r4, java.lang.String r5) {
        /*
            r0 = 1
            r1 = 0
            r2 = 28
            if (r4 != 0) goto L16
            boolean r3 = cn.jiguang.a.a.m
            if (r3 == 0) goto Lf
            int r3 = android.os.Build.VERSION.SDK_INT
            if (r3 < r2) goto L14
            goto L2b
        Lf:
            int r3 = android.os.Build.VERSION.SDK_INT
            if (r3 <= r2) goto L14
            goto L2b
        L14:
            r0 = 0
            goto L2b
        L16:
            boolean r4 = cn.jiguang.a.a.m
            if (r4 == 0) goto L1f
            int r4 = android.os.Build.VERSION.SDK_INT
            if (r4 < r2) goto L14
            goto L23
        L1f:
            int r4 = android.os.Build.VERSION.SDK_INT
            if (r4 <= r2) goto L14
        L23:
            android.content.pm.ApplicationInfo r3 = r3.getApplicationInfo()
            int r3 = r3.targetSdkVersion
            if (r3 <= r2) goto L14
        L2b:
            if (r0 == 0) goto L41
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "is Android Q, msg: "
            r3.<init>(r4)
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "JCoreGobal"
            cn.jiguang.ac.d.b(r4, r3)
        L41:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.a.a.a(android.content.Context, boolean, java.lang.String):boolean");
    }

    public static void b() {
        d.b("JCoreGobal", "call testAndroidQ");
        m = true;
    }

    public static void b(Context context, String str, Bundle bundle) {
        cn.jiguang.ar.a.a("SDK_SERVICE_INIT", new c(context, true, str, bundle));
    }

    public static synchronized boolean b(Context context) {
        synchronized (a.class) {
            Boolean bool = i;
            if (bool != null) {
                return bool.booleanValue();
            } else if (context == null) {
                d.i("JCoreGobal", "init failed,context is null");
                return false;
            } else {
                d.e("JCoreGobal", "action:init jcore,version:2.1.2,build id:40");
                d.b("JCoreGobal", "build type:release");
                a = context.getApplicationContext();
                Context applicationContext = context.getApplicationContext();
                cn.jiguang.ab.c.a();
                String b2 = cn.jiguang.ab.d.b(applicationContext);
                if ((cn.jiguang.ab.c.a().c() || cn.jiguang.ab.c.a().b()) && TextUtils.isEmpty(b2)) {
                    i = false;
                    d.i("JCoreGobal", "AndroidManifest.xml missing required service:" + JCommonService.class.getCanonicalName() + ",please custom one service and extends JCommonService");
                    return false;
                }
                cn.jiguang.b.a.a().b();
                String b3 = cn.jiguang.ab.d.b(applicationContext);
                if (TextUtils.isEmpty(b3)) {
                    d.d("JCoreGobal", "not found commonServiceClass（JCommonService）");
                } else if (DataShare.isBinding()) {
                    d.b("JCoreGobal", "is binding service");
                } else {
                    try {
                        Intent intent = new Intent();
                        intent.setClass(applicationContext, Class.forName(b3));
                        if (applicationContext.bindService(intent, k, 1)) {
                            d.a("JCoreGobal", "Remote Service on binding...");
                            DataShare.setBinding();
                        } else {
                            d.a("JCoreGobal", "Remote Service bind failed");
                        }
                    } catch (SecurityException unused) {
                        d.g("JCoreGobal", "Remote Service bind failed caused by SecurityException!");
                    }
                }
                Boolean bool2 = true;
                i = bool2;
                return bool2.booleanValue();
            }
        }
    }

    public static synchronized boolean c(Context context) {
        synchronized (a.class) {
            Boolean bool = j;
            if (bool != null) {
                return bool.booleanValue();
            } else if (context == null) {
                d.i("JCoreGobal", "init failed,context is null");
                return false;
            } else {
                d.b("JCoreGobal", "serviceInit...");
                a = context.getApplicationContext();
                Context applicationContext = context.getApplicationContext();
                if (!cn.jiguang.ab.b.d(applicationContext)) {
                    Boolean bool2 = false;
                    j = bool2;
                    return bool2.booleanValue();
                }
                cn.jiguang.ab.c.a();
                IntentFilter intentFilter = new IntentFilter();
                PushReceiver pushReceiver = new PushReceiver();
                intentFilter.addAction("android.intent.action.USER_PRESENT");
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                applicationContext.registerReceiver(pushReceiver, intentFilter);
                if (!g.a(applicationContext, PushReceiver.class)) {
                    IntentFilter intentFilter2 = new IntentFilter();
                    intentFilter2.addAction("android.intent.action.PACKAGE_ADDED");
                    intentFilter2.addAction("android.intent.action.PACKAGE_REMOVED");
                    intentFilter2.addDataScheme("package");
                    applicationContext.registerReceiver(pushReceiver, intentFilter2);
                }
                j = true;
                cn.jiguang.b.a.a().b(applicationContext);
                cn.jiguang.ah.d.a(applicationContext, "service_create", null);
                return j.booleanValue();
            }
        }
    }

    public static synchronized void d(Context context) {
        synchronized (a.class) {
            try {
            } catch (Throwable th) {
                d.g("JCoreGobal", "registerActivityLifecycleCallbacks failed:" + th);
                g = true;
            }
            if (g && Build.VERSION.SDK_INT >= 14 && (context instanceof Application)) {
                String a2 = cn.jiguang.as.a.a(context);
                String packageName = context.getPackageName();
                if (a2 != null && packageName != null && context.getPackageName().equals(a2)) {
                    g = false;
                    ((Application) context).registerActivityLifecycleCallbacks(new cn.jiguang.c.a());
                    d.b("JCoreGobal", "registerActivityLifecycleCallbacks in main process,packageName:" + packageName + ",currentProcessName:" + a2);
                    return;
                }
                d.b("JCoreGobal", "need not registerActivityLifecycleCallbacks in other process :" + a2);
            }
        }
    }
}
