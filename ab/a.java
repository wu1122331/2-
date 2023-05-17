package cn.jiguang.ab;

import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.api.JCoreManager;
import cn.jiguang.api.JDispatchAction;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class a {
    private static volatile a a;
    private static final Object b = new Object();
    private static HashMap<String, JDispatchAction> c = new HashMap<>();
    private static HashMap<String, String> d = new HashMap<>();

    public static a a() {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    public static void a(Context context, String str, Object obj) {
        cn.jiguang.ac.d.a("ActionManager", "onSended type:" + str + ",actionMap size:" + c.size());
        if (TextUtils.isEmpty(str)) {
            for (Map.Entry<String, JDispatchAction> entry : c.entrySet()) {
                entry.getValue().handleMessage(context, entry.getKey(), obj);
            }
            return;
        }
        JDispatchAction jDispatchAction = c.get(str);
        if (jDispatchAction != null) {
            jDispatchAction.handleMessage(context, str, obj);
        }
    }

    public static void a(String str, String str2) {
        cn.jiguang.ac.d.b("ActionManager", "addAction type:" + str + ",action:" + str2);
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        if (c.containsKey(str)) {
            cn.jiguang.ac.d.b("ActionManager", "has same type action");
            return;
        }
        try {
            Object newInstance = Class.forName(str2).newInstance();
            if (!(newInstance instanceof JDispatchAction)) {
                cn.jiguang.ac.d.f("ActionManager", "this action is not a JDispatchAction,please check and extends JDispatchAction");
                return;
            }
            d.put(str, str2);
            c.put(str, (JDispatchAction) newInstance);
        } catch (Throwable th) {
            cn.jiguang.ac.d.g("ActionManager", "#unexcepted - instance " + str2 + " class failed:" + th);
        }
    }

    public static boolean a(JSONObject jSONObject) {
        try {
            jSONObject.put("core_sdk_ver", JCoreManager.SDK_VERSION);
            for (Map.Entry<String, JDispatchAction> entry : c.entrySet()) {
                JDispatchAction value = entry.getValue();
                jSONObject.put(value.getReportVersionKey(entry.getKey()), value.getSdkVersion(entry.getKey()));
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static HashMap<String, String> b() {
        return d;
    }
}
