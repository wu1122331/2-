package cn.jiguang.u;

import android.content.Context;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class b implements Callable<cn.jiguang.v.a> {
    final /* synthetic */ a a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(a aVar) {
        this.a = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* renamed from: a */
    public cn.jiguang.v.a call() {
        Context context;
        try {
            context = this.a.a;
            d dVar = new d(context);
            dVar.b();
            for (int i = 0; i < 20; i++) {
                cn.jiguang.v.a a = dVar.a();
                if (a != null) {
                    return a;
                }
                Thread.sleep(2000L);
            }
            return null;
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("JLocation", "JLocationCellInfo call failed:" + th.getMessage());
            return null;
        }
    }
}
