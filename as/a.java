package cn.jiguang.as;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class a {
    private static String a;

    public static ProviderInfo a(Context context, String str, String str2) {
        ProviderInfo[] providerInfoArr;
        if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 8);
                if (packageInfo.providers != null && packageInfo.providers.length != 0) {
                    for (ProviderInfo providerInfo : packageInfo.providers) {
                        if (str2.equals(providerInfo.authority)) {
                            return providerInfo;
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0039, code lost:
        cn.jiguang.as.a.a = r1.processName;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r3) {
        /*
            java.lang.String r0 = cn.jiguang.as.a.a
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto Lb
            java.lang.String r3 = cn.jiguang.as.a.a
            return r3
        Lb:
            android.content.Context r3 = cn.jiguang.a.a.a(r3)     // Catch: java.lang.Throwable -> L3e
            r0 = 0
            if (r3 == 0) goto L1b
            java.lang.String r0 = "activity"
            java.lang.Object r3 = r3.getSystemService(r0)     // Catch: java.lang.Throwable -> L3e
            r0 = r3
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0     // Catch: java.lang.Throwable -> L3e
        L1b:
            if (r0 == 0) goto L57
            int r3 = android.os.Process.myPid()     // Catch: java.lang.Throwable -> L3e
            java.util.List r0 = r0.getRunningAppProcesses()     // Catch: java.lang.Throwable -> L3e
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L3e
        L29:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Throwable -> L3e
            if (r1 == 0) goto L57
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> L3e
            android.app.ActivityManager$RunningAppProcessInfo r1 = (android.app.ActivityManager.RunningAppProcessInfo) r1     // Catch: java.lang.Throwable -> L3e
            int r2 = r1.pid     // Catch: java.lang.Throwable -> L3e
            if (r2 != r3) goto L29
            java.lang.String r3 = r1.processName     // Catch: java.lang.Throwable -> L3e
            cn.jiguang.as.a.a = r3     // Catch: java.lang.Throwable -> L3e
            goto L57
        L3e:
            r3 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "#unexcepted - getCurProcessName failed:"
            r0.<init>(r1)
            java.lang.String r3 = r3.getMessage()
            java.lang.StringBuilder r3 = r0.append(r3)
            java.lang.String r3 = r3.toString()
            java.lang.String r0 = "AndroidUtil"
            cn.jiguang.ac.d.g(r0, r3)
        L57:
            java.lang.String r3 = cn.jiguang.as.a.a
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.as.a.a(android.content.Context):java.lang.String");
    }

    public static String a(Context context, String str) {
        try {
            return context.getPackageManager().getServiceInfo(new ComponentName(context.getPackageName(), str), 128).processName;
        } catch (Throwable unused) {
            return "";
        }
    }

    public static List<String> a(Context context, Intent intent, String str) {
        ArrayList arrayList = new ArrayList();
        try {
            List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
            PackageManager packageManager = context.getPackageManager();
            for (ResolveInfo resolveInfo : queryIntentServices) {
                if (resolveInfo.serviceInfo != null) {
                    String str2 = resolveInfo.serviceInfo.name;
                    if (!TextUtils.isEmpty(str2)) {
                        boolean z = true;
                        if (!TextUtils.isEmpty(str) && packageManager.checkPermission(str, resolveInfo.activityInfo.packageName) != 0) {
                            z = false;
                        }
                        if (z) {
                            arrayList.add(str2);
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return arrayList;
    }

    public static List<String> a(Context context, List<String> list) {
        if (list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!cn.jiguang.ap.a.a(context, str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static String b(Context context, String str) {
        try {
            return context.getPackageManager().getReceiverInfo(new ComponentName(context.getPackageName(), str), 128).processName;
        } catch (Throwable unused) {
            return "";
        }
    }
}
