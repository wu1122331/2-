package cn.jiguang.c;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ b b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(b bVar, Context context) {
        this.b = bVar;
        this.a = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            b.a(this.b, this.a);
        } catch (Throwable unused) {
        }
    }
}
