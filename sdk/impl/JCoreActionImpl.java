package cn.jiguang.sdk.impl;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jiguang.ae.c;
import cn.jiguang.ah.d;
import cn.jiguang.ah.g;
import cn.jiguang.ah.i;
import cn.jiguang.ap.h;
import cn.jiguang.api.JCoreAction;
import cn.jiguang.api.JCoreManager;

/* loaded from: classes.dex */
public class JCoreActionImpl extends JCoreAction {
    private boolean a;

    private synchronized void a(Context context) {
        if (this.a) {
            return;
        }
        if (context == null) {
            return;
        }
        cn.jiguang.ai.a.c("JCoreActionImpl", "init jcore impl ,version:2.1.2");
        this.a = true;
        try {
            if (JCoreManager.onEvent(context, a.d, 32, "", null, new Object[0]) instanceof Bundle) {
                cn.jiguang.ai.a.c("JCoreActionImpl", "hb:" + a.b + ",google:false,internal:" + a.a);
                Bundle bundle = new Bundle();
                int i = !TextUtils.isEmpty("") ? 2 : 0;
                bundle.putString("name", "core");
                bundle.putInt("custom", i);
                bundle.putInt("dynamic", 0);
                d.a(context, "set_sdktype_info", bundle);
            }
        } catch (Throwable unused) {
        }
        String str = (String) c.a(context, cn.jiguang.ae.b.g());
        if (TextUtils.isEmpty(str) || str.startsWith("1.")) {
            b.o(context);
        }
        if (TextUtils.isEmpty(str) || !JCoreManager.SDK_VERSION.equals(str)) {
            c.a(context, cn.jiguang.ae.b.g().a((cn.jiguang.ae.b<String>) JCoreManager.SDK_VERSION));
        }
        String str2 = (String) c.a(context, cn.jiguang.ae.b.h());
        String i2 = b.i(context);
        cn.jiguang.ai.a.c("InitHelper", "appkey=" + i2 + " last=" + str2);
        if (h.a(str2) || "null".equals(str2) || !str2.equalsIgnoreCase(i2)) {
            c.a(context, cn.jiguang.ae.b.h().a((cn.jiguang.ae.b<String>) i2));
            cn.jiguang.ai.a.d("InitHelper", "We found the appKey is changed or register appkey is empty. Will re-register.");
            b.k(context);
        }
    }

    @Override // cn.jiguang.api.JCoreAction
    public void handleAction(Context context, String str, Bundle bundle) {
        String string;
        a(context);
        if (TextUtils.isEmpty(str)) {
            cn.jiguang.ai.a.h("JCoreActionImpl", "handleAction Failed,action is empty");
            return;
        }
        cn.jiguang.ai.a.c("JCoreActionImpl", "handleAction action:" + str);
        String string2 = bundle != null ? bundle.getString("sdk_type") : "";
        if (str.equals("a1")) {
            if (bundle != null) {
                try {
                    string = bundle.getString("report_data");
                } catch (Throwable th) {
                    cn.jiguang.ai.a.g("JCoreActionImpl", "report failed:" + th.getMessage());
                    return;
                }
            } else {
                string = null;
            }
            b.a(context, (Object) string);
        } else if (str.startsWith("tcp_")) {
            i.a().a(context, str, bundle);
        } else if (str.equals("a2")) {
            g.a().a(context, true);
        } else if (str.equals("a3")) {
            cn.jiguang.ah.b.a();
            cn.jiguang.ah.b.a(context, string2, bundle);
        } else if (str.equals("a4")) {
            b.a(context, bundle);
        }
    }
}
