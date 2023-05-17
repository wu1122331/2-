package cn.jiguang.sdk.impl;

import android.content.Context;
import android.os.Bundle;
import cn.jiguang.ah.i;
import cn.jiguang.ah.l;
import cn.jiguang.aj.c;
import cn.jiguang.api.JCoreManager;
import cn.jiguang.api.ReportCallBack;
import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;
import java.io.File;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class b {
    public static boolean a = false;

    public static long a(Context context, long j) {
        Object onEvent = JCoreManager.onEvent(context, null, 19, null, null, Long.valueOf(j));
        return onEvent instanceof Long ? ((Long) onEvent).longValue() : j / 1000;
    }

    public static Object a() {
        return JCoreManager.onEvent(null, a.d, 43, null, null, new Object[0]);
    }

    public static String a(Context context) {
        Object onEvent = JCoreManager.onEvent(context, null, 5, null, null, new Object[0]);
        return onEvent instanceof String ? (String) onEvent : "";
    }

    public static void a(Context context, int i) {
        JCoreManager.onEvent(context, a.d, 57, null, null, Integer.valueOf(i));
    }

    public static void a(Context context, long j, String str, String str2) {
        JCoreManager.onEvent(context, a.d, 35, null, null, Long.valueOf(j), str, str2);
    }

    public static void a(Context context, Bundle bundle) {
        if (bundle != null) {
            a = bundle.getBoolean("foreground");
            cn.jiguang.ai.a.c("JCoreManagerInternal", "changeForeGroundStat:" + a);
            if (a) {
                cn.jiguang.am.a.a().b(context);
            }
        }
    }

    public static void a(Context context, Object obj) {
        JCoreManager.onEvent(context, a.d, 39, null, null, obj);
    }

    public static void a(Context context, String str) {
        JCoreManager.onEvent(context, a.d, 36, null, null, str);
    }

    public static void a(Context context, String str, int i, int i2, long j, long j2, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putInt("cmd", i);
        bundle.putInt("ver", 0);
        bundle.putLong("rid", j);
        bundle.putLong("timeout", j2);
        bundle.putByteArray(SDKConstants.PARAM_A2U_BODY, bArr);
        JCoreManager.onEvent(context, str, 17, null, bundle, new Object[0]);
    }

    public static void a(Context context, String str, long j) {
        JCoreManager.onEvent(context, a.d, 44, null, null, str, Long.valueOf(j));
    }

    public static void a(Context context, JSONObject jSONObject, byte[] bArr, int i, File file, Set<String> set, ReportCallBack reportCallBack) {
        l.a();
        l.a().a(context, l.a(context, jSONObject, bArr, i, file, set, reportCallBack));
    }

    public static void a(Context context, boolean z, long j) {
        JCoreManager.onEvent(context, a.d, 40, null, null, false, 0L);
    }

    public static void a(Bundle bundle) {
        cn.jiguang.ai.a.c("JCoreManagerInternal", "setSDKConfigs");
        if (bundle == null || bundle.isEmpty()) {
            return;
        }
        int i = 86400;
        try {
            int i2 = bundle.getInt("heartbeat_interval", a.b);
            if (i2 < 30) {
                i2 = 30;
            } else if (i2 > 86400) {
                i2 = 86400;
            }
            a.b = i2;
            cn.jiguang.ai.a.c("JCoreManagerInternal", "set heartbeat interval=" + i2);
        } catch (Throwable unused) {
        }
        try {
            int i3 = bundle.getInt("alarm_delay", a.c);
            if (i3 <= 86400) {
                i = i3;
            }
            if (i < a.b + 5) {
                i = a.b + 5;
            }
            a.c = i;
            cn.jiguang.ai.a.c("JCoreManagerInternal", "set alarm delay=" + i);
        } catch (Throwable unused2) {
        }
        try {
            c.a(bundle.getInt("ipv_config", -1));
            byte b = (byte) bundle.getInt("tcp_algorithm", -1);
            if (b >= 0) {
                a.e = b;
                cn.jiguang.ai.a.c("JCoreManagerInternal", "set tcp algorithm=" + ((int) b));
            }
        } catch (Throwable unused3) {
        }
    }

    public static void a(Runnable runnable) {
        JCoreManager.onEvent(null, null, 12, null, null, runnable);
    }

    public static void a(String str) {
        JCoreManager.onEvent(null, null, 13, str, null, new Object[0]);
    }

    public static void a(String str, Runnable runnable) {
        JCoreManager.onEvent(null, null, 11, str, null, runnable);
    }

    public static void a(boolean z) {
        JCoreManager.changeLiveStatus(z);
    }

    public static boolean a(Context context, boolean z, String str) {
        Object onEvent = JCoreManager.onEvent(context, null, 60, null, null, Boolean.valueOf(z), str);
        if (onEvent instanceof Boolean) {
            return ((Boolean) onEvent).booleanValue();
        }
        return false;
    }

    public static String b(Context context) {
        Object onEvent = JCoreManager.onEvent(context, null, 8, null, null, new Object[0]);
        return onEvent instanceof String ? (String) onEvent : "";
    }

    public static void b(Context context, int i) {
        JCoreManager.onEvent(context, a.d, 51, "", null, Integer.valueOf(i));
    }

    public static void b(Context context, long j) {
        if (j > 0) {
            JCoreManager.onEvent(context, a.d, 37, null, null, Long.valueOf(j));
        }
    }

    public static void b(Context context, String str, int i, int i2, long j, long j2, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putInt("cmd", 4);
        bundle.putInt("ver", 2);
        bundle.putLong("rid", j);
        bundle.putLong("timeout", 0L);
        bundle.putByteArray(SDKConstants.PARAM_A2U_BODY, bArr);
        bundle.putLong("uid", j2);
        JCoreManager.onEvent(context, str, 59, null, bundle, new Object[0]);
    }

    public static boolean b() {
        return i.a().d();
    }

    public static int c(Context context) {
        Object onEvent = JCoreManager.onEvent(context, null, 33, null, null, new Object[0]);
        if (onEvent instanceof Integer) {
            return ((Integer) onEvent).intValue();
        }
        return 1;
    }

    public static String d(Context context) {
        Object onEvent = JCoreManager.onEvent(context, null, 6, null, null, new Object[0]);
        return onEvent instanceof String ? (String) onEvent : "";
    }

    public static long e(Context context) {
        Object onEvent = JCoreManager.onEvent(context, null, 20, null, null, new Object[0]);
        if (onEvent instanceof Long) {
            return ((Long) onEvent).longValue();
        }
        return 0L;
    }

    public static String f(Context context) {
        Object onEvent = JCoreManager.onEvent(context, null, 34, null, null, new Object[0]);
        return onEvent instanceof String ? (String) onEvent : "";
    }

    public static String g(Context context) {
        Object onEvent = JCoreManager.onEvent(context, null, 4, null, null, new Object[0]);
        return onEvent instanceof String ? (String) onEvent : "";
    }

    public static boolean h(Context context) {
        Object onEvent = JCoreManager.onEvent(context, null, 21, null, null, new Object[0]);
        if (onEvent instanceof Boolean) {
            return ((Boolean) onEvent).booleanValue();
        }
        return false;
    }

    public static String i(Context context) {
        Object onEvent = JCoreManager.onEvent(context, null, 7, null, null, new Object[0]);
        return onEvent instanceof String ? (String) onEvent : "";
    }

    public static long j(Context context) {
        Object onEvent = JCoreManager.onEvent(context, null, 19, null, null, new Object[0]);
        return onEvent instanceof Long ? ((Long) onEvent).longValue() : System.currentTimeMillis() / 1000;
    }

    public static void k(Context context) {
        JCoreManager.onEvent(context, a.d, 38, null, null, new Object[0]);
    }

    public static Map l(Context context) {
        Object onEvent = JCoreManager.onEvent(context, a.d, 45, null, null, new Object[0]);
        if (onEvent instanceof Map) {
            return (Map) onEvent;
        }
        return null;
    }

    public static int m(Context context) {
        Object onEvent = JCoreManager.onEvent(context, a.d, 47, null, null, new Object[0]);
        if (onEvent instanceof Integer) {
            return ((Integer) onEvent).intValue();
        }
        return 0;
    }

    public static int n(Context context) {
        Object onEvent = JCoreManager.onEvent(context, a.d, 46, null, null, new Object[0]);
        if (onEvent instanceof Integer) {
            return ((Integer) onEvent).intValue();
        }
        return -1;
    }

    public static void o(Context context) {
        JCoreManager.onEvent(context, a.d, 48, null, null, new Object[0]);
    }

    public static void p(Context context) {
        cn.jiguang.ah.a.a(context);
    }

    public static boolean q(Context context) {
        Object onEvent = JCoreManager.onEvent(context, "", 53, null, null, new Object[0]);
        if (onEvent instanceof Boolean) {
            return ((Boolean) onEvent).booleanValue();
        }
        return true;
    }

    public static boolean r(Context context) {
        Object onEvent = JCoreManager.onEvent(context, null, 58, null, null, new Object[0]);
        if (onEvent instanceof Boolean) {
            return ((Boolean) onEvent).booleanValue();
        }
        return true;
    }
}
