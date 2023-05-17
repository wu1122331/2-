package cn.jiguang.o;

import android.content.Context;
import android.content.SharedPreferences;
import cn.jiguang.as.j;
import cn.jiguang.r.d;

/* loaded from: classes.dex */
public final class c {
    private static SharedPreferences a;

    public static String a(Context context) {
        return b(context, "number_version", "1.3.0");
    }

    public static String a(Context context, int i) {
        return (i < 0 || i >= 3) ? "http://182.92.20.189:9099/" : b(context, "number_url" + i, "http://182.92.20.189:9099/");
    }

    public static void a(Context context, String str) {
        String a2 = d.a(context);
        if (j.a(a2)) {
            a2 = "number_num";
        }
        a(context, a2, str);
    }

    public static void a(Context context, String str, String str2) {
        g(context).edit().putString(str, cn.jiguang.al.a.a(str2)).apply();
    }

    public static void a(Context context, boolean z) {
        g(context).edit().putBoolean("nb_upload", z).apply();
    }

    public static String b(Context context) {
        return b(context, "number_appid", "7");
    }

    private static String b(Context context, String str, String str2) {
        String string = g(context).getString(str, "");
        return j.a(string) ? str2 : cn.jiguang.al.a.b(string, str2);
    }

    public static String c(Context context) {
        return b(context, "number_appsecret", "2b90de0f1f88eaf49593f1d827b19c63");
    }

    public static String d(Context context) {
        String a2 = d.a(context);
        if (j.a(a2)) {
            a2 = "number_num";
        }
        return b(context, a2, "");
    }

    public static boolean e(Context context) {
        return g(context).getBoolean("nb_upload", false);
    }

    public static void f(Context context) {
        g(context).edit().putLong("nb_lasttime", System.currentTimeMillis()).apply();
    }

    private static SharedPreferences g(Context context) {
        if (a == null) {
            a = context.getSharedPreferences("cn.jiguang.common.pn", 0);
        }
        return a;
    }
}
