package cn.jiguang.b;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import cn.jiguang.ab.f;
import cn.jiguang.ac.d;
import cn.jiguang.ad.k;
import cn.jiguang.as.e;
import cn.jiguang.e.c;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.facebook.internal.security.CertificateUtil;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class a implements Thread.UncaughtExceptionHandler {
    private static a b = new a();
    private static int c = 1048576;
    public boolean a;
    private Thread.UncaughtExceptionHandler d = null;

    private a() {
        this.a = true;
        this.a = ((Boolean) cn.jiguang.e.b.a(cn.jiguang.a.a.a(null), cn.jiguang.e.a.b())).booleanValue();
    }

    public static a a() {
        return b;
    }

    private static String a(Throwable th) {
        String th2 = th.toString();
        try {
            String[] split = th2.split(CertificateUtil.DELIMITER);
            if (split.length > 1) {
                for (int length = split.length - 1; length >= 0; length--) {
                    if (!split[length].endsWith("Exception") && !split[length].endsWith("Error")) {
                    }
                    return split[length];
                }
                return th2;
            }
            return th2;
        } catch (NullPointerException | PatternSyntaxException unused) {
            return th2;
        }
    }

    private JSONArray a(Context context, Throwable th) {
        String b2 = e.b(e.a(context, "jpush_uncaughtexception_file"));
        JSONArray jSONArray = null;
        int i = 0;
        if (!TextUtils.isEmpty(b2)) {
            try {
                JSONArray jSONArray2 = new JSONArray(b2);
                try {
                    i = b2.length();
                } catch (JSONException unused) {
                }
                jSONArray = jSONArray2;
            } catch (JSONException unused2) {
            }
        }
        return a(context, jSONArray, i, th);
    }

    private JSONArray a(Context context, JSONArray jSONArray, int i, Throwable th) {
        JSONObject jSONObject;
        long currentTimeMillis = System.currentTimeMillis() + c.c(context);
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String stringWriter2 = stringWriter.toString();
        if (jSONArray == null) {
            jSONArray = new JSONArray();
        }
        int i2 = 0;
        while (true) {
            jSONObject = null;
            try {
                if (i2 >= jSONArray.length()) {
                    break;
                }
                jSONObject = jSONArray.optJSONObject(i2);
                if (jSONObject != null && stringWriter2.equals(jSONObject.getString("stacktrace"))) {
                    jSONObject.put("count", jSONObject.getInt("count") + 1);
                    jSONObject.put("crashtime", currentTimeMillis);
                    break;
                }
                i2++;
            } catch (Throwable unused) {
            }
        }
        if (jSONObject == null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("crashtime", currentTimeMillis);
            jSONObject2.put("stacktrace", stringWriter2);
            jSONObject2.put("message", a(th));
            jSONObject2.put("count", 1);
            jSONObject2.put("networktype", cn.jiguang.ap.a.i(context));
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo != null) {
                String str = packageInfo.versionName == null ? "null" : packageInfo.versionName;
                String sb = new StringBuilder().append(packageInfo.versionCode).toString();
                jSONObject2.put("versionname", str);
                jSONObject2.put("versioncode", sb);
            }
            if (i + jSONObject2.toString().length() < c) {
                jSONArray.put(jSONObject2);
            } else {
                long j = -1;
                int i3 = 0;
                for (int i4 = 0; i4 < jSONArray.length(); i4++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i4);
                    if (optJSONObject != null) {
                        long optLong = optJSONObject.optLong("crashtime");
                        if (j == -1 || optLong < j) {
                            i3 = i4;
                            j = optLong;
                        }
                    }
                }
                jSONArray.put(i3, jSONObject2);
            }
        }
        return jSONArray;
    }

    public static void a(Context context) {
        if (context == null) {
            d.f("JPushCrashHandler", "Action - deleteCrashLog context is null");
        } else {
            e.a(e.a(context, "jpush_uncaughtexception_file"));
        }
    }

    private static JSONArray c(Context context) {
        String b2 = e.b(e.a(context, "jpush_uncaughtexception_file"));
        if (TextUtils.isEmpty(b2)) {
            return null;
        }
        try {
            return new JSONArray(b2);
        } catch (JSONException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject d(Context context) {
        JSONArray c2 = c(context);
        if (c2 == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("crashlogs", c2);
            jSONObject.put("network_type", cn.jiguang.ap.a.i(context));
            k.a(context, jSONObject, "crash_log");
            Object d = f.d(context);
            JSONObject jSONObject2 = d instanceof JSONObject ? (JSONObject) d : null;
            if (jSONObject2 != null && jSONObject2.length() > 0) {
                jSONObject.put(DeviceRequestsHelper.DEVICE_INFO_PARAM, jSONObject2);
            }
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    public final void b() {
        if (this.d == null) {
            this.d = Thread.getDefaultUncaughtExceptionHandler();
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public final void b(Context context) {
        if (context == null) {
            d.f("JPushCrashHandler", "Action - reportCrashLog context is null");
        } else if (c.a(context)) {
            try {
                new b(this).start();
            } catch (Throwable th) {
                d.h("JPushCrashHandler", "report crash e:" + th);
            }
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        File a;
        if (this.a) {
            d.a("JPushCrashHandler", "enable crash report");
            if (this.a) {
                Context a2 = cn.jiguang.a.a.a(null);
                if (a2 != null) {
                    JSONArray a3 = a(a2, th);
                    a(a2);
                    String jSONArray = a3 != null ? a3.toString() : null;
                    if (!TextUtils.isEmpty(jSONArray) && (a = e.a(a2, "jpush_uncaughtexception_file")) != null) {
                        e.a(a, jSONArray);
                    }
                } else {
                    d.h("JPushCrashHandler", "handleException failed: context is null");
                }
            }
            try {
                b bVar = new b(this);
                bVar.start();
                bVar.join(2000L);
            } catch (InterruptedException unused) {
            } catch (Throwable th2) {
                d.h("JPushCrashHandler", "report crash e:" + th2);
            }
        } else {
            d.a("JPushCrashHandler", "disable crash report");
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.d;
        if (uncaughtExceptionHandler == this || uncaughtExceptionHandler == null) {
            return;
        }
        uncaughtExceptionHandler.uncaughtException(thread, th);
    }
}
