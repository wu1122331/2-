package cn.jiguang.ad;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jiguang.api.JCoreManager;
import java.util.LinkedHashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class i {
    private static j a;
    private static volatile FutureTask<?> b;
    private static final Object c = new Object();
    private static final LinkedHashSet<String> d;
    private static final LinkedHashSet<String> e;

    static {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        d = linkedHashSet;
        linkedHashSet.add("https://tsis.jpush.cn");
        e = new LinkedHashSet<>();
    }

    public static LinkedHashSet<String> a() {
        cn.jiguang.a.a.a();
        return d;
    }

    public static void a(Context context, boolean z) {
        String str;
        StringBuilder sb;
        if (a == null) {
            a = new j(context, (byte) 0);
        }
        if (b == null || b.isCancelled() || b.isDone()) {
            synchronized (c) {
                if (b == null || b.isCancelled() || b.isDone()) {
                    b = new FutureTask<>(a, null);
                    cn.jiguang.ar.a.a("ASYNC", b);
                }
            }
        }
        if (z) {
            try {
                b.get(10L, TimeUnit.SECONDS);
            } catch (InterruptedException e2) {
                e = e2;
                str = "ReportSis";
                sb = new StringBuilder("sis task e:");
                cn.jiguang.ac.d.f(str, sb.append(e).toString());
            } catch (ExecutionException e3) {
                e = e3;
                str = "ReportSis";
                sb = new StringBuilder("sis task e:");
                cn.jiguang.ac.d.f(str, sb.append(e).toString());
            } catch (TimeoutException e4) {
                e = e4;
                str = "ReportSis";
                sb = new StringBuilder("sis task e:");
                cn.jiguang.ac.d.f(str, sb.append(e).toString());
            } catch (Throwable th) {
                cn.jiguang.ac.d.f("ReportSis", "sis task e:" + th);
            }
        }
    }

    public static boolean b(Context context, String str, String str2) {
        n a2 = b.a(str, str2, context, true, 3, 2);
        cn.jiguang.ac.d.b("ReportSis", "report sis code[" + a2.a() + "] from url=" + str + "\n body=" + a2.b());
        if (a2.a() == 0) {
            String b2 = a2.b();
            if (TextUtils.isEmpty(b2)) {
                return false;
            }
            try {
                a.a().a(context, new JSONObject(b2).getJSONObject("ret"));
                return true;
            } catch (Throwable th) {
                cn.jiguang.ac.d.f("ReportSis", "getUrls e:" + th);
                return false;
            }
        }
        return false;
    }

    public static String c(Context context) {
        long j;
        Object a2;
        double d2;
        double d3;
        JSONObject jSONObject = new JSONObject();
        try {
            String b2 = cn.jiguang.ab.f.b(context);
            long longValue = ((Long) cn.jiguang.e.b.a(context, cn.jiguang.e.a.c())).longValue();
            int a3 = cn.jiguang.ap.i.a(context);
            String b3 = cn.jiguang.ap.i.b(context);
            jSONObject.put("type", a3);
            jSONObject.put("appkey", b2);
            jSONObject.put("sdkver", JCoreManager.SDK_VERSION);
            jSONObject.put("platform", 0);
            j = 0;
            if (longValue != 0) {
                jSONObject.put("uid", longValue);
            }
            if (b3 != null) {
                jSONObject.put("opera", b3);
            }
            a2 = cn.jiguang.ah.d.a(context, "get_loc_info", null);
            d2 = 200.0d;
        } catch (Throwable unused) {
        }
        if (a2 instanceof Bundle) {
            try {
                Bundle bundle = (Bundle) a2;
                double d4 = bundle.getDouble("lat");
                try {
                    d2 = bundle.getDouble("lot");
                    j = bundle.getLong("time");
                } catch (Throwable unused2) {
                }
                double d5 = d2;
                d2 = d4;
                d3 = d5;
            } catch (Throwable unused3) {
            }
            if (d2 > -90.0d && d2 < 90.0d && d3 > -180.0d && d3 < 180.0d) {
                jSONObject.put("lat", d2);
                jSONObject.put("lng", d3);
                jSONObject.put("time", j);
            }
            return jSONObject.toString();
        }
        d3 = 200.0d;
        if (d2 > -90.0d) {
            jSONObject.put("lat", d2);
            jSONObject.put("lng", d3);
            jSONObject.put("time", j);
        }
        return jSONObject.toString();
    }

    public static LinkedHashSet<String> d(Context context) {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        String str = (String) cn.jiguang.e.b.a(context, cn.jiguang.e.a.s());
        if (TextUtils.isEmpty(str)) {
            return linkedHashSet;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                linkedHashSet.add(jSONArray.optString(i));
            }
        } catch (JSONException unused) {
        }
        return linkedHashSet;
    }
}
