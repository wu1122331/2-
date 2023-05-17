package cn.jiguang.f;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class f {
    private static SharedPreferences a;
    private static HashMap<String, Long> b;

    public static int a(Context context) {
        return g(context).getInt("rrat", 1);
    }

    private static String a(String str, String str2) {
        return str + str2;
    }

    public static void a(Context context, int i) {
        g(context).edit().putInt("rrat", i).apply();
    }

    public static void a(Context context, String str, long j) {
        if (j < 0) {
            return;
        }
        if (!str.contains("JLocation") || j <= g(context, str)) {
            String a2 = a(str, "_bi");
            cn.jiguang.ai.a.e("JCommonConfig", "update " + str + " businessInterval:" + j);
            g(context).edit().putLong(a2, j).apply();
        }
    }

    public static void a(Context context, String str, String str2) {
        g(context).edit().putString(a("JType", str), str2).apply();
    }

    public static void a(Context context, String str, boolean z) {
        g(context).edit().putBoolean(a(str, "_ace"), z).apply();
    }

    public static void a(Context context, boolean z) {
        g(context).edit().putBoolean("JArponceEnable", z).apply();
    }

    public static boolean a(Context context, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        long d = d(context, str);
        long j = g(context).getLong(a(str, "_bi"), b(str, "_bi"));
        boolean z = currentTimeMillis - d > j;
        cn.jiguang.ai.a.e("JCommonConfig", "is " + str + " businessTime:" + z + ",curTime:" + currentTimeMillis + ",lastBusinessTime:" + d + ",businessInterval:" + j);
        return z;
    }

    public static int b(Context context) {
        return g(context).getInt("rrpt", 1);
    }

    private static long b(String str, String str2) {
        try {
            return b.get(a(str, str2)).longValue();
        } catch (NullPointerException unused) {
            return 0L;
        }
    }

    public static void b(Context context, int i) {
        g(context).edit().putInt("rrpt", i).apply();
    }

    public static void b(Context context, String str, long j) {
        if (j < 0) {
            return;
        }
        String a2 = a(str, "_ri");
        cn.jiguang.ai.a.e("JCommonConfig", "update " + str + " reportInterval:" + j);
        g(context).edit().putLong(a2, j).apply();
    }

    public static void b(Context context, String str, boolean z) {
        g(context).edit().putBoolean(a(str, "_aue"), z).apply();
    }

    public static boolean b(Context context, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        long f = f(context, str);
        long g = g(context, str);
        boolean z = currentTimeMillis - f > g;
        cn.jiguang.ai.a.e("JCommonConfig", "is " + str + " reportTime:" + z + ",curTime:" + currentTimeMillis + ",lastReportTime:" + f + ",reportInterval:" + g);
        return z;
    }

    public static void c(Context context, String str) {
        if (str.contains("JApp")) {
            return;
        }
        String a2 = a(str, "_blt");
        long currentTimeMillis = System.currentTimeMillis();
        cn.jiguang.ai.a.e("JCommonConfig", "update " + str + " lastBusinessTime");
        g(context).edit().putLong(a2, currentTimeMillis).apply();
    }

    public static boolean c(Context context) {
        return g(context).getBoolean("JArponceEnable", false);
    }

    public static long d(Context context, String str) {
        return g(context).getLong(a(str, "_blt"), 0L);
    }

    public static String d(Context context) {
        return g(context).getString(a("JLocation", "info"), "");
    }

    public static String e(Context context) {
        return g(context).getString("JNotificationState", "");
    }

    public static void e(Context context, String str) {
        cn.jiguang.ai.a.e("JCommonConfig", "update " + str + " lastReportTime");
        long currentTimeMillis = System.currentTimeMillis();
        g(context).edit().putLong(a(str, "_rlt"), currentTimeMillis).apply();
    }

    public static long f(Context context, String str) {
        return g(context).getLong(a(str, "_rlt"), 0L);
    }

    public static String f(Context context) {
        return g(context).getString("JDevicesession", "");
    }

    public static long g(Context context, String str) {
        return g(context).getLong(a(str, "_ri"), b(str, "_ri"));
    }

    private static SharedPreferences g(Context context) {
        if (a == null) {
            a = context.getSharedPreferences("cn.jiguang.common", 0);
            HashMap<String, Long> hashMap = new HashMap<>();
            b = hashMap;
            hashMap.put(a("JLocation", "_bi"), 900000L);
            b.put(a("JWake", "_bi"), 3600000L);
            b.put(a("JWakeConfigHelper", "_bi"), 3600000L);
            b.put(a("JAppAll", "_ri"), 604800000L);
            b.put(a("JAppMovement", "_ri"), 3600000L);
            b.put(a("JAppRunning", "_ri"), 3600000L);
            b.put(a("JArp", "_ri"), 3600000L);
            b.put(a("JDeviceBattery", "_ri"), 3600000L);
            b.put(a("JDevice", "_ri"), 86400000L);
            b.put(a("JLocation", "_ri"), 3600000L);
            b.put(a("JWake", "_ri"), 3600000L);
        }
        return a;
    }

    public static boolean h(Context context, String str) {
        return g(context).getBoolean(a(str, "_ace"), !str.equals("JArp"));
    }

    public static boolean i(Context context, String str) {
        return g(context).getBoolean(a(str, "_aue"), true);
    }

    public static String j(Context context, String str) {
        return g(context).getString(a("JType", str), "0,0");
    }

    public static void k(Context context, String str) {
        g(context).edit().putBoolean(a("JArp", str), true).apply();
    }

    public static boolean l(Context context, String str) {
        return g(context).getBoolean(a("JArp", str), false);
    }

    public static void m(Context context, String str) {
        g(context).edit().putString(a("JLocation", "info"), str).apply();
    }

    public static void n(Context context, String str) {
        g(context).edit().putString("JNotificationState", str).apply();
    }

    public static void o(Context context, String str) {
        cn.jiguang.ai.a.e("JCommonConfig", "update deviceSession");
        g(context).edit().putString("JDevicesession", str).apply();
    }
}
