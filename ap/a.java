package cn.jiguang.ap;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import cn.jpush.android.service.PushReceiver;
import com.facebook.internal.AnalyticsEvents;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.security.auth.x500.X500Principal;

/* loaded from: classes.dex */
public final class a {
    private static String a = "";
    private static String b = "";
    private static String c = "";

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        String[] split = str.split("\\.");
        return (Integer.parseInt(split[0]) << 16) + (Integer.parseInt(split[1]) << 8) + Integer.parseInt(split[2]);
    }

    public static ComponentInfo a(Context context, String str, Class<?> cls) {
        int i;
        ComponentInfo[] componentInfoArr;
        if (context == null || TextUtils.isEmpty(str) || cls == null) {
            cn.jiguang.ai.a.g("AndroidUtil", "Action - hasComponent, invalide param, context:" + context + ",packageName:" + str + ",cls:" + cls);
            return null;
        }
        try {
            int i2 = Service.class.isAssignableFrom(cls) ? 4 : BroadcastReceiver.class.isAssignableFrom(cls) ? 2 : Activity.class.isAssignableFrom(cls) ? 1 : ContentProvider.class.isAssignableFrom(cls) ? 8 : 0;
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, i2);
            componentInfoArr = i2 != 1 ? i2 != 2 ? i2 != 4 ? i2 != 8 ? null : packageInfo.providers : packageInfo.services : packageInfo.receivers : packageInfo.activities;
        } catch (Throwable th) {
            cn.jiguang.ai.a.h("AndroidUtil", "hasComponent error:" + th);
        }
        if (componentInfoArr == null) {
            return null;
        }
        for (ComponentInfo componentInfo : componentInfoArr) {
            if (cls.isAssignableFrom(Class.forName(componentInfo.name))) {
                return componentInfo;
            }
        }
        return null;
    }

    public static String a(Context context) {
        if (TextUtils.isEmpty(a)) {
            String packageName = context.getPackageName();
            a = packageName;
            return packageName;
        }
        return a;
    }

    public static String a(Context context, String str, String str2) {
        try {
            return (String) f.a(context.getClassLoader().loadClass("android.os.SystemProperties"), "get", new Object[]{str, str2}, new Class[]{String.class, String.class});
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception unused) {
            return "";
        }
    }

    private static List<String> a(Context context, Intent intent, String str) {
        ArrayList arrayList = new ArrayList();
        try {
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
            PackageManager packageManager = context.getPackageManager();
            for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                if (resolveInfo.activityInfo != null) {
                    String str2 = resolveInfo.activityInfo.name;
                    if (!TextUtils.isEmpty(str2)) {
                        boolean z = true;
                        if (!TextUtils.isEmpty(null) && packageManager.checkPermission(null, resolveInfo.activityInfo.packageName) != 0) {
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

    public static void a(Context context, Intent intent) {
        try {
            context.sendBroadcast(intent);
        } catch (Throwable unused) {
            try {
                List<String> a2 = a(context, intent, (String) null);
                if (a2.isEmpty()) {
                    cn.jiguang.ai.a.h("AndroidUtil", "sendBroadcast failed again: receiver not found, action:" + intent.getAction());
                    return;
                }
                for (String str : a2) {
                    try {
                        Intent intent2 = (Intent) intent.clone();
                        intent2.setComponent(new ComponentName(context.getPackageName(), str));
                        context.sendBroadcast(intent2);
                    } catch (Exception e) {
                        cn.jiguang.ai.a.h("AndroidUtil", "sendBroadcast failed again:" + e.getMessage() + ", action:" + intent.getAction());
                    }
                }
            } catch (Throwable th) {
                cn.jiguang.ai.a.h("AndroidUtil", "tryAgainSendBrocast failed:" + th.getMessage());
            }
        }
    }

    public static void a(Context context, String str, int i) {
        int i2;
        Notification notification;
        if (!l(context)) {
            cn.jiguang.ai.a.c("AndroidUtil", "not debuggable");
        } else if (!a(context, PushReceiver.class)) {
            new Handler(Looper.getMainLooper()).post(new b(context, str));
        } else {
            cn.jiguang.ai.a.c("AndroidUtil", "action:showPermanentNotification");
            Intent intent = new Intent(context, PushReceiver.class);
            intent.setAction("noti_open_proxy");
            intent.addCategory(context.getPackageName());
            intent.putExtra("debug_notification", true);
            intent.putExtra("toastText", str);
            intent.putExtra("type", -1);
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 134217728);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            try {
                i2 = context.getPackageManager().getApplicationInfo(context.getApplicationContext().getPackageName(), 0).icon;
            } catch (Throwable th) {
                cn.jiguang.ai.a.c("AndroidUtil", "failed to get application info and icon.", th);
                i2 = 17301586;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (Build.VERSION.SDK_INT >= 11) {
                Notification.Builder when = new Notification.Builder(context.getApplicationContext()).setContentTitle("Jiguang提示：包名和AppKey不匹配").setContentText("请到 Portal 上获取您的包名和AppKey并更新AndroidManifest相应字段").setContentIntent(broadcast).setSmallIcon(i2).setTicker(str).setWhen(currentTimeMillis);
                if (Build.VERSION.SDK_INT >= 26) {
                    when.setChannelId("JPush_Notification");
                }
                notification = when.getNotification();
                notification.flags = 34;
            } else {
                Notification notification2 = new Notification(i2, str, currentTimeMillis);
                notification2.flags = 34;
                try {
                    f.a(notification2, "setLatestEventInfo", new Object[]{context, "Jiguang提示：包名和AppKey不匹配", "请到 Portal 上获取您的包名和AppKey并更新AndroidManifest相应字段", broadcast}, new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class});
                } catch (Exception unused) {
                }
                notification = notification2;
            }
            notificationManager.notify(str.hashCode(), notification);
        }
    }

    public static boolean a() {
        boolean z;
        try {
            z = Environment.getExternalStorageState().equals("mounted");
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("AndroidUtil", "isSdcardExist exception: " + th);
            z = false;
        }
        if (!z) {
            cn.jiguang.ai.a.c("AndroidUtil", "SDCard is not mounted");
        }
        return z;
    }

    private static boolean a(Context context, Class<?> cls) {
        boolean z;
        boolean z2 = false;
        try {
            z = !context.getPackageManager().queryBroadcastReceivers(new Intent(context, cls), 0).isEmpty();
        } catch (Throwable unused) {
        }
        if (z) {
            return z;
        }
        try {
            if (a(context, context.getPackageName(), cls) != null) {
                z2 = true;
            }
        } catch (Throwable unused2) {
            z2 = z;
        }
        return z2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002e, code lost:
        if (((android.app.AppOpsManager) r4.getSystemService("appops")).noteProxyOpNoThrow(r5, r4.getPackageName()) == 0) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(android.content.Context r4, java.lang.String r5) {
        /*
            r0 = 0
            r1 = 1
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L32
            r3 = 23
            if (r2 < r3) goto L30
            android.content.pm.ApplicationInfo r2 = r4.getApplicationInfo()     // Catch: java.lang.Throwable -> L32
            int r2 = r2.targetSdkVersion     // Catch: java.lang.Throwable -> L32
            if (r2 < r3) goto L17
            int r4 = r4.checkSelfPermission(r5)     // Catch: java.lang.Throwable -> L32
            if (r4 != 0) goto L4b
            goto L30
        L17:
            java.lang.String r5 = android.app.AppOpsManager.permissionToOp(r5)     // Catch: java.lang.Throwable -> L32
            if (r5 != 0) goto L1e
            goto L30
        L1e:
            java.lang.String r2 = "appops"
            java.lang.Object r2 = r4.getSystemService(r2)     // Catch: java.lang.Throwable -> L32
            android.app.AppOpsManager r2 = (android.app.AppOpsManager) r2     // Catch: java.lang.Throwable -> L32
            java.lang.String r4 = r4.getPackageName()     // Catch: java.lang.Throwable -> L32
            int r4 = r2.noteProxyOpNoThrow(r5, r4)     // Catch: java.lang.Throwable -> L32
            if (r4 != 0) goto L4b
        L30:
            r0 = 1
            goto L4b
        L32:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r1 = "checkPermission error:"
            r5.<init>(r1)
            java.lang.String r4 = r4.getMessage()
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "AndroidUtil"
            cn.jiguang.ai.a.g(r5, r4)
        L4b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.ap.a.a(android.content.Context, java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int b(android.content.Context r5) {
        /*
            java.lang.String r0 = "AndroidUtil"
            r1 = -1
            if (r5 != 0) goto L6
            return r1
        L6:
            android.content.IntentFilter r2 = new android.content.IntentFilter
            java.lang.String r3 = "android.intent.action.BATTERY_CHANGED"
            r2.<init>(r3)
            r3 = 0
            android.content.Intent r3 = r5.registerReceiver(r3, r2)     // Catch: java.lang.Exception -> L13 java.lang.SecurityException -> L28
            goto L2d
        L13:
            r5 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "getChargedStatus unkown exception:"
            r2.<init>(r4)
            java.lang.String r5 = r5.getMessage()
            java.lang.StringBuilder r5 = r2.append(r5)
            java.lang.String r5 = r5.toString()
            goto L2a
        L28:
            java.lang.String r5 = "getChargedStatus SecurityException"
        L2a:
            cn.jiguang.ai.a.g(r0, r5)
        L2d:
            if (r3 != 0) goto L30
            return r1
        L30:
            java.lang.String r5 = "status"
            int r5 = r3.getIntExtra(r5, r1)
            r0 = 2
            if (r5 == r0) goto L3f
            r0 = 5
            if (r5 != r0) goto L3d
            goto L3f
        L3d:
            r5 = 0
            goto L40
        L3f:
            r5 = 1
        L40:
            if (r5 != 0) goto L43
            return r1
        L43:
            java.lang.String r5 = "plugged"
            int r5 = r3.getIntExtra(r5, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.ap.a.b(android.content.Context):int");
    }

    public static ProviderInfo b(Context context, String str, Class<? extends ContentProvider> cls) {
        try {
            return context.getPackageManager().getProviderInfo(new ComponentName(str, cls.getName()), 65536);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b() {
        /*
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L9 java.lang.Exception -> Ld
            java.lang.String r0 = r0.getPath()     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L9 java.lang.Exception -> Ld
            goto Le
        L9:
            r0 = move-exception
            r0.printStackTrace()
        Ld:
            r0 = 0
        Le:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L27
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r1 = "/data/"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
        L27:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.ap.a.b():java.lang.String");
    }

    public static String b(Context context, String str) {
        String str2 = null;
        try {
        } catch (Exception e) {
            cn.jiguang.ai.a.i("AndroidUtil", e.getMessage());
        }
        if (cn.jiguang.sdk.impl.b.a(context, false, "do not getImei")) {
            return "";
        }
        if (a(context, "android.permission.READ_PHONE_STATE")) {
            str2 = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        }
        return h.h(str2) ? str2 : str;
    }

    public static ComponentInfo c(Context context, String str, Class<?> cls) {
        int i;
        ComponentInfo[] componentInfoArr;
        if (context == null || TextUtils.isEmpty(str) || cls == null) {
            cn.jiguang.ai.a.g("AndroidUtil", "Action - hasComponent, invalide param, context:" + context + ",packageName:" + str + ",cls:" + cls);
            return null;
        }
        try {
            int i2 = Service.class.isAssignableFrom(cls) ? 4 : BroadcastReceiver.class.isAssignableFrom(cls) ? 2 : Activity.class.isAssignableFrom(cls) ? 1 : ContentProvider.class.isAssignableFrom(cls) ? 8 : 0;
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, i2);
            componentInfoArr = i2 != 1 ? i2 != 2 ? i2 != 4 ? i2 != 8 ? null : packageInfo.providers : packageInfo.services : packageInfo.receivers : packageInfo.activities;
        } catch (Throwable th) {
            cn.jiguang.ai.a.h("AndroidUtil", "hasComponent error:" + th.getMessage());
        }
        if (componentInfoArr == null) {
            return null;
        }
        String name = cls.getName();
        for (ComponentInfo componentInfo : componentInfoArr) {
            if (name.equals(componentInfo.name)) {
                return componentInfo;
            }
        }
        return null;
    }

    public static String c() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                        return nextElement.getHostAddress();
                    }
                }
            }
            return "";
        } catch (Exception e) {
            cn.jiguang.ai.a.g("AndroidUtil", "getPhoneIp:");
            e.printStackTrace();
            return "";
        }
    }

    public static String c(Context context, String str) {
        try {
            return cn.jiguang.sdk.impl.b.a(context, false, "do not getImsi") ? "" : a(context, "android.permission.READ_PHONE_STATE") ? ((TelephonyManager) context.getSystemService("phone")).getSubscriberId() : str;
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("AndroidUtil", "getImsi failed:" + th.getMessage());
            return str;
        }
    }

    public static boolean c(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            if (a(context, "android.permission.ACCESS_NETWORK_STATE") && (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) != null) {
                if (activeNetworkInfo.isConnected()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }

    public static String d(Context context, String str) {
        String m = m(context);
        if (!h.i(m)) {
            m = n(context);
        }
        if (h.i(m)) {
            str = m;
        }
        cn.jiguang.ai.a.c("AndroidUtil", "getWifiMac:" + str);
        return str;
    }

    public static boolean d(Context context) {
        String str = context.getApplicationInfo().sourceDir;
        if (h.a(str)) {
            cn.jiguang.ai.a.i("AndroidUtil", "Unexpected: cannot get pk installed path");
            return false;
        }
        cn.jiguang.ai.a.c("AndroidUtil", "Current pk installed path: " + str);
        if (str.startsWith("/system/app/")) {
            return true;
        }
        if (str.startsWith("/data/app/")) {
            return false;
        }
        cn.jiguang.ai.a.e("AndroidUtil", "NOTE: the pk does not installed in system/data. ");
        return false;
    }

    public static String e(Context context) {
        DisplayMetrics displayMetrics;
        if (context == null || context.getResources() == null || (displayMetrics = context.getResources().getDisplayMetrics()) == null) {
            return "0*0";
        }
        return displayMetrics.widthPixels + "*" + displayMetrics.heightPixels;
    }

    public static boolean e(Context context, String str) {
        if (h.a(str)) {
            return false;
        }
        return context.getPackageManager().getPackageInfo(str, 0) != null;
    }

    public static String f(Context context) {
        String str;
        try {
            str = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable unused) {
            str = "";
        }
        return h.h(str) ? str : "";
    }

    public static boolean f(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("empty params");
        }
        try {
            context.getPackageManager().getPermissionInfo(str, 128);
            return true;
        } catch (Throwable th) {
            cn.jiguang.ai.a.h("AndroidUtil", "hasPermissionDefined error:" + th.getMessage());
            return false;
        }
    }

    public static String g(Context context) {
        try {
            String d = d(context, "");
            if (d != null && !d.equals("")) {
                cn.jiguang.ai.a.e("AndroidUtil", "MAC addr info---- " + d);
                return h.d(d.toLowerCase() + Build.MODEL);
            }
            return null;
        } catch (Exception e) {
            cn.jiguang.ai.a.b("AndroidUtil", "", e);
            return null;
        }
    }

    public static boolean h(Context context) {
        String a2 = a(context, "ro.product.brand", "");
        cn.jiguang.ai.a.c("AndroidUtil", "brand = " + a2);
        String a3 = a(context, "ro.miui.ui.version.name", "");
        if (TextUtils.isEmpty(a2) || !"Xiaomi".equals(a2) || TextUtils.isEmpty(a3)) {
            return true;
        }
        String a4 = a(context, "ro.build.version.incremental", "");
        if (TextUtils.isEmpty(a4) || !a4.startsWith("V7.1")) {
            return true;
        }
        cn.jiguang.ai.a.g("AndroidUtil", "7.1 will not get wifi list");
        return false;
    }

    public static String i(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            }
            String typeName = activeNetworkInfo.getTypeName();
            String subtypeName = activeNetworkInfo.getSubtypeName();
            return typeName == null ? AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN : !h.a(subtypeName) ? typeName + "," + subtypeName : typeName;
        } catch (Exception e) {
            e.printStackTrace();
            return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
    }

    public static String j(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            if (wifiManager == null || !a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return "";
            }
            String bssid = wifiManager.getConnectionInfo().getBSSID();
            if (bssid != null) {
                try {
                    if (bssid.startsWith("02:00:00:")) {
                        return "";
                    }
                } catch (Throwable unused) {
                }
                return bssid;
            }
            return "";
        } catch (Throwable unused2) {
            return "";
        }
    }

    public static String k(Context context) {
        Object invoke;
        String str = null;
        int i = -1;
        if (context != null) {
            try {
                Object systemService = context.getApplicationContext().getSystemService("country_detector");
                if (systemService != null) {
                    Method declaredMethod = systemService.getClass().getDeclaredMethod("detectCountry", new Class[0]);
                    if (declaredMethod != null && (invoke = declaredMethod.invoke(systemService, new Object[0])) != null) {
                        String str2 = (String) invoke.getClass().getDeclaredMethod("getCountryIso", new Class[0]).invoke(invoke, new Object[0]);
                        try {
                            i = ((Integer) invoke.getClass().getDeclaredMethod("getSource", new Class[0]).invoke(invoke, new Object[0])).intValue();
                            str = str2;
                        } catch (Throwable th) {
                            th = th;
                            str = str2;
                            cn.jiguang.ai.a.i("AndroidUtil", "getCountryCode failed, error :" + th);
                            cn.jiguang.ai.a.c("AndroidUtil", "get CountCode = " + str + " source = " + i);
                            if (i != 0) {
                            }
                        }
                    }
                } else {
                    cn.jiguang.ai.a.c("AndroidUtil", "country_detector is null");
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        cn.jiguang.ai.a.c("AndroidUtil", "get CountCode = " + str + " source = " + i);
        return (i != 0 || i == 1) ? str : "";
    }

    private static boolean l(Context context) {
        try {
            cn.jiguang.ai.a.c("AndroidUtil", "isDebug:" + ((context.getApplicationInfo().flags & 2) != 0));
            X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
            String[] strArr = {"CN=Android Debug", "O=Android", "C=US"};
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            boolean z = false;
            for (Signature signature : signatureArr) {
                try {
                    X509Certificate x509Certificate = (X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(signature.toByteArray()));
                    z = x509Certificate.getSubjectX500Principal().equals(x500Principal);
                    cn.jiguang.ai.a.c("AndroidUtil", "debuggable :" + z);
                    if (z) {
                        break;
                    }
                    String str = null;
                    try {
                        str = x509Certificate.getSubjectX500Principal().getName();
                    } catch (Exception unused) {
                    }
                    cn.jiguang.ai.a.c("AndroidUtil", "certName:" + str);
                    if (str != null && str.contains(strArr[0]) && str.contains(strArr[1]) && str.contains(strArr[2])) {
                        return true;
                    }
                } catch (Throwable unused2) {
                }
            }
            return z;
        } catch (Throwable unused3) {
            return false;
        }
    }

    private static String m(Context context) {
        String str = "";
        if (Build.VERSION.SDK_INT >= 23 || !a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return "";
        }
        try {
            str = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
            cn.jiguang.ai.a.c("AndroidUtil", "mac address from WifiManager:" + str);
            return str;
        } catch (Exception e) {
            cn.jiguang.ai.a.a("AndroidUtil", "get mac from wifiManager failed ", e);
            return str;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3 = new java.lang.StringBuilder();
        r5 = r4.length;
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004c, code lost:
        if (r6 >= r5) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004e, code lost:
        r3.append(java.lang.String.format(java.util.Locale.ENGLISH, "%02x:", java.lang.Byte.valueOf(r4[r6])));
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006a, code lost:
        if (r3.length() <= 0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006c, code lost:
        r3.deleteCharAt(r3.length() - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0074, code lost:
        r2 = r3.toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0078, code lost:
        cn.jiguang.ai.a.c("AndroidUtil", "mac address from NetworkInterface:" + r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x008b, code lost:
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x008c, code lost:
        r3 = r12;
        r12 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x009a, code lost:
        cn.jiguang.ai.a.g("AndroidUtil", "get mac from NetworkInterface failed:" + r12.getMessage());
        r12 = r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String n(android.content.Context r12) {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "AndroidUtil"
            r2 = 0
            java.lang.String r3 = "android.permission.ACCESS_WIFI_STATE"
            boolean r3 = a(r12, r3)     // Catch: java.lang.Exception -> L97
            if (r3 == 0) goto L1e
            android.content.Context r12 = r12.getApplicationContext()     // Catch: java.lang.Exception -> L97
            java.lang.String r3 = "wifi"
            java.lang.Object r12 = r12.getSystemService(r3)     // Catch: java.lang.Exception -> L97
            android.net.wifi.WifiManager r12 = (android.net.wifi.WifiManager) r12     // Catch: java.lang.Exception -> L97
            boolean r12 = r12.isWifiEnabled()     // Catch: java.lang.Exception -> L97
            goto L1f
        L1e:
            r12 = 0
        L1f:
            java.util.Enumeration r3 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch: java.lang.Exception -> L92
        L23:
            boolean r4 = r3.hasMoreElements()     // Catch: java.lang.Exception -> L92
            if (r4 == 0) goto L90
            java.lang.Object r4 = r3.nextElement()     // Catch: java.lang.Exception -> L92
            java.net.NetworkInterface r4 = (java.net.NetworkInterface) r4     // Catch: java.lang.Exception -> L92
            java.lang.String r5 = "wlan0"
            java.lang.String r6 = r4.getName()     // Catch: java.lang.Exception -> L92
            boolean r5 = r5.equalsIgnoreCase(r6)     // Catch: java.lang.Exception -> L92
            if (r5 == 0) goto L23
            byte[] r4 = r4.getHardwareAddress()     // Catch: java.lang.Exception -> L92
            if (r4 == 0) goto L23
            int r5 = r4.length     // Catch: java.lang.Exception -> L92
            if (r5 == 0) goto L23
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L92
            r3.<init>()     // Catch: java.lang.Exception -> L92
            int r5 = r4.length     // Catch: java.lang.Exception -> L92
            r6 = 0
        L4b:
            r7 = 1
            if (r6 >= r5) goto L66
            r8 = r4[r6]     // Catch: java.lang.Exception -> L92
            java.util.Locale r9 = java.util.Locale.ENGLISH     // Catch: java.lang.Exception -> L92
            java.lang.String r10 = "%02x:"
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Exception -> L92
            java.lang.Byte r8 = java.lang.Byte.valueOf(r8)     // Catch: java.lang.Exception -> L92
            r7[r2] = r8     // Catch: java.lang.Exception -> L92
            java.lang.String r7 = java.lang.String.format(r9, r10, r7)     // Catch: java.lang.Exception -> L92
            r3.append(r7)     // Catch: java.lang.Exception -> L92
            int r6 = r6 + 1
            goto L4b
        L66:
            int r2 = r3.length()     // Catch: java.lang.Exception -> L92
            if (r2 <= 0) goto L74
            int r2 = r3.length()     // Catch: java.lang.Exception -> L92
            int r2 = r2 - r7
            r3.deleteCharAt(r2)     // Catch: java.lang.Exception -> L92
        L74:
            java.lang.String r2 = r3.toString()     // Catch: java.lang.Exception -> L92
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L8b
            java.lang.String r4 = "mac address from NetworkInterface:"
            r3.<init>(r4)     // Catch: java.lang.Exception -> L8b
            java.lang.StringBuilder r3 = r3.append(r2)     // Catch: java.lang.Exception -> L8b
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> L8b
            cn.jiguang.ai.a.c(r1, r3)     // Catch: java.lang.Exception -> L8b
            goto Lb1
        L8b:
            r3 = move-exception
            r11 = r3
            r3 = r12
            r12 = r11
            goto L9a
        L90:
            r2 = r0
            goto Lb1
        L92:
            r2 = move-exception
            r3 = r12
            r12 = r2
            r2 = r0
            goto L9a
        L97:
            r12 = move-exception
            r2 = r0
            r3 = 0
        L9a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "get mac from NetworkInterface failed:"
            r4.<init>(r5)
            java.lang.String r12 = r12.getMessage()
            java.lang.StringBuilder r12 = r4.append(r12)
            java.lang.String r12 = r12.toString()
            cn.jiguang.ai.a.g(r1, r12)
            r12 = r3
        Lb1:
            if (r12 != 0) goto Lc6
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r3 = "mac address is dropped, which is "
            r12.<init>(r3)
            java.lang.StringBuilder r12 = r12.append(r2)
            java.lang.String r12 = r12.toString()
            cn.jiguang.ai.a.c(r1, r12)
            goto Lc7
        Lc6:
            r0 = r2
        Lc7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.ap.a.n(android.content.Context):java.lang.String");
    }
}
