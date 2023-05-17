package cn.jiguang.aq;

import android.text.TextUtils;
import cn.jiguang.api.JAction;
import cn.jiguang.api.JActionExtra;
import cn.jiguang.api.JCoreManager;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class a {
    private static volatile a d;
    public static HashMap<String, g> a = new HashMap<>();
    public static HashMap<String, JAction> b = new HashMap<>();
    public static HashMap<String, JActionExtra> c = new HashMap<>();
    private static final Object e = new Object();

    private a() {
    }

    public static JAction a(String str) {
        if (b.containsKey(str)) {
            return b.get(str);
        }
        return null;
    }

    public static a a() {
        if (d == null) {
            synchronized (e) {
                if (d == null) {
                    d = new a();
                }
            }
        }
        return d;
    }

    public static void a(String str, String str2) {
        d.a("DispacthManager", "addAction type:" + str + ",action:" + str2);
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        if (!a.containsKey(str)) {
            g gVar = new g();
            JCoreManager.addDispatchAction(str, g.class.getCanonicalName());
            a.put(str, gVar);
        }
        if (b.containsKey(str)) {
            return;
        }
        try {
            Object newInstance = Class.forName(str2).newInstance();
            if (newInstance instanceof JAction) {
                b.put(str, (JAction) newInstance);
            }
        } catch (Throwable th) {
            d.d("DispacthManager", "#unexcepted - instance " + str2 + " class failed:" + th);
        }
    }

    public static JActionExtra b(String str) {
        if (c.containsKey(str)) {
            return c.get(str);
        }
        return null;
    }

    public static void b(String str, String str2) {
        d.a("DispacthManager", "addActionExtra type:" + str + ",action:" + str2);
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        if (!a.containsKey(str)) {
            g gVar = new g();
            JCoreManager.addDispatchAction(str, g.class.getCanonicalName());
            a.put(str, gVar);
        }
        if (c.containsKey(str)) {
            return;
        }
        try {
            Object newInstance = Class.forName(str2).newInstance();
            if (newInstance instanceof JActionExtra) {
                c.put(str, (JActionExtra) newInstance);
            }
        } catch (Throwable th) {
            d.d("DispacthManager", "#unexcepted - instance " + str2 + " class failed:" + th);
        }
    }
}
