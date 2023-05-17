package cn.jiguang.f;

import android.content.Context;

/* loaded from: classes.dex */
public final class d implements Runnable {
    final /* synthetic */ a a;
    private Context b;
    private String c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(a aVar, Context context, String str) {
        this.a = aVar;
        this.b = context;
        this.c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            a.a(this.a, this.b, this.c);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
