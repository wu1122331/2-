package cn.jiguang.ab;

import android.content.Context;
import android.os.Bundle;

/* loaded from: classes.dex */
final class e implements Runnable {
    final /* synthetic */ d a;
    private String b;
    private Bundle c;
    private Context d;

    public e(d dVar, Context context, String str, Bundle bundle) {
        this.a = dVar;
        this.b = str;
        this.c = bundle;
        this.d = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            d.a.handleAction(this.d, this.b, this.c);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
