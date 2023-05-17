package cn.jiguang.f;

import android.content.Context;

/* loaded from: classes.dex */
public final class c implements Runnable {
    final /* synthetic */ a a;
    private Context b;
    private String c;

    public c(a aVar, Context context, String str) {
        this.a = aVar;
        this.b = context;
        this.c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            a.b(this.a, this.b, this.c);
        } catch (Throwable unused) {
        }
    }
}
