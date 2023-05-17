package cn.jiguang.u;

import android.content.Context;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class c implements Callable<cn.jiguang.v.b> {
    final /* synthetic */ a a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(a aVar) {
        this.a = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* renamed from: a */
    public cn.jiguang.v.b call() {
        Context context;
        try {
            context = this.a.a;
            f a = f.a(context);
            a.c();
            for (int i = 0; i < 30; i++) {
                if (a.b()) {
                    return a.a();
                }
                Thread.sleep(1000L);
            }
            return null;
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("JLocation", "JLocationGpsInfo call failed:" + th.getMessage());
            return null;
        }
    }
}
