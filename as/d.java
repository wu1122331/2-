package cn.jiguang.as;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import java.io.File;

/* loaded from: classes.dex */
public final class d {
    public static int a = 1;

    /* JADX WARN: Removed duplicated region for block: B:27:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r7) {
        /*
            cn.jiguang.e.a r0 = cn.jiguang.e.a.a()
            java.lang.Object r0 = cn.jiguang.e.b.a(r7, r0)
            java.lang.String r0 = (java.lang.String) r0
            boolean r1 = cn.jiguang.as.j.g(r0)
            if (r1 == 0) goto L14
            r7 = 3
            cn.jiguang.as.d.a = r7
            return r0
        L14:
            java.lang.String r0 = b(r7, r0)
            boolean r1 = cn.jiguang.as.j.g(r0)
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L35
            cn.jiguang.as.d.a = r3
            d(r7, r0)
            cn.jiguang.e.a[] r1 = new cn.jiguang.e.a[r3]
            cn.jiguang.e.a r3 = cn.jiguang.e.a.a()
            cn.jiguang.e.a r3 = r3.a(r0)
            r1[r2] = r3
            cn.jiguang.e.b.a(r7, r1)
            return r0
        L35:
            java.lang.String r0 = "do not get deviceId from SD"
            boolean r0 = cn.jiguang.a.a.a(r7, r3, r0)
            if (r0 != 0) goto L86
            java.lang.String r0 = "android.permission.READ_EXTERNAL_STORAGE"
            boolean r0 = cn.jiguang.ap.a.a(r7, r0)
            if (r0 == 0) goto L86
            java.lang.String r0 = cn.jiguang.ap.a.b()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L7f
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r0 = r4.append(r0)
            java.lang.String r4 = ".push_deviceid"
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            java.lang.String r0 = cn.jiguang.as.e.b(r1)
            if (r0 == 0) goto L86
            java.lang.String r1 = "\n"
            int r1 = r0.indexOf(r1)
            if (r1 >= 0) goto L76
            goto L7a
        L76:
            java.lang.String r0 = r0.substring(r2, r1)
        L7a:
            java.lang.String r0 = r0.trim()
            goto L87
        L7f:
            java.lang.String r0 = "DeviceIdUtils"
            java.lang.String r1 = "can't get sdcard data path"
            cn.jiguang.ac.d.h(r0, r1)
        L86:
            r0 = 0
        L87:
            boolean r1 = cn.jiguang.as.j.g(r0)
            if (r1 == 0) goto La3
            r1 = 2
            cn.jiguang.as.d.a = r1
            c(r7, r0)
            cn.jiguang.e.a[] r1 = new cn.jiguang.e.a[r3]
            cn.jiguang.e.a r3 = cn.jiguang.e.a.a()
            cn.jiguang.e.a r3 = r3.a(r0)
            r1[r2] = r3
            cn.jiguang.e.b.a(r7, r1)
            return r0
        La3:
            int r1 = android.os.Build.VERSION.SDK_INT
            r4 = 23
            java.lang.String r5 = ""
            if (r1 >= r4) goto Lb0
            java.lang.String r0 = cn.jiguang.ap.a.b(r7, r0)
            goto Lb1
        Lb0:
            r0 = r5
        Lb1:
            java.lang.String r1 = cn.jiguang.ap.a.f(r7)
            java.lang.String r4 = cn.jiguang.ap.a.d(r7, r5)
            java.util.UUID r5 = java.util.UUID.randomUUID()
            java.lang.String r5 = r5.toString()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.StringBuilder r0 = r6.append(r0)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.StringBuilder r0 = r0.append(r5)
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = cn.jiguang.as.j.c(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto Le5
            goto Le6
        Le5:
            r5 = r0
        Le6:
            cn.jiguang.e.a[] r0 = new cn.jiguang.e.a[r3]
            cn.jiguang.e.a r1 = cn.jiguang.e.a.a()
            cn.jiguang.e.a r1 = r1.a(r5)
            r0[r2] = r1
            cn.jiguang.e.b.a(r7, r0)
            cn.jiguang.as.d.a = r2
            c(r7, r5)
            d(r7, r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.as.d.a(android.content.Context):java.lang.String");
    }

    public static void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        c(context, str);
        d(context, str);
        cn.jiguang.e.b.a(context, cn.jiguang.e.a.a().a((cn.jiguang.e.a<String>) str));
    }

    private static String b(Context context, String str) {
        if (cn.jiguang.ap.a.a(context, "android.permission.WRITE_SETTINGS")) {
            try {
                return Settings.System.getString(context.getContentResolver(), "dig");
            } catch (Throwable unused) {
                cn.jiguang.ac.d.h("DeviceIdUtils", "Can not read from settings");
                return str;
            }
        }
        return str;
    }

    private static String c(Context context, String str) {
        if (cn.jiguang.ap.a.a(context, "android.permission.WRITE_SETTINGS")) {
            try {
                if (Settings.System.putString(context.getContentResolver(), "dig", str)) {
                    return str;
                }
                return null;
            } catch (Throwable unused) {
                cn.jiguang.ac.d.h("DeviceIdUtils", "Can not write settings");
                return null;
            }
        }
        return null;
    }

    private static String d(Context context, String str) {
        if (cn.jiguang.a.a.a(context, true, "not write deviceId to SD")) {
            return str;
        }
        if (cn.jiguang.ap.a.a(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            try {
                String b = cn.jiguang.ap.a.b();
                if (TextUtils.isEmpty(b)) {
                    cn.jiguang.ac.d.h("DeviceIdUtils", "can't get sdcard data path");
                    return null;
                }
                e.a(new File(b + ".push_deviceid"), str);
                return str;
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }
}
