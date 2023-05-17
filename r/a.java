package cn.jiguang.r;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import cn.jiguang.api.JCoreManager;
import cn.jiguang.as.j;
import cn.jiguang.f.i;
import com.facebook.internal.security.CertificateUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class a {
    private static String a = "";
    private static String b = "";
    private static final FileFilter c = new b();

    public static int a(String str) {
        if (j.a(str)) {
            return -1;
        }
        if (str.equalsIgnoreCase("ChinaTelecom")) {
            return 2;
        }
        if (str.equalsIgnoreCase("ChinaMobile")) {
            return 0;
        }
        return str.equalsIgnoreCase("ChinaUnicom") ? 1 : -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0063, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int a(java.lang.String r10, java.io.FileInputStream r11) {
        /*
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r0]
            r2 = -1
            int r11 = r11.read(r1)     // Catch: java.lang.Throwable -> L66
            r3 = 0
            r4 = 0
        Lb:
            if (r4 >= r11) goto L66
            r5 = r1[r4]     // Catch: java.lang.Throwable -> L66
            r6 = 10
            if (r5 == r6) goto L15
            if (r4 != 0) goto L63
        L15:
            r5 = r1[r4]     // Catch: java.lang.Throwable -> L66
            if (r5 != r6) goto L1b
            int r4 = r4 + 1
        L1b:
            r5 = r4
        L1c:
            if (r5 >= r11) goto L63
            int r7 = r5 - r4
            r8 = r1[r5]     // Catch: java.lang.Throwable -> L66
            char r9 = r10.charAt(r7)     // Catch: java.lang.Throwable -> L66
            if (r8 != r9) goto L63
            int r8 = r10.length()     // Catch: java.lang.Throwable -> L66
            int r8 = r8 + (-1)
            if (r7 != r8) goto L60
        L30:
            if (r5 >= r0) goto L5f
            r10 = r1[r5]     // Catch: java.lang.Throwable -> L66
            if (r10 == r6) goto L5f
            r10 = r1[r5]     // Catch: java.lang.Throwable -> L66
            r11 = 48
            if (r10 < r11) goto L5c
            r10 = r1[r5]     // Catch: java.lang.Throwable -> L66
            r4 = 57
            if (r10 > r4) goto L5c
            int r10 = r5 + 1
        L44:
            if (r10 >= r0) goto L51
            r6 = r1[r10]     // Catch: java.lang.Throwable -> L66
            if (r6 < r11) goto L51
            r6 = r1[r10]     // Catch: java.lang.Throwable -> L66
            if (r6 > r4) goto L51
            int r10 = r10 + 1
            goto L44
        L51:
            java.lang.String r11 = new java.lang.String     // Catch: java.lang.Throwable -> L66
            int r10 = r10 - r5
            r11.<init>(r1, r3, r5, r10)     // Catch: java.lang.Throwable -> L66
            int r10 = java.lang.Integer.parseInt(r11)     // Catch: java.lang.Throwable -> L66
            return r10
        L5c:
            int r5 = r5 + 1
            goto L30
        L5f:
            return r2
        L60:
            int r5 = r5 + 1
            goto L1c
        L63:
            int r4 = r4 + 1
            goto Lb
        L66:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.r.a.a(java.lang.String, java.io.FileInputStream):int");
    }

    public static String a() {
        if (TextUtils.isEmpty(a)) {
            f();
            return a;
        }
        return a;
    }

    public static String a(Context context) {
        DisplayMetrics displayMetrics;
        if (context == null || context.getResources() == null || (displayMetrics = context.getResources().getDisplayMetrics()) == null) {
            return "0*0";
        }
        return displayMetrics.widthPixels + "*" + displayMetrics.heightPixels;
    }

    public static String a(Context context, int i) {
        String str;
        if (i != 53) {
            return null;
        }
        try {
            String str2 = (String) cn.jiguang.ae.c.b(context, new cn.jiguang.ae.b("cn.jpush.preferences.v2", "n_udp_report_device_info", ""));
            if (TextUtils.isEmpty(str2)) {
                str = (String) cn.jiguang.ae.c.b(context, new cn.jiguang.ae.b("cn.jpush.preferences.v2", "udp_report_device_info", ""));
                if (!TextUtils.isEmpty(str)) {
                    a(context, str);
                }
            } else {
                str = new String(Base64.decode(str2, 2));
            }
            cn.jiguang.ai.a.c("JDeviceHelper", "read deviceinfo:" + str);
            if (TextUtils.isEmpty(str)) {
                str = e(context);
            }
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject();
            cn.jiguang.ah.b.a();
            jSONObject2.put("rom_type", (int) cn.jiguang.ah.b.a(context));
            cn.jiguang.ah.b.a();
            jSONObject2.put("regid", cn.jiguang.ah.b.b(context));
            jSONObject.put("rom_info", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("cmd", 53);
            jSONObject3.put("content", jSONObject);
            return jSONObject3.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    public static void a(Context context, String str) {
        cn.jiguang.ae.c.a(context, new cn.jiguang.ae.b("cn.jpush.preferences.v2", "n_udp_report_device_info", "").a((cn.jiguang.ae.b) Base64.encodeToString(str.getBytes(), 2)));
        cn.jiguang.ai.a.c("JDeviceHelper", "save device info:" + str);
    }

    public static double b(Context context) {
        double pow;
        int i;
        Point point = new Point();
        boolean z = context instanceof Activity;
        if (z) {
            Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
            if (Build.VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealSize(point);
            } else if (Build.VERSION.SDK_INT >= 13) {
                defaultDisplay.getSize(point);
            } else {
                point.x = defaultDisplay.getWidth();
                point.y = defaultDisplay.getHeight();
            }
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (z) {
            pow = Math.pow(point.x / displayMetrics.xdpi, 2.0d);
            i = point.y;
        } else {
            pow = Math.pow(displayMetrics.widthPixels / displayMetrics.xdpi, 2.0d);
            i = displayMetrics.heightPixels;
        }
        return Math.sqrt(pow + Math.pow(i / displayMetrics.ydpi, 2.0d));
    }

    private static long b(String str) {
        String readLine;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 4096);
            do {
                readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
            } while (!readLine.contains(str));
            bufferedReader.close();
            return Integer.valueOf(readLine.split("\\s+")[1]).intValue();
        } catch (Throwable unused) {
            return -1L;
        }
    }

    public static String b() {
        if (TextUtils.isEmpty(b)) {
            f();
            return b;
        }
        return b;
    }

    public static long c() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return (statFs.getBlockCount() * statFs.getBlockSize()) / 1024;
        } catch (Throwable unused) {
            return -1L;
        }
    }

    public static long c(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 16) {
                ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
                activityManager.getProcessMemoryInfo(new int[]{0});
                ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                activityManager.getMemoryInfo(memoryInfo);
                return memoryInfo.totalMem / 1024;
            }
        } catch (Throwable unused) {
        }
        return b("MemTotal");
    }

    public static int d() {
        if (Build.VERSION.SDK_INT <= 10) {
            return 1;
        }
        try {
            return new File("/sys/devices/system/cpu/").listFiles(c).length;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static JSONArray d(Context context) {
        JSONObject a2;
        List<cn.jiguang.q.b> a3 = e.a(context.getApplicationContext());
        if (a3 != null) {
            JSONArray jSONArray = new JSONArray();
            for (cn.jiguang.q.b bVar : a3) {
                if (bVar != null && (a2 = bVar.a()) != null) {
                    jSONArray.put(a2);
                }
            }
            return jSONArray;
        }
        return null;
    }

    public static int e() {
        int i = -1;
        for (int i2 = 0; i2 < d(); i2++) {
            try {
                File file = new File("/sys/devices/system/cpu/cpu" + i2 + "/cpufreq/cpuinfo_max_freq");
                if (file.exists()) {
                    byte[] bArr = new byte[128];
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        fileInputStream.read(bArr);
                        int i3 = 0;
                        while (bArr[i3] >= 48 && bArr[i3] <= 57 && i3 < 128) {
                            i3++;
                        }
                        Integer valueOf = Integer.valueOf(Integer.parseInt(new String(bArr, 0, i3)));
                        if (valueOf.intValue() > i) {
                            i = valueOf.intValue();
                        }
                    } catch (Throwable unused) {
                    }
                    fileInputStream.close();
                }
            } catch (Throwable unused2) {
                return -1;
            }
        }
        if (i == -1) {
            FileInputStream fileInputStream2 = new FileInputStream("/proc/cpuinfo");
            int a2 = a("cpu MHz", fileInputStream2) * 1000;
            if (a2 > i) {
                i = a2;
            }
            fileInputStream2.close();
        }
        return i;
    }

    public static String e(Context context) {
        try {
            cn.jiguang.ap.c a2 = cn.jiguang.ap.c.a(context);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("sim_slots", d(context));
            jSONObject.put("packagename", context.getPackageName());
            jSONObject.put("appkey", cn.jiguang.sdk.impl.b.i(context));
            jSONObject.put("platform", 0);
            jSONObject.put("apkversion", a2.a);
            jSONObject.put("systemversion", a2.b);
            jSONObject.put("modelnumber", a2.c);
            jSONObject.put("basebandversion", a2.d);
            jSONObject.put("buildnumber", a2.e);
            jSONObject.put("channel", a2.f);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("PushSDKVer", i.a(0));
            jSONObject2.put("StatisticSDKVer", i.a(1));
            jSONObject2.put("ShareSDKVer", i.a(2));
            jSONObject2.put("CoreSDKVer", i.a(3));
            jSONObject2.put("SspSDKVer", i.a(4));
            jSONObject2.put("VerificationSDKVer", i.a(5));
            jSONObject.put("sdkver", jSONObject2);
            jSONObject.put("installation", a2.g);
            jSONObject.put("resolution", a2.h);
            cn.jiguang.ah.b.a();
            jSONObject.put("business", cn.jiguang.ah.b.b());
            jSONObject.put("device_id_status", ((Integer) JCoreManager.onEvent(context, "JCOMMON", 33, null, null, new Object[0])).intValue());
            jSONObject.put("device_id", cn.jiguang.sdk.impl.b.b(context));
            jSONObject.put("android_id", a2.i);
            jSONObject.put("mac_address", cn.jiguang.ap.a.d(context, ""));
            jSONObject.put("serial_number", a2.j);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    private static void f() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            if (new File("/proc/cpuinfo").exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/proc/cpuinfo")));
                String str = null;
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    if (readLine.contains("Processor")) {
                        int indexOf = readLine.indexOf(CertificateUtil.DELIMITER);
                        if (indexOf >= 0 && indexOf < readLine.length() - 1) {
                            str = readLine.substring(indexOf + 1).trim();
                        }
                        if (str != null && !stringBuffer.toString().contains(str)) {
                            stringBuffer.append(str);
                        }
                    }
                    if (readLine.contains("Hardware")) {
                        try {
                            String trim = readLine.substring(readLine.indexOf(CertificateUtil.DELIMITER) + 1).trim();
                            if (!TextUtils.isEmpty(trim)) {
                                b = trim;
                            }
                        } catch (Throwable unused) {
                        }
                    }
                }
                bufferedReader.close();
            }
        } catch (Throwable unused2) {
        }
        a = stringBuffer.toString();
    }
}
