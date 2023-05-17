package cn.jiguang.ab;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import cn.jiguang.as.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes.dex */
public final class b {
    public static String a = ".permission.JPUSH_MESSAGE";
    private static String b = "";
    private static String c;
    private static Pair<String, Integer> d;
    private static final ArrayList<String> e;
    private static final ArrayList<String> f;
    private static final ArrayList<String> g;

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        e = arrayList;
        ArrayList<String> arrayList2 = new ArrayList<>();
        f = arrayList2;
        arrayList.add("android.permission.INTERNET");
        arrayList.add("android.permission.ACCESS_NETWORK_STATE");
        arrayList2.add("android.permission.WAKE_LOCK");
        arrayList2.add("android.permission.VIBRATE");
        arrayList2.add("android.permission.CHANGE_WIFI_STATE");
        ArrayList<String> arrayList3 = new ArrayList<>();
        g = arrayList3;
        arrayList3.add("android.permission.ACCESS_FINE_LOCATION");
        arrayList3.add("android.permission.ACCESS_COARSE_LOCATION");
        arrayList3.add("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS");
        arrayList3.add("android.permission.ACCESS_WIFI_STATE");
    }

    public static String a(Context context) {
        if (TextUtils.isEmpty(b)) {
            try {
                if (context != null) {
                    ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                    if (applicationInfo != null && applicationInfo.metaData != null) {
                        String a2 = a(applicationInfo.metaData, "JPUSH_APPKEY");
                        b = a2;
                        if (!TextUtils.isEmpty(a2)) {
                            b = b.toLowerCase(Locale.getDefault());
                        }
                    }
                } else {
                    cn.jiguang.ac.d.b("CheckManifestHelper", "[getAppKey] context is null");
                }
            } catch (Throwable unused) {
            }
        }
        return b;
    }

    public static String a(Bundle bundle, String str) {
        Object obj;
        if (bundle == null || (obj = bundle.get(str)) == null) {
            return null;
        }
        return obj.toString();
    }

    public static void a(Context context, String str) {
        c = str;
        cn.jiguang.e.b.a(context, cn.jiguang.e.a.l().a((cn.jiguang.e.a<String>) str));
        b(context);
    }

    public static String b(Context context) {
        StringBuilder append;
        if (c == null && context != null) {
            try {
                String a2 = f.a(context);
                c = a2;
                if (a2 != null) {
                    append = new StringBuilder("get option channel - ").append(c);
                } else {
                    ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                    if (applicationInfo != null && applicationInfo.metaData != null) {
                        String a3 = a(applicationInfo.metaData, "JPUSH_CHANNEL");
                        c = a3;
                        if (!TextUtils.isEmpty(a3)) {
                            c = j.b(c);
                        }
                    }
                    append = new StringBuilder("manifest:channel - ").append(c);
                }
                cn.jiguang.ac.d.c("CheckManifestHelper", append.toString());
            } catch (Throwable unused) {
            }
        }
        return c;
    }

    public static Pair<String, Integer> c(Context context) {
        if (d == null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                String str = packageInfo.versionName;
                if (str != null && str.length() > 30) {
                    str = str.substring(0, 30);
                }
                d = new Pair<>(str, Integer.valueOf(packageInfo.versionCode));
            } catch (Throwable unused) {
                cn.jiguang.ac.d.c("CheckManifestHelper", "NO versionCode or versionName defined in manifest.");
            }
        }
        return d;
    }

    public static boolean d(Context context) {
        String a2 = a(context);
        if (TextUtils.isEmpty(a2)) {
            cn.jiguang.ac.d.i("CheckManifestHelper", "errorcode:10001,metadata: JCore appKey - not defined in manifest");
            cn.jiguang.ap.a.a(context, " 未在manifest中配置AppKey", -1);
            return false;
        } else if (a2.length() != 24) {
            cn.jiguang.ac.d.i("CheckManifestHelper", "errorcode:1008,Invalid appKey : " + a2 + ", Please get your Appkey from JIGUANG web console!");
            cn.jiguang.ap.a.a(context, " AppKey:" + a2 + " 是无效的AppKey,请确认与JIGUANG web端的AppKey一致", -1);
            return false;
        } else {
            b(context);
            if (c.a().c() || c.a().b()) {
                String str = context.getPackageName() + a;
                if (!cn.jiguang.ap.a.f(context, str)) {
                    cn.jiguang.ac.d.i("CheckManifestHelper", "The permission should be defined - " + str);
                    return false;
                }
                e.add(str);
            }
            Iterator<String> it = e.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (!cn.jiguang.ap.a.a(context, next)) {
                    cn.jiguang.ac.d.i("CheckManifestHelper", "The permissoin is required - " + next);
                    return false;
                }
            }
            if (Build.VERSION.SDK_INT < 23 && !cn.jiguang.ap.a.a(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                cn.jiguang.ac.d.i("CheckManifestHelper", "The permissoin is required - android.permission.WRITE_EXTERNAL_STORAGE");
                return false;
            }
            Iterator<String> it2 = f.iterator();
            while (it2.hasNext()) {
                String next2 = it2.next();
                if (!cn.jiguang.ap.a.a(context, next2)) {
                    cn.jiguang.ac.d.f("CheckManifestHelper", "We recommend you add the permission - " + next2);
                }
            }
            Iterator<String> it3 = g.iterator();
            while (it3.hasNext()) {
                String next3 = it3.next();
                if (!cn.jiguang.ap.a.a(context, next3)) {
                    cn.jiguang.ac.d.f("CheckManifestHelper", "We recommend you add the permission - " + next3 + ", otherwise you can not locate the devices.");
                }
            }
            return true;
        }
    }
}
