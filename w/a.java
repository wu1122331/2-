package cn.jiguang.w;

import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import cn.jiguang.f.f;
import cn.jiguang.f.i;
import cn.jiguang.sdk.impl.b;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class a {
    public static void a(Context context, int i) {
        boolean z;
        boolean z2;
        String e = f.e(context);
        new a();
        boolean a = Build.VERSION.SDK_INT >= 24 ? a(context) : b(context);
        boolean z3 = true;
        if (!TextUtils.isEmpty(e)) {
            if (TextUtils.equals("ON", e)) {
                z = false;
                z2 = true;
            } else {
                z = !TextUtils.equals("OFF", e);
                z2 = false;
            }
            if (z) {
                cn.jiguang.ai.a.c("JNotificationState", "notification state do not changed");
                z3 = z;
            } else if (z2 == a) {
                z3 = false;
            }
        }
        cn.jiguang.ai.a.c("JNotificationState", "lastCacheNotificationState:" + e + ",currentNotificationSate:" + a + ",isNeedReport:" + z3 + ",triggerScene:" + i);
        if (!z3) {
            cn.jiguang.ai.a.c("JNotificationState", "do not need report notification state");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("notification_state", a);
            jSONObject.put("imei", cn.jiguang.ap.a.b(context, cn.jiguang.ap.a.b(context, "")));
            jSONObject.put("device_id", b.b(context));
            jSONObject.put("trigger_scene", i);
            i.a(context, jSONObject, "android_notification_state");
            i.a(context, (Object) jSONObject);
            f.n(context, a ? "ON" : "OFF");
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("JNotificationState", "report notification state failed, error:" + th.getMessage());
        }
    }

    private static boolean a(Context context) {
        try {
            return ((NotificationManager) context.getSystemService("notification")).areNotificationsEnabled();
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("JNotificationState", "invoke areNotificationsEnabled method failed, error:" + th.getMessage());
            return true;
        }
    }

    private static boolean b(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            String packageName = context.getApplicationContext().getPackageName();
            int i = applicationInfo.uid;
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            return ((Integer) cls.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class).invoke((AppOpsManager) context.getSystemService("appops"), Integer.valueOf(((Integer) cls.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), packageName)).intValue() == 0;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException unused) {
            return true;
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("JNotificationState", "getNotificationStateCommon failed, other error:" + th.getMessage());
            return true;
        }
    }
}
