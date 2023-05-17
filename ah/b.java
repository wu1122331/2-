package cn.jiguang.ah;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jiguang.api.JCoreManager;
import cn.jiguang.api.JDispatchAction;
import cn.jpush.android.api.JPushInterface;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public final class b {
    private static volatile b b;
    private static final Object c = new Object();
    public static HashMap<String, JDispatchAction> a = new HashMap<>();

    private b() {
        Object a2 = cn.jiguang.sdk.impl.b.a();
        if (a2 instanceof HashMap) {
            HashMap<String, String> hashMap = (HashMap) a2;
            cn.jiguang.ai.a.c("DispatchActionManager", "actiom map size:" + hashMap.size());
            a(hashMap);
            b(cn.jiguang.sdk.impl.a.d, e.class.getCanonicalName());
        }
    }

    public static byte a(Context context) {
        for (Map.Entry<String, JDispatchAction> entry : a.entrySet()) {
            JDispatchAction value = entry.getValue();
            if (value != null) {
                Object beforLogin = value.beforLogin(context, entry.getKey(), 23, "platformtype");
                if (beforLogin instanceof Byte) {
                    return ((Byte) beforLogin).byteValue();
                }
            }
        }
        return (byte) 0;
    }

    public static b a() {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new b();
                }
            }
        }
        return b;
    }

    public static String a(String str, String str2) {
        StringBuilder append;
        String str3;
        JDispatchAction jDispatchAction = a.get(str);
        if (jDispatchAction != null) {
            str3 = jDispatchAction.getSdkVersion(str);
            if (!TextUtils.isEmpty(str3)) {
                return str3;
            }
            append = new StringBuilder().append(str).append(" sdk action sdkversion:");
        } else {
            append = new StringBuilder().append(str);
            str3 = " sdk action is null";
        }
        cn.jiguang.ai.a.a("DispatchActionManager", append.append(str3).toString());
        return str2;
    }

    public static void a(Context context, cn.jiguang.ak.c cVar, ByteBuffer byteBuffer) {
        if (cVar == null) {
            cn.jiguang.ai.a.g("DispatchActionManager", "Action - dispatchMessage unexcepted - head was null");
            return;
        }
        c b2 = o.a().b(cVar.e);
        if (b2 != null) {
            cn.jiguang.ai.a.c("DispatchActionManager", "dispacth msg with reuqest :" + b2);
            JDispatchAction jDispatchAction = a.get(b2.c);
            if (jDispatchAction != null) {
                jDispatchAction.dispatchMessage(context, b2.c, cVar.c, cVar.b, cVar.e, b2.b, byteBuffer);
            }
            Bundle bundle = new Bundle();
            bundle.putLong("rid", cVar.e);
            i.a().a(context, "tcp_a7", bundle);
            return;
        }
        for (Map.Entry<String, JDispatchAction> entry : a.entrySet()) {
            JDispatchAction value = entry.getValue();
            if (value != null && value.isSupportedCMD(entry.getKey(), cVar.c)) {
                value.dispatchMessage(context, entry.getKey(), cVar.c, cVar.b, cVar.e, -1L, byteBuffer);
            }
        }
    }

    public static void a(Context context, String str, long j, int i) {
        if (TextUtils.isEmpty(str) || !str.equals(cn.jiguang.sdk.impl.a.d)) {
            JDispatchAction jDispatchAction = a.get(str);
            if (jDispatchAction != null) {
                jDispatchAction.dispatchTimeOutMessage(context, str, j, i);
            } else {
                cn.jiguang.ai.a.g("DispatchActionManager", "not found dispatch action by sdktype:" + str);
            }
        } else if (i == 26) {
            q.a().a(j);
        } else if (i == 30 || i == 32) {
            cn.jiguang.am.a.a().a(context, i);
        }
    }

    public static void a(Context context, String str, Bundle bundle) {
        if (bundle == null) {
            cn.jiguang.ai.a.h("DispatchActionManager", "run action bundle is null");
        } else if (TextUtils.isEmpty(str)) {
            cn.jiguang.ai.a.h("DispatchActionManager", "run action sdktype is empty");
        } else {
            if (cn.jiguang.sdk.impl.a.d.contains(str)) {
                str = cn.jiguang.sdk.impl.a.d;
            }
            JDispatchAction jDispatchAction = a.get(str);
            if (jDispatchAction == null) {
                cn.jiguang.ai.a.h("DispatchActionManager", "dispacth action is null by sdktype:" + str);
            } else {
                jDispatchAction.onActionRun(context, str, bundle.getString("internal_action"), bundle);
            }
        }
    }

    private void a(HashMap<String, String> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            cn.jiguang.ai.a.g("DispatchActionManager", "init map is empty");
            return;
        }
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            b(entry.getKey(), entry.getValue());
        }
    }

    public static boolean a(int i) {
        for (Map.Entry<String, JDispatchAction> entry : a.entrySet()) {
            JDispatchAction value = entry.getValue();
            if (value != null) {
                try {
                    cn.jiguang.ai.a.d("DispatchActionManager", "isAllowAction actionType:0,sdktype:" + entry.getKey() + ",action:" + value.checkAction(entry.getKey(), 0));
                    if (!value.checkAction(entry.getKey(), 0)) {
                        return false;
                    }
                } catch (Throwable th) {
                    cn.jiguang.ai.a.g("DispatchActionManager", "isAllowAction error:" + th.getMessage());
                }
            }
        }
        return true;
    }

    public static String b(int i) {
        for (Map.Entry<String, JDispatchAction> entry : a.entrySet()) {
            JDispatchAction value = entry.getValue();
            if (value != null && value.getRegPriority(entry.getKey()) == i) {
                return value.getSdkVersion(entry.getKey());
            }
        }
        return "";
    }

    public static String b(Context context) {
        for (Map.Entry<String, JDispatchAction> entry : a.entrySet()) {
            JDispatchAction value = entry.getValue();
            if (value != null) {
                Object beforLogin = value.beforLogin(context, entry.getKey(), 23, "platformregid");
                if (beforLogin instanceof String) {
                    return (String) beforLogin;
                }
            }
        }
        return "";
    }

    public static short b() {
        short regFlag;
        short s = 0;
        for (Map.Entry<String, JDispatchAction> entry : a.entrySet()) {
            JDispatchAction value = entry.getValue();
            if (value != null && (regFlag = value.getRegFlag(entry.getKey())) != 0) {
                s = (short) (s | regFlag);
            }
        }
        return s;
    }

    private static void b(String str, String str2) {
        cn.jiguang.ai.a.c("DispatchActionManager", "addAction type:" + str + ",action:" + str2);
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        if (a.containsKey(str)) {
            cn.jiguang.ai.a.c("DispatchActionManager", "has same type action");
            return;
        }
        try {
            Object newInstance = Class.forName(str2).newInstance();
            if (newInstance instanceof JDispatchAction) {
                a.put(str, (JDispatchAction) newInstance);
            } else {
                cn.jiguang.ai.a.g("DispatchActionManager", "this action is not a JDispatchAction,please check and extends JDispatchAction");
            }
        } catch (Throwable th) {
            cn.jiguang.ai.a.h("DispatchActionManager", "#unexcepted - instance " + str2 + " class failed:" + th);
        }
    }

    public static short c() {
        short loginFlag;
        short s = 0;
        for (Map.Entry<String, JDispatchAction> entry : a.entrySet()) {
            JDispatchAction value = entry.getValue();
            if (value != null && (loginFlag = value.getLoginFlag(entry.getKey())) != 0) {
                s = (short) (s | loginFlag);
            }
        }
        return s;
    }

    public static String d() {
        StringBuilder append;
        String str;
        JDispatchAction value;
        short s = 3;
        for (Map.Entry<String, JDispatchAction> entry : a.entrySet()) {
            short regPriority = entry.getValue().getRegPriority(entry.getKey());
            if (s < regPriority) {
                s = regPriority;
            }
        }
        cn.jiguang.ai.a.c("DispatchActionManager", "max reg priority:" + ((int) s));
        String str2 = "";
        for (int i = 0; i <= s; i++) {
            if (i == 3) {
                append = new StringBuilder().append(str2);
                str = "2.1.2|";
            } else {
                Iterator<Map.Entry<String, JDispatchAction>> it = a.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry<String, JDispatchAction> next = it.next();
                    if (next.getValue().getRegPriority(next.getKey()) == i) {
                        str2 = str2 + value.getSdkVersion(next.getKey());
                        break;
                    }
                }
                append = new StringBuilder().append(str2);
                str = "|";
            }
            str2 = append.append(str).toString();
        }
        return str2.substring(0, str2.length() - 1);
    }

    public static String e() {
        JDispatchAction value;
        String str = cn.jiguang.ap.a.a(JCoreManager.SDK_VERSION) + "|";
        short s = 0;
        for (Map.Entry<String, JDispatchAction> entry : a.entrySet()) {
            short logPriority = entry.getValue().getLogPriority(entry.getKey());
            if (s < logPriority) {
                s = logPriority;
            }
        }
        cn.jiguang.ai.a.c("DispatchActionManager", "max login priority:" + ((int) s));
        for (int i = 1; i <= s; i++) {
            Iterator<Map.Entry<String, JDispatchAction>> it = a.entrySet().iterator();
            while (true) {
                if (it.hasNext()) {
                    Map.Entry<String, JDispatchAction> next = it.next();
                    if (next.getValue().getLogPriority(next.getKey()) == i) {
                        str = str + cn.jiguang.ap.a.a(value.getSdkVersion(next.getKey()));
                        break;
                    }
                }
            }
            str = str + "|";
        }
        return str.substring(0, str.length() - 1);
    }

    public final void a(Context context, int i, int i2, String str) {
        for (Map.Entry<String, JDispatchAction> entry : a.entrySet()) {
            JDispatchAction value = entry.getValue();
            if (value != null) {
                value.onEvent(context, entry.getKey(), i, i2, str);
            }
        }
        Intent intent = null;
        try {
            if (i == 0 && i2 == 0) {
                intent = new Intent(JPushInterface.ACTION_REGISTRATION_ID);
                intent.putExtra(JPushInterface.EXTRA_REGISTRATION_ID, str);
            } else if (i == -1 || i == 1) {
                intent = new Intent(JPushInterface.ACTION_CONNECTION_CHANGE);
                if (i == -1) {
                    intent.putExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                } else {
                    intent.putExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, true);
                }
            }
            if (intent != null) {
                String packageName = context.getPackageName();
                intent.addCategory(packageName);
                intent.setPackage(packageName);
                cn.jiguang.ap.a.a(context, intent);
            }
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("DispatchActionManager", "sendToOldPushUser failed:" + th.getMessage());
        }
    }
}
