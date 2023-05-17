package cn.jiguang.r;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/* loaded from: classes.dex */
public final class c {
    public static String a(Context context, String str) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (String) loadClass.getMethod("get", String.class).invoke(loadClass, new String(str));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String a(String str) {
        if (!TextUtils.isEmpty(str) && str.length() < 14) {
            return null;
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<cn.jiguang.q.b> a(android.content.Context r17) {
        /*
            Method dump skipped, instructions count: 2446
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.r.c.a(android.content.Context):java.util.ArrayList");
    }

    public static String b(Context context, String str) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cn.jiguang.sdk.impl.b.a(context, false, "do not getIccid")) {
            return "";
        }
        if (cn.jiguang.ap.a.a(context, "android.permission.READ_PHONE_STATE")) {
            str = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        } else {
            cn.jiguang.ai.a.g("JIGUANG-JDeviceImeiHelper", "collect simSerialNumber failed because has no android.permission.READ_PHONE_STATE");
        }
        return str;
    }
}
