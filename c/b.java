package cn.jiguang.c;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.ad.k;
import cn.jiguang.as.j;
import com.facebook.gamingservices.cloudgaming.internal.SDKAnalyticsEvents;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b {
    public static boolean a = false;
    public static boolean b = false;
    private static volatile b c;
    private ExecutorService d = Executors.newSingleThreadExecutor();
    private String e = null;
    private String f = null;
    private long g = 30;
    private long h = 0;
    private long i = 0;
    private boolean j = true;
    private boolean k = false;
    private boolean l = true;
    private long m = 0;
    private JSONObject n = null;
    private final Object o = new Object();

    private b() {
    }

    public static b a() {
        if (c == null) {
            synchronized (b.class) {
                c = new b();
            }
        }
        return c;
    }

    private JSONObject a(Context context, long j) {
        StringBuilder sb = new StringBuilder();
        String b2 = cn.jiguang.ab.f.b(context);
        if (!TextUtils.isEmpty(b2)) {
            sb.append(b2);
        }
        sb.append(j);
        this.f = j.c(sb.toString());
        cn.jiguang.e.b.a(context, cn.jiguang.e.a.n().a((cn.jiguang.e.a<Long>) Long.valueOf(this.h)), cn.jiguang.e.a.q().a((cn.jiguang.e.a<String>) this.f));
        JSONObject jSONObject = new JSONObject();
        try {
            a(jSONObject);
            k.a(context, jSONObject, "active_launch");
            jSONObject.put(SDKAnalyticsEvents.PARAMETER_SESSION_ID, this.f);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0077, code lost:
        if ((r10.h - r10.i) <= (r10.g * 1000)) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0069, code lost:
        if ((r10.h - r5) <= (r10.g * 1000)) goto L7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void a(cn.jiguang.c.b r10, android.content.Context r11) {
        /*
            boolean r0 = r10.j
            r1 = 0
            r2 = 1000(0x3e8, double:4.94E-321)
            r4 = 1
            if (r0 == 0) goto L6c
            r10.j = r1
            java.lang.String r0 = "PushSA"
            java.lang.String r5 = "statistics start"
            cn.jiguang.ac.d.b(r0, r5)
            cn.jiguang.e.a r0 = cn.jiguang.e.a.p()
            java.lang.Object r0 = cn.jiguang.e.b.a(r11, r0)
            java.lang.Long r0 = (java.lang.Long) r0
            long r5 = r0.longValue()
            java.lang.String r0 = "PushSA"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "lastPause:"
            r7.<init>(r8)
            java.lang.StringBuilder r7 = r7.append(r5)
            java.lang.String r8 = ",latestResumeTime:"
            java.lang.StringBuilder r7 = r7.append(r8)
            long r8 = r10.h
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = ",interval:"
            java.lang.StringBuilder r7 = r7.append(r8)
            long r8 = r10.g
            long r8 = r8 * r2
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = ",a:"
            java.lang.StringBuilder r7 = r7.append(r8)
            long r8 = r10.h
            long r8 = r8 - r5
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            cn.jiguang.ac.d.b(r0, r7)
            r7 = 0
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 <= 0) goto L7a
            long r7 = r10.h
            long r7 = r7 - r5
            long r5 = r10.g
            long r5 = r5 * r2
            int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r0 > 0) goto L7a
            goto L7b
        L6c:
            long r5 = r10.h
            long r7 = r10.i
            long r5 = r5 - r7
            long r7 = r10.g
            long r7 = r7 * r2
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 > 0) goto L7a
            goto L7b
        L7a:
            r1 = 1
        L7b:
            if (r1 == 0) goto Lc5
            java.lang.String r0 = "PushSA"
            java.lang.String r1 = "new statistics session"
            cn.jiguang.ac.d.d(r0, r1)
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            long r1 = r10.h
            org.json.JSONObject r1 = r10.a(r11, r1)
            if (r1 == 0) goto L94
            r0.put(r1)
        L94:
            java.lang.Object r1 = r10.o
            monitor-enter(r1)
            org.json.JSONObject r2 = r10.d(r11)     // Catch: java.lang.Throwable -> Lc2
            if (r2 == 0) goto Lb0
            int r3 = r2.length()     // Catch: java.lang.Throwable -> Lc2
            if (r3 <= 0) goto Lb0
            java.lang.String r3 = "active_terminate"
            cn.jiguang.ad.k.a(r11, r2, r3)     // Catch: java.lang.Exception -> La8 java.lang.Throwable -> Lc2
        La8:
            java.lang.String r3 = "push_stat_cache.json"
            r4 = 0
            cn.jiguang.ad.k.a(r11, r3, r4)     // Catch: java.lang.Throwable -> Lc2
            r10.n = r4     // Catch: java.lang.Throwable -> Lc2
        Lb0:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Lc2
            if (r2 == 0) goto Lbc
            int r10 = r2.length()
            if (r10 <= 0) goto Lbc
            r0.put(r2)
        Lbc:
            java.lang.String r10 = cn.jiguang.a.a.d
            cn.jiguang.ad.k.a(r11, r10, r0)
            return
        Lc2:
            r10 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Lc2
            throw r10
        Lc5:
            cn.jiguang.e.a r0 = cn.jiguang.e.a.q()
            java.lang.Object r11 = cn.jiguang.e.b.b(r11, r0)
            java.lang.String r11 = (java.lang.String) r11
            r10.f = r11
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.c.b.a(cn.jiguang.c.b, android.content.Context):void");
    }

    private static void a(JSONObject jSONObject) {
        String a2 = cn.jiguang.as.b.a();
        String str = a2.split("_")[0];
        String str2 = a2.split("_")[1];
        jSONObject.put("date", str);
        jSONObject.put("time", str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(b bVar, Context context) {
        long j;
        if (context != null) {
            synchronized (bVar.o) {
                cn.jiguang.e.b.a(context, cn.jiguang.e.a.p().a((cn.jiguang.e.a<Long>) Long.valueOf(bVar.i)), cn.jiguang.e.a.o().a((cn.jiguang.e.a<Long>) Long.valueOf(bVar.i)));
                JSONObject d = bVar.d(context);
                if (d == null) {
                    d = new JSONObject();
                }
                try {
                    long longValue = ((Long) cn.jiguang.e.b.a(context, cn.jiguang.e.a.n())).longValue();
                    if (longValue <= 0) {
                        long j2 = bVar.i - bVar.m;
                        j = j2 > 0 ? j2 / 1000 : 10L;
                        cn.jiguang.e.b.a(context, cn.jiguang.e.a.n().a((cn.jiguang.e.a<Long>) Long.valueOf(bVar.m)));
                    } else {
                        j = (bVar.i - longValue) / 1000;
                    }
                    d.put("duration", j);
                    d.put("itime", System.currentTimeMillis() / 1000);
                    d.put(SDKAnalyticsEvents.PARAMETER_SESSION_ID, bVar.f);
                    a(d);
                } catch (Exception unused) {
                }
                bVar.n = d;
                k.a(context, "push_stat_cache.json", d);
            }
        }
    }

    private boolean c(Context context, String str) {
        if (!this.l) {
            cn.jiguang.ac.d.d("PushSA", "stat function has been disabled");
            return false;
        } else if (context == null) {
            cn.jiguang.ac.d.d("PushSA", "context is null");
            return false;
        } else if (context instanceof Application) {
            cn.jiguang.ac.d.i("PushSA", "Context should be an Activity on this method : " + str);
            return false;
        } else {
            return true;
        }
    }

    private JSONObject d(Context context) {
        if (this.n == null) {
            this.n = k.a(context, "push_stat_cache.json");
        }
        return this.n;
    }

    public final void a(long j) {
        this.g = j;
    }

    public final void a(Context context) {
        try {
            if (this.e == null || !this.k) {
                return;
            }
            this.i = System.currentTimeMillis();
            this.d.execute(new e(this, context.getApplicationContext()));
        } catch (Throwable unused) {
        }
    }

    public final void a(Context context, String str) {
        if (this.k) {
            cn.jiguang.ac.d.b("PushSA", "JCoreInterface.onResume() must be called after called JCoreInterface.onPause() in last Activity or Fragment");
            return;
        }
        this.k = true;
        this.e = str;
        this.h = System.currentTimeMillis();
        try {
            this.d.execute(new c(this, context.getApplicationContext()));
        } catch (Throwable unused) {
        }
    }

    public final void a(boolean z) {
        this.l = z;
    }

    public final void b(Context context) {
        if (c(context, "onResume")) {
            a = true;
            try {
                this.k = false;
            } catch (ClassCastException | Exception unused) {
            }
            if (this.k) {
                return;
            }
            this.k = true;
            this.h = System.currentTimeMillis();
            this.e = context.getClass().getName();
            try {
                this.d.execute(new f(true, context.getApplicationContext(), this));
            } catch (Throwable unused2) {
            }
        }
    }

    public final void b(Context context, String str) {
        if (!this.k) {
            cn.jiguang.ac.d.b("PushSA", "JCoreInterface.onPause() must be called after called JCoreInterface.onResume() in this Activity or Fragment");
            return;
        }
        this.k = false;
        String str2 = this.e;
        if (str2 == null || !str2.equals(str)) {
            cn.jiguang.ac.d.i("PushSA", "page name didn't match the last one passed by onResume");
            return;
        }
        this.i = System.currentTimeMillis();
        try {
            this.d.execute(new d(this, context.getApplicationContext()));
        } catch (Throwable unused) {
        }
    }

    public final void c(Context context) {
        if (c(context, "onPause")) {
            b = true;
            try {
                this.k = true;
            } catch (ClassCastException unused) {
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (this.k) {
                this.k = false;
                String str = this.e;
                if (str == null || !str.equals(context.getClass().getName())) {
                    cn.jiguang.ac.d.d("PushSA", "the activity pass by onPause didn't match last one passed by onResume");
                    return;
                }
                this.i = System.currentTimeMillis();
                this.m = this.h;
                try {
                    this.d.execute(new f(false, context.getApplicationContext(), this));
                } catch (Throwable unused2) {
                }
            }
        }
    }
}
