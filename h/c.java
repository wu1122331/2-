package cn.jiguang.h;

import android.content.Context;
import android.content.Intent;

/* loaded from: classes.dex */
public final class c implements Runnable {
    final /* synthetic */ b a;
    private Context b;
    private Intent c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(b bVar, Context context, Intent intent) {
        this.a = bVar;
        this.b = context;
        this.c = intent;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            b.a(this.a, this.b, this.c);
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("JAppMovement", "dealMovementAction throwable:" + th.getMessage());
        }
    }
}
