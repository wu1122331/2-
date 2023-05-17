package cn.jiguang.ad;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import cn.jiguang.api.JCoreManager;
import cn.jiguang.api.ReportCallBack;
import com.facebook.internal.security.CertificateUtil;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class k {
    public static JSONObject a = null;
    public static boolean b = true;
    public static boolean c = true;
    private static String d = "";
    private static String e = "/v3/report";
    private static String f = "";

    public static int a(Context context, Set<String> set, JSONObject jSONObject, File file, ReportCallBack reportCallBack) {
        if (jSONObject != null) {
            try {
                if (jSONObject.length() != 0) {
                    if (!cn.jiguang.ap.a.c(context)) {
                        cn.jiguang.ac.d.f("ReportUtils", "no network, give up upload");
                        d.a(file);
                        if (reportCallBack != null) {
                            reportCallBack.onFinish(-2);
                        }
                        return -2;
                    }
                    Pair<byte[], Integer> a2 = a(jSONObject.toString(), true, 2);
                    if (a2 != null && a2.first != null && ((byte[]) a2.first).length != 0) {
                        byte[] bArr = (byte[]) a2.first;
                        int intValue = ((Integer) a2.second).intValue();
                        cn.jiguang.ac.d.b("ReportUtils", "will upload length=" + bArr.length);
                        if (b && bArr.length < 30680 && set != null && !set.contains("crash_log")) {
                            cn.jiguang.sdk.impl.b.a(context, jSONObject, bArr, intValue, file, set, reportCallBack);
                            return 1;
                        }
                        int a3 = a(context, bArr, intValue, set);
                        if (a3 == 0) {
                            cn.jiguang.ac.d.b("ReportUtils", "http upload success json=" + cn.jiguang.as.h.a(jSONObject));
                            cn.jiguang.as.e.a(file);
                        }
                        if (a3 != 1) {
                            d.a(file);
                            if (reportCallBack != null) {
                                reportCallBack.onFinish(a3);
                            }
                        }
                        return a3;
                    }
                    cn.jiguang.ac.d.f("ReportUtils", "package body failed, give up upload");
                    d.a(file);
                    if (reportCallBack != null) {
                        reportCallBack.onFinish(-1);
                    }
                    return -1;
                }
            } catch (Throwable th) {
                try {
                    cn.jiguang.ac.d.f("ReportUtils", "upload failed, error:" + th);
                    d.a(file);
                    if (reportCallBack != null) {
                        reportCallBack.onFinish(-1);
                    }
                    return -1;
                } catch (Throwable th2) {
                    if (0 != 1) {
                        d.a(file);
                        if (reportCallBack != null) {
                            reportCallBack.onFinish(0);
                        }
                    }
                    throw th2;
                }
            }
        }
        cn.jiguang.ac.d.f("ReportUtils", "upload content is empty, do nothing");
        d.a(file);
        if (reportCallBack != null) {
            reportCallBack.onFinish(-1);
        }
        return -1;
    }

    private static int a(Context context, byte[] bArr, int i, Set<String> set) {
        StringBuilder append;
        String[] split;
        StringBuilder sb;
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet();
        Set<String> b2 = a.a().b(context).b(set);
        if (b2 != null) {
            for (String str : b2) {
                if (!TextUtils.isEmpty(str)) {
                    linkedHashSet.add(str);
                }
            }
        }
        String str2 = (!JCoreManager.isTestEnv() || TextUtils.isEmpty(f)) ? "stats.jpush.cn" : f;
        if (!TextUtils.isEmpty(str2)) {
            linkedHashSet.add("https://" + str2);
        }
        String str3 = (String) cn.jiguang.e.b.a(context, cn.jiguang.e.a.a(true));
        if (!TextUtils.isEmpty(str3)) {
            for (String str4 : str3.split(",")) {
                if (!cn.jiguang.as.j.e(str4)) {
                    if (!cn.jiguang.as.j.f(str4)) {
                        sb = new StringBuilder();
                    } else if (!c) {
                        sb = new StringBuilder();
                    }
                    linkedHashSet.add(sb.append("https://").append(str4).toString());
                } else if (c) {
                    sb = new StringBuilder();
                    linkedHashSet.add(sb.append("https://").append(str4).toString());
                }
            }
        }
        cn.jiguang.ac.d.b("ReportUtils", "types=" + set + " find urls=" + linkedHashSet);
        if (linkedHashSet.isEmpty()) {
            cn.jiguang.ac.d.f("ReportUtils", "can't get url, give up upload");
            return -2;
        }
        String str5 = " type=" + set;
        for (String str6 : linkedHashSet) {
            if (TextUtils.isEmpty(str6)) {
                cn.jiguang.ac.d.f("ReportUtils", "can't get url, give up upload");
            } else {
                if (!str6.endsWith(e)) {
                    str6 = str6 + e;
                }
                String str7 = str6;
                cn.jiguang.ac.d.b("ReportUtils", "upload" + str5 + " to url:" + str7);
                n a2 = b.a(context, str7, bArr, i, 3, 1);
                int a3 = a2.a();
                if (a3 == -3) {
                    d.a(context, cn.jiguang.ab.f.b(context));
                    return -2;
                }
                if (a3 == -1) {
                    append = new StringBuilder("upload").append(str5).append(" error:").append(a2.b());
                } else if (a3 == 0) {
                    return 0;
                } else {
                    append = new StringBuilder("upload").append(str5).append(" failed");
                }
                cn.jiguang.ac.d.b("ReportUtils", append.toString());
            }
        }
        return -1;
    }

    private static Pair<byte[], Integer> a(String str, boolean z, int i) {
        try {
            byte[] a2 = cn.jiguang.ap.k.a(str.getBytes("UTF-8"));
            int a3 = cn.jiguang.as.i.a();
            return new Pair<>(cn.jiguang.as.i.a(a2, cn.jiguang.as.i.a(a3), "iop203040506aPk!"), Integer.valueOf(a3));
        } catch (UnsupportedEncodingException | IOException | Exception unused) {
            return null;
        }
    }

    public static String a(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        long longValue = ((Long) cn.jiguang.e.b.a(context, cn.jiguang.e.a.c())).longValue();
        if (longValue == 0) {
            cn.jiguang.ac.d.b("ReportUtils", " miss uid,generate report token failed");
            return null;
        }
        String i = cn.jiguang.as.j.i(longValue + cn.jiguang.as.j.d((String) cn.jiguang.e.b.a(context, cn.jiguang.e.a.e())) + str);
        if (cn.jiguang.as.j.a(i)) {
            return null;
        }
        try {
            return Base64.encodeToString((longValue + CertificateUtil.DELIMITER + i + CertificateUtil.DELIMITER + str2).getBytes(), 10);
        } catch (Exception unused) {
            cn.jiguang.ac.d.h("getBasicAuthorization", "basic authorization encode failed");
            return null;
        }
    }

    public static String a(String str) {
        try {
            return cn.jiguang.as.i.a(str, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCi0v4VEUhOdfIEfFCrPC72pcDsQF/luTmr4q34NY0EZYGKzfQuTrUAm916P52HCgF+342gjZ/Nvijts5543qYNyoLvgtu4NRcHJmuGI/w7qifhfsivYeoEj9wYphXOyB9HUjzwn1BtAih+1RyUrcErCi249yabUOIKQygPZ9OXXQIDAQAB");
        } catch (Throwable unused) {
            cn.jiguang.ac.d.h("getBasicAuthorization", "basic authorization encode failed");
            return null;
        }
    }

    private static ArrayList<JSONArray> a(JSONArray jSONArray, int i, int i2) {
        ArrayList<JSONArray> arrayList = new ArrayList<>();
        if (jSONArray != null && jSONArray.length() != 0) {
            if (jSONArray.length() == 1) {
                arrayList.add(jSONArray);
                return arrayList;
            }
            JSONArray jSONArray2 = new JSONArray();
            int i3 = 0;
            int i4 = 0;
            for (int length = jSONArray.length() - 1; length >= 0; length--) {
                JSONObject optJSONObject = jSONArray.optJSONObject(length);
                try {
                    int b2 = cn.jiguang.as.h.b(optJSONObject);
                    if (b2 != 0) {
                        int i5 = i3 + b2;
                        if (i5 > 204800) {
                            break;
                        }
                        int i6 = i4 + b2;
                        if (i6 > 40960) {
                            if (jSONArray2.length() > 0) {
                                arrayList.add(jSONArray2);
                            }
                            JSONArray jSONArray3 = new JSONArray();
                            try {
                                jSONArray3.put(optJSONObject);
                                jSONArray2 = jSONArray3;
                            } catch (Throwable th) {
                                th = th;
                                jSONArray2 = jSONArray3;
                                cn.jiguang.ac.d.f("ReportUtils", "partition exception:" + th);
                            }
                        } else {
                            jSONArray2.put(optJSONObject);
                            b2 = i6;
                        }
                        i4 = b2;
                        i3 = i5;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (jSONArray2.length() > 0) {
                arrayList.add(jSONArray2);
            }
        }
        return arrayList;
    }

    public static Set<String> a(JSONObject jSONObject) {
        return jSONObject == null ? new HashSet() : c(jSONObject.optJSONArray("content"));
    }

    public static JSONObject a(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("platform", "a");
            long longValue = ((Long) cn.jiguang.e.b.a(context, cn.jiguang.e.a.c())).longValue();
            if (longValue == 0) {
                cn.jiguang.ac.d.f("ReportUtils", "miss uid when wrap container info");
                return null;
            }
            jSONObject.put("uid", longValue);
            String b2 = cn.jiguang.ab.f.b(context);
            if (cn.jiguang.as.j.a(b2)) {
                cn.jiguang.ac.d.h("ReportUtils", "miss app_key when wrap container info");
                return null;
            }
            jSONObject.put("app_key", b2);
            cn.jiguang.ab.a.a();
            cn.jiguang.ab.a.a(jSONObject);
            jSONObject.put("core_sdk_ver", JCoreManager.SDK_VERSION);
            String c2 = cn.jiguang.ab.f.c(context);
            if (cn.jiguang.as.j.a(c2)) {
                cn.jiguang.ac.d.g("ReportUtils", "miss channel when wrap container info,but continue report...");
            } else {
                jSONObject.put("channel", c2);
            }
            Pair<String, Integer> e2 = cn.jiguang.ab.f.e(context);
            if (e2 == null || cn.jiguang.as.j.a((String) e2.first)) {
                cn.jiguang.ac.d.g("ReportUtils", "miss app version when wrap container info,but continue report...");
            } else {
                jSONObject.put("app_version", e2.first);
            }
            return jSONObject;
        } catch (Throwable th) {
            cn.jiguang.ac.d.f("ReportUtils", "wrapContainerInfo exception:" + th);
            return null;
        }
    }

    public static JSONObject a(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                cn.jiguang.ac.d.b("ReportUtils", "file_name is null , give up read ");
                return null;
            }
            String b2 = cn.jiguang.as.e.b(cn.jiguang.as.e.a(context, str));
            if (cn.jiguang.as.j.a(b2)) {
                cn.jiguang.ac.d.b("ReportUtils", "read String is empty");
                return null;
            }
            return new JSONObject(b2.trim());
        } catch (Throwable th) {
            cn.jiguang.ac.d.b("ReportUtils", "can't build " + str + " into JsonObject, give up read :" + th);
            return null;
        }
    }

    public static JSONObject a(Context context, JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put("itime", cn.jiguang.e.c.b(context));
            jSONObject.put("type", str);
            jSONObject.put("account_id", cn.jiguang.e.b.a(context, cn.jiguang.e.a.m()));
        } catch (JSONException e2) {
            cn.jiguang.ac.d.f("ReportUtils", "fillBase exception:" + e2);
        }
        return jSONObject;
    }

    private static JSONObject a(JSONArray jSONArray, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("content", jSONArray);
        } catch (JSONException unused) {
        }
        cn.jiguang.as.h.a(jSONObject2, jSONObject);
        return jSONObject2;
    }

    public static void a(Context context, int i, byte[] bArr, int i2, File file, Set<String> set, ReportCallBack reportCallBack) {
        try {
            cn.jiguang.ac.d.b("ReportUtils", "onTcpReportResult, types=" + set + " code=" + i);
            if (i == -3) {
                d.a(context, cn.jiguang.ab.f.b(context));
            } else if (i == 0 || (i = a(context, bArr, i2, set)) == 0) {
                boolean z = cn.jiguang.a.a.c;
                cn.jiguang.as.e.a(file);
            }
            if (reportCallBack != null) {
                reportCallBack.onFinish(i);
            }
            d.a(file);
        } catch (Throwable unused) {
            d.a(file);
        }
    }

    public static void a(Context context, Object obj) {
        try {
            if (b(obj)) {
                cn.jiguang.ar.a.a("BUILD_REPORT", new l(obj, context));
            } else {
                cn.jiguang.ac.d.b("ReportUtils", "data is invalid or empty");
            }
            d.b(context);
        } catch (Throwable th) {
            cn.jiguang.ac.d.f("ReportUtils", "report e:" + th);
        }
    }

    public static void a(Context context, String str, Object obj) {
        try {
            cn.jiguang.ac.d.b("ReportUtils", "going to report data at push service");
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("sdk_type", str);
            }
            if (b(obj)) {
                bundle.putString("report_data", obj.toString());
            }
            cn.jiguang.a.a.a(context, "a1", bundle);
        } catch (Throwable th) {
            try {
                cn.jiguang.ac.d.a("ReportUtils", "reportAtPushService", th);
            } catch (Throwable th2) {
                cn.jiguang.ac.d.a("ReportUtils", "reportAtPushService", th2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(Context context, JSONArray jSONArray, Set set) {
        try {
            String a2 = a.a().a(context).a(set);
            JSONObject a3 = a(context);
            boolean z = a3 != null;
            String str = File.separator + a2 + File.separator + (z ? "tmp" : "nowrap");
            Iterator<JSONArray> it = a(jSONArray, 40960, 204800).iterator();
            while (it.hasNext()) {
                JSONObject a4 = a(it.next(), a3);
                File a5 = d.a(context, str, a4, z);
                cn.jiguang.ac.d.b("ReportUtils", "save report types=" + set + " at " + str + File.separator + a5.getName());
                if (z) {
                    cn.jiguang.ar.a.a("UPLOAD_REPORT", new m(context, set, a4, a5));
                }
            }
        } catch (Throwable th) {
            cn.jiguang.ac.d.h("ReportUtils", "report exception:" + th);
        }
    }

    public static void a(Context context, JSONObject jSONObject, ReportCallBack reportCallBack) {
        if (jSONObject != null) {
            try {
                if (jSONObject.length() > 0) {
                    JSONObject a2 = a(context);
                    if (a2 == null) {
                        cn.jiguang.ac.d.h("ReportUtils", "wrap data failed");
                        if (reportCallBack != null) {
                            reportCallBack.onFinish(-1);
                        }
                    } else {
                        String b2 = b(jSONObject);
                        JSONObject a3 = a(new JSONArray().put(jSONObject), a2);
                        HashSet hashSet = new HashSet();
                        hashSet.add(b2);
                        cn.jiguang.ac.d.b("ReportUtils", "reportWithoutStore type=" + b2);
                        a(context, hashSet, a3, null, reportCallBack);
                    }
                }
            } catch (Throwable th) {
                cn.jiguang.ac.d.h("ReportUtils", "reportWithoutStore exception:" + th);
            }
        }
        d.b(context);
    }

    public static boolean a(Context context, String str, JSONObject jSONObject) {
        try {
            if (cn.jiguang.as.j.a(str)) {
                cn.jiguang.ac.d.b("ReportUtils", "file_name is null , give up save ");
                return false;
            } else if (context != null) {
                return cn.jiguang.as.e.a(cn.jiguang.as.e.a(context, str), jSONObject != null ? jSONObject.toString() : "");
            } else {
                cn.jiguang.ac.d.b("ReportUtils", "context is null , give up save " + str);
                return false;
            }
        } catch (Throwable th) {
            cn.jiguang.ac.d.b("ReportUtils", "writeLogFile e:" + th);
            return false;
        }
    }

    private static String b(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optString("type");
        }
        return null;
    }

    private static JSONArray b(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return jSONArray;
        }
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null && optJSONObject.length() > 0) {
                jSONArray2.put(optJSONObject);
            }
        }
        return jSONArray2;
    }

    private static boolean b(Object obj) {
        return obj instanceof String ? ((String) obj).length() > 2 : obj instanceof JSONObject ? ((JSONObject) obj).length() > 0 : (obj instanceof JSONArray) && b((JSONArray) obj).length() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Set<String> c(JSONArray jSONArray) {
        HashSet hashSet = new HashSet();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                String b2 = b(jSONArray.optJSONObject(i));
                if (b2 == null) {
                    b2 = "";
                }
                hashSet.add(b2);
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003c A[Catch: all -> 0x0043, TRY_LEAVE, TryCatch #0 {all -> 0x0043, blocks: (B:3:0x0001, B:16:0x0036, B:18:0x003c, B:9:0x001f, B:11:0x0023, B:12:0x002d, B:14:0x0031), top: B:25:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.json.JSONArray c(java.lang.Object r3) {
        /*
            r0 = 0
            boolean r1 = r3 instanceof java.lang.String     // Catch: java.lang.Throwable -> L43
            if (r1 == 0) goto L1f
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch: java.lang.Throwable -> Le
            r2 = r3
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Throwable -> Le
            r1.<init>(r2)     // Catch: java.lang.Throwable -> Le
            goto L36
        Le:
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L35
            r1.<init>()     // Catch: java.lang.Throwable -> L35
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L35
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L35
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L35
            org.json.JSONArray r1 = r1.put(r2)     // Catch: java.lang.Throwable -> L35
            goto L36
        L1f:
            boolean r1 = r3 instanceof org.json.JSONObject     // Catch: java.lang.Throwable -> L43
            if (r1 == 0) goto L2d
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L43
            r1.<init>()     // Catch: java.lang.Throwable -> L43
            org.json.JSONArray r1 = r1.put(r3)     // Catch: java.lang.Throwable -> L43
            goto L36
        L2d:
            boolean r1 = r3 instanceof org.json.JSONArray     // Catch: java.lang.Throwable -> L43
            if (r1 == 0) goto L35
            r1 = r3
            org.json.JSONArray r1 = (org.json.JSONArray) r1     // Catch: java.lang.Throwable -> L43
            goto L36
        L35:
            r1 = r0
        L36:
            org.json.JSONArray r3 = b(r1)     // Catch: java.lang.Throwable -> L43
            if (r3 == 0) goto L58
            int r1 = r3.length()     // Catch: java.lang.Throwable -> L43
            if (r1 <= 0) goto L58
            return r3
        L43:
            r3 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "adapt JSONArray e:"
            r1.<init>(r2)
            java.lang.StringBuilder r3 = r1.append(r3)
            java.lang.String r3 = r3.toString()
            java.lang.String r1 = "ReportUtils"
            cn.jiguang.ac.d.f(r1, r3)
        L58:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.ad.k.c(java.lang.Object):org.json.JSONArray");
    }
}
