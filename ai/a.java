package cn.jiguang.ai;

import cn.jiguang.api.JCoreManager;

/* loaded from: classes.dex */
public final class a {
    private static final String a = cn.jiguang.sdk.impl.a.d;

    private static void a(int i, boolean z, String str, String str2, Throwable th) {
        JCoreManager.onEvent(null, a, 18, str, null, Integer.valueOf(i), Boolean.valueOf(z), str2, th);
    }

    public static void a(String str, String str2) {
        a(2, true, str, str2, null);
    }

    public static void a(String str, String str2, Throwable th) {
        a(5, true, str, str2, th);
    }

    public static void b(String str, String str2) {
        a(2, false, str, str2, null);
    }

    public static void b(String str, String str2, Throwable th) {
        a(6, true, str, str2, th);
    }

    public static void c(String str, String str2) {
        a(3, true, str, str2, null);
    }

    public static void c(String str, String str2, Throwable th) {
        a(6, false, str, str2, th);
    }

    public static void d(String str, String str2) {
        a(3, false, str, str2, null);
    }

    public static void d(String str, String str2, Throwable th) {
        c(str, str2, th);
        if (JCoreManager.isInternal()) {
            throw new RuntimeException(th);
        }
    }

    public static void e(String str, String str2) {
        a(4, true, str, str2, null);
    }

    public static void f(String str, String str2) {
        a(4, false, str, str2, null);
    }

    public static void g(String str, String str2) {
        a(5, true, str, str2, null);
    }

    public static void h(String str, String str2) {
        a(5, false, str, str2, null);
    }

    public static void i(String str, String str2) {
        a(6, true, str, str2, null);
    }

    public static void j(String str, String str2) {
        a(6, false, str, str2, null);
    }
}
