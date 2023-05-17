package cn.jiguang.u;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a extends cn.jiguang.f.a {
    private static volatile a b;
    private Context a;

    private static Bundle a(long j, double d, double d2) {
        Bundle bundle = new Bundle();
        bundle.putLong("time", j);
        bundle.putDouble("lot", d);
        bundle.putDouble("lat", d2);
        return bundle;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0156 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.json.JSONObject a(java.util.List<cn.jiguang.v.c> r19, cn.jiguang.v.a r20, cn.jiguang.v.b r21) {
        /*
            Method dump skipped, instructions count: 394
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.u.a.a(java.util.List, cn.jiguang.v.a, cn.jiguang.v.b):org.json.JSONObject");
    }

    private JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject jSONObject3;
        JSONArray jSONArray;
        cn.jiguang.ai.a.c("JLocation", "currentJson:" + jSONObject + ",cacheJson:" + jSONObject2);
        if (jSONObject == null) {
            return jSONObject2;
        }
        if (jSONObject2 == null) {
            try {
                jSONObject3 = new JSONObject();
            } catch (JSONException e) {
                e = e;
            }
            try {
                JSONArray jSONArray2 = new JSONArray();
                jSONArray2.put(jSONObject);
                jSONObject3.put("content", jSONArray2);
                return jSONObject3;
            } catch (JSONException e2) {
                e = e2;
                jSONObject2 = jSONObject3;
                cn.jiguang.ai.a.g("JLocation", "mergeJson exception:" + e.getMessage());
                return jSONObject2;
            }
        }
        try {
            jSONArray = jSONObject2.getJSONArray("content");
        } catch (JSONException unused) {
            jSONArray = new JSONArray();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            JSONArray optJSONArray = optJSONObject.optJSONArray("wifi");
            JSONArray optJSONArray2 = optJSONObject.optJSONArray("cell");
            JSONArray optJSONArray3 = optJSONObject.optJSONArray("gps");
            JSONArray optJSONArray4 = jSONObject.optJSONArray("wifi");
            JSONArray optJSONArray5 = jSONObject.optJSONArray("cell");
            JSONArray optJSONArray6 = jSONObject.optJSONArray("gps");
            if (optJSONArray != null && optJSONArray.equals(optJSONArray4)) {
                jSONObject.remove("wifi");
            }
            if (optJSONArray2 != null && optJSONArray2.equals(optJSONArray5)) {
                jSONObject.remove("cell");
            }
            if (optJSONArray3 != null && optJSONArray3.equals(optJSONArray6)) {
                jSONObject.remove("gps");
            }
        }
        if (jSONObject.length() != 0) {
            try {
                jSONObject.put("network_type", cn.jiguang.ap.a.i(this.a));
                jSONObject.put("local_dns", cn.jiguang.ap.a.c());
                cn.jiguang.f.i.a(this.a, jSONObject, "loc_info");
                jSONArray.put(jSONObject);
            } catch (JSONException e3) {
                cn.jiguang.ai.a.g("JLocation", "package json exception:" + e3.getMessage());
            }
        }
        return jSONObject2;
    }

    public static a d() {
        if (b == null) {
            synchronized (a.class) {
                b = new a();
            }
        }
        return b;
    }

    @Override // cn.jiguang.f.a
    protected final void a(JSONObject jSONObject) {
        int optInt = jSONObject.optInt("cmd");
        JSONObject optJSONObject = jSONObject.optJSONObject("content");
        if (optInt != 5) {
            if (optInt == 45) {
                cn.jiguang.f.f.a(this.a, "JLocation", optJSONObject.optLong("interval", 0L) * 1000);
                return;
            }
            return;
        }
        boolean z = !optJSONObject.optBoolean("disable", true);
        cn.jiguang.f.f.a(this.a, "JLocation", z);
        if (z) {
            cn.jiguang.f.f.b(this.a, "JLocation", optJSONObject.optLong("frequency", 0L) * 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final boolean a() {
        return cn.jiguang.f.f.i(this.a, "JLocation");
    }

    @Override // cn.jiguang.f.a
    protected final boolean a(Context context, String str) {
        return cn.jiguang.f.f.a(context, str);
    }

    @Override // cn.jiguang.f.a
    protected final boolean b() {
        return cn.jiguang.f.f.h(this.a, "JLocation");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't wrap try/catch for region: R(20:1|(2:3|(17:5|6|7|(12:12|13|14|15|(6:20|(3:28|29|(1:31))|22|(1:24)|25|26)|35|(3:39|(4:41|(2:62|63)(1:43)|44|45)(1:64)|(1:47))|(0)|22|(0)|25|26)|69|(13:71|72|73|14|15|(7:17|20|(0)|22|(0)|25|26)|35|(4:37|39|(0)(0)|(0))|(0)|22|(0)|25|26)|13|14|15|(0)|35|(0)|(0)|22|(0)|25|26)(1:93))(1:95)|94|6|7|(13:9|12|13|14|15|(0)|35|(0)|(0)|22|(0)|25|26)|69|(0)|13|14|15|(0)|35|(0)|(0)|22|(0)|25|26|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0098, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0099, code lost:
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00aa, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00ab, code lost:
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b6, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b7, code lost:
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ca, code lost:
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x01aa, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01b7, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01c2, code lost:
        r0 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0061 A[Catch: all -> 0x0098, InterruptedException -> 0x00aa, ExecutionException -> 0x00b6, TimeoutException -> 0x00ca, TRY_LEAVE, TryCatch #7 {InterruptedException -> 0x00aa, ExecutionException -> 0x00b6, TimeoutException -> 0x00ca, all -> 0x0098, blocks: (B:10:0x0048, B:12:0x004e, B:15:0x0055, B:16:0x005b, B:18:0x0061), top: B:100:0x0048 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0102 A[Catch: all -> 0x01aa, InterruptedException -> 0x01b7, ExecutionException -> 0x01c2, TimeoutException -> 0x01d5, TryCatch #8 {InterruptedException -> 0x01b7, ExecutionException -> 0x01c2, TimeoutException -> 0x01d5, all -> 0x01aa, blocks: (B:42:0x00d0, B:44:0x0102, B:47:0x0109, B:48:0x0110, B:50:0x0116, B:52:0x0120, B:54:0x012a, B:70:0x0183, B:68:0x016b), top: B:99:0x00d0 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0116 A[Catch: all -> 0x01aa, InterruptedException -> 0x01b7, ExecutionException -> 0x01c2, TimeoutException -> 0x01d5, TryCatch #8 {InterruptedException -> 0x01b7, ExecutionException -> 0x01c2, TimeoutException -> 0x01d5, all -> 0x01aa, blocks: (B:42:0x00d0, B:44:0x0102, B:47:0x0109, B:48:0x0110, B:50:0x0116, B:52:0x0120, B:54:0x012a, B:70:0x0183, B:68:0x016b), top: B:99:0x00d0 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x012a A[Catch: all -> 0x01aa, InterruptedException -> 0x01b7, ExecutionException -> 0x01c2, TimeoutException -> 0x01d5, TRY_LEAVE, TryCatch #8 {InterruptedException -> 0x01b7, ExecutionException -> 0x01c2, TimeoutException -> 0x01d5, all -> 0x01aa, blocks: (B:42:0x00d0, B:44:0x0102, B:47:0x0109, B:48:0x0110, B:50:0x0116, B:52:0x0120, B:54:0x012a, B:70:0x0183, B:68:0x016b), top: B:99:0x00d0 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x016b A[Catch: all -> 0x01aa, InterruptedException -> 0x01b7, ExecutionException -> 0x01c2, TimeoutException -> 0x01d5, TRY_ENTER, TryCatch #8 {InterruptedException -> 0x01b7, ExecutionException -> 0x01c2, TimeoutException -> 0x01d5, all -> 0x01aa, blocks: (B:42:0x00d0, B:44:0x0102, B:47:0x0109, B:48:0x0110, B:50:0x0116, B:52:0x0120, B:54:0x012a, B:70:0x0183, B:68:0x016b), top: B:99:0x00d0 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0183 A[Catch: all -> 0x01aa, InterruptedException -> 0x01b7, ExecutionException -> 0x01c2, TimeoutException -> 0x01d5, TRY_LEAVE, TryCatch #8 {InterruptedException -> 0x01b7, ExecutionException -> 0x01c2, TimeoutException -> 0x01d5, all -> 0x01aa, blocks: (B:42:0x00d0, B:44:0x0102, B:47:0x0109, B:48:0x0110, B:50:0x0116, B:52:0x0120, B:54:0x012a, B:70:0x0183, B:68:0x016b), top: B:99:0x00d0 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01dc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // cn.jiguang.f.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(android.content.Context r16, java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 550
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.u.a.c(android.content.Context, java.lang.String):void");
    }

    @Override // cn.jiguang.f.a
    protected final String d(Context context) {
        this.a = context;
        return "JLocation";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void d(Context context, String str) {
        String str2;
        String str3;
        JSONObject a = cn.jiguang.s.b.a(context, "rl.catch");
        if (a == null) {
            str2 = "JLocation";
            str3 = "there are no data to report";
        } else {
            JSONArray optJSONArray = a.optJSONArray("content");
            if (optJSONArray != null) {
                cn.jiguang.f.i.a(context, optJSONArray);
                cn.jiguang.ai.a.c("JLocation", "clean cache json");
                if (context != null && !TextUtils.isEmpty("rl.catch")) {
                    try {
                        if (!cn.jiguang.s.b.a(context, "rl.catch", (JSONObject) null)) {
                            synchronized ("rl.catch") {
                                context.deleteFile("rl.catch");
                            }
                        }
                    } catch (Throwable th) {
                        cn.jiguang.ai.a.g("JCommonFileHelper", "cleanJson throwable:" + th.getMessage());
                    }
                }
                super.d(context, str);
                return;
            }
            str2 = "JLocation";
            str3 = "there are no content data to report";
        }
        cn.jiguang.ai.a.c(str2, str3);
    }

    @Override // cn.jiguang.f.a
    protected final boolean e(Context context) {
        return true;
    }

    @Override // cn.jiguang.f.a
    /* renamed from: g */
    public final Bundle f(Context context) {
        long longValue;
        double doubleValue;
        double doubleValue2;
        String d = cn.jiguang.f.f.d(context);
        if (TextUtils.isEmpty(d)) {
            longValue = 0;
            doubleValue = 200.0d;
            doubleValue2 = 200.0d;
        } else {
            String[] split = d.split(",");
            longValue = Long.valueOf(split[0]).longValue();
            doubleValue = Double.valueOf(split[1]).doubleValue();
            doubleValue2 = Double.valueOf(split[2]).doubleValue();
        }
        return a(longValue, doubleValue, doubleValue2);
    }
}
