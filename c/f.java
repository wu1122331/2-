package cn.jiguang.c;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class f implements Runnable {
    boolean a;
    Context b;
    b c;

    public f(boolean z, Context context, b bVar) {
        this.a = z;
        this.b = context;
        this.c = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (this.a) {
                b.a(this.c, this.b);
            } else {
                b.b(this.c, this.b);
            }
        } catch (Throwable unused) {
        }
    }
}
