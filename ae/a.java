package cn.jiguang.ae;

import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.api.JCoreManager;
import java.util.LinkedHashMap;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class a {
    public static String a;
    public static int b;
    public static String c;
    public static int d;
    private static final LinkedHashMap<String, Integer> e;
    private static final LinkedHashMap<String, Integer> f;
    private static final LinkedHashMap<String, Integer> g;
    private static final LinkedHashMap<String, Integer> h;
    private static String i;
    private static String j;
    private static String k;
    private static String l;

    static {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        e = linkedHashMap;
        linkedHashMap.put("s.jpush.cn", 19000);
        linkedHashMap.put("sis.jpush.io", 19000);
        linkedHashMap.put("easytomessage.com", 19000);
        LinkedHashMap<String, Integer> linkedHashMap2 = new LinkedHashMap<>();
        f = linkedHashMap2;
        linkedHashMap2.put("123.196.118.23", 19000);
        linkedHashMap2.put("103.229.215.60", 19000);
        linkedHashMap2.put("117.121.49.100", 19000);
        g = new LinkedHashMap<>();
        h = new LinkedHashMap<>();
        i = "";
        j = "";
        k = "";
        l = "";
    }

    public static String a(Context context) {
        if (!JCoreManager.isTestEnv() || TextUtils.isEmpty(i)) {
            String str = (String) c.b(context, b.v());
            return !TextUtils.isEmpty(str) ? str : "im64.jpush.cn";
        }
        return i;
    }

    public static LinkedHashMap<String, Integer> a() {
        if (JCoreManager.isTestEnv()) {
            LinkedHashMap<String, Integer> linkedHashMap = g;
            if (!linkedHashMap.isEmpty()) {
                return linkedHashMap;
            }
        }
        return e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.lang.CharSequence, T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.lang.CharSequence, T, java.lang.String] */
    public static void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            cn.jiguang.ai.a.g("HostConfig", "conn info was empty");
            return;
        }
        cn.jiguang.ai.a.c("HostConfig", "get conn info=" + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            ?? optString = jSONObject.optString("srv");
            cn.jiguang.ai.a.c("HostConfig", "save srvHost:" + ((String) optString));
            if (!TextUtils.isEmpty(optString)) {
                b<String> w = b.w();
                w.c = optString;
                c.a(context, w);
            }
            ?? optString2 = jSONObject.optString("conn");
            cn.jiguang.ai.a.c("HostConfig", "save connHost:" + ((String) optString2));
            if (TextUtils.isEmpty(optString2)) {
                return;
            }
            b<String> v = b.v();
            v.c = optString2;
            c.a(context, v);
        } catch (Throwable unused) {
        }
    }

    public static String b(Context context) {
        if (!JCoreManager.isTestEnv() || TextUtils.isEmpty(j)) {
            String str = (String) c.b(context, b.w());
            return !TextUtils.isEmpty(str) ? str : "_im64._tcp.jpush.cn";
        }
        return j;
    }

    public static LinkedHashMap<String, Integer> b() {
        if (JCoreManager.isTestEnv()) {
            LinkedHashMap<String, Integer> linkedHashMap = h;
            if (!linkedHashMap.isEmpty()) {
                return linkedHashMap;
            }
        }
        return f;
    }

    public static String c() {
        return (!JCoreManager.isTestEnv() || TextUtils.isEmpty(k)) ? "_psis._udp.jpush.cn" : k;
    }

    public static String d() {
        return (!JCoreManager.isTestEnv() || TextUtils.isEmpty(l)) ? "" : l;
    }
}
