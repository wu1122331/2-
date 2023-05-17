package cn.jiguang.api;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import cn.jiguang.ab.a;
import cn.jiguang.ab.d;
import cn.jiguang.ab.f;
import cn.jiguang.ab.g;
import cn.jiguang.sdk.impl.b;
import com.facebook.internal.ServerProtocol;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class JCoreManager {
    public static final boolean IG = false;
    public static final boolean SD = false;
    public static final String SDK_NAME = "";
    public static final String SDK_VERSION = "2.1.2";
    private static final String TAG = "JCoreManager";
    private static final AtomicBoolean isInited = new AtomicBoolean();

    public static void addDispatchAction(String str, String str2) {
        a.a();
        a.a(str, str2);
    }

    public static void changeLiveStatus(boolean z) {
        cn.jiguang.a.a.f = true;
    }

    public static Context getAppContext(Context context) {
        return cn.jiguang.a.a.a(context);
    }

    @Deprecated
    public static boolean getConnectionState(Context context) {
        try {
            Bundle a = g.a().a(context, "INTERNAL_API", "isTcpLoggedIn", null, d.a(context));
            return (a == null || !a.containsKey(ServerProtocol.DIALOG_PARAM_STATE)) ? b.b() : a.getBoolean(ServerProtocol.DIALOG_PARAM_STATE);
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean getDebugMode() {
        return cn.jiguang.a.a.c;
    }

    public static void init(Context context) {
        Context a = cn.jiguang.a.a.a(context);
        if (a == null) {
            return;
        }
        AtomicBoolean atomicBoolean = isInited;
        if (atomicBoolean.get()) {
            return;
        }
        atomicBoolean.set(true);
        cn.jiguang.a.a.d(a);
        cn.jiguang.a.a.a(a, "tcp_a1", (Bundle) null);
        if (((Long) cn.jiguang.e.b.a(a, cn.jiguang.e.a.w())).longValue() <= 0) {
            cn.jiguang.e.b.a(a, cn.jiguang.e.a.w().a((cn.jiguang.e.a<Long>) Long.valueOf(System.currentTimeMillis())));
        }
    }

    public static void initCrashHandler(Context context) {
        cn.jiguang.b.a a = cn.jiguang.b.a.a();
        if (a.a) {
            return;
        }
        a.a = true;
        cn.jiguang.e.b.a(cn.jiguang.a.a.a(context), cn.jiguang.e.a.b().a((cn.jiguang.e.a<Boolean>) true));
    }

    public static boolean isInternal() {
        return false;
    }

    public static boolean isTestEnv() {
        return cn.jiguang.a.a.a();
    }

    public static Object onEvent(Context context, String str, int i, String str2, Bundle bundle, Object... objArr) {
        init(context);
        return f.a(context, str, i, str2, bundle, objArr);
    }

    public static void requestPermission(Context context) {
        String str;
        if (context == null) {
            str = "[requestPermission] context was null";
        } else if (context instanceof Activity) {
            if (Build.VERSION.SDK_INT < 23 || context.getApplicationInfo().targetSdkVersion < 23) {
                return;
            }
            try {
                LinkedList linkedList = new LinkedList();
                linkedList.add("android.permission.WRITE_EXTERNAL_STORAGE");
                linkedList.add("android.permission.READ_EXTERNAL_STORAGE");
                linkedList.add("android.permission.ACCESS_FINE_LOCATION");
                linkedList.add("android.permission.READ_PHONE_STATE");
                if (cn.jiguang.a.a.a(context, false, "will request background location permission")) {
                    linkedList.add("android.permission.ACCESS_BACKGROUND_LOCATION");
                }
                List<String> a = cn.jiguang.as.a.a(context, linkedList);
                if (a != null && !a.isEmpty()) {
                    cn.jiguang.ac.d.a(TAG, "lackPermissions:" + a);
                    Class.forName("android.app.Activity").getDeclaredMethod("requestPermissions", String[].class, Integer.TYPE).invoke(context, a.toArray(new String[a.size()]), 1);
                    return;
                }
                return;
            } catch (Exception e) {
                cn.jiguang.ac.d.g(TAG, "#unexcepted - requestPermission e:" + e);
                return;
            }
        } else {
            str = "[requestPermission] context must instanceof Activity";
        }
        cn.jiguang.ac.d.g(TAG, str);
    }

    public static void setAnalysisAction(JAnalyticsAction jAnalyticsAction) {
        cn.jiguang.a.a.b = jAnalyticsAction;
    }

    public static void setDebugMode(boolean z) {
        cn.jiguang.a.a.c = z;
    }

    public static void setSDKConfigs(Context context, Bundle bundle) {
        f.a(context, cn.jiguang.a.a.d, 55, null, bundle, new Object[0]);
    }

    public static void stopCrashHandler(Context context) {
        cn.jiguang.b.a a = cn.jiguang.b.a.a();
        if (a.a) {
            a.a = false;
            cn.jiguang.e.b.a(cn.jiguang.a.a.a(context), cn.jiguang.e.a.b().a((cn.jiguang.e.a<Boolean>) false));
        }
    }
}
