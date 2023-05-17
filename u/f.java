package cn.jiguang.u;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes.dex */
final class f {
    private static final Object e = new Object();
    private static f h;
    protected Handler a;
    private Context b;
    private LocationManager c;
    private cn.jiguang.v.b d;
    private boolean f;
    private String g;
    private LocationListener i = new g(this);

    private f(Context context) {
        this.b = context;
        this.c = (LocationManager) context.getSystemService("location");
    }

    public static f a(Context context) {
        if (h == null) {
            synchronized (e) {
                if (h == null) {
                    h = new f(context);
                }
            }
        }
        return h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Location location) {
        if (location == null) {
            this.d = null;
            return;
        }
        cn.jiguang.ai.a.c("JLocationGps", "updateGpsInfo location time:" + location.getTime());
        if (this.d == null) {
            this.d = new cn.jiguang.v.b();
        }
        this.d.a = cn.jiguang.sdk.impl.b.a(this.b, location.getTime());
        this.d.b = location.getProvider();
        this.d.c = location.getLatitude();
        this.d.d = location.getLongitude();
        this.d.f = location.getBearing();
        this.d.g = location.getAccuracy();
    }

    private boolean a(Location location, Location location2) {
        if (location == null) {
            return false;
        }
        if (location2 == null) {
            return true;
        }
        long time = location.getTime() - location2.getTime();
        boolean z = time > 120000;
        boolean z2 = time < -120000;
        boolean z3 = time > 0;
        if (z) {
            return true;
        }
        if (z2) {
            return false;
        }
        int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
        boolean z4 = accuracy > 0;
        boolean z5 = accuracy < 0;
        boolean z6 = accuracy > 200;
        String provider = location.getProvider();
        String provider2 = location2.getProvider();
        boolean equals = provider == null ? provider2 == null : provider.equals(provider2);
        if (z5) {
            return true;
        }
        if (!z3 || z4) {
            return z3 && !z6 && equals;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(f fVar) {
        cn.jiguang.ai.a.c("JLocationGps", "gps will done");
        fVar.f = true;
        fVar.f();
        Handler handler = fVar.a;
        if (handler == null) {
            cn.jiguang.ai.a.h("JLocationGps", "cellLocationManager is null,please check it");
            return;
        }
        if (handler.hasMessages(PointerIconCompat.TYPE_WAIT)) {
            fVar.a.removeMessages(PointerIconCompat.TYPE_WAIT);
        }
        if (fVar.a.hasMessages(PointerIconCompat.TYPE_HELP)) {
            fVar.a.removeMessages(PointerIconCompat.TYPE_HELP);
        }
        if (fVar.a.hasMessages(PointerIconCompat.TYPE_CONTEXT_MENU)) {
            fVar.a.removeMessages(PointerIconCompat.TYPE_CONTEXT_MENU);
        }
        if (fVar.a.hasMessages(1005)) {
            fVar.a.removeMessages(1005);
        }
        try {
            fVar.a.getLooper().quit();
        } finally {
            try {
            } finally {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        try {
            LocationListener locationListener = this.i;
            if (locationListener == null) {
                cn.jiguang.ai.a.g("JLocationGps", "Location listener is null , do nothing!");
                return;
            }
            LocationManager locationManager = this.c;
            if (locationManager != null) {
                locationManager.removeUpdates(locationListener);
            } else {
                cn.jiguang.ai.a.g("JLocationGps", "locationManager is null , do nothing!");
            }
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("JLocationGps", "remove location listener failed  e:" + th.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final cn.jiguang.v.b a() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean b() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c() {
        this.f = false;
        this.d = null;
        if (this.c == null) {
            cn.jiguang.ai.a.g("JLocationGps", "get locationManager failed");
            this.f = true;
            return;
        }
        try {
            if (this.a == null) {
                HandlerThread handlerThread = new HandlerThread("location");
                handlerThread.start();
                this.a = new h(this, handlerThread.getLooper());
            }
        } catch (Throwable th) {
            cn.jiguang.ai.a.i("JLocationGps", "start load loc-info failed - error:" + th);
        }
        if (this.a == null) {
            cn.jiguang.ai.a.g("JLocationGps", " mAsyncHandler is empty");
            this.f = true;
        } else if (this.c.isProviderEnabled("network")) {
            this.g = "network";
            this.a.sendEmptyMessage(PointerIconCompat.TYPE_HELP);
        } else if (this.c.isProviderEnabled("gps")) {
            this.g = "gps";
            this.a.sendEmptyMessage(PointerIconCompat.TYPE_HELP);
        } else {
            this.g = "network";
            this.a.sendEmptyMessage(PointerIconCompat.TYPE_WAIT);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0032 A[Catch: all -> 0x0069, TryCatch #0 {all -> 0x0069, blocks: (B:3:0x0002, B:5:0x0020, B:13:0x0032, B:15:0x0041, B:8:0x0027), top: B:21:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final cn.jiguang.v.b d() {
        /*
            r7 = this;
            java.lang.String r0 = "JLocationGps"
            android.location.LocationManager r1 = r7.c     // Catch: java.lang.Throwable -> L69
            java.lang.String r2 = "gps"
            android.location.Location r1 = r1.getLastKnownLocation(r2)     // Catch: java.lang.Throwable -> L69
            android.location.LocationManager r2 = r7.c     // Catch: java.lang.Throwable -> L69
            java.lang.String r3 = "network"
            android.location.Location r2 = r2.getLastKnownLocation(r3)     // Catch: java.lang.Throwable -> L69
            android.location.LocationManager r3 = r7.c     // Catch: java.lang.Throwable -> L69
            java.lang.String r4 = "passive"
            android.location.Location r3 = r3.getLastKnownLocation(r4)     // Catch: java.lang.Throwable -> L69
            boolean r4 = r7.a(r1, r2)     // Catch: java.lang.Throwable -> L69
            if (r4 == 0) goto L27
            boolean r2 = r7.a(r1, r3)     // Catch: java.lang.Throwable -> L69
            if (r2 == 0) goto L2f
            goto L30
        L27:
            boolean r1 = r7.a(r2, r3)     // Catch: java.lang.Throwable -> L69
            if (r1 == 0) goto L2f
            r1 = r2
            goto L30
        L2f:
            r1 = r3
        L30:
            if (r1 == 0) goto L80
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L69
            long r4 = r1.getTime()     // Catch: java.lang.Throwable -> L69
            long r2 = r2 - r4
            r4 = 30000(0x7530, double:1.4822E-319)
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L80
            r7.a(r1)     // Catch: java.lang.Throwable -> L69
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L69
            java.lang.String r2 = "bestLocation:"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L69
            cn.jiguang.v.b r2 = r7.d     // Catch: java.lang.Throwable -> L69
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L69
            java.lang.String r2 = ",curTime:"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L69
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L69
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L69
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L69
            cn.jiguang.ai.a.c(r0, r1)     // Catch: java.lang.Throwable -> L69
            cn.jiguang.v.b r0 = r7.d     // Catch: java.lang.Throwable -> L69
            return r0
        L69:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "loadLastGpsInfo failed: "
            r2.<init>(r3)
            java.lang.String r1 = r1.getMessage()
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            cn.jiguang.ai.a.g(r0, r1)
        L80:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.u.f.d():cn.jiguang.v.b");
    }

    public final boolean e() {
        String str;
        try {
            LocationManager locationManager = this.c;
            if (locationManager != null) {
                if (locationManager.isProviderEnabled("gps") || this.c.isProviderEnabled("network")) {
                    return true;
                }
                return this.c.isProviderEnabled("passive");
            }
            return false;
        } catch (IllegalArgumentException unused) {
            str = "The provider [gps] is illegal argument!";
            cn.jiguang.ai.a.g("JLocationGps", str);
            return false;
        } catch (SecurityException unused2) {
            str = "No suitable permission is present when get GPS_PROVIDER!";
            cn.jiguang.ai.a.g("JLocationGps", str);
            return false;
        } catch (Exception unused3) {
            str = "The ILocationManager is null!";
            cn.jiguang.ai.a.g("JLocationGps", str);
            return false;
        }
    }
}
