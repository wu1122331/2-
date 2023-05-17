package cn.jiguang.ap;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public final class i {
    public static int a(Context context) {
        String c = c(context);
        if (TextUtils.isEmpty(c)) {
            return 0;
        }
        if ("wifi".equals(c)) {
            return 1;
        }
        if ("2g".equals(c)) {
            return 2;
        }
        if ("3g".equals(c)) {
            return 3;
        }
        return "4g".equals(c) ? 4 : 0;
    }

    public static String a(Context context, int i) {
        String c = c(context);
        cn.jiguang.ai.a.a("TeleonyManagerUtils", "getCurrentNetType - type:" + c);
        if (TextUtils.isEmpty(c)) {
            try {
                Object a = f.a((Class<?>) TelephonyManager.class, "getNetworkClass", new Object[]{Integer.valueOf(i)}, new Class[]{Integer.TYPE});
                if (((Integer) a).intValue() == 0) {
                    c = "unknown";
                } else if (((Integer) a).intValue() == 1) {
                    c = "2g";
                } else if (((Integer) a).intValue() == 2) {
                    c = "3g";
                } else if (((Integer) a).intValue() == 3) {
                    c = "4g";
                }
                cn.jiguang.ai.a.c("TeleonyManagerUtils", "step2 - type:" + c);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | Exception unused) {
            }
        }
        return TextUtils.isEmpty(c) ? "unknown" : c;
    }

    public static String b(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String c(android.content.Context r4) {
        /*
            java.lang.String r0 = "unknown"
            java.lang.String r1 = "connectivity"
            java.lang.Object r4 = r4.getSystemService(r1)     // Catch: java.lang.Exception -> L4b
            android.net.ConnectivityManager r4 = (android.net.ConnectivityManager) r4     // Catch: java.lang.Exception -> L4b
            android.net.NetworkInfo r4 = r4.getActiveNetworkInfo()     // Catch: java.lang.Exception -> L4b
            if (r4 == 0) goto L4f
            int r1 = r4.getType()     // Catch: java.lang.Exception -> L4b
            r2 = 1
            if (r1 != r2) goto L1a
            java.lang.String r0 = "wifi"
            goto L4f
        L1a:
            int r1 = r4.getType()     // Catch: java.lang.Exception -> L4b
            if (r1 != 0) goto L4f
            int r4 = r4.getSubtype()     // Catch: java.lang.Exception -> L4b
            java.lang.String r1 = "TeleonyManagerUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L4b
            java.lang.String r3 = "getNetworkClass networkType:"
            r2.<init>(r3)     // Catch: java.lang.Exception -> L4b
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch: java.lang.Exception -> L4b
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> L4b
            cn.jiguang.ai.a.a(r1, r2)     // Catch: java.lang.Exception -> L4b
            java.lang.String r1 = "4g"
            java.lang.String r2 = "3g"
            java.lang.String r3 = "2g"
            switch(r4) {
                case 1: goto L49;
                case 2: goto L49;
                case 3: goto L47;
                case 4: goto L49;
                case 5: goto L47;
                case 6: goto L47;
                case 7: goto L49;
                case 8: goto L47;
                case 9: goto L47;
                case 10: goto L47;
                case 11: goto L49;
                case 12: goto L47;
                case 13: goto L45;
                case 14: goto L47;
                case 15: goto L47;
                default: goto L41;
            }
        L41:
            switch(r4) {
                case 16: goto L49;
                case 17: goto L47;
                case 18: goto L45;
                case 19: goto L45;
                default: goto L44;
            }
        L44:
            goto L4f
        L45:
            r0 = r1
            goto L4f
        L47:
            r0 = r2
            goto L4f
        L49:
            r0 = r3
            goto L4f
        L4b:
            r4 = move-exception
            r4.printStackTrace()
        L4f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.ap.i.c(android.content.Context):java.lang.String");
    }
}
