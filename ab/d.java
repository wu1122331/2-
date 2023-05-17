package cn.jiguang.ab;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jiguang.api.JCoreAction;
import cn.jiguang.sdk.impl.JCoreActionImpl;
import cn.jpush.android.service.JCommonService;
import com.facebook.share.internal.ShareConstants;
import java.util.List;

/* loaded from: classes.dex */
public final class d {
    private static volatile d c;
    private static String d;
    private static String e;
    private static final Object b = new Object();
    public static JCoreAction a = new JCoreActionImpl();

    public static d a() {
        if (c == null) {
            synchronized (b) {
                if (c == null) {
                    c = new d();
                }
            }
        }
        return c;
    }

    public static String a(Context context) {
        String str = e;
        if (str != null) {
            return str;
        }
        String b2 = b(context);
        if (TextUtils.isEmpty(b2)) {
            e = "";
            return "";
        }
        e = cn.jiguang.as.a.a(context, b2);
        cn.jiguang.ac.d.b("JCommonServiceHelper", "user serviceProcess is:" + e);
        return e;
    }

    public static void a(Context context, String str, Bundle bundle) {
        try {
            cn.jiguang.ac.d.b("JCommonServiceHelper", "onAction action:" + str + " bundle:" + (bundle == null ? "null" : bundle.toString()));
            String b2 = b(context);
            if (TextUtils.isEmpty(b2)) {
                cn.jiguang.a.a.b(context, str, bundle);
            } else {
                g.a().a(context, b2, str, bundle);
            }
        } catch (Throwable th) {
            cn.jiguang.ac.d.b("JCommonServiceHelper", "onAction failed", th);
        }
    }

    public static String b(Context context) {
        ComponentInfo a2;
        String str;
        try {
            str = d;
        } catch (Throwable th) {
            cn.jiguang.ac.d.b("JCommonServiceHelper", "getUserServiceClass failed:" + th);
        }
        if (str != null) {
            return str;
        }
        Intent intent = new Intent();
        intent.setAction("cn.jiguang.user.service.action");
        intent.setPackage(context.getPackageName());
        List<String> a3 = cn.jiguang.as.a.a(context, intent, "");
        if (a3.size() > 0 && JCommonService.class.isAssignableFrom(Class.forName(a3.get(0)))) {
            d = a3.get(0);
            cn.jiguang.ac.d.e("JCommonServiceHelper", "found userServiceClass :" + d + " by getCommonServiceNames");
        }
        if (TextUtils.isEmpty(d) && (a2 = cn.jiguang.ap.a.a(context, context.getPackageName(), JCommonService.class)) != null) {
            d = a2.name;
            cn.jiguang.ac.d.e("JCommonServiceHelper", "found userServiceClass :" + d + " by getComponentInfo");
        }
        if (TextUtils.isEmpty(d)) {
            d = "";
        }
        return d;
    }

    public final void b(Context context, String str, Bundle bundle) {
        try {
            cn.jiguang.ac.d.d("JCommonServiceHelper", "callAction action:" + str + " bundle:" + (bundle == null ? "null" : bundle.toString()));
            cn.jiguang.ar.a.a(ShareConstants.ACTION, new e(this, cn.jiguang.a.a.a(context), str, bundle));
        } catch (Throwable th) {
            cn.jiguang.ac.d.b("JCommonServiceHelper", "callAction failed", th);
        }
    }
}
