package cn.jiguang.ap;

import android.content.Context;
import android.os.Build;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public final class c {
    private static volatile c s;
    private static final Object t = new Object();
    private static String u;
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public int g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    private transient AtomicBoolean r;

    private c(Context context) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.r = atomicBoolean;
        if (atomicBoolean.get() || context == null) {
            return;
        }
        this.b = a(Build.VERSION.RELEASE) + "," + Build.VERSION.SDK_INT;
        this.c = a(Build.MODEL);
        this.d = a.a(context, "gsm.version.baseband", "baseband");
        this.e = a(Build.DEVICE);
        this.k = a(Build.PRODUCT);
        this.l = a(Build.MANUFACTURER);
        this.m = a(Build.FINGERPRINT);
        this.n = a(Build.BRAND);
        this.a = b(context);
        this.o = cn.jiguang.sdk.impl.b.i(context);
        this.f = cn.jiguang.sdk.impl.b.d(context);
        this.g = a.d(context) ? 1 : 0;
        this.h = a.e(context);
        this.i = a.f(context);
        this.j = cn.jiguang.sdk.impl.b.a(context, false, "won't get serial") ? " " : Build.SERIAL;
        this.p = a.c(context, "");
        Object a = cn.jiguang.ah.d.a(context, "get_imei", null);
        if (a instanceof String) {
            this.q = (String) a;
        }
        this.r.set(true);
    }

    public static c a(Context context) {
        if (s == null) {
            synchronized (t) {
                if (s == null) {
                    s = new c(context);
                }
            }
        }
        return s;
    }

    private static String a(String str) {
        if (str != null) {
            return str.trim();
        }
        return null;
    }

    private static String b(Context context) {
        if (u == null) {
            try {
                String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                if (str.length() > 30) {
                    str = str.substring(0, 30);
                }
                u = str;
            } catch (Throwable unused) {
                cn.jiguang.ai.a.d("DeviceInfo", "NO versionName defined in manifest.");
            }
        }
        String str2 = u;
        return str2 == null ? "" : str2;
    }
}
