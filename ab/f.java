package cn.jiguang.ab;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.jiguang.api.JCoreManager;
import cn.jiguang.as.j;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public final class f {
    public static boolean a = false;

    private static Bundle a(Context context, int i, Bundle bundle) {
        try {
            Bundle bundle2 = new Bundle();
            if (i == 4096) {
                if (bundle != null) {
                    b.a(context, b.a(bundle, "arg1"));
                }
                return bundle2;
            } else if (i == 4098) {
                JCoreManager.init(context);
                return bundle2;
            } else {
                if (i == 36865 && JCoreManager.isInternal()) {
                    cn.jiguang.a.a.b();
                }
                return null;
            }
        } catch (Throwable th) {
            cn.jiguang.ac.d.h("JCoreHelper", "si e:" + th);
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x0115, code lost:
        if (r11 <= 0) goto L45;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object a(android.content.Context r10, java.lang.String r11, int r12, java.lang.String r13, android.os.Bundle r14, java.lang.Object... r15) {
        /*
            Method dump skipped, instructions count: 1792
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.ab.f.a(android.content.Context, java.lang.String, int, java.lang.String, android.os.Bundle, java.lang.Object[]):java.lang.Object");
    }

    public static String a(Context context) {
        return (String) cn.jiguang.e.b.b(context, cn.jiguang.e.a.l());
    }

    public static void a(Context context, Intent intent) {
        Method declaredMethod;
        Boolean bool;
        boolean z;
        cn.jiguang.ah.d.a(context, "get_receiver", intent);
        String action = intent.getAction();
        if (action == null) {
            cn.jiguang.ac.d.f("JCoreHelper", "onReceive empty action");
        } else if (action.equals("android.intent.action.USER_PRESENT")) {
            cn.jiguang.ac.d.d("JCoreHelper", "onReceiveandroid.intent.action.USER_PRESENT");
            cn.jiguang.a.a.a(context, true, 0L);
        } else if (action.equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE")) {
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            if (networkInfo == null) {
                cn.jiguang.ac.d.f("JCoreHelper", "Not found networkInfo");
                return;
            }
            cn.jiguang.ac.d.b("JCoreHelper", "Connection state changed to - " + networkInfo.toString());
            if (2 == networkInfo.getType() || 3 == networkInfo.getType()) {
                cn.jiguang.ac.d.b("JCoreHelper", "MMS or SUPL network state change, to do nothing!");
                return;
            }
            boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
            Bundle extras = intent.getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            if (booleanExtra) {
                cn.jiguang.ac.d.b("JCoreHelper", "No any network is connected");
                extras.putBoolean("connected", false);
            } else {
                try {
                    if (NetworkInfo.State.CONNECTED == networkInfo.getState()) {
                        cn.jiguang.ac.d.b("JCoreHelper", "Network is connected.");
                        extras.putBoolean("connected", true);
                    } else if (NetworkInfo.State.DISCONNECTED == networkInfo.getState()) {
                        cn.jiguang.ac.d.b("JCoreHelper", "Network is disconnected.");
                        extras.putBoolean("connected", false);
                    } else {
                        cn.jiguang.ac.d.b("JCoreHelper", "other network state - " + networkInfo.getState() + ". Do nothing.");
                    }
                } catch (Throwable unused) {
                    extras.putBoolean("connected", cn.jiguang.ap.a.c(context));
                }
            }
            cn.jiguang.a.a.a(context, "tcp_a15", extras);
        } else if (!action.equals("android.os.action.DEVICE_IDLE_MODE_CHANGED") && !action.equals("android.os.action.POWER_SAVE_MODE_CHANGED")) {
            if (action.equals("noti_open_proxy") && intent.getBooleanExtra("debug_notification", false)) {
                String stringExtra = intent.getStringExtra("toastText");
                if (TextUtils.isEmpty(stringExtra)) {
                    return;
                }
                Toast makeText = Toast.makeText(context, stringExtra, 0);
                try {
                    View view = makeText.getView();
                    if (view instanceof LinearLayout) {
                        View childAt = ((LinearLayout) view).getChildAt(0);
                        if (childAt instanceof TextView) {
                            TextView textView = (TextView) childAt;
                            if (!j.a(stringExtra)) {
                                textView.setText(stringExtra);
                            }
                            textView.setTextSize(13.0f);
                        }
                    }
                } catch (Exception unused2) {
                }
                makeText.show();
            }
        } else {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            if (powerManager != null) {
                try {
                    Class<?> cls = Class.forName("android.os.PowerManager");
                    if (cls != null) {
                        if (action.equals("android.os.action.DEVICE_IDLE_MODE_CHANGED")) {
                            Method declaredMethod2 = cls.getDeclaredMethod("isDeviceIdleMode", new Class[0]);
                            if (declaredMethod2 != null) {
                                bool = (Boolean) declaredMethod2.invoke(powerManager, new Object[0]);
                                z = bool.booleanValue();
                            }
                            z = true;
                        } else {
                            if (action.equals("android.os.action.POWER_SAVE_MODE_CHANGED") && (declaredMethod = cls.getDeclaredMethod("isPowerSaveMode", new Class[0])) != null) {
                                bool = (Boolean) declaredMethod.invoke(powerManager, new Object[0]);
                                z = bool.booleanValue();
                            }
                            z = true;
                        }
                        if (z) {
                            return;
                        }
                        cn.jiguang.ac.d.b("JCoreHelper", "doze or powersave mode exit.");
                        cn.jiguang.a.a.a(context, true, 0L);
                    }
                } catch (Throwable th) {
                    cn.jiguang.ac.d.h("JCoreHelper", "handle DEVICE_IDLE_MODE_CHANGED or POWER_SAVE_MODE_CHANGED fail:" + th);
                }
            }
        }
    }

    public static void a(Context context, String str, Bundle bundle) {
        cn.jiguang.a.a.b(context, str, bundle);
    }

    private static void a(Context context, String str, Bundle bundle, String str2) {
        if (bundle != null) {
            bundle.putString("sdk_type", str);
            cn.jiguang.a.a.a(context, str2, bundle);
        }
    }

    public static void a(Context context, String str, String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        cn.jiguang.ac.d.c("JCoreHelper", "runActionWithService action:" + str2);
        bundle.putString("sdk_type", str);
        bundle.putString("internal_action", str2);
        cn.jiguang.a.a.a(context, "a3", bundle);
    }

    public static void a(Context context, boolean z) {
        cn.jiguang.ac.d.b("JCoreHelper", "changeForegroudStat:" + z);
        a = z;
        Bundle bundle = new Bundle();
        bundle.putBoolean("foreground", a);
        cn.jiguang.a.a.a(context, "a4", bundle);
    }

    public static String b(Context context) {
        return b.a(context);
    }

    public static String c(Context context) {
        return b.b(context);
    }

    public static Object d(Context context) {
        return cn.jiguang.ah.d.a(context, "deviceinfo", null);
    }

    public static Pair<String, Integer> e(Context context) {
        return b.c(context);
    }
}
