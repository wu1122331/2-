package cn.jiguang.f;

import android.content.Context;
import android.os.Bundle;

/* loaded from: classes.dex */
public final class b implements Runnable {
    final /* synthetic */ a a;
    private Context b;
    private String c;
    private Bundle d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(a aVar, Context context, String str, Bundle bundle) {
        this.a = aVar;
        this.b = context;
        this.c = str;
        this.d = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            a.a(this.a, this.b, this.c, this.d);
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("JCommon", "BundleAction failed:" + th.getMessage());
        }
    }
}
