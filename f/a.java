package cn.jiguang.f;

import android.content.Context;
import android.os.Bundle;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(a aVar, Context context, String str) {
        boolean a = aVar.a(context, str);
        cn.jiguang.ai.a.c("JCommon", str + " isBusinessEnable:" + a);
        if (a) {
            aVar.c(context, str);
        }
        boolean b = aVar.b(context, str);
        cn.jiguang.ai.a.c("JCommon", str + " isReportEnable:" + b);
        if (b) {
            aVar.d(context, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(a aVar, Context context, String str, Bundle bundle) {
        aVar.a(str, bundle);
        boolean c = aVar.c();
        cn.jiguang.ai.a.c("JCommon", str + " isActionBundleEnable:" + c);
        if (c) {
            aVar.c(context, str);
            aVar.d(context, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(a aVar, Context context, String str, JSONObject jSONObject) {
        aVar.a(jSONObject);
        if (jSONObject.optInt("cmd") != 45) {
            boolean b = aVar.b();
            cn.jiguang.ai.a.c("JCommon", str + " isActionCommandEnable:" + b);
            if (b) {
                aVar.c(context, str);
                aVar.d(context, str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(a aVar, Context context, String str) {
        aVar.c(context, str);
        aVar.d(context, str);
    }

    private boolean e(Context context, String str) {
        boolean a = a();
        boolean b = b();
        boolean e = e(context);
        boolean z = a && b && e;
        cn.jiguang.ai.a.c("JCommon", str + " isActionEnable:" + z + ",actionUserEnable:" + a + ",actionCommandEnable:" + b + ",actionUidEnable:" + e);
        return z;
    }

    public final void a(Context context) {
        String d = d(context);
        cn.jiguang.ai.a.c("JCommon", "executeAction: [" + d + "] from heartBeat");
        if (e(context, d)) {
            cn.jiguang.sdk.impl.b.a("JCommon", new d(this, context, d));
        }
    }

    public final void a(Context context, Bundle bundle) {
        String d = d(context);
        cn.jiguang.ai.a.c("JCommon", "executeBundleAction: [" + d + "] from bundle");
        boolean a = a();
        cn.jiguang.ai.a.c("JCommon", d + " isActionUserEnable:" + a);
        if (a) {
            cn.jiguang.sdk.impl.b.a("JCommon", new b(this, context, d, bundle));
        }
    }

    public final void a(Context context, JSONObject jSONObject) {
        String d = d(context);
        cn.jiguang.ai.a.c("JCommon", "executeJsonAction: [" + d + "] from cmd");
        boolean a = a();
        cn.jiguang.ai.a.c("JCommon", d + " isActionUserEnable:" + a);
        if (a) {
            cn.jiguang.sdk.impl.b.a("JCommon", new e(this, context, d, jSONObject));
        }
    }

    protected void a(String str, Bundle bundle) {
        if (bundle != null) {
            cn.jiguang.ai.a.c("JCommon", str + " parseJson:" + bundle.toString());
        }
    }

    protected void a(JSONObject jSONObject) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a() {
        return true;
    }

    protected boolean a(Context context, String str) {
        return f.b(context, str);
    }

    public final void b(Context context) {
        String d = d(context);
        cn.jiguang.ai.a.c("JCommon", "executeCommandAction: [" + d + "] from cmd");
        if (e(context, d)) {
            cn.jiguang.sdk.impl.b.a("JCommon", new c(this, context, d));
        }
    }

    public final void b(Context context, JSONObject jSONObject) {
        String d = d(context);
        cn.jiguang.ai.a.c("JCommon", "executeCommandActionSingle: [" + d + "] from cmd");
        boolean a = a();
        cn.jiguang.ai.a.c("JCommon", d + " isActionUserEnable:" + a);
        if (a) {
            cn.jiguang.sdk.impl.b.a(d, new e(this, context, d, jSONObject));
        }
    }

    protected boolean b() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b(Context context, String str) {
        return f.b(context, str);
    }

    public final void c(Context context) {
        String d = d(context);
        cn.jiguang.ai.a.c("JCommon", "executeActionSingle: [" + d + "] from heartBeat");
        if (e(context, d)) {
            cn.jiguang.sdk.impl.b.a(d, new d(this, context, d));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(Context context, String str) {
        f.c(context, str);
    }

    protected boolean c() {
        return true;
    }

    protected abstract String d(Context context);

    /* JADX INFO: Access modifiers changed from: protected */
    public void d(Context context, String str) {
        f.e(context, str);
    }

    protected boolean e(Context context) {
        return cn.jiguang.sdk.impl.b.e(context) > 0;
    }

    public Object f(Context context) {
        return null;
    }
}
