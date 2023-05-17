package cn.jiguang.ag;

import android.util.Pair;
import java.util.LinkedHashSet;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class n implements Callable<LinkedHashSet<cn.jiguang.af.h>> {
    private String a;
    private m b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(String str, m mVar) {
        this.a = str;
        this.b = mVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ LinkedHashSet<cn.jiguang.af.h> call() {
        LinkedHashSet<cn.jiguang.af.h> a = m.a(this.a);
        if (a != null && a.size() > 0) {
            m.a(this.b, this.a, new Pair(a, Long.valueOf(System.currentTimeMillis())));
        }
        return a;
    }
}
