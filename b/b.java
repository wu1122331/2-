package cn.jiguang.b;

import android.content.Context;
import cn.jiguang.ac.d;
import cn.jiguang.ad.k;
import cn.jiguang.api.ReportCallBack;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class b extends Thread implements ReportCallBack {
    final /* synthetic */ a a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(a aVar) {
        this.a = aVar;
    }

    @Override // cn.jiguang.api.ReportCallBack
    public final void onFinish(int i) {
        d.f("JPushCrashHandler", "ReportDirect finish : " + i);
        if (i == 0) {
            a.a(cn.jiguang.a.a.a(null));
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        JSONObject d;
        try {
            Context a = cn.jiguang.a.a.a(null);
            if (a == null) {
                d.f("JPushCrashHandler", "ReportDirect context is null");
                return;
            }
            a aVar = this.a;
            d = a.d(a);
            if (d != null) {
                k.a(a, d, this);
            }
        } catch (Throwable th) {
            d.h("JPushCrashHandler", "run report crash e:" + th);
        }
    }
}
